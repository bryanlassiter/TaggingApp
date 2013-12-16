package com.example.tagging;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.app.ListActivity;
import android.widget.EditText;

public class AddTag extends ListActivity {
  private Database datasource;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.addtag);

    datasource = new Database(this);
    datasource.open();

    List<Tag> values = datasource.getAllTags();

    // use the SimpleCursorAdapter to show the
    // elements in a ListView
    ArrayAdapter<Tag> adapter = new ArrayAdapter<Tag>(this,
        android.R.layout.simple_spinner_item, values);
    setListAdapter(adapter);
  }
  
  // Will be called via the onClick attribute
  // of the buttons in main.xml
  public void onClick(View view) {
    @SuppressWarnings("unchecked")
	ArrayAdapter<Tag> adapter = (ArrayAdapter<Tag>) getListAdapter();
    Tag tag = null;
    Log.v("AddTag", "add");
    
    switch (view.getId()) {
    case R.id.button1:
    	
    	EditText tagName = (EditText) findViewById(R.id.editText1);
    	String tagNameString = tagName.getText().toString();
    	EditText desc = (EditText) findViewById(R.id.editText2);
    	String descString = desc.getText().toString();
    	tag = datasource.createTag(tagNameString, "x", descString);
    	adapter.add(tag);
    	break;
    }
    adapter.notifyDataSetChanged();
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