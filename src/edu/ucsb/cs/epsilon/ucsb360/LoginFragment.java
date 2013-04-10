package edu.ucsb.cs.epsilon.ucsb360;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginFragment extends Fragment{
	
	private static final String TAG = "LoginFragment";
	private UiLifecycleHelper uiHelper;
	private Button btnViewNoLogin;
	private Session activeSession;
	private static boolean isLoggedIn;
	private String userId;
	private String userName;
	private String userBirthday;
	private String userLocation;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	   /* if(!DatabaseManager.isConnected())
	    {
	    	DatabaseManager.Connect();
	    }*/
	    DatabaseManager.Connect();
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.activity_main, container, false);
	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    
	    btnViewNoLogin = (Button)view.findViewById(R.id.btnViewNoLogin);
	    btnViewNoLogin.setOnClickListener(new OnClickListener()
	    
	    {
	    	@Override
	    	public void onClick(View v)
	    	{
	    		Log.d(TAG, "clickon that not button");
	    	    		Intent i = new Intent(getActivity(), com.qualcomm.QCARSamples.CloudRecognition.CloudReco.class);
	    	    		//Intent i = new Intent(getActivity(), SplashScreen.class);
	    	  	        getActivity().startActivity(i);
	    		
	    	}
	    });
	    return view;
	}
	//call when session state is changed
	/*
	 * session state changes when user logs in or out
	 */
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) {
	        Log.i(TAG, "Logged in...");
	        activeSession = Session.getActiveSession();
	        isLoggedIn = true;
	      
	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	        activeSession.closeAndClearTokenInformation();
	        isLoggedIn = false;
	    }
	    
	   
	}
	
	//listener for logging in and out
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	        
	        
	    }
	};
	/*
	 * THE FOLLOWING ARE OVERRIDE TO ENSURE PROPER FRAGMENT LIFECYCLE
	 */
	@Override
	public void onResume() {
	    super.onResume();
	    // For scenarios where the main activity is launched and user
	    // session is not null, the session state change notification
	    // may not be triggered. Trigger it if it's open/closed.
	    Session session = Session.getActiveSession();
	    if (session != null &&
	           (session.isOpened() || session.isClosed()) ) {
	        onSessionStateChange(session, session.getState(), null);
	    }

	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	    Log.i(TAG, "onActivityResult, should only be here after login");
	    //CODE FOR CAMERA 
	    Intent i = new Intent(getActivity(), com.qualcomm.QCARSamples.CloudRecognition.CloudReco.class);
	    getActivity().startActivity(i);
	    //CODE FOR USER STUFF
	    Request.executeMeRequestAsync(Session.getActiveSession(), new Request.GraphUserCallback() {
			
			@Override
			public void onCompleted(GraphUser user, Response response) {
				// TODO Auto-generated method stub
				Log.d(TAG, user.getBirthday() + " "+ user.getId() + user.getUsername()+" "+user.getName());
				userId = user.getId();
				userName= user.getName();
				userBirthday = user.getBirthday();
				getInfo(userId, userName, userBirthday);
			}
		});
	}

	/*
	 * Used to log facebook user info to SQL
	 */
	public void getInfo(String uId, String uName, String uBday)
	{
		boolean isUserRegistered = User.logIn(uId);
		if(!isUserRegistered)
		{
			User.createUser(uId, uName, uBday);
		}
		Log.i(TAG,"USERINFO");
	}
	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
	/*
	 * OVERRIDE ENDS
	 */
}
