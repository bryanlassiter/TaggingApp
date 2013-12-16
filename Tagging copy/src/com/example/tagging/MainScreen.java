package com.example.tagging;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		Button button1 = (Button) findViewById(R.id.button1);
	    Button button2 = (Button) findViewById(R.id.button2);
	    button1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
	    		Intent intent = new Intent(MainScreen.this, Signup.class);
	    		startActivity(intent);
	    	}
	    	
	    });
	    button2.setOnClickListener(new OnClickListener(){
	    		
	    	@Override
	    	public void onClick(View v) {
	    		Intent intent = new Intent(MainScreen.this, Login.class);
	    		startActivity(intent);
	    	}	
	    });   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_screen, menu);
		return true;
	}

}
