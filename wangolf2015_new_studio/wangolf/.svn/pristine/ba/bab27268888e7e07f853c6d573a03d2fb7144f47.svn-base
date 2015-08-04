package me.wangolf.adapter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月6日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月6日
 * 
 * 描述 ：学院通用adapter 如装备、技巧、教学
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.util.ArrayList;
import com.lidroid.xutils.BitmapUtils;
import com.meigao.mgolf.R;


import me.wangolf.bean.college.KnowledgeEntity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.Xutils;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CollegeAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<KnowledgeEntity> list;

	public ArrayList<KnowledgeEntity> getList() {
		return list;
	}

	public void setList(ArrayList<KnowledgeEntity> list) {
		this.list = list;
	}

	public CollegeAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		int size = 0;
		if (list != null) {
			size = list.size();
		}
		return size;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_begin_list, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.item_title);
			holder.brief = (TextView) convertView.findViewById(R.id.item_price);
			holder.brief.setTextColor(R.color.txt_gray);
			holder.brief.setGravity(Gravity.LEFT);
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
			holder.palyicon = (ImageView) convertView.findViewById(R.id.iv_play);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		KnowledgeEntity bean = list.get(position);
		if (!bean.getVideoimg().equals("")) {
			String path = bean.getVideoimg();
			String[] s = path.split(",");
			path = s[0].substring(0, s[0].lastIndexOf(".")) + "_180_180" + s[0].substring(s[0].lastIndexOf("."));
			Xutils.getBitmap(context, holder.image, path);
			holder.palyicon.setVisibility(0); // 显示视频图标
		} else {
			String path = bean.getLogo();

			if (!CheckUtils.checkEmpty(path)) {
				String[] s = path.split(",");
				path = s[0].substring(0, s[0].lastIndexOf(".")) + "_180_180" + s[0].substring(s[0].lastIndexOf("."));
			}
			Xutils.getBitmap(context, holder.image, path);
			holder.palyicon.setVisibility(8);
		}
		holder.title.setText(bean.getTitle());
		String brief = bean.getBrief();
		if (brief.length() >= 28) {
			brief = brief.trim().substring(0, 28) + "...";
		}
		holder.brief.setText(brief);
		return convertView;
	}

	private class ViewHolder {
		public TextView title;
		public TextView brief;
		public ImageView image;
		public ImageView palyicon;

	}

	public void getBitmap(ImageView image, String url) {

		BitmapUtils bitmapUtils = new BitmapUtils(context);
		// 加载网络图片
		bitmapUtils.display(image, url);
	}
}