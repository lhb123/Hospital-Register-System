package com.srdz.bigproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import com.srdz.bigproject.R;

public class SplashScreenActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		final View view = View.inflate(this, R.layout.splash_screen, null);
		setContentView(view);
		// 初始化 Bmob SDK
		Bmob.initialize(this, "f20be0efcb9e800d5d73ca8667225f6d");
		// 使用推送服务时的初始化操作
		BmobInstallation.getCurrentInstallation(this).save();
		// 启动推送服务
		// BmobPush.startWork(this, "f20be0efcb9e800d5d73ca8667225f6d");

		// 渐变展示启动屏,这里通过动画来设置了开启应用程序的界面
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		// 给动画添加监听方法
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});
	}

	private void redirectTo() {
		Intent intent = new Intent(SplashScreenActivity.this,
				LoginActivity.class);
		startActivity(intent);
		finish();
	}
}