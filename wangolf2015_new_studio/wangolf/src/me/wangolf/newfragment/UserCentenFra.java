package me.wangolf.newfragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.MainAdapter;
import me.wangolf.ballprac.BallInfoActivity;
import me.wangolf.bean.HomePageAdsEntity;
import me.wangolf.bean.event.EventEntity;
import me.wangolf.bean.usercenter.UserInfoEntity;
import me.wangolf.college.CollegePage;
import me.wangolf.event.EventDetailActivity;
import me.wangolf.event.EventMainActivity;
import me.wangolf.event.EventnNoticeActivity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.practice.PracticeInfoActivity;
import me.wangolf.practice.PracticeListActivity;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.shop.ShopProActivity;
import me.wangolf.usercenter.OrderListActivity;
import me.wangolf.usercenter.UpDataUserInfoActivity;
import me.wangolf.usercenter.UserAccountActivity;
import me.wangolf.usercenter.UserEventListActivity;
import me.wangolf.usercenter.UserInfoNewActivity;
import me.wangolf.usercenter.UserNotificationMessageActivity;
import me.wangolf.usercenter.UserNotificationMessageNewActivity;
import me.wangolf.usercenter.UserSet;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ImageViewUtil;
import me.wangolf.utils.LogUtils;
import me.wangolf.utils.SharedPreferencesUtils;
import me.wangolf.utils.TelUtils;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshListView;
import me.wangolf.utils.viewUtils.RollViewPager;

public class UserCentenFra extends Fragment implements OnClickListener {
    @ViewInject(R.id.common_title)
    private TextView common_title;// 标题
    private View view;
    DisplayImageOptions options;
    private boolean flag;
    LayoutInflater inflater;
    @ViewInject(R.id.name)
    private TextView name;// 呢称
    @ViewInject(R.id.account)
    private TextView account;// 余额
    @ViewInject(R.id.ib)
    private de.hdodenhof.circleimageview.CircleImageView ib; // 圆形图片
    @ViewInject(R.id.reletag)
    private ImageView reletag;
    @ViewInject(R.id.orderlist)
    private RelativeLayout orderlist;// 订单列表
    @ViewInject(R.id.my_account)
    private RelativeLayout my_account;// 我的账户
    @ViewInject(R.id.my_set)
    private RelativeLayout my_set;// 设置
    @ViewInject(R.id.my_event)
    private RelativeLayout my_event;// 我的活动
    @ViewInject(R.id.up_userinfo)
    private RelativeLayout up_userinfo;// 更新资料
    @ViewInject(R.id.common_bt)
    private TextView mMessage;// 消息
    @ViewInject(R.id.user_new_info)
    private Button mUserInfo;// 个人中心
    @ViewInject(R.id.user_customer)
    private Button mCustomer;// 客服中心
    private Double my_accounts;// 我的账户余额
    private Double my_vouchers;// 我的代金券余额
    private String path;
    private String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.ac_user_center, null);
        ViewUtils.inject(this, view);
        initData();
        return view;
    }

    public void initData() {
        common_title.setText("发现");
        mMessage.setVisibility(View.VISIBLE);
        mMessage.setText("消息");
        orderlist.setOnClickListener(this);
        my_set.setOnClickListener(this);
        my_account.setOnClickListener(this);
        my_event.setOnClickListener(this);
        up_userinfo.setOnClickListener(this);
        ib.setOnClickListener(this);
        mMessage.setOnClickListener(this);
        mUserInfo.setOnClickListener(this);
        mCustomer.setOnClickListener(this);
        uid = ConstantValues.UID;
        if (CheckUtils.checkEmpty(uid)) {
            return;
        }
        getData();
    }

    public void getData() {
        try {
            ServiceFactory.getIUserEngineInstatice().getUserInfo(uid, new IOAuthCallBack() {
                @Override
                public void getIOAuthCallBack(String result) {

                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(getActivity(), ConstantValues.NONETWORK);
                    } else {
                        UserInfoEntity user = GsonTools.changeGsonToBean(result, UserInfoEntity.class);
                        if ("1".equals(user.getStatus())) {
                            UserInfoEntity.DataEntity userinfo = user.getData().get(0);
                            name.setText(CheckUtils.checkEmpty(userinfo.getNick_name()) ? userinfo.getMobile() : userinfo.getNick_name());
                            account.setText("￥" + userinfo.getAccount());

                            my_accounts = Double.valueOf(userinfo.getAccount());
                            
//                            my_vouchers = userinfo.getVouchers();
                            
                            String path_1 = userinfo.getAvatar();
                            
//                            if (!CheckUtils.checkEmpty(path_1)) 
//                            {
//                                path_1 = path_1.substring(0, path_1.lastIndexOf(".")) + "_180_180" + path_1.substring(path_1.lastIndexOf("."));
//                            }
                            
                            ImageViewUtil.loadimg(path_1, ib, getActivity());
                        }
                    }
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) 
    {
        if (CommonUtil.isNetworkAvailable(getActivity()) == 0) 
        {
            ToastUtils.showInfo(getActivity(), ConstantValues.NONETWORK);
        } 
        else
        {
            switch (v.getId()) {
                case R.id.orderlist:
                    Intent order = new Intent(getActivity(), OrderListActivity.class);
                    getActivity().startActivity(order);
                    break;
                case R.id.my_account:
                    if (my_accounts >= 0.0) {
                        Intent account = new Intent(getActivity(), UserAccountActivity.class);
                        account.putExtra("account", my_accounts.toString());
                        account.putExtra("vouchers", my_vouchers.toString());
                        getActivity().startActivity(account);
                    }
                    break;
                case R.id.my_set:
                    Intent my_set = new Intent(getActivity(), UserSet.class);
                    // getActivity().startActivity(my_set);
                    getActivity().startActivityForResult(my_set, 101);
                    break;
                case R.id.my_event:
                    Intent event = new Intent(getActivity(), UserEventListActivity.class);
                    getActivity().startActivity(event);
                    break;
                case R.id.up_userinfo:
                    Intent up_userinfo = new Intent(getActivity(), UpDataUserInfoActivity.class);
                    getActivity().startActivity(up_userinfo);
                    break;
                case R.id.ib:
                    Intent ib = new Intent(getActivity(), UpDataUserInfoActivity.class);
                    getActivity().startActivity(ib);
                    // ShowPickUtils.ShowPickDialog(getActivity());
                    // Intent intent = new Intent(Intent.ACTION_PICK, null);
                    //
                    // intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    // "image/*");
                    // getActivity()startActivityForResult(intent, 1);
                    break;
                case R.id.common_bt:
                    // Intent message = new Intent(getActivity(),
                    // UserNotificationActivity.class);
                    Intent message = new Intent(getActivity(), UserNotificationMessageNewActivity.class);
                    getActivity().startActivity(message);
                    break;
                case R.id.user_new_info:

                    Intent userinfo = new Intent(getActivity(), UserInfoNewActivity.class);

                    getActivity().startActivity(userinfo);
                    break;
                default:

                    break;
            }

        }
        if (v.getId() == R.id.user_customer)
            TelUtils.tel(getActivity(), "13302311999");
    }

    public void getUpData() {
        getData();

    }
}
