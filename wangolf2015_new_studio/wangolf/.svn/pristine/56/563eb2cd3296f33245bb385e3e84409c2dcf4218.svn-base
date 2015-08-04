package me.wangolf.event;

/**
 * ============================================================
 *
 * 版权 ：美高传媒 版权所有 (c) 2015年1月16日
 *
 * 作者:copy
 *
 * 版本 ：1.0
 *
 * 创建日期 ： 2015年1月16日
 *
 * 描述 ：活动首页
 *
 *
 * 修订历史 ：
 *
 * ============================================================
 **/

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.FgEventAdapter;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.community.CommunityPostsEntity;
import me.wangolf.bean.event.EventEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshListView;
import me.wangolf.utils.viewUtils.PullToRefreshBase.OnRefreshListener;

public class EventMainActivity extends BaseActivity implements OnClickListener {
    @ViewInject(R.id.common_back)
    private Button common_back; // 后退
    @ViewInject(R.id.common_title)
    private TextView common_title;// 标题
    @ViewInject(R.id.common_bt)
    private TextView common_bt;// 地图
    @ViewInject(R.id.pull_refresh_list)
    private PullToRefreshListView pull_refresh_list;
    private FgEventAdapter adapter;
    private String time;
    private int page = 1;
    private int number = 10;
    private String version = "4";
    private String recommend = "0"; // 0代表普通活动1代表首页活动
    private Dialog dialog;
    private boolean isRefresh;// 是否下拉刷新

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_event_list);
        ViewUtils.inject(this);

        if (adapter == null) {
            adapter = new FgEventAdapter(this);
        } else {
            adapter.notifyDataSetChanged();
        }
        pull_refresh_list.getRefreshableView().setAdapter(adapter);
        pull_refresh_list.setPullLoadEnabled(false);
        // 滚动到底自动加载可用
        pull_refresh_list.setScrollLoadEnabled(true);
        // 得到实际的ListView 设置点击
        pull_refresh_list.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventEntity bean = (EventEntity) adapter.getItem(position);
                if (CommonUtil.isNetworkAvailable(getApplicationContext()) == 0) {
                    ToastUtils.showInfo(EventMainActivity.this, ConstantValues.NONETWORK);
                } else {
                    Intent intent = new Intent(getApplicationContext(), EventDetailActivity.class);
                    intent.putExtra("bean", bean);
                    intent.putExtra("eventid", bean.getId() + "");
                    intent.putExtra("submit", bean.getSubmit() + "");
                    intent.putExtra("price", bean.getPrice());
                    intent.putExtra("logo", bean.getLogo());
                    intent.putExtra("title", bean.getTitle());
                    startActivity(intent);

                }
            }
        });

        // 设置下拉刷新的listener
        pull_refresh_list.setOnRefreshListener(new OnRefreshListener<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                isRefresh = true;
                page = 1;
                getData();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                getData();
            }
        });
        initData();
    }

    @Override
    public void initData() {
        dialog = DialogUtil.getDialog(this);
        dialog.show();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = sDateFormat.format(new java.util.Date());
        common_back.setVisibility(View.VISIBLE);
        common_bt.setVisibility(View.VISIBLE);
        common_title.setText(ConstantValues.EVENT_TITLE);
        common_bt.setText(ConstantValues.EVENT_ISSUE);
        common_back.setOnClickListener(this);
        common_bt.setOnClickListener(this);
        getData();
    }

    @Override
    public void getData() {
        try {
            ServiceFactory.getEventEngineInstatice().getEventList(time, page + "", number + "", version, recommend, new IOAuthCallBack() {

                @Override
                public void getIOAuthCallBack(String result) {
                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(EventMainActivity.this, ConstantValues.NONETWORK);
                        onLoaded();
                        dialog.cancel();
                    } else {
                        EventEntity bean = GsonTools.changeGsonToBean(result, EventEntity.class);
                        if ("1".equals(bean.getStatus())) {
                            if (bean.getData() != null) {
                                ArrayList<EventEntity> data = bean.getData();
                                ArrayList<EventEntity> mList = adapter.getList();
                                if (data.size() == 0) {
                                    // 没有更多数据
                                    ToastUtils.showInfo(EventMainActivity.this, ConstantValues.NOMORE);
                                    return;
                                }
                                if (isRefresh) {
                                    // 下拉刷新
                                    isRefresh = false;
                                    if (mList != null)
                                        mList.clear();
                                    adapter.setList(data);
                                } else {
                                    // mList == null初始化。mList ！= null 加载更多
                                    if (mList == null) {
                                        adapter.setList(data);
                                    } else {
                                        mList.addAll(data);
                                    }
                                }
                                adapter.notifyDataSetChanged();

                            }
                        }
                        dialog.cancel();
                        onLoaded();
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
            case R.id.common_back:
                finish();
                break;
            case R.id.common_bt:
                if (ConstantValues.ISLOGIN) {
                    Intent send_event = new Intent(EventMainActivity.this, EventSendActivity_1.class);
                    startActivity(send_event);
                } else {
                    // 支登录
                    Intent toLogin = new Intent(this, LoginActivity.class);
                    toLogin.putExtra("flag", "orderPrac");
                    startActivityForResult(toLogin, ConstantValues.ORDERPRAC);
                }
                break;
            default:
                break;
        }
    }

    // 关闭刷新
    private void onLoaded() {

        pull_refresh_list.onPullDownRefreshComplete();
        pull_refresh_list.onPullUpRefreshComplete();
    }

}
