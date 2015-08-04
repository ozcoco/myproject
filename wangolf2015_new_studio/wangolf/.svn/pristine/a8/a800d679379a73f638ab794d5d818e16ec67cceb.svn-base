package me.wangolf.adapter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 上午11:26:19
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 上午11:26:19
 * 
 * 描述 ：用户相册adapter
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.util.ArrayList;

import me.wangolf.bean.usercenter.UserInfoNewEntity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.Xutils;

import com.meigao.mgolf.R;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class UserImagesAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<UserInfoNewEntity> mList = new ArrayList<UserInfoNewEntity>();

	public UserImagesAdapter(Context context) {
		super();
		this.context = context;
	}

	public ArrayList<UserInfoNewEntity> getmList() {
		return mList;
	}

	public void setmList(ArrayList<UserInfoNewEntity> mList) {
		this.mList = mList;
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {


		UserInfoNewEntity bean = mList.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_user_images, null);
			holder.mAvatar = (ImageView) convertView.findViewById(R.id.c_d_avatar);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String path = bean.getUrl();
		if (!CheckUtils.checkEmpty(path)) {
			path = path.substring(0, path.lastIndexOf(".")) + "_180_180" + path.substring(path.lastIndexOf("."));
		}
		//ImageViewUtil.loadimg(path, holder.mAvatar, context);
		Xutils.getBitmap(context, holder.mAvatar, path);
		return convertView;
	}

	private class ViewHolder {
		public ImageView mAvatar;

	}
}
