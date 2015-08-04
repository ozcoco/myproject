package me.wangolf.adapter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月28日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月28日
 * 
 * 描述 ：消费明细adapter
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.util.ArrayList;

import com.meigao.mgolf.R;

import me.wangolf.bean.usercenter.ConsumerDetail;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ConsumerDetailMainAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<ConsumerDetail> mListItems;

	private class ViewHolder {
		public TextView content;
		public TextView date;
		public TextView status;
		public ImageView image;
		public TextView money;

	}

	public ConsumerDetailMainAdapter(Context context) {
		this.context = context;
	}

	public ArrayList<ConsumerDetail> getmListItems() {
		return mListItems;
	}

	public void setmListItems(ArrayList<ConsumerDetail> mListItems) {
		this.mListItems = mListItems;
	}

	@Override
	public int getCount() {
		return mListItems == null ? 0 : mListItems.size();
	}

	@Override
	public ConsumerDetail getItem(int position) {
		return mListItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		final ViewHolder holder;
		if (convertView == null) {
			view = View.inflate(context, R.layout.item_custom_detail, null);
			holder = new ViewHolder();
			holder.content = (TextView) view.findViewById(R.id.content);
			holder.status = (TextView) view.findViewById(R.id.item_status);
			holder.image = (ImageView) view.findViewById(R.id.item_img);
			holder.date = (TextView) view.findViewById(R.id.item_date);
			holder.money = (TextView) view.findViewById(R.id.item_money);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		ConsumerDetail bean = mListItems.get(position);

		String type = bean.getModify();
		@SuppressWarnings("unused")
		String transtypeTxt ;
		// 根据type设置图片
		if ("0".equals(type)) {
			// 根据type设置图片
			holder.image.setImageResource(R.drawable.item_cus_zhichu);
			transtypeTxt = "支出";
			holder.money.setText("-￥" + bean.getMoney());
		} else if ("1".equals(type)) {
			holder.image.setImageResource(R.drawable.item_cus_chongzhi);
			transtypeTxt = "充值";
			holder.money.setText("+￥" + bean.getMoney());
		}
		holder.content.setText(bean.getContent());
		holder.date.setText(bean.getAddtime().substring(0, bean.getAddtime().indexOf(" ")));
		String s = bean.getStatus();
		String sta = "";
		if ("0".equals(s)) {
			sta = "交易成功";
		} else if ("1".equals(s)) {
			sta = "交易失败";
		}
		holder.status.setText(sta);

		return view;
	}

}