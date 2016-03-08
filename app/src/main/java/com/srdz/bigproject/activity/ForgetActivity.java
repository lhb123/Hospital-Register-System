package com.srdz.bigproject.activity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.ResetPasswordListener;
import com.srdz.bigproject.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ForgetActivity extends Activity implements OnClickListener {
	private EditText userName;
	private ImageButton findPwdButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.forget);
		findPwdButton = (ImageButton) findViewById(R.id.findPwdButton);
		userName = (EditText) findViewById(R.id.userName);
		findPwdButton.setOnClickListener(this);
	}

	@SuppressLint("DefaultLocale")
	@Override
	public void onClick(View v) {
		String email = this.userName.getText().toString().trim().toLowerCase();
		BmobUser.resetPassword(this, email, new ResetPasswordListener() {
			@Override
			public void onSuccess() {
				Builder builder = new Builder(ForgetActivity.this);
				builder.setTitle("提示");
				builder.setMessage("请到电子邮箱修改密码！");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent();
								intent.setClass(ForgetActivity.this,
										LoginActivity.class);
								startActivity(intent);
								finish();
							}
						});
				builder.create().show();
			}

			@Override
			public void onFailure(int code, String e) {
				Toast.makeText(ForgetActivity.this, "修改密码失败，请填写有效的电子邮箱！",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}