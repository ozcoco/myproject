package me.wangolf.adapter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月5日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月5日
 * 
 * 描述 ：  教练list_adapter
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.util.ArrayList;
import com.meigao.mgolf.R;
import me.wangolf.bean.college.CoachListEntity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.ImageViewUtil;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.lidroid.xutils.BitmapUtils;
/**
 * 教练列表
 * 
 */
public class CoachListAdapter extends BaseAdapter {

	private boolean isFling;
	private Context context;
	private ArrayList<CoachListEntity> list = new ArrayList<CoachListEntity>();

	public CoachListAdapter(Context context) {
		this.context = context;
	}

	public boolean isFling() {
		return isFling;
	}

	public void setFling(boolean isFling) {
		this.isFling = isFling;
	}

	public ArrayList<CoachListEntity> getList() {
		return list;
	}

	public void setList(ArrayList<CoachListEntity> list) {
		this.list = list;
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
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		// 加载或复用item界面
		ViewHolder holder = null;
		if (view == null) {
			// 无可复用item界面，加载新界面
			view = View.inflate(context, R.layout.item_coachlist, null);
			holder = new ViewHolder();
			holder.ivAlbum = (de.hdodenhof.circleimageview.CircleImageView) view.findViewById(R.id.item_proimg);
			holder.tvname = (TextView) view.findViewById(R.id.item_name);
			holder.tvdistance = (TextView) view.findViewById(R.id.item_distance);
			holder.tvsex = (TextView) view.findViewById(R.id.item_sex);
			holder.tvpraise = (TextView) view.findViewById(R.id.item_praise);
			holder.tvbadges = (TextView) view.findViewById(R.id.item_badges);
			holder.tvballage = (TextView) view.findViewById(R.id.item_ballage);
			holder.tvprice = (TextView) view.findViewById(R.id.item_price);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if (isFling == false) {
			// 获取指定位置的数据
			// 绑定数据到item界面
			CoachListEntity bean = list.get(position);
			holder.tvname.setText(bean.getUsername());
			holder.tvdistance.setText(bean.getDistance());
			// getBitmap((ImageView)holder.ivAlbum, bean.getPhoto());
			String path_1 = bean.getPhoto();
			if (!CheckUtils.checkEmpty(path_1)) {
				path_1 = path_1.substring(0, path_1.lastIndexOf(".")) + "_180_180" + path_1.substring(path_1.lastIndexOf("."));
			}
			ImageViewUtil.loadimg(path_1, (ImageView) holder.ivAlbum, context);
			String sextext = "";
			int sex = bean.getSex();
			switch (sex) {
			case -1:
				sextext = "未知";
				break;
			case 0:
				sextext = "女";
				break;
			case 1:
				sextext = "男";
				break;

			default:
				break;
			}
			holder.tvsex.setText(sextext);
			holder.tvpraise.setText(bean.getPraise());
			holder.tvbadges.setText(bean.getBadges());
			holder.tvballage.setText(bean.getBallage());
			String priceText = bean.getPrice();
			holder.tvprice.setText(priceText);
			String path = bean.getPhoto();
			String[] s = path.split(",");
			path = s[0];

		}
		// 返回item界面
		return view;
	}

	class ViewHolder {
		private de.hdodenhof.circleimageview.CircleImageView ivAlbum;
		private TextView tvname;
		private TextView tvdistance;
		private TextView tvsex;
		private TextView tvpraise;
		private TextView tvbadges;
		private TextView tvballage;
		private TextView tvprice;
	}

	public void getBitmap(ImageView image, String url) {

		BitmapUtils bitmapUtils = new BitmapUtils(context);
		// 加载网络图片
		bitmapUtils.display(image, url);
	}
}
