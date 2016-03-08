package com.srdz.bigproject.activity;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.srdz.bigproject.R;
import com.srdz.bigproject.communal.PageMenu;
import com.srdz.bigproject.view.SlidingLayout;

public class RegistrationActivity extends Activity {

	private int hospitalId;
	private ListView list;
	// 侧滑菜单
	private SlidingLayout slidingLayout;// 侧滑布局对象，用于通过手指滑动将左侧的菜单布局进行显示或隐藏
	private ImageButton muneButton;// menu按钮，点击按钮展示左侧布局，再点击一次隐藏左侧布局
	private LinearLayout contentListView;// 放在content布局中的ListView

	public int[] Image = { R.drawable.hos_final1, R.drawable.hos_final2,
			R.drawable.hos_final3, R.drawable.hos_final4,
			R.drawable.hos_final_5 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);

		// 侧滑菜单
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		muneButton = (ImageButton) findViewById(R.id.registration_muenuButton);
		contentListView = (LinearLayout) findViewById(R.id.registration_contentList);
		// 将监听滑动事件绑定在contentListView上
		slidingLayout.setScrollEvent(contentListView);
		muneButton.setOnClickListener(new OnClickListener() {
			PageMenu pm = new PageMenu(RegistrationActivity.this, 2);

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
		list = (ListView) findViewById(R.id.registration_listview1);

		// 生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 5; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", Image[i]);// 图像资源的ID
			// map.put("ItemTitle", "Level "+i);
			listItem.add(map);
		}
		// 生成适配器的Item和动态数组对应的元素
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// 数据源
				R.layout.forr_list,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "ItemImage" },
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.forr_list_itemImage });
		// 添加并且显示
		list.setAdapter(listItemAdapter);
		// 添加点击
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				setYiyuanId(arg2);
				// switch (arg2) {
				// case 0:
				//
				// break;
				// }
				Intent intent = new Intent(RegistrationActivity.this,
						HospitalDetailActivity.class);
				startActivity(intent);
				System.out.println("用户选择了第" + (hospitalId + 1) + "家医院！");
			}
		});

		// 添加长按点击
		// list.setOnCreateContextMenuListener(new OnCreateContextMenuListener()
		// {
		// @Override
		// public void onCreateContextMenu(ContextMenu menu, View v,
		// ContextMenuInfo menuInfo) {
		// menu.setHeaderTitle("选择挂号类型");
		// menu.add(0, 0, 0, "普通挂号");
		// menu.add(0, 1, 0, "急诊挂号");
		// }
		// });
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.setClass(RegistrationActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		return true;
	}

	public int getYiyuanId() {
		return hospitalId;
	}

	public void setYiyuanId(int yiyuanId) {
		this.hospitalId = yiyuanId;
	}
	// 长按菜单响应函数
	// @Override
	// public boolean onContextItemSelected(MenuItem item) {
	// setTitle("选择了" + item.getItemId() + "医院");
	// return super.onContextItemSelected(item);
	// }
}
