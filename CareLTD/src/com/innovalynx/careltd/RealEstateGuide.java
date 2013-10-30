package com.innovalynx.careltd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RealEstateGuide extends Activity implements OnClickListener {
	
	Button back;
	TextView guide;
	String strGuide;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.realestateguide);
		
		strGuide = getString(R.string.realestateguide);
		guide = (TextView) findViewById (R.id.tvGuideDescription);
		guide.setText(strGuide);
		//guide.setText(Html.fromHtml(getString(R.string.realestateguide)));
		back = (Button) findViewById (R.id.btBack);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		finish();
	}

}