package me.wangolf.adapter;

import java.util.List;

import me.wangolf.base.Mo_BasePage;
import me.wangolf.utils.viewUtils.CustomViewPager;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class CommonPageAdapter<T> extends PagerAdapter {
	private List<T> list;

	public CommonPageAdapter(Context ct, List<T> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		// super.destroyItem(container, position, object);
		((CustomViewPager) container).removeView(((Mo_BasePage) list.get(position)).getRootView());

	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		((CustomViewPager) container).addView(((Mo_BasePage) list.get(position)).getRootView(), 0);
		return ((Mo_BasePage) list.get(position)).getRootView();
	}

}
