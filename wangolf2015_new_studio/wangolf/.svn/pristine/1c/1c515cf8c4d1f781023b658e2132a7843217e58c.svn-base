package me.wangolf.adapter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 下午3:00:04
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 下午3:00:04
 * 
 * 描述 ：用户——消息——adapter
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import me.wangolf.bean.usercenter.UserMessageListEntity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.FileUtils;
import me.wangolf.utils.ImageViewUtil;
import me.wangolf.utils.Xutils;

import com.meigao.mgolf.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserMessageAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<UserMessageListEntity> mMessage_list;

	public UserMessageAdapter(Context context) {
		super();
		this.context = context;
	}

	public ArrayList<UserMessageListEntity> getmMessage_list() {
		return mMessage_list;
	}

	public void setmMessage_list(ArrayList<UserMessageListEntity> mMessage_list) {
		this.mMessage_list = mMessage_list;
	}

	@Override
	public int getCount() {
		return mMessage_list == null ? 0 : mMessage_list.size();

	}

	@Override
	public Object getItem(int position) {

		return mMessage_list.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_user_message_new, null);
			holder.mName = (TextView) convertView.findViewById(R.id.message_name);
			holder.mContent = (TextView) convertView.findViewById(R.id.message_content);
			holder.mAvatar = (CircleImageView) convertView.findViewById(R.id.avatar);
			holder.mAddtime = (TextView) convertView.findViewById(R.id.message_addtime);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		UserMessageListEntity bean = mMessage_list.get(position);
		holder.mName.setText(bean.getName());
		holder.mContent.setText(bean.getContent());
		holder.mAddtime.setText(bean.getUpdate_time());
		//Xutils.getBitmap(context, holder.mAvatar, bean.getAvatar());
		String path = bean.getAvatar();
		if (!CheckUtils.checkEmpty(path)) {
			//path = path.substring(0, path.lastIndexOf(".")) + "_180_180" + path.substring(path.lastIndexOf("."));
			path = FileUtils.getPhotoPath(path);
			ImageViewUtil.loadimg(path, holder.mAvatar, context);
		}
		return convertView;
	}

	private class ViewHolder {
		public TextView mName;
		public TextView mContent;
		public CircleImageView mAvatar;
		public TextView mAddtime;

	}
}
