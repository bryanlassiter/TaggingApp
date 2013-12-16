package com.example.tagging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	
	private EditText userName;
	private EditText password;
	private Database datasource;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        datasource = new Database(this);
        datasource.open();
        
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				userName = (EditText)findViewById(R.id.editText1);
		        password = (EditText)findViewById(R.id.editText2);
				String user = (userName.getText().toString());
				String pass = (password.getText().toString());
				if (datasource.validateUser(user, pass)){
					//Toast.makeText(getBaseContext(), "Success",  Toast.LENGTH_LONG).show();
					Intent intent = new Intent(Login.this, LoggedIn.class);
					startActivity(intent);
				}
				else {
					Toast.makeText(getBaseContext(), "Failure",  Toast.LENGTH_LONG).show();
				}
			}
		});
        userName = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);   
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