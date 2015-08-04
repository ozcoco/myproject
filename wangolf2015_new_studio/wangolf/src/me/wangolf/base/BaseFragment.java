package me.wangolf.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2015/4/2.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化数据
     */
    public void initData(){};

    /**
     * 初始化视图控件
     */
    public void initView(){};


    /**
     * 获取数据
     */
    public void getData() {
    }

    ;

    /**
     * 更新数据
     */
    public void upData() {
    }

    /**
     * 初始化下拉刷新刷新
     */
    public void initRefreshableView() {
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MobclickAgent.onPageStart("MainScreen"); //统计页面
    }
}
