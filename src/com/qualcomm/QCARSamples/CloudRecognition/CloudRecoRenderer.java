/*==============================================================================
            Copyright (c) 2012 QUALCOMM Austria Research Center GmbH.
            All Rights Reserved.
            Qualcomm Confidential and Proprietary

@file
    CloudRecoRenderer.java

@brief
    Sample for CloudReco

==============================================================================*/

package com.qualcomm.QCARSamples.CloudRecognition;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.example.android.apis.graphics.FingerPaint;
import com.qualcomm.QCAR.QCAR;
import com.qualcomm.QCARSamples.CloudRecognition.utils.DebugLog;

/** The renderer class for the CloudReco sample. */
public class CloudRecoRenderer implements GLSurfaceView.Renderer
{
    public boolean mIsActive = false;

    /** Reference to main activity **/
    public CloudReco mActivity;

    /** Native function for initializing the renderer. */
    public native void initRendering();

    /** Native function to update the renderer. */
    public native void updateRendering(int width, int height);


    //current view width and height
    private int mViewWidth = 0;
    private int mViewHeight = 0;
    
    //date
    private Date date;
    //boolean for taking Screen shot
    public boolean takeScreenShot = false;
    
    //HJC
    //disable scanning bar to take screen shot
    public native void enterScreenShotModeNative();
    
    //enable scanning bar back after took the screen shot
    public native void exitScreenShotModeNative();
    
    /** Called when the surface is created or recreated. */
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        // Call native function to initialize rendering:
        initRendering();

        // Call QCAR function to (re)initialize rendering after first use
        // or after OpenGL ES context was lost (e.g. after onPause/onResume):
        QCAR.onSurfaceCreated();
    }


    /** Called when the surface changed size. */
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        // Call native function to update rendering when render surface
        // parameters
        // have changed:
        updateRendering(width, height);
        
        mViewWidth = width;
        mViewHeight = height;
        
        FingerPaint.mHeight = height;
        
        // Call QCAR function to handle render surface size changes:
        QCAR.onSurfaceChanged(width, height);
    }


    /** The native render function. */
    public native void renderFrame();


    /** Called to draw the current frame. */
    public void onDrawFrame(GL10 gl)
    {
        if (!mIsActive)
        {
            return;
        }


        // Update render view (projection matrix and viewport) if needed:
        mActivity.updateRenderView();

        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        
        // Call our native function to render content
        renderFrame();

        // make sure the OpenGL rendering is finalized
        //GLES20.glFinish();
     
        GLES20.glFlush();
        
        if ( takeScreenShot ) {
        	saveScreenShot(0, 0, mViewWidth, mViewHeight);
            CloudReco.snapshot = grabPixels(0,0,mViewWidth, mViewHeight);
            Log.d("SNAPSHOT","snapshot taken in redner");
            takeScreenShot = false;
            CloudReco.imageReady = true;
         //   exitScreenShotModeNative();
        }
        
        
     

    }
    
    private void saveScreenShot(int x, int y, int w, int h) {
        Bitmap bmp = grabPixels(x, y, w, h);
        CloudReco.snapshot = bmp;
        try {
            GLES20.glFlush();
            GLES20.glFinish();
            
        } catch (Exception e) {
            DebugLog.LOGD(e.getStackTrace().toString());
        }
        
    }
  
    public Bitmap grabPixels(int x, int y, int w, int h) {
        int b[] = new int[w * (y + h)];
        int bt[] = new int[w * h];
        IntBuffer ib = IntBuffer.wrap(b);
        ib.position(0);
         
        GLES20.glReadPixels(x, 0, w, y + h, 
                   GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, ib);
  
        for (int i = 0, k = 0; i < h; i++, k++) {
            for (int j = 0; j < w; j++) {
                int pix = b[i * w + j];
                int pb = (pix >> 16) & 0xff;
                int pr = (pix << 16) & 0x00ff0000;
                int pix1 = (pix & 0xff00ff00) | pr | pb;
                bt[(h - k - 1) * w + j] = pix1;
            }
        }
  
        Bitmap sb = Bitmap.createBitmap(bt, w, h, Bitmap.Config.ARGB_8888);
        GLES20.glFlush();
        return sb;
    }
    
    public void takeScreenShot()
    {
    //	enterScreenShotModeNative();
    	takeScreenShot = true;
        date = new Date();
    }
    
    public final class takeSnapShot extends AsyncTask<Void, Void, Bitmap>{
    	
    	@Override
    	protected Bitmap doInBackground(Void... params){
    	//	enterScreenShotModeNative();
    		Bitmap temp = grabPixels(0,0,mViewWidth, mViewHeight);
        	
    		CloudReco.snapshot = grabPixels(0,0,mViewWidth, mViewHeight);
    		
	      	  if(CloudReco.snapshot != null){
	      		DebugLog.LOGD("XXXXXXXX: Something in SnapShot");
	    	  }
	    	  else{
	    		  DebugLog.LOGD("XXXXXXXX: snapShot is NULLLLLLL");
	    	  }
    		
    	//	exitScreenShotModeNative();
    		
			return temp;
    	}
    	
    	protected void onPostExecute(Bitmap temp){
    		CloudReco.snapshot = temp;
    		DebugLog.LOGD("XXXXXXX: AsyncTask finished" );
    	}
    }
    
    public byte[] bitToByte(Bitmap input){
    	byte[] temp = null;
  	  	ByteArrayOutputStream stream = new ByteArrayOutputStream();
  	  	input.compress(Bitmap.CompressFormat.PNG, 100, stream);
  	  	temp = stream.toByteArray();
    	return temp;
    }
    
}
