package edu.ucsb.cs.epsilon.ucsb360;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

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

import edu.ucsb.cs.epsilon.ucsb360.database.UserLoginTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
	
	/*FB PRIVATES*/
	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");
	private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
	private boolean pendingPublishReauthorization = false;
	/*					*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

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
	    	  	        //publishStory();
	    	}
	    });
	    
	    if (savedInstanceState != null) {
	        pendingPublishReauthorization = 
	            savedInstanceState.getBoolean(PENDING_PUBLISH_KEY, false);
	    }
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
	        User.setLoggedIn();	      
	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	        activeSession.closeAndClearTokenInformation();
	        User.logOut();
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
	    pendingPublishReauthorization = false;
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
	    
	    //For user permission to post
	    Session session = Session.getActiveSession();
	    if (session != null){
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
	    //postToFb();
	}

	/*
	 * Used to log facebook user info to SQL
	 */
	public void getInfo(String uId, String uName, String uBday)
	{

		new UserLoginTask().execute(uId, uName, uBday);
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
	
	private Bundle publishStory(){
		 Bundle postParams = new Bundle();
	        /*postParams.putString("name", "Facebook UCSB360 OFFICIAL");
	        postParams.putString("caption", "UCSB 360 Post to Fb caption");
	        postParams.putString("description", "UCSB 360 ANDROID TEST");
	        postParams.putString("link", "https://developers.facebook.com/android");
	        postParams.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");*/

		 	//postParams.putString("message", "OMG IT WORKS");
	        return postParams;
	        
	}
	
	private void postToFb()
	{
		Session session = Session.getActiveSession();
        Request.Callback callback= new Request.Callback() {
            public void onCompleted(Response response) {
                JSONObject graphResponse = response
                                           .getGraphObject()
                                           .getInnerJSONObject();
                String postId = null;
                try {
                    postId = graphResponse.getString("id");
                } catch (JSONException e) {
                    Log.i(TAG,
                        "JSON error "+ e.getMessage());
                }
                FacebookRequestError error = response.getError();
                if (error != null) {
                    Toast.makeText(getActivity()
                         .getApplicationContext(),
                         error.getErrorMessage(),
                         Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity()
                             .getApplicationContext(), 
                             postId,
                             Toast.LENGTH_LONG).show();
                }
            }
        };

        Request request = new Request(session, "me/feed", publishStory(), 
                              HttpMethod.POST, callback);

        RequestAsyncTask task = new RequestAsyncTask(request);
        task.execute();
	}
/*	private void publishStory() {
	    

	        Bundle postParams = new Bundle();
	        postParams.putString("name", "Facebook UCSB360 OFFICIAL");
	        postParams.putString("caption", "UCSB 360 Post to Fb caption");
	        postParams.putString("description", "UCSB 360 ANDROID TEST");

	        Request.Callback callback= new Request.Callback() {
	            public void onCompleted(Response response) {
	                JSONObject graphResponse = response
	                                           .getGraphObject()
	                                           .getInnerJSONObject();
	                String postId = null;
	                try {
	                    postId = graphResponse.getString("id");
	                } catch (JSONException e) {
	                    Log.i(TAG,
	                        "JSON error "+ e.getMessage());
	                }
	                FacebookRequestError error = response.getError();
	                if (error != null) {
	                    Toast.makeText(getActivity()
	                         .getApplicationContext(),
	                         error.getErrorMessage(),
	                         Toast.LENGTH_SHORT).show();
	                    } else {
	                        Toast.makeText(getActivity()
	                             .getApplicationContext(), 
	                             postId,
	                             Toast.LENGTH_LONG).show();
	                }
	            }
	        };

	        Request request = new Request(session, "me/feed", postParams, 
	                              HttpMethod.POST, callback);

	        RequestAsyncTask task = new RequestAsyncTask(request);
	        task.execute();
	    }

	}
	
	*/
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
	    uiHelper.onSaveInstanceState(outState);
	}
	/*
	 * OVERRIDE ENDS
	 */
}
