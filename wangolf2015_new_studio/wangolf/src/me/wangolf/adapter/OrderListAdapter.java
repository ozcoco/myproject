package me.wangolf.adapter;

import java.util.List;

import com.meigao.mgolf.R;

import me.wangolf.bean.usercenter.OrBallListEntity;
import me.wangolf.bean.usercenter.OrBallListEntity.DataEntity;
import me.wangolf.usercenter.OrderListActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.Xutils;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
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
public class OrderListAdapter<T> extends BaseAdapter
{

	private Context				context;

	private List<T>				mListItems;

	private OrderListActivity	order_activity;

	
	public OrderListAdapter(Context context)
	{
		this.context = context;

		this.order_activity = (OrderListActivity) context;

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

	@SuppressWarnings("unchecked")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		
		ViewHolder holder = null;
		
		if (convertView == null)
		{
			convertView = View.inflate(context, R.layout.item_order_list, null);
			
			holder = new ViewHolder();
			
			holder.title = (TextView) convertView.findViewById(R.id.title);
			
			holder.sn = (TextView) convertView.findViewById(R.id.sn);
			
			holder.attime = (TextView) convertView.findViewById(R.id.attime);
			
			holder.info = (TextView) convertView.findViewById(R.id.info);
			
			holder.status = (TextView) convertView.findViewById(R.id.status);
			
			holder.amount = (TextView) convertView.findViewById(R.id.amount);
			
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			
			holder.orderCancel = (TextView) convertView.findViewById(R.id.orderCancel);
			
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		OrBallListEntity.DataEntity bean = (DataEntity) mListItems
				.get(position);

		holder.title.setText(bean.getCommodity_name());

		holder.sn.setText("订单号：" + bean.getOut_trade_no());

		holder.attime.setText(bean.getSingle_time());

		holder.info.setText(bean.getDescription());

		holder.amount.setText("总价   ￥" + bean.getOrder_amount());

		String path_1 = bean.getIcon();

		Log.i("图片网址", path_1);
		
		if (!CheckUtils.checkEmpty(path_1))
		{
			path_1 = path_1.substring(0, path_1.lastIndexOf(".")) + "_180_180" + path_1
					.substring(path_1.lastIndexOf("."));
		}

		Xutils.getBitmap(context, holder.icon, path_1);

		String num = bean.getStatus();

		String status = "";

		holder.orderCancel.setVisibility(View.VISIBLE);

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

		return convertView;
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
	
}
