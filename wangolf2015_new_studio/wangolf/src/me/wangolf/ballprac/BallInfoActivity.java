package me.wangolf.ballprac;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import me.wangolf.ConstantValues;
import me.wangolf.adapter.DistributorListAdapter;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.WeatherEntity;
import me.wangolf.bean.ball.BallDetailEntity;
import me.wangolf.bean.ball.BallDistriEntity;
import me.wangolf.bean.ball.BallInfoEntity;
import me.wangolf.calender.CalendarView;
import me.wangolf.dao.CityDao;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.fragment.RoutePlan;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.shop.ShopImgActivit;
import me.wangolf.time.TimeSingleActivity;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.DateFormatUtils;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ShareUtils;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.WeatherUtils;
import me.wangolf.utils.Xutils;

public class BallInfoActivity extends BaseActivity implements OnClickListener
{
	@ViewInject(R.id.common_back)
	private Button							common_back;						// 后退
	@ViewInject(R.id.common_title)
	private TextView						common_title;						// 标题
	@ViewInject(R.id.common_bt)
	private TextView						common_bt;							// 分享
	@ViewInject(R.id.lv_distributor)
	private ListView						lv_distributor;
	@ViewInject(R.id.head_layout_detail)
	private RelativeLayout					head_layout_detail;					// 详情
	private String							ballid;							// 球场ID
	private String							date;
	private DistributorListAdapter			adapter;
	private String							ballname;
	private String[]						ballimg;							// 图片集
	@ViewInject(R.id.ball_img)
	private ImageView						ball_img;
	private List<BallInfoEntity.DataEntity>	data;
	@ViewInject(R.id.img_whether)
	private ImageView						img_whether;						// 天气图
	@ViewInject(R.id.tv_weather)
	private TextView						tv_weather;							// 天气字
	@ViewInject(R.id.layout_towheather)
	private LinearLayout					layout_towheather;					// 天气块
	@ViewInject(R.id.tv_date)
	private TextView						tv_date;							// 显示的日期
	@ViewInject(R.id.layout_date)
	private RelativeLayout					layout_date;						// 选择的日期
	@ViewInject(R.id.tv_time)
	private TextView						tv_time;							// 显示的时间
	@ViewInject(R.id.layout_time)
	private RelativeLayout					layout_time;						// 选择的时间
	@ViewInject(R.id.head_layout_nagtive)
	private RelativeLayout					head_layout_nagtive;
	private List<WeatherEntity>				weatherArray;
	private ArrayList<String>				urlList	= new ArrayList<String>();
	private String							cityname;
	private String							showDate;
	private String							showTiem;
	private String							dateStr;
	private String							time;
	private String[]						dates;
	private Dialog							dialog;
	private BallDetailEntity.DataEntity		bean;
	private String							sharetitle;
	private String							shareUrl;
	private String							picfile;
	private String							imagename;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ac_ball_info);

		ViewUtils.inject(this);

		LinearLayout head_ball = (LinearLayout) View
				.inflate(this, R.layout.head_ball, null);

		ViewUtils.inject(this, head_ball);

		lv_distributor.addHeaderView(head_ball);

		if (adapter == null)
		{
			adapter = new DistributorListAdapter(this, this);
		}
		else
		{
			adapter.notifyDataSetChanged();
		}

		lv_distributor.setAdapter(adapter);

		initData();

	}

	@Override
	public void initData()
	{
		dialog = DialogUtil.getDialog(this);

		dialog.show();

		common_back.setVisibility(View.VISIBLE);

		common_bt.setVisibility(View.VISIBLE);

		bean = (BallDetailEntity.DataEntity) getIntent()
				.getSerializableExtra("BallDetailEntity");

		if (bean != null)
		{
			ballid = bean.getId();

			date = getIntent().getStringExtra("date");

			ballname = getIntent().getStringExtra("ballname");

			cityname = getIntent().getStringExtra("cityname");

			common_title.setText(ballname);

			dates = date.split("%20");

			tv_date.setText(dates[0]);

			tv_time.setText(dates[1]);

			getWeather();// 初始化天气数据

		}
		else
		{
			ballid = getIntent().getStringExtra("ballid");// 首页广告过来的

			try
			{

				date = DateFormatUtils.formatDetail(new Date());

				dates = date.split(" ");

				tv_date.setText(dates[0]);

				tv_time.setText(dates[1]);

				date = dates[0] + "%20" + dates[1];
			}
			catch(Exception e)
			{

				e.printStackTrace();

			}
		}

		ConstantValues.orderdate = date;

		common_bt.setText(ConstantValues.SHARE);

		common_bt.setOnClickListener(this);

		common_back.setOnClickListener(this);

		ball_img.setOnClickListener(this);

		head_layout_detail.setOnClickListener(this);

		layout_date.setOnClickListener(this);

		layout_time.setOnClickListener(this);

		head_layout_nagtive.setOnClickListener(this);

		getData();

	}

	@Override
	public void getData()
	{
		try
		{

			ServiceFactory.getBallEngineInstatice()
					.getBallInfo(ballid, date, new IOAuthCallBack()
					{

						@Override
						public void getIOAuthCallBack(String result)
						{

							if (result.equals(ConstantValues.FAILURE))
							{

								ToastUtils.showInfo(BallInfoActivity.this, ConstantValues.NONETWORK);

							}
							else
							{
								BallInfoEntity bean = GsonTools
										.changeGsonToBean(result, BallInfoEntity.class);

								if (bean.getData() != null)
								{
									data = bean.getData();

									shareUrl = data.get(0).getWeb_app_uri();

									List<BallInfoEntity.DataEntity.DistributorsEntity> datas = data
											.get(0).getDistributors();

									adapter.setList(datas);

									adapter.notifyDataSetChanged();

									ProcessData();
								}

								dialog.cancel();
							}

						}
					});

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void ProcessData()
	{
		if (bean == null)
		{
			CityDao city = new CityDao(this);

			ballname = data.get(0).getCourt_name();

			cityname = city.getCityNameById(data.get(0).getCity());

			common_title.setText(ballname);

			getWeather();
		}

		ballimg = data.get(0).getImg_list().split(",");

		for (String url : ballimg)
		{
			if (!CheckUtils.checkEmpty(url))
			{
				url = url.substring(0, url.lastIndexOf(".")) + "_640_395" + url
						.substring(url.lastIndexOf("."));
			}

			urlList.add(url);
		}

		picfile = urlList.get(0);

		if (!CheckUtils.checkEmpty(ballimg[0]))
		{
			String path = ballimg[0].substring(0, ballimg[0].lastIndexOf(".")) + "_640_395" + ballimg[0]
					.substring(ballimg[0].lastIndexOf("."));

			Xutils.getBitmap(this, ball_img, path);
		}

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{

			case R.id.common_back:
				finish();
				break;

			case R.id.ball_img:
				if (urlList.size() > 0)
				{
					Intent intent = new Intent(this, ShopImgActivit.class);

					intent.putStringArrayListExtra("url", urlList);

					startActivity(intent);

				}
				break;

			case R.id.head_layout_detail:

				Intent balltag = new Intent(this, BallTagDetailActivity.class);

				balltag.putExtra("ballid", ballid);

				startActivity(balltag);

				break;

			case R.id.layout_date:

				Intent dataIntent = new Intent(this, CalendarView.class);

				dataIntent.putExtra("type", "balldate");

				startActivityForResult(dataIntent, ConstantValues.dateCode);

				break;

			case R.id.layout_time:

				Intent timeIntent = new Intent(this, TimeSingleActivity.class);

				timeIntent.putExtra("type", "balltime");

				startActivityForResult(timeIntent, ConstantValues.timeCode);

				break;

			case R.id.head_layout_nagtive:
				if (bean == null) { return; }
				Intent route = new Intent(this, RoutePlan.class);
				route.putExtra("longitude", bean.getLongitude());// 球场
				route.putExtra("latitude", bean.getLatitude());// 球场
				startActivity(route);
				break;
			case R.id.common_bt:
				sharetitle = cityname + "   " + ballname;
				if (!CheckUtils.checkEmpty(picfile))
				{
					Xutils.loadImage(picfile);
					int p = picfile.lastIndexOf("/");
					imagename = picfile.substring(p);
				}
				ShareUtils
						.showShareandUrl(sharetitle, shareUrl, this, CheckUtils
								.checkEmpty(imagename) ? "" : imagename);
				break;
			default:
				break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (data == null) { return; }
		switch (requestCode)
		{
			case ConstantValues.dateCode:
				dateStr = data.getStringExtra("date");
				showDate = data.getStringExtra("showDate");
				tv_date.setText(showDate);
				// 处理显示今天明天后天start
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try
				{
					Date d1 = new Date();
					Date d2 = df.parse(dateStr);
					double diff = d2.getTime() - d1.getTime();
					double days = diff / (1000 * 60 * 60 * 24);
					// firstDate = dateStr;
				}
				catch(Exception e)
				{
				}
				if (CheckUtils.checkEmpty(time))
				{
					date = dateStr + " " + dates[1];
				}
				else
				{
					date = dateStr + " " + time + ":00";
				}
				ConstantValues.orderdate = date;
				getData();
				break;
			case ConstantValues.timeCode:
				time = data.getStringExtra("time");
				tv_time.setText(time);
				if (CheckUtils.checkEmpty(dateStr))
				{
					date = dates[0] + " " + time + ":00";
				}
				else
				{
					date = dateStr + " " + time + ":00";
				}
				ConstantValues.orderdate = date;
				getData();
				break;
			default:
				break;
		}
	}

	public void getWeather()
	{
		if (CheckUtils.checkEmpty(cityname)) { return; }
		String path = WeatherUtils.getWeatherUrl(cityname, this
				.getApplicationContext());
		ServiceFactory.getPracEngineInstatice()
				.getWeather(path, new IOAuthCallBack()
				{

					@Override
					public void getIOAuthCallBack(String result)
					{
						if ("failure".equals(result))
						{

							ToastUtils.showInfo(BallInfoActivity.this, "获取天气失败");
						}
						else
						{
							String st2 = result
									.substring(38, result.length() - 2);
							try
							{
								JSONObject obj = new JSONObject(st2);
								// JSONObject
								// obj=objF.getJSONObject("weatherinfo");
								weatherArray = new ArrayList<WeatherEntity>();
								int len = 0;
								try
								{
									String day7 = obj.getString("day7");
									len = 7;
									// 正常执行表示有7天天气
								}
								catch(Exception e)
								{
									// 否则只有6天天气
									len = 6;
								}
								if (len == 6)
								{

									for (int i = 1; i <= 6; i++)
									{
										WeatherEntity w6 = new WeatherEntity();
										w6.setCity(obj.getString("city"));
										w6.setTempler(obj.getString("temp" + i));
										w6.setWeather(obj
												.getString("weather" + i));
										String sday6 = "";
										if (i == 1)
										{
											sday6 = "今天";
										}
										else if (i == 2)
										{
											sday6 = "明天";
										}
										else if (i == 3)
										{
											sday6 = "后天";
										}
										else
										{
											// 取得今天的日期
											String today = obj
													.getString("date_y");
											Date d = (Date) DateFormatUtils
													.formaToDate(today);
											Calendar c = Calendar.getInstance();
											c.setTime(d);
											c.add(Calendar.DATE, i);
											Date datetmp6 = (Date) c.getTime();
											sday6 = DateFormatUtils
													.formatToMMdd(datetmp6);
										}
										w6.setDate(sday6);
										w6.setWind(obj.getString("wind" + i));
										int imgnum = Integer
												.parseInt(obj
														.getString("img" + ((2 * i) - 1)));
										if (imgnum > 31)
										{// 大于所有图片的代号则去取晚上的图片
											imgnum = Integer
													.parseInt(obj
															.getString("img" + ((2 * i))));
										}
										w6.setPic(imgnum + ".png");
										weatherArray.add(w6);
									}
								}
								else if (len == 7)
								{

									for (int i = 1; i <= 7; i++)
									{
										WeatherEntity w7 = new WeatherEntity();
										w7.setCity(obj.getString("city"));
										w7.setTempler(obj.getString("temp" + i));
										w7.setWeather(obj
												.getString("weather" + i));
										String sday7 = "";
										if (i == 1)
										{
											sday7 = "今天";
										}
										else if (i == 2)
										{
											sday7 = "明天";
										}
										else if (i == 3)
										{
											sday7 = "后天";
										}
										else
										{
											// 取得今天的日期
											String today = obj
													.getString("date_y");
											Date d = (Date) DateFormatUtils
													.formaToDate(today);
											Calendar c = Calendar.getInstance();
											c.setTime(d);
											c.add(Calendar.DATE, i);
											Date datetmp6 = (Date) c.getTime();
											sday7 = DateFormatUtils
													.formatToMMdd(datetmp6);
										}
										w7.setDate(sday7);
										w7.setWind(obj.getString("wind" + i));
										int imgnum = Integer.parseInt(obj
												.getString("img" + i));
										if (imgnum > 31)
										{// 大于所有图片的代号则去取晚上的图片
											imgnum = Integer
													.parseInt(obj
															.getString("img_night" + i));
										}
										w7.setPic(imgnum + ".png");
										weatherArray.add(w7);
									}
								}
								Bitmap bm = null;
								String imgpath = weatherArray.get(0).getPic();
								try
								{
									bm = BitmapFactory
											.decodeStream(BallInfoActivity.this
													.getAssets().open(imgpath));
									if (bm != null)
									{
										img_whether.setImageBitmap(bm);
									}

									WeatherEntity w2 = weatherArray.get(1);
									tv_weather
											.setText(w2.getCity() + "\t" + w2
													.getDate() + "\n" + w2
													.getWeather() + "\n" + w2
													.getTempler());
								}
								catch(IOException e)
								{

								}
								layout_towheather.setVisibility(View.VISIBLE);// 显示天气块

							}
							catch(JSONException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
				});
	}

	// ********回调喽
	public void toBuy(BallInfoEntity.DataEntity.DistributorsEntity bean)
	{
		if (!ConstantValues.ISLOGIN)
		{
			Intent in = new Intent(this, LoginActivity.class);

			in.putExtra("flag", "other");

			this.startActivity(in);

			return;
		}

		Intent in = new Intent(this, OrderDialog.class);

		in.putExtra("type", "ball");// 存放预定的信息

		in.putExtra("ballname", ballname);// 存放预定的信息

		in.putExtra("date", date);// 打球时间

		in.putExtra("entity", bean);// 存放预定的信息

		in.putExtra("ballid", ballid);// 将得到ballid传入

		this.startActivity(in);

	}

}
