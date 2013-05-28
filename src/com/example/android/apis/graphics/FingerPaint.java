/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.graphics;

import com.qualcomm.QCARSamples.CloudRecognition.utils.DebugLog;


import edu.ucsb.cs.epsilon.ucsb360.Global;
import edu.ucsb.cs.epsilon.ucsb360.R;
import edu.ucsb.cs.epsilon.ucsb360.ReviewAugmentation;
import com.qualcomm.vuforia.CloudRecognition.samples.PostNewTarget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.lang.Math;
import android.widget.TextView.OnEditorActionListener;

public class FingerPaint extends GraphicsActivity
        implements ColorPickerDialog.OnColorChangedListener {    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //new upLoadToVuforiaTargetDatabase().execute();
        
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        backgrnd = getIntent().getByteArrayExtra("bitMP");
        byte[] foregrnd = getIntent().getByteArrayExtra("previousCanvas");
        
        
        
        // either redraws old bitmap previously drawn on by user or creates a new one if they haven't made one
        if(foregrnd != null)
        {
        	Bitmap tempBmp = BitmapFactory.decodeByteArray(foregrnd, 0, foregrnd.length);
        	mBitmap = tempBmp.copy(Bitmap.Config.ARGB_8888, true); // making bitmap mutable so it can be drawn on canvas
        	tempBmp = null;
        }
        else
        {
        	new upLoadToVuforiaTargetDatabase().execute();
        	mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }
        
        //setContentView(R.layout.create_augmentation);
        thisView = new MyView(this);
        setContentView(thisView);
         
        LayoutInflater inflater = getLayoutInflater();
        getWindow().addContentView(inflater.inflate(R.layout.create_augmentation,null),
        		new ViewGroup.LayoutParams(
        				ViewGroup.LayoutParams.FILL_PARENT,
        				ViewGroup.LayoutParams.FILL_PARENT));
    		
    		
        //this.addContentView(new MyView(this), null);


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFFF0000);
        //mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //mPaint.setStrokeWidth(12);
        mPaint.setStrokeWidth(12);
        
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLUE);
        textPaint.setTextSize(26);
        textPaint.setStrokeWidth(1);
        
        mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 },
                                       0.4f, 6, 3.5f);

        mBlur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
        

        if (backgrnd == null)
        	DebugLog.LOGD("backgrnd NULL!!!1");
        else
        	DebugLog.LOGD("backgrnd NOT NULL!!!1");
        
        addListenerOnButtonAndTextChange();
    }
    
    private class upLoadToVuforiaTargetDatabase extends AsyncTask<Void, Boolean, Boolean> {

        protected void onPreExecute() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
        	targetId = new PostNewTarget().uploadTarget("test upload in finger paint", backgrnd);
            return true;
        }

        protected void onPostExecute(Boolean result) {
        	
        }

    }
    
	Button doneButton;
	Button clearButton;
	Button undoButton;
	Button toolsButton;
	static EditText augInput;
	TextView display;
	private MyView thisView;
	private Paint textPaint;
	String targetId;
	public static String getText()
	{
		return augInput.getText().toString();
	}
    public void addListenerOnButtonAndTextChange() 
	{
		// Buttons go here JFN JCH
		final Context context = this;
		
		doneButton = (Button) findViewById(R.id.create_augmentation_done);
		augInput = (EditText) findViewById(R.id.augmentation_text);
		display = (TextView) findViewById(R.id.createAugText);
		clearButton = (Button) findViewById(R.id.create_augmentation_erase);
		undoButton = (Button) findViewById(R.id.create_augmentation_textmode);
		toolsButton = (Button) findViewById(R.id.create_augmentation_tools);
		
		// LB - Listener to input text on screen from keyboard input (on enter only?)
		augInput.setOnEditorActionListener(new OnEditorActionListener()
		{
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
		    {
		    	//boolean handled = false;
			    if (v.getText().toString() != null)
			    {
					mCanvas.drawText(v.getText().toString(), (float)Math.floor(width/2)-textPaint.measureText(v.getText().toString())/2, (float)Math.floor(height/2), textPaint);
					DebugLog.LOGD("textChanged");					
					thisView.invalidate();
			        //handled = true;
			    }
			    //DebugLog.LOGD("textChanged2");
			    //return handled; 
			    return false;
		    }
		});
		
		
		doneButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				if (augInput.getText().toString() != null){
					//display.setText(augInput.getText().toString());
					//display.refreshDrawableState();
				}
				//Intent intent = new Intent(getApplicationContext(), ReviewAugmentation.class);
				//startActivity(intent);
				
    			ByteArrayOutputStream stream = new ByteArrayOutputStream();
    			mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
    			byte[] canvasByteArray = stream.toByteArray();
    			stream = null;
				
				
				Intent result = new Intent();
				result.putExtra("returnedAugmentation", canvasByteArray);
				result.putExtra("returnedTargetId", targetId);
				setResult(Activity.RESULT_OK, result);
	  	        finish();
				//goto viewing page of target with augmentation
			}
		});
		
		// LB - clears canvas on button click
		clearButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				DebugLog.LOGD("clearingCanvas");
				mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
				thisView.invalidate();
			}
		});
		
		// LB - undoes last edit on button click
		undoButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				DebugLog.LOGD("undoOnCanvas");				
			}
		});
		
		// MH - opens tools menu
		toolsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// LUKE: Pull up the paint tools menu here
			}
		});
		
	}
    
    
    private Paint       mPaint;
    private MaskFilter  mEmboss;
    private MaskFilter  mBlur;
    private byte[] backgrnd;
    
    //LB moved outside
    private Canvas  mCanvas;
    private Bitmap  mBitmap;

    
    private int width;
    private int height;
    
    public void colorChanged(int color) {
        mPaint.setColor(color);
    }

    public class MyView extends View {
        
        private static final float MINP = 0.25f;
        private static final float MAXP = 0.75f;
        

        //private Canvas  mCanvas;
        private Path    mPath;
        private Paint   mBitmapPaint;
        
        
        public MyView(Context c) {
            super(c);
            
            Drawable d = new BitmapDrawable(getResources(),BitmapFactory.decodeByteArray(backgrnd, 0, backgrnd.length));
            super.setBackgroundDrawable(d);
            
            mCanvas = new Canvas(mBitmap); 
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
            //canvas.drawColor(0xFFAAAAAA);
            canvas.drawColor(Color.TRANSPARENT); // to make background transparent
            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
            
            canvas.drawPath(mPath, mPaint);
        }
        
        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;
        
        private void touch_start(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }
        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
                mX = x;
                mY = y;
            }
        }
        private void touch_up() {
            mPath.lineTo(mX, mY);
            // commit the path to our offscreen
            mCanvas.drawPath(mPath, mPaint);
            // kill this so we don't double draw
            mPath.reset();
            
        }
        
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }

    }
    
    private static final int COLOR_MENU_ID = Menu.FIRST;
    private static final int EMBOSS_MENU_ID = Menu.FIRST + 1;
    private static final int BLUR_MENU_ID = Menu.FIRST + 2;
    private static final int ERASE_MENU_ID = Menu.FIRST + 3;
    private static final int SRCATOP_MENU_ID = Menu.FIRST + 4;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        
        menu.add(0, COLOR_MENU_ID, 0, "Color").setShortcut('3', 'c');
        menu.add(0, EMBOSS_MENU_ID, 0, "Emboss").setShortcut('4', 's');
        menu.add(0, BLUR_MENU_ID, 0, "Blur").setShortcut('5', 'z');
        menu.add(0, ERASE_MENU_ID, 0, "Erase").setShortcut('5', 'z');
        menu.add(0, SRCATOP_MENU_ID, 0, "SrcATop").setShortcut('5', 'z');

        /****   Is this the mechanism to extend with filter effects?
        Intent intent = new Intent(null, getIntent().getData());
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        menu.addIntentOptions(
                              Menu.ALTERNATIVE, 0,
                              new ComponentName(this, NotesList.class),
                              null, intent, 0, null);
        *****/
        return true;
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xFF);

        switch (item.getItemId()) {
            case COLOR_MENU_ID:
                new ColorPickerDialog(this, this, mPaint.getColor()).show();
                return true;
            case EMBOSS_MENU_ID:
                if (mPaint.getMaskFilter() != mEmboss) {
                    mPaint.setMaskFilter(mEmboss);
                } else {
                    mPaint.setMaskFilter(null);
                }
                return true;
            case BLUR_MENU_ID:
                if (mPaint.getMaskFilter() != mBlur) {
                    mPaint.setMaskFilter(mBlur);
                } else {
                    mPaint.setMaskFilter(null);
                }
                return true;
            case ERASE_MENU_ID:
                mPaint.setXfermode(new PorterDuffXfermode(
                                                        PorterDuff.Mode.CLEAR));
                return true;
            case SRCATOP_MENU_ID:
                mPaint.setXfermode(new PorterDuffXfermode(
                                                    PorterDuff.Mode.SRC_ATOP));
                mPaint.setAlpha(0x80);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
