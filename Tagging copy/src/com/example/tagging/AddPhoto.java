package com.example.tagging;

import java.util.List;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class AddPhoto extends Activity {

	private Database datasource;
	private Spinner spinner;
	private ImageView imgView;
	private EditText editText;
	Uri imageUri;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addphoto);
		
		Intent intent = this.getIntent();
		String action = intent.getAction();
	    String type = intent.getType();
	    
	    
	    
	    datasource = new Database(this);
	    datasource.open();
	    
	    spinner = (Spinner) findViewById(R.id.spinner1);
	    imgView = (ImageView) findViewById(R.id.ImageView01);
	    editText = (EditText) findViewById(R.id.editText1);

	    
	    
	    if (Intent.ACTION_SEND.equals(action) && type != null) {
	        if (type.startsWith("image/")) {
	            handleSendImage(intent); // Handle single image being sent
	        }
	    }
	}

	private void handleSendImage(Intent intent) {
		imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
		if (imageUri != null) {
	    	imgView.setImageURI(imageUri);
	        List<String> values = datasource.getAllTagNames();
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	            android.R.layout.simple_spinner_item, values);
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner.setAdapter(adapter);
	    }
	}

	public void onClick(View view) {
		Photo photo = null;
	    switch (view.getId()) {
	    case R.id.button1:
	    	String tagName = spinner.getSelectedItem().toString();
	    	String photoDesc = editText.getText().toString();
	    	Uri uri = (Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM);
			photo = datasource.createPhoto(uri.toString(), tagName, photoDesc);
	    	Intent intent = new Intent(AddPhoto.this, MainScreen.class);
    		startActivity(intent);
	    	break;
	    }
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
		@Override
		protected void onDestroy() {
			  imgView.setOnTouchListener(null);
			  imgView.setImageBitmap(null);
			  System.gc();
			  super.onDestroy();
			}
}