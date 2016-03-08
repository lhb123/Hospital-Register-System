package com.srdz.bigproject.activity;

import com.srdz.bigproject.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class AboutUsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.aboutus);

	}
}