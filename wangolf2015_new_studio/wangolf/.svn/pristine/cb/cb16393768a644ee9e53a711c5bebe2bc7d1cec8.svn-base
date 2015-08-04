package me.wangolf.test;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.meigao.mgolf.R;

public class MainActivity extends Activity {

	private WebView webView;
	private static EditText webText;
	private Button webButton;
	static String source;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		webView = (WebView) findViewById(R.id.webView);
		webText = (EditText) findViewById(R.id.Location);
		webText.setText("www.cnblogs.com/hibraincol/");
		webButton = (Button) findViewById(R.id.Goto);
		//设置属性
		webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        webView.setWebViewClient(new MyWebViewClient());
		// 网页转向
		webButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String url = webText.getText().toString();
				if (url.contains("http://") == false)
					url = "http://" + url;
				webView.loadUrl(url);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);
		webText.setText(url);
		return true;
	}
	
	public void showSource(){
		EditText s=new EditText(this);
    	s.setText(source);
    	ScrollView scroll=new ScrollView(this);
    	LinearLayout layout=new LinearLayout(this);
    	scroll.addView(layout);
    	layout.addView(s);
		new AlertDialog.Builder(MainActivity.this)
		.setIcon(R.drawable.ic_launcher)
    	.setView(scroll)
    	.setPositiveButton("确定", new DialogInterface.OnClickListener() {
    	    public void onClick(DialogInterface dialog, int which) {
    	    	dialog.dismiss();
    	    }
    	})
    	.show();
	}

	final class MyWebViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
		}

		public void onPageFinished(WebView view, String url) {
			view.loadUrl("javascript:window.local_obj.showSource('<head>'+" +
					"document.getElementsByTagName('html')[0].innerHTML+'</head>');");
			MainActivity.webText.setText(url);
			super.onPageFinished(view, url);
		}
	}

	final class InJavaScriptLocalObj {
		public void showSource(String html) {
			MainActivity.source=html;
			//Log.i("wangolf",html+"*******************");
		}
	}
}
