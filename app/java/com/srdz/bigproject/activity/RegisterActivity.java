package com.srdz.bigproject.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;
import com.srdz.bigproject.R;
import com.srdz.bigproject.bean.UserData;

public class RegisterActivity extends Activity implements
		OnItemSelectedListener, OnClickListener {
	final static String TAG = "Register";

	private EditText userName;
	private EditText userPwd;
	private EditText userPwdAgain;
	private EditText userPersonName;
	private RadioGroup userSex;
	private EditText userIdcard;
	private EditText userTel;
	private Spinner userProvince;
	private Spinner userCity;
	private Button registerButton;
	private CheckBox agreementBox;
	private TextView agreementText;
	private static boolean sex;
	private static String province;
	private static String city;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 启动layout
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		// 找到各个组件
		userName = (EditText) findViewById(R.id.register_userName);
		userPwd = (EditText) findViewById(R.id.register_userPwd);
		userPwdAgain = (EditText) findViewById(R.id.register_userPwdAgain);
		userPersonName = (EditText) findViewById(R.id.register_userPersonName);
		userSex = (RadioGroup) findViewById(R.id.register_userSex);
		userIdcard = (EditText) findViewById(R.id.register_userIdcard);
		userTel = (EditText) findViewById(R.id.register_userTel);
		userProvince = (Spinner) findViewById(R.id.register_userProvince);
		userCity = (Spinner) findViewById(R.id.register_userCity);
		agreementBox = (CheckBox) findViewById(R.id.agreementBox);
		agreementText = (TextView) findViewById(R.id.agreementText);
		registerButton = (Button) findViewById(R.id.register_registerButton);
		// 设置初始值
		RegisterActivity.sex = true;
		// 给组件设置监听器
		registerButton.setOnClickListener(this);
		userSex.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// 获取变更后的选中项的ID
				int radioButtonId = arg0.getCheckedRadioButtonId();
				// 根据ID获取RadioButton的实例
				RadioButton rb = (RadioButton) findViewById(radioButtonId);
				// 设置sex值，以符合选中项
				if (rb.getText().toString().trim().equals("男")) {
					RegisterActivity.sex = true;
				} else if (rb.getText().toString().trim().equals("女")) {
					RegisterActivity.sex = false;
				}
			}
		});
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
				RegisterActivity.this, R.array.province_arry,
				android.R.layout.simple_spinner_item);
		userProvince.setAdapter(adapter1);
		userProvince.setOnItemSelectedListener(this);
		userCity.setOnItemSelectedListener(this);
		agreementText.setOnClickListener(this);
	}

	/**
	 * 注册方法
	 */
	public void register() {
		if (checkInfo()) {
			String userName = this.userName.getText().toString().trim()
					.toLowerCase();
			String userPwd = this.userPwd.getText().toString().trim();
			String userPersonName = this.userPersonName.getText().toString()
					.trim();
			boolean userSex = RegisterActivity.sex;
			String userIdcard = this.userIdcard.getText().toString().trim();
			String userTel = this.userTel.getText().toString();
			String userProvince = RegisterActivity.province;
			String userCity = RegisterActivity.city;
			UserData user = new UserData(userPersonName, userSex, userIdcard,
					userTel, userProvince, userCity);
			user.setUsername(userName);
			user.setPassword(userPwd);
			user.setEmail(userName);
			user.signUp(this, new SaveListener() {
				@Override
				public void onSuccess() {
					Toast.makeText(RegisterActivity.this,
							getString(R.string.register_sucess),
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(RegisterActivity.this,
							LoginActivity.class);
					startActivity(intent);
					finish();
				}

				@Override
				public void onFailure(int code, String msg) {
					Toast.makeText(RegisterActivity.this,
							getString(R.string.register_fail),
							Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	/**
	 * 验证注册信息
	 */
	@SuppressLint("DefaultLocale")
	public boolean checkInfo() {
		String userName = this.userName.getText().toString().trim();
		String userPwd = this.userPwd.getText().toString().trim();
		String userPwdAgain = this.userPwdAgain.getText().toString().trim();
		String userPersonName = this.userPersonName.getText().toString().trim();
		String userIdcard = this.userIdcard.getText().toString().trim()
				.toUpperCase();
		String userTel = this.userTel.getText().toString().trim();

		if (userName.equals("")) {
			Toast.makeText(this, getString(R.string.username_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (!userName
				.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
			Toast.makeText(this, getString(R.string.username_valid),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (userPwd.equals("")) {
			Toast.makeText(this, getString(R.string.userpwd_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (userPwd.length() < 6) {
			Toast.makeText(this, getString(R.string.userpwd_short),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (userPwdAgain.equals("")) {
			Toast.makeText(this, getString(R.string.userPwdAgain_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (!userPwdAgain.equals(userPwd)) {
			Toast.makeText(this, getString(R.string.userPwd_error),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (userPersonName.equals("")) {
			Toast.makeText(this, getString(R.string.userPersonName_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (userIdcard.equals("")) {
			Toast.makeText(this, getString(R.string.userIdcard_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (userIdcard.length() != 18) {
			Toast.makeText(this, getString(R.string.userIdcard_valid),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (!userIdcard.matches("[0-9]{17}[0-9,X]")) {
			Toast.makeText(this, getString(R.string.userIdcard_valid),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (!judgeIdcard6()) {
			Toast.makeText(this, getString(R.string.userIdcard_valid),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (!judgeIdcard8()) {
			Toast.makeText(this, getString(R.string.userIdcard_valid),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (!judgeIdcard4()) {
			Toast.makeText(this, getString(R.string.userIdcard_valid),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (userTel.length() != 11 && !JudgeTel()) {
			Toast.makeText(this, getString(R.string.userTel_error),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (!JudgeTel()) {
			Toast.makeText(this, getString(R.string.userTel_error),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (!agreementBox.isChecked()) {
			Toast.makeText(this, getString(R.string.agreement_error),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	@SuppressLint("DefaultLocale")
	public boolean judgeIdcard6() {
		String userIdcard = this.userIdcard.getText().toString().trim()
				.toUpperCase();
		String strTwo = userIdcard.substring(0, 2);
		String[] num = new String[] { "11", "12", "13", "14", "15", "21", "22",
				"23", "31", "32", "33", "34", "35", "36", "37", "41", "42",
				"43", "44", "45", "46", "50", "51", "52", "53", "54", "61",
				"62", "63", "64", "65", "71", "81", "82", "91" };
		for (int i = 0; i < num.length; i++)
			if (strTwo.equals(num[i])) {
				return true;
			}
		return false;
	}

	@SuppressLint("DefaultLocale")
	public boolean judgeIdcard8() {
		String userIdcard = this.userIdcard.getText().toString().trim()
				.toUpperCase();
		int year = Integer.parseInt(userIdcard.substring(6, 10));
		int month = Integer.parseInt(userIdcard.substring(10, 12));
		int day = Integer.parseInt(userIdcard.substring(12, 14));
		if (year > 2014)
			return false;
		if (month > 12)
			return false;
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12)
			if (day > 31)
				return false;
		if (month == 4 || month == 6 || month == 9 || month == 11)
			if (day > 30)
				return false;
		if ((year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
				&& (month == 2))
			if (day > 29)
				return false;
		if (!(year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
				&& (month == 2))
			if (day > 28)
				return false;
		return true;
	}

	@SuppressLint("DefaultLocale")
	public boolean judgeIdcard4() {
		String userIdcard = this.userIdcard.getText().toString().trim()
				.toUpperCase();
		char num = userIdcard.charAt(17);
		char[] id = userIdcard.toCharArray();
		int[] idnum = new int[id.length];
		for (int i = 0; i < 17; i++)
			idnum[i] = Integer.parseInt(String.valueOf(id[i]));
		int[] ratio = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
				8, 4, 2 };
		int sum = 0;
		for (int i = 0; i < ratio.length; i++)
			sum += ratio[i] * idnum[i];
		int remainder = sum % 11;
		int[] rem = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		char[] last = new char[] { '1', '0', 'X', '9', '8', '7', '6', '5', '4',
				'3', '2' };
		char lastnum = 'A';
		for (int i = 0; i < rem.length; i++)
			if (rem[i] == remainder)
				lastnum = last[i];
		if (lastnum == num)
			return true;
		else
			return false;
	}

	public boolean JudgeTel() {
		String userTel = this.userTel.getText().toString().trim();
		String[] nk = new String[] { "130", "131", "132", "133", "134", "135",
				"136", "137", "138", "139", "150", "151", "152", "153", "154",
				"155", "156", "157", "158", "159", "170", "180", "181", "182",
				"183", "184", "185", "186", "187", "188", "189" };
		if (userTel.length() == 11) {
			String num = userTel.substring(0, 3);
			int i;
			for (i = 0; i < nk.length; i++)
				if (nk[i].equals(num))
					break;
			if (i < nk.length) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 下拉列表监听器
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
			long arg3) {
		switch (parent.getId()) {
		case R.id.register_userProvince:
			switch (pos) {
			case 0:
				province = parent.getItemAtPosition(pos).toString();
				// city
				ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
						.createFromResource(RegisterActivity.this,
								R.array.city_chongqing_arry,
								android.R.layout.simple_spinner_item);
				userCity.setAdapter(adapter2);
				break;

			case 1:
				province = parent.getItemAtPosition(pos).toString();
				// city
				ArrayAdapter<CharSequence> adapter3 = ArrayAdapter
						.createFromResource(RegisterActivity.this,
								R.array.city_shannxi_arry,
								android.R.layout.simple_spinner_item);
				userCity.setAdapter(adapter3);
				break;
			}
			break;
		case R.id.register_userCity:
			city = parent.getItemAtPosition(pos).toString();
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_registerButton:
			register();
			break;
		case R.id.agreementText:
			Intent intent = new Intent();
			intent.setClass(RegisterActivity.this, AgreementActivity.class);
			startActivity(intent);
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
}