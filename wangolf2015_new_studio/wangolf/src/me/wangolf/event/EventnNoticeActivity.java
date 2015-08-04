package me.wangolf.event;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import me.wangolf.ConstantValues;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.event.EventDetailEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;

public class EventnNoticeActivity extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.common_back)
	private Button common_back; // 后退
	@ViewInject(R.id.common_title)
	private TextView common_title;// 标题
	@ViewInject(R.id.common_bt)
	private TextView common_bt;// 地图
	@ViewInject(R.id.webView)
	private WebView webView;// 内容
	private String info;// 公告内容
	private Dialog dialog;
	private String eveid;
	private String code;// 公告内容

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_event_notice);
		ViewUtils.inject(this);
		initData();
	}

	@Override
	public void initData() {
		common_back.setVisibility(0);
		common_title.setText("活动公告");
		common_back.setOnClickListener(this);
		eveid = getIntent().getStringExtra("noticeid");
		code = getIntent().getStringExtra("code");
		loadData(code);
	}

	@Override
	public void getData() {

		dialog = DialogUtil.getDialog(this);
		dialog.show();
		try {
			ServiceFactory.getEventEngineInstatice().getEventDetail(eveid, new IOAuthCallBack() {
				private EventDetailEntity data;

				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), ConstantValues.NONETWORK, 0).show();
					} else {
						EventDetailEntity bean = GsonTools.changeGsonToBean(result, EventDetailEntity.class);
						if ("1".equals(bean.getStatus())) {
							data = bean.getData().get(0);
							loadData(data.getContent()); // 加载内容
							dialog.cancel();
						}
					}
				}
			});

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.common_back:
			finish();
			break;

		default:
			break;
		}
	}

	// 加载活动数据
	public void loadData(String info) {
		WebSettings setting = webView.getSettings();
		// setting.setUseWideViewPort(true);
		setting.setJavaScriptEnabled(true);
		// setting.setSupportZoom(true);
		// setting.setBuiltInZoomControls(true);
		// setting.setJavaScriptCanOpenWindowsAutomatically(true);
		// setting.setLoadWithOverviewMode(true);

		webView.loadData(info, "text/html; charset=UTF-8", null);
	}
}
