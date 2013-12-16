package com.example.tagging;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class TagPage extends Activity {

	private Database datasource;
	private String tagName;
	ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tagpage);
		
		list = (ListView) findViewById(R.id.list);
		tagName = getIntent().getStringExtra("value");
		
		
		
		datasource = new Database(this);
        datasource.open();
		
        TextView textview = (TextView) findViewById(R.id.textView1);
		textview.setText("Tag: " + tagName);
		TextView textView1 = (TextView) findViewById(R.id.textView2);
		textView1.setText("Description: " + datasource.getTagDesc(tagName));
        
		List<String> values = datasource.getAllPhotos(tagName);

		String[] array = new String[values.size()];
		values.toArray(array);
		
		final CustomList adapter = new CustomList(TagPage.this, array);
		list.setAdapter(adapter);
		list.post(new Runnable() {
		    public void run() {
		        list.setAdapter(adapter);
		        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                    	String string = datasource.getPhotoUri(id + 1, tagName);
        	    		Intent intent = new Intent(TagPage.this, PhotoPage.class).putExtra("value", string);
        	    		startActivity(intent);
                    }
                });
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