package me.wangolf.event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.InfoEntity;
import me.wangolf.bean.event.EventDetailEntity;
import me.wangolf.bean.event.EventEntity;
import me.wangolf.bean.usercenter.OrderpayBean;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.usercenter.OrderPayActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.DialogUtils;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.LogUtils;
import me.wangolf.utils.ShareUtils;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.Xutils;

public class EventDetailActivity extends BaseActivity implements OnClickListener {
    @ViewInject(R.id.common_back)
    private Button common_back; // 后退
    @ViewInject(R.id.common_title)
    private TextView common_title;// 标题
    @ViewInject(R.id.common_bt)
    private TextView common_bt;// 地图
    @ViewInject(R.id.webView)
    private WebView webView;//
    @ViewInject(R.id.joinEventList)
    private Button joinEventList;
    @ViewInject(R.id.bt_event)
    private Button bt_event;// 我要报名 或我要参加
    @ViewInject(R.id.relayout)
    private LinearLayout relayout;//
    @ViewInject(R.id.event_logo)
    private ImageView mLogo;
    @ViewInject(R.id.event_title)
    private TextView mTitle;
    @ViewInject(R.id.event_price)
    private TextView mPrice;
    @ViewInject(R.id.event_origin_price)
    private TextView mOPrice;
    private String eveid;
    private Dialog dialog;
    private EventDetailEntity data;
    private String submit;
    private int flag;// 是报名0 或是参加1
    private int price;// 需要支付的钱
    private OrderpayBean bean;
    private String uid;// 用户id
    private String cid;// 供应商ID
    private String eid;// 活动ID
    private String cusname;// 消费人名字
    private String sex;// 性别
    private String option;// 可选填写的内容
    private String mobile;// 电话号码
    private String logo;
    private String image;
    private String picfile;
    private String imagename;
    private String sharetitle;
    private String shareUrl;//分享的链接

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_event_detail);
        ViewUtils.inject(this);
        initData();
    }

    @SuppressLint("NewApi")
    @Override
    public void initData() {
        common_back.setVisibility(View.VISIBLE);
        common_bt.setVisibility(View.VISIBLE);
        common_title.setText(ConstantValues.EVENT_DETAIL);
        common_bt.setText(ConstantValues.SHARE);
        common_back.setOnClickListener(this);
        joinEventList.setOnClickListener(this);
        common_bt.setOnClickListener(this);
        EventEntity bean = (EventEntity) getIntent().getSerializableExtra("bean");
        submit = getIntent().getStringExtra("submit");
        eveid = getIntent().getStringExtra("eventid");
        //	price = Integer.parseInt(getIntent().getStringExtra("price"));
        logo = getIntent().getStringExtra("logo");
        sharetitle = getIntent().getStringExtra("title");
        mPrice.setText("￥"+bean.getPrice());
        mTitle.setText(sharetitle);
        mOPrice.setText("￥"+bean.getOrigin_price());
        mOPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        if (!CheckUtils.checkEmpty(logo)) {
            picfile = logo.substring(0, logo.lastIndexOf(".")) + "_180_180" + logo.substring(logo.lastIndexOf("."));
            image = logo.substring(0, logo.lastIndexOf(".")) + "_640_395" + logo.substring(logo.lastIndexOf("."));
        }
        Xutils.getBitmap(this, mLogo, image);
        if ("1".equals(submit)) {
            bt_event.setOnClickListener(this);
        } else {
            bt_event.setBackground(getResources().getDrawable(R.drawable.bt_event_gray_yuan));
        }
        getData();
    }

    @Override
    public void getData() {
        dialog = DialogUtil.getDialog(this);
        dialog.show();
        try {
            ServiceFactory.getEventEngineInstatice().getEventDetail(eveid, new IOAuthCallBack() {

                @Override
                public void getIOAuthCallBack(String result) {

                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(EventDetailActivity.this, ConstantValues.NONETWORK);
                    } else {
                        EventDetailEntity bean = GsonTools.changeGsonToBean(result, EventDetailEntity.class);
                        if ("1".equals(bean.getStatus())) {
                            data = bean.getData().get(0);
                            price = bean.getData().get(0).getPrice();
                            flag = data.getWritesign();
                            shareUrl = data.getWeb_app_uri();
                            loadNewsData(data.getContent()); // 加载内容
                            dialog.cancel();
                        }
                    }
                }
            });

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    // 处理服务回来的数据
    private void loadNewsData(String info) {
        switch (flag) {
            case 0:
                //relayout.setVisibility(View.VISIBLE);
               // bt_event.setText("我要报名");
                break;
            case 1:
               // relayout.setVisibility(View.VISIBLE);
               // bt_event.setText("我要参加");
                break;
            case 2:
                relayout.setVisibility(View.GONE);

                break;

            default:
                break;
        }

        WebSettings setting = webView.getSettings();
        // setting.setUseWideViewPort(true);
        setting.setJavaScriptEnabled(true);
        // setting.setSupportZoom(true);
        // setting.setBuiltInZoomControls(true);
        // setting.setJavaScriptCanOpenWindowsAutomatically(true);
        // setting.setLoadWithOverviewMode(true);
        
        webView.loadData(info, "text/html; charset=UTF-8", null);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.joinEventList:
                Intent intent = new Intent(this, EventJoinPersonsListActivity.class);
                intent.putExtra("eventid", eveid);
                startActivity(intent);
                break;
            case R.id.bt_event:
                if (data != null) {
                    switch (flag) {
                        case 0:// 报名的活动
                            toEnroling();
                            break;
                        case 1:// 参加的活动
                            toJoin();
                            break;
                        default:
                            break;
                    }
                }
                break;
            case R.id.common_bt:
                if (!CheckUtils.checkEmpty(picfile)) {
                    Xutils.loadImage(picfile);
                    int p = picfile.lastIndexOf("/");
                    imagename = picfile.substring(p);
                }
                ShareUtils.showShareandUrl(sharetitle,shareUrl, this, CheckUtils.checkEmpty(imagename) ? null : imagename);
                break;
            default:
                break;
        }
    }

    public void toJoin() {
        if (ConstantValues.ISLOGIN) {
            if (price > 0) {
                // 需要支付
                if (data == null)
                    return;
                bean = new OrderpayBean();
                bean.setType("4");
                bean.setSupplier_id(data.getCid() + "");
                bean.setUser_id(ConstantValues.UID);
                bean.setMobiel(ConstantValues.USER_MOBILE);
                bean.setConsumer_num(1);
                bean.setProduct_id(data.getId());
                bean.setOrder_amount((double) price);
                bean.setArrival_time(data.getBtime());
                Prepay(bean);// 先生成订单
            } else {
                // 不需要支付
                DialogUtils.tel(this, "是否确定参加活动?", "detail");
            }
        } else {
            // 支登录
            Intent toLogin = new Intent(this, LoginActivity.class);
            toLogin.putExtra("flag", "orderPrac");
            toLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(toLogin, ConstantValues.ORDERPRAC);
        }

    }

    // 我要报名
    public void toEnroling() {

        if (ConstantValues.ISLOGIN) {
            if (price >= 0) {
                // 需要支付
                if (data == null)
                    return;
                bean = new OrderpayBean();
                bean.setType("4");
                bean.setSupplier_id(data.getCid() + "");
                bean.setUser_id(ConstantValues.UID);
                bean.setMobiel(ConstantValues.USER_MOBILE);
                bean.setConsumer_num(1);
                bean.setProduct_id(data.getId());
                bean.setOrder_amount((double) price);
                bean.setArrival_time(data.getBtime());
                Intent to_enroling = new Intent(this, EventEnrolingActivity.class);
                to_enroling.putExtra("bean", bean);
                to_enroling.putExtra("eveid", eveid);
                startActivity(to_enroling);
            } else {
                // 不需要支付
                // DialogUtils.tel(this, "是否确定参加活动?","detail");
            }
        } else {
            // 支登录
            Intent toLogin = new Intent(this, LoginActivity.class);
            toLogin.putExtra("flag", "orderPrac");
            toLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(toLogin, ConstantValues.ORDERPRAC);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == ConstantValues.ORDERPRAC) {
            // toJoin();
        }
    }

    // 生成订单
    public void Prepay(OrderpayBean order_bean) {
        try {
            ServiceFactory.getIUserEngineInstatice().toPrepay(order_bean, new IOAuthCallBack() {

                private String sn;

                @Override
                public void getIOAuthCallBack(String result) {
                    JSONObject jsonObj;
                    try {
                        jsonObj = new JSONObject(result);
                        String status = jsonObj.getString("status");
                        if ("1".equals(status)) {
                            JSONArray jsonArray = jsonObj.getJSONArray("data");
                            if (jsonArray.length() > 0) {
                                JSONObject obj = jsonArray.getJSONObject(0);
                                sn = obj.getString("sn");
                            }
                            if (sn != null) {
                                Intent order_bean = new Intent(getApplicationContext(), OrderPayActivity.class);
                                order_bean.putExtra("order_bean", bean);
                                order_bean.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getApplicationContext().startActivity(order_bean);
                            }

                        } else {
                            ToastUtils.showInfo(getApplicationContext(), jsonObj.getString("info"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 直接参加活动
    public void toEventJoin() {
        uid = ConstantValues.UID;
        cid = data.getCid() + "";
        eid = eveid;
        mobile = ConstantValues.USER_MOBILE;
        try {
            ServiceFactory.getIUserEngineInstatice().toEventJoin(uid, cid, eid, cusname, sex, option, mobile, new IOAuthCallBack() {

                @Override
                public void getIOAuthCallBack(String result) {
                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(EventDetailActivity.this, ConstantValues.NONETWORK);
                    } else {
                        InfoEntity bean = GsonTools.changeGsonToBean(result, InfoEntity.class);
                        if ("1".equals(bean.getStatus())) {
                            ToastUtils.showInfo(getApplicationContext(), bean.getInfo());
                        } else {
                            ToastUtils.showInfo(getApplicationContext(), bean.getInfo());
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
