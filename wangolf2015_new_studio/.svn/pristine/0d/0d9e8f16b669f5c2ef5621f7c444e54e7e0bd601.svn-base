package me.wangolf.base;

import com.lidroid.xutils.ViewUtils;
import com.meigao.mgolf.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.baseactivity);

        ViewUtils.inject(this);
        // ***推送统计h
        PushAgent.getInstance(this).onAppStart();

    }

    public void initData() {

    }

    public void getData() {
    }

    public void onResume() {
        super.onResume();

        MobclickAgent.onPageStart("SplashScreen"); // 统计页面
        MobclickAgent.onResume(this); // 统计时长
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SplashScreen"); // 保证 onPageEnd 在onPause
        // 之前调用,因为 onPause 中会保存信息
        MobclickAgent.onPause(this);
    }
}
