package me.wangolf.newfragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;
import java.util.ArrayList;
import me.wangolf.ConstantValues;
import me.wangolf.adapter.ShopPagerAdapter;
import me.wangolf.base.BaseFragment;
import me.wangolf.shop.ShopPageItem;
import me.wangolf.text.view.ExpandTabView;
import me.wangolf.text.view.ViewRight;
import me.wangolf.view.pagerindicator.TabPageIndicator;

/**
 * Created by Administrator on 2015/4/2.
 */
public class ShopFra extends BaseFragment {
    private ArrayList<ShopPageItem> pages = new ArrayList<ShopPageItem>();
    private String[] title = ConstantValues.SHOP_TITLE_S;
    private int curIndex;
    @ViewInject(R.id.indicator)
    private TabPageIndicator indicator;
    @ViewInject(R.id.pager)
    private ViewPager pager;
    @ViewInject(R.id.common_sort)
    private LinearLayout common_sort; // 排行列表
    @ViewInject(R.id.price_low)
    private TextView price_low; // 人
    @ViewInject(R.id.price_high)
    private TextView price_high;
    @ViewInject(R.id.discount_low)
    private TextView discount_low;// 折扣
    @ViewInject(R.id.common_bt)
    private TextView common_bt;// bar排行
    @ViewInject(R.id.sales_high)
    private TextView sales_high;// 销量最高
    @ViewInject(R.id.product_up)
    private TextView product_up;// 新品上架
    @ViewInject(R.id.common_title)
    private TextView common_title;// 标题
    private ShopPagerAdapter<ShopPageItem> adapter;
    @ViewInject(R.id.iv_edit_cate)
    private ExpandTabView expandTabView;
    private ViewRight viewRight;
    private ArrayList<View> mViewArray = new ArrayList<View>();

    private int sorttype; // 排序
    private int rule;
    private boolean initview;
    private boolean getData;
    private boolean isLoadSuccess;//是否加载成功标记

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_news, null);
        ViewUtils.inject(this, view);
        initData();
        return view;
    }

    @Override
    public void initData() {
        initIndicator();
        common_sort.setVisibility(View.GONE);// 移动隐排序
        common_title.setText(ConstantValues.SHOP_TITLE);// 设置标题
        common_bt.setText(ConstantValues.SORT);
        common_bt.setVisibility(View.VISIBLE);// 显示排序
        common_bt.setOnClickListener(this);
        price_low.setOnClickListener(this);
        price_high.setOnClickListener(this);
        discount_low.setOnClickListener(this);
        sales_high.setOnClickListener(this);
        product_up.setOnClickListener(this);
        setTextView();
        if (!initview) {
            initView();
            initValue();
            initListener();
        }

    }

    @Override
    public void initView() {
        initview = true;
        viewRight = new ViewRight(getActivity());
        viewRight.getBackground().setAlpha(238);
    }

    private void initValue() {
        mViewArray.add(viewRight);
        ArrayList<String> mTextArray = new ArrayList<String>();
        mTextArray.add("");
        expandTabView.setValue(mTextArray, mViewArray);
        expandTabView.setTitle(viewRight.getShowText(), 2);
        // expandTabView.setBackground(background);

    }

    private void initIndicator() {
        if (!getData) {

            if (!isLoadSuccess) {
                price_low.setBackgroundColor(getActivity().getResources().getColor(R.color.bg_black_color));
                // pages.add(new ShopPageItem(getActivity(), "6"));
//                pages.add(new ShopPageItem(getActivity(), "0"));
//                pages.add(new ShopPageItem(getActivity(), "1"));
//                pages.add(new ShopPageItem(getActivity(), "2"));
//                pages.add(new ShopPageItem(getActivity(), "3"));
//                pages.add(new ShopPageItem(getActivity(), "4"));
//                pages.add(new ShopPageItem(getActivity(), "5"));
            }
            isLoadSuccess = true;
            if (adapter == null) {
                adapter = new ShopPagerAdapter<ShopPageItem>(getActivity(), pages, title);
            }
            pager.setAdapter(adapter);
            indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int arg0) {
                    ShopPageItem page = pages.get(arg0);
                    if (!page.isLoadSuccess) {
                        page.initData();
                    }
                    curIndex = arg0;
                    setBackgroundColor();
                    price_low.setBackgroundColor(getActivity().getResources().getColor(R.color.bg_black_color));
                    common_sort.setVisibility(View.GONE);// 移动隐排序

                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int arg0) {

                }
            });
            pages.get(0).initData();
            indicator.setViewPager(pager);
            indicator.setCurrentItem(curIndex);
            getData = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_bt:
                if (common_sort.getVisibility() == View.GONE) {
                    common_sort.setVisibility(View.VISIBLE);
                } else {
                    common_sort.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.price_low:
                setBackgroundColor();
                price_low.setBackgroundColor(getActivity().getResources().getColor(R.color.bg_black_color));
                common_sort.setVisibility(View.VISIBLE);
                sorttype = 0;
                rule = 0;
                pages.get(curIndex).UpSort(sorttype, rule);
                break;
            case R.id.price_high:
                setBackgroundColor();
                price_high.setBackgroundColor(getActivity().getResources().getColor(R.color.bg_black_color));
                common_sort.setVisibility(View.GONE);
                sorttype = 0;
                rule = 1;
                pages.get(curIndex).UpSort(sorttype, rule);
                break;
            case R.id.discount_low:
                setBackgroundColor();
                discount_low.setBackgroundColor(getActivity().getResources().getColor(R.color.bg_black_color));
                common_sort.setVisibility(View.GONE);
                sorttype = 1;
                rule = 0;
                pages.get(curIndex).UpSort(sorttype, rule);
                break;
            case R.id.sales_high:
                setBackgroundColor();
                sales_high.setBackgroundColor(getActivity().getResources().getColor(R.color.bg_black_color));
                common_sort.setVisibility(View.GONE);
                sorttype = 2;
                rule = 1;
                pages.get(curIndex).UpSort(sorttype, rule);
                break;
            case R.id.product_up:
                setBackgroundColor();
                product_up.setBackgroundColor(getActivity().getResources().getColor(R.color.bg_black_color));
                common_sort.setVisibility(View.GONE);
                sorttype = 3;
                rule = 1;
                pages.get(curIndex).UpSort(sorttype, rule);
                break;
            default:
                break;
        }
    }

    private void initListener() {

        viewRight.setOnSelectListener(new ViewRight.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText, int id) {
                onRefresh(viewRight, showText, id);
            }

            @Override
            public void getValue(String distance, String showText) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void onRefresh(View view, String showText, int id) {

        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            // expandTabView.setTitle(showText, position);
            ShopPageItem page = pages.get(id);
            if (!page.isLoadSuccess) {
                page.initData(getActivity());
            }
            curIndex = id;
            common_sort.setVisibility(View.GONE);// 移动隐排序
            indicator.setViewPager(pager);
            indicator.setCurrentItem(curIndex);

        }
        // Toast.makeText(getActivity(), showText, Toast.LENGTH_SHORT).show();

    }

    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    public void setBackgroundColor() {
        price_low.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
        price_high.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
        discount_low.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
        sales_high.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
        product_up.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
    }

    public void setTextView() {
        price_low.setText("价格最低");
        price_high.setText("价格最高");
        discount_low.setText("折扣最低");
        sales_high.setText("销量最高");
        product_up.setText("新品上架");
    }
}
