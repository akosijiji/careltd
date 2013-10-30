package com.innovalynx.careltd;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.innovalynx.careltd.util.DBHelper;

public class ViewHouseAndLand extends Activity implements OnClickListener, OnItemClickListener {
	
	ListView lv;
	String strSpinner;
	
	TextView textview1, textview2;
	
	static final int[] imgs = {
			R.drawable.dinaretreat, // 0
			R.drawable.cobterrace, // 1
			R.drawable.ventassostreet, // 2
			R.drawable.summerhillblvddrouin, // 3
			R.drawable.todmanstreetdrouin, // 4
			R.drawable.aqueductroad, // 5
			R.drawable.northroad, // 6
			R.drawable.pottsroad, // 7
			R.drawable.onemcclenaghanplace, // 8
			R.drawable.twomcclenaghanplace, // 9
			R.drawable.threemcclenaghanplace, // 10
			R.drawable.fourmcclenaghanplace, // 11
			R.drawable.fivemcclenaghanplace, // 12
			R.drawable.sevenmcclenaghanplace, // 13
			R.drawable.elevenplacemcclenaghanplace, // 14
			R.drawable.twelvemcclenaghanplace, // 15
			R.drawable.fivethreetwosummerhillblvddrouin, // 16
			R.drawable.seventeenajaxstreetdrouin, // 17
			R.drawable.onethirtythreemountainviewblvd, // 18
			R.drawable.fivethreeonesummerhillblvddrouin // 19
	};

	static final String[] sortBy = {
		"Select...",
		"Highest Price",
		"Lowest Price",
		"Location"
	};
	
	String[] text, price;
	ArrayList<String> priceList;
	
	private DBHelper dbHelper;
	Cursor cursor;
	
	MyCustomAdapter adapter;
	
	Button back, filter;
	TextView highest, lowest, location;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewhouseandland);
		
		initControls();
		
		displayRecords();
	}

	// TODO displayRecords
	private void displayRecords() {

        checkDatabaseConnection();
        
        text = dbHelper.getAll();
        price = dbHelper.getAllPrices();

        adapter = new MyCustomAdapter(imgs, text, price);
        
        lv.setAdapter(adapter);
        
	}
	
	// TODO checkDatabaseConnection
	private void checkDatabaseConnection() {
        
        dbHelper = new DBHelper(this);

			try {
			
			        dbHelper.createDataBase();
			
			} catch (IOException ioe) {
			
			        throw new Error("Unable to create database");
			
			}
			
			try {
			
			        dbHelper.openDataBase();
			
			} catch (SQLException sqle) {
			
			        throw sqle;
			
			}
	}

	private void initControls() {
		
		// TextViews
		highest = (TextView) findViewById (R.id.tvHighest);
		lowest = (TextView) findViewById (R.id.tvLowest);
		location = (TextView) findViewById (R.id.tvLocation);
		
		// Buttons
		filter = (Button) findViewById (R.id.btFilter);
		back = (Button) findViewById (R.id.btBack);
		back.setOnClickListener(this);
		filter.setOnClickListener(this);
		
		// ListView
		lv = (ListView) findViewById (R.id.lv);
		lv.setFastScrollEnabled(true);
		
		lv.setOnItemClickListener(this);
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
		String strHouseName = "house_name";
		String strHousePrice = "house_price";
		
		textview1 = (TextView) v.findViewById(R.id.text1);
	    textview2 = (TextView) v.findViewById(R.id.text2);
	    
	    String strName = textview1.getText().toString().trim();
	    String strPrice = textview2.getText().toString().trim();

	    Log.v("", strName);
	    Log.v("", strPrice);
		//String selectedFromList =(String) (lv.getItemAtPosition(pos));
		Intent i;
		
		i = new Intent(this, ViewHouse.class);
		i.putExtra(strHouseName, strName);
		i.putExtra(strHousePrice, strPrice);
		startActivity(i);

		/*
		switch(pos){
		case 0:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "5 Dina Retreat, Carrum Downs");
			i.putExtra(strHousePrice, "$300,000.00");
			startActivity(i);
			break;
		case 1:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 941 Cob Terrace, Clyde North");
			i.putExtra(strHousePrice, "$800,000.00");
			startActivity(i);
			break;
		case 2:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 932 Ventasso Street, Clyde");
			i.putExtra(strHousePrice, "$1,000,000.00");
			startActivity(i);
			break;
		case 3:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 515 Summerhill Blvd, Drouin");
			i.putExtra(strHousePrice, "$300,000.00");
			startActivity(i);
			break;
		case 4:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 17 Todman Street, Drouin (Option 2)"); 
			i.putExtra(strHousePrice, "$550,000.00");
			startActivity(i);
			break;
		case 5:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 32 Aqueduct Road, Langwarrin");
			i.putExtra(strHousePrice, "$600,000.00");
			startActivity(i);
			break;
		case 6:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Units 2-7, 269 North Road, Langwarin");
			i.putExtra(strHousePrice, "$450,000.00");
			startActivity(i);
			break;
		case 7:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "U 39, 40-60 Potts Road, Langwarrin");
			i.putExtra(strHousePrice, "$1,300,000.00");
			startActivity(i);
			break;
		case 8:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 1 McClenaghan Place, Pakenham");
			i.putExtra(strHousePrice, "$2,000,000.00");
			startActivity(i);
			break;
		case 9:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 2 McClenaghan Place, Pakenham");
			i.putExtra(strHousePrice, "$300,000.00");
			startActivity(i);
			break;
		case 10:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 3 McClenaghan Place, Pakenham");
			i.putExtra(strHousePrice, "$900,000.00");
			startActivity(i);
			break;
		case 11:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 4 McClenaghan Place, Pakenham");
			i.putExtra(strHousePrice, "$600,000.00");
			startActivity(i);
			break;
		case 12:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 5 McClenaghan Place, Pakenham");
			i.putExtra(strHousePrice, "$1,200,000.00");
			startActivity(i);
			break;
		case 13:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 7 McClenaghan Place, Pakenham");
			i.putExtra(strHousePrice, "$700,000.00");
			startActivity(i);
			break;
		case 14:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 11 McClenaghan Place, Pakenham");
			i.putExtra(strHousePrice, "$500,000.00");
			startActivity(i);
			break;
		case 15:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 12 McClenaghan Place, Pakenham");
			i.putExtra(strHousePrice, "$3,000,000.00");
			startActivity(i);
			break;
		case 16:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 532 Summerhill Blvd, Drouin");
			i.putExtra(strHousePrice, "$300,000.00");
			startActivity(i);
			break;
		case 17:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 17 Ajax Street, Drouin");
			i.putExtra(strHousePrice, "$300,000.00");
			startActivity(i);
			break;
		case 18:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 133 Mountainview Blvd, Cranbourne North");
			i.putExtra(strHousePrice, "$300,000.00");
			startActivity(i);
			break;
		case 19:
			i = new Intent(this, ViewHouse.class);
			i.putExtra(strHouseName, "Lot 531 Summerhill Blvd, Drouin");
			i.putExtra(strHousePrice, "$300,000.00");
			startActivity(i);
			break;
		default: Log.d(strHouseName, "No such house name available");
		}  */
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btBack:
			finish();
			break;
		case R.id.btFilter:
			displayDialog();
			break;
		}
	}

	@SuppressLint("InlinedApi")
	private void displayDialog() {
		// TODO displayDialog
		final ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
	            android.R.layout.simple_spinner_item, sortBy);
		
		LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog_layout, null);
	    
        promptsView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
	    		LayoutParams.WRAP_CONTENT));
   
	    final Spinner mSpinner= (Spinner) promptsView
                .findViewById(R.id.spDialog);
        
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Sort By...");
        builder.setIcon(R.drawable.launcher);
        
        mSpinner.setAdapter(adp);
        mSpinner.setSelection(0);
    	mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
    	
		public void onItemSelected(AdapterView<?> parent, View v,
                int pos, long id) {
			strSpinner = mSpinner.getSelectedItem().toString();

			if(strSpinner.equals("Highest Price")){
        		highest.setTypeface(Typeface.DEFAULT_BOLD);
        		lowest.setTypeface(Typeface.DEFAULT);
        		location.setTypeface(Typeface.DEFAULT);
        		price = dbHelper.sortHighestPrice();
        		
        		adapter = new MyCustomAdapter(imgs, text, price);
        		lv.setAdapter(adapter);
        		adapter.notifyDataSetChanged();
        		
        	} else if (strSpinner.equals("Lowest Price")){
        		highest.setTypeface(Typeface.DEFAULT);
        		lowest.setTypeface(Typeface.DEFAULT_BOLD);
        		location.setTypeface(Typeface.DEFAULT); 
        		price = dbHelper.sortLowestPrice();
        		
        		adapter = new MyCustomAdapter(imgs, text, price);
        		lv.setAdapter(adapter);
        		adapter.notifyDataSetChanged();
        	} else if (strSpinner.equals("Location")) {
        		highest.setTypeface(Typeface.DEFAULT);
        		lowest.setTypeface(Typeface.DEFAULT);
        		location.setTypeface(Typeface.DEFAULT_BOLD);
        	} else {
        		Log.d("Default", "Default");
        	}
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }
    	});

        builder.setPositiveButton("Okay",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        
	    builder.setView(promptsView);
	    AlertDialog alert = builder.create();
        alert.show();
        
        //((Button)alert.findViewById(android.R.id.button1)).setBackgroundResource(R.drawable.custom_button);
        //((Button)alert.findViewById(android.R.id.button2)).setBackgroundResource(R.drawable.custom_button);
	} 

	class MyCustomAdapter extends BaseAdapter
	{
	    String[] data_text1;
	    String[] data_text2;
	    int[] data_image;

	MyCustomAdapter() {
	    data_text1 = null;
	    data_text2 = null;
	    data_image = null;
	}

	MyCustomAdapter(int[] image, String[] house, String[] price) {
	    data_text1 = house;
	    data_text2 = price;
	    data_image = image;
	}

	public int getCount() {
	    return data_text1.length;
	}

	public String getItem(int position) {
	    return null;
	}

	public long getItemId(int position) {
	    return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

	    LayoutInflater inflater = getLayoutInflater();
	    View row;

	    row = inflater.inflate(R.layout.listrow, null);

	    textview1 = (TextView) row.findViewById(R.id.text1);
	    textview2 = (TextView) row.findViewById(R.id.text2);
	    ImageView imageview = (ImageView) row.findViewById(R.id.image);
	    
	    imageview.setScaleType(ImageView.ScaleType.FIT_XY);
	    textview1.setText(data_text1[position]);
	    textview2.setText("$" + (new DecimalFormat("#,###.00")).format(Double.parseDouble(data_text2[position])) );
	    imageview.setImageResource(data_image[position]);

	    return (row);

	    }
	}

}