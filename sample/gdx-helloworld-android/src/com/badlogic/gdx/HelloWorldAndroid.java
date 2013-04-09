/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
/*
 * Copyright 2010 Mario Zechner (contact@badlogicgames.com), Nathan Sweet (admin@esotericsoftware.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.badlogic.gdx;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.qualcomm.QCAR.QCAR;

public class HelloWorldAndroid extends AndroidApplication 
{
	private static final String TAG = "HelloWorldAndroid";
	
	private HelloWorld mHelloWorld; 
	
	// Application status constants:
    private static final int APPSTATUS_UNINITED         = -1;
    private static final int APPSTATUS_INIT_APP         = 0;
    private static final int APPSTATUS_INIT_QCAR        = 1;
    private static final int APPSTATUS_INIT_APP_AR      = 2;
    private static final int APPSTATUS_INIT_TRACKER     = 3;
    private static final int APPSTATUS_INITED           = 4;
    private static final int APPSTATUS_CAMERA_STOPPED   = 5;
    private static final int APPSTATUS_CAMERA_RUNNING   = 6;

    // Name of the native dynamic libraries to load:
	private static final String NATIVE_LIB_QCAR = "QCAR";
    private static final String NATIVE_LIB_IMAGETARGETS = "ImageTargets";
    
    // Display size of the device
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    
    // The current application status
    private int mAppStatus = APPSTATUS_UNINITED;
    
    // The async tasks to initialize the QCAR SDK 
    private InitQCARTask mInitQCARTask;
    private LoadTrackerTask mLoadTrackerTask;

    // QCAR initialization flags
    private int mQCARFlags = QCAR.GL_11;
    
    // The minimum time the splash screen should be visible:
    private static final long MIN_SPLASH_SCREEN_TIME = 2000;
    
    // The time when the splash screen has become visible:
    long mSplashScreenStartTime = 0;
    
    // The view to display the sample splash screen:
    private ImageView mSplashScreenView;
    
    private int mSplashScreenImageResource = 0;
    
    /** Static initializer block to load native libraries on start-up. */
    static
    {
        loadLibrary(NATIVE_LIB_QCAR);
        loadLibrary(NATIVE_LIB_IMAGETARGETS);
    }
    
	@Override
	public void onCreate (Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		Log.d(TAG, "before update application status");
        updateApplicationStatus(APPSTATUS_INIT_APP);
        Log.d(TAG, "after update application status");
        
        mHelloWorld = new HelloWorld( );
		initialize(mHelloWorld, false);
	}
	
	@Override
	public void onDestroy () 
	{
		super.onDestroy();
		
		if (mInitQCARTask != null &&
				mInitQCARTask.getStatus() != InitQCARTask.Status.FINISHED)
        {
            mInitQCARTask.cancel(true);
            mInitQCARTask = null;
        }
        
        if (mLoadTrackerTask != null &&
            mLoadTrackerTask.getStatus() != LoadTrackerTask.Status.FINISHED)
        {
            mLoadTrackerTask.cancel(true);
            mLoadTrackerTask = null;
        }
        
        // Do application deinitialization in native code
        mHelloWorld.mQCARManager.deinitApplicationNative();
        
        // Deinitialize QCAR SDK
        QCAR.deinit();
        
        System.gc();

	}
	
	/** An async task to initialize QCAR asynchronously. */
    private class InitQCARTask extends AsyncTask<Void, Integer, Boolean>
    {   
        // Initialize with invalid value
        private int mProgressValue = -1;
        
        protected Boolean doInBackground(Void... params)
        {
        	Log.d(TAG,"before setInitParameters");
        	
            QCAR.setInitParameters(HelloWorldAndroid.this, mQCARFlags);
            
            Log.d(TAG,"after setInitParameters");
            
            do
            {
                // QCAR.init() blocks until an initialization step is complete,
                // then it proceeds to the next step and reports progress in
                // percents (0 ... 100%)
                // If QCAR.init() returns -1, it indicates an error.
                // Initialization is done when progress has reached 100%.
                mProgressValue = QCAR.init();
                
                // Publish the progress value:
                publishProgress(mProgressValue);
                
                // We check whether the task has been canceled in the meantime
                // (by calling AsyncTask.cancel(true))
                // and bail out if it has, thus stopping this thread.
                // This is necessary as the AsyncTask will run to completion
                // regardless of the status of the component that started is.
            } while (!isCancelled() && mProgressValue >= 0 && mProgressValue < 100);
            
            return (mProgressValue > 0);
        }

        
        protected void onProgressUpdate(Integer... values)
        {
            // Do something with the progress value "values[0]", e.g. update
            // splash screen, progress bar, etc.
        }

        
        protected void onPostExecute(Boolean result)
        {
            // Done initializing QCAR, proceed to next application
            // initialization status:
            if (result)
            {
                DebugLog.LOGD("InitQCARTask::onPostExecute: QCAR initialization" +
                                                            " successful");

                updateApplicationStatus(APPSTATUS_INIT_APP_AR);
            }
            else
            {
                // Create dialog box for display error:
                AlertDialog dialogError = new AlertDialog.Builder(HelloWorldAndroid.this).create();
                dialogError.setButton(
                    "Close",
                    new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            // Exiting application
                            System.exit(1);
                        }
                    }
                ); 
                
                String logMessage;

                // NOTE: Check if initialization failed because the device is
                // not supported. At this point the user should be informed
                // with a message.
                if (mProgressValue == QCAR.INIT_DEVICE_NOT_SUPPORTED)
                {
                    logMessage = "Failed to initialize QCAR because this " +
                        "device is not supported.";
                }
                else if (mProgressValue ==
                            QCAR.INIT_CANNOT_DOWNLOAD_DEVICE_SETTINGS)
                {
                    logMessage = 
                        "Network connection required to initialize camera " +
                        "settings. Please check your connection and restart " +
                        "the application. If you are still experiencing " +
                        "problems, then your device may not be currently " +
                        "supported.";
                }
                else
                {
                    logMessage = "Failed to initialize QCAR.";
                }
                
                // Log error:
                DebugLog.LOGE("InitQCARTask::onPostExecute: " + logMessage +
                                " Exiting.");
                
                // Show dialog box with error message:
                dialogError.setMessage(logMessage);  
                dialogError.show();
            }
        }
    }
    
    
    /** An async task to load the tracker data asynchronously. */
    private class LoadTrackerTask extends AsyncTask<Void, Integer, Boolean>
    {
        protected Boolean doInBackground(Void... params)
        {
            // Initialize with invalid value
            int progressValue = -1;

            do
            {
                progressValue = QCAR.load();
                publishProgress(progressValue);
                
            } while (!isCancelled() && progressValue >= 0 &&
                        progressValue < 100);
            
            return (progressValue > 0);
        }
        
        
        protected void onProgressUpdate(Integer... values)
        {
            // Do something with the progress value "values[0]", e.g. update
            // splash screen, progress bar, etc.
        }
        
        
        protected void onPostExecute(Boolean result)
        {
            DebugLog.LOGD("LoadTrackerTask::onPostExecute: execution " +
                        (result ? "successful" : "failed"));

            // Done loading the tracker, update application status: 
            updateApplicationStatus(APPSTATUS_INITED);
        }
    }
    
    /** NOTE: this method is synchronized because of a potential concurrent
     * access by ImageTargets::onResume() and InitQCARTask::onPostExecute(). */
    private synchronized void updateApplicationStatus(int appStatus)
    {
        // Exit if there is no change in status
        if (mAppStatus == appStatus)
            return;

        // Store new status value      
        mAppStatus = appStatus;

        // Execute application state-specific actions
        switch (mAppStatus)
        {
            case APPSTATUS_INIT_APP:
            	Log.d(TAG, "APPSTATUS: init");
                // Initialize application elements that do not rely on QCAR
                // initialization  
                initApplication();
                
                // Proceed to next application initialization status
                updateApplicationStatus(APPSTATUS_INIT_QCAR);
                break;

            case APPSTATUS_INIT_QCAR:
            	Log.d(TAG, "APPSTATUS: qcar init");
                // Initialize QCAR SDK asynchronously to avoid blocking the
                // main (UI) thread.
                // This task instance must be created and invoked on the UI
                // thread and it can be executed only once!
                try
                {
                	Log.d(TAG, "inside app status qcar init1");
                    mInitQCARTask = new InitQCARTask();
                    Log.d(TAG, "inside app status qcar init2");
                    mInitQCARTask.execute();
                    Log.d(TAG, "inside app status qcar init3");
                }
                catch (Exception e)
                {
                	Log.d(TAG,"Initializing QCAR SDK failed");
                }
                break;
                
            case APPSTATUS_INIT_APP_AR:
            	Log.d(TAG, "APPSTATUS: init ar app");
                // Initialize Augmented Reality-specific application elements
                // that may rely on the fact that the QCAR SDK has been
                // already initialized
                initApplicationAR();
                
                // Proceed to next application initialization status
                updateApplicationStatus(APPSTATUS_INIT_TRACKER);
                break;
                
            case APPSTATUS_INIT_TRACKER:
            	Log.d(TAG, "APPSTATUS: init tracker");
                // Load the tracking data set
                //
                // This task instance must be created and invoked on the UI
                // thread and it can be executed only once!
                try
                {
                    mLoadTrackerTask = new LoadTrackerTask();
                    mLoadTrackerTask.execute();
                }
                catch (Exception e)
                {
                    DebugLog.LOGE("Loading tracking data set failed");
                }
                break;
                
            case APPSTATUS_INITED:
            	Log.d(TAG, "APPSTATUS: status inited");
                // Hint to the virtual machine that it would be a good time to
                // run the garbage collector.
                //
                // NOTE: This is only a hint. There is no guarantee that the
                // garbage collector will actually be run.
                System.gc();

                // Native post initialization:
                //pg: commenting this out
                //onQCARInitializedNative();
                
                // The elapsed time since the splash screen was visible:
                //long splashScreenTime = System.currentTimeMillis() - mSplashScreenStartTime;
                //long newSplashScreenTime = 0;
                //if (splashScreenTime < MIN_SPLASH_SCREEN_TIME)
                //{
                //    newSplashScreenTime = MIN_SPLASH_SCREEN_TIME -
                 //                           splashScreenTime;   
                //}
                
                // Request a callback function after a given timeout to dismiss
                // the splash screen:
                
                Log.d(TAG, "APPSTATUS: status inited - new handler");
                
                Handler handler = new Handler();
                
                handler.postDelayed(
                    new Runnable() {
                        public void run()
                        {
                            // Hide the splash screen
                            //mSplashScreenView.setVisibility(View.INVISIBLE);
                            
                            // Activate the renderer
                            //pg: commenting this out
                            //mRenderer.mIsActive = true;

                            // Now add the GL surface view. It is important
                            // that the OpenGL ES surface view gets added
                            // BEFORE the camera is started and video
                            // background is configured.
                            // pg: commenting this out
                            //addContentView(mGlView, new LayoutParams(
                             //               LayoutParams.FILL_PARENT,
                             //               LayoutParams.FILL_PARENT));
                            
                            // Start the camera:
                            updateApplicationStatus(APPSTATUS_CAMERA_RUNNING);
                        }
                    }
                    //, newSplashScreenTime);                
                    , 0);
                                
                
                break;
                
            case APPSTATUS_CAMERA_STOPPED:
            	Log.d(TAG, "APPSTATUS: camera stopped");
                // Call the native function to stop the camera
                mHelloWorld.mQCARManager.stopCamera();
                break;
                
            case APPSTATUS_CAMERA_RUNNING:
            	Log.d(TAG, "APPSTATUS: camera running");
                // Call the native function to start the camera
            	mHelloWorld.mQCARManager.startCamera();
            	mHelloWorld.qcarInited = true;           	
                break;
                
            default:
                throw new RuntimeException("Invalid application state");
        }
    }
    
    /** Initialize application GUI elements that are not related to AR. */
    private void initApplication()
    {
        // Set the screen orientation
        //
        // NOTE: It is recommended to set this because of the following reasons:
        //
        //    1.) Before Android 2.2 there is no reliable way to query the
        //        absolute screen orientation from an activity, therefore using 
        //        an undefined orientation is not recommended. Screen 
        //        orientation matching orientation sensor measurements is also
        //        not recommended as every screen orientation change triggers
        //        deinitialization / (re)initialization steps in internal QCAR 
        //        SDK components resulting in unnecessary overhead during 
        //        application run-time.
        //
        //    2.) Android camera drivers seem to always deliver landscape images
        //        thus QCAR SDK components (e.g. camera capturing) need to know 
        //        when we are in portrait mode. Before Android 2.2 there is no 
        //        standard, device-independent way to let the camera driver know 
        //        that we are in portrait mode as each device seems to require a
        //        different combination of settings to rotate camera preview 
        //        frames images to match portrait mode views. Because of this,
        //        we suggest that the activity using the QCAR SDK be locked
        //        to landscape mode if you plan to support Android 2.1 devices
        //        as well. Froyo is fine with both orientations.
    	
    	Log.d(TAG, "inside initApplication1");
    	
        int screenOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        
        Log.d(TAG, "inside initApplication2");
        
        // Apply screen orientation
        setRequestedOrientation(screenOrientation);
        
        Log.d(TAG, "inside initApplication3");
        
        // Pass on screen orientation info to native code
        //mHelloWorld.mQCARManager.setActivityPortraitMode(screenOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        Log.d(TAG, "inside initApplication4");
        
        // Query display dimensions
        DisplayMetrics metrics = new DisplayMetrics();
        (HelloWorldAndroid.this).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;

        Log.d(TAG, "inside initApplication5");
        
        // As long as this window is visible to the user, keep the device's
        // screen turned on and bright.
        //pg: commenting this out
        //((Activity)mContext).getWindow().setFlags(
         //   WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
         //   WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        Log.d(TAG, "inside initApplication6");
        
        // Create and add the splash screen view
      //pg: commenting this out
        //mSplashScreenView = new ImageView(((Activity)mContext));
        //mSplashScreenView.setImageResource(mSplashScreenImageResource);
        //((Activity)mContext).addContentView(mSplashScreenView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        
        Log.d(TAG, "inside initApplication7");
        
        mSplashScreenStartTime = System.currentTimeMillis();
        
        Log.d(TAG, "inside initApplication8");

    }
    
    /** Initializes AR application components. */
    private void initApplicationAR()
    {        
        // Do application initialization in native code (e.g. registering
        // callbacks, etc.)
    	//pg: commenting this out
    	mHelloWorld.mQCARManager.initApplicationNative(mScreenWidth, mScreenHeight);    	

        // Create OpenGL ES view:
        int depthSize = 16;
        int stencilSize = 0;
        boolean translucent = QCAR.requiresAlpha();
        
        //pg: commenting this out
        //mGlView = new QCARSampleGLView(this);
        //mGlView.init(mQCARFlags, translucent, depthSize, stencilSize);
        
        //mRenderer = new ImageTargetsRenderer(this);
        //mGlView.setRenderer(mRenderer);
 
    }
	
	/** A helper for loading native libraries stored in "libs/armeabi*". */
    public static boolean loadLibrary(String nLibName)
    {
        try
        {
            System.loadLibrary(nLibName);
     //       DebugLog.LOGI("Native library lib" + nLibName + ".so loaded");
            return true;
        }
        catch (UnsatisfiedLinkError ulee)
        {
       //     DebugLog.LOGE("The library lib" + nLibName +
           //                 ".so could not be loaded");
        }
        catch (SecurityException se)
        {
         //   DebugLog.LOGE("The library lib" + nLibName +
         //                   ".so was not allowed to be loaded");
        }

        return false;
    }
}
