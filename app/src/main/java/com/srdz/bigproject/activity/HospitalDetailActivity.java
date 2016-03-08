package com.srdz.bigproject.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import com.srdz.bigproject.R;
import com.srdz.bigproject.communal.NotificationDoctorThread;
import com.srdz.bigproject.communal.PageMenu;
import com.srdz.bigproject.view.SlidingLayout;

public class HospitalDetailActivity extends Activity implements OnClickListener {
	// 预约的时间
	private int mYear;
	private int mMonth;
	private int mDay;
	private static final int DATE_DIALOG = 1;
	private NotificationManager nm;

	private Button keshi;
	private int DoctorId;
	private SlidingLayout slidingLayout;// 侧滑布局对象，用于通过手指滑动将左侧的菜单布局进行显示或隐藏
	private ImageButton muneButton;// menu按钮，点击按钮展示左侧布局，再点击一次隐藏左侧布局
	private LinearLayout contentListView;// 放在content布局中的ListView

	public int[] DoctorImages = { R.drawable.doc11, R.drawable.doc22,
			R.drawable.doc33, R.drawable.doc44, R.drawable.doc55 };
	public String[] text = {
			"\n姓名：李凯\n中华医学会骨科分会骨病组委员\n擅长针灸推拿和小针刀治疗\n科室：骨科\n职务-职称：副主任医师\n坐诊时间：周一至周五、周日",
			"\n姓名：季霞\n协和不孕不育研究所首席专家\n中华医学会生殖医学分会委员\n科室：不孕不育中心\n职务-职称：主任医师\n坐诊时间：周一至周六",
			"\n姓名：叶天琼\n重庆市不孕不育临床中心主任 \n妇女健康知识巡回宣讲团讲师\n科室：不孕不育中心\n职务-职称：副主任医师\n坐诊时间：周一至周六",
			"\n姓名：徐小蓉\n中华医学会妇产科分会委员\n抗癌协会妇科肿瘤专委会全国委员\n科室：妇产科\n职务-职称：主任医师 教授 \n坐诊时间：周二至周六",
			"\n姓名：赵本书\n妇女病康复专业委员会不孕症学组委员\n多次参加全国妇产科专业学术交流\n科室：不孕不育中心\n职务-职称：主治医师\n坐诊时间：全天坐诊" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除标题
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hospital_detail);

		keshi = (Button) findViewById(R.id.hospital_detail_button1);
		keshi.setOnClickListener(this);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// 侧滑菜单
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		muneButton = (ImageButton) findViewById(R.id.hospitalDetail_muenuButton);
		contentListView = (LinearLayout) findViewById(R.id.hospitalDetail_contentList);
		// 将监听滑动事件绑定在contentListView上
		slidingLayout.setScrollEvent(contentListView);
		muneButton.setOnClickListener(new OnClickListener() {
			PageMenu pm = new PageMenu(HospitalDetailActivity.this, 0);

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
		// 绑定Layout里面的ListView
		ListView list = (ListView) findViewById(R.id.hospital_detail_listView1);

		// 生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 5; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("DoctorImage", DoctorImages[i]);// 图像资源的ID
			map.put("Itemtext", text[i]);
			listItem.add(map);
		}
		// 生成适配器的Item和动态数组对应的元素
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// 数据源
				R.layout.listfor_hospital_detail,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "DoctorImage", "Itemtext" },
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.listfor_hospital_detail_itemImage,
						R.id.listfor_hospital_detail_textView1 });
		// 添加并且显示
		list.setAdapter(listItemAdapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				setDoctorId(arg2);
				Builder builder = new Builder(
						HospitalDetailActivity.this);
				// 2所有builder设置一些参数
				builder.setTitle("提示");
				// builder.setMessage("请选择您需要的服务：");
				builder.setMessage("确定要预约这位医生吗？");
				builder.setPositiveButton("快速预约",
						new DialogInterface.OnClickListener() {
							@SuppressWarnings("deprecation")
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// 预约时间

								final Calendar c = Calendar.getInstance(); // 获取日历中内容
								mYear = c.get(Calendar.YEAR);
								mMonth = c.get(Calendar.MONTH);
								mDay = c.get(Calendar.DAY_OF_MONTH);
								showDialog(DATE_DIALOG);
								setDateTime();
								// ****************************
							}

						});
				//
				// builder.setNeutralButton("收藏医生",
				// new DialogInterface.OnClickListener() {
				// public void onClick(DialogInterface dialog,
				// int which) {
				//
				// System.out.println("用户选择了第" + (DoctorId + 1)
				// + "位医生！");
				//
				// }
				// });
				builder.create().show();
			}
		});
	}

	private void setDateTime() {
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		// updateDateDisplay();
	}

	private void updateDateDisplay() {
		NotificationDoctorThread ndt = new NotificationDoctorThread(
				HospitalDetailActivity.this, nm);
		ndt.start();
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDateDisplay();
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case DATE_DIALOG:
			((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
			break;
		}
	}

	public int getDoctorId() {
		return DoctorId;
	}

	public void setDoctorId(int DoctorId) {
		this.DoctorId = DoctorId;
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.hospital_detail_button1:
			Intent intent1 = new Intent(HospitalDetailActivity.this,
					DepartmentGuideActivity.class);
			startActivity(intent1);
			break;
		}
	}

}