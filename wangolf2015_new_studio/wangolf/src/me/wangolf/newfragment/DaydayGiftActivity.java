package me.wangolf.newfragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import java.io.File;
import java.util.Random;

import me.wangolf.ConstantValues;
import me.wangolf.base.BaseActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.FileUtils;
import me.wangolf.utils.LogUtils;
import me.wangolf.utils.ShareUtils;
import me.wangolf.utils.Xutils;

/**
 * Created by Administrator on 2015/6/16.
 */
public class DaydayGiftActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.common_back)
    private TextView mBack;
    @ViewInject(R.id.common_title)
    private TextView mTitle;
    @ViewInject(R.id.common_bt)
    private TextView mBt;
    @ViewInject(R.id.webView)
    private WebView mWeb;
    private String sharetitle;
    private String picfile;
    private String shareUrl;
    private String imagename;
    static  String html;
    Random random = new Random();
    private static final String APP_CACAHE_DIRNAME = "/ webviewCacheChromium";
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_daydaygift);
        ViewUtils.inject(this);
        initData();

    }

    @Override
    public void getData() {
        super.getData();
    }
    @Override
    public void initData() {
        mBack.setVisibility(View.VISIBLE);
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText("天天有礼");
        mBt.setText("分享");
        mBack.setOnClickListener(this);
        WebSettings setting = mWeb.getSettings();
        //setting.setUseWideViewPort(true);
        setting.setLoadWithOverviewMode(true);
        setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        setting.setJavaScriptEnabled(true);
        mWeb.requestFocus();
        setting.setDomStorageEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setDomStorageEnabled(true);
        setting.setDatabaseEnabled(true);
        setting.setUseWideViewPort(false);
        setting.setAppCacheEnabled(false);
        mWeb.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        mWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }


        });
        String user_id = ConstantValues.UID;
        String passowd = CheckUtils.checkEmpty(ConstantValues.PASSWORD) ? ConstantValues.OPEN_ID : ConstantValues.PASSWORD;
        String Url;
        if (CheckUtils.checkEmpty(ConstantValues.UID) | CheckUtils.checkEmpty(passowd)) {
            Url = "http://www.wangolf.me/webApp/purchase/daydayGift.html?user_id=-1" + "&password=-1&random=" + random.nextInt(1000);
        } else {
            Url = "http://www.wangolf.me/webApp/purchase/daydayGift.html?user_id=" + user_id + "&password=" + passowd + "&random=" + random.nextInt(1000);
        }
        //Url="http://www.chzz.org";
        mWeb.loadUrl(Url);
        //Log.i("wangolf",Url);
        mWeb.setWebViewClient(new webViewClient());


        //点击后退按钮,让WebView后退一页(也可以覆写Activity的onKeyDown方法)
        mWeb.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWeb.canGoBack()) {  //表示按返回键

                        picfile = "";
                        imagename = "";
                        mBt.setVisibility(View.GONE);
                        mWeb.goBack();   //后退

                        //webview.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_back:
                // mWeb.goBack();
                if (4 == KeyEvent.KEYCODE_BACK && mWeb.canGoBack()) {  //表示按返回键
                    // clearWebViewCache();
                    mWeb.goBack();   //后退
                    picfile = "";
                    imagename = "";
                    mBt.setVisibility(View.GONE);
                } else {
                    finish();
                }
                break;
            case R.id.common_bt:
                sharetitle = "每日惊喜不断，【打球】礼包天天送，今天你抢了吗？";
                String sharecontent ="【打球】App，打高尔夫必备神器，超低价位，分享有礼，更有礼包优惠享不停！";
               // shareUrl = "http://www.wangolf.me/webApp/purchase/daydayGift.html?user_id=-1" + "&password=-1&random=" + random.nextInt(1000);
                if (CheckUtils.checkEmpty(imageUrl)) {
                    if (!FileUtils.isFile("share_logo.jpg"))
                        FileUtils.saveBitToSD(FileUtils.drawableToBitamp(getResources().getDrawable(R.drawable.app_logo)), "share_logo");
                    picfile = "/share_logo.jpg";
                } else {

                    picfile = "http://www.wangolf.me" + imageUrl.replace("src=\"", "");
                    Xutils.loadImage(picfile);

                }

                if (!CheckUtils.checkEmpty(picfile)) {

                    int p = picfile.lastIndexOf("/");
                    imagename = picfile.substring(p);
                }
                ShareUtils.showShareandUrl(sharetitle,sharecontent, shareUrl, this, CheckUtils.checkEmpty(imagename) ? "" : imagename);
            default:
                break;
        }

    }

    //Web视图
    private class webViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            imageUrl = "";
            shareUrl=url;
           // picfile = "";
            view.loadUrl(url);
            mBt.setVisibility(View.VISIBLE);
            mBt.setOnClickListener(DaydayGiftActivity.this);

            return true;
        }

        @Override
        public void onLoadResource(WebView view, String url) {

            super.onLoadResource(view, url);
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.loadUrl("javascript:window.local_obj.showSource('<head>'+"
                    + "document.getElementsByTagName('html')[0].innerHTML+'</head>');");

        }

    }
    // 设置回退

    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWeb.canGoBack()) {
            //  clearWebViewCache();
            mBt.setVisibility(View.GONE);
            mWeb.goBack(); // goBack()表示返回WebView的上一页面
            picfile = "";
            imagename = "";
            return true;

        }

        return super.onKeyDown(keyCode, event);

    }

    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            // picfile="http://www.wangolf.me"+ html.substring(html.indexOf("src=\"/public/"), html.indexOf(".jpg")).replace("src=\"","")+".jpg";
           // Log.i("wangolf", html.indexOf("src=\"/public/") + "*******************" + html.indexOf(".jpg"));
            if (html.indexOf("src=\"/public/")!=-1& (html.indexOf(".jpg")!=-1|html.indexOf(".JPG")!=-1)){
                int index;
                String last_url;
              if(html.indexOf(".jpg")==-1){
                  index =html.indexOf(".JPG");
                  last_url=".JPG";
              }else if(html.indexOf(".JPG")==-1) {
                  index =html.indexOf(".jpg");
                  last_url=".jpg";
              }else if(html.indexOf(".jpg")>html.indexOf(".JPG")) {
                  index =html.indexOf(".JPG");
                  last_url=".JPG";
              }else {
                  last_url=".jpg";
                  index =html.indexOf(".jpg");
              }

                    imageUrl = html.substring(html.indexOf("src=\"/public/"), index)+last_url;
            }
            DaydayGiftActivity.html=html;

        }
    }
}


