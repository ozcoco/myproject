package me.wangolf.shop;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import java.util.ArrayList;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.ShopPagerAdapter;
import me.wangolf.base.Mo_BasePage;
import me.wangolf.text.view.ExpandTabView;
import me.wangolf.text.view.ViewRight;
import me.wangolf.view.pagerindicator.TabPageIndicator;

public class ShopPage extends Mo_BasePage implements OnClickListener {
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

	public ShopPage(Context context) {
		super(context);

	}

	@Override
	public View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.frag_news, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData() {
		initIndicator();
		common_sort.setVisibility(8);// 移动隐排序
		common_title.setText(ConstantValues.SHOP_TITLE);// 设置标题
		common_bt.setText(ConstantValues.SORT);
		common_bt.setVisibility(0);// 显示排序
		common_bt.setOnClickListener(this);
		price_low.setOnClickListener(this);
		price_high.setOnClickListener(this);
		discount_low.setOnClickListener(this);
		sales_high.setOnClickListener(this);
		product_up.setOnClickListener(this);
		setTextView();
		if (!initview) {
			initView();
			initVaule();
			initListener();
		}
	}

	private void initView() {
		initview = true;
		viewRight = new ViewRight(context);
		viewRight.getBackground().setAlpha(238);

	}

	private void initVaule() {

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
				price_low.setBackgroundColor(context.getResources().getColor(R.color.bg_black_color));
				// pages.add(new ShopPageItem(context, "6"));
//				pages.add(new ShopPageItem(context, "0"));
//				pages.add(new ShopPageItem(context, "1"));
//				pages.add(new ShopPageItem(context, "2"));
//				pages.add(new ShopPageItem(context, "3"));
//				pages.add(new ShopPageItem(context, "4"));
//				pages.add(new ShopPageItem(context, "5"));
			}
			isLoadSuccess = true;
			if (adapter == null) {
				adapter = new ShopPagerAdapter<ShopPageItem>(context, pages, title);
			}
			pager.setAdapter(adapter);
			indicator.setOnPageChangeListener(new OnPageChangeListener() {

				@Override
				public void onPageSelected(int arg0) {
					ShopPageItem page = pages.get(arg0);
					if (!page.isLoadSuccess) {
						page.initData();
					}
					curIndex = arg0;
					setBackgroundColor();
					price_low.setBackgroundColor(context.getResources().getColor(R.color.bg_black_color));
					common_sort.setVisibility(8);// 移动隐排序

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
			if (common_sort.getVisibility() == 8) {
				common_sort.setVisibility(0);
			} else {
				common_sort.setVisibility(8);
			}
			break;
		case R.id.price_low:
			setBackgroundColor();
			price_low.setBackgroundColor(context.getResources().getColor(R.color.bg_black_color));
			common_sort.setVisibility(8);
			sorttype = 0;
			rule = 0;
			pages.get(curIndex).UpSort(sorttype, rule);
			break;
		case R.id.price_high:
			setBackgroundColor();
			price_high.setBackgroundColor(context.getResources().getColor(R.color.bg_black_color));
			common_sort.setVisibility(8);
			sorttype = 0;
			rule = 1;
			pages.get(curIndex).UpSort(sorttype, rule);
			break;
		case R.id.discount_low:
			setBackgroundColor();
			discount_low.setBackgroundColor(context.getResources().getColor(R.color.bg_black_color));
			common_sort.setVisibility(8);
			sorttype = 1;
			rule = 0;
			pages.get(curIndex).UpSort(sorttype, rule);
			break;
		case R.id.sales_high:
			setBackgroundColor();
			sales_high.setBackgroundColor(context.getResources().getColor(R.color.bg_black_color));
			common_sort.setVisibility(8);
			sorttype = 2;
			rule = 1;
			pages.get(curIndex).UpSort(sorttype, rule);
			break;
		case R.id.product_up:
			setBackgroundColor();
			product_up.setBackgroundColor(context.getResources().getColor(R.color.bg_black_color));
			common_sort.setVisibility(8);
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
				page.initData(context);
			}
			curIndex = id;
			common_sort.setVisibility(8);// 移动隐排序
			indicator.setViewPager(pager);
			indicator.setCurrentItem(curIndex);

		}
		// Toast.makeText(context, showText, Toast.LENGTH_SHORT).show();

	}

	private int getPositon(View tView) {
		for (int i = 0; i < mViewArray.size(); i++) {
			if (mViewArray.get(i) == tView) {
				return i;
			}
		}
		return -1;
	}

//	@Override
//	public void onBackPressed() {
//
//		if (!expandTabView.onPressBack()) {
//			finish();
//		}
//	}

	public void setBackgroundColor() {
		price_low.setBackgroundColor(context.getResources().getColor(R.color.transparent));
		price_high.setBackgroundColor(context.getResources().getColor(R.color.transparent));
		discount_low.setBackgroundColor(context.getResources().getColor(R.color.transparent));
		sales_high.setBackgroundColor(context.getResources().getColor(R.color.transparent));
		product_up.setBackgroundColor(context.getResources().getColor(R.color.transparent));
	}

	public void setTextView() {
		price_low.setText("价格最低");
		price_high.setText("价格最高");
		discount_low.setText("折扣最低");
		sales_high.setText("销量最高");
		product_up.setText("新品上架");
	}
}
