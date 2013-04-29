package edu.ucsb.cs.epsilon.ucsb360;

import com.qualcomm.QCAR.QCAR;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.QCARManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.math.*;

import java.lang.*;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.app.Activity;
import android.app.AlertDialog;

public class RenderGdx implements ApplicationListener 
{
	private static final String TAG = "HelloWorld";
	
	SpriteBatch spriteBatch;
	Texture texture;
	BitmapFont font;
	Vector2 textPosition = new Vector2(100, 100);
	Vector2 textDirection = new Vector2(1, 1);

	// Display size of the device
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    
    float[] lightColor = {1, 1, 1, 0};
    float[] lightPosition = {2, 5, 10, 0};
    
    public QCARManager mQCARManager;
    
    public boolean qcarInited = false; 
    
    private Mesh squareMesh;
    
    /** Constructor. */
    RenderGdx( )
    {
     	Log.d(TAG, "construct qcarmanager");
		mQCARManager = new QCARManager( );
    }
    
    @Override
	public void create () 
	{
    	//createSquareMesh( );
    	createFont(); //create the font
	}
    
    private void createFont(){
    	//font property initialization
    	spriteBatch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("data/testFont.fnt"),
      	         Gdx.files.internal("data/testFont.png"), false);
       
    }
    private void createSquareMesh( ) 
	{
    	if (squareMesh == null) 
    	{
            squareMesh = new Mesh(true, 4, 4, 
                    new VertexAttribute(Usage.Position, 3, "a_position"),
                    new VertexAttribute(Usage.ColorPacked, 4, "a_color"));

            squareMesh.setVertices(new float[] {
                    -50.0f, -50.0f, 0, Color.toFloatBits(128, 0, 0, 255),
                    50.0f, -50.0f, 0, Color.toFloatBits(192, 0, 0, 255),
                    -50.0f, 50.0f, 0, Color.toFloatBits(192, 0, 0, 255),
                    50.0f, 50.0f, 0, Color.toFloatBits(255, 0, 0, 255) });   
            squareMesh.setIndices(new short[] { 0, 1, 2, 3});
        }
	}
    
    private void renderSquareMesh( float[] mv )
    {
    	if( mv != null )
		{
    		float[] projMatrix = null; 
            projMatrix = mQCARManager.getProjectionMatrix();
            
            Gdx.gl10.glMatrixMode(GL10.GL_PROJECTION);
            Gdx.gl10.glLoadIdentity();
            Gdx.gl10.glLoadMatrixf(projMatrix, 0);
            Gdx.gl10.glMatrixMode(GL10.GL_MODELVIEW);
            Gdx.gl10.glLoadIdentity();
            Gdx.gl10.glLoadMatrixf(mv, 0);
    	}
    	
    	squareMesh.render(GL10.GL_TRIANGLE_STRIP, 0, 4);

    }
    
    private void renderBitMapFont( float[] mv )
    {
    	if( mv != null )
		{
    		float[] projMatrix = null; 
            projMatrix = mQCARManager.getProjectionMatrix();
            
            Gdx.gl10.glMatrixMode(GL10.GL_PROJECTION);
            Gdx.gl10.glLoadIdentity();
            Gdx.gl10.glLoadMatrixf(projMatrix, 0);
            Gdx.gl10.glMatrixMode(GL10.GL_MODELVIEW);
            Gdx.gl10.glLoadIdentity();
            Gdx.gl10.glLoadMatrixf(mv, 0);
            
            spriteBatch.getTransformMatrix().set(mv);
    		spriteBatch.getProjectionMatrix().set(projMatrix);
    	
        	spriteBatch.begin();
        	font.draw(spriteBatch, "hello world",  -100 , 0);
        	spriteBatch.end();
      
    	}
    	
    }
    
	@Override
	public void render () 
	{
		float[] modelView = null;

		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BITS );
		Gdx.gl10.glEnable(GL10.GL_CULL_FACE);
		
		if( qcarInited )
		{
			modelView = mQCARManager.renderFrame();		
		}
		
      	//renderSquareMesh( modelView );
		renderBitMapFont(modelView); //render bimapfont with modelview matrix
		Gdx.gl.glFlush();	
	}
	
	@Override
	public void resize (int width, int height) 
	{
		// Call native function to update rendering when render surface parameters have changed:
        mQCARManager.updateRendering(width, height);

        // Call QCAR function to handle render surface size changes:
        QCAR.onSurfaceChanged(width, height);
	}

	@Override
	public void pause () {

	}

	@Override
	public void resume () {

	}

	@Override
	public void dispose () 
	{

	}
}
