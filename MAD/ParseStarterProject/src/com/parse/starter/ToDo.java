package com.parse.starter;

import com.parse.LogOutCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

public class ToDo extends Activity {
	private ParseQueryAdapter<ParseObject> mainAdapter;
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do);
		mainAdapter = new ParseQueryAdapter<ParseObject>(this, "Todo");
		mainAdapter.setTextKey("activity");
		listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(mainAdapter);
		mainAdapter.loadObjects();
		
		
		   
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.add:
	        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        	builder.setTitle("Add Task");

	        	// Set up the input
	        	final EditText input = new EditText(this);
	        	// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
	        	input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
	        	builder.setView(input);

	        	// Set up the buttons
	        	builder.setPositiveButton("Add", new DialogInterface.OnClickListener() { 
	        	    @Override
	        	    public void onClick(DialogInterface dialog, int which) {
	        	       ParseObject parseobject = new ParseObject("Todo");
	        	       parseobject.put("activity", input.getText().toString());
	        	       parseobject.setACL(new ParseACL(ParseUser.getCurrentUser()));
	        	       parseobject.saveInBackground();
	        	       
	        	       
	        	    }
	        	});
	        	builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        	    @Override
	        	    public void onClick(DialogInterface dialog, int which) {
	        	        dialog.cancel();
	        	    }
	        	});

	        	builder.show();
	            return true;
	        case R.id.logout:
	        	
	           ParseUser.logOutInBackground(new LogOutCallback() {
				
				@Override
				public void done(ParseException e) {
					if(e==null)
					{
						Intent intent = new Intent(ToDo.this, MainActivity.class);
						startActivity(intent);
						
					}
				}
			});;
	            return true;
	        default:
	        	mainAdapter.loadObjects();
	            return super.onOptionsItemSelected(item);
	    }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.layout.menu, menu);
	    return true;
	}
	
	
}
