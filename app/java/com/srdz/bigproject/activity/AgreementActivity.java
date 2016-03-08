package com.srdz.bigproject.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.srdz.bigproject.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AgreementActivity extends Activity {
	String[] agree = {
			"【首部及导言】欢迎您使用“施人定治”软件及服务！为使用“施人定治”软件（以下统称“本软件”）及服务，您应当阅读并遵守"
					+ "《“施人定治”软件许可及服务协议》（以下简称“本协议”），以及《“施人定治”服务协议》。请您务必审慎阅读、充分理解各条款内容，特别是免除或者限制责任的条款，"
					+ "以及开通或使用某项服务的单独协议，并选择接受或不接受。限制、免责条款可能以加粗形式提示您注意。"
					+ "除非您已阅读并接受本协议所有条款，否则您无权下载、安装或使用本软件及相关服务。您的下载、安装、使用、登录等行为即视为您已阅读并同意本协议的约束。"
					+ "如果您未满18周岁，请在法定监护人的陪同下阅读本协议，并特别注意未成年人使用条款。",
			"  一、【协议的范围】\n1.1【协议适用主体范围】\n 本协议是您与“施人定治”之间关于您下载、安装、使用、登录本软件，以及使用本服务所订立的协议。\n"
					+ " 1.2【协议关系及冲突条款】\n  本协议的内容，包括但不限于以下与本服务、本协议相关的协议、规则、规范以及“施人定治”可能不断发布的关于本服务的相关协议、规则、规范等内容，前述内容一经正式发布，即为本协议不可分割的组成部分，与其构成统一整体，您同样应当遵守：\n"
					+ "（1）《“施人定治”服务协议》。\n（2）《“施人定治”账号规则》。\n  本协议与上述内容存在冲突的，以本协议为准。",
			"  二、【关于本服务】\n"
					+ "  2.1【本服务内容】\n"
					+ " 本服务内容是指“施人定治”通过本软件向用户提供的相关服务（简称“本服务”）。\n"
					+ " 2.2【本服务形式】\n"
					+ " 您可以通过手机终端以客户端形式使用本服务，具体以“施人定治”提供的为准，同时，“施人定治”会不断丰富您使用本服务的终端、形式等。当您使用本服务时，您应选择与您的终端、系统等相匹配的本软件版本，否则，您可能无法正常使用本服务。"
					+ "2.3【许可的范围】\n"
					+ "2.3.1 “施人定治”给予您一项个人的、不可转让及非排他性的许可，以使用本软件。您可以为非商业目的在单一台终端设备上下载、安装、使用、登录本软件。"
					+ "2.3.2 您可以制作本软件的一个副本，仅用作备份。备份副本必须包含原软件中含有的所有著作权信息。\n"
					+ " 2.3.3 本条及本协议其他条款未明示授权的其他一切权利仍由“施人定治”保留，您在行使这些权利时须另外取得“施人定治”的书面许可。“施人定治”如果未行使前述任何权利，并不构成对该权利的放弃。\n" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.agreement);
		// 绑定Layout里面的ListView
		ListView list = (ListView) findViewById(R.id.agreetext);

		// 生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 3; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("agreetext", agree[i]);// 图像资源的ID
			listItem.add(map);
		}
		// 生成适配器的Item和动态数组对应的元素
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// 数据源
				R.layout.listfor_agreement,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "agreetext" },
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.agreement_text1 });
		// 添加并且显示
		list.setAdapter(listItemAdapter);

	}
}