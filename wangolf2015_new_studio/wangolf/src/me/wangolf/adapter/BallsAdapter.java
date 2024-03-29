package me.wangolf.adapter;

import java.util.ArrayList;
import java.util.List;

import com.meigao.mgolf.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import me.wangolf.bean.ball.BallDetailEntity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.Xutils;

public class BallsAdapter extends BaseAdapter {

	private boolean isFling;
	
	private List<BallDetailEntity.DataEntity> list = new ArrayList<BallDetailEntity.DataEntity>();
	
	private Context context;

	public boolean isFling() 
	{
		return isFling;
	}

	public void setFling(boolean isFling) 
	{
		this.isFling = isFling;
	}

	public BallsAdapter(Context context) 
	{
		this.isFling = false;
		
		this.context = context;
	}

	public List<BallDetailEntity.DataEntity> getList() 
	{
		return list;
	}

	public void setList(List<BallDetailEntity.DataEntity> list) 
	{
		this.list = list;
	}

	@Override
	public int getCount() 
	{
		return list == null ? 0 : list.size();
	}

	@Override
	public BallDetailEntity.DataEntity getItem(int arg0) 
	{
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) 
	{
		return Long.valueOf(getItem(arg0).getId());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 加载或复用item界面
		ViewHolder holder = null;
		
		if (convertView == null)
		{
			// 无可复用item界面，加载新界面
			convertView = View.inflate(context, R.layout.item_ball, null);
			
			holder = new ViewHolder();
			
			holder.ivAlbum = (ImageView) convertView.findViewById(R.id.item_img);
			
			holder.tvBallName = (TextView) convertView.findViewById(R.id.item_ballname);
			
			holder.tvDistance = (TextView) convertView.findViewById(R.id.item_distance);
			
			holder.tvPrice = (TextView) convertView.findViewById(R.id.item_price);
			
			holder.tvCourtdata = (TextView) convertView.findViewById(R.id.item_courtdata);
			
			holder.tvAddress = (TextView) convertView.findViewById(R.id.item_address);

			convertView.setTag(holder);
			
		} 
		else 
		{
			holder = (ViewHolder) convertView.getTag();
		}

		
		if (isFling == false) 
		{
			// 获取指定位置的数据
			BallDetailEntity.DataEntity bean = getItem(position);
			// 绑定数据到item界面
			holder.tvBallName.setText(bean.getCourt_name());
			
			String price = String.valueOf(bean.getPrice());
			
			holder.tvPrice.setText("￥" + price);
			
			String distance = String.valueOf(bean.getDistance());
			
			holder.tvCourtdata.setText(bean.getCourt_data());
			
			holder.tvAddress.setText(bean.getAddress());
			
			holder.tvDistance.setText(String.valueOf(distance));
			
			String path = bean.getIcon();
			
//			if (!CheckUtils.checkEmpty(path)) 
//			{
//				path = path.substring(0, path.lastIndexOf(".")) + "_180_180" + path.substring(path.lastIndexOf("."));
//			}
			
			Xutils.getBitmap(context, holder.ivAlbum, path); // 拿图片

		}
		// 返回item界面
		return convertView;
	}

	
	class ViewHolder
	{
		private ImageView ivAlbum;
		private TextView tvBallName;
		private TextView tvDistance;
		private TextView tvPrice;
		private TextView tvCourtdata;
		private TextView tvAddress;
	}

}
