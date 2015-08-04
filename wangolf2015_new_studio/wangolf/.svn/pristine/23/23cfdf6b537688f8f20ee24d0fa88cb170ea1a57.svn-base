package me.wangolf.adapter;

import com.meigao.mgolf.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ShopColorAdapter extends BaseAdapter {
	private Context context;
	private String[] color;
	private int clickTemp = -1;

	public ShopColorAdapter(Context context, String[] color) {
		super();
		this.context = context;
		this.color = color;
	}

	public int getClickTemp() {
		return clickTemp;
	}

	public void setClickTemp(int clickTemp) {
		this.clickTemp = clickTemp;
	}

	@Override
	public int getCount() {

		return color.length;
	}

	@Override
	public Object getItem(int arg0) {

		return color[arg0];
	}

	@Override
	public long getItemId(int arg0) {

		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		arg1 = View.inflate(context, R.layout.shopcolor, null);
		TextView tv_color = (TextView) arg1.findViewById(R.id.shop_color);
		tv_color.setText(color[arg0]);
		int bottom = tv_color.getPaddingBottom();
		int top = tv_color.getPaddingTop();
		int right = tv_color.getPaddingRight();
		int left = tv_color.getPaddingLeft();
		if (clickTemp == arg0) {
			tv_color.setBackgroundResource(R.drawable.bg_select);
			tv_color.setTextColor(context.getResources().getColor(R.color.select_color));
		} else {
			// tv_color.setBackgroundColor();
		}
		tv_color.setPadding(left, top, right, bottom);
		return arg1;
	}
}
