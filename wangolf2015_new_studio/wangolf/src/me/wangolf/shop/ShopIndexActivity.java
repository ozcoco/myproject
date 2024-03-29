package me.wangolf.shop;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.topnewgrid.ShopChannelActivity;
import com.example.topnewgrid.bean.ChannelItem;
import com.example.topnewgrid.bean.ChannelManage;
import com.example.topnewgrid.db.SQLHelper;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import java.util.ArrayList;

import me.wangolf.ConstantValues;
import me.wangolf.LocationApplication;
import me.wangolf.adapter.ShopPagerAdapter;
import me.wangolf.base.BaseActivity;
import me.wangolf.text.view.ExpandTabView;
import me.wangolf.text.view.ViewRight;
import me.wangolf.view.pagerindicator.TabPageIndicator;

public class ShopIndexActivity extends Activity implements View.OnClickListener 
{
    private ArrayList<ShopPageItem> pages = new ArrayList<ShopPageItem>();
    
    private String[] title ;
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
    
    @ViewInject(R.id.common_back)
    private TextView mBack;
    
    @ViewInject(R.id.iv_newtag)
    private ImageView mTag;
    
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
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.frag_news);
        
        ViewUtils.inject(this);
        
        initData();
    }


    public void initData() 
    {
        initIndicator();
       // mTag.setVisibility(View.VISIBLE);
        mBack.setVisibility(View.VISIBLE);
        
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
        
        mBack.setOnClickListener(this);
        
        mTag.setOnClickListener(this);
        
        setTextView();
        
        if (!initview) 
        {
            initView();
            
            initValue();
            
            initListener();
        }

    }

    public void initView()
    {
        initview = true;
        
        viewRight = new ViewRight(this);
        
        viewRight.getBackground().setAlpha(238);
    }

    private void initValue() 
    {
        mViewArray.add(viewRight);
        
        ArrayList<String> mTextArray = new ArrayList<String>();
        
        mTextArray.add("");
        
        expandTabView.setValue(mTextArray, mViewArray);
        
        expandTabView.setTitle(viewRight.getShowText(), 2);
        
        // expandTabView.setBackground(background);

    }

    private void initIndicator()
    {
        if (!getData) 
        {

            if (!isLoadSuccess)
            {
                price_low.setBackgroundColor(this.getResources().getColor(R.color.bg_black_color));
                // pages.add(new ShopPageItem(this, "6"));
//                pages.add(new ShopPageItem(this, "0"));
//                pages.add(new ShopPageItem(this, "1"));
//                pages.add(new ShopPageItem(this, "2"));
//                pages.add(new ShopPageItem(this, "3"));
//                pages.add(new ShopPageItem(this, "4"));
//                pages.add(new ShopPageItem(this, "5"));
                for(int i=0;i<ConstantValues.shop_title_name.length;i++)
                {
                    pages.add(new ShopPageItem(this,ConstantValues.shop_title_id[i]));
                }
                
                title = ConstantValues.shop_title_name;
            }
            
            isLoadSuccess = true;
            
            if (adapter == null) 
            {
                adapter = new ShopPagerAdapter<ShopPageItem>(this, pages, title);
            }
            
            pager.setAdapter(adapter);
            
            indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
            {

                @Override
                public void onPageSelected(int arg0) 
                {
                    ShopPageItem page = pages.get(arg0);
                    
                    if (!page.isLoadSuccess) 
                    {
                        page.initData();
                    }
                    
                    curIndex = arg0;
                    
                    setBackgroundColor();
                    
                    price_low.setBackgroundColor(getResources().getColor(R.color.bg_black_color));
                    
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
    public void onClick(View v) 
    {
        switch (v.getId()) 
        {
            case R.id.common_bt:
                if (common_sort.getVisibility() == View.GONE)
                {
                    common_sort.setVisibility(View.VISIBLE);
                } 
                else 
                {
                    common_sort.setVisibility(View.VISIBLE);
                }
                break;
                
            case R.id.price_low:
            	
                setBackgroundColor();
                
                price_low.setBackgroundColor(this.getResources().getColor(R.color.bg_black_color));
               
                common_sort.setVisibility(View.GONE);
                
                sorttype = 0;
                
                rule = 0;
                
                pages.get(curIndex).UpSort(sorttype, rule);
               
                break;
                
            case R.id.price_high:
            	
                setBackgroundColor();
                
                price_high.setBackgroundColor(this.getResources().getColor(R.color.bg_black_color));
              
                common_sort.setVisibility(View.GONE);
               
                sorttype = 0;
               
                rule = 1;
                
                pages.get(curIndex).UpSort(sorttype, rule);
               
                break;
                
            case R.id.discount_low:
                setBackgroundColor();
                discount_low.setBackgroundColor(this.getResources().getColor(R.color.bg_black_color));
                common_sort.setVisibility(View.GONE);
                sorttype = 1;
                rule = 0;
                pages.get(curIndex).UpSort(sorttype, rule);
                break;
            case R.id.sales_high:
                setBackgroundColor();
                sales_high.setBackgroundColor(this.getResources().getColor(R.color.bg_black_color));
                common_sort.setVisibility(View.GONE);
                sorttype = 2;
                rule = 1;
                pages.get(curIndex).UpSort(sorttype, rule);
                break;
            case R.id.product_up:
                setBackgroundColor();
                product_up.setBackgroundColor(this.getResources().getColor(R.color.bg_black_color));
                common_sort.setVisibility(View.GONE);
                sorttype = 3;
                rule = 1;
                pages.get(curIndex).UpSort(sorttype, rule);
                break;
            case R.id.common_back:
                finish();
                break;
            case R.id.iv_newtag:
                Intent tag = new Intent(this, ShopChannelActivity.class);
                tag.putExtra("newstags", "newstags");
                tag.putExtra("tableName", SQLHelper.TABLE_SHOP);
               startActivityForResult(tag, 100);
            default:
                break;
        }
    }

    private void initListener() 
    {

        viewRight.setOnSelectListener(new ViewRight.OnSelectListener()
        {

            @Override
            public void getValue(String distance, String showText, int id) 
            {
                onRefresh(viewRight, showText, id);
            }

            @Override
            public void getValue(String distance, String showText) 
            {
                // TODO Auto-generated method stub

            }
        });

    }

    private void onRefresh(View view, String showText, int id) 
    {

        expandTabView.onPressBack();
        
        int position = getPositon(view);
        
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) 
        {
            // expandTabView.setTitle(showText, position);
            ShopPageItem page = pages.get(id);
           
            if (!page.isLoadSuccess) 
            {
                page.initData(this);
            }
            
            curIndex = id;
            
            common_sort.setVisibility(View.GONE);// 移动隐排序
            
            indicator.setViewPager(pager);
            
            indicator.setCurrentItem(curIndex);

        }
        // Toast.makeText(this, showText, Toast.LENGTH_SHORT).show();

    }

    private int getPositon(View tView) 
    {
        for (int i = 0; i < mViewArray.size(); i++)
        {
            if (mViewArray.get(i) == tView) 
            {
                return i;
            }
        }
        return -1;
    }

    
    public void setBackgroundColor() 
    {
        price_low.setBackgroundColor(this.getResources().getColor(R.color.transparent));
        
        price_high.setBackgroundColor(this.getResources().getColor(R.color.transparent));
        
        discount_low.setBackgroundColor(this.getResources().getColor(R.color.transparent));
        
        sales_high.setBackgroundColor(this.getResources().getColor(R.color.transparent));
        
        product_up.setBackgroundColor(this.getResources().getColor(R.color.transparent));
    }

    public void setTextView() 
    {
        price_low.setText("价格最低");
        price_high.setText("价格最高");
        discount_low.setText("折扣最低");
        sales_high.setText("销量最高");
        product_up.setText("新品上架");
    }

    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode==100)
        {
           initData();
        }
        
        super.onActivityResult(requestCode, resultCode, data);
    }
}
