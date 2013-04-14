package edu.ucsb.cs.epsilon.ucsb360;

import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.Session.StatusCallback;

import com.facebook.android.Facebook;
import com.facebook.internal.SessionTracker;
import com.facebook.model.GraphUser;
import com.qualcomm.QCARSamples.CloudRecognition.CloudReco;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
	private LoginFragment loginFragment;
	static final String TAG = "EXE";
	private Button btnViewNoLogin;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	        loginFragment = new LoginFragment();
	        getSupportFragmentManager()
	        .beginTransaction()
	        .add(android.R.id.content, loginFragment)
	        .commit();
	    } else {
	        // Or set the fragment from restored state info
	        loginFragment = (LoginFragment) getSupportFragmentManager()
	        .findFragmentById(android.R.id.content);
	    }
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Global.applicationResumed();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Global.applicationPaused();
	}

}
