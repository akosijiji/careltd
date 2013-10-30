package com.innovalynx.careltd;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHouse extends Activity implements OnClickListener {
	
	ImageView iv;
	TextView houseName, housePrice;
	String valueHouseName, valueHousePrice;
	int img;
	Button back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.viewhouse);
		
		initControls();
		
		callHouseNames();
		
	}

	private void callHouseNames() {
		// TODO callHouseNames
		Bundle extras = getIntent().getExtras();
		valueHouseName = extras.getString("house_name");
		valueHousePrice = extras.getString("house_price");
		if(valueHouseName.equals("5 Dina Retreat, Carrum Downs")){
			img = R.drawable.dinaretreat;
			displayDetails();
		} else if (valueHouseName.equals("Lot 941 Cob Terrace, Clyde North")){
			img = R.drawable.cobterrace;
			displayDetails();
		} else if (valueHouseName.equals("Lot 932 Ventasso Street, Clyde")){
			img = R.drawable.ventassostreet;
			displayDetails();
		} else if (valueHouseName.equals("Lot 515 Summerhill Blvd, Drouin")){
			img = R.drawable.summerhillblvddrouin;
			displayDetails();
		} else if (valueHouseName.equals("Lot 17 Todman Street, Drouin (Option 2)")){
			img = R.drawable.todmanstreetdrouin;
			displayDetails();
		} else if (valueHouseName.equals("Lot 32 Aqueduct Road, Langwarrin")){
			img = R.drawable.aqueductroad;
			displayDetails();
		} else if (valueHouseName.equals("Units 2-7, 269 North Road, Langwarin")){
			img = R.drawable.northroad;
			displayDetails();
		} else if (valueHouseName.equals("U 39, 40-60 Potts Road, Langwarrin")){
			img = R.drawable.pottsroad;
			displayDetails();
		} else if (valueHouseName.equals("Lot 1 McClenaghan Place, Pakenham")){
			img = R.drawable.onemcclenaghanplace;
			displayDetails();
		} else if (valueHouseName.equals("Lot 2 McClenaghan Place, Pakenham")){
			img = R.drawable.twelvemcclenaghanplace;
			displayDetails();
		} else if (valueHouseName.equals("Lot 3 McClenaghan Place, Pakenham")){
			img = R.drawable.threemcclenaghanplace;
			displayDetails();
		} else if (valueHouseName.equals("Lot 4 McClenaghan Place, Pakenham")){
			img = R.drawable.fourmcclenaghanplace;
			displayDetails();
		} else if (valueHouseName.equals("Lot 5 McClenaghan Place, Pakenham")){
			img = R.drawable.fivemcclenaghanplace;
			displayDetails();
		} else if (valueHouseName.equals("Lot 7 McClenaghan Place, Pakenham")){
			img = R.drawable.sevenmcclenaghanplace;
			displayDetails();
		} else if (valueHouseName.equals("Lot 11 McClenaghan Place, Pakenham")){
			img = R.drawable.elevenplacemcclenaghanplace;
			displayDetails();
		} else if (valueHouseName.equals("Lot 12 McClenaghan Place, Pakenham")){
			img = R.drawable.twelvemcclenaghanplace;
			displayDetails();
		} else if (valueHouseName.equals("Lot 532 Summerhill Blvd, Drouin")){
			img = R.drawable.fivethreetwosummerhillblvddrouin;
			displayDetails();
		} else if (valueHouseName.equals("Lot 17 Ajax Street, Drouin")){
			img = R.drawable.seventeenajaxstreetdrouin;
			displayDetails();
		} else if (valueHouseName.equals("Lot 133 Mountainview Blvd, Cranbourne North")){
			img = R.drawable.onethirtythreemountainviewblvd;
			displayDetails();
		} else if (valueHouseName.equals("Lot 531 Summerhill Blvd, Drouin")){
			img = R.drawable.fivethreeonesummerhillblvddrouin;
			displayDetails();
		} else {
			Log.d("House name", "No such house name available");
		}
	}
	
	private void displayDetails(){
		houseName.setText(valueHouseName);
		housePrice.setText(valueHousePrice);
		iv.setImageResource(img);
	}

	private void initControls() {
		// TODO initControls
		iv = (ImageView) findViewById (R.id.ivHouse);
		houseName = (TextView) findViewById (R.id.tvHouseName);
		housePrice = (TextView) findViewById (R.id.tvHousePrice);
		back = (Button) findViewById (R.id.btBack);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		finish();
	}

}
