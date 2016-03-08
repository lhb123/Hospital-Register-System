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
	// ԤԼ��ʱ��
	private int mYear;
	private int mMonth;
	private int mDay;
	private static final int DATE_DIALOG = 1;
	private NotificationManager nm;

	private Button keshi;
	private int DoctorId;
	private SlidingLayout slidingLayout;// �໬���ֶ�������ͨ����ָ���������Ĳ˵����ֽ�����ʾ������
	private ImageButton muneButton;// menu��ť�������ťչʾ��಼�֣��ٵ��һ��������಼��
	private LinearLayout contentListView;// ����content�����е�ListView

	public int[] DoctorImages = { R.drawable.doc11, R.drawable.doc22,
			R.drawable.doc33, R.drawable.doc44, R.drawable.doc55 };
	public String[] text = {
			"\n�����\n�л�ҽѧ��ǿƷֻ�ǲ���ίԱ\n�ó�������ú�С�뵶����\n���ң��ǿ�\nְ��-ְ�ƣ�������ҽʦ\n����ʱ�䣺��һ�����塢����",
			"\n����ϼ\nЭ�Ͳ��в����о�����ϯר��\n�л�ҽѧ����ֳҽѧ�ֻ�ίԱ\n���ң����в�������\nְ��-ְ�ƣ�����ҽʦ\n����ʱ�䣺��һ������",
			"\n����Ҷ����\n�����в��в����ٴ��������� \n��Ů����֪ʶѲ�����Ž�ʦ\n���ң����в�������\nְ��-ְ�ƣ�������ҽʦ\n����ʱ�䣺��һ������",
			"\n������С��\n�л�ҽѧ�Ḿ��Ʒֻ�ίԱ\n����Э�Ḿ������רί��ȫ��ίԱ\n���ң������\nְ��-ְ�ƣ�����ҽʦ ���� \n����ʱ�䣺�ܶ�������",
			"\n�����Ա���\n��Ů������רҵίԱ�᲻��֢ѧ��ίԱ\n��βμ�ȫ����רҵѧ������\n���ң����в�������\nְ��-ְ�ƣ�����ҽʦ\n����ʱ�䣺ȫ������" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȥ�����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hospital_detail);

		keshi = (Button) findViewById(R.id.hospital_detail_button1);
		keshi.setOnClickListener(this);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// �໬�˵�
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		muneButton = (ImageButton) findViewById(R.id.hospitalDetail_muenuButton);
		contentListView = (LinearLayout) findViewById(R.id.hospitalDetail_contentList);
		// �������¼�����contentListView��
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
		// ��Layout�����ListView
		ListView list = (ListView) findViewById(R.id.hospital_detail_listView1);

		// ��ɶ�̬���飬�������
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 5; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("DoctorImage", DoctorImages[i]);// ͼ����Դ��ID
			map.put("Itemtext", text[i]);
			listItem.add(map);
		}
		// �����������Item�Ͷ�̬�����Ӧ��Ԫ��
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// ���Դ
				R.layout.listfor_hospital_detail,// ListItem��XMLʵ��
				// ��̬������ImageItem��Ӧ������
				new String[] { "DoctorImage", "Itemtext" },
				// ImageItem��XML�ļ������һ��ImageView,����TextView ID
				new int[] { R.id.listfor_hospital_detail_itemImage,
						R.id.listfor_hospital_detail_textView1 });
		// ��Ӳ�����ʾ
		list.setAdapter(listItemAdapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				setDoctorId(arg2);
				Builder builder = new Builder(
						HospitalDetailActivity.this);
				// 2����builder����һЩ����
				builder.setTitle("��ʾ");
				// builder.setMessage("��ѡ������Ҫ�ķ���");
				builder.setMessage("ȷ��ҪԤԼ��λҽ����");
				builder.setPositiveButton("����ԤԼ",
						new DialogInterface.OnClickListener() {
							@SuppressWarnings("deprecation")
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// ԤԼʱ��

								final Calendar c = Calendar.getInstance(); // ��ȡ����������
								mYear = c.get(Calendar.YEAR);
								mMonth = c.get(Calendar.MONTH);
								mDay = c.get(Calendar.DAY_OF_MONTH);
								showDialog(DATE_DIALOG);
								setDateTime();
								// ****************************
							}

						});
				//
				// builder.setNeutralButton("�ղ�ҽ��",
				// new DialogInterface.OnClickListener() {
				// public void onClick(DialogInterface dialog,
				// int which) {
				//
				// System.out.println("�û�ѡ���˵�" + (DoctorId + 1)
				// + "λҽ��");
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
		// TODO �Զ���ɵķ������
		switch (v.getId()) {
		case R.id.hospital_detail_button1:
			Intent intent1 = new Intent(HospitalDetailActivity.this,
					DepartmentGuideActivity.class);
			startActivity(intent1);
			break;
		}
	}

}