package me.wangolf.practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.GlobalConsts;
import me.wangolf.adapter.PracDistributorListAdapter;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.WeatherEntity;
import me.wangolf.bean.practice.PracDetailEntity_new;
import me.wangolf.bean.practice.PracDistriEntity;
import me.wangolf.college.CollegeCoachList;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.fragment.RoutePlan;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.shop.ShopImgActivit;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.DateFormatUtils;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.LogUtils;
import me.wangolf.utils.ShareUtils;
import me.wangolf.utils.TelUtils;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.WeatherUtils;
import me.wangolf.utils.Xutils;
import me.wangolf.utils.viewUtils.RollViewPager_NORoll;
import me.wangolf.utils.viewUtils.RollViewPager_NORoll.OnPagerClickCallback;

public class PracticeInfoActivity extends BaseActivity implements OnClickListener, OnItemClickListener {
    @ViewInject(R.id.common_back)
    private Button common_back; // 后退
    @ViewInject(R.id.common_title)
    private TextView common_title;// 标题
    @ViewInject(R.id.common_bt)
    private TextView common_bt;// 分享
    @ViewInject(R.id.lv_distributor)
    private ListView lv_distributor; // list列表
    private LinearLayout hardView;
    private LinearLayout footView;
    private String rgid;// 球场ID

    @ViewInject(R.id.tv_tee_num)
    private TextView mTeeNum;
    @ViewInject(R.id.tv_tee_floor_num)
    private TextView mTeeFloorNum;
    @ViewInject(R.id.tv_supporting)
    private TextView mSupporting;
    @ViewInject(R.id.tv_summy)
    private TextView mSummy;
    @ViewInject(R.id.rl_coach)
    private RelativeLayout mCoach;

    @ViewInject(R.id.img_whether)
    private ImageView img_whether; // 天气图
    @ViewInject(R.id.tv_weather)
    private TextView tv_weather;// 天气字
    @ViewInject(R.id.layout_towheather)
    private LinearLayout layout_towheather;// 天气块
    private ArrayList<WeatherEntity> weatherArray;

    @ViewInject(R.id.layout_detail)
    private RelativeLayout layout_detail;
    @ViewInject(R.id.layout_coachs)
    private RelativeLayout layout_coachs;// 教练
    @ViewInject(R.id.layout_phone)
    private RelativeLayout layout_phone; // 拨打电话
    @ViewInject(R.id.layout_native)
    private RelativeLayout layout_native;// 导航
    @ViewInject(R.id.head_tv_nagtive)
    private TextView mNagtive;
    @ViewInject(R.id.im_phone)
    private ImageView mPhone;//电话


    private RollViewPager_NORoll Rpage; //
    private ArrayList<View> dotList; // 链接
    @ViewInject(R.id.dots_ll)
    private LinearLayout dots_ll; // 填充滚动viewpage点
    @ViewInject(R.id.shop_viewpager)
    private LinearLayout shop_viewpager; // 滚动图
    private ArrayList<String> titles;
    private ArrayList<String> urlList = new ArrayList<String>();
    private LinkedList<PracDistriEntity> distributors;
    private PracDistributorListAdapter adapter;
    private ArrayList<PracDetailEntity_new> datas; // 数据
    private PracDetailEntity_new data;
    private String rgname;
    private String cityname;
    private Dialog dialog;
    private String picfile;
    private String imagename;
    private String sharetitle;
    private String shareUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_prac_info);
        ViewUtils.inject(this);
        if (adapter == null) {
            adapter = new PracDistributorListAdapter(this);
        } else {
            adapter.notifyDataSetChanged();
        }

        setView();// 初始视图
        initData();
        lv_distributor.setAdapter(adapter);
        lv_distributor.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        dialog = DialogUtil.getDialog(this);
        dialog.show();
        rgid = getIntent().getStringExtra("rgid");
        rgname = getIntent().getStringExtra("rgname");
        cityname = getIntent().getStringExtra("cityname");
        sharetitle = cityname + "   " + rgname;
        common_back.setVisibility(View.VISIBLE);
        common_bt.setVisibility(View.VISIBLE);
        // common_title.setText(rgname);
        common_bt.setText(ConstantValues.SHARE);
        common_bt.setOnClickListener(this);
        common_back.setOnClickListener(this);
        layout_coachs.setOnClickListener(this);
        layout_phone.setOnClickListener(this);
        layout_detail.setOnClickListener(this);
        layout_native.setOnClickListener(this);
        mCoach.setOnClickListener(this);
        mNagtive.setOnClickListener(this);
        mPhone.setOnClickListener(this);
        getData();
        getWeather();
    }

    public void setView() {
        hardView = (LinearLayout) View.inflate(this, R.layout.head_practice, null);
        footView = (LinearLayout) View.inflate(this, R.layout.item_practice_info_foot, null);
        ViewUtils.inject(this, hardView);
        ViewUtils.inject(this, footView);
        lv_distributor.addHeaderView(hardView);
        lv_distributor.addFooterView(footView);
    }

    @Override
    public void getData() {
        ServiceFactory.getPracEngineInstatice().getPracticeInfo(rgid, new IOAuthCallBack() {

            @Override
            public void getIOAuthCallBack(String result) {
               // Log.i("wangolf",rgid+"************");
                if (result.equals(ConstantValues.FAILURE)) {

                    ToastUtils.showInfo(PracticeInfoActivity.this, ConstantValues.NONETWORK);
                } else {
                    PracDetailEntity_new bean = GsonTools.changeGsonToBean(result, PracDetailEntity_new.class);
                    datas = bean.getData();
                    if (bean.getData().size() == 0) {
                        ToastUtils.showInfo(PracticeInfoActivity.this, ConstantValues.NONETWORK);
                    } else {
                        data = datas.get(0);
                        setPracticeInfo(data);//更新球场详情
                        String[] urls = data.getRange_img().split(",");
                        for (int i = 0; i < urls.length; i++) {
                            String path = urls[i];
                            if (!CheckUtils.checkEmpty(path)) {
                                String[] s = path.split(",");
                                path = s[0].substring(0, s[0].lastIndexOf(".")) + "_640_395" + s[0].substring(s[0].lastIndexOf("."));
                            }
                            urlList.add(path);
                        }
                        shareUrl = data.getWeb_app_uri();
                        picfile = urlList.get(0);// 分享的图片
                        common_title.setText(data.getRange_name());

                        initDot(urlList.size()); // 滚动视图点
                        initRoll();// 滚动视图初始化
                        if ("0".equals(data.getCoach()))
                            layout_coachs.setVisibility(View.GONE); // 0表示没有教练
                        distributors = data.getDistributors();
                        adapter.setList(distributors);
                        adapter.notifyDataSetChanged();
                        dialog.cancel();
                    }
                }
            }

        });

    }

    //更新球场详情
    public void setPracticeInfo(PracDetailEntity_new bean) {

        mTeeNum.setText("打位个数:" + bean.getTee_num());
        mTeeFloorNum.setText("打个层数:" + bean.getTee_floor_num());
        mSupporting.setText(bean.getSupporting());
        mSummy.setText(bean.getSummy());
        mNagtive.setText(bean.getAddress());
    }

    public void toBuyPractice(PracDistriEntity bean) {

        if (ConstantValues.ISLOGIN) {
            if (bean != null & !"0".equals(bean.getPrice())) {
                Intent intent = new Intent(this, PracToBuyActivity.class);
                intent.putExtra("bean", bean);

                intent.putExtra("rgname", rgname);
                intent.putExtra("rgid", rgid);// 将得到ballid传入
                startActivity(intent);
            } else if ("0".equals(bean.getPrice())) {
                ToastUtils.showInfo(this, "暂不提供在线购买");
            }
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("flag", "orderPrac");
            startActivityForResult(intent, ConstantValues.ORDERPRAC);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.layout_coachs:
                Intent intent = new Intent(this, CollegeCoachList.class);
                intent.putExtra("rgid", rgid);
                this.startActivity(intent);
                break;
            case R.id.layout_phone:
                if (data == null)
                    return;
                if (!CheckUtils.checkEmpty(data.getMobile())) {
                    TelUtils.tel(this, data.getMobile());
                }
                break;
            case R.id.layout_detail:
                Intent in = new Intent(this, PracTagDetailActivity.class);

                in.putExtra("rgid", rgid);// 球场id
                // in.putExtra("imgUris", data.getRgimg());
                startActivity(in);
                break;
            case R.id.layout_native:
                if (data == null) {
                    return;
                }
                Intent route = new Intent(this, RoutePlan.class);
                route.putExtra("longitude", data.getLongitude());// 球场
                route.putExtra("latitude", data.getLatitude());// 球场
                startActivity(route);
                break;
            case R.id.common_bt:
                if (!CheckUtils.checkEmpty(picfile)) {
                    Xutils.loadImage(picfile);
                    int p = picfile.lastIndexOf("/");
                    imagename = picfile.substring(p);
                }
                ShareUtils.showShareandUrl(sharetitle, shareUrl, this, CheckUtils.checkEmpty(imagename) ? "" : imagename);
                break;
            case R.id.rl_coach:
                //跳转到教练
                Intent coach = new Intent(this, CollegeCoachList.class);
                coach.putExtra("rgid", rgid);
                this.startActivity(coach);
                break;
            case R.id.head_tv_nagtive:
                //导航
                if (data == null) {
                    return;
                }
                Intent nagtive = new Intent(this, RoutePlan.class);
                nagtive.putExtra("longitude", data.getLongitude());// 球场
                nagtive.putExtra("latitude", data.getLatitude());// 球场
                startActivity(nagtive);
                break;
            case R.id.im_phone:
                //打电话
                if (data == null)
                    return;
                if (!CheckUtils.checkEmpty(data.getMobile())) {
                    TelUtils.tel(this, data.getMobile());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//        if (distributors.size() > 0) {
//            PracDistriEntity bean = (PracDistriEntity) adapter.getItem(arg2 - 1);
//            if (!"true".equals(GlobalConsts.ISLOGIN)) {
//                if (bean != null & !"0".equals(bean.getPrice())) {
//                    Intent intent = new Intent(this, OrderDialogPractice.class);
//                    intent.putExtra("bean", bean);
//                    intent.putExtra("rgname", rgname);
//                    intent.putExtra("rgid", rgid);// 将得到ballid传入
//                    this.startActivity(intent);
//                } else if ("0".equals(bean.getPrice())) {
//
//                    ToastUtils.showInfo(PracticeInfoActivity.this, "暂不提供在线购买");
//                }
//            } else {
//                Intent intent = new Intent(this, LoginActivity.class);
//                this.startActivity(intent);
//            }
//        }
    }

    public void getWeather() {
        String path = WeatherUtils.getWeatherUrl(cityname, this.getApplicationContext());
        ServiceFactory.getPracEngineInstatice().getWeather(path, new IOAuthCallBack() {

            @Override
            public void getIOAuthCallBack(String result) {
                if (result.equals(ConstantValues.FAILURE)) {

                    ToastUtils.showInfo(PracticeInfoActivity.this, "获取天气失败");
                } else {
                    String st2 = result.substring(38, result.length() - 2);
                    try {
                        JSONObject obj = new JSONObject(st2);
                        // JSONObject obj=objF.getJSONObject("weatherinfo");
                        weatherArray = new ArrayList<WeatherEntity>();
                        int len = 0;
                        try {
                            String day7 = obj.getString("day7");
                            len = 7;
                            // 正常执行表示有7天天气
                        } catch (Exception e) {
                            // 否则只有6天天气
                            len = 6;
                        }
                        if (len == 6) {

                            for (int i = 1; i <= 6; i++) {
                                WeatherEntity w6 = new WeatherEntity();
                                w6.setCity(obj.getString("city"));
                                w6.setTempler(obj.getString("temp" + i));
                                w6.setWeather(obj.getString("weather" + i));
                                String sday6 = "";
                                if (i == 1) {
                                    sday6 = "今天";
                                } else if (i == 2) {
                                    sday6 = "明天";
                                } else if (i == 3) {
                                    sday6 = "后天";
                                } else {
                                    // 取得今天的日期
                                    String today = obj.getString("date_y");
                                    Date d = (Date) DateFormatUtils.formaToDate(today);
                                    Calendar c = Calendar.getInstance();
                                    c.setTime(d);
                                    c.add(Calendar.DATE, i);
                                    Date datetmp6 = (Date) c.getTime();
                                    sday6 = DateFormatUtils.formatToMMdd(datetmp6);
                                }
                                w6.setDate(sday6);
                                w6.setWind(obj.getString("wind" + i));
                                int imgnum = Integer.parseInt(obj.getString("img" + ((2 * i) - 1)));
                                if (imgnum > 31) {// 大于所有图片的代号则去取晚上的图片
                                    imgnum = Integer.parseInt(obj.getString("img" + ((2 * i))));
                                }
                                w6.setPic(imgnum + ".png");
                                weatherArray.add(w6);
                            }
                        } else if (len == 7) {

                            for (int i = 1; i <= 7; i++) {
                                WeatherEntity w7 = new WeatherEntity();
                                w7.setCity(obj.getString("city"));
                                w7.setTempler(obj.getString("temp" + i));
                                w7.setWeather(obj.getString("weather" + i));
                                String sday7 = "";
                                if (i == 1) {
                                    sday7 = "今天";
                                } else if (i == 2) {
                                    sday7 = "明天";
                                } else if (i == 3) {
                                    sday7 = "后天";
                                } else {
                                    // 取得今天的日期
                                    String today = obj.getString("date_y");
                                    Date d = (Date) DateFormatUtils.formaToDate(today);
                                    Calendar c = Calendar.getInstance();
                                    c.setTime(d);
                                    c.add(Calendar.DATE, i);
                                    Date datetmp6 = (Date) c.getTime();
                                    sday7 = DateFormatUtils.formatToMMdd(datetmp6);
                                }
                                w7.setDate(sday7);
                                w7.setWind(obj.getString("wind" + i));
                                int imgnum = Integer.parseInt(obj.getString("img" + i));
                                if (imgnum > 31) {// 大于所有图片的代号则去取晚上的图片
                                    imgnum = Integer.parseInt(obj.getString("img_night" + i));
                                }
                                w7.setPic(imgnum + ".png");
                                weatherArray.add(w7);
                            }
                        }
                        Bitmap bm = null;
                        String imgpath = weatherArray.get(0).getPic();
                        try {
                            bm = BitmapFactory.decodeStream(PracticeInfoActivity.this.getAssets().open(imgpath));
                            if (bm != null) {
                                img_whether.setImageBitmap(bm);
                            }

                            WeatherEntity w2 = weatherArray.get(1);
                            tv_weather.setText(w2.getCity() + "\t" + w2.getDate() + "\t" + w2.getWeather() + "\t" + w2.getTempler());
                        } catch (IOException e) {

                        }
                        layout_towheather.setVisibility(View.VISIBLE);// 显示天气块

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    public void initRoll() {

        // ================滚动图片数据============= 滚动图初始化数据
        Rpage = new RollViewPager_NORoll(getApplicationContext(), dotList, R.drawable.dot_focus, R.drawable.dot_normal, new OnPagerClickCallback() {
            @Override
            public void onPagerClick(int position) {
                if (urlList.size() > 0) {
                    Intent intent = new Intent(getApplicationContext(), ShopImgActivit.class);
                    intent.putStringArrayListExtra("url", urlList);
                    startActivity(intent);
                }
            }
        });
        Rpage.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        titles = new ArrayList<String>();

        // Rpage.setTitle(chzz_news_roll_title, titles);//标题
        Rpage.setUriList(urlList);
        Rpage.startRoll();
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

}