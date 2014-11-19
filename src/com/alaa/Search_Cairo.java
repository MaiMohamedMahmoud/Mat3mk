package com.alaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.color;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Search_Cairo extends ListActivity implements OnItemClickListener {

	 DatabaseHandler db;
	    ArrayList<HashMap<String, String>> cairolist;

	    
	   private  final  String Cairo_name ="CAiro";
	   private  final  String  Cairo_Address ="lkj";
	   String tab;
	   ListView s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cario_list_item_1);
	//	 SharedPreferences pref = getSharedPreferences("PREF_NAME", MODE_PRIVATE);
		///    String ediTextVal = pref.getString("Editing", "anyDefaultValue");
	s = (ListView)findViewById(android.R.id.list);
	
		Intent i = getIntent();
//	Toast.makeText(Search_Cairo.this, i.getStringExtra("M"), Toast.LENGTH_SHORT).show();
		 
//	Toast.makeText(Search_Cairo.this,i.getStringExtra("tabid"), Toast.LENGTH_SHORT).show();
		  db = new DatabaseHandler(this);
		   tab = i.getStringExtra("tabid");
		   if(db.getSearchCount()!=0)
			  {
			   db.DeleteSearch();
			  }
	cairolist = new ArrayList<HashMap<String,String>>();
	List<Rest> contact = 	db.getContactName(i.getStringExtra("M"),i.getStringExtra("tabid"));
	for (Rest cn : contact) {
        
   	 HashMap<String, String> map = new HashMap<String, String>();
   	String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Address: " + cn.getAddress() + ",Phone:"+cn.getPhoneNumber();
           // Writing Contacts to log
   	
       
   	map.put(Cairo_Address, cn.getAddress());
   	map.put(Cairo_name, cn.getName());
        // adding HashList to ArrayList
        cairolist.add(map);
   Log.d("Name: ", log);
 
    db.addsearch(new Rest(cn.getName(),cn.getAddress(),cn.getPhoneNumber()));
}

  
            ListAdapter adapter = new SimpleAdapter(
                    Search_Cairo.this, cairolist,
                    R.layout.search_cairo, new String[] { Cairo_name, Cairo_Address },
                    new int[] { R.id.Name, R.id.Address});
            // updating listview
             setListAdapter(adapter);
           

            
			 s.setCacheColorHint(Color.BLACK);
			 s.setBackgroundColor(Color.BLACK);
			 s.setOnItemClickListener(this);
			
	}

	@Override
	public void onItemClick(AdapterView<?> myadapter, View myView, int myItemInt, long arg3) {
		// TODO Auto-generated method stub
	if(tab.contains("0"))
	{
		Rest contact = 	db.getSearch(myItemInt +1);
		
    	
		
		myView.setBackgroundColor(color.primary_text_dark);
		Intent intent = new Intent(Intent.ACTION_DIAL);
		 String uri = "tel:" + contact.getPhoneNumber().trim();
		 Toast.makeText(Search_Cairo.this,uri, Toast.LENGTH_SHORT).show();
		 Toast.makeText(Search_Cairo.this, contact.getName(), Toast.LENGTH_SHORT).show();
		 intent.setData(Uri.parse(uri));
		 startActivity(intent);
		 
	}
	if(tab.contains("1"))
	{
	Rest contact = 	db.getSearch(myItemInt +1);
		
    	
		
		myView.setBackgroundColor(color.primary_text_dark);
		Intent intent = new Intent(Intent.ACTION_DIAL);
		 String uri = "tel:" + contact.getPhoneNumber().trim();
		 Toast.makeText(Search_Cairo.this,uri, Toast.LENGTH_SHORT).show();
		 Toast.makeText(Search_Cairo.this, contact.getName(), Toast.LENGTH_SHORT).show();
		 intent.setData(Uri.parse(uri));
		 startActivity(intent);
		
		
	}
	if(tab.contains("2"))
	{
	Rest contact = 	db.getSearch(myItemInt +1);
		
    	
		
		myView.setBackgroundColor(color.primary_text_dark);
		Intent intent = new Intent(Intent.ACTION_DIAL);
		 String uri = "tel:" + contact.getPhoneNumber().trim();
		 Toast.makeText(Search_Cairo.this,uri, Toast.LENGTH_SHORT).show();
		 Toast.makeText(Search_Cairo.this, contact.getName(), Toast.LENGTH_SHORT).show();
		 intent.setData(Uri.parse(uri));
		 startActivity(intent);
		
	}
		 
	}

	
}
