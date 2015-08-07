package me.wangolf.base;

import com.meigao.mgolf.R;
import com.umeng.analytics.MobclickAgent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public abstract class Mo_BasePage extends FragmentActivity {
	public boolean isLoadSuccess; // 用于判断是否是第一次加载
	protected LinearLayout loadfailView;
	private View view;
	public Context context;
	// @ViewInject(R.id.loading_view)
	protected View loadingView;

	public void dismissLoadingView() {
		if (loadingView != null)
			loadingView.setVisibility(View.INVISIBLE);
	}

	/**
	 * 1 。画界面 2 初始化数据
	 */
	public Mo_BasePage(Context context) {
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = initView(inflater);
		// loadingView = view.findViewById(R.id.loading_view);
		loadfailView = (LinearLayout) view.findViewById(R.id.ll_load_fail);
	}

	public View getRootView() {
		return view;
	}

	public abstract View initView(LayoutInflater inflater);

	public abstract void initData();

	public void UpSort(int sorttype, int rule) {
	}

	public void UpSort(int sorttype) {
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	// 更新界面
	public void upView() {
	}

	@Override
	protected void onPause() {

		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

}
