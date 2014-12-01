package com.alaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.drawable;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity{

	 private static final String INBOX_SPEC = "القاهرة";
	    private static final String OUTBOX_SPEC = " أسكندرية";
	    private static final String PROFILE_SPEC = "أسيوط";

	
	EditText ed;
	//	AutoCompleteTextView ed;
		String Editing = "More";
		DatabaseHandler db ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		ed = (EditText) findViewById(R.id.editText1);
		//ed = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		
		 SharedPreferences pref = getSharedPreferences("PREF_NAME", MODE_PRIVATE);
		    pref.edit().putString("Editing", ed.getText().toString()).commit();
		        final TabHost tabHost = getTabHost();
		          
		        
		        // Inbox Tab
		        TabSpec inboxSpec = tabHost.newTabSpec(INBOX_SPEC);
		        // Tab Icon
		        inboxSpec.setIndicator(INBOX_SPEC);
		        Intent inboxIntent = new Intent(this, Cairo_list.class);
		        
		        // Tab Content
		        inboxSpec.setContent(inboxIntent);
	
		        // Outbox Tab
		        TabSpec outboxSpec = tabHost.newTabSpec(OUTBOX_SPEC);
		        outboxSpec.setIndicator(OUTBOX_SPEC);
		        Intent outboxIntent = new Intent(this, CityList.class);
//		        outboxIntent.putExtra("name", "Alex");
		        outboxSpec.setContent(outboxIntent);
		         
		        // Profile Tab
		        TabSpec profileSpec = tabHost.newTabSpec(PROFILE_SPEC);
		        profileSpec.setIndicator(PROFILE_SPEC);
		        Intent profileIntent = new Intent(this, Assuit_list.class);
		        profileSpec.setContent(profileIntent);
		         
		        // Adding all TabSpec to TabHost
		        tabHost.addTab(inboxSpec); // Adding Inbox tab
		        tabHost.addTab(outboxSpec); // Adding Outbox tab
		        tabHost.addTab(profileSpec); // Adding Profile tab
		     
		    //for make colour in your tab 
		    /*    for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
		        {
		        if (i == 0) tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#edb2b2"));

		        else tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#cc0000"));
		        }  
		        tabHost.setOnTabChangedListener(new OnTabChangeListener(){
		        	@Override
		        	public void onTabChanged(String tabId) {
		        	    // TODO Auto-generated method stub
		        	     for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
		        	        {
		        	           tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#cc0000")); //unselected
		        	        }
		        	        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#edb2b2")); // selected
		        	}
		        	});
		        	*/
		        ed.setOnEditorActionListener(
						    new EditText.OnEditorActionListener() {
						@Override
						public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
						   /* if (actionId == EditorInfo.IME_ACTION_SEARCH ||
						            actionId == EditorInfo.IME_ACTION_DONE ||
						            event.getAction() == KeyEvent.ACTION_DOWN &&
						            event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
						        if (!event.isShiftPressed()) {
						           // the user is done typing. 
						        	Toast.makeText(MainActivity.this, "Left Swipe", Toast.LENGTH_SHORT).show();
						        	/*Intent i = new Intent(MainActivity.this, Search_Cairo.class);
								      i.putExtra("M",  ed.getText().toString());
									startActivity(i);
									*/
							/*
						           return true; // consume.
						        }                
						        Toast.makeText(MainActivity.this, "Left Swipe", Toast.LENGTH_SHORT).show();
						    }
						    return false; // pass on to other listeners. 
						*/
						String Current =	String.valueOf(tabHost.getCurrentTab());
							
						//	Toast.makeText(MainActivity.this,ed.getText().toString() , Toast.LENGTH_SHORT).show();
						//	Toast.makeText(MainActivity.this,  Current, Toast.LENGTH_SHORT).show();
							Intent i = new Intent(MainActivity.this, Search_Cairo.class);
						      i.putExtra("M",  ed.getText().toString());
						      i.putExtra("tabid", Current);
							startActivity(i);
							
							/*
							  ed.setOnEditorActionListener(
						    new EditText.OnEditorActionListener() {
						@Override
						public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
						   /* if (actionId == EditorInfo.IME_ACTION_SEARCH ||
						            actionId == EditorInfo.IME_ACTION_DONE ||
						            event.getAction() == KeyEvent.ACTION_DOWN &&
						            event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
						        if (!event.isShiftPressed()) {
						           // the user is done typing. 
						        	Toast.makeText(MainActivity.this, "Left Swipe", Toast.LENGTH_SHORT).show();
						        	/*Intent i = new Intent(MainActivity.this, Search_Cairo.class);
								      i.putExtra("M",  ed.getText().toString());
									startActivity(i);
									*/
							/*
						           return true; // consume.
						        }                
						        Toast.makeText(MainActivity.this, "Left Swipe", Toast.LENGTH_SHORT).show();
						    }
						    return false; // pass on to other listeners. 
						*/
		
			          
		        
		        
							 
							 return false;
						}
						
						});
			          
		        
		        
		   
	}
	

	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	  }

}
