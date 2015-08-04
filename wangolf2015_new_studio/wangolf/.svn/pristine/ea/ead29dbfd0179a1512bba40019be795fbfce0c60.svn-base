package me.wangolf.adapter;

import java.util.ArrayList;

import com.meigao.mgolf.R;

import me.wangolf.bean.event.PublishEventEntity;
import me.wangolf.usercenter.OrderListActivity;
import me.wangolf.utils.Xutils;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 订单列表
 * 
 * @author Administrator
 * @param <T>
 * 
 */
@SuppressLint("ResourceAsColor")
public class MyEventListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<PublishEventEntity> mListItems;
	private OrderListActivity order_activity;

	private class ViewHolder {
		public TextView title;
		public TextView sn;
		public TextView attime;
		public TextView info;
		public TextView status;
		public TextView amount;
		public ImageView icon;
		public TextView orderCancel;

	}

	public MyEventListAdapter(Context context) {
		this.context = context;

	}

	public ArrayList<PublishEventEntity> getmListItems() {
		return mListItems;
	}

	public void setmListItems(ArrayList<PublishEventEntity> mListItems) {
		this.mListItems = mListItems;
	}

	@Override
	public int getCount() {
		return mListItems == null ? 0 : mListItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mListItems.get(arg0);
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
			view = View.inflate(context, R.layout.item_order_list, null);
			holder = new ViewHolder();
			holder.title = (TextView) view.findViewById(R.id.title);
			holder.sn = (TextView) view.findViewById(R.id.sn);
			holder.attime = (TextView) view.findViewById(R.id.attime);
			holder.info = (TextView) view.findViewById(R.id.info);
			holder.status = (TextView) view.findViewById(R.id.status);
			holder.amount = (TextView) view.findViewById(R.id.amount);
			holder.icon = (ImageView) view.findViewById(R.id.icon);
			holder.orderCancel = (TextView) view.findViewById(R.id.orderCancel);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		PublishEventEntity bean = (PublishEventEntity) mListItems.get(position);
		holder.title.setText(bean.getName());
		holder.attime.setText(bean.getEvent_begin_time());
		holder.info.setText(bean.getInfo());
		holder.amount.setText("总价   ￥" + bean.getPrice());
		Xutils.getBitmap(context, holder.icon, bean.getIcon());
		String num = bean.getStatus();
		String status = "";
		holder.orderCancel.setVisibility(8);
		holder.status.setBackgroundResource(R.drawable.tv_all_round_white_s);
		holder.status.setTextColor(context.getResources().getColor(R.color.common_text));
		if ("1".equals(num)) {
			status = "待付款";
			holder.status.setTextColor(context.getResources().getColor(R.color.white));
			holder.status.setBackgroundResource(R.drawable.tv_all_round_orage);
			holder.orderCancel.setVisibility(0);
			holder.orderCancel.setBackgroundResource(R.drawable.tv_all_round_gray_s);

		} else if ("7".equals(num)) {
			status = "已取消";
			// holder.status.setBackgroundResource(R.drawable.tv_all_round_gray);
		} else if ("3".equals(num)) {
			status = "已付款";
		} else if ("8".equals(num)) {
			status = "已撤消";
		} else if ("10".equals(num)) {
			status = "已付款";
		} else {
			status = "审核中";
			holder.orderCancel.setVisibility(0);
			//holder.orderCancel.setBackgroundResource(R.drawable.tv_all_round_gray_s);
		}
		holder.status.setText(status);

		return view;
	}

	/**
	 * 取消或支付订单
	 * 
	 * @author Administrator
	 * 
	 */
	class OrderOnClickListener implements OnClickListener {

		private String sn;
		private String order_amount;

		public OrderOnClickListener(String sn) {
			this.sn = sn;
		}

		public OrderOnClickListener(String sn, String order_amount) {
			super();
			this.sn = sn;
			this.order_amount = order_amount;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.orderCancel:
				// 取消订单
				order_activity.toOrderCancel(sn);
				break;
			case R.id.status:
				// 支付订单
				order_activity.toPayOrder(sn, order_amount);
				break;
			default:
				break;
			}
		}
	}

}