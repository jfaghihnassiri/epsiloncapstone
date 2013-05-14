package edu.ucsb.cs.epsilon.ucsb360;

import com.qualcomm.QCARSamples.CloudRecognition.CloudReco;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class ShareOnFb extends DialogFragment{
	
	
	private ImageView image;
	private EditText message;
	private AlertDialog alert;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //set up label
        builder.setTitle(R.string.sharefb);

        //set up inflater and view
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.share_fb, null);
        
        LinearLayout layout =  ((LinearLayout)view.findViewById(R.id.share_fb_container));
        layout.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) v.getContext()
			            .getSystemService(Context.INPUT_METHOD_SERVICE);
			    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				
			}
        	
        });
        //set up all privates
        image = new ImageView(getActivity());
        Bitmap bitmap = (((CloudReco) getActivity()).getSnapshot());
        image.setImageBitmap(bitmap);
        image.setVisibility(View.VISIBLE);
        ((LinearLayout)view.findViewById(R.id.share_fb_container)).addView(image);
        
        
        
        /*done = (Button)view.findViewById(R.id.post_to_fb);
        done.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
        	
        });*/
        message = (EditText)view.findViewById(R.id.message);
        message.setVisibility(View.VISIBLE);
        message.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus)
				{
					InputMethodManager imm = (InputMethodManager) v.getContext()
			            .getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				}
			}
        	
       });
        builder.setView(view)
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity().getApplicationContext(), "Canceled",Toast.LENGTH_SHORT).show();
			}
		
		});
        alert = builder.create();
        Button doneButton = (Button)view.findViewById(R.id.done_edit);
        doneButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity().getApplicationContext(), "Posting to Facebook...",Toast.LENGTH_SHORT).show();
				CloudReco.postToFb3((((CloudReco) getActivity()).getSnapshot()), message.getText().toString());
				dismiss();
			}
        	
        });
        
       
		return builder.create();
		
	}
}
