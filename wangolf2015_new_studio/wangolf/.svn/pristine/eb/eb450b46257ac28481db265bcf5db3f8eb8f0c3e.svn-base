package me.wangolf.base;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class Mo_BaseAdapter<T, V> extends BaseAdapter {

	private Context context;
	private List<T> list;
	private V view;

	public Mo_BaseAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mo_BaseAdapter(Context context, List<T> list) {
		super();
		this.context = context;
		this.list = list;
	}

	public Mo_BaseAdapter(Context context, List<T> list, V view) {
		super();
		this.context = context;
		this.list = list;
		this.view = view;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView,
			ViewGroup parent);

}
