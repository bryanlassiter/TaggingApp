package com.example.tagging;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoPage extends Activity {

	private Database datasource;
	String tagName;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photopage);
		
		datasource = new Database(this);
        datasource.open();
		
		tagName = getIntent().getStringExtra("value");
		TextView textview = (TextView) findViewById(R.id.textView1);
		textview.setText("Tag: " + datasource.getData(tagName));
		TextView textView1 = (TextView) findViewById(R.id.textView2);
		textView1.setText("Description: " + datasource.getPhotoDesc(tagName));
		
		ImageView imageView = (ImageView) findViewById(R.id.img);
		imageView.setImageURI(Uri.parse(tagName));
		
		Button button1 = (Button) findViewById(R.id.button1);
	    button1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String tag = datasource.getData(tagName);
	    		datasource.deletePhoto(tagName);
				Intent intent = new Intent(PhotoPage.this, TagPage.class).putExtra("value", tag);
	    		startActivity(intent);
	    	}
	    	
	    });
	}
	
	@Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }
}