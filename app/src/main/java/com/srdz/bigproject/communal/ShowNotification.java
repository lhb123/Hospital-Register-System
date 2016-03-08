package com.srdz.bigproject.communal;

import com.srdz.bigproject.activity.MainActivity;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

// 调用时先在onCreate()方法中初始化
// nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

public class ShowNotification {

	private Activity activity;
	private NotificationManager nm;
	private int notificationId;// 设置一个唯一的ID，随便设置

	public ShowNotification(Activity activity, NotificationManager nm,
			int notificationId) {
		super();
		this.activity = activity;
		this.nm = nm;
		this.notificationId = notificationId;
	}

	@SuppressWarnings("deprecation")
	public void showNotification(int icon, String tickertext, String title,
			String content) {
		// 设置一个唯一的ID，随便设置
		// Notification管理器
		Notification notification = new Notification(icon, tickertext, System.currentTimeMillis());
		// 后面的参数分别是显示在顶部通知栏的小图标，小图标旁的文字（短暂显示，自动消失）系统当前时间
		//notification.defaults = Notification.DEFAULT_ALL;
		// 这是设置通知是否同时播放声音或振动，声音为Notification.DEFAULT_SOUND
		// 振动为Notification.DEFAULT_VIBRATE;
		// Light为Notification.DEFAULT_LIGHTS，在我的Milestone上好像没什么反应
		// 全部为Notification.DEFAULT_ALL
		// 如果是振动或者全部，必须在AndroidManifest.xml加入振动权限
		PendingIntent pt = PendingIntent.getActivity(activity, 0, new Intent(
				activity, MainActivity.class), 0);
		// 点击通知后的动作，这里是转回main 这个Acticity
		//notification.setLatestEventInfo(activity, title, content, pt);
		//nm.notify(notificationId, notification);
	}
}