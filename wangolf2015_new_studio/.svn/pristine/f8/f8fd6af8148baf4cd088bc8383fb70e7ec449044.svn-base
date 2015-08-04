package me.wangolf.community;

import java.util.ArrayList;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.base.BaseActivity;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.viewUtils.RollViewPager_NORoll_Community;
import me.wangolf.utils.viewUtils.RollViewPager_NORoll_Community.OnPagerClickCallback;

public class CommunityImgActivit extends BaseActivity implements OnClickListener {

    @ViewInject(R.id.common_back)
    private Button common_back; // 后退
    @ViewInject(R.id.common_title)
    private TextView common_title; // 标题
    private RollViewPager_NORoll_Community Rpage; //
    private ArrayList<View> dotList; // 链接
    @ViewInject(R.id.dots_ll)
    private LinearLayout dots_ll; // 填充滚动viewpage点
    @ViewInject(R.id.shop_viewpager)
    private LinearLayout shop_viewpager; // 滚动图
    private ArrayList<String> titles;
    private ArrayList<String> urlList = new ArrayList<String>();
    private int setCurrentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_img);
        ViewUtils.inject(this);
        initData();
    }

    public void initData() {
        common_title.setText(ConstantValues.SHOP_IMG);
        common_back.setVisibility(View.VISIBLE);// 后退键可见
        common_back.setOnClickListener(this);
        urlList = getIntent().getStringArrayListExtra("url"); // 拿图片链接
        setCurrentItem = Integer.parseInt(getIntent().getStringExtra("setCurrentItem"));
        initDot(urlList.size());
        initRoll();
    }

    // ================滚动图片数据============= 滚动图初始化数据
    public void initRoll() {

        Rpage = new RollViewPager_NORoll_Community(getApplicationContext(), dotList, R.drawable.dot_focus, R.drawable.dot_normal, new OnPagerClickCallback() {
            @Override
            public void onPagerClick(int position) {

            }
        });
        Rpage.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        titles = new ArrayList<String>();

        // Rpage.setTitle(chzz_news_roll_title, titles);//标题
        Rpage.setUriList(urlList);
        Rpage.startRoll();
        Rpage.setCurrentItem(setCurrentItem);
        shop_viewpager.removeAllViews(); // 使用前先清除所有的内容view
        shop_viewpager.addView(Rpage);
    }

    // ===========滚动图片view=============
    private void initDot(int size) {
        dotList = new ArrayList<View>();
        dots_ll.removeAllViews();
        for (int i = 0; i < size; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CommonUtil.dip2px(getApplicationContext(), 6), CommonUtil.dip2px(
                    getApplicationContext(), 6));
            params.setMargins(5, 0, 5, 0);
            View m = new View(getApplicationContext());
            if (i == 0) {
                m.setBackgroundResource(R.drawable.dot_focus);
            } else {
                m.setBackgroundResource(R.drawable.dot_normal);
            }
            m.setLayoutParams(params);
            dots_ll.addView(m);
            dotList.add(m);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_back:
                finish();
                break;

            default:
                break;
        }
    }
}
