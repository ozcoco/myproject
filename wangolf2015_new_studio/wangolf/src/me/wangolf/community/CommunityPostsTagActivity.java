package me.wangolf.community;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import java.util.ArrayList;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.CommunityPostsTagListAdapter;
import me.wangolf.adapter.PostsPraiseAdapter;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.community.CommunityPraiseEntity;
import me.wangolf.bean.community.CommunityTagEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.usercenter.UserFriendsInfoActivity;
import me.wangolf.usercenter.UserInfoNewActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshListView;

/**
 * Created by Administrator on 2015/6/8.
 */
public class CommunityPostsTagActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.common_back)
    private TextView mBack;
    @ViewInject(R.id.common_title)
    private TextView mTitle;
    @ViewInject(R.id.pull_refresh_list)
    private PullToRefreshListView mPullList;
    private CommunityPostsTagListAdapter adapter;
    private int page = 1;//页数
    private int number = 10;//分页外数
    private boolean isRefresh;// 是否下拉刷新
    private int posts_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_praise_list);
        ViewUtils.inject(this);
        if(adapter==null){
            adapter= new CommunityPostsTagListAdapter(this);
        }
        adapter.notifyDataSetChanged();
        mPullList.getRefreshableView().setAdapter(adapter);
        initData();
        initRefreshableView();
    }
    private void initRefreshableView(){
        mPullList.setPullLoadEnabled(false);
        // 滚动到底自动加载可用
        mPullList.setScrollLoadEnabled(true);
        // 得到实际的ListView 设置点击
        mPullList.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!ConstantValues.ISLOGIN) {
                    // 去登录
                    Intent toLogin = new Intent(CommunityPostsTagActivity.this, LoginActivity.class);
                    toLogin.putExtra("flag", "orderPrac");
                    CommunityPostsTagActivity.this.startActivity(toLogin);
                    return;
                }
                CommunityTagEntity bean = (CommunityTagEntity) adapter.getItem(position);
                Intent intent = new Intent(CommunityPostsTagActivity.this, CommunityPostsListActivity.class);
                intent.putExtra("tags_id", bean.getId());
                intent.putExtra("tags_name", bean.getName());
                CommunityPostsTagActivity.this.startActivity(intent);

            }
        });

        // 设置下拉刷新的listener
        mPullList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //getData();
                onLoaded();
            }
        });
    }
    @Override
    public void initData() {
        mTitle.setText("全部标签");
        mBack.setVisibility(View.VISIBLE);
        mTitle.setVisibility(View.VISIBLE);
        mBack.setOnClickListener(this);
        getData();
    }
    @Override
    public void getData() {
        try {
            ServiceFactory.getCommunityEngineInstatice().getTagsList(new IOAuthCallBack() {
                @Override
                public void getIOAuthCallBack(String result) {
                    CommunityTagEntity bean = GsonTools.jsonToBean(result,CommunityTagEntity.class);
                    if(1==bean.getStatus()){
                        ArrayList<CommunityTagEntity> data = bean.getData();
                        adapter.setmList(data);
                        adapter.notifyDataSetChanged();
                        onLoaded();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 关闭刷新
    private void onLoaded() {
        mPullList.onPullDownRefreshComplete();
        mPullList.onPullUpRefreshComplete();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_back:
                finish();
                break;
            default:
                break;
        }
    }
}
