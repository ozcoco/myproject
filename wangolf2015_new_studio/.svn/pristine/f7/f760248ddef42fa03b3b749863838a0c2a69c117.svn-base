package me.wangolf.adapter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年3月4日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年3月4日
 * 
 * 描述 ：社区首页 置顶adapter
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.util.ArrayList;

import com.meigao.mgolf.R;

import me.wangolf.bean.community.CommunityHotPostsEntity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommunityIndexHotAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<CommunityHotPostsEntity> hot_list;

	public CommunityIndexHotAdapter(Context context) {
		super();
		this.context = context;
	}

	public ArrayList<CommunityHotPostsEntity> getHot_list() {
		return hot_list;
	}

	public void setHot_list(ArrayList<CommunityHotPostsEntity> hot_list) {
		this.hot_list = hot_list;
	}

	@Override
	public int getCount() {

		return hot_list == null ?0 : hot_list.size();
	}

	@Override
	public Object getItem(int position) {

		return hot_list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 加载或复用item界面
		ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_comm_hot_list, null);
			holder.hot_title = (TextView) convertView.findViewById(R.id.comm_hot_title);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 设数据

		CommunityHotPostsEntity data_hot = hot_list.get(position);
		holder.hot_title.setText(data_hot.getTitle());
		return convertView;
	}

	class ViewHolder {
//		private ImageView hot_ico;
		private TextView hot_title;
//		private TextView comm_list_title;
//		private TextView comm_list_content;
//		private TextView comm_name;
//		private TextView comm_time;
//		private TextView comm_reply;
//		private TextView comm_visit;
	}
}
