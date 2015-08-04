package me.wangolf.adapter;

import java.util.ArrayList;

import com.meigao.mgolf.R;
import me.wangolf.bean.usercenter.RedPackEntity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 红包列表适配器
 * 
 */
public class RedPackListAdapter extends BaseAdapter {

	private boolean isFling;
	private ArrayList<RedPackEntity> list;
	// 颜色值数组
	// private int[]
	private int[] colors = { R.color.redpackcolor0, R.color.redpackcolor1, R.color.redpackcolor2, R.color.redpackcolor3, R.color.txt_gray };
	private Context context;

	public boolean isFling() {
		return isFling;
	}

	public void setFling(boolean isFling) {
		this.isFling = isFling;
	}

	public RedPackListAdapter(Context context) {
		this.isFling = false;
		this.context = context;
	}

	public ArrayList<RedPackEntity> getList() {
		return list;
	}

	public void setList(ArrayList<RedPackEntity> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public RedPackEntity getItem(int arg0) {
		return list==null?null:list.get(arg0);
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
			convertView = View.inflate(context, R.layout.item_redpacklist_new, null);
			holder = new ViewHolder();
			holder.mInfo = (TextView) convertView.findViewById(R.id.tv_info);
			holder.mDate = (TextView) convertView.findViewById(R.id.tv_date);
			holder.mPrice = (TextView) convertView.findViewById(R.id.tv_price);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		RedPackEntity bean = list.get(position);
		holder.mInfo.setText(bean.getInfo());
		holder.mDate.setText(bean.getAddtime());
		holder.mPrice.setText("+￥"+bean.getPrice()+"");
		// 返回item界面
		return convertView;
	}

	/**
	 * 分享红包
	 * 
	 * @param bean
	 * @return
	 */
	private OnClickListener toShareRedPack(final RedPackEntity bean) {
		return new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (bean.getStatus() == 1) {
					return;
				}
				// Intent redPackIntent = new Intent(context,
				// OptRedPackDialog.class);
				// redPackIntent.putExtra("packsid", bean.getId());
				// redPackIntent.putExtra("packprice", bean.getPrice());
				// redPackIntent.putExtra("from", 1);// 表示来自红包列表的红包分享
				// context.startActivity(redPackIntent);
			}
		};
	}

	class ViewHolder {
		private LinearLayout layoutleft;
		private TextView mInfo;
		private TextView mDate;
		private TextView mPrice;
		private TextView tvdate;
	}

}
