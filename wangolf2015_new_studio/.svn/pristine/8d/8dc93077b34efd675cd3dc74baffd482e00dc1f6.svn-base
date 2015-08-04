package me.wangolf.base;

import com.umeng.analytics.MobclickAgent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Mo_BaseFragment extends Fragment {
	public boolean isLoadSuccess; // 用于判断是否是第一次加载
	public View view;
	public Context content;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initData(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		content = getActivity();

	}

	/**
	 * setContentView;
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = initView(inflater);

		return view;
	}

	public View getRootView() {
		return view;
	}

	/**
	 * 初始化view
	 */
	public abstract View initView(LayoutInflater inflater);

	/**
	 * 初始化数据
	 */
	public abstract void initData(Bundle savedInstanceState);

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
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
