package me.wangolf.college;

import java.util.ArrayList;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.bean.college.InfoDetailEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CollegePageContent extends Activity implements OnClickListener 
{

	@ViewInject(R.id.common_back)
	private Button common_back; // 返回键
	
	@ViewInject(R.id.chzz_news_content_webview)
	private WebView mWebView;
	
	@ViewInject(R.id.chzz_news_no_data_image)
	private LinearLayout chzz_news_no_data_image;
	
	@ViewInject(R.id.common_title)
	private TextView common_title;
	
	private String knowid;
	
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.college_page_content);
		
		ViewUtils.inject(this);
		
		knowid = getIntent().getStringExtra("knowid"); // 内容的ID
		
		initData();

	}

	public void initData() 
	{
		dialog = DialogUtil.getDialog(this);
		
		dialog.show();
		
		common_title.setText(ConstantValues.CONTENT_TITLE); // 初始化标题 又写死了
		
		common_back.setVisibility(0);// 返回键可见
		
		common_back.setOnClickListener(this); // 返回键事件
		
		getData(); // 拿显示的数据
	}

	
	public void getData() 
	{
		try 
		{
			ServiceFactory.getCollegeEngineInstatice().getInfoDetail(knowid, new IOAuthCallBack()
			{
				@Override
				public void getIOAuthCallBack(String result) 
				{
					if (result.equals(ConstantValues.FAILURE)) 
					{
						Toast.makeText(getApplicationContext(), "请确保已联网", 0).show();
					} 
					else 
					{
						InfoDetailEntity bean = GsonTools.changeGsonToBean(result, InfoDetailEntity.class);
						
						ArrayList<InfoDetailEntity> info = bean.getData();
						
						loadNewsData(info.get(0).getContent());
						
						dialog.cancel();
					}
				}
			});
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	private void loadNewsData(String info) 
	{
		WebSettings setting = mWebView.getSettings();
		// setting.setUseWideViewPort(true);
		setting.setJavaScriptEnabled(true);
		// setting.setSupportZoom(true);
		// setting.setBuiltInZoomControls(true);
		// setting.setJavaScriptCanOpenWindowsAutomatically(true);
		// setting.setLoadWithOverviewMode(true);
		chzz_news_no_data_image.setVisibility(8);
		
		mWebView.loadData(info, "text/html; charset=UTF-8", null);

	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
		// 返回事件
		case R.id.common_back:
			finish();
		default:
			break;
		}

	}

}
