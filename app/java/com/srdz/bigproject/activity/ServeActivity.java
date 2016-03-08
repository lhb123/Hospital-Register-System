package com.srdz.bigproject.activity;

import com.srdz.bigproject.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServeActivity extends Activity implements OnClickListener {
	private Button serveButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.serve);
		serveButton = (Button) findViewById(R.id.serve_button1);
		serveButton.setOnClickListener(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.setClass(ServeActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		Builder builder = new Builder(ServeActivity.this);
		builder.setTitle("��ʾ");
		builder.setMessage("��ѯ���ύ");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.create().show();
	}
}