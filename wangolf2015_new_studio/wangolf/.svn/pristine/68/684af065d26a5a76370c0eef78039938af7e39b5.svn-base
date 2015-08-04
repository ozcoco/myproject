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
import java.util.ArrayList;

import com.meigao.mgolf.R;

import de.hdodenhof.circleimageview.CircleImageView;
import me.wangolf.bean.usercenter.UserMyFriendsEntity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.ImageViewUtil;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UserMyFriendsAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<UserMyFriendsEntity> mList;

	public UserMyFriendsAdapter(Context context) {
		super();
		this.context = context;
	}

	public ArrayList<UserMyFriendsEntity> getmList() {
		return mList;
	}

	public void setmList(ArrayList<UserMyFriendsEntity> mList) {
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
			convertView = View.inflate(context, R.layout.item_user_myfriends, null);
			holder.mFriendsName = (TextView) convertView.findViewById(R.id.friend_name);
			holder.mFriendAvatar = (CircleImageView) convertView.findViewById(R.id.friend_avatar);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		UserMyFriendsEntity bean = mList.get(position);
		holder.mFriendsName.setText(bean.getFriend_name());
		String path = bean.getFriend_avatar();
		if (!CheckUtils.checkEmpty(path)) {
			path = path.substring(0, path.lastIndexOf(".")) + "_180_180" + path.substring(path.lastIndexOf("."));
		}
		ImageViewUtil.loadimg(path, holder.mFriendAvatar, context);
		return convertView;
	}

	private class ViewHolder {
		public CircleImageView mFriendAvatar;
		public TextView mFriendsName;

	}

}
