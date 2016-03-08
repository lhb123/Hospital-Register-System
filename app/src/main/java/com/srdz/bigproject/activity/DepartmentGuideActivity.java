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

	private SlidingLayout slidingLayout;// 侧滑布局对象，用于通过手指滑动将左侧的菜单布局进行显示或隐藏
	private ImageButton muneButton;// menu按钮，点击按钮展示左侧布局，再点击一次隐藏左侧布局
	private LinearLayout contentListView;// 放在content布局中的ListView
	private ListView list1;
	private ListView list2;
	private Button guideButtonsure;
	private int department;
	// 预约的时间
	private int mYear;
	private int mMonth;
	private int mDay;
	private static final int DATE_DIALOG = 1;
	private static final int SHOW_DATE = 0;
	// ****************************************

	private NotificationManager nm;
	public String[] buttontext = { "急诊科", "骨科", "内科", "儿科", "外科", "胃肠科", "超声科",
			"妇产科", "口腔科", "肿瘤科", "检验科", "泌尿科", "耳鼻喉科" };
	public String[] textview = {
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "急诊医学科（室）或急诊医学中心是医院中重症病人最集中、病种最多、抢救和管理任务最重的科室，是所有急诊病人入院治疗的必经之路。综合医院急诊设有全科、内、外、妇、儿、五官、发热、腹泻等专科诊室。"
					+ "急诊医学科已发展为集急诊、急救与重症监护三位一体的大型的急救医疗技术中心和急诊医学科学研究中心，可以对急、危、重病人实行一站式无中转急救医疗服务，被喻为现代医学的标志和人类生命健康的守护神。",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "重庆协和医院骨科，是西南地区少数专业致力于骨疾病研究和临床诊治的机构，设有专门的骨疾病研究室，根据本地患者体质特点，创造个性化诊疗方式。"
					+ "本院骨科汇集了多位多年从事骨科临床工作的专家及医师组成，同时引先进的仪器设备，应用中西医结合的治疗手段，在股骨头坏死、腰椎间盘突出、骨质增生、颈椎病、肩周炎、风湿、类风湿性关节炎等骨科疾病的治疗上取得了卓越成效。",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "重庆协和医院内科现已发展为一个集科研、临床和教学为一体的重点科室，凭借医院强大的病理诊断、影像检查、临床检验优势，不断朝着现代内科学方向发展和提高。"
					+ "科室由一批主任及副主任级专家亲自应诊，他们以几十年的内科临床工作经验，形成了一套较为完整的内科诊治体系，对消化系统、呼吸系统、心血管系统等内科常见病、多发病采用急则治标、缓则治本的原则，形成了特色治疗体系。",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "重庆协和医院儿科由重庆市著名儿科专家虞曦明亲自坐诊，对儿科常见病、多发病，如营养障碍性疾病、新生儿疾病、感染性疾病、消化系统疾病、泌尿系统疾病、神经系统疾病等具有很高的诊疗水平；"
					+ "对儿科如遗传代谢性疾病、免疫性疾病、心血管疾病，造血系统疾病、内分泌疾病等少见病具有较高的诊治水平，尤其是对小儿急救、婴儿黄疸、呼吸道疾病、消化系统疾病等进行了深入的研究。",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "本院外科涵盖了综合外科所有常见病、多发病的临床诊治。由一批临床经验丰富、技术精湛的高级医师主诊及手术。"
					+ "科室收治甲状腺、肝、胆、脾、胰、胃肠的各种良恶性疾病病人及血管外科病人，能完成包括胰十二指肠切除、腹主动脉瘤在内的各种普外科手术，门脉高压上消化道出血、急性坏死性胰腺炎、感染性休克、失血性休克有成熟的临床经验。此外本院微创技术也得到长足发展。",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "重庆协和医院胃肠科由有临床四十余年经验的主任医师王长福领衔。重庆协和医院胃肠科采用国际先进医疗设备，诊治胃肠道疾病。"
					+ "率先在西南地区开展国际先进的不开刀技术--“氩气术”,微创治疗疣状胃炎、胃肠道息肉、食管狭窄等疾病，多年来经数万名患者临床验证，手术成功率达95%以上，疗效显著，不易复发。",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "近年来科室大力引进世界超声新技术、新设备，高端彩色多普勒超声、彩超诊断仪，可开展包含腹部、心脏、浅表及小器官、造影超声诊断以及超声导航下的微创介入治疗，"
					+ "覆盖了超声医学领域的主要内容，并在腹部彩色多普勒超声、心脏彩色多普勒超声、小器官彩超、等方面具有明显的区位优势和影响。",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "重庆协和医院妇产科作为重点学科，下设妇科、产科和计划生育科，由有着40多年科研、临床、教学经验的重庆医科大学教授徐小蓉领衔，云集了金玉顺、刘富蓉、封娟等十几位主任、副主任级资深妇科专家，"
					+ "并与上海市妇女病康复专业委员会共同建立“西南妇女病康复技术指导基地”，为女性朋友提供高质量的医疗服务。",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "口腔科拥有多台先进医疗设备，如重庆地区最先进的数字化牙科曲面断层全景X光机，可用于头面部多角度摄像及全景牙片的拍摄；高级的牙科器械消毒灭菌设备等。"
					+ "目前科室设施日臻完善，技术力量雄厚，开展的业务项目全面。对专科疾病的诊治水平在本市位居前列，不少治疗手段属国内领先，在重庆市享有较高声誉。",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "肿瘤科主要从事鼻咽癌、宫颈癌、乳腺癌、肺癌、食管癌、淋巴瘤、脑瘤等恶性肿瘤的放射治疗、化学治疗、聚焦超声治疗和生物治疗。本科室设备齐全，技术力量雄厚，在恶性肿瘤的综合治疗、规范化治疗方面成效显著，治疗效果达国内领先水平。 ",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "科室在复旦大学泌尿科资深专家张元芳教授（博士生导师）、原全军泌尿外科中心主任张绍增教授等享受国务院特殊津贴专家带领下，组建了一支具有老中青三代衔接、汇聚多名主任、副主任级医师的骨干精英队伍，科室成员获得国家级、省部级各类奖项上百项，参与完成七五、八五期间多个国家级科技攻关课题，并夺得“2010全国实用医学新进展暨优秀成果学术交流会”优秀成果一等奖。 ",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "科室目前拥有数名由主任检验师、副主任检验师组成的庞大专家队伍，以深厚的技术功底、扎实的理论基础和丰富的操作经验，凭借严谨的管理、规范的操作和完善的服务体系，即让每一个病人能得到最放心的检验报告。 ",
			"\t"
					+ "\t"
					+ "\t"
					+ "\t"
					+ "重庆协和医院耳鼻喉科由行医30多年，有着丰富临床经验而技术精湛的著名耳鼻喉疾病治疗专家、主任医师陈应兰教授坐诊，"
					+ "在各种鼻窦、鼻腔疾病、过敏性鼻炎、咽炎、扁桃体炎、鼾症及青光眼、白内障、中耳炎等疾病的治疗上有明显优势。", };

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
		// 侧滑菜单
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		muneButton = (ImageButton) findViewById(R.id.departement_guide_muenuButton);
		contentListView = (LinearLayout) findViewById(R.id.departement_guide_contentList);
		// 将监听滑动事件绑定在contentListView上
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

		// 预约时间

		final Calendar c = Calendar.getInstance(); // 获取日历中内容
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		initializeView();
		setDateTime();
		// ****************************

		list1 = (ListView) findViewById(R.id.department_guide_button);
		list2 = (ListView) findViewById(R.id.department_guide_text);
		// left---Button生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 13; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("guideLeft", buttontext[i]);// 图像资源的ID
			// map.put("Itemtext", text[i]);
			listItem.add(map);
		}
		// 生成适配器的Item和动态数组对应的元素
		SimpleAdapter listLeftAdapter = new SimpleAdapter(this, listItem,// 数据源
				R.layout.listfor_depar_guide_left,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "guideLeft" },
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.guide_textviews1 });
		// 添加并且显示
		list1.setAdapter(listLeftAdapter);

		// 添加list1点击
		list1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				department = arg2;
				// right----TextView加入数据
				ArrayList<HashMap<String, Object>> listText = new ArrayList<HashMap<String, Object>>();

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("TextView", textview[arg2]);// 图像资源的ID
				// map.put("Itemtext", text[i]);
				listText.add(map);

				// 生成适配器的Item和动态数组对应的元素
				SimpleAdapter listTextViewAdapter = new SimpleAdapter(
						DepartmentGuideActivity.this, listText,// 数据源
						R.layout.listfor_guide_text,// ListItem的XML实现
						// 动态数组与ImageItem对应的子项
						new String[] { "TextView" },
						// ImageItem的XML文件里面的一个ImageView,两个TextView ID
						new int[] { R.id.guide_textViews });
				// 添加并且显示
				list2.setAdapter(listTextViewAdapter);
				guideButtonsure.setVisibility(0);
				// guideButtonsure.setOnClickListener(DepartmentGuideActivity.this);
			}
		});
	}

	// 预约时间
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
	// System.out.println("用户挂号的科室是：" + (department + 1));
	// // 1获取一个对话框的创建器
	// AlertDialog.Builder builder = new Builder(DepartmentGuideActivity.this);
	// // 2所有builder设置一些参数
	// builder.setTitle("提示");
	// builder.setMessage("正在为您挂号，请稍后。。。");
	// builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
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