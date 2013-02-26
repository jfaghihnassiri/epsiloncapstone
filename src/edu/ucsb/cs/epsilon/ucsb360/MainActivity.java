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

/**
 * MainActivity for UCSB 360
 * Handles Facebook Log in and Log out, view without log in
 * @author Andy Chou
 */
public class MainActivity extends Activity implements OnClickListener {

	/*
	 * Privates
	 */
	private int DEBUG = 1;
	private Button authButton;
	private Button btnViewNoLogin;
    boolean pendingRequest;
    static final String applicationId = "560602963958157";
    static final String TAG = "EXE";
    static final String PENDING_REQUEST_BUNDLE_KEY = "com.facebook.samples.graphapi:PendingRequest";
    Session session;
    
    /**
     * Populates after the splash screen
     * @author Andy Chou
     * @param saved instance of the previous state
     * @return void
     */
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
	
	/**
	 * Creates a new session used for Facebook 
	 * @author Andy Chou
	 * @return A newly activated session
	 */
    private Session createSession() {
        Session activeSession = Session.getActiveSession();
        if (activeSession == null || activeSession.getState().isClosed()) {
            activeSession = new Session.Builder(this).setApplicationId(applicationId).build();
            Session.setActiveSession(activeSession);
        }
        return activeSession;
    }
	
    /**
     * Called after login operation is complete
     * @author Andy Chou
     * @param requestCode The requestCode parameter from the forwarded call. When this onActivityResult occurs as part of Facebook authorization flow, this value is the activityCode passed to open or authorize.
     * @param resultCode An int containing the resultCode parameter from the forwarded call.
     * @param data The Intent passed as the data parameter from the forwarded call.
     * 
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (this.session.onActivityResult(this, requestCode, resultCode, data) &&
                pendingRequest &&
                this.session.getState().isOpened()) {
    		Session.getActiveSession()
            .onActivityResult(this, requestCode, resultCode, data);
    		Intent i = new Intent(this, CameraView.class);
			startActivity(i);
        }
    }
    /**
     * (COPIED FROM GOOGLE ANDROID PAGE)
     * This method is called after onStart() when the activity is being re-initialized from a previously saved state, given here in savedInstanceState.
     *  Most implementations will simply use onCreate(Bundle) to restore their state, but it is sometimes convenient to do it here after all of the initialization has been done or to allow subclasses to decide whether to use your default implementation. 
     *	The default implementation of this method performs a restore of any view state that had previously been frozen by onSaveInstanceState(Bundle).
     *
     *
     *	THIS SHOULD NEVER BE CALLED AS OF 2/24/13
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(DEBUG == 1)
        {
        	Log.d(TAG,"RESTOR INSTANCE");
        }
        pendingRequest = savedInstanceState.getBoolean(PENDING_REQUEST_BUNDLE_KEY, pendingRequest);
    }
    /**
     * (COPIED FROM GOOGLE ANDROID PAGE)
     * Called to retrieve per-instance state from an activity before being killed so that the state can be restored in onCreate(Bundle) or onRestoreInstanceState(Bundle)
     *  (the Bundle populated by this method will be passed to both).
	 *	This method is called before an activity may be killed so that when it comes back some time in the future it can restore its state. 
	 * For example, if activity B is launched in front of activity A, and at some point activity A is killed to reclaim resources, 
	 *	activity A will have a chance to save the current state of its user interface via this method so that when the user returns to activity A, 
	 *	the state of the user interface can be restored via onCreate(Bundle) or onRestoreInstanceState(Bundle).
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(DEBUG == 1)
        {
        	Log.d(TAG,"SAVEINSTANCE");
        }
        outState.putBoolean(PENDING_REQUEST_BUNDLE_KEY, pendingRequest);
    }

    /**
     * Handles button Clicking
     * @author Andy Chou
     * @param the current view
     * 
     */
    public void onClick(View v) {
    	switch(v.getId())
    	{
    		//button for no log in view
    		case R.id.btnViewNoLogin : 
    		{
    			Intent i = new Intent(this, CameraView.class);
    			startActivity(i);
    			break;
    		}
    		//button for FB log in
    		case R.id.authButton:
    		{
    			if(this.session.isOpened())
    			{
    					//close session, then create a new one to init state
    				this.session.closeAndClearTokenInformation();
    				this.session = createSession();
    			}
    			else
    			{
    			StatusCallback callback = new StatusCallback() {
                    public void call(Session session, SessionState state, Exception exception) {
                    	//called when user quit FB log in, 
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
                // Used for FB Log in
                this.session.openForRead(new Session.OpenRequest(this).setCallback(callback));
    			
    			}
    			break;
    			 
    		}
    	}
    }
    
    
   
}
