package me.wangolf.adapter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月6日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月6日
 * 
 * 描述 ： 学院---学院adapter
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.util.ArrayList;
import com.meigao.mgolf.R;
import me.wangolf.bean.college.CollegeListEntity;
import me.wangolf.utils.Xutils;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CollegeListAdapter extends BaseAdapter {

	private boolean isFling;
	private ArrayList<CollegeListEntity> list = new ArrayList<CollegeListEntity>();
	private Context context;

	public CollegeListAdapter(Context context) {
		this.context = context;

	}

	public ArrayList<CollegeListEntity> getList() {
		return list;
	}

	public void setList(ArrayList<CollegeListEntity> list) {
		this.list = list;
	}

	public boolean isFling() {
		return isFling;
	}

	public void setFling(boolean isFling) {
		this.isFling = isFling;
	}

	@Override
	public int getCount() {
		int size = 0;
		if (list.size() > 0) {
			size = list.size();
		}
		return size;
	}

	@Override
	public Object getItem(int arg0) {

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
			convertView = View.inflate(context, R.layout.item_collegelist, null);
			holder = new ViewHolder();
			holder.ivAlbum = (ImageView) convertView.findViewById(R.id.item_proimg);
			holder.tvname = (TextView) convertView.findViewById(R.id.item_name);
			holder.tvdistance = (TextView) convertView.findViewById(R.id.item_distance);
			holder.tvaddress = (TextView) convertView.findViewById(R.id.item_address);
			holder.tvcoachnum = (TextView) convertView.findViewById(R.id.item_coachnum);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (isFling == false) {
			// 获取指定位置的数据
			CollegeListEntity bean = (CollegeListEntity) getItem(position);
			// 绑定数据到item界面

			holder.tvname.setText(bean.getName());
			holder.tvdistance.setText(bean.getDistance());
			String address = bean.getAddress();
			holder.tvaddress.setText(address);
			holder.tvcoachnum.setText(bean.getCoachnumber() + "名教练");
			String path = bean.getPhoto();
			if (path != null) {
				path = path.substring(0, path.lastIndexOf(".")) + "_180_180" + path.substring(path.lastIndexOf("."));
			}
			Xutils.getBitmap(context, holder.ivAlbum, path);
		}
		// 返回item界面
		return convertView;
	}

	class ViewHolder {
		private ImageView ivAlbum;
		private TextView tvname;
		private TextView tvdistance;
		private TextView tvaddress;
		private TextView tvcoachnum;
	}

}
