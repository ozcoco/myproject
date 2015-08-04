package me.wangolf.adapter;

import java.util.ArrayList;

import com.meigao.mgolf.R;
import me.wangolf.ballprac.BallInfoActivity;
import me.wangolf.bean.ball.BallDistriEntity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DistributorListAdapter extends BaseAdapter {

	private boolean isFling;
	private ArrayList<BallDistriEntity> list;
	private Context context;
	private String ballid;
	private String ballbook;
	private BallInfoActivity ballinfoactivity;

	public boolean isFling() {
		return isFling;
	}

	public void setFling(boolean isFling) {
		this.isFling = isFling;
	}

	public ArrayList<BallDistriEntity> getList() {
		return list;
	}

	public void setList(ArrayList<BallDistriEntity> list) {
		this.list = list;
	}

	private void setlist(ArrayList<BallDistriEntity> list) {
		if (list != null && list.size() > 0)
			this.list = list;
		else
			this.list = new ArrayList<BallDistriEntity>();

	}

	public DistributorListAdapter(Context context, BallInfoActivity ballinfoactivity) {
		this.context = context;
		this.ballinfoactivity = ballinfoactivity;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public BallDistriEntity getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 加载或复用item界面
		ViewHolder holder = null;
		if (convertView == null) {
			// 无可复用item界面，加载新界面
			convertView = View.inflate(context, R.layout.item_distributor, null);
			holder = new ViewHolder();
			holder.tvDName = (TextView) convertView.findViewById(R.id.item_dname);
			holder.tvService = (TextView) convertView.findViewById(R.id.item_service);
			holder.tvPrice = (TextView) convertView.findViewById(R.id.item_price);
			holder.tvOrder = (TextView) convertView.findViewById(R.id.item_order);
			holder.tvPriceBook = (TextView) convertView.findViewById(R.id.item_pricebook);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (isFling == false) {
			// 获取指定位置的数据
			BallDistriEntity bean = (BallDistriEntity) getItem(position);

			// 给预定设置监听
			holder.tvOrder.setOnClickListener(new OrderOnClickListener(bean));

			// 绑定数据到item界面
			holder.tvDName.setText(bean.getDname());
			String price = String.valueOf(bean.getPrice());
			holder.tvPrice.setText("￥" + price);
			holder.tvService.setText(bean.getService());

			// 判断是否有全额
			holder.tvPriceBook.setVisibility(View.VISIBLE);
			int pricetype = bean.getPricetype();
			String text = "";
			switch (pricetype) {
			case 2:
				text = "球场现付";
				break;
			case 3:
				text = "全额支付";
				break;
			case 4:
				text = "部分支付";
				break;

			default:
				holder.tvPriceBook.setVisibility(View.GONE);
				break;
			}
			holder.tvPriceBook.setText(text);

		}
		// 返回item界面
		return convertView;
	}

	/**
	 * 显示预定的dialog
	 * 
	 * @author Administrator
	 * 
	 */
	class OrderOnClickListener implements OnClickListener {

		private BallDistriEntity bean;

		public OrderOnClickListener(BallDistriEntity bean) {
			this.bean = bean;
		}

		@Override
		public void onClick(View arg0) {
			ballinfoactivity.toBuy(bean);

		}
	}

	class ViewHolder {
		private TextView tvDName;
		private TextView tvService;
		private TextView tvPrice;
		private TextView tvOrder;
		private TextView tvPriceBook;
	}

	public void notifyDataSetChanged(ArrayList<BallDistriEntity> distributors, String ballbook) {
		this.list = distributors;
		this.ballbook = ballbook;
		notifyDataSetChanged();
	}

}
