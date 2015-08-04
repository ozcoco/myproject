package me.wangolf.time;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.meigao.mgolf.R;

import me.wangolf.GlobalConsts;
import me.wangolf.event.EventSendActivity_1;

import org.json.JSONObject;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 发布活动年月日dialog
 * 
 * @author Administrator
 * 
 */
public class YeMoDaDialog extends Activity implements OnWheelChangedListener {

	/**
	 * 把全国的省市区的信息以json的格式保存，解析完成后赋值为null
	 */
	private JSONObject mJsonObj;
	/**
	 * 省的WheelView控件
	 */
	private WheelView mYear;
	/**
	 * 市的WheelView控件
	 */
	private WheelView mMon;
	/**
	 * 区的WheelView控件
	 */
	private WheelView mDay;

	/**
	 * 所有省
	 */
	private String[] mProvinceDatas;
	/**
	 * key - 省 value - 市s
	 */
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	/**
	 * key - 市 values - 区s
	 */
	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

	/**
	 * 所有省
	 */
	private String[] hours;
	/**
	 * 所有省
	 */
	private String[] mins;

	/**
	 * 当前省的名称
	 */
	private String mCurrentProviceName;
	/**
	 * 当前市的名称
	 */
	private String mCurrentCityName;
	/**
	 * 当前区的名称
	 */
	private String mCurrentAreaName = "";
	private TextView btnCancle;
	private int year;
	private int month;
	private int day;
	private int index;
	private WheelView mHour;
	private WheelView mMin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_yemodhm);
		TextView tvTitle = (TextView) findViewById(R.id.textView1);
		tvTitle.setText("请选择发布日期");
		index = getIntent().getIntExtra("index", 0);
		iniDialog();

		// 初始化小时分钟数组
		hours = new String[24];
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				hours[i] = "0" + i;
			} else {
				hours[i] = i + "";
			}
		}
		mins = new String[60];
		for (int i = 0; i < 60; i++) {
			if (i < 10) {
				mins[i] = "0" + i;
			} else {
				mins[i] = i + "";
			}
		}

		mYear = (WheelView) findViewById(R.id.id_province);
		mYear.setWheelForeground(R.drawable.select_bg);// TODO 设置中间的图片
		mMon = (WheelView) findViewById(R.id.id_city);
		mMon.setWheelForeground(R.drawable.select_bg);// TODO 设置中间的图片
		mDay = (WheelView) findViewById(R.id.id_area);
		mDay.setWheelForeground(R.drawable.select_bg);// TODO 设置中间的图片

		mHour = (WheelView) findViewById(R.id.id_hour);
		mHour.setWheelForeground(R.drawable.select_bg);// TODO 设置中间的图片
		mMin = (WheelView) findViewById(R.id.id_min);
		mMin.setWheelForeground(R.drawable.select_bg);// TODO 设置中间的图片

		btnCancle = (TextView) findViewById(R.id.bt_cancle);
		btnCancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		initDatas();

		mYear.setViewAdapter(new ArrayWheelAdapter<String>(this, mProvinceDatas));
		// 添加change事件
		mYear.addChangingListener(this);
		// 添加change事件
		mMon.addChangingListener(this);
		// 添加change事件
		mDay.addChangingListener(this);

		mYear.setVisibleItems(5);
		mMon.setVisibleItems(5);
		mDay.setVisibleItems(5);
		mHour.setVisibleItems(5);
		mMin.setVisibleItems(5);

		updateCities();
		updateAreas();

		// 设置当前月份与天数

		mDay.setCurrentItem(day);

	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mMon.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mAreaDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
			// TODO 自己添加修改的部分，为了得到区
			mCurrentAreaName = "";
		} else if (mCitisDatasMap.get(pCurrent) == null && areas != null) {
			// TODO 自己添加修改的部分，为了得到区
			mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[0];

		}

		mDay.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
		mDay.setCurrentItem(0);

		// 设置时分
		mHour.setViewAdapter(new ArrayWheelAdapter<String>(this, hours));
		mMin.setViewAdapter(new ArrayWheelAdapter<String>(this, mins));

	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mYear.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mMon.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mMon.setCurrentItem(0);
		updateAreas();
	}

	/**
	 * 解析整个Json对象，完成后释放Json对象的内存
	 */
	private void initDatas() {
		// 从日历中取得的时间
		Calendar c = Calendar.getInstance(Locale.CHINA);
		Date mydate = new Date();
		c.setTime(mydate);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH) + 1;
		day = c.get(Calendar.DAY_OF_MONTH);
		int tmpyear = 0;
		mProvinceDatas = new String[5];
		for (int i = 0; i < 5; i++) {
			if (i == 0) {// TODO 现在年月日
				// 得到当前的年份
				tmpyear = year + i;// 年份
				mProvinceDatas[i] = tmpyear + "";

				String[] mCitiesDatas2 = new String[12 - month + 1];
				for (int j = 0; j < mCitiesDatas2.length; j++) {
					int mon = month + j;// 得到月份
					int dayLen;
					if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
						dayLen = 31;
					} else if (mon == 2 && tmpyear % 4 == 0) {
						dayLen = 29;
					} else if (mon == 2 && tmpyear % 4 != 0) {
						dayLen = 28;
					} else {
						dayLen = 30;
					}

					if (j == 0) {// 现在day
						String[] mAreasDatas = new String[dayLen - day + 1];
						for (int k = 0; k < mAreasDatas.length; k++) {
							mAreasDatas[k] = (day + k) + "";
						}
						mCitiesDatas2[j] = mon + "";
						mAreaDatasMap.put(mon + "", mAreasDatas);

					} else {
						String[] mAreasDatas = new String[dayLen];
						for (int k = 0; k < dayLen; k++) {
							mAreasDatas[k] = (k + 1) + "";
						}
						mCitiesDatas2[j] = mon + "";
						mAreaDatasMap.put(mon + "", mAreasDatas);
					}
				}

				mCitisDatasMap.put(tmpyear + "", mCitiesDatas2);
			} else {// TODO 其他年月日

				// 得到当前的年份
				tmpyear = year + i;// 年份
				mProvinceDatas[i] = tmpyear + "";

				String[] mCitiesDatas = new String[12];
				for (int j = 0; j < 12; j++) {
					int mon = j + 1;// 得到月份
					int dayLen;
					if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
						dayLen = 31;
					} else if (mon == 2 && tmpyear % 4 == 0) {
						dayLen = 29;
					} else if (mon == 2 && tmpyear % 4 != 0) {
						dayLen = 28;
					} else {
						dayLen = 30;
					}
					String[] mAreasDatas = new String[dayLen];
					for (int k = 0; k < dayLen; k++) {
						mAreasDatas[k] = (k + 1) + "";
					}
					mCitiesDatas[j] = mon + "";
					mAreaDatasMap.put(mon + "", mAreasDatas);
				}

				mCitisDatasMap.put(tmpyear + "", mCitiesDatas);

			}

		}

	}

	/**
	 * change事件的处理
	 */
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mYear) {
			updateCities();
		} else if (wheel == mMon) {
			updateAreas();
		} else if (wheel == mDay) {
			mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[newValue];
		}
	}

	public void showChoose(View view) {
		String year = mCurrentProviceName;
		String mon = mCurrentCityName;
		String day = mCurrentAreaName;

		String hour = hours[mHour.getCurrentItem()];
		String min = mins[mMin.getCurrentItem()];

		Intent timeIntent = new Intent(this, EventSendActivity_1.class);
		timeIntent.putExtra("type", 1);
		timeIntent.putExtra("year", year);
		timeIntent.putExtra("mon", mon);
		timeIntent.putExtra("day", day);
		timeIntent.putExtra("hour", hour);
		timeIntent.putExtra("min", min);
		timeIntent.putExtra("index", index);
		setResult(GlobalConsts.eventYMDCode, timeIntent);
		finish();
	}

	/**
	 * set dialog width and height
	 */
	private void iniDialog() {
		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay();
		android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
		// p.height=(int) (d.getHeight() * 0.5); //高度设置为屏幕的1.0
		p.width = (int) (d.getWidth() * 1.0) + 1; // 宽度设置为屏幕
		getWindow().setAttributes(p); // 设置生效
		getWindow().setGravity(Gravity.BOTTOM);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}
}
