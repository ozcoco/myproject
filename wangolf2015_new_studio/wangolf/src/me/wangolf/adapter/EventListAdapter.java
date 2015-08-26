package me.wangolf.adapter;

import java.util.List;

import com.meigao.mgolf.R;

import me.wangolf.bean.usercenter.OrBallListEntity;
import me.wangolf.usercenter.UserEventListActivity;
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
public class EventListAdapter<T> extends BaseAdapter
{

	private Context					context;

	private List<T>					mListItems;

	private UserEventListActivity	event_activity;

	private class ViewHolder
	{
		public TextView		title;
		public TextView		sn;
		public TextView		attime;
		public TextView		info;
		public TextView		status;
		public TextView		amount;
		public ImageView	icon;
		public TextView		orderCancel;

	}

	public EventListAdapter(Context context)
	{
		this.context = context;
		this.event_activity = (UserEventListActivity) context;

	}

	public List<T> getmListItems()
	{
		return mListItems;
	}

	public void setmListItems(List<T> mListItems)
	{
		this.mListItems = mListItems;
	}

	@Override
	public int getCount()
	{
		return mListItems == null ? 0 : mListItems.size();
	}

	@Override
	public Object getItem(int arg0)
	{
		return mListItems.get(arg0);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		View view = convertView;

		final ViewHolder holder;

		if (convertView == null)
		{
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

		}
		else
		{
			holder = (ViewHolder) view.getTag();
		}

		OrBallListEntity.DataEntity bean = (OrBallListEntity.DataEntity) mListItems
				.get(position);

		holder.title.setText(bean.getCommodity_name());
		holder.sn.setText("订单号：" + bean.getOut_trade_no());
		holder.attime.setText(bean.getSingle_time());
		holder.info.setText(bean.getDescription());
		holder.amount.setText("总价   ￥" + bean.getOrder_amount());
		Xutils.getBitmap(context, holder.icon, bean.getIcon());
		String num = bean.getStatus();
		String status = "";
		holder.orderCancel.setVisibility(8);
		holder.status.setBackgroundResource(R.drawable.tv_all_round_white_s);
		holder.status.setTextColor(context.getResources()
				.getColor(R.color.common_text));
		if ("1".equals(num))
		{
			status = "待付款";
			holder.status.setTextColor(context.getResources()
					.getColor(R.color.white));
			holder.status.setBackgroundResource(R.drawable.tv_all_round_orage);
			holder.status.setOnClickListener(new OrderOnClickListener(bean
					.getOut_trade_no(), bean.getOrder_amount()));
			holder.orderCancel.setVisibility(0);
			holder.orderCancel
					.setBackgroundResource(R.drawable.tv_all_round_gray_s);
			holder.orderCancel.setOnClickListener(new OrderOnClickListener(bean
					.getOut_trade_no()));
		}
		else if ("7".equals(num))
		{
			status = "已取消";
			// holder.status.setBackgroundResource(R.drawable.tv_all_round_gray);
		}
		else if ("3".equals(num))
		{
			status = "已付款";
		}
		else if ("8".equals(num))
		{
			status = "已撤消";
		}
		else if ("10".equals(num))
		{
			status = "已付款";
		}
		else
		{
			status = "确认中";
			holder.orderCancel.setVisibility(0);
			holder.orderCancel
					.setBackgroundResource(R.drawable.tv_all_round_gray_s);
			holder.orderCancel.setOnClickListener(new OrderOnClickListener(bean
					.getOut_trade_no()));
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
	class OrderOnClickListener implements OnClickListener
	{

		private String	sn;
		private String	order_amount;

		public OrderOnClickListener(String sn)
		{
			this.sn = sn;
		}

		public OrderOnClickListener(String sn, String order_amount)
		{
			super();
			this.sn = sn;
			this.order_amount = order_amount;
		}

		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
				case R.id.orderCancel:
					// 取消订单
					event_activity.toOrderCancel(sn);
					break;
				case R.id.status:
					// 支付订单
					event_activity.toPayOrder(sn, order_amount);
					break;
				default:
					break;
			}
		}
	}

}
