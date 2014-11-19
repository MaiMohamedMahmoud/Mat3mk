package com.alaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.color;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Alex_list extends  ListActivity implements OnItemClickListener,OnTouchListener{

	ListView s;
    List<String> name_res ;
    DatabaseHandler db;
    int flag = 0;
    ArrayList<HashMap<String, String>> Alexlist;
    private  final  String Alex_name ="Alex";
    private  final  String  Alex_Address ="Adress";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.alex);
		db = new DatabaseHandler(this);
		 s = (ListView)findViewById(android.R.id.list);
         name_res = new ArrayList<String>();	     
         Alexlist = new ArrayList<HashMap<String,String>>();
		 
		   /**
	         * CRUD Operations
	         * */
         if (isFirstTime()) {
       	    // insert data 
       	  Log.d("kjjdkshdjs","dlkjaedw");
 	        Log.d("Insert: ", "Inserting .."); 
	 	       db.AddAlex(new Rest("مطعم ملوكية","32 ش النصر معالم الطريق: داخل فندق امون المنشية ", "034818351"));
	 	       db.AddAlex(new Rest("حسني المندرة","المندرة - شارع جمال عبد الناصر - الاسكندرية", "035507799"));
	 	       db.AddAlex(new Rest("ماجستي المعمورة","عمارة الكوثر الامن العام شارع النصر", "19915"));
	 	       db.AddAlex(new Rest("بست واي إسكندرية","ش فيكتور عمانويل - أبراج شهر زاد - مصطفى كامل", ""));
	 	       db.AddAlex(new Rest("كوك دور لوران","37 شارع شعرواى لوران  الاسكندرية )", "16999"));
			   db.AddAlex(new Rest("حضر موت العجمي","125 ش البيطاش الرئيسي - العجمي", "01000962299"));
		 	   db.AddAlex(new Rest("حضر موت الهانوفيل","طريق اسكندرية مطروح - بجوار كفتريا العوامي - الهانوفيل", "01009697961"));
		 	   db.AddAlex(new Rest("كنتاكي محطة الرمل","1 شارع سعد زغلول الرمل", "19019"));
		 	   db.AddAlex(new Rest("ماكدونالدز ميامي","277 - ش جمال عبد الناصر - الاسكندرية", "035493998"));
		 	   db.AddAlex(new Rest("ماكدونالدز سان ستيفانو","سان ستيفانو مول - الاسكندرية", "034690102"));
		 	   db.AddAlex(new Rest("ماكدونالدز سموحة","ش فيكتور ايمانويل - نادي سموحة - الاسكندرية", "034255644"));
		 	   db.AddAlex(new Rest("ماكدونالدز محطة الرمل","66 ش صفية زغلول - محطة الرمل - الاسكندرية", "034872879"));
		 	   db.AddAlex(new Rest("مؤمن المنتزه","فتح الله ماركت بجوار أبراج شيراتون", "16600"));
		 	   db.AddAlex(new Rest("بيتزا هت كارفور إسكندرية","سيتي سنتر الأسكندرية", "033970129"));
		 	   db.AddAlex(new Rest("ببيتزا هت رشدي","238 شارع الكورنيش", "19000"));
 	    

       	 }


         // Reading all contacts
	        Log.d("Reading: ", "Reading all contacts.."); 
	        
	        List<Rest> contacts = db.getAllAlex();       
	        
	        for (Rest cn : contacts) {
	            
	        	  
		        	HashMap<String, String> map = new HashMap<String, String>();
		        	String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Address: " + cn.getAddress() + ",Phone:"+cn.getPhoneNumber();
		                // Writing Contacts to log
		        	name_res.add(cn.getName());
		            
		        	map.put(Alex_Address, cn.getAddress());
		        	map.put(Alex_name, cn.getName());
	                 // adding HashList to ArrayList
		        	Alexlist.add(map);
		        Log.d("Name: ", log);
	    }
	      //  ArrayAdapter<String> a = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name_res);
			// s.setAdapter(a);
	        ListAdapter adapter = new SimpleAdapter(
                    Alex_list.this, Alexlist,
                    R.layout.alex_list_item, new String[] { Alex_name, Alex_Address },
                    new int[] { R.id.Name, R.id.Address});
            // updating listview
            setListAdapter(adapter);
			 s.setCacheColorHint(Color.BLACK);
			 s.setBackgroundColor(Color.BLACK);
			 
			 s.setOnItemClickListener(this);
			 s.setOnTouchListener(this);
	}

	 private boolean isFirstTime()
	 {
	SharedPreferences preferences = getPreferences(MODE_PRIVATE);
	boolean ranBefore = preferences.getBoolean("RanBefore", false);
	if (!ranBefore) {
	    // first time
	    SharedPreferences.Editor editor = preferences.edit();
	    editor.putBoolean("RanBefore", true);
	    editor.commit();
	}
	return !ranBefore;
	}
	@Override
	public void onItemClick(AdapterView<?> myadapter, View myView, int myItemInt, long arg3) {
		// TODO Auto-generated method stub
		Rest contact = 	db.getAlex(myItemInt +1);
		
    //	Toast.makeText(Alex_list.this, "Left Swipe", Toast.LENGTH_SHORT).show();
		
		myView.setBackgroundColor(color.primary_text_dark);
		Intent intent = new Intent(Intent.ACTION_DIAL);
		 String uri = "tel:" + contact.getPhoneNumber().trim();
		 Toast.makeText(Alex_list.this,uri, Toast.LENGTH_SHORT).show();
		 Toast.makeText(Alex_list.this, contact.getName(), Toast.LENGTH_SHORT).show();
		 intent.setData(Uri.parse(uri));
		Toast.makeText(Alex_list.this, "Calling !!", Toast.LENGTH_SHORT).show();
			
		 startActivity(intent);
		 
	}

	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction() == MotionEvent.ACTION_DOWN  &&  flag == 0)
		{
			flag = 1;
			Toast.makeText(Alex_list.this, "Click To Call The Restaurant !!", Toast.LENGTH_LONG).show();
			Toast.makeText(Alex_list.this, "Click To Call The Restaurant !!", Toast.LENGTH_LONG).show();
		}
		return false;
	}
}


