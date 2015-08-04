package me.wangolf.adapter;

import java.util.ArrayList;

import com.meigao.mgolf.R;
import me.wangolf.bean.event.EventJoinPersonsEntity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventJoinPersonsAdapter extends BaseAdapter {

	private ArrayList<EventJoinPersonsEntity> list;
	private Context context;

	public EventJoinPersonsAdapter(Context context) {
		this.context = context;
	}

	public ArrayList<EventJoinPersonsEntity> getList() {
		return list;
	}

	public void setList(ArrayList<EventJoinPersonsEntity> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public EventJoinPersonsEntity getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_event_joinpersons_list, null);
			holder.tvname = (TextView) convertView.findViewById(R.id.item_name);
			holder.mobile = (TextView) convertView.findViewById(R.id.item_mobile);
			holder.tvsex = (TextView) convertView.findViewById(R.id.item_sex);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		EventJoinPersonsEntity bean = list.get(position);
		holder.tvname.setText(bean.name);
		holder.mobile.setText(bean.mobile);
		String sex = "";
		if ("-1".equals(bean.sex)) {
			sex = "未知";
		} else if ("0".equals(bean.sex)) {
			sex = "女";
		} else if ("1".equals(bean.sex)) {
			sex = "男";
		}
		holder.tvsex.setText(sex);

		return convertView;
	}

	class ViewHolder {
		TextView tvname;
		TextView mobile;
		TextView tvsex;
	}

	public void notifyDataSetChanged(ArrayList<EventJoinPersonsEntity> personlist) {
		this.list = personlist;
		notifyDataSetChanged();
	}
}
