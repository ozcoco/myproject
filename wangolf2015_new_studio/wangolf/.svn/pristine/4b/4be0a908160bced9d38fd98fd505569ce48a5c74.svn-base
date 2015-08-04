package me.wangolf.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.meigao.mgolf.R;
import me.wangolf.bean.event.EventSend2DataEntity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class EveSend2LvCBAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Map<String, Object>> mData;
	private ArrayList<EventSend2DataEntity> list;
	public static Map<Integer, Boolean> isSelected;

	public EveSend2LvCBAdapter(Context context) {
		this.context = context;
		mData = new ArrayList<Map<String, Object>>();

		// 这儿定义isSelected这个map是记录每个listitem的状态，初始状态全部为false。
		isSelected = new HashMap<Integer, Boolean>();

	}

	public ArrayList<EventSend2DataEntity> getList() {
		return list;
	}

	public void setList(ArrayList<EventSend2DataEntity> list) {
		this.list = list;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				// 如过不是固定的选项（前5个之外）
				if (i < 3) {
					isSelected.put(i, true);
				} else {
					isSelected.put(i, false);
				}
			}
		}
	}

	public ArrayList<Map<String, Object>> getmData() {
		return mData;
	}

	public void setmData(ArrayList<Map<String, Object>> mData) {
		this.mData = mData;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		// convertView为null的时候初始化convertView。
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_listview_checkbox, null);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			if (position < 3) {
				holder.title.setTextColor(R.color.txt_gray);
			}
			holder.cBox = (CheckBox) convertView.findViewById(R.id.cb);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText(list.get(position).getName());
		holder.cBox.setChecked(isSelected.get(position));
		return convertView;
	}

	public final class ViewHolder {
		// public ImageView img;
		public TextView title;
		public CheckBox cBox;
	}
}