package com.innovalynx.careltd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Contact extends Activity implements OnClickListener {
	
	Button back;
	TextView tvContact;
	String strContact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);
		
		strContact = getString(R.string.contactdetails);
		tvContact = (TextView) findViewById (R.id.tvContactDetails);
		tvContact.setText(strContact);
		back = (Button) findViewById (R.id.btBack);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		finish();
	}

}
