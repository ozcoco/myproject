package me.wangolf.college;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import me.wangolf.ConstantValues;
import me.wangolf.adapter.CollegeAdapter;
import me.wangolf.base.Mo_BasePage;
import me.wangolf.bean.college.Basebean;
import me.wangolf.bean.college.KnowledgeEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.play.MainActivity;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshBase.OnRefreshListener;
import me.wangolf.utils.viewUtils.PullToRefreshListView;

public class CollegePageItem extends Mo_BasePage 
{
    private String type;
    
    private boolean ismore = false; // 断判是否有更多数据
    
    // public boolean isLoadSuccess;
    private boolean ismoredata;
    
    private boolean isRefresh;// 是否刷新
    
    private String lastid; // 最后的数据ID
    
    private String size = "10";// 每次加载的数目(默认15条)
    
    private ArrayList<KnowledgeEntity> p_list; // 商品列表list
    
    @ViewInject(R.id.pull_refresh_list)
    private PullToRefreshListView pull_refresh_list;
    
    private CollegeAdapter adapter;
    
    private int page=1;
    
    private int number = 10;
    
    private Dialog dialog;

    public CollegePageItem(Context context) 
    {
        super(context);

    }

    public CollegePageItem(Context context, String type) 
    {
        super(context);
        this.type = type;
    }

    @Override
    public View initView(LayoutInflater inflater) 
    {
        View view = inflater.inflate(R.layout.ac_collgeg_list, null);
        
        ViewUtils.inject(this, view);

        if (adapter == null) 
        {
            adapter = new CollegeAdapter(context);
            
            pull_refresh_list.getRefreshableView().setAdapter(adapter);
            
        } 
        else 
        {
            adapter.notifyDataSetChanged();
        }
        
        pull_refresh_list.setPullLoadEnabled(false);
        // 滚动到底自动加载可用
        pull_refresh_list.setScrollLoadEnabled(true);
        // 得到实际的ListView 设置点击
        pull_refresh_list.getRefreshableView().setOnItemClickListener(new OnItemClickListener() 
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
            	
            	Basebean.DataEntity bean = adapter.getItem(position);
                
                if (CommonUtil.isNetworkAvailable(context) == 0) 
                {
                    ToastUtils.showInfo(context, ConstantValues.NONETWORK);
                } 
                else 
                {
                    if ("10".equals(type)) 
                    {
                        Intent intent = new Intent(context, MainActivity.class);
                        
                        intent.putExtra("knowid", bean.getId() + "");
                        
                        context.startActivity(intent);
                        
                    } 
                    else 
                    {
                        Intent intent = new Intent(context, CollegePageContent.class);
                        
                        intent.putExtra("knowid", bean.getId() + "");
                        
                        context.startActivity(intent);
                    }
                }
            }
        });

        // 设置下拉刷新的listener
        pull_refresh_list.setOnRefreshListener(new OnRefreshListener<ListView>() 
        {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) 
            {
                page = 1;
                
                isRefresh = true;
                
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) 
            {

                page++;
                
                getData();
            }
        });
        
        return view;
    }

    @Override
    public void initData() 
    {
        dialog = DialogUtil.getDialog(context);
        
        dialog.show();
        
        getData();
    }

    public void getData() {

        try {
            // System.out.println("type=" + type);
            ServiceFactory.getCollegeEngineInstatice().getNewsList(type, page, number, new IOAuthCallBack() 
            {

                @Override
                public void getIOAuthCallBack(String result) 
                {

                    if (result.equals(ConstantValues.FAILURE))
                    {
                        ToastUtils.showInfo(context, ConstantValues.NONETWORK);
                        
                        dialog.cancel();
                        
                        onLoaded();
                        
                    } 
                    else 
                    {

                        Basebean bean = GsonTools.changeGsonToBean(result, Basebean.class);                              
                        
                        if ("1".equals(bean.getStatus())) 
                        {
                        	                 	
                            if (bean.getData() != null) 
                            {
                                List<Basebean.DataEntity> data = bean.getData();
                                
                                List<Basebean.DataEntity> mList = adapter.getList();
                                
                                if (data.size() == 0)
                                {
                                    // 没有更多数据
                                    ToastUtils.showInfo(context, ConstantValues.NOMORE);
                                    
                                    dialog.cancel();
                                    
                                    onLoaded();
                                    
                                    return;
                                }
                                
                                if (isRefresh) 
                                {
                                    // 下拉刷新
                                    isRefresh = false;
                                    
                                    if (mList != null)
                                        mList.clear();
                                    
                                    adapter.setList(data);
                                    
                                } 
                                else
                                {
                                    isLoadSuccess = true;
                                    // mList == null初始化。mList ！= null 加载更多
                                    if (mList == null) 
                                    {
                                        adapter.setList(data);
                                    } 
                                    else 
                                    {
                                        mList.addAll(data);
                                    }
                                }
                                
                                adapter.notifyDataSetChanged();
                            }
                        }
                        
                        dialog.cancel();
                        
                        onLoaded();
                        
                        setLastUpdateTime();
                    }
                }
            });
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

    }

    public void processData() {
    }

    private void setLastUpdateTime() 
    {
        String text = CommonUtil.getStringDate();
        
        pull_refresh_list.setLastUpdatedLabel(text);
    }

    private void onLoaded() 
    {
        dismissLoadingView();
        
        pull_refresh_list.onPullDownRefreshComplete();
        
        pull_refresh_list.onPullUpRefreshComplete();
    }
}
