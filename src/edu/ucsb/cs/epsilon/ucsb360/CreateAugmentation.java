package edu.ucsb.cs.epsilon.ucsb360;

import edu.ucsb.cs.epsilon.ucsb360.R;
import edu.ucsb.cs.epsilon.ucsb360.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class CreateAugmentation extends Activity{

	Button doneButton;
	EditText augInput;
	TextView display;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_augmentation);
		addListenerOnButton();
	}

	
	public void addListenerOnButton() 
	{
		// Buttons go here JFN JCH
		final Context context = this;
		
		doneButton = (Button) findViewById(R.id.create_augmentation_done);
		augInput = (EditText) findViewById(R.id.augmentation_text);
		display = (TextView) findViewById(R.id.createAugText);
		
		doneButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				if (augInput.getText().toString() != null){
					//display.setText(augInput.getText().toString());
					//display.refreshDrawableState();
				}
				Intent intent = new Intent(getApplicationContext(), ReviewAugmentation.class);
				startActivity(intent);
				//goto viewing page of target with augmentation
			}
		});
		
		
	}
	
	
}
