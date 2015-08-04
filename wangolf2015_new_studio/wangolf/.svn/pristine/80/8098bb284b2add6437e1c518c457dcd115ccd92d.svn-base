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

import de.hdodenhof.circleimageview.CircleImageView;
import me.wangolf.bean.community.CommunityPraiseEntity;
import me.wangolf.bean.usercenter.UserMyFriendsEntity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.FileUtils;
import me.wangolf.utils.ImageViewUtil;

public class PostsPraiseAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<CommunityPraiseEntity> mList;

	public PostsPraiseAdapter(Context context) {
		super();
		this.context = context;
	}

	public ArrayList<CommunityPraiseEntity> getmList() {
		return mList;
	}

	public void setmList(ArrayList<CommunityPraiseEntity> mList) {
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
			convertView = View.inflate(context, R.layout.item_posts_praise, null);
			holder.mPraiseName = (TextView) convertView.findViewById(R.id.praise_name);
			holder.mPraiseAvatar = (CircleImageView) convertView.findViewById(R.id.praise_avatar);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		CommunityPraiseEntity bean = mList.get(position);
		holder.mPraiseName.setText(bean.getName());
		String path = bean.getAvatar();
		if (!CheckUtils.checkEmpty(path)) {
			path = FileUtils.getPhotoPath(path);

		}

		ImageViewUtil.loadimg(path, holder.mPraiseAvatar, context);
		return convertView;
	}

	private class ViewHolder {
		public CircleImageView mPraiseAvatar;
		public TextView mPraiseName;

	}

}
