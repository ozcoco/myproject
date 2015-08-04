package me.wangolf.ballprac;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.base.Mo_BasePage;
import me.wangolf.bean.CityEntity;
import me.wangolf.calender.CalendarView;
import me.wangolf.city.CityActivity;
import me.wangolf.time.TimeSingleActivity;
import me.wangolf.utils.DateFormatUtils;

@SuppressLint("ValidFragment")
public class BallPracSearchActivity extends Mo_BasePage implements OnClickListener {

	@ViewInject(R.id.common_back)
	private Button common_back; // 后退
	@ViewInject(R.id.common_title)
	private TextView common_title;// 标题
	@ViewInject(R.id.common_bt)
	private TextView common_bt;// 常打
	@ViewInject(R.id.bt_search)
	private Button bt_search;// 搜索按键
	@ViewInject(R.id.lay_city)
	private LinearLayout lay_city; // 选择城市
	@ViewInject(R.id.tv_curlocation)
	private TextView tv_curlocation;// 选择的城市
	@ViewInject(R.id.lay_date)
	private LinearLayout lay_date;// 选择日期
	@ViewInject(R.id.tv_date)
	private TextView tv_date;// 选择的日期
	@ViewInject(R.id.tv_date_tip)
	private TextView tv_date_tip;
	@ViewInject(R.id.lay_time)
	private LinearLayout lay_time; // 选择时间
	@ViewInject(R.id.tv_time)
	private TextView tv_time;// 显示时间
	@ViewInject(R.id.lay_ballname)
	private LinearLayout lay_ballname;
	@ViewInject(R.id.tv_ballname)
	private TextView tv_ballname;// 选择的球场

	private String date;// 打球时间(手机端需要将日期与时间组合)
	private String ballname;// 球场名称(模糊搜索)
	private String cityname;
	private int cityid;
	private String dateStr;
	private String showDate;
	private String time;
	private String firstDate;
	private String ddate;

	public BallPracSearchActivity(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.ac_searchball);
	// ViewUtils.inject(this);
	// initData();
	// }

	@Override
	public View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.ac_searchball, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData() {

		// common_back.setVisibility(0);
		// common_bt.setVisibility(0);
		
		common_title.setText(ConstantValues.BALLSEARCH_TITLE);
		common_bt.setText(ConstantValues.BALLSEARCH_BT);
		common_back.setOnClickListener(this);
		bt_search.setOnClickListener(this);
		lay_city.setOnClickListener(this);
		lay_date.setOnClickListener(this);
		lay_time.setOnClickListener(this);
		lay_ballname.setOnClickListener(this);
		setData();// 设置默认时间
		if (ConstantValues.cityname != null) {
			initResultCity();
		} else {
			cityname = ConstantValues.CITYNAME;
			cityid = ConstantValues.CITYID;
			tv_curlocation.setText(cityname);
		}
		if (ConstantValues.showDate != null)
			initinitResultdate();
		if (ConstantValues.balltime != null)
			initinitResultTime();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.common_back:
			break;
		case R.id.bt_search:
			Intent bt_search = new Intent(context, BallListActivity.class);
			getIDFromDatabase(cityname);
			bt_search.putExtra("cityid", cityid + "");
			bt_search.putExtra("cityname", cityname);
			bt_search.putExtra("ballname", ballname);
			date = firstDate + " " + time + ":00";
			bt_search.putExtra("date", date);
			context.startActivity(bt_search);
			break;
		case R.id.lay_city:
			Intent cityIntent = new Intent(context, CityActivity.class);
			cityIntent.putExtra("type", "ballcity");
			((Activity) context).startActivityForResult(cityIntent, ConstantValues.CITY_CODE);
			break;
		case R.id.lay_date:
			Intent dataIntent = new Intent(context, CalendarView.class);
			dataIntent.putExtra("type", "balldate");
			((Activity) context).startActivityForResult(dataIntent, ConstantValues.dateCode);
			break;
		case R.id.lay_time:
			Intent timeIntent = new Intent(context, TimeSingleActivity.class);
			timeIntent.putExtra("type", "balltime");
			((Activity) context).startActivityForResult(timeIntent, ConstantValues.timeCode);
			break;
		case R.id.lay_ballname:
			Intent nameIntent = new Intent(context, BallnameActivity.class);
			nameIntent.putExtra("type", "ballname");
			((Activity) context).startActivityForResult(nameIntent, ConstantValues.nameCode);
			break;
		default:
			break;
		}

	}

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	// if (data == null) {
	// return;
	// }
	// switch (resultCode) {
	// case ConstantValues.CITY_CODE:
	// CityEntity city = (CityEntity) data.getSerializableExtra("city");
	// cityname = city.getName();
	// tv_curlocation.setText(cityname);
	// ballname = "";
	// tv_ballname.setText("");
	// cityid = city.getId();
	// break;
	// case ConstantValues.dateCode:
	// dateStr = data.getStringExtra("date");
	// showDate = data.getStringExtra("showDate");
	// tv_date.setText(showDate);
	// // 处理显示今天明天后天start
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	// try {
	// Date d1 = new Date();
	// Date d2 = df.parse(dateStr);
	// double diff = d2.getTime() - d1.getTime();
	// double days = diff / (1000 * 60 * 60 * 24);
	// if (days < 0) {
	// tv_date_tip.setText("(今天)");
	// } else if (days >= 0 && days < 1) {
	// tv_date_tip.setText("(明天)");
	// } else if (days >= 1 && days < 2) {
	// tv_date_tip.setText("(后天)");
	// } else {
	// tv_date_tip.setText("");
	// }
	// firstDate = dateStr;
	// } catch (Exception e) {
	// }
	// break;
	// case ConstantValues.timeCode:
	// time = data.getStringExtra("time");
	// tv_time.setText(time);
	// break;
	// case ConstantValues.nameCode:
	// BallNameEntity bean = (BallNameEntity)
	// data.getSerializableExtra("ballbean");
	// if (bean != null) {
	// cityname = bean.getCityname();
	// cityid = bean.getId();
	// tv_curlocation.setText(cityname);
	// tv_ballname.setText(bean.getBallname());
	// ballname = bean.getBallname();
	// } else {
	// tv_ballname.setText("");
	// ballname = "";
	// }
	// break;
	// default:
	// break;
	// }
	// }

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	// ******设置默认时间
	private void setData() {

		Calendar c = Calendar.getInstance(Locale.CHINA);
		Date d = new Date();
		c.setTime(d);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int ti = c.get(Calendar.HOUR);

		int h = c.get(Calendar.HOUR_OF_DAY);
		/*
		 * if(h>=12){ c.add(Calendar.DAY_OF_MONTH, 1);
		 * month=c.get(Calendar.MONTH)+1; day=c.get(Calendar.DAY_OF_MONTH); }
		 */

		c.add(Calendar.DAY_OF_MONTH, 1);
		month = c.get(Calendar.MONTH) + 1;
		day = c.get(Calendar.DAY_OF_MONTH);

		long tt = c.getTimeInMillis();
		ddate = DateFormatUtils.formatToMMdd(new Date(tt));
		time = null;
		try {
			firstDate = DateFormatUtils.formatYMD(new Date(tt));
			// GlobalConsts.BALL_SHOW_DATE=ddate;
			// date =ddate;
			time = "09:30";
			/*
			 * if(ti>=9){ time="12:00"; }
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//
		tv_date.setText(ddate);
		tv_time.setText(time);
	}

	public void initResultCity() {

		if (ConstantValues.ballcitytag) {
			cityname = ConstantValues.cityname;
			cityid = ConstantValues.cityid;
			tv_curlocation.setText(cityname);
			ballname = "";
			tv_ballname.setText(ballname);
		} else {
			cityname = ConstantValues.cityname;
			cityid = ConstantValues.cityid;
			tv_curlocation.setText(cityname);
			ballname = ConstantValues.ballname;
			tv_ballname.setText(ballname);
		}
	}

	public void initinitResultdate() {
		dateStr = ConstantValues.dateStr;
		showDate = ConstantValues.showDate;
		tv_date.setText(showDate);
		// 处理显示今天明天后天start
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = new Date();
			Date d2 = df.parse(dateStr);
			double diff = d2.getTime() - d1.getTime();
			double days = diff / (1000 * 60 * 60 * 24);
			if (days < 0) {
				tv_date_tip.setText("(今天)");
			} else if (days >= 0 && days < 1) {
				tv_date_tip.setText("(明天)");
			} else if (days >= 1 && days < 2) {
				tv_date_tip.setText("(后天)");
			} else {
				tv_date_tip.setText("");
			}
			firstDate = dateStr;
		} catch (Exception e) {
		}
	}

	public void initinitResultTime() {
		time = ConstantValues.balltime;
		tv_time.setText(time);
	}

	public void getIDFromDatabase(String cityname) {
		DbUtils db = DbUtils.create(context, "mgolf.db3");
		db.configAllowTransaction(true);
		db.configDebug(true);

		try {
			CityEntity parent = db.findFirst(Selector.from(CityEntity.class).where("name", "=", cityname));
			cityid = parent.getId();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
