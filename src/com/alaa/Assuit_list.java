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

public class Assuit_list extends ListActivity implements OnItemClickListener,
		OnTouchListener {

	ListView s;
	List<String> name_res;
	DatabaseHandler db;
	int flag = 0;
	ArrayList<HashMap<String, String>> Assuitxlist;
	private final String Assuit_name = "Assuit";
	private final String Assuit_Address = "Adress";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.assuit);
		db = new DatabaseHandler(this);
		s = (ListView) findViewById(android.R.id.list);
		name_res = new ArrayList<String>();

		Assuitxlist = new ArrayList<HashMap<String, String>>();
		/**
		 * CRUD Operations
		 * */
		if (isFirstTime()) {
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

		// Reading all contacts
		Log.d("Reading: ", "Reading all contacts..");

		List<Rest> contacts = db.getAllAssuit();

		for (Rest cn : contacts) {
			HashMap<String, String> map = new HashMap<String, String>();
			String log = "Id: " + cn.getID() + " ,Name: " + cn.getName()
					+ " ,Address: " + cn.getAddress() + ",Phone:"
					+ cn.getPhoneNumber();
			// Writing Contacts to log
			name_res.add(cn.getName());

			map.put(Assuit_Address, cn.getAddress());
			map.put(Assuit_name, cn.getName());
			// adding HashList to ArrayList
			Assuitxlist.add(map);
			Log.d("Name: ", log);
		}
		// ArrayAdapter<String> a = new
		// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name_res);
		// s.setAdapter(a);

		ListAdapter adapter = new SimpleAdapter(Assuit_list.this, Assuitxlist,
				R.layout.assuit_list_item, new String[] { Assuit_name,
						Assuit_Address }, new int[] { R.id.Name, R.id.Address });
		// updating listview
		setListAdapter(adapter);

		s.setCacheColorHint(Color.BLACK);
		s.setBackgroundColor(Color.BLACK);

		s.setOnItemClickListener(this);
		s.setOnTouchListener(this);
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
	public void onItemClick(AdapterView<?> myadapter, View myView,
			int myItemInt, long arg3) {
		// TODO Auto-generated method stub
		Rest contact = db.getAssuit(myItemInt + 1);

		// Toast.makeText(Assuit_list.this, "Left Swipe",
		// Toast.LENGTH_SHORT).show();

		myView.setBackgroundColor(color.primary_text_dark);
		Intent intent = new Intent(Intent.ACTION_DIAL);
		String uri = "tel:" + contact.getPhoneNumber().trim();
		Toast.makeText(Assuit_list.this, uri, Toast.LENGTH_SHORT).show();
		Toast.makeText(Assuit_list.this, contact.getName(), Toast.LENGTH_SHORT)
				.show();
		intent.setData(Uri.parse(uri));
		Toast.makeText(Assuit_list.this, "Calling !!", Toast.LENGTH_SHORT)
				.show();
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
		if (event.getAction() == MotionEvent.ACTION_DOWN && flag == 0) {
			flag = 1;
			Toast.makeText(Assuit_list.this, "Click To Call The Restaurant !!",
					Toast.LENGTH_LONG).show();
			Toast.makeText(Assuit_list.this, "Click To Call The Restaurant !!",
					Toast.LENGTH_LONG).show();
		}
		return false;
	}

}
