package com.srdz.bigproject.communal;

import com.srdz.bigproject.R;
import android.app.Activity;
import android.app.NotificationManager;

public class NotificationDoctorThread extends Thread {

	private Activity activity;
	private NotificationManager nm;

	public NotificationDoctorThread(Activity activity, NotificationManager nm) {
		this.activity = activity;
		this.nm = nm;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int random() {
		int x = (int) (Math.random() * 100);
		return x;
	}

	@Override
	public void run() {
		int rd = (int) (Math.random() * 100);
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ShowNotification sn = new ShowNotification(getActivity(), nm, 6435541);
		sn.showNotification(R.drawable.ic_launcher, "恭喜你，预约成功", "恭喜你，预约成功",
				"预约成功了，编号：" + rd + "。请尽快去医院就医！");
	}
}