package edu.ucsb.cs.epsilon.ucsb360;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class ReviewAugmentation extends Activity{

	Button doneButton;
	Button shareButton;
	Button cancelButton;
	public String userInput;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_augmentation);
		addListenerOnButton();
	}

	public void addListenerOnButton() 
	{
		// Buttons go here JFN JCH
		final Context context = this;
		
		doneButton = (Button) findViewById(R.id.done_review);
		shareButton = (Button) findViewById(R.id.share_review);
		cancelButton = (Button) findViewById(R.id.cancel_review);
		
		doneButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				Intent intent = new Intent(getApplicationContext(), com.qualcomm.QCARSamples.CloudRecognition.CloudReco.class);
	    		//Intent i = new Intent(getActivity(), SplashScreen.class);
	  	        startActivity(intent);
			}
		});
		
		shareButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				Toast.makeText( getApplicationContext(), "ANDY, POST this to FaceBook", Toast.LENGTH_LONG).show();
			}
		});
		
		cancelButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				Intent intent = new Intent(getApplicationContext(), CreateAugmentation.class);
	    		//Intent i = new Intent(getActivity(), SplashScreen.class);
	  	        startActivity(intent);
			}
		});
	}
}
