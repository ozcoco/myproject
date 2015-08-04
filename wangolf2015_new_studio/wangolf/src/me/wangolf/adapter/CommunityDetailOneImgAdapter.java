package me.wangolf.adapter;

import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.Xutils;

import com.meigao.mgolf.R;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CommunityDetailOneImgAdapter extends BaseAdapter {
    private Context context;
    private String[] img;

    public CommunityDetailOneImgAdapter(Context context) {
        super();
        this.context = context;
    }

    public CommunityDetailOneImgAdapter(Context context, String[] img) {
        super();
        this.context = context;
        this.img = img;
    }

    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return img.length;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 加载或复用item界面
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_comm_detail_one_img, null);
            holder.mImg = (ImageView) convertView.findViewById(R.id.c_d_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String path = img[position];
        if (!CheckUtils.checkEmpty(path)) {
            path = path.substring(0, path.lastIndexOf(".")) + "_180_180" + path.substring(path.lastIndexOf("."));
        }

        Xutils.getBitmap(context, holder.mImg, path);
        return convertView;
    }

    class ViewHolder {
        private ImageView mImg;
    }
}
