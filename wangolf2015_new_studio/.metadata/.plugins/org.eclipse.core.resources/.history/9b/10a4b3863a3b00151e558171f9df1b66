package me.wangolf.adapter;

import java.util.ArrayList;

import com.meigao.mgolf.R;

import me.wangolf.bean.practice.PracticeEntity;
import me.wangolf.practice.PracticeListActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.Xutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PracticesAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private boolean isFling;
    private ListView listView;
    private ArrayList<PracticeEntity> list = new ArrayList<PracticeEntity>();
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private PracticeListActivity activity;
    DisplayImageOptions options;
    private Context context;

    public PracticesAdapter(Context context) {
        this.context = context;
        this.activity = (PracticeListActivity) context;
    }

    public boolean isFling() {
        return isFling;
    }

    public void setFling(boolean isFling) {
        this.isFling = isFling;
    }

    public ArrayList<PracticeEntity> getList() {
        return list;
    }

    public void setList(ArrayList<PracticeEntity> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public PracticeEntity getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 加载或复用item界面
        ViewHolder holder = null;
        if (convertView == null) {
            // 无可复用item界面，加载新界面
            convertView = View.inflate(context, R.layout.item_practice, null);
            holder = new ViewHolder();
            holder.ivAlbum = (ImageView) convertView.findViewById(R.id.item_rglogo);
            holder.tvBallName = (TextView) convertView.findViewById(R.id.item_ballname);
            holder.tvDistance = (TextView) convertView.findViewById(R.id.item_distance);
            holder.tvAddress = (TextView) convertView.findViewById(R.id.item_rgaddr);
            holder.support_buy = (TextView) convertView.findViewById(R.id.support_buy);
            holder.mMobile = (ImageView) convertView.findViewById(R.id.im_mobile);
            holder.mCoach = (TextView) convertView.findViewById(R.id.tv_coach);
            holder.mLi = (LinearLayout) convertView.findViewById(R.id.li_up);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (isFling == false) {
            // 获取指定位置的数据
            PracticeEntity bean = (PracticeEntity) getItem(position);
            // 绑定数据到item界面
            holder.tvBallName.setText(bean.getRgname());
            String distance = String.valueOf(bean.getDistance());
            holder.tvDistance.setText(distance + "km");
            String address = bean.getRgaddr();
            holder.tvAddress.setText(address);
            holder.mCoach.setText(bean.getCoach() == 1 ? "有教练" : "无教练");
            holder.mMobile.setVisibility(View.GONE);
            if (!CheckUtils.checkEmpty(bean.getMin_price())) {
                holder.mLi.setVisibility(View.VISIBLE);
                holder.support_buy.setText("￥" + bean.getMin_price() + "元");
            } else {
                holder.mLi.setVisibility(View.GONE);
                holder.mMobile.setVisibility(View.VISIBLE);
                holder.mMobile.setOnClickListener(new toTel(bean));
            }
            String path = bean.getRglogo();
            if (!CheckUtils.checkEmpty(path)) {
                String[] s = path.split(",");
                path = s[0].substring(0, s[0].lastIndexOf(".")) + "_180_180" + s[0].substring(s[0].lastIndexOf("."));
            }
            Xutils.getBitmap(context, holder.ivAlbum, path);

        }
        // 返回item界面
        return convertView;
    }

    class ViewHolder {
        private ImageView ivAlbum;
        private TextView tvBallName;
        private TextView tvDistance;
        private TextView tvAddress;
        private TextView support_buy;
        private ImageView mMobile;
        private TextView mCoach;
        private LinearLayout mLi;
    }

    class toTel implements OnClickListener {
        private PracticeEntity bean;

        public toTel(PracticeEntity bean) {
            this.bean = bean;
        }

        @Override
        public void onClick(View arg0) {
            activity.onTel(bean.getMobile());
        }

    }
}
