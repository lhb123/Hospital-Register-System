package com.srdz.bigproject.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import com.srdz.bigproject.R;
import com.srdz.bigproject.communal.NotificationRegistrationThread;
import com.srdz.bigproject.communal.PageMenu;
import com.srdz.bigproject.view.SlidingLayout;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class DepartmentGuideActivity extends Activity {

	private SlidingLayout slidingLayout;// �໬���ֶ�������ͨ����ָ���������Ĳ˵����ֽ�����ʾ������
	private ImageButton muneButton;// menu��ť�������ťչʾ��಼�֣��ٵ��һ��������಼��
	private LinearLayout contentListView;// ����content�����е�ListView
	private ListView list1;
	private ListView list2;
	private Button guideButtonsure;
	private int department;
	// ԤԼ��ʱ��
	private int mYear;
	private int mMonth;
	private int mDay;
	private static final int DATE_DIALOG = 1;
	private static final int SHOW_DATE = 0;
	// ****************************************

	private NotificationManager nm;
	public String[] buttontext = { "�����", "�ǿ�", "�ڿ�", "���", "���", "θ����", "�����",
			"�����", "��ǻ��", "������", "�����", "�����", "��Ǻ��" };
	public String[] textview = {
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "����ҽѧ�ƣ��ң�����ҽѧ������ҽԺ����֢������С�������ࡢ���Ⱥ͹����������صĿ��ң������м��ﲡ����Ժ���Ƶıؾ�֮·���ۺ�ҽԺ��������ȫ�ơ��ڡ��⡢��������١����ȡ���к��ר�����ҡ�"
					+ "����ҽѧ���ѷ�չΪ�������������֢�໤��λһ��Ĵ��͵ļ���ҽ�Ƽ������ĺͼ���ҽѧ��ѧ�о����ģ����ԶԼ���Σ���ز���ʵ��һվʽ����ת����ҽ�Ʒ��񣬱���Ϊ�ִ�ҽѧ�ı�־������������ػ���",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "����Э��ҽԺ�ǿƣ������ϵ�������רҵ�����ڹǼ����о����ٴ����εĻ�����ר�ŵĹǼ����о��ң���ݱ��ػ��������ص㣬������Ի����Ʒ�ʽ��"
					+ "��Ժ�ǿƻ㼯�˶�λ������¹ǿ��ٴ�������ר�Ҽ�ҽʦ��ɣ�ͬʱ���Ƚ�������豸��Ӧ������ҽ��ϵ������ֶΣ��ڹɹ�ͷ��������׵����ͻ������������׵���������ס���ʪ�����ʪ�Թؽ��׵ȹǿƼ�����������ȡ����׿Խ��Ч��",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "����Э��ҽԺ�ڿ����ѷ�չΪһ�������С��ٴ��ͽ�ѧΪһ����ص���ң�ƾ��ҽԺǿ��Ĳ�����ϡ�Ӱ���顢�ٴ��������ƣ����ϳ����ִ��ڿ�ѧ����չ����ߡ�"
					+ "������һ�����μ������μ�ר������Ӧ������Լ�ʮ����ڿ��ٴ��������飬�γ���һ�׽�Ϊ������ڿ�������ϵ������ϵͳ������ϵͳ����Ѫ��ϵͳ���ڿƳ����෢�����ü����αꡢ�����α���ԭ���γ�����ɫ������ϵ��",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "����Э��ҽԺ�����������������ר����������������Զ�Ƴ����෢������Ӫ���ϰ��Լ��������������Ⱦ�Լ�������ϵͳ����������ϵͳ��������ϵͳ�����Ⱦ��кܸߵ�����ˮƽ��"
					+ "�Զ�����Ŵ���л�Լ����������Լ�������Ѫ�ܼ�������Ѫϵͳ�������ڷ��ڼ������ټ���нϸߵ�����ˮƽ�������Ƕ�С��ȡ�Ӥ����㡢�������������ϵͳ�����Ƚ�����������о���",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "��Ժ��ƺ������ۺ�������г����෢�����ٴ����Ρ���һ���ٴ�����ḻ��������տ�ĸ߼�ҽʦ���Ｐ������"
					+ "�������μ�״�١��Ρ�����Ƣ���ȡ�θ���ĸ��������Լ������˼�Ѫ����Ʋ��ˣ�����ɰ�����ʮ��ָ���г������������ڵĸ��������������������ѹ�������Ѫ�����Ի����������ס���Ⱦ���ݿˡ�ʧѪ���ݿ��г�����ٴ����顣���ⱾԺ΢������Ҳ�õ����㷢չ��",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "����Э��ҽԺθ���������ٴ���ʮ���꾭�������ҽʦ���������Ρ�����Э��ҽԺθ���Ʋ��ù���Ƚ�ҽ���豸������θ����������"
					+ "���������ϵ���չ����Ƚ�Ĳ���������--���������,΢��������״θ�ס�θ����Ϣ�⡢ʳ����խ�ȼ����������������������ٴ���֤�������ɹ��ʴ�95%���ϣ���Ч�����׸�����",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "���������Ҵ���������糬���¼��������豸���߶˲�ɫ�����ճ���ʳ�����ǣ��ɿ�չ��������ࡢǳ�?С���١���Ӱ��������Լ������µ�΢���������ƣ�"
					+ "�����˳���ҽѧ�������Ҫ���ݣ����ڸ�����ɫ�����ճ��������ɫ�����ճ���С���ٲʳ����ȷ���������Ե���λ���ƺ�Ӱ�졣",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "����Э��ҽԺ�������Ϊ�ص�ѧ�ƣ����踾�ơ���ƺͼƻ�����ƣ�������40������С��ٴ�����ѧ���������ҽ�ƴ�ѧ������С�����Σ��Ƽ��˽���˳�������ء�����ʮ��λ���Ρ������μ������ר�ң�"
					+ "�����Ϻ��и�Ů������רҵίԱ�Ṳͬ���������ϸ�Ů����������ָ����ء���ΪŮ�������ṩ��������ҽ�Ʒ���",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "��ǻ��ӵ�ж�̨�Ƚ�ҽ���豸��������������Ƚ�����ֻ���������ϲ�ȫ��X��������ͷ�沿��Ƕ�����ȫ����Ƭ�����㣻�߼���������е������豸�ȡ�"
					+ "Ŀǰ������ʩ�������ƣ����������ۺ񣬿�չ��ҵ����Ŀȫ�档��ר�Ƽ���������ˮƽ�ڱ���λ��ǰ�У����������ֶ����������ȣ������������нϸ�������",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "��������Ҫ���±��ʰ��������������ٰ����ΰ���ʳ�ܰ����ܰ����������ȶ��������ķ������ơ���ѧ���ơ��۽��������ƺ��������ơ��������豸��ȫ�����������ۺ��ڶ����������ۺ����ơ��淶�����Ʒ����Ч��������Ч����������ˮƽ�� ",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "�����ڸ�����ѧ���������ר����Ԫ�����ڣ���ʿ��ʦ����ԭȫ��������������������������ڵ����ܹ���Ժ�������ר�Ҵ����£��齨��һ֧��������������νӡ���۶������Ρ������μ�ҽʦ�ĹǸɾ�Ӣ���飬���ҳ�Ա��ù�Ҽ���ʡ�������ཱ���ϰ������������塢�����ڼ�����Ҽ��Ƽ����ؿ��⣬����á�2010ȫ��ʵ��ҽѧ�½�չ������ɹ�ѧ�������ᡱ����ɹ�һ�Ƚ��� ",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "����Ŀǰӵ�����������μ���ʦ�������μ���ʦ��ɵ��Ӵ�ר�Ҷ��飬�����ļ������ס���ʵ�����ۻ�ͷḻ�Ĳ������飬ƾ���Ͻ��Ĺ��?�淶�Ĳ��������Ƶķ�����ϵ������ÿһ�������ܵõ�����ĵļ��鱨�档 ",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "����Э��ҽԺ��Ǻ������ҽ30���꣬���ŷḻ�ٴ����������տ�������Ǻ?������ר�ҡ�����ҽʦ��Ӧ���������"
					+ "�ڸ��ֱ�񼡢��ǻ�����������Ա��ס����ס��������ס���֢������ۡ������ϡ��ж��׵ȼ��������������������ơ�", };

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.department_guide);
		guideButtonsure = (Button) findViewById(R.id.guide_buttonsure);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// �໬�˵�
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		muneButton = (ImageButton) findViewById(R.id.departement_guide_muenuButton);
		contentListView = (LinearLayout) findViewById(R.id.departement_guide_contentList);
		// �������¼�����contentListView��
		slidingLayout.setScrollEvent(contentListView);
		muneButton.setOnClickListener(new OnClickListener() {
			PageMenu pm = new PageMenu(DepartmentGuideActivity.this, 0);

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

		// ԤԼʱ��

		final Calendar c = Calendar.getInstance(); // ��ȡ����������
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		initializeView();
		setDateTime();
		// ****************************

		list1 = (ListView) findViewById(R.id.department_guide_button);
		list2 = (ListView) findViewById(R.id.department_guide_text);
		// left---Button��ɶ�̬���飬�������
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 13; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("guideLeft", buttontext[i]);// ͼ����Դ��ID
			// map.put("Itemtext", text[i]);
			listItem.add(map);
		}
		// �����������Item�Ͷ�̬�����Ӧ��Ԫ��
		SimpleAdapter listLeftAdapter = new SimpleAdapter(this, listItem,// ���Դ
				R.layout.listfor_depar_guide_left,// ListItem��XMLʵ��
				// ��̬������ImageItem��Ӧ������
				new String[] { "guideLeft" },
				// ImageItem��XML�ļ������һ��ImageView,����TextView ID
				new int[] { R.id.guide_textviews1 });
		// ��Ӳ�����ʾ
		list1.setAdapter(listLeftAdapter);

		// ���list1���
		list1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				department = arg2;
				// right----TextView�������
				ArrayList<HashMap<String, Object>> listText = new ArrayList<HashMap<String, Object>>();

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("TextView", textview[arg2]);// ͼ����Դ��ID
				// map.put("Itemtext", text[i]);
				listText.add(map);

				// �����������Item�Ͷ�̬�����Ӧ��Ԫ��
				SimpleAdapter listTextViewAdapter = new SimpleAdapter(
						DepartmentGuideActivity.this, listText,// ���Դ
						R.layout.listfor_guide_text,// ListItem��XMLʵ��
						// ��̬������ImageItem��Ӧ������
						new String[] { "TextView" },
						// ImageItem��XML�ļ������һ��ImageView,����TextView ID
						new int[] { R.id.guide_textViews });
				// ��Ӳ�����ʾ
				list2.setAdapter(listTextViewAdapter);
				guideButtonsure.setVisibility(0);
				// guideButtonsure.setOnClickListener(DepartmentGuideActivity.this);
			}
		});
	}

	// ԤԼʱ��
	private void initializeView() {
		guideButtonsure = (Button) findViewById(R.id.guide_buttonsure);
		// setTime = (Button)findViewById(R.id.set_time_button);
		// showDate = (TextView)findViewById(R.id.show_date_textView);
		// showTime = (TextView)findViewById(R.id.show_time_textView);

		guideButtonsure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Message msg = new Message();
				if (guideButtonsure.equals(v)) {
					msg.what = DepartmentGuideActivity.SHOW_DATE;
				}
				DepartmentGuideActivity.this.dateandtimeHandler
						.sendMessage(msg);
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
		// showDate.setText(new StringBuilder().append(mYear).append("-")
		// .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth +
		// 1)).append("-")
		// .append((mDay < 10) ? "0" + mDay : mDay));
		NotificationRegistrationThread nt = new NotificationRegistrationThread(
				DepartmentGuideActivity.this, nm);
		nt.start();

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

	Handler dateandtimeHandler = new Handler() {
		@Override
		@SuppressWarnings("deprecation")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DepartmentGuideActivity.SHOW_DATE:
				showDialog(DATE_DIALOG);
				break;
			}
		}
	};

	// ****************************************************************

	// @Override
	// public void onClick(View v) {
	// System.out.println("�û��ҺŵĿ����ǣ�" + (department + 1));
	// // 1��ȡһ���Ի���Ĵ�����
	// AlertDialog.Builder builder = new Builder(DepartmentGuideActivity.this);
	// // 2����builder����һЩ����
	// builder.setTitle("��ʾ");
	// builder.setMessage("����Ϊ��Һţ����Ժ󡣡���");
	// builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// NotificationThread nt = new NotificationThread(
	// DepartmentGuideActivity.this, nm);
	// nt.start();
	// }
	// });
	// builder.create().show();
	// }
}