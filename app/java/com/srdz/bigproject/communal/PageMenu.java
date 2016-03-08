package com.srdz.bigproject.communal;

import cn.bmob.v3.BmobUser;
import com.srdz.bigproject.R;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.srdz.bigproject.activity.AboutUsActivity;
import com.srdz.bigproject.activity.CommonSenseActivity;
import com.srdz.bigproject.activity.MainActivity;
import com.srdz.bigproject.activity.RegistrationActivity;
import com.srdz.bigproject.activity.ServeActivity;
import com.srdz.bigproject.activity.SettingActivity;
import com.srdz.bigproject.bean.UserData;

public class PageMenu implements OnClickListener {

	private Activity activity;
	private int activityId;
	private View view1;
	private View view2;
	private View view3;
	private View view4;
	private View view5;
	private View view6;
	private ImageView iv;
	private TextView tv;

	public PageMenu(Activity activity, int activityId) {
		super();
		this.setActivity(activity);
		this.setActivityId(activityId);
		// 找到各个组件
		view1 = activity.findViewById(R.id.menuHome);
		view2 = activity.findViewById(R.id.menuRegistration);
		view3 = activity.findViewById(R.id.menuCommonSense);
		view4 = activity.findViewById(R.id.menuService);
		view5 = activity.findViewById(R.id.menuSetting);
		view6 = activity.findViewById(R.id.menuAbout);
		iv = (ImageView) activity.findViewById(R.id.userImageShow);
		tv = (TextView) activity.findViewById(R.id.userPersonNameShow);
		// 获取用户信息
		UserData userInfo = BmobUser.getCurrentUser(activity, UserData.class);
		// 更改头像
		// 更改名字
		tv.setText(userInfo.getUserPersonName());
	}

	// 绑定监听器
	public void addListener() {
		getView1().setOnClickListener(this);
		getView2().setOnClickListener(this);
		getView3().setOnClickListener(this);
		getView4().setOnClickListener(this);
		getView5().setOnClickListener(this);
		getView6().setOnClickListener(this);
	}

	// 取消绑定监听器
	public void delListener() {
		getView1().setOnClickListener(null);
		getView2().setOnClickListener(null);
		getView3().setOnClickListener(null);
		getView4().setOnClickListener(null);
		getView5().setOnClickListener(null);
		getView6().setOnClickListener(null);
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public View getView1() {
		return view1;
	}

	public View getView2() {
		return view2;
	}

	public View getView3() {
		return view3;
	}

	public View getView4() {
		return view4;
	}

	public View getView5() {
		return view5;
	}

	public View getView6() {
		return view6;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menuHome:
			if (activityId != 1) {
				Intent intent1 = new Intent(getActivity(), MainActivity.class);
				getActivity().startActivity(intent1);
			}
			break;
		case R.id.menuRegistration:
			if (activityId != 2) {
				Intent intent2 = new Intent(getActivity(),
						RegistrationActivity.class);
				getActivity().startActivity(intent2);
			}
			break;
		case R.id.menuCommonSense:
			if (activityId != 3) {
				Intent intent3 = new Intent(getActivity(),
						CommonSenseActivity.class);
				getActivity().startActivity(intent3);
			}
			break;
		case R.id.menuService:
			if (activityId != 4) {
				Intent intent4 = new Intent(getActivity(), ServeActivity.class);
				getActivity().startActivity(intent4);
			}
			break;
		case R.id.menuSetting:
			if (activityId != 5) {
				Intent intent5 = new Intent(getActivity(),
						SettingActivity.class);
				getActivity().startActivity(intent5);
			}
			break;
		case R.id.menuAbout:
			if (activityId != 6) {
				Intent intent6 = new Intent(getActivity(),
						AboutUsActivity.class);
				getActivity().startActivity(intent6);
			}
			break;
		}
	}
}