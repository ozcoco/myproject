package me.wangolf.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.meigao.mgolf.R;

import me.wangolf.bean.usercenter.AddressBean;
import me.wangolf.bean.usercenter.RespUserAdrrEntity;
import me.wangolf.dao.CityDao;
import me.wangolf.usercenter.AddressEditActivity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class AddressAdapter extends BaseAdapter {

	DisplayImageOptions options;
	/*
	 * private ImageLoadingListener animateFirstListener = new
	 * AnimateFirstDisplayListener(); protected ImageLoader imageLoader =
	 * ImageLoader.getInstance();
	 */
	private Context context;
	private ArrayList<RespUserAdrrEntity> list = new ArrayList<RespUserAdrrEntity>();
	private String procitxian;

	private class ViewHolder {
		public TextView consignee;
		public TextView phone;
		public TextView address;
		public TextView addressDetail;
		public TextView edit;
		// public TextView zip;
		public ImageView iv;

	}

	public ArrayList<RespUserAdrrEntity> getList() {
		return list;
	}

	public void setList(ArrayList<RespUserAdrrEntity> list) {
		this.list = list;
	}

	public AddressAdapter(Context context) {
		this.context = context;

	}

	@Override
	public int getCount() {
		return list.size() == 0 ? 0 : list.size();
	}

	@Override
	public RespUserAdrrEntity getItem(int position) {
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
			view = View.inflate(context, R.layout.item_order_address_list, null);

			holder = new ViewHolder();
			holder.consignee = (TextView) view.findViewById(R.id.consignee);
			holder.phone = (TextView) view.findViewById(R.id.phone);
			holder.address = (TextView) view.findViewById(R.id.address);
			holder.addressDetail = (TextView) view.findViewById(R.id.addressDetail);
			// holder.zip = (TextView) view.findViewById(R.id.zip);
			holder.edit = (TextView) view.findViewById(R.id.edit);
			holder.iv = (ImageView) view.findViewById(R.id.iv);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		RespUserAdrrEntity bean = list.get(position);
		holder.consignee.setText(bean.getConsignee() == null ? "" : bean.getConsignee());
		holder.phone.setText(bean.getMobile() == null ? "" : bean.getMobile());
		HashMap<String, String> map = new CityDao(context).getResUserAdrr(bean.getRid1(), bean.getRid2(), bean.getRid3(), bean.getRid4());
		String provice = map.get("province") == null ? "" : map.get("province");
		String city = map.get("city") == null ? "" : map.get("city");
		String xian = map.get("xian") == null ? "" : map.get("xian");
		procitxian = provice + " " + city + " " + xian;
		holder.address.setText(procitxian);
		String detail = bean.getAddress();
		holder.addressDetail.setText(detail);
		// holder.zip.setText(bean.getZip()==null?"":bean.getZip());
		if (bean.getType().equals("1")) {// 显示默认标示图
			holder.iv.setVisibility(View.VISIBLE);
			// 保存默认地址
			saveAdress(bean);
		} else {
			holder.iv.setVisibility(View.GONE);
		}

		final View v = holder.edit;
		v.setClickable(true);
		v.setTag(bean);
		v.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("static-access")
			@Override
			public void onClick(View arg0) {
				// 跳转到编辑界面
				// ((AddressListActivity) context).flag = true;
				Intent Intent = new Intent(context, AddressEditActivity.class);
				Intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				RespUserAdrrEntity beans = (RespUserAdrrEntity) v.getTag();
				AddressBean bean = new AddressBean();
				bean.setAid(beans.getId() + "");
				bean.setRid2(beans.getRid2());
				bean.setRid3(beans.getRid3());
				bean.setRid4(beans.getRid4());
				bean.setAddress(beans.getAddress());
				bean.setZip(beans.getZip());
				bean.setMobile(beans.getMobile());
				bean.setType(beans.getType());
				bean.setConsignee(beans.getConsignee());
				Intent.putExtra("respUserAdrr", bean);
				context.startActivity(Intent);

			}
		});

		// imageLoader.displayImage(imageUrl, holder.image, options,
		// animateFirstListener);

		return view;
	}

	/**
	 * 保存用户地址
	 * 
	 * @param bean
	 */
	private void saveAdress(RespUserAdrrEntity bean) {
		// new UserBiz(context).saveUserdefaultAddress(bean);

	}

}