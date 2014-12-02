package com.alaa;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.color;
import android.R.string;
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
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Handling different cities lists
 * 
 * @author Mai Mohamed Mahmoud <mai.mohamed.mahmoud91@gmail.com>
 * @version 1.1.0
 * 
 */
public class CityList extends ListActivity implements OnItemClickListener,OnTouchListener {

	ListView s;
	List<String> name_res;
	DatabaseHandler db;
	int flag = 0;
	ArrayList<HashMap<String, String>> cityListMap;
	private String _name ;
	private String _address ;
	private int _city_code ;

	final int CITY_ASSIUT = 0;
	final int CITY_ALEX = 1;
   
	/**
	 * Instantiate a new city list
	 * 
	 * */
//	public CityList(String name, String address, int city_code) {
//		 
//		Log.i("msg","betaa3");
//		this._name = name;
//		this._address = address;
//		this._city_code = city_code;
//	}  

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction() == MotionEvent.ACTION_DOWN && flag == 0)
		{
			flag = 1;

			Toast.makeText(CityList.this, "Click To Call The Restaurant !!", Toast.LENGTH_LONG).show();
			Toast.makeText(CityList.this, "Click To Call The Restaurant !!", Toast.LENGTH_LONG).show();
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int myintentInt, long arg3) {
		// TODO Auto-generated method stub
		Rest contact = 	db.getContact(myintentInt +1);
		
		  //  	Toast.makeText(Cairo_list.this, "Left Swipe", Toast.LENGTH_SHORT).show();
				
		view.setBackgroundColor(color.primary_text_dark);
				Intent intent = new Intent(Intent.ACTION_DIAL);
				 String uri = "tel:" + contact.getPhoneNumber().trim();
			//	 Toast.makeText(Cairo_list.this,uri, Toast.LENGTH_SHORT).show();
			//	 Toast.makeText(Cairo_list.this, contact.getName(), Toast.LENGTH_SHORT).show();
				 
				 intent.setData(Uri.parse(uri));
				 Toast.makeText(CityList.this,"Calling !!", Toast.LENGTH_SHORT).show();
				 startActivity(intent);
				 
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    Intent newIntent = getIntent();
	 
		switch (this._city_code) {
		case 0:
			
			  _city_code=  newIntent.getIntExtra("city_code",2);
				 _address = newIntent.getStringExtra("address");
				 _name = newIntent.getStringExtra("name");
				 Log.i("City Code","City Code"+ _name +_city_code);
			setContentView(R.layout.cairo_list);
			
			init_Db();
			  
			break;
		case 1:
			   _city_code=   newIntent.getIntExtra("city_code", 1);
				 _address = newIntent.getStringExtra("address");
				 _name = newIntent.getStringExtra("name");
				 Log.i("City Code","City Code "+ _name +_city_code);
			setContentView(R.layout.alex);
			init_Db();
			break;
		case 2:
			 _city_code=  newIntent.getIntExtra("city_code",0);
			 _address = newIntent.getStringExtra("address");
			 _name = newIntent.getStringExtra("name");
			 Log.i("City Code","City Code "+ _name +_city_code);
		setContentView(R.layout.assuit);
		init_Db();
			break;
		}
		
	

	}

	private void init_Db() {

		switch (this._city_code) {
		case 0:
			Cairo_Fun();
			
			break;
		case 1:
			Alex_Fun();
			break;
		case 2:
			Assuit_Fun();	
			break;
		}
	}

 private void Read_Alex()
	{
	 Log.d("Reading: ", "Reading all contacts..");

		List<Rest> contacts = db.getAllAlex();

		for (Rest cn : contacts) {
			HashMap<String, String> map = new HashMap<String, String>();
			String log = "Id: " + cn.getID() + " ,Name: " + cn.getName()
					+ " ,Address: " + cn.getAddress() + ",Phone:"
					+ cn.getPhoneNumber();
			// Writing Contacts to log
			name_res.add(cn.getName());

			map.put(_address, cn.getAddress());
			map.put(_name, cn.getName());
			// adding HashList to ArrayList
			cityListMap.add(map);
			Log.d("Name: ", log);
		}
		
		
		ListAdapter adapter = new SimpleAdapter(
                CityList.this, cityListMap,
                R.layout.alex_list_item, new String[] { _name, _address },
                new int[] { R.id.Name, R.id.Address});
        // updating listview
        setListAdapter(adapter);
			 s.setCacheColorHint(Color.BLACK);
			 s.setBackgroundColor(Color.BLACK);
			 
			 s.setOnItemClickListener(this);
			 s.setOnTouchListener(this);
	}

 
 private void Read_Assuit()
 {
	 Log.d("Reading: ", "Reading all contacts..");

		List<Rest> contacts = db.getAllAssuit();

		for (Rest cn : contacts) {
			HashMap<String, String> map = new HashMap<String, String>();
			String log = "Id: " + cn.getID() + " ,Name: " + cn.getName()
					+ " ,Address: " + cn.getAddress() + ",Phone:"
					+ cn.getPhoneNumber();
			// Writing Contacts to log
			name_res.add(cn.getName());

			map.put(_address, cn.getAddress());
			map.put(_name, cn.getName());
			// adding HashList to ArrayList
			cityListMap.add(map);
			Log.d("Name: ", log);
		}
		// ArrayAdapter<String> a = new
		// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name_res);
		// s.setAdapter(a);

		ListAdapter adapter = new SimpleAdapter(CityList.this, cityListMap,
				R.layout.assuit_list_item, new String[] { _name,
						_address }, new int[] { R.id.Name, R.id.Address });
		// updating listview
		setListAdapter(adapter);

		s.setCacheColorHint(Color.BLACK);
		s.setBackgroundColor(Color.BLACK);

		s.setOnItemClickListener(this);
		s.setOnTouchListener(this);
 }

 
 private void Read_Cairo()
 {

     // Reading all contacts
        Log.d("Reading: ", "Reading all contacts.."); 
        
        List<Rest> contacts = db.getAllContacts();       
        
        for (Rest cn : contacts) {
            
        	 HashMap<String, String> map = new HashMap<String, String>();
        	String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Address: " + cn.getAddress() + ",Phone:"+cn.getPhoneNumber();
                // Writing Contacts to log
        	name_res.add(cn.getName());
            
        	map.put(_address, cn.getAddress());
        	map.put(_name, cn.getName());
             // adding HashList to ArrayList
             cityListMap.add(map);
        Log.d("Name: ", log);
    }
        ListAdapter adapter = new SimpleAdapter(
                CityList.this, cityListMap,
                R.layout.cairo_list_item, new String[] { _name, _address },
                new int[] { R.id.Name, R.id.Address});
        // updating listview
        setListAdapter(adapter);
        

        Intent i = getIntent();
    //	Toast.makeText(Cairo_list.this, i.getStringExtra("M"), Toast.LENGTH_SHORT).show();

    //	Toast.makeText(Cairo_list.this,i.getStringExtra("tabid"), Toast.LENGTH_SHORT).show();
    	if(i.getStringExtra("M")!=null)
    	{
    		  db = new DatabaseHandler(this);
    	cityListMap = new ArrayList<HashMap<String,String>>();
    	List<Rest> contact = 	db.getContactName(i.getStringExtra("M"),i.getStringExtra("tabid"));
    	for (Rest cn : contact) {
            
       	 HashMap<String, String> map = new HashMap<String, String>();
       	String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Address: " + cn.getAddress() + ",Phone:"+cn.getPhoneNumber();
               // Writing Contacts to log
       	
           
       	map.put(_address, cn.getAddress());
       	map.put(_name, cn.getName());
            // adding HashList to ArrayList
            cityListMap.add(map);
       Log.d("Name: ", log);
    }
    	

      
                ListAdapter a = new SimpleAdapter(
                        CityList.this, cityListMap,
                        R.layout.cairo_list_item, new String[] { _name, _address },
                        new int[] { R.id.Name, R.id.Address});
                // updating listview
                setListAdapter(a);
    	} 
	     // ArrayAdapter<String> a = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name_res);
	     // s.setAdapter(a);
		
       
		 s.setCacheColorHint(Color.BLACK);
		 s.setBackgroundColor(Color.BLACK);
		 
		 
		 
		 s.setOnItemClickListener(this);
		
		s.setOnTouchListener(this); 
		 
	
 }
 
private void ReadAlldata() {
		// Reading all contacts
		
		switch(this._city_code)
		{
		case 0:
			
			Read_Cairo();
			break;
		case 1:
			  Read_Alex();
			break;
		case 2:
			Read_Assuit();
			break;
		}
		
	}

	private boolean isFirstTime() {
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
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	/**
	 * Alex function to insert some data
	 * */
	private void Alex_Fun() {
		db = new DatabaseHandler(this);
		s = (ListView) findViewById(android.R.id.list);
		name_res = new ArrayList<String>();
		cityListMap = new ArrayList<HashMap<String, String>>();

		/**
		 * CRUD Operations
		 * */
		if (isFirstTime()) {
			// insert data

			Log.d("Insert: ", "Inserting ..");
			db.AddAlex(new Rest("مطعم ملوكية",
					"32 ش النصر معالم الطريق: داخل فندق امون المنشية ",
					"034818351"));
			db.AddAlex(new Rest("حسني المندرة",
					"المندرة - شارع جمال عبد الناصر - الاسكندرية", "035507799"));
			db.AddAlex(new Rest("ماجستي المعمورة",
					"عمارة الكوثر الامن العام شارع النصر", "19915"));
			db.AddAlex(new Rest("بست واي إسكندرية",
					"ش فيكتور عمانويل - أبراج شهر زاد - مصطفى كامل", ""));
			db.AddAlex(new Rest("كوك دور لوران",
					"37 شارع شعرواى لوران  الاسكندرية )", "16999"));
			db.AddAlex(new Rest("حضر موت العجمي",
					"125 ش البيطاش الرئيسي - العجمي", "01000962299"));
			db.AddAlex(new Rest("حضر موت الهانوفيل",
					"طريق اسكندرية مطروح - بجوار كفتريا العوامي - الهانوفيل",
					"01009697961"));
			db.AddAlex(new Rest("كنتاكي محطة الرمل", "1 شارع سعد زغلول الرمل",
					"19019"));
			db.AddAlex(new Rest("ماكدونالدز ميامي",
					"277 - ش جمال عبد الناصر - الاسكندرية", "035493998"));
			db.AddAlex(new Rest("ماكدونالدز سان ستيفانو",
					"سان ستيفانو مول - الاسكندرية", "034690102"));
			db.AddAlex(new Rest("ماكدونالدز سموحة",
					"ش فيكتور ايمانويل - نادي سموحة - الاسكندرية", "034255644"));
			db.AddAlex(new Rest("ماكدونالدز محطة الرمل",
					"66 ش صفية زغلول - محطة الرمل - الاسكندرية", "034872879"));
			db.AddAlex(new Rest("مؤمن المنتزه",
					"فتح الله ماركت بجوار أبراج شيراتون", "16600"));
			db.AddAlex(new Rest("بيتزا هت كارفور إسكندرية",
					"سيتي سنتر الأسكندرية", "033970129"));
			db.AddAlex(new Rest("ببيتزا هت رشدي", "238 شارع الكورنيش", "19000"));

		}
		
		ReadAlldata();

	}

	/**
	 * Assuit function to insert some data
	 * */
	private void Assuit_Fun() {
		db = new DatabaseHandler(this);
		s = (ListView) findViewById(android.R.id.list);
		name_res = new ArrayList<String>();

		cityListMap = new ArrayList<HashMap<String, String>>();
		/**
		 * CRUD Operations
		 * */
		if (!(isFirstTime())) {
			// insert data
			Log.d("kjjdkshdjs", "dlkjaedw");
			Log.d("Insert: ", "Inserting ..");
			db.addAssuit(new Rest("سلطانة",
					"100 ش محمد على مكارم بجوار مدرسة الجلاء الاعدادية",
					"0882350273"));
			db.addAssuit(new Rest("مصطفى درويش",
					"ش الجمهورية بجوار محطة البنزين", "0882305813"));
			db.addAssuit(new Rest("بيتزا رويال", "ش الهلالى ابراج المدشة",
					"0882328006"));
			db.addAssuit(new Rest("بيتزا ألذ",
					"ش النميس أمام معرض النساجون الشرقيون", "0882318102"));
			db.addAssuit(new Rest("بندقة", "29 ش محمد على مكارم", "0882356555"));
			db.addAssuit(new Rest("كازبلانكا سويت",
					"وسط البلد امام فندق الدار البيضاء", "08823242727"));
			db.addAssuit(new Rest("كوك دور",
					"ش جامعة الازهر امام ابراج القضاة", "16999"));
			db.addAssuit(new Rest("مؤمن",
					"ش كورنيش النيل بجوار نادى الحقوقيين", "16600"));
			db.addAssuit(new Rest("الحاج راضى",
					"ش 26 يوليو العتبة الزرقاء - امام كنيسة الكاثوليك",
					"0882360279"));
			db.addAssuit(new Rest("طلعت",
					"ش خالد بن الوليد المتفرع من ش الجمهورية برج لأمل",
					"0882300942"));
			db.addAssuit(new Rest(" بيتزا تو يتى",
					"11 ش يسرى راغب - برج الكرنك", "0882367575"));
			db.addAssuit(new Rest("باتيسيا  اليونانى",
					"ش المحافظة بجوار مدرسة الوحدة العربية", "0882312666"));
			db.addAssuit(new Rest("كريب ألذ", "اخر النميس بجوار سمكمك",
					"01145507825"));
			db.addAssuit(new Rest("ألذ", "شارع الجامعة امام مكتبات الجامعة",
					"0882340180"));
			db.addAssuit(new Rest("بتزا لاكازا",
					"تقسيم البترول - برج الحقوقيين", "0882287252"));
			db.addAssuit(new Rest("بيتزا بيكانتو",
					"شارع الجمهورية برج رياض الصالحيين", "01066006101"));
			db.addAssuit(new Rest("الشرق الأوسط", "بجوار ريموندس للأزياء",
					"01000380308"));
			db.addAssuit(new Rest("ماك ميكس", "شارع يسرى راغب", "01096722664"));
			db.addAssuit(new Rest("سمر",
					"2 شارع يسرى راغب شركة قلتة امام الجمعية التعاونية",
					"01060189100"));
			db.addAssuit(new Rest("عجيبة",
					"ش يسرى راغب أمام مطعم البركة بجوار نفق الزهراء",
					"01114221344"));
			db.addAssuit(new Rest("فيتساي", "شارع العادلي بجوار برج القدس",
					"0882292777"));
			db.addAssuit(new Rest("كشرى عزو", "شارع سيتى امام معرض السجاد",
					"01114422311"));
			db.addAssuit(new Rest("اسماك الحمد",
					"شارع سيتي امام ابراج الزراعيين", "0882359200"));
			db.addAssuit(new Rest("الطحاوي",
					"تقاطع شارع محمود رشوان مع شارع العادلي امام مسجد مكة",
					"0889201859"));
			db.addAssuit(new Rest("اولاد الشيخ",
					"شارع الهلالى امام الدفاع المدني ", "0882306214"));
			db.addAssuit(new Rest("مطعم وكافتريا الرومانية",
					"آول كوبرى فيصل أمام مدرسة الجامعة", "01000398102"));
			db.addAssuit(new Rest("سمكمك الزعيم", " ش النميس", "0882316080"));
			db.addAssuit(new Rest("مترو", "شارع الجيش ", "0882366898"));
			db.addAssuit(new Rest("سمارة", " ش الهلالى", "01001104759"));
			db.addAssuit(new Rest("نيو كوكولاند",
					"66 ش عمر بن عبدالعزيز بجوار كنيسة النعمة ", "01227075178"));
			db.addAssuit(new Rest("جلاتريا روما",
					"ش جامعة الأزهر - برج النزهة", "0882180775"));
			db.addAssuit(new Rest("سكوير بيتزا",
					"تقسيم البترول أمام الكنيسة الإنجيلية", "01002263153"));
			db.addAssuit(new Rest("عمرو سمير", "أبراج النصر أمام مديرية الصحة",
					"01096584410"));
			db.addAssuit(new Rest("كرم فرع 1", " حى السادات", "01002292291"));
			db.addAssuit(new Rest("كرم فرع 2", "ميدان البدرى أمام مدرسة ناصر ",
					"01009313303"));
			db.addAssuit(new Rest("بيتزا كريم", "أمام مدرسة ناصر الإعدادية",
					"0882288477"));
			db.addAssuit(new Rest("ساندوتش كبدة على بركة الله",
					"ش الهلالى بجوار نفابة التطبيقيين", "01063019550"));
			db.addAssuit(new Rest("كشرى حمو", "ش 26 يوليو أمام مستشفى الإيمان",
					"0882335030"));
			db.addAssuit(new Rest("كوك وندو",
					"ش النيل - أبراج السعوديين - برج 1", "0882313310"));
			db.addAssuit(new Rest("مرجان",
					"جامعة أسيوط - أمام كلية الهندسة أعلى صالات الامتحانات",
					"01150842111"));
			db.addAssuit(new Rest("برونتو", "40 نهاية ش النميس", "01207888863"));
			db.addAssuit(new Rest("مطعم أرزاق",
					"ش كورنيش النيل - برج السعوديين", "0882285857"));
			db.addAssuit(new Rest("مسمط بوحة",
					"ش الكرنك متفرع من يسرى راغب أمام بيتزا تويتى",
					"0882375357"));
			;
			db.addAssuit(new Rest("الأزهر", "عمارة الأوقاف 4", "01146705957"));
			db.addAssuit(new Rest("الرحمن", "برج معونة الشتا", "01223934663"));
			db.addAssuit(new Rest("الطاحونة", "الزهراء - ش العادلى",
					"0882289583"));
			db.addAssuit(new Rest("سن شين", "ش الكورنيش - أمام جراند نايل",
					"0882314907"));
			db.addAssuit(new Rest("كشرى أولاد البلد",
					"ش التمليك البحرى - حى الأربعين", "01003510244"));
			db.addAssuit(new Rest("الحسن والحسين",
					"أبراج النصر - بجوار لاند بوند", "01019599555"));
			db.addAssuit(new Rest("بورست ألذ", "ش النميس - بجوار مسجد العطار",
					"0882296677"));
			db.addAssuit(new Rest("مطعم الرومانى للمشويات",
					"ش 26 يوليو - أمام برج السكرى", "0889202046"));
			db.addAssuit(new Rest("ساندوتش المصرى",
					"ش الهلالى - بجوار الدفاع المدنى", "01005509005"));
			db.addAssuit(new Rest("كشرى الوحيد",
					"شركة فريال - أمام برج الجعار", "0882284529"));
			db.addAssuit(new Rest("كشرى الخديوى",
					"ش الهلالى - بجوار كافتريا أبو على", "01114133083"));
			db.addAssuit(new Rest("كشرى جلال", "ش ثابت", "0882331760"));
			db.addAssuit(new Rest("كشرى حسام الدين",
					"ش الجلاء أمام نفق الزهراء بجوار عمر افندى", "01064820801"));
			db.addAssuit(new Rest("كشرى سلطان زمان", "ش النميس - بجوار سمكمك",
					"0882307478"));
			db.addAssuit(new Rest("كشرى علاء الدين",
					"ش الجمهورية - أول ش خالد بن الوليد", "0882335760"));
			db.addAssuit(new Rest("مطعم 3 ستار",
					"أبراج النصر - بجوار لاند بوند", "01005176295"));
			db.addAssuit(new Rest("مطعم أبو سالم",
					"مدينة الأربعين - أمام المجلس", "01008152411"));
			db.addAssuit(new Rest("مطعم أبو عبده",
					"ش بورسعيد - أمام مخبز أبو دوح السياحى", "01003597442"));
			db.addAssuit(new Rest("مطعم أمير البحار",
					"ش الهلالى - 12 ش إسماعيل القبانى بجوار مكتبة العروبة",
					"01000482605"));
			db.addAssuit(new Rest("مطعم الاسكندرانى",
					"ش الهلالى برج الحقوقيين", "0882306999"));
			db.addAssuit(new Rest("مطعم البركة",
					"ش يسرى راغب - بجوار الجمعية التعاونية", "0882343436"));
			db.addAssuit(new Rest("مطعم الحاج راضى وأولاده",
					"ش 26 يوليو - العتبة الزرقاء أمام كنيسة الكاثوليك",
					"0882360279"));
			db.addAssuit(new Rest("مطعم السكرية", "ش 23 يوليو", "0882359693"));
			db.addAssuit(new Rest("مطعم اللوتس",
					"ش الهلالى - أمام مسجد الخلفاء الراشدين", "01116124466"));
			db.addAssuit(new Rest("مطعم بيتو",
					"جامعة أسيوط - أمام كلية هندسة - أمام قاعة الامتحانات",
					"0882344195"));
			db.addAssuit(new Rest("مطعم بوب",
					"شركة فريال - 26 ش جودة الأسدى أمام النساجون الشرقيون",
					"0882286555"));
			db.addAssuit(new Rest("مطعم المصراوية",
					"حى السادات أمام مستشفى الحميات", "01126392122"));
			db.addAssuit(new Rest("مطعم المراكبى",
					"ش حسن الباقورىأبراج النصر أمام مديرية الصحة", "0882186067"));
			db.addAssuit(new Rest("مطعم المختار 2",
					"مدينة الأربعين - أمام المجلس", "01002925684"));
			db.addAssuit(new Rest("مطعم المحيط",
					"ش أحمد مدحت متفرع من محمد على مكارم", "01007041475"));
			db.addAssuit(new Rest("مطعم الليل وآخره",
					"حى السادات - أمام مستشفى فاروق مراد", "01009272325"));
			db.addAssuit(new Rest("مطعم بيكانتو",
					"ش الجمهورية - برج رياض الصالحين", "0882291012"));
			db.addAssuit(new Rest("مطعم زياد", "برج الطائف - ش أحمد على علوبة",
					"01003678191"));
			db.addAssuit(new Rest("مطعم زيادة 2",
					"ش القدس - المتفرع من ش محمود رشوان", "01117453713"));
			db.addAssuit(new Rest("مطعم زين العابدين",
					"ش رياض - بجوار نادى ناصر", "01009867234"));
			db.addAssuit(new Rest("مطعم كويك", "ش يسرى راغب - برج الزهور",
					"0882353696"));
			db.addAssuit(new Rest("مطعم قاصد كريم", "ميدان المجاهدين",
					"01096134770"));
			db.addAssuit(new Rest("مطعم فتافيت",
					"22 ش الهلالى - بجوار نقابة التطبيقيين", "088284777"));
			db.addAssuit(new Rest("مطعم عالم اليوم", "21 ش الهلالى",
					"0882311380"));
			db.addAssuit(new Rest("مطعم لبنانى",
					"ش الجمهورية - مدخل أبراج عثمان بن عفان", "0882373996"));
			db.addAssuit(new Rest("مطعم وصايا",
					"ش الهلالى - برج الحقوقيين 2 بجوار الدفاع المدنى",
					"0889203501"));
			db.addAssuit(new Rest("مطعم وكباب أولاد الحاج ثابت",
					"ش سيتى الأول - أبراج الزراعيين برج  هـ", "01121526152"));
			db.addAssuit(new Rest("مطعم وكبابجى السعد", "ش الصحفى",
					"01003618713"));
			db.addAssuit(new Rest("مطعم يا ما انت كريم يارب",
					"حى السادات - ش مسجد الحق", "01062028922"));

		}

		
		ReadAlldata();
	}

	/**
	 * Cairo function to insert some data
	 * */
	private void Cairo_Fun() {

		db = new DatabaseHandler(this);
		s = (ListView) findViewById(android.R.id.list);

		name_res = new ArrayList<String>();

		cityListMap = new ArrayList<HashMap<String, String>>();
		/**
		 * CRUD Operations
		 * */
		if (!(isFirstTime())) {
			// insert data
			Log.d("kjjdkshdjs", "dlkjaedw");
			Log.d("Insert: ", "Inserting ..");
			db.addContact(new Rest("توم أند بصل طلعت حرب", "طلعت حرب",
					"025760800"));
			db.addContact(new Rest("أناضول إكسبرس وسط البلد",
					"14 ش شامبليون - تقاطع محمد بسيوني", "027732222"));
			db.addContact(new Rest("كوك دور وسط البلد",
					"44 شارع هدى شعراوى - ميدان الفلكى", "16999"));
			db.addContact(new Rest("كنتاكي وسط البلد  عبد الخالق ثروت ",
					"21 عبد الخالق ثروت وسط المدينة", "19019"));
			db.addContact(new Rest("كنتاكي وسط البلد طلعت حرب",
					"8 شارع طلعت حرب وسط المدينة", "19019"));
			db.addContact(new Rest("كنتاكي وسط البلد التحرير",
					"9 ميدان التحرير وسط المدينة", "19019"));
			db.addContact(new Rest("ماكدونالدز التحرير",
					"6 الأمير كاتادار من شارع محمد محمود - التحرير", "19991 "));
			db.addContact(new Rest("ماكدونالدز سليمان باشا",
					"24 ش طلعت حرب - أمام سينما مترو - وسط المدينة", "19991"));
			db.addContact(new Rest("الشبراوي عباس العقاد", "47 ش عباس العقاد",
					"024031069"));
			db.addContact(new Rest("زنجر مدينة نصر",
					"3 شارع أفريقيا - امتداد مصطفى النحاس بعد مدارس المنهل",
					"01008982225"));
			db.addContact(new Rest("حمو",
					"سوق 27 - نهاية إمتداد مكرم عبيد - بجوار معهد الألسن",
					"026717177"));
			db.addContact(new Rest("سي فود هوس",
					"55 شارع عباس العقاد - مدينة نصر", "022611751"));
			db.addContact(new Rest("تاكوبي مدينة نصر",
					"34 شارع عطية الصوالحي -أمام السراج مول - مدينة نصر ",
					"022877435"));
			db.addContact(new Rest(
					"فيروز",
					"23 شارع سمير عبد الرؤوف امتداد مكرم عبيد - بجوار تشايلد مول - مدينة نصر",
					"011140123441"));
			db.addContact(new Rest(
					"بيت العمدة",
					"6 شارع هشام لبيب - أخر مكرم عبيد - بجوار موبليات سعد محمد سعد - مدينة نصر",
					"01006466106"));
			db.addContact(new Rest("الدوار مدينة نصر", "16 شارع مصطفى النحاس",
					"022738964"));
			db.addContact(new Rest(
					"برونتو",
					"51 شارع سمير عبد الرؤوف - إمتداد مكرم عبيد - بجوار معهد الألسن",
					"01017196399"));
			db.addContact(new Rest("بسمتيو مدينة نصر",
					"مدينة نصر: 54 شارع مصطفى النحاس - تقاطع مكرم عبيد",
					"16400"));
			db.addContact(new Rest("البرج",
					"2 شارع عبد الله العربي - من مصطفى النحاس - مدينة نصر ",
					"01274040730"));
			db.addContact(new Rest("طيبات مصرية",
					"12 ش عطية الصوالحي أمام السراج مول - مدينة نصر",
					"0111215133"));
			db.addContact(new Rest("شاورمة أبو مازن السورية مدينة نصر",
					"16 ش مصطفى النحاس بجوار البنك الأهلي المتحد",
					"01114333320"));
			db.addContact(new Rest(
					"حسونة",
					"10 ش سمير عبد الرؤوف بجوار معهد الألسن - مكرم عبيد - مدينة نصر ",
					"01061838118"));
			db.addContact(new Rest("الشيف سرحان", "مدينة نصر", "16014"));
			db.addContact(new Rest("ماجيستي مدينة نصر",
					"7 شارع عباس العقاد - مدينة نصر", "19915"));
			db.addContact(new Rest("أناضول إكسبرس م.نصر", "جنينة مول",
					"022610810"));
			db.addContact(new Rest("بفلو برجر",
					"60 ش عبد الله العربي - مدينة نصر", "19914"));
			db.addContact(new Rest("روما بيتزا 2 جو",
					"60 ش عبد الله العربي - مدينة نصر", "19914"));
			db.addContact(new Rest("بست واي مدينة نصر",
					"48 ش عبد الله العربي - الحي السابع - مدينة نصر ",
					"022620249"));
			db.addContact(new Rest("كوك دور مدينة نصر (عباس العقاد)ـ",
					"6 شارع الصناعه خلف مستشفى التوفيقية  عباس العقاد)",
					"16999"));
			db.addContact(new Rest("كوك دور مدينة نصر (حسن المأمون)ـ",
					"71 شارع الحريه - مصر الجديدة", "16999"));
			db.addContact(new Rest("كوك دور سيتي ستارز",
					"مول سيتى ستارز - منطقة المطاعم - الملحق 2", "024802348"));
			db.addContact(new Rest("استاكوزا فيش مدينة نصر",
					"ش محمد مقلد من مصطفى النحاس - مدينة نصر", "19666"));
			db.addContact(new Rest("حضر موت مصطفى النحاس",
					"43 ش مصطفى النحاس - مدينة نصر", "01146668152"));
			db.addContact(new Rest(
					"حضر موت مكرم عبيد",
					"30 ش حسن ابراهيم من مكرم عبيد بجوار مفكو حلوان - مدينة نصر ",
					"01228606461"));
			db.addContact(new Rest(
					"حضر موت ميدان الساعة",
					"6 عمارات أول مايو - طريق النصر - ميدان الساعة - مدينة نصر",
					"022713818"));
			db.addContact(new Rest(
					"باب الحارة",
					"18 هشام لبيب بجوار موبيليات سعد محمد سعد- مكرم عبيد - مدينة نصر",
					"01156495330"));
			db.addContact(new Rest("أبو أحمد",
					"398 ش أحمد الزمر- ميدان الحي العاشر - مدينة نصر",
					"01000300651"));
			db.addContact(new Rest(
					"شاورما دريم",
					"4 ش محمد مقلد من مصطفى النحاس - بعد أولاد رجب - الحي الثامن - مدينة نصر ",
					"01126426005"));
			db.addContact(new Rest(
					"مطعم التقوى",
					"شارع محمد مقلد - من مصطفى النحاس - سوق 68 - أول يمين بعد أولاد رجب - مدينة نصر",
					"01222903022"));
			db.addContact(new Rest(
					"الشرقاوي سيتي ستارز",
					"4 عمارات الإمداد والتموين - بجوار دار الإمداد والتموين - طريق النصر - مدينة نصر",
					"01114130678"));
			db.addContact(new Rest("جندوفلي مدينة نصر",
					"30 شارع أبو داوود الظاهري - مكرم عبيد - مدينة نصر ",
					"02275577"));
			db.addContact(new Rest("أسماك العقاد",
					"137 مصطفى النحاس - بجوار أولاد رجب - الحي الثامن",
					"01093514159"));
			db.addContact(new Rest("فلفول 1",
					"386 شارع أحمد الزمر - موقف الحي العاشر - مدينة نصر",
					"022726428"));
			db.addContact(new Rest("الأصيل مصطفى النحاس",
					"120 مصطفى النحاس - مدينة نصر", "01001752232"));
			db.addContact(new Rest("كشري الشيخ",
					"397 ش أحمد الزمر - الحي العاشر - مدينة نصر", "01004414834"));
			db.addContact(new Rest("فتحي السويركي",
					"528 أحمد الزمر - أمام مركز كبار الممولين - م.نصر",
					"01061105844"));
			db.addContact(new Rest(
					"بلح البحر",
					"406شارع أحمد الزمر - ميدان الحي العاشر - مدينة نصر - بعد سوق السيارات",
					"01000900240"));
			db.addContact(new Rest("أبو مازن السوري الأصلي - السراج مول",
					"السراج مول - مدينة نصر ", "026706203"));
			db.addContact(new Rest("كنتاكي مدينة نصر",
					"28 ش مصطفى النحاس - مدينة نصر", "19019"));
			db.addContact(new Rest("ماكدونالدز سيتي ستارز ",
					"سيتي ستارز مول - منطقة المطاعم 1- مدينة نصر", "024802088"));
			db.addContact(new Rest("ماكدونالدز جنينة مول",
					"شارع البطراوي - مدينة نصر ", "19991"));
			db.addContact(new Rest("ماكدزنالدز مصطفى النحاس",
					"91 شارع مصطفى النحاس - مدسنة نصر", "026702193"));
			db.addContact(new Rest("ماكدونالدز عباس العقاد",
					"39 شارع عباس العقاد - مدينة نصر", "024023899"));
			db.addContact(new Rest("أفندينا",
					"1 ش إبراهيم الرفاعي - أمام سنترال مدينة نصر 2",
					"01128080862"));
			db.addContact(new Rest("مؤمن عباس العقاد",
					"11 شارع عباس العقاد - مدينة نصر", "16600"));
			db.addContact(new Rest("بيتزا هت عباس العقاد",
					"9 شارع عباس العقاد", "19000"));
			db.addContact(new Rest("زنجر المعادي",
					"1 شارع 270 تقاطع ش النصر - أمام ألأبان المالكي",
					"0182717444"));
			db.addContact(new Rest("الدوار المعادي", "  شارع 9", "023803510"));
			db.addContact(new Rest("توم أند بصل", "المعادي", "025166080"));
			db.addContact(new Rest("ماجيستي المعادي", "1 ش 270 شارع النصر",
					"19915"));
			db.addContact(new Rest("كوك دور كارفور المعادي", "كارفور المعادي",
					"16999"));
			db.addContact(new Rest("كوك دور المعادى النصر",
					"2/8 شارع النصر المعادى الجديده", "16999"));
			db.addContact(new Rest("أرزاق الكوثر المعادي 2",
					"طريق مصر حلوان الزراعي", "16107"));
			db.addContact(new Rest("جندوفلي معادي",
					"22 شارع النصر - المعادي الجديدة", "025202250"));
			db.addContact(new Rest("ثري ستارز المعادي",
					"97 عمارات امتداد الأمل - طريق الأتوستراد - المعادي",
					"027000301"));
			db.addContact(new Rest("كنتاكي المعادي  شارع النصر",
					"9/2 ش النصر، المعادى الجديدة المعادى", "19019"));
			db.addContact(new Rest("ماكدونالدز المعادي شارع النصر",
					"7/1 شارع النصر - المعادي الجديدة", "025168875"));
			db.addContact(new Rest("بيتزا هت المعادي", "73 شارع 9 - المعادي",
					"19000"));
			db.addContact(new Rest("الدوار مصر الجديدة",
					"45 شارع عثمان بن عفان - مصر الجديدة", "024179464"));
			db.addContact(new Rest("بسمتيو مصر الجديدة",
					"مصر الجديدة: 58 شارع أبو بكر الصديق - ميدان سفير", "16400"));
			db.addContact(new Rest("شاورمة أبو مازن السورية مصر الجديدة",
					"3 أ شارع دمشق من إبراهيم اللقاني", "01114333320"));
			db.addContact(new Rest("ماجيستي روكسي",
					"76 شارع الخليفة المأمون - روكسي - مصر الجديدة", "19915"));
			db.addContact(new Rest("حضر موت البخاري مصر الجديدة",
					"95 ب ش الحجاز - مصر الجديدة - أمام حلو الصمدي",
					"026398855"));
			db.addContact(new Rest("كوك دور روكسي",
					"6 شارع السيد المرغنى مصر الجديده", "16999"));
			db.addContact(new Rest("كوك دور القاهرة الجديدة",
					"قطعة رقم 1 - مركز خدمات التجمع الخامس - القاهرة الجديدة",
					"16999"));
			db.addContact(new Rest("الدهان المرغني",
					"70 شارع الميرغني - مصر الجديدة", "16194"));
			db.addContact(new Rest("كنتاكي مصر الجديدة  مصر والسودان",
					"115 شارع مصر والسودان - مصر الجديدة", "19019"));
			db.addContact(new Rest("كنتاكي مصر الجديدة الخليفة المأمون",
					"كنتاكي مصر الجديدة (الخليفة المأمون)", "19019"));
			db.addContact(new Rest("كنتاكي مصر الجديدة  روكسي",
					"4 ش السيد الميرغني، روكسي مصر الجديدة", "19019"));
			db.addContact(new Rest("أرابياتا روكسي",
					"ش الخليفة المأمون - مصر الجديدة", "16919"));
			db.addContact(new Rest("ثري ستارز مصر الجديدة",
					"189 شارع النزهة - ميدان الحجاز", "026445704"));
			db.addContact(new Rest("مصعب - مصر الجديدة",
					"163 شارع النزهة - الحجاز - مصر الجديدة ", "16065"));
			db.addContact(new Rest("كنتاكي الزمالك",
					"49 أ شارع أبو الفدا الزمالك", "19019"));
			db.addContact(new Rest("ماكدونالدز الميرغني",
					"110 شارع الميرغني - مصر الجديدة", "024175996"));
			db.addContact(new Rest("ماكدونالدز شيل ش 90",
					"التجمع الخامس - القاهرة الجديدة - شارع التسعين",
					"029297980"));
			db.addContact(new Rest("ماكدونالدز الزمالك",
					"9ب , شارع أبو الفدا - الزمالك", "027380349"));
			db.addContact(new Rest("مؤمن تريومف",
					"94 شارع عثمان بن عفان - ميدان تريومف - مصر الجديدة",
					"16600"));
			db.addContact(new Rest("مؤمن حدائق القبة",
					"السراي مول - والي الأحد - حدائق القبة", "16600"));
			db.addContact(new Rest("بيتزا هت مصر الجديدة الهرم",
					"38 شارع الهرم - مصر الجديدة", "022580518"));
			db.addContact(new Rest("بيتزا هت الزمالك",
					"9 ب أيو الفدا - الزمالك", "19000"));
			db.addContact(new Rest("بيتزا هت الميري لاند",
					"102 ش السبك، مصر الجديدة", "19000"));
			db.addContact(new Rest("توم أند بصل شيراتون", "شيراتون",
					"022666752"));
			db.addContact(new Rest("شاورمة ومشاوي أبو مازن شيراتون",
					"1 شارع أنقرة - النطقة 2 - بجوار كنتاكي - شيراتون",
					"022663448"));
			db.addContact(new Rest(
					"بست واي شيراتون",
					"41 شارع أنقرة - مساكن مصر للتعمير - المنطقة الأولى - شيراتون",
					"022680711"));
			db.addContact(new Rest("حمادة شيراتون",
					"54 عمارات صقر قريش خلف توم اند بصل - مساكن شيراتون",
					"01111418922"));
			db.addContact(new Rest("الدوار المنيل", "92 شارع متحف المنيل",
					"023610935"));
			db.addContact(new Rest(
					"برونتو",
					"51 شارع سمير عبد الرؤوف - إمتداد مكرم عبيد - بجوار معهد الألسن",
					"01017196399"));
			db.addContact(new Rest("توم أند بصل شبرا", "شبرا", "022010027"));
			db.addContact(new Rest("توم أند بصل الرحاب", "مدينة الرحاب",
					"026927773"));
			db.addContact(new Rest("ماجيستي العباسية",
					"أمام مستشفى القوات الجوية بجوار أولاد رجب", "19915"));
			db.addContact(new Rest("الحرمين روض الفرج",
					"12 ش روض الفرج - أمام مستشفى تبارك للأطفال", "01225170111"));
			db.addContact(new Rest("الحرمين شبرا",
					"154 ش الترعة - بجوار إشارة أحمد بدوي", "01227092688"));
			db.addContact(new Rest("الحرمين مظلات",
					"286 ش شبرا - أمام سينما مودرن - المظلات", "01225819266"));
			db.addContact(new Rest("سنتر الأمير للأسماك",
					"90 ش شبرا - دوران شبرا", "024572084"));
			db.addContact(new Rest("ملك الجمبري شبرا", "125 ش شبرا دوران شبرا",
					"022025798"));
			db.addContact(new Rest("أبو محمد الشرقاوي",
					"39 ش عبد الحميد الديب - ناصية مسجد الرحمة - الخلفاوي",
					"022058626"));
			db.addContact(new Rest("الشبراوي جسر السويس",
					"172 ش جسر السويس - بجوار التجنيد", "024016897"));
			db.addContact(new Rest("أناضول إكسبرس الرحاب",
					"السوق التجاري القديم بجوار كمبيوتر شوب", "026927777"));
			db.addContact(new Rest("استاكوزا فيش الرحاب",
					"16 السوق التجاري - مدينة الرحاب", "01000887174"));
			db.addContact(new Rest("تيبستي نيو",
					"1373 ش كورنيش النيل - أبراج أغاخان - شبرا مصر",
					"01010333270"));
			db.addContact(new Rest(
					"الشرقاوي",
					"28 ش سمير عبد الرؤوف - إمتداد مكرم عبيد - بجوار عمر أفندي - مدينة نصر",
					"01008797744"));
			db.addContact(new Rest("الدهان الحسين",
					"16 شارع جوهر القائد - المركز التجاري", "02593925"));
			db.addContact(new Rest("الدهان الرحاب",
					"محل 183-184 منطقة الخدمات - السوق التجاري", "01225959100"));
			db.addContact(new Rest(
					"الشرقاوي السراج مول",
					"3 شارع عبد الحميد لطفي - أخر مكرم عبيد - أمام بنك القاهرة - بجوار توكيل نوكيا",
					"01065843678"));
			db.addContact(new Rest(
					"الشرقاوي سيتي ستارز",
					"4 عمارات الإمداد والتموين - بجوار دار الإمداد والتموين - طريق النصر - مدينة نصر",
					"01114130678"));
			db.addContact(new Rest(
					"الدهان التجمع الخامس",
					"محل رقم 82 - 83 سيلفر ستار مول - شارع أخناتون - الحي الأول - التجمع الخامس",
					"012288881117"));
			db.addContact(new Rest("جندوفلي التجمع الخامس",
					"ش اخناتون - سلفر ستار مول - الحي الأول - بجوار موبيل",
					"026161811"));
			db.addContact(new Rest("فطاطري أحفاد أبو سريع القليوبي",
					"5 شارع معمل الألبان - أمام مسجد الفتح - الخلفاوي",
					"01124448924"));
			db.addContact(new Rest("العائلات",
					"145 أ شارع شبرا - أمام كلية الهندسة", "022038173"));
			db.addContact(new Rest("الأصيل مصطفى النحاس",
					"120 مصطفى النحاس - مدينة نصر", "022879715"));

		}
 
		ReadAlldata();
	}

}
