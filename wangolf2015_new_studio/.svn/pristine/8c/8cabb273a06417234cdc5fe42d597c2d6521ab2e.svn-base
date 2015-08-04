package me.wangolf.community;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import java.util.ArrayList;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.CommunityIndexAdapter;
import me.wangolf.adapter.CommunityIndexHotAdapter;
import me.wangolf.base.Mo_BasePage;
import me.wangolf.bean.community.CommunityHotPostsEntity;
import me.wangolf.bean.community.CommunityPostsEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.MyListView;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshBase.OnRefreshListener;
import me.wangolf.utils.viewUtils.PullToRefreshListView;

public class CommunityPage extends Mo_BasePage implements OnClickListener {
    @ViewInject(R.id.common_title)
    private TextView common_title;
    @ViewInject(R.id.common_img)
    private ImageView common_img;
    @ViewInject(R.id.comm_hot_list)
    private MyListView comm_hot_list;
    @ViewInject(R.id.pull_refresh_list)
    private PullToRefreshListView mPullList;
    private CommunityIndexHotAdapter adapter_hot;
    private CommunityIndexAdapter adapter_list;
    private String user_id;
    private int page = 1;
    private int number = 10;
    private boolean isRefresh;// 是否下拉刷新

    public CommunityPage(Context context) {
        super(context);
    }

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.ac_community, null);
        ViewUtils.inject(this, view);
        View head = inflater.inflate(R.layout.comm_hot_head, null);
        ViewUtils.inject(this, head);
        if (adapter_hot == null) {
            adapter_hot = new CommunityIndexHotAdapter(context);
        }
        if (adapter_list == null) {
            adapter_list = new CommunityIndexAdapter(context);
        }
        adapter_list.notifyDataSetChanged();
        adapter_hot.notifyDataSetChanged();
        comm_hot_list.setAdapter(adapter_hot);
        mPullList.getRefreshableView().addHeaderView(comm_hot_list);
        mPullList.getRefreshableView().setAdapter(adapter_list);

        mPullList.setPullLoadEnabled(false);
        // 滚动到底自动加载可用
        mPullList.setScrollLoadEnabled(true);
        // 得到实际的ListView 设置点击
        mPullList.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (CommonUtil.isNetworkAvailable(context) == 0) {

                    ToastUtils.showInfo(context, ConstantValues.NONETWORK);
                } else {
                    CommunityPostsEntity bean = (CommunityPostsEntity) adapter_list.getItem(position - 1);
                    Intent intent = new Intent(context, CommunityDetailActivity.class);
                    intent.putExtra("posts_id", bean.getId() + "");
                    context.startActivity(intent);
                }
            }
        });

        // 设置下拉刷新的listener
        mPullList.setOnRefreshListener(new OnRefreshListener<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                isRefresh = true;
                page = 1;
                getListData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                page++;
                getListData();

            }
        });
        setHotOnclick();
        return view;
    }

    //置顶点击事件
    public void setHotOnclick() {
        comm_hot_list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                if (CommonUtil.isNetworkAvailable(context) == 0) {
                    ToastUtils.showInfo(context, ConstantValues.NONETWORK);
                } else {
                    CommunityHotPostsEntity bean = (CommunityHotPostsEntity) adapter_hot.getItem(position);
                    Intent intent = new Intent(context, CommunityDetailActivity.class);
                    intent.putExtra("posts_id", bean.getId() + "");
                    context.startActivity(intent);
                }

            }
        });
    }

    @SuppressLint("NewApi")
    @Override
    public void initData() {
        common_title.setText("社区");
        common_img.setVisibility(View.VISIBLE);
        common_img.setBackground(context.getResources().getDrawable(R.drawable.r2_c6));
        common_img.setOnClickListener(this);
        getHotData();
        getListData();
    }

    @Override
    public void upView() {
        isRefresh = true;
        page = 1;
        getListData();
    }

    // 置顶数据
    public void getHotData() {
        try {
            ServiceFactory.getCommunityEngineInstatice().getHotPosts(new IOAuthCallBack() {

                @Override
                public void getIOAuthCallBack(String result) {
                    if (result.equals(ConstantValues.FAILURE)) {

                        ToastUtils.showInfo(context, ConstantValues.NONETWORK);
                    } else {
                        CommunityHotPostsEntity bean = GsonTools.changeGsonToBean(result, CommunityHotPostsEntity.class);
                        if ("1".equals(bean.getStatus())) {

                            ArrayList<CommunityHotPostsEntity> data = bean.getData();
                            adapter_hot.setHot_list(data);
                            adapter_hot.notifyDataSetChanged();

                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 列表数据
    public void getListData() {
        try {
            ServiceFactory.getCommunityEngineInstatice().getPostsList(user_id,user_id,"", page, number, new IOAuthCallBack() {

                @Override
                public void getIOAuthCallBack(String result) {

                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(context, ConstantValues.NONETWORK);
                    } else {
                        CommunityPostsEntity bean = GsonTools.changeGsonToBean(result, CommunityPostsEntity.class);
                        if ("1".equals(bean.getStatus())) {
                            ArrayList<CommunityPostsEntity> data = bean.getData();
                            ArrayList<CommunityPostsEntity> mList = adapter_list.getPosts_list();
                            if (data.size() == 0) {
                                // 没有更多数据
                                ToastUtils.showInfo(context, ConstantValues.NOMORE);
                                return;
                            }
                            if (isRefresh) {
                                // 下拉刷新
                                isRefresh = false;
                                if (mList != null)
                                    mList.clear();
                                adapter_list.setPosts_list(data);
                            } else {
                                // mList == null初始化。mList ！= null 加载更多
                                if (mList == null) {
                                    adapter_list.setPosts_list(data);
                                } else {
                                    mList.addAll(data);
                                }
                            }
                            adapter_list.notifyDataSetChanged();
                            onLoaded();
                        }
                    }
                }
            });
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_img:
                if (ConstantValues.ISLOGIN) {
                    // 登录成功
                    toEditPosts();
                } else {
                    // 去登录
                    Intent toLogin = new Intent(context, LoginActivity.class);
                    toLogin.putExtra("flag", "orderPrac");
                    ((FragmentActivity) context).startActivityForResult(toLogin, ConstantValues.ORDERPRAC);
                }
                break;

            default:
                break;
        }
    }

    // 去发帖
    public void toEditPosts() {
        Intent send = new Intent(context, AlbumEditActivity.class);
        context.startActivity(send);
    }

    // 关闭刷新
    private void onLoaded() {
        dismissLoadingView();
        mPullList.onPullDownRefreshComplete();
        mPullList.onPullUpRefreshComplete();
    }

}
