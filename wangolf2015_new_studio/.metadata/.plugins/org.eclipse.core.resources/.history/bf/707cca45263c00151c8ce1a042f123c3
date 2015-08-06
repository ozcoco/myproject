package me.wangolf.adapter;

import java.util.ArrayList;

import com.meigao.mgolf.R;
import me.wangolf.bean.ball.BallNameEntity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BallNamesAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<BallNameEntity> list;

	private class ViewHolder {
		public TextView ballname;
	}

	public BallNamesAdapter(Context context) {
		this.context = context;

	}

	public ArrayList<BallNameEntity> getList() {
		return list;
	}

	public void setList(ArrayList<BallNameEntity> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public BallNameEntity getItem(int position) {
		return list.get(position);
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
			view = View.inflate(context, R.layout.item_ball_names, null);

			holder = new ViewHolder();
			holder.ballname = (TextView) view.findViewById(R.id.tv_ball_name);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		BallNameEntity bean = list.get(position);
		String name = bean.getBallname();
		if (name.length() > 22) {
			name = name.substring(0, 22) + "..";
		}
		holder.ballname.setText(name);
		return view;
	}

}