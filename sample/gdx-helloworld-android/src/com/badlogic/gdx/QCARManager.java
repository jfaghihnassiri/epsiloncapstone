package com.badlogic.gdx;

public class QCARManager 
{
	 /** Native sample initialization. */
    public native void onQCARInitializedNative();    
        
    /** Native methods for starting camera. */ 
    public native void startCamera();
    
    /** Native methods for stopping camera. */
    public native void stopCamera();

    /** Native methods for rendering camera background. */
    public native float[] renderFrame();
    
    /** Tells native code whether we are in portait or landscape mode */
    public native void setActivityPortraitMode(boolean isPortrait);
    
    /** Native function to initialize the application. */
    public native void initApplicationNative(int width, int height);
    
    /** Native function to update rendering. */
    public native void updateRendering(int width, int height);
    
    /** Native function to deinitialize the application.*/
    public native void deinitApplicationNative();
    
    /** Native function to get projection matrix.*/
    public native float[] getProjectionMatrix();
}
