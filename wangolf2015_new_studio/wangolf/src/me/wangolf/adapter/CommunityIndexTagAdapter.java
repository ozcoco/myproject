package me.wangolf.adapter;

/**
 * ============================================================
 * <p/>
 * 版权 ：美高传媒 版权所有 (c) 2015年3月4日
 * <p/>
 * 作者:copy
 * <p/>
 * 版本 ：1.0
 * <p/>
 * 创建日期 ： 2015年3月4日
 * <p/>
 * 描述 ：社区首页 标签
 * <p/>
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meigao.mgolf.R;

import java.util.ArrayList;

import me.wangolf.bean.community.CommunityPostsEntity;
import me.wangolf.bean.community.CommunityTagEntity;

public class CommunityIndexTagAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CommunityTagEntity> posts_tag;

    public CommunityIndexTagAdapter(Context context) {
        super();
        this.context = context;
    }

    public ArrayList<CommunityTagEntity> getPosts_tag() {
        return posts_tag;
    }

    public void setPosts_tag(ArrayList<CommunityTagEntity> posts_tag) {
        this.posts_tag = posts_tag;
    }

    @Override
    public int getCount() {
        return posts_tag == null ? 0 : posts_tag.size() > 6 ? 6 : posts_tag.size();
    }

    @Override
    public Object getItem(int position) {

        return posts_tag.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 加载或复用item界面
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_comm_tag, null);
            holder.mTag = (TextView) convertView.findViewById(R.id.tv_tag);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 设数据
        switch (position){
            case 0:
                holder.mTag.setTextColor(context.getResources().getColor(R.color.tag_0));
                break;
            case 1:
                holder.mTag.setTextColor(context.getResources().getColor(R.color.tag_1));
                break;
            case 2:
                holder.mTag.setTextColor(context.getResources().getColor(R.color.tag_2));
                break;
            case 3:
                holder.mTag.setTextColor(context.getResources().getColor(R.color.tag_3));
                break;
            case 4:
                holder.mTag.setTextColor(context.getResources().getColor(R.color.tag_4));
                break;
            case 5:
                holder.mTag.setTextColor(context.getResources().getColor(R.color.tag_5));
                break;
            default:
                break;
        }


        CommunityTagEntity bean = posts_tag.get(position);
        if (position == 5) {
            holder.mTag.setText("更多标签");
        }else {
            holder.mTag.setText(bean.getName());
        }
        return convertView;
    }

    class ViewHolder {

        private TextView mTag;

    }
}
