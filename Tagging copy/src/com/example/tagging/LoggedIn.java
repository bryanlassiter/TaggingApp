package com.example.tagging;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class LoggedIn extends ListActivity {
	
	private Database datasource;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loggedin);

        datasource = new Database(this);
        datasource.open();

        List<String> values = datasource.getAllTagNames();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            R.layout.list_item, values);
        setListAdapter(adapter);
      
        
	    Button button1 = (Button) findViewById(R.id.button1);
	    button1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
	    		Intent intent = new Intent(LoggedIn.this, AddTag.class);
	    		startActivity(intent);
	    	}
	    	
	    });
	    
	    ListView lv = (ListView) findViewById(android.R.id.list);
	    
	    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	        public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
	                                long id) {

	            
	            TextView clickedView = (TextView) view;
	            String viewString = clickedView.getText().toString();
	            Intent intent = new Intent(getApplicationContext(), TagPage.class).putExtra("value", viewString);
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