package me.wangolf.adapter;

import java.util.ArrayList;

import me.wangolf.bean.usercenter.UserMyReplyEntity;

import com.meigao.mgolf.R;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UserMyReplyAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<UserMyReplyEntity> mList;

	public UserMyReplyAdapter(Context context) {
		super();
		this.context = context;
	}

	public ArrayList<UserMyReplyEntity> getmList() {
		return mList;
	}

	public void setmList(ArrayList<UserMyReplyEntity> mList) {
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
			convertView = View.inflate(context, R.layout.item_user_myreply, null);
			holder.mPostsTitle = (TextView) convertView.findViewById(R.id.posts_title);
			holder.mReplycontent = (TextView) convertView.findViewById(R.id.reply_content);
			holder.mName = (TextView) convertView.findViewById(R.id.posts_name);
			holder.mPostsTime = (TextView) convertView.findViewById(R.id.posts_time);
			holder.mReplyCount = (TextView) convertView.findViewById(R.id.reply_count);
			holder.mPostContent = (TextView) convertView.findViewById(R.id.posts_content);
			holder.mReplyContentTiem = (TextView) convertView.findViewById(R.id.reply_content_tiem);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		UserMyReplyEntity bean = mList.get(position);
		if (bean != null) {
			holder.mPostsTitle.setText(bean.getPosts_title());
			holder.mReplycontent.setText(Html.fromHtml("<font color=\"#C14C29\">回复:</font>" + bean.getContent()));
			holder.mName.setText(bean.getPosts_user_id());
			holder.mPostsTime.setText(bean.getPosts_time());
			holder.mReplyCount.setText(bean.getPosts_reply_count() + "");
			holder.mPostContent.setText(bean.getPosts_content());
			holder.mReplyContentTiem.setText(bean.getAddtime());
		}
		return convertView;
	}

	private class ViewHolder {
		public TextView mPostsTitle;
		public TextView mReplycontent;
		public TextView mName;
		public TextView mPostsTime;
		public TextView mReplyCount;
		public TextView mPostContent;
		private TextView mReplyContentTiem;

	}
}
