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

import me.wangolf.ConstantValues;
import me.wangolf.bean.usercenter.UserMessageDetailEntity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.FileUtils;
import me.wangolf.utils.ImageViewUtil;

import com.meigao.mgolf.R;

import de.hdodenhof.circleimageview.CircleImageView;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UserMessageDetailAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<UserMessageDetailEntity> mMessage_list;

	public UserMessageDetailAdapter(Context context) {
		super();
		this.context = context;
	}

	public ArrayList<UserMessageDetailEntity> getmMessage_list() {
		return mMessage_list;
	}

	public void setmMessage_list(ArrayList<UserMessageDetailEntity> mMessage_list) {
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_user_message_detail, null);
			holder.mName_receive = (CircleImageView) convertView.findViewById(R.id.message_name);
			holder.mName_send = (CircleImageView) convertView.findViewById(R.id.message_name_send);
			holder.mContent_receive = (TextView) convertView.findViewById(R.id.message_content);
			holder.mContent_send = (TextView) convertView.findViewById(R.id.message_content_send);
			holder.mTime = (TextView) convertView.findViewById(R.id.message_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		UserMessageDetailEntity bean = mMessage_list.get(position);
		String path = bean.getSend_avatar();
		if (!CheckUtils.checkEmpty(path)) {
			path = FileUtils.getPhotoPath(path);
		}

//		if (!CheckUtils.checkEmpty(path)) {
//			path = path.substring(0, path.lastIndexOf(".")) + "_180_180" + path.substring(path.lastIndexOf("."));
//		}

		if (Integer.parseInt(ConstantValues.UID) == bean.getSend_id()) {
			holder.mName_receive.setVisibility(View.INVISIBLE);
			holder.mName_send.setVisibility(View.VISIBLE);
			holder.mContent_receive.setVisibility(View.INVISIBLE);
			holder.mContent_send.setVisibility(View.VISIBLE);
			holder.mContent_send.setText(bean.getContent());
			ImageViewUtil.loadimg(path, holder.mName_send, context);
		} else {
			ImageViewUtil.loadimg(path, holder.mName_receive, context);
			holder.mName_receive.setVisibility(View.VISIBLE);
			holder.mName_send.setVisibility(View.INVISIBLE);
			holder.mContent_receive.setVisibility(View.VISIBLE);
			holder.mContent_send.setVisibility(View.INVISIBLE);
			holder.mContent_receive.setText(bean.getContent());
		}
		holder.mTime.setText(bean.getAddtime());
		return convertView;
	}

	private class ViewHolder {
		public CircleImageView mName_receive;
		public CircleImageView mName_send;
		public TextView mContent_receive;
		public TextView mContent_send;
		public TextView mTime;

	}
}
