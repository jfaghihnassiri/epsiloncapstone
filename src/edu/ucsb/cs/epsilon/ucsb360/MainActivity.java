package edu.ucsb.cs.epsilon.ucsb360;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	/*constant integer for bring up the camera
	 * not sure if we need to use this as constant
	 * may change for later development
	 * */

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*button for the View without Login
		 * 
		 * */
		Button btnViewNoLogin = (Button)findViewById(R.id.btnViewNoLogin);
		btnViewNoLogin.setOnClickListener(this);
	}

	
    public void onClick(View v) {
      Intent i = new Intent(this, CameraView.class);
      startActivity(i);
    }
    
    
}
