package edu.ucsb.cs.epsilon.ucsb360;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.qualcomm.QCARSamples.CloudRecognition.utils.DebugLog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class LoginFragment extends Fragment{
	
	private static final String TAG = "LoginFragment";
	private UiLifecycleHelper uiHelper;
	private Button btnViewNoLogin;
	private Button btnViewLogin;
	private Button btnOntopFB;
	public static Session activeSession;
	private static boolean isLoggedIn;
	private static String userId;
	private static String userName;
	private static String userBirthday;
	private String userLocation;
	private static MainActivity activity;
	private static Vector<String>friendList;
	private static ArrayList<String>friendList2 = new ArrayList<String>();
	private TranslateAnimation translateLeft;
	private TranslateAnimation translateRight;
	/*FB PRIVATES*/
	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");
	private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
	private static final String FRIEND_LIST = "friendList";
	private static final String USER_ID = "userId";
	private static final String USER_NAME= "userName";
	private static final String USER_BIRTHDAY=" userBirthday";
	private boolean pendingPublishReauthorization = false;
	private LoginButton authButton;
	/*					*/
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    if(savedInstanceState != null)
	    {
	    	Log.d(TAG,"in oncreatesavedInstance");
	    	friendList2 = savedInstanceState.getStringArrayList(FRIEND_LIST);
	    	userId = savedInstanceState.getString(USER_ID);
	    	userName = savedInstanceState.getString(USER_NAME);
	    	userBirthday = savedInstanceState.getString(USER_BIRTHDAY);
	    	Log.d(TAG,"Complete oncreate restor");
	    }
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.activity_main, container, false);
	    authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    authButton.setReadPermissions(Arrays.asList("read_friendlists"));
	    
	    friendList = new Vector<String>();
	    activeSession = Session.getActiveSession();
	    Log.d("FB","PASSED ACTIVE SESSION IN ONCREATEVIEW");
	    activity  = (MainActivity) getActivity();
	    btnViewLogin = (Button)view.findViewById(R.id.btnViewLogin);
	    btnViewNoLogin = (Button)view.findViewById(R.id.btnViewNoLogin);
	    btnOntopFB = (Button)view.findViewById(R.id.btnOntopFB);
	    btnViewNoLogin.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
	    	public void onClick(View v)
	    	{
	    		if (isNetworkAvailable(getActivity()))
	    		{
					Log.d(TAG, "clickon that not button");
		    		Intent i = new Intent(getActivity(), com.qualcomm.QCARSamples.CloudRecognition.CloudReco.class);
		    		//Intent i = new Intent(getActivity(), SplashScreen.class);
		  	        getActivity().startActivity(i);
		  	        //publishStory();
	    		}
	    		else
      			  Toast.makeText(getActivity(), "Please connect to the internet before using three60", Toast.LENGTH_SHORT).show(); 

	    	}
	    });
	    
	    
	    btnOntopFB.setOnClickListener(new OnClickListener() 
	    {
	    	public void onClick(View v)
	    	{
	    		DebugLog.LOGD("Ontop click");
	    		if (!isNetworkAvailable(getActivity()))
	    		{
	    			Toast.makeText(getActivity(), "Please connect to the internet before using three60", Toast.LENGTH_SHORT).show();
	    			//onResume();
	    		}
	    		else
	    			authButton.performClick();	    			
	    	}
	    });
	    
	    if (savedInstanceState != null) {
	        pendingPublishReauthorization = 
	            savedInstanceState.getBoolean(PENDING_PUBLISH_KEY, false);
	    }
	    
	    // Animate the buttons
	    translateLeft = new TranslateAnimation(480, 0, 0, 0);
	    translateLeft.setDuration(1000);
	    translateLeft.setFillAfter(true);
	    
	    translateRight = new TranslateAnimation(-480, 0, 0, 0);
	    translateRight.setDuration(1000);
	    translateRight.setFillAfter(true);
	    
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

			//CODE FOR FRIENDS STUFF
			if(activeSession.isOpened()&& activeSession!= null && userId == null && Session.openActiveSessionFromCache(getActivity())!= null) {
				Request.executeMyFriendsRequestAsync(Session.getActiveSession(), new Request.GraphUserListCallback() {

					@Override
					public void onCompleted(List<GraphUser> users, Response response) {
						// TODO Auto-generated method stub
						if(activeSession == Session.getActiveSession())
						{
							Log.i(TAG, "Friend List...");
							if(users != null){
								for (int i = 0; i < users.size(); i++)
								{
									friendList.addElement(users.get(i).getId());
									friendList2.add(users.get(i).getId());
									
								}
							}
							else
							{
								Log.d(TAG,"something went wrong in friends list");
							}
						}
						
					}

				});

				//CODE FOR USER STUFF
				Request.executeMeRequestAsync(Session.getActiveSession(), new Request.GraphUserCallback() {

					@Override
					public void onCompleted(GraphUser user, Response response) {
						// TODO Auto-generated method stub
						if(activeSession == Session.getActiveSession())
						{
							if(user != null)
							{
								Log.d(TAG, user.getBirthday() + " "+ user.getId() + user.getUsername()+" "+user.getName());
								userId = user.getId();
								userName= user.getName();
								userBirthday = user.getBirthday();
								getInfo(userId, userName, userBirthday);
								Log.i(TAG, "User Info");
							}
							else
							{
								Log.d("TAG", "something went wrong in userinfo");
							}
						}

					}
				});
				
			}

	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	        userId = null;
	        userName = null;
	        userBirthday = null;
	        User.logOut();
	        activeSession.closeAndClearTokenInformation();
	    }
	    
	   
	}
	
	//listener for logging in and out
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	    	if(state.equals(SessionState.CLOSED) || state.equals(SessionState.CLOSED_LOGIN_FAILED))
	    	{
	    		onSessionStateChange(session, state, exception);
	    	}
	    	else if(state.equals(SessionState.OPENED))
	    	{
	    		onSessionStateChange(session, state, exception);
	    	}
	        
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

	    pendingPublishReauthorization = false;
	    uiHelper.onResume();
	    
	    btnViewNoLogin.startAnimation(translateLeft);
	    btnViewLogin.startAnimation(translateRight);
	    authButton.startAnimation(translateRight);
	}

	/**
	 * 
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	    Log.i(TAG, "onActivityResult, should only be here after login");
	    activeSession = Session.getActiveSession();
	    //CODE FOR PERMISSION MOVED
	    if(Session.getActiveSession() != null){
		    //For user permission to post
	    	Session.getActiveSession().onActivityResult(getActivity(), requestCode, resultCode, data);
		    Session session = Session.getActiveSession();
		    if (session != null && session.isOpened()){
		    	Log.d("TAG ANDY","PUBLISH STORY");/////////////////////
		        // Check for publish permissions    
		        List<String> permissions = session.getPermissions();
		        Log.d("TAG ANDY", permissions.toString());/////////////////////
		        if (!isSubsetOf(PERMISSIONS, permissions)) {
		        	Log.d("TAG ANDY","AUTHROZIATION");////////////////////
		            pendingPublishReauthorization = true;
		            Session.NewPermissionsRequest newPermissionsRequest = new Session
		                    .NewPermissionsRequest(this, PERMISSIONS);
		        session.requestNewPublishPermissions(newPermissionsRequest);
		            return;
		        }
		    }
	    }
	    //CODE FOR CAMERA 
	    Intent i = new Intent(getActivity(), com.qualcomm.QCARSamples.CloudRecognition.CloudReco.class);
	    getActivity().startActivity(i);
	  
	}

	/*
	 * Used to log facebook user info to SQL
	 */
	public void getInfo(String uId, String uName, String uBday)
	{
		if(DatabaseManager.isConnected() && !Global.isLoggingIn())
			DatabaseManager.login(uId, uName, uBday);
		Log.i(TAG,"USERINFO");
	}
	private boolean isSubsetOf(Collection<String> subset, Collection<String> superset) {
	    for (String string : subset) {
	        if (!superset.contains(string)) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static Bundle publishStory(){
		 Bundle postParams = new Bundle();

		 	postParams.putString("message", "TESTING 2ND WAY");
	        return postParams;
	        
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
	    outState.putBoolean(PENDING_PUBLISH_KEY, pendingPublishReauthorization);
	    outState.putStringArrayList(FRIEND_LIST, friendList2);
	    outState.putString(USER_ID, userId);
	    outState.putString(USER_NAME, userName);
	    outState.putSerializable(USER_BIRTHDAY, userBirthday);
	    uiHelper.onSaveInstanceState(outState);
	}
	

	public static Vector<String> getFriends()
	{
		return friendList;
	}
	
	public static ArrayList<String> getFriendList()
	{
		return friendList2;
	}
	/*
	 * OVERRIDE ENDS
	 */
	
	public static String getUserId() {
		return userId;
	}
	
	public static String getUserName() {
		return userName;
	}
	
	public static String getBirthday() {
		return userBirthday;
	}
	
	// LTB - checks for internet connection
	private boolean isNetworkAvailable(Context context) {
	    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
}
