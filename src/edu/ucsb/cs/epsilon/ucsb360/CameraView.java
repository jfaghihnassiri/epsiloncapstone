package edu.ucsb.cs.epsilon.ucsb360;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.qualcomm.QCAR.QCAR;

public class CameraView extends Activity {

	//private Camera camera;
    //private SurfaceHolder camSurfaceHolder;
    
    //private native void startCamera();
	
	  private SurfaceView preview=null;
	  private SurfaceHolder previewHolder=null;
	  private Camera camera=null;
	  private boolean inPreview=false;
	  private boolean cameraConfigured=false;
	
	  @SuppressWarnings("deprecation")
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_camera_view);
	    preview=(SurfaceView)findViewById(R.id.surface_camera);
	    previewHolder=preview.getHolder();
	    previewHolder.addCallback(surfaceCallback);
	    previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	  }


	  @Override
	  public void onResume() {
	    super.onResume();
	    camera=Camera.open();
	    startPreview();
	  }
	    
	  @Override
	  public void onPause() {
	    if (inPreview) {
	      camera.stopPreview();
	    }
	    
	    camera.release();
	    camera=null;
	    inPreview=false;
	          
	    super.onPause();
	  }
	  
	  private void initPreview(int width, int height) {
	    if (camera!=null && previewHolder.getSurface()!=null) {
	      try {
	        camera.setPreviewDisplay(previewHolder);
	      }
	      catch (Throwable t) { }

	      if (!cameraConfigured) {
	        Camera.Parameters parameters=camera.getParameters();
	        //Camera.Size size=getBestPreviewSize(width, height, parameters);
	        
	        //if (size!=null) {
//	          parameters.setPreviewSize(size.width, size.height);
	          //parameters.setPreviewSize(width, height);
	        	
	          camera.setDisplayOrientation(90);
	          camera.setParameters(parameters);
	          cameraConfigured=true;
	        //}
	      }
	    }
	  }
	  
	  private void startPreview() {
	    if (cameraConfigured && camera!=null) {
	      camera.startPreview();
	      inPreview=true;
	    }
	  }
	  
	  SurfaceHolder.Callback surfaceCallback=new SurfaceHolder.Callback() {
	    public void surfaceCreated(SurfaceHolder holder) {
	      // no-op -- wait until surfaceChanged()
	    }
	    
	    public void surfaceChanged(SurfaceHolder holder,
	                               int format, int width,
	                               int height) {
	      initPreview(width, height);
	      startPreview();
	    }
	    
	    public void surfaceDestroyed(SurfaceHolder holder) {
	      // no-op
	    }
	  };
	 
}	
	