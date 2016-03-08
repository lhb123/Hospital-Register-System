package com.srdz.bigproject.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.bmob.v3.BmobUser;
import com.srdz.bigproject.R;
import com.srdz.bigproject.communal.PageMenu;
import com.srdz.bigproject.communal.SlideView;
import com.srdz.bigproject.view.SlidingLayout;

public class MainActivity extends Activity implements OnClickListener {

	private ImageButton jiankangkepu;
	private ImageButton wodexinxi;
	private ImageButton code;
	private ImageButton changeUserButton;
	// �໬�˵�
	private SlidingLayout slidingLayout;// �໬���ֶ�������ͨ����ָ���������Ĳ˵����ֽ�����ʾ������
	private ImageButton muneButton;// menu��ť�������ťչʾ��಼�֣��ٵ��һ��������಼��
	private LinearLayout contentListView;// ����content�����е�ListView

	// ����ͼƬ
	private ImageView[] imageViews = null;
	private ImageView imageView = null;
	private ViewPager advPager = null;
	private AtomicInteger what = new AtomicInteger(0);
	private boolean isContinue = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		jiankangkepu = (ImageButton) findViewById(R.id.main_jiankangkepuButton);
		wodexinxi = (ImageButton) findViewById(R.id.main_wodexinxiButton);
		code = (ImageButton) findViewById(R.id.main_codeButton);
		changeUserButton = (ImageButton) findViewById(R.id.main_changeUserButton);

		jiankangkepu.setOnClickListener(this);
		wodexinxi.setOnClickListener(this);
		code.setOnClickListener(this);
		changeUserButton.setOnClickListener(this);
		initViewPager();// ����ͼƬ��ʼ��

		// �����ҺŰ�ť
		final SlideView slideView = (SlideView) findViewById(R.id.slider);
		slideView.setSlideListener(new SlideView.SlideListener() {
			@Override
			public void onDone() {
				Intent intent1 = new Intent(MainActivity.this,
						RegistrationActivity.class);
				startActivity(intent1);
				slideView.reset();
			}
		});

		// �໬�˵�
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		muneButton = (ImageButton) findViewById(R.id.main_muenuButton);
		contentListView = (LinearLayout) findViewById(R.id.main_contentList);
		// �������¼�����contentListView��
		slidingLayout.setScrollEvent(contentListView);
		muneButton.setOnClickListener(new OnClickListener() {
			PageMenu pm = new PageMenu(MainActivity.this, 1);

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

	// �˳�ʱ�����Ի���
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// && event.getRepeatCount() ==0
			Builder builder = new Builder(MainActivity.this);
			builder.setMessage("ȷ��Ҫ�˳���?");
			builder.setTitle("��ʾ");
			builder.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							android.os.Process.killProcess(android.os.Process
									.myPid());
						}
					});
			builder.setNegativeButton("ȡ��",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			builder.create().show();
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_jiankangkepuButton:
			Intent intent2 = new Intent(MainActivity.this,
					CommonSenseActivity.class);
			startActivity(intent2);
			break;
		case R.id.main_wodexinxiButton:
			Intent intent3 = new Intent(MainActivity.this,
					AccountInfoActivity.class);
			startActivity(intent3);
			break;
		case R.id.main_codeButton:
			Intent intent4 = new Intent(MainActivity.this,
					CaptureActivity.class);
			startActivityForResult(intent4, 0);
			break;
		case R.id.main_changeUserButton:
			Builder builder = new Builder(MainActivity.this);
			builder.setTitle("��ʾ");
			builder.setMessage("�Ƿ�ȷ���л��û���");
			builder.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							BmobUser.logOut(MainActivity.this); // �����û�����
							Intent intent5 = new Intent(MainActivity.this,
									LoginActivity.class);
							startActivity(intent5);
							finish();
						}
					});
			builder.setNeutralButton("ȡ��",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			builder.create().show();
			break;
		}
	}

	// ����ͼƬһϵ�з���
	@SuppressLint("ClickableViewAccessibility")
	private void initViewPager() {
		advPager = (ViewPager) findViewById(R.id.adv_pager);
		List<View> advPics = new ArrayList<View>();
		// 1
		ImageView img1 = new ImageView(this);
		img1.setBackgroundResource(R.drawable.main_datu1);
		advPics.add(img1);
		// 2
		ImageView img2 = new ImageView(this);
		img2.setBackgroundResource(R.drawable.main_datu2);
		advPics.add(img2);
		// 3
		ImageView img3 = new ImageView(this);
		img3.setBackgroundResource(R.drawable.main_datu3);
		advPics.add(img3);
		// 4
		// ImageView img4 = new ImageView(this);
		// img4.setBackgroundResource(R.drawable.main_datu1);
		// advPics.add(img4);

		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
		imageViews = new ImageView[advPics.size()];

		for (int i = 0; i < advPics.size(); i++) {
			imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(20, 20));
			imageView.setPadding(20, 0, 20, 0);
			imageViews[i] = imageView;
			if (i == 0) {
				imageViews[i]
						.setBackgroundResource(R.drawable.banner_dian_focus);
			} else {
				imageViews[i]
						.setBackgroundResource(R.drawable.banner_dian_blur);
			}
			group.addView(imageViews[i]);
		}

		advPager.setAdapter(new AdvAdapter(advPics));
		advPager.setOnPageChangeListener(new GuidePageChangeListener());
		advPager.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					isContinue = false;
					break;
				case MotionEvent.ACTION_UP:
					isContinue = true;
					break;
				default:
					isContinue = true;
					break;
				}
				return false;
			}
		});

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (isContinue) {
						viewHandler.sendEmptyMessage(what.get());
						whatOption();
					}
				}
			}

		}).start();
	}

	private void whatOption() {
		what.incrementAndGet();
		if (what.get() > imageViews.length - 1) {
			what.getAndAdd(-4);
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}
	}

	@SuppressLint("HandlerLeak")
	private final Handler viewHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			advPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}
	};

	// ��ȡ��ά��ɨ����
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			Builder builder = new Builder(MainActivity.this);
			builder.setTitle("ɨ��ɹ�");
			builder.setMessage(scanResult);
			builder.setPositiveButton("֧�����ɷ�",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			builder.setNegativeButton("ҽ�����ɷ�",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			builder.create().show();
		}
	}

	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0]
						.setBackgroundResource(R.drawable.banner_dian_focus);
				if (arg0 != i) {
					imageViews[i]
							.setBackgroundResource(R.drawable.banner_dian_blur);
				}
			}
		}
	}

	private final class AdvAdapter extends PagerAdapter {
		private List<View> views = null;

		public AdvAdapter(List<View> views) {
			this.views = views;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(views.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {

		}

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(views.get(arg1), 0);
			return views.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}
	}

}