package me.wangolf.adapter;


import java.util.List;

import com.lidroid.xutils.BitmapUtils;

import com.meigao.mgolf.R;

import me.wangolf.bean.shop.Basebean;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.Xutils;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 商品分类列表
 * 
 * @author Administrator
 * 
 */
public class ShopListAdapter extends BaseAdapter
{

	private LayoutInflater inflater;
	
	private Context context;
	
	private boolean isFling;
	
	private List<Basebean.DataEntity> list = null;;

	public boolean isFling()
{
		return isFling;
	}

	public void setFling(boolean isFling) 
	{
		this.isFling = isFling;
	}

	public List<Basebean.DataEntity> getList()
	{
		return list;
	}

	public void setList(List<Basebean.DataEntity> list)
	{
		this.list = list;
	}

	public ShopListAdapter(Context context)
	{
		this.inflater = LayoutInflater.from(context);
		
		this.context = context;
		
		this.isFling = false;

	}

	@Override
	public int getCount() 
	{
		int size = 0;
		
		if (list != null)
		{
			size = list.size();
		}
		
		return size;
	}

	@Override
	public Basebean.DataEntity getItem(int arg0) 
	{
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) 
	{
		return Long.valueOf(getItem(arg0).getId());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{

		// 加载或复用item界面
		ViewHolder holder = null;
		if (convertView == null) {
			// 无可复用item界面，加载新界面
			convertView = inflater.inflate(R.layout.item_pro, null);
			
			holder = new ViewHolder();
			
			holder.ivAlbum = (ImageView) convertView.findViewById(R.id.item_proimg);
			
			holder.tvproname = (TextView) convertView.findViewById(R.id.item_proname);
			
			holder.tvprooprice = (TextView) convertView.findViewById(R.id.item_prooprice);
			
			holder.tvprooprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 中划线
			
			holder.tvpronprice = (TextView) convertView.findViewById(R.id.item_pronprice);
			
			holder.tvprodis = (TextView) convertView.findViewById(R.id.item_prodis);
			
			holder.tvprocount = (TextView) convertView.findViewById(R.id.item_procount);

			convertView.setTag(holder);
			
		} 
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		if (isFling == false) 
		{
			// 获取指定位置的数据
			Basebean.DataEntity bean = (Basebean.DataEntity) getItem(position);
			// 绑定数据到item界面
			holder.tvproname.setText(bean.getSub_name());
			
			holder.tvprooprice.setText("￥" + bean.getOrigin_price());
			
			holder.tvpronprice.setText("￥" + bean.getCurrent_price());
			
			holder.tvprodis.setText(bean.getDiscount() + "折");
			
			holder.tvprocount.setText(bean.getBuy_count());

			String path = bean.getIcon();
			
			if (!CheckUtils.checkEmpty(path)) 
			{
				String[] s = path.split(",");
				
				path = s[0].substring(0, s[0].lastIndexOf(".")) + "_230" + s[0].substring(s[0].lastIndexOf("."));
			}
			
			Xutils.getBitmap(context, holder.ivAlbum, path);

			// 截取前19个字
			/*
			 * double dis=bean.getDistance(); double
			 * distance=Double.valueOf(dis); int disResult=(int)
			 * (distance/1*1E6);
			 */

			// imageLoader.displayImage(ImgUtils.getImgUrl(path,
			// ImgUtils.img230), holder.ivAlbum, options, animateFirstListener);
			// imageLoader.displayImage(ImgUtils.getImgUrl(path,
			// ImgUtils.img640db), holder.ivAlbum, options,
			// animateFirstListener);

		}
		// 返回item界面
		return convertView;
	}

	class ViewHolder
	{
		private ImageView ivAlbum;
		private TextView tvproname;
		private TextView tvprooprice;
		private TextView tvpronprice;
		private TextView tvprodis;
		private TextView tvprocount;
	}

	public void notifyDataSetChanged(List<Basebean.DataEntity> proList) 
	{
		this.list = proList;
		
		notifyDataSetChanged();
	}

	public void getBitmap(ImageView image, String url)
	{

		BitmapUtils bitmapUtils = new BitmapUtils(context);
		// 加载网络图片
		bitmapUtils.display(image, url);

		// 加载本地图片(路径以/开头， 绝对路径)
		// bitmapUtils.display(testImageView, "/sdcard/test.jpg");

		// 加载assets中的图片(路径以assets开头)
		// bitmapUtils.display(testImageView, "assets/img/wallpaper.jpg");

		// 使用ListView等容器展示图片时可通过PauseOnScrollListener控制滑动和快速滑动过程中时候暂停加载图片
		// listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils,
		// false, true));
		// listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils,

	}
}