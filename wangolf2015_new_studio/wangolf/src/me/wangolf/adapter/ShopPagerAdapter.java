package me.wangolf.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import me.wangolf.base.Mo_BasePage;

public class ShopPagerAdapter<T> extends PagerAdapter 
{
	private List<T> pages;
	
	private String[] title;

	public ShopPagerAdapter(Context ct, ArrayList<T> pages, String[] title)
	{
		this.pages = pages;
		
		this.title = title;
	}

	public List<T> getPages() 
	{
		return pages;
	}

	public void setPages(List<T> pages)
	{
		this.pages = pages;
	}

	public String[] getTitle()
	{
		return title;
	}

	public void setTitle(String[] title) 
	{
		this.title = title;
	}

	@Override
	public void destroyItem(View container, int position, Object object) 
	{
		if (position >= pages.size())
			return;
		((ViewPager) container).removeView(((Mo_BasePage) pages.get(position)).getRootView());
	}

	@Override
	public CharSequence getPageTitle(int position) 
	{
		return title[position];
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) 
	{
		((ViewPager) arg0).addView(((Mo_BasePage) pages.get(arg1)).getRootView(), 0);
		
		return ((Mo_BasePage) pages.get(arg1)).getRootView();
	}

	@Override
	public int getCount() 
	{
		return pages.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) 
	{
		return arg1 == arg0;
	}

}
