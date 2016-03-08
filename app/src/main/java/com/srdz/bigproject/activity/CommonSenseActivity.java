package com.srdz.bigproject.activity;

import com.srdz.bigproject.R;
import com.srdz.bigproject.communal.PageMenu;
import com.srdz.bigproject.view.SlidingLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class CommonSenseActivity extends Activity {

	private SlidingLayout slidingLayout;// 侧滑布局对象，用于通过手指滑动将左侧的菜单布局进行显示或隐藏
	private ImageButton muneButton;// menu按钮，点击按钮展示左侧布局，再点击一次隐藏左侧布局
	private LinearLayout contentListView;// 放在content布局中的ListView

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除标题
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.common_sense);

		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		muneButton = (ImageButton) findViewById(R.id.common_sense_muenuButton);
		contentListView = (LinearLayout) findViewById(R.id.common_sense_contentList);

		// 将监听滑动事件绑定在contentListView上
		slidingLayout.setScrollEvent(contentListView);
		muneButton.setOnClickListener(new OnClickListener() {
			PageMenu pm = new PageMenu(CommonSenseActivity.this, 3);

			@Override
			public void onClick(View v) {
				if (slidingLayout.isLeftLayoutVisible()) {
					pm.delListener();
					slidingLayout.scrollToRightLayout();
				} else {
					pm.addListener();
					slidingLayout.scrollToLeftLayout();
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.setClass(CommonSenseActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		return true;
	}

}