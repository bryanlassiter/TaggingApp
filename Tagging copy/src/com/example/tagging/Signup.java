package com.example.tagging;

import java.util.List;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class Signup extends ListActivity {
  private Database datasource;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.signup);

    datasource = new Database(this);
    datasource.open();

    List<User> values = datasource.getAllUsers();

    // use the SimpleCursorAdapter to show the
    // elements in a ListView
    ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
        android.R.layout.simple_spinner_item, values);
    setListAdapter(adapter);
  }
  
  // Will be called via the onClick attribute
  // of the buttons in main.xml
  public void onClick(View view) {
    @SuppressWarnings("unchecked")
	ArrayAdapter<User> adapter = (ArrayAdapter<User>) getListAdapter();
    User user = null;
    
    switch (view.getId()) {
    case R.id.button1:
    	EditText userName = (EditText) findViewById(R.id.editText1);
    	String userNameString = userName.getText().toString();
    	EditText password = (EditText) findViewById(R.id.editText2);
    	String passwordString = password.getText().toString();
    	EditText verifyPass = (EditText) findViewById(R.id.editText3);
    	String verifyPassString = verifyPass.getText().toString();
    	EditText firstName = (EditText) findViewById(R.id.editText4);
    	String firstNameString = firstName.getText().toString();
    	EditText lastName = (EditText) findViewById(R.id.editText5);
    	String lastNameString = lastName.getText().toString();
    	user = datasource.createUser(userNameString, passwordString, verifyPassString, firstNameString, lastNameString);
    	adapter.add(user);
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