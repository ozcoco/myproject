package me.wangolf.shop;

import java.util.ArrayList;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.bean.shop.ProImgDetail;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.GsonTools;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProImgDetailActivity extends Activity implements OnClickListener {
	private String proid;// 商品ID
	@ViewInject(R.id.common_title)
	private TextView common_title;
	@ViewInject(R.id.shop_pro_webview)
	private WebView shop_pro_webview;
	@ViewInject(R.id.common_back)
	private Button common_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_pro_imgdetail);
		ViewUtils.inject(this);
		initData();
	}

	public void initData() {
		proid = getIntent().getStringExtra("proid");
		common_title.setText(ConstantValues.SHOP_IMG_TITLE);
		common_back.setVisibility(0);
		common_back.setOnClickListener(this);
		getData();

	}

	public void getData() {
		try {
			ServiceFactory.getShopEngineInstatice().getproImgDetail(proid, new IOAuthCallBack() {

				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), ConstantValues.NONETWORK, 0).show();
					} else {
						ProImgDetail bean = GsonTools.changeGsonToBean(result, ProImgDetail.class);
						if ("1".equals(bean.getStatus())) {
							ArrayList<ProImgDetail> data = bean.getData();
							loadNewsData(data.get(0).getImgdetail());
						}
					}
				}

			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadNewsData(String info) {
		WebSettings setting = shop_pro_webview.getSettings();
		// setting.setUseWideViewPort(true);
		setting.setJavaScriptEnabled(true);
		shop_pro_webview.loadData(info, "text/html; charset=UTF-8", null);

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
}
