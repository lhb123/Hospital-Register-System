package com.srdz.bigproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import com.srdz.bigproject.R;
import com.srdz.bigproject.bean.UserData;

public class LoginActivity extends Activity implements OnClickListener {
	final static String TAG = "Login";

	private EditText userName;
	private EditText userPwd;
	private ImageButton registerButton;
	private ImageButton loginButton;
	private CheckBox remPwd;
	private CheckBox autoLogin;
	private TextView forgetPwdText;
	private SharedPreferences sp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);

		userName = (EditText) findViewById(R.id.userName);
		userPwd = (EditText) findViewById(R.id.userPwd);
		registerButton = (ImageButton) findViewById(R.id.registerButton);
		loginButton = (ImageButton) findViewById(R.id.loginButton);
		remPwd = (CheckBox) findViewById(R.id.remPwd);
		autoLogin = (CheckBox) findViewById(R.id.login_autoLogin);
		forgetPwdText = (TextView) findViewById(R.id.forgetPwdText);
		sp = this.getSharedPreferences("userInfo", CONTEXT_IGNORE_SECURITY);
		registerButton.setOnClickListener(this);
		loginButton.setOnClickListener(this);
		forgetPwdText.setOnClickListener(this);

		// 判断记住密码多选框的状态
		if (sp.getBoolean("ISCHECK", false)) {
			// 设置默认是记录密码状态
			remPwd.setChecked(true);
			userName.setText(sp.getString("USER_NAME", ""));
			userPwd.setText(sp.getString("PASSWORD", ""));

			// 判断自动登陆多选框状态
			if (sp.getBoolean("AUTO_ISCHECK", false)) {
				// 设置默认是自动登录状态
				autoLogin.setChecked(true);
				UserData userInfo = BmobUser.getCurrentUser(LoginActivity.this,
						UserData.class);
				if (userInfo != null) {
					Toast.makeText(LoginActivity.this, R.string.login_sucess,
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(LoginActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
				}
			}
		}

		// 监听记住密码多选框按钮事件
		remPwd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (remPwd.isChecked()) {
					// 记住密码已选中
					sp.edit().putBoolean("ISCHECK", true).commit();
				} else {
					// 记住密码没有选中
					sp.edit().putBoolean("ISCHECK", false).commit();
				}
			}
		});

		// 监听自动登录多选框事件
		autoLogin.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (autoLogin.isChecked()) {
					System.out.println("自动登录已选中");
					remPwd.setChecked(true);
					sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
				} else {
					System.out.println("自动登录没有选中");
					sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
				}
			}
		});
	}

	public void login() {
		if (isUserNameAndPwdValid()) {
			final String userName = this.userName.getText().toString().trim();
			final String userPwd = this.userPwd.getText().toString().trim();
			BmobUser user = new BmobUser();
			user.setUsername(userName);
			user.setPassword(userPwd);
			user.login(LoginActivity.this, new SaveListener() {
				@Override
				public void onSuccess() {
					if (remPwd.isChecked()) {
						// 记住用户名、密码
						Editor editor = sp.edit();
						editor.putString("USER_NAME", userName);
						editor.putString("PASSWORD", userPwd);
						editor.commit();
					}
					UserData userInfo = BmobUser.getCurrentUser(
							LoginActivity.this, UserData.class);
					if (userInfo.getEmailVerified()) {
						if (autoLogin.isChecked()) {
							if (userInfo != null) {
								Toast.makeText(LoginActivity.this,
										R.string.login_sucess,
										Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(LoginActivity.this,
										MainActivity.class);
								startActivity(intent);
								finish();
							}
						} else {
							Toast.makeText(LoginActivity.this,
									R.string.login_sucess, Toast.LENGTH_SHORT)
									.show();
							Intent intent = new Intent(LoginActivity.this,
									MainActivity.class);
							startActivity(intent);
							finish();
						}

					} else {
						Toast.makeText(LoginActivity.this,
								R.string.login_fails, Toast.LENGTH_SHORT)
								.show();
					}
				}

				@Override
				public void onFailure(int code, String msg) {
					Toast.makeText(LoginActivity.this, R.string.login_fail,
							Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	public boolean isUserNameAndPwdValid() {
		if (userName.getText().toString().trim().equals("")) {
			Toast.makeText(this, getString(R.string.username_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (userPwd.getText().toString().trim().equals("")) {
			Toast.makeText(this, getString(R.string.userpwd_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.registerButton:
			Intent intent1 = new Intent();
			intent1.setClass(LoginActivity.this, RegisterActivity.class);
			startActivity(intent1);
			break;
		case R.id.loginButton:
			login();
			break;
		case R.id.forgetPwdText:
			Intent intent2 = new Intent();
			intent2.setClass(LoginActivity.this, ForgetActivity.class);
			startActivity(intent2);
		}
	}
}