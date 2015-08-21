package me.wangolf.newfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.topnewgrid.bean.ChannelManage;
import com.example.topnewgrid.bean.KnowledgeChannelManage;
import com.example.topnewgrid.bean.ShopChannelManage;
import com.example.topnewgrid.db.SQLHelper;
import com.meigao.mgolf.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.analytics.MobclickAgent;

import java.util.Timer;
import java.util.TimerTask;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import me.wangolf.ConstantValues;
import me.wangolf.LocationApplication;
import me.wangolf.bean.usercenter.User;
import me.wangolf.bean.usercenter.UserInfoEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.fragment.IndexPage;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.usercenter.UserBindMobileActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.DBHelper;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ImageViewUtil;
import me.wangolf.utils.SharedPreferencesUtils;
import me.wangolf.utils.ToastUtils;

/**
 * fragment
 * 
 * @author andye
 */
public class MainActivity extends FragmentActivity implements OnClickListener
{

	private ImageView							mSelBg;

	private LinearLayout						mTab_item_container;

	private FragmentManager						mFM				= null;

	@ViewInject(R.id.tab_tv_1)
	private TextView							mTv1;																// 首页Tv

	@ViewInject(R.id.tab_tv_2)
	private TextView							mTv2;																// 球场Tv

	@ViewInject(R.id.tab_tv_3)
	private TextView							mTv3;																// 商城Tv

	@ViewInject(R.id.tab_tv_4)
	private TextView							mTv4;																// 社区Tv

	@ViewInject(R.id.tab_tv_5)
	private TextView							mTv5;																// 发现Tv

	private IndexFra							fIndex;																// 首页Fra

	private BallPracFra							fBallPra;															// 球场Fra

	private ShopFra								fShop;																// 商城Fra

	private CommunityFra						fCommunity;															// 社区Fra

	private UserCentenFra						fUserCenter;														// 发现Fra

	private int									mSelectIndex	= 0;

	private View								last, now;															// 动画视图

	private int									CurrentFlag;														// 当前点击的频道
																													// 0
																													// 首页
																													// 1球场
																													// 2
																													// 商城
																													// 3
																													// 社区
																													// 4
																													// 个人中心

	LinearLayout								content_container,
			content_container2;

	private LocationClient						mLocationClient;													// 定位

	private LocationClientOption.LocationMode	tempMode		= LocationClientOption.LocationMode.Hight_Accuracy;

	private String								tempcoor		= "bd09ll";											// 地址编码

	private int									isExit;															// 是否退出

	private long								lasttime		= 0;												// 点时间

	FragmentTransaction							ft;

	@SuppressLint("HandlerLeak")
	private Handler								handler			= new Handler()
																{
																	@Override
																	public void handleMessage(Message msg)
																	{
																		if (ConstantValues.isloaction)
																		{
																			// ToastUtils.showInfo(MainActivity.this,
																			// "定位成功");
																		}
																		else
																		{
																			ToastUtils
																					.showInfo(MainActivity.this, "定位失败，请打开GPS重新定位！");
																		}

																		mLocationClient
																				.stop();

																		super.handleMessage(msg);
																	}

																};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.main);

		mLocationClient = ((LocationApplication) getApplication()).mLocationClient; // 定位

		ViewUtils.inject(this);

		init();

		changeIndex();

	}

	private void init()
	{
		mTab_item_container = (LinearLayout) findViewById(R.id.tab_item_container);

		mTv1.setOnClickListener(this);

		mTv2.setOnClickListener(this);

		mTv3.setOnClickListener(this);

		mTv4.setOnClickListener(this);

		mTv5.setOnClickListener(this);

		initData();

		mSelBg = (ImageView) findViewById(R.id.tab_bg_view);

		content_container = (LinearLayout) findViewById(R.id.content_container);

		content_container2 = (LinearLayout) findViewById(R.id.content_container2);

		if (null == mFM)
		{
			mFM = getSupportFragmentManager();
		}

	}

	// 动画滚动width
	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);

		LayoutParams lp = mSelBg.getLayoutParams();

		lp.width = mTab_item_container.getWidth() / 5;

	}

	
	
	View	v1, v2;

	// 点击事件监听
	/* (非 Javadoc) 
	* <p>Title: onClick</p> 
	* <p>Description: 点击事件监听</p> 
	* @param arg0 
	* @see android.view.View.OnClickListener#onClick(android.view.View) 
	*/
	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
			case R.id.tab_tv_1:

				last = mTab_item_container.getChildAt(mSelectIndex);

				now = mTab_item_container.getChildAt(0);

				startAnimation(last, now);

				mSelectIndex = 0;

				CurrentFlag = 0;

				changeIndex();

				break;

			case R.id.tab_tv_2:

				last = mTab_item_container.getChildAt(mSelectIndex);

				now = mTab_item_container.getChildAt(1);

				startAnimation(last, now);

				mSelectIndex = 1;

				CurrentFlag = 1;

				changeBallPra();

				break;

			case R.id.tab_tv_3:

				last = mTab_item_container.getChildAt(mSelectIndex);

				now = mTab_item_container.getChildAt(2);

				startAnimation(last, now);

				mSelectIndex = 2;

				CurrentFlag = 2;

				changeShop();

				break;

			case R.id.tab_tv_4:

				last = mTab_item_container.getChildAt(mSelectIndex);

				now = mTab_item_container.getChildAt(3);

				startAnimation(last, now);

				mSelectIndex = 3;

				CurrentFlag = 3;

				changeCommunity();

				break;

			case R.id.tab_tv_5:

				last = mTab_item_container.getChildAt(mSelectIndex);

				now = mTab_item_container.getChildAt(4);

				startAnimation(last, now);

				mSelectIndex = 4;

				changeUserContent();

				break;

			default:

				break;

		}
	}


	/** 
	* @Title: startAnimation 
	* @Description: 设置滚动动画 
	* @param @param last
	* @param @param now    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void startAnimation(View last, View now)
	{
		TranslateAnimation ta = new TranslateAnimation(last.getLeft(), now.getLeft(), 0, 0);

		ta.setDuration(300);

		ta.setFillAfter(true);

		mSelBg.startAnimation(ta);
	}


	
	/** 
	* @Title: changeIndex 
	* @Description: 切换主页fragement 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void changeIndex()
	{
		ft = mFM.beginTransaction();

		hideFragments(ft);

		if (null == fIndex)
		{
			fIndex = new IndexFra();

			ft.add(R.id.content_container, fIndex);

		}
		else
		{
			ft.show(fIndex);
		}

		// ft.commit();
		mTv1.setTextColor(getResources().getColor(R.color.main_text_select));

		mTv1.setCompoundDrawablesWithIntrinsicBounds(null, getResources()
				.getDrawable(R.drawable.main_index_select), null, null);

		ft.commitAllowingStateLoss();
	}


	/** 
	* @Title: changeBallPra 
	* @Description: 切换球场fragement
	* @param     
	* @return void    返回类型 
	* @throws 
	*/
	public void changeBallPra()
	{
		ft = mFM.beginTransaction();
		hideFragments(ft);
		if (null == fBallPra)
		{
			fBallPra = new BallPracFra();
			ft.add(R.id.content_container, fBallPra);
		}
		else
		{
			ft.show(fBallPra);
		}
		// ft.commit();
		mTv2.setTextColor(getResources().getColor(R.color.main_text_select));
		mTv2.setCompoundDrawablesWithIntrinsicBounds(null, getResources()
				.getDrawable(R.drawable.main_ballprac_select), null, null);
		ft.commitAllowingStateLoss();
	}

	
	/** 
	* @Title: changeShop 
	* @Description: 切换商城fragement 
	* @param     
	* @return void    返回类型 
	* @throws 
	*/
	public void changeShop()
	{
		ft = mFM.beginTransaction();
		hideFragments(ft);
		if (null == fShop)
		{
			fShop = new ShopFra();
			ft.add(R.id.content_container, fShop);
		}
		else
		{
			ft.show(fShop);
		}
		// ft.commit();
		ft.commitAllowingStateLoss();
	}


	/** 
	* @Title: changeCommunity 
	* @Description: 切换到社区fragement
	* @param     
	* @return void    返回类型 
	* @throws 
	*/
	public void changeCommunity()
	{
		ft = mFM.beginTransaction();
		hideFragments(ft);
		if (null == fCommunity)
		{
			fCommunity = new CommunityFra();
			ft.add(R.id.content_container, fCommunity);
		}
		else
		{
			ft.show(fCommunity);
		}
		// ft.commit();
		mTv4.setTextColor(getResources().getColor(R.color.main_text_select));
		mTv4.setCompoundDrawablesWithIntrinsicBounds(null, getResources()
				.getDrawable(R.drawable.main_community_select), null, null);
		ft.commitAllowingStateLoss();
	}

	
	/** 
	* @Title: changeUserContent 
	* @Description: 切换fragement 
	* @param     
	* @return void    返回类型 
	* @throws 
	*/
	public void changeUserContent()
	{
		if (!ConstantValues.ISLOGIN)
		{
			// 去登录
			Intent toLogin = new Intent(this, LoginActivity.class);

			toLogin.putExtra("flag", "userlogin");

			this.startActivityForResult(toLogin, 100);

			return;
		}

		ft = mFM.beginTransaction();

		hideFragments(ft);

		if (null == fUserCenter)
		{
			fUserCenter = new UserCentenFra();

			ft.add(R.id.content_container, fUserCenter);
		}
		else
		{
			fUserCenter.getUpData();

			ft.show(fUserCenter);
		}
		// ft.commit();
		mTv5.setTextColor(getResources().getColor(R.color.main_text_select));

		mTv5.setCompoundDrawablesWithIntrinsicBounds(null, getResources()
				.getDrawable(R.drawable.main_user_select), null, null);

		ft.commitAllowingStateLoss();
	}

	
	
	/** 
	* @Title: hideFragments 
	* @Description: 将所有的Fragment都置为隐藏状态。
	* @param @param transaction    
	* @return void    返回类型 
	* @throws 
	*/
	private void hideFragments(FragmentTransaction transaction)
	{
		if (fIndex != null)
		{
			transaction.hide(fIndex);
		}
		if (fBallPra != null)
		{
			transaction.hide(fBallPra);
		}
		if (fShop != null)
		{
			transaction.hide(fShop);
		}
		if (fCommunity != null)
		{
			transaction.hide(fCommunity);
		}
		if (fUserCenter != null)
		{
			transaction.hide(fUserCenter);
		}
		
		mTv1.setCompoundDrawablesWithIntrinsicBounds(null, getResources()
				.getDrawable(R.drawable.main_index), null, null);
		mTv2.setCompoundDrawablesWithIntrinsicBounds(null, getResources()
				.getDrawable(R.drawable.main_ballprac), null, null);
		mTv4.setCompoundDrawablesWithIntrinsicBounds(null, getResources()
				.getDrawable(R.drawable.main_community), null, null);
		mTv5.setCompoundDrawablesWithIntrinsicBounds(null, getResources()
				.getDrawable(R.drawable.main_user), null, null);
		mTv1.setTextColor(getResources().getColor(R.color.main_text));
		mTv2.setTextColor(getResources().getColor(R.color.main_text));
		mTv4.setTextColor(getResources().getColor(R.color.main_text));
		mTv5.setTextColor(getResources().getColor(R.color.main_text));
	}

	/**
	 * @Title: getScreenWidth
	 * @Description: 获取手机屏宽高
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	private int getScreenWidth()
	{
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		ConstantValues.SCREENWIDTH = screenWidth;
		int screenHeight = dm.heightPixels;
		ConstantValues.SCREENHEIGHT = screenHeight;
		return screenWidth;
	}

	/**
	 * @Title: initData
	 * @Description: 初始化程序使用数据
	 * @param
	 * @return void 返回类型
	 * @throws
	 */
	private void initData()
	{
		ChannelManage CollgegChannle = new ChannelManage();
		CollgegChannle.initData(this);// 初始学院化频道数据存入数据库
		ShopChannelManage ShopChannle = new ShopChannelManage();
		ShopChannle.initData(this);// 初始学院化频道数据存入数据库
		KnowledgeChannelManage KonwledgeChannle = new KnowledgeChannelManage();
		KonwledgeChannle.initData(this);// 初始高球常识频道数据存入数据库

		initDatabase();// 导入城市数据库
		ConstantValues.CITYNAME = "深圳";// 默认城市名称
		ConstantValues.CITYID = 77;// 默认城市ID
		ConstantValues.UUID = Settings.Secure.getString(getApplicationContext()
				.getContentResolver(), Settings.Secure.ANDROID_ID); // 手机唯一标识
		getScreenWidth();// 手机屏宽度
		InitLocation();// 定位
		mLocationClient.start();
		initDatabase();// 导入城市数据库
		login();// 缓存登录
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		if (requestCode == 100)
		{
			isLogin();
		}
		if (requestCode == 101)
		{
			// 退出用户中心
			if (ConstantValues.ISLOGIN) { return; }
			last = mTab_item_container.getChildAt(mSelectIndex);
			now = mTab_item_container.getChildAt(0);
			startAnimation(last, now);
			mSelectIndex = 0;
			changeIndex();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * @Title: isLogin
	 * @Description: 判断登录成功或从登录页返回
	 * @param
	 * @return void 返回类型
	 * @throws
	 */
	public void isLogin()
	{
		// 登录成功，进入用户中心,否则如果是返回，回到原频道
		if (ConstantValues.ISLOGIN)
		{
			changeUserContent();
		}
		else
		{
			last = mTab_item_container.getChildAt(CurrentFlag);

			now = mTab_item_container.getChildAt(CurrentFlag);

			startAnimation(last, now);

			mSelectIndex = CurrentFlag;

			switch (CurrentFlag)
			{
				case 0:

					changeIndex();

					break;

				case 1:

					changeBallPra();

					break;

				case 2:

					changeShop();

					break;

				case 3:

					changeCommunity();

					break;
			}
		}
	}

	/**
	 * @Title: initDatabase
	 * @Description: 初始化数据库
	 * @param
	 * @return void 返回类型
	 * @throws
	 */
	public void initDatabase()
	{
		DBHelper dbHelper = new DBHelper(this);
		dbHelper.openDatabase();
	}

	/**
	 * @Title: InitLocation
	 * @Description: 设置百度地图定位参数
	 * @param
	 * @return void 返回类型
	 * @throws
	 */
	private void InitLocation()
	{
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(tempMode);// 设置定位模式
		option.setCoorType(tempcoor);// 返回的定位结果是百度经纬度，默认值gcj02
		int span = 5000;
		option.setScanSpan(span);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		// option.setIsNeedAddress(checkGeoLocation.isChecked());
		mLocationClient.setLocOption(option);
		handler.sendEmptyMessageDelayed(103, 30000);

	}

	/**
	 * @Title: login
	 * @Description: 使用缓存用户登录
	 * @param
	 * @return void 返回类型
	 * @throws
	 */
	public void login()
	{
		final String cache_user = SharedPreferencesUtils
				.getString(this, "mgolf_n");

		final String cache_pwd = SharedPreferencesUtils
				.getString(this, "mgolf_p");

		final String wx_open_id = SharedPreferencesUtils
				.getString(this, "mgolf_uid");

		if (!CheckUtils.checkEmpty(cache_user) & !CheckUtils
				.checkEmpty(cache_pwd))
		{
			User u = new User();

			u.setUsername(cache_user);

			u.setPassword(cache_pwd);

			try
			{
				ServiceFactory.getIUserEngineInstatice()
						.UserLogin(u, new IOAuthCallBack()
						{

							@Override
							public void getIOAuthCallBack(String result)
							{
								// Log.i("wangolf",result);
								if (result.equals(ConstantValues.FAILURE))
								{
									ToastUtils.showInfo(MainActivity.this, ConstantValues.NONETWORK);
								}
								else
								{
									UserInfoEntity user = GsonTools
											.changeGsonToBean(result, UserInfoEntity.class);

									if (user.getStatus().equals("1"))
									{
										UserInfoEntity.DataEntity userinfo = user
												.getData().get(0);

										ConstantValues.ISLOGIN = true;

										ConstantValues.HOME_ISLOGIN = true;

										ConstantValues.USER_MOBILE = cache_user;

										// if(
										// !CheckUtils.checkEmpty(userinfo.getWeixin_open_id()))
										//
										// ConstantValues.ISWXlOGIN =true;
										// else
										// ConstantValues.ISWXlOGIN =false;

										ConstantValues.PASSWORD = cache_pwd;

										if (!CheckUtils.checkEmpty(userinfo
												.getNick_name()) & !CheckUtils
												.checkEmpty(userinfo
														.getAvatar()))
											ConstantValues.ISCOMPLETEINFO = true;

										ConstantValues.UID = userinfo
												.getUser_id();

										ConstantValues.UNIQUE_KEY = userinfo
												.getUnique_key();

									}
									else
									{
										ToastUtils
												.showInfo(MainActivity.this, user
														.getInfo());
									}
								}
							}
						});

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (!CheckUtils.checkEmpty(wx_open_id))
		{
			// 微信登录
			toWxLogin(wx_open_id);
		}
	}

	// 微信登陆
	private void toWxLogin1(String wx_openid)
	{
		try
		{
			ServiceFactory.getIUserEngineInstatice()
					.toWxLogin(wx_openid, "", "", "", new IOAuthCallBack()
					{
						@Override
						public void getIOAuthCallBack(String result)
						{
							if (result.equals(ConstantValues.FAILURE))
							{
								ToastUtils.showInfo(MainActivity.this, ConstantValues.NONETWORK);
							}
							else
							{
								UserInfoEntity bean = GsonTools
										.changeGsonToBean(result, UserInfoEntity.class);

								if ("1".equals(bean.getStatus()))
								{
									// 登录成功且已绑定手机号
									UserInfoEntity.DataEntity userinfo = bean
											.getData().get(0);
									// setCache("wx_open_rid",
									// userinfo.getWeixin_open_id());//缓存用户名
									ConstantValues.ISLOGIN = true;

									ConstantValues.USER_MOBILE = userinfo
											.getMobile();

									ConstantValues.ISWXlOGIN = true;

									// ConstantValues.OPEN_ID=userinfo.getWeixin_open_id();

									ConstantValues.UID = userinfo.getUser_id();

									// ToastUtils.showInfo(LoginActivity.this,
									// "登录成功");
									// 取消的字段
									// |!CheckUtils.checkEmpty(userinfo.getWeixin_avatar())
									if (!CheckUtils.checkEmpty(userinfo
											.getNick_name()) & (!CheckUtils
											.checkEmpty(userinfo.getAvatar())))

										ConstantValues.ISCOMPLETEINFO = true;

									else

										ConstantValues.ISCOMPLETEINFO = false;

								}

							}
						}
					});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void toWxLogin(String wx_openid)
	{
		try
		{
			ServiceFactory.getIUserEngineInstatice()
					.getUserInfo(wx_openid, new IOAuthCallBack()
					{
						@Override
						public void getIOAuthCallBack(String result)
						{

							if (result.equals(ConstantValues.FAILURE))
							{
								ToastUtils.showInfo(MainActivity.this, ConstantValues.NONETWORK);
							}
							else
							{
								// Log.i("wangolf",result);
								UserInfoEntity user = GsonTools
										.changeGsonToBean(result, UserInfoEntity.class);

								if ("1".equals(user.getStatus()))
								{
									// 登录成功且已绑定手机号
									UserInfoEntity.DataEntity userinfo = user
											.getData().get(0);
									// setCache("wx_open_rid",
									// userinfo.getWeixin_open_id());//缓存用户名
									ConstantValues.ISLOGIN = true;

									ConstantValues.USER_MOBILE = userinfo
											.getMobile();

									ConstantValues.ISWXlOGIN = true;

									// ConstantValues.OPEN_ID=userinfo.getWeixin_open_id();

									ConstantValues.UID = userinfo.getUser_id();

									// ToastUtils.showInfo(LoginActivity.this,
									// "登录成功");

									// |!CheckUtils.checkEmpty(userinfo.getWeixin_avatar())

									if (!CheckUtils.checkEmpty(userinfo
											.getNick_name()) & (!CheckUtils
											.checkEmpty(userinfo.getAvatar())))

										ConstantValues.ISCOMPLETEINFO = true;

									else

										ConstantValues.ISCOMPLETEINFO = false;
								}

							}
						}
					});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	// ============记录返回事件============
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			long currenttime = System.currentTimeMillis();

			if ((currenttime - lasttime) > 1000)
			{
				this.isExit = 0;

				lasttime = currenttime;
			}

			if (this.isExit == 0)
			{
				this.isExit++;

				ToastUtils.showInfo(this, "点击两次退出");

				return true;
			}

			if (this.isExit == 1)
			{
				android.os.Process.killProcess(android.os.Process.myPid());
			}

			return false;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		MobclickAgent.onPause(this);
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		MobclickAgent.onResume(this);
	}
}
