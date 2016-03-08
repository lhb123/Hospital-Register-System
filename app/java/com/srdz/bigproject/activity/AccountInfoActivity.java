package com.srdz.bigproject.activity;

import cn.bmob.v3.BmobUser;
import com.srdz.bigproject.R;
import com.srdz.bigproject.bean.UserData;
import com.srdz.bigproject.communal.PageMenu;
import com.srdz.bigproject.view.SlidingLayout;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class AccountInfoActivity extends TabActivity implements
		OnTabChangeListener {

	private ImageView headIcon;
	private TextView name;
	private TextView sex;
	private TextView add;
	private TabHost mTabHost;
	// 侧滑菜单
	private SlidingLayout slidingLayout;// 侧滑布局对象，用于通过手指滑动将左侧的菜单布局进行显示或隐藏
	private ImageButton muneButton;// menu按钮，点击按钮展示左侧布局，再点击一次隐藏左侧布局
	private LinearLayout contentListView;// 放在content布局中的ListView

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.account_info);
		headIcon = (ImageView) findViewById(R.id.account_info_icon);
		name = (TextView) findViewById(R.id.account_info_name);
		sex = (TextView) findViewById(R.id.account_info_sex);
		add = (TextView) findViewById(R.id.account_info_add);
		// 获取用户信息
		UserData userInfo = BmobUser.getCurrentUser(AccountInfoActivity.this,
				UserData.class);
		// 更改头像
		// 更改名字
		this.name.setText(userInfo.getUserPersonName());
		String sex = null;
		if (userInfo.isUserSex()) {
			sex = "男";
		} else {
			sex = "女";
		}
		this.sex.setText(sex);
		this.add.setText(userInfo.getUserProvince() + "省"
				+ userInfo.getUserCity() + "市");

		// 侧滑菜单
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		muneButton = (ImageButton) findViewById(R.id.account_info_muenuButton);
		contentListView = (LinearLayout) findViewById(R.id.account_info_contentList);
		// 将监听滑动事件绑定在contentListView上
		slidingLayout.setScrollEvent(contentListView);
		muneButton.setOnClickListener(new OnClickListener() {
			PageMenu pm = new PageMenu(AccountInfoActivity.this, 0);

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

		// 切换卡
		mTabHost = getTabHost();
		mTabHost.addTab(mTabHost.newTabSpec("tab1")
				.setIndicator(getString(R.string.account_info_xinxi))
				.setContent(R.id.account_info_tab1));
		mTabHost.addTab(mTabHost.newTabSpec("tab2")
				.setIndicator(getString(R.string.account_info_bingli))
				.setContent(R.id.account_info_tab2));
		mTabHost.addTab(mTabHost.newTabSpec("tab3")
				.setIndicator(getString(R.string.account_info_shoucang))
				.setContent(R.id.account_info_tab3));
		mTabHost.setCurrentTab(0);
		// mTabHost.setOnTabChangedListener(this);
	}

	@Override
	public void onTabChanged(String tabId) {

	}
}