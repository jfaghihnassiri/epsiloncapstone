package edu.ucsb.cs.epsilon.ucsb360;

import com.facebook.LoggingBehavior;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.Session.StatusCallback;
import com.facebook.android.Facebook;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	/*
	 * Privates
	 */
	private Button authButton;
	private Button btnViewNoLogin;
    boolean pendingRequest;
    static final String applicationId = "560602963958157";
    static final String TAG = "EXE";
    static final String PENDING_REQUEST_BUNDLE_KEY = "com.facebook.samples.graphapi:PendingRequest";
    Session session;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*button for the View without Login
		 * 
		 * */
		btnViewNoLogin = (Button)findViewById(R.id.btnViewNoLogin);
		btnViewNoLogin.setOnClickListener(this);
		authButton = (Button)findViewById(R.id.authButton);
		authButton.setOnClickListener(this);
		
        this.session = createSession();
        Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
	}
	
    private Session createSession() {
        Session activeSession = Session.getActiveSession();
        if (activeSession == null || activeSession.getState().isClosed()) {
            activeSession = new Session.Builder(this).setApplicationId(applicationId).build();
            Session.setActiveSession(activeSession);
        }
        return activeSession;
    }
	
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (this.session.onActivityResult(this, requestCode, resultCode, data) &&
                pendingRequest &&
                this.session.getState().isOpened()) {
    		Session.getActiveSession()
            .onActivityResult(this, requestCode, resultCode, data);
    		Intent i = new Intent(this, CameraView.class);
			startActivity(i);
			Log.d(TAG, "onActivityResult OPEN");
        }


    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        pendingRequest = savedInstanceState.getBoolean(PENDING_REQUEST_BUNDLE_KEY, pendingRequest);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(PENDING_REQUEST_BUNDLE_KEY, pendingRequest);
    }
    
    public void onClick(View v) {
    	switch(v.getId())
    	{
    		case R.id.btnViewNoLogin : 
    		{
    			Intent i = new Intent(this, CameraView.class);
    			startActivity(i);
    			break;
    		}
    		case R.id.authButton:
    		{
    			if(this.session.isOpened())
    			{
    				Log.d(TAG,"ISOPEN TRUE");
    				this.session.closeAndClearTokenInformation();
    				pendingRequest = false;
    				Log.d(TAG,"should be false");
    				this.session = createSession();
    			}
    			else
    			{
    			StatusCallback callback = new StatusCallback() {
                    public void call(Session session, SessionState state, Exception exception) {
                        if (exception != null) {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle(R.string.login_failed_dialog_title)
                                    .setMessage(exception.getMessage())
                                    .setPositiveButton(R.string.ok_button, null)
                                    .show();
                            MainActivity.this.session = createSession();
                        }
       
                    }
                    
                };
                pendingRequest = true;
                this.session.openForRead(new Session.OpenRequest(this).setCallback(callback));
    			
    			}
    			break;
    			 
    		}
    	}
    }
    
    
   
}
