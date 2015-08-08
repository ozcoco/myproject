package me.wangolf;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 下午4:12:48
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 下午4:12:48
 * 
 * 描述 ：主程主页main
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.example.topnewgrid.bean.ChannelManage;
import com.meigao.mgolf.R;
import com.umeng.analytics.MobclickAgent;
import me.wangolf.bean.usercenter.User;
import me.wangolf.bean.usercenter.UserInfoEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.fragment.HomeFragment;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.DBHelper;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.SharedPreferencesUtils;
import me.wangolf.utils.ToastUtils;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.Secure;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;

public class MainActivity extends FragmentActivity 
{
	private int isExit; // 是否退出
	
	private long lasttime = 0;// 点时间
	
	private LocationClient mLocationClient;// 定位
	
	private LocationMode tempMode = LocationMode.Hight_Accuracy;
	
	private String tempcoor = "bd09ll";// 地址编码

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() 
	{
		@Override
		public void handleMessage(Message msg) 
		{
			
			if (ConstantValues.isloaction)
			{
				
			}
			else 
			{
				ToastUtils.showInfo(MainActivity.this, "定位失败，请打开GPS重新定位！");
			}
			
			mLocationClient.stop();
			
			super.handleMessage(msg);
		}

	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.mg_main_content);
		
		mLocationClient = ((LocationApplication) getApplication()).mLocationClient; // 定位
		
		HomeFragment homeFragment = new HomeFragment();
		
		getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeFragment, "Home").commit();
		
		initData();// 初始化全局数据

	}

	
	public void initData() 
	{
		//ChannelManage.initData(this);// 初始化频道数据存入数据库
		ConstantValues.CITYNAME = "深圳";
		
		ConstantValues.CITYID = 77;
		
		ConstantValues.UUID = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID); // 手机唯一标识
		
		getScreenWidth();// 手机屏宽度
		
		InitLocation();// 定位
		
		mLocationClient.start();
		
		initDatabase();// 导入城市数据库
		
		login();// 缓存登录
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


	// 获取手机屏宽高
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

	// 设置百度地图定位参数
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

	// 初始化数据库
	public void initDatabase() 
	{
		DBHelper dbHelper = new DBHelper(this);
		
		dbHelper.openDatabase();
	}

	// 使用缓存用户登录
	public void login() 
	{
		final String cache_user = SharedPreferencesUtils.getString(this, "mgolf_n");
		
		String cache_pwd = SharedPreferencesUtils.getString(this, "mgolf_p");
		
		//Log.i("wangolf","***********************"+cache_pwd+cache_user);
		if (!CheckUtils.checkEmpty(cache_user) & !CheckUtils.checkEmpty(cache_pwd)) 
		{
			//Log.i("wangolf","***********************");
			User u = new User();
			
			u.setUsername(cache_user);
			
			u.setPassword(cache_pwd);
			
			try {
				
				ServiceFactory.getIUserEngineInstatice().UserLogin(u, new IOAuthCallBack() 
				{

					@Override
					public void getIOAuthCallBack(String result)
					{
						//Log.i("wangolf",result);
						if (result.equals(ConstantValues.FAILURE)) 
						{
							ToastUtils.showInfo(MainActivity.this, ConstantValues.NONETWORK);
						} 
						else
						{
							UserInfoEntity user = GsonTools.changeGsonToBean(result, UserInfoEntity.class);

							if (user.getStatus().equals("1")) 
							{
								UserInfoEntity.DataEntity userinfo = user.getData().get(0);
								
								ConstantValues.ISLOGIN = true;
								
								ConstantValues.HOME_ISLOGIN = true;
								
								ConstantValues.USER_MOBILE = cache_user;
								
								if(!CheckUtils.checkEmpty(userinfo.getNick_name())&!CheckUtils.checkEmpty(userinfo.getAvatar()))
									
								ConstantValues.ISCOMPLETEINFO = false;
								
								ConstantValues.UID = userinfo.getUser_id();
								
							} 
							else 
							{
								ToastUtils.showInfo(MainActivity.this, user.getInfo());
							}
						}
					}
				});
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}
