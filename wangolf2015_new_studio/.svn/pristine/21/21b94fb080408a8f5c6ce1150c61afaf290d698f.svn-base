package me.wangolf.adapter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 下午2:42:45
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 下午2:42:45
 * 
 * 描述 ： 15关注列表 adapter
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meigao.mgolf.R;

import java.util.ArrayList;


import me.wangolf.bean.community.CommunityTagEntity;

public class CommunityPostsTagListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<CommunityTagEntity> mList;

	public CommunityPostsTagListAdapter(Context context) {
		super();
		this.context = context;
	}

	public ArrayList<CommunityTagEntity> getmList() {
		return mList;
	}

	public void setmList(ArrayList<CommunityTagEntity> mList) {
		this.mList = mList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList == null ? 0 : mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_tag_list, null);
			holder.mTagName = (TextView) convertView.findViewById(R.id.tag_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		CommunityTagEntity bean = mList.get(position);
		holder.mTagName.setText(bean.getName());
		return convertView;
	}

	private class ViewHolder {

		public TextView mTagName;

	}

}
