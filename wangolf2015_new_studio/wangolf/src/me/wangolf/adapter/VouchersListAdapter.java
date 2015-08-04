package me.wangolf.adapter;

import java.util.ArrayList;

import com.meigao.mgolf.R;
import me.wangolf.bean.usercenter.VouchersListEntity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class VouchersListAdapter extends BaseAdapter {

	private boolean isFling;
	private ArrayList<VouchersListEntity> list;
	private Context context;

	public ArrayList<VouchersListEntity> getList() {
		return list;
	}

	public void setList(ArrayList<VouchersListEntity> list) {
		this.list = list;
	}

	public boolean isFling() {
		return isFling;
	}

	public void setFling(boolean isFling) {
		this.isFling = isFling;
	}

	public VouchersListAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public VouchersListEntity getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return getItem(arg0).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 加载或复用item界面
		ViewHolder holder = null;
		if (convertView == null) {
			// 无可复用item界面，加载新界面
			convertView = View.inflate(context, R.layout.item_vouchers_list, null);
			holder = new ViewHolder();
			holder.tvmoney = (TextView) convertView.findViewById(R.id.money);
			holder.tvendtime = (TextView) convertView.findViewById(R.id.end_time);
			holder.tvsn = (TextView) convertView.findViewById(R.id.sn);
			// holder.tvuse = (TextView) convertView.findViewById(R.id.tv_use);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (isFling == false) {
			// 获取指定位置的数据
			VouchersListEntity bean = (VouchersListEntity) getItem(position);
			// 绑定数据到item界面
			holder.tvmoney.setText("￥" + bean.getMoney());
			holder.tvsn.setText("号码：" + bean.getSn());

			// tvCurrent=holder.tvuse;
			// tvCurrent.setOnClickListener(new UserVouchersListener(bean));
			/*
			 * if(type==0){
			 * tvCurrent.setBackgroundDrawable(context.getResources(
			 * ).getDrawable(R.drawable.draw_orage)); tvCurrent.setText("可使用");
			 * }else{
			 * tvCurrent.setBackgroundDrawable(context.getResources().getDrawable
			 * (R.drawable.bg_clor_gray)); tvCurrent.setText("不可用"); }
			 */

			String starttime = bean.getBegin_time();
			String endtime = bean.getEnd_time();
			/*
			 * Date date = null; if(endtime!=null){ long
			 * time=Long.parseLong(endtime); date=new Date(time); }
			 */
			try {
				// holder.tvendtime.setText("使用期限:"+endtime==null?"长期有效":DateFormatUtils.format(date));
				holder.tvendtime.setText("使用期限:" + (endtime == null ? "长期有效" : starttime + "至" + endtime));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 返回item界面
		return convertView;
	}

	class ViewHolder {
		private TextView tvsn;
		private TextView tvmoney;
		private TextView tvendtime;
		private TextView tvuse;
	}
}
