package me.wangolf.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;
import com.umeng.common.message.Log;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager.LayoutParams;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import me.wangolf.ConstantValues;
import me.wangolf.adapter.MainAdapter;
import me.wangolf.ballprac.BallInfoActivity;
import me.wangolf.base.Mo_BasePage;
import me.wangolf.bean.HomePageAdsEntity;
import me.wangolf.bean.event.EventEntity;
import me.wangolf.college.CollegePage;
import me.wangolf.event.EventDetailActivity;
import me.wangolf.event.EventMainActivity;
import me.wangolf.event.EventnNoticeActivity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.practice.PracticeInfoActivity;
import me.wangolf.practice.PracticeListActivity;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.shop.ShopProActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.SharedPreferencesUtils;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshBase.OnRefreshListener;
import me.wangolf.utils.viewUtils.PullToRefreshListView;
import me.wangolf.utils.viewUtils.RollViewPager;
import me.wangolf.utils.viewUtils.RollViewPager.OnPagerClickCallback;

public class IndexPage extends Mo_BasePage implements OnClickListener {
	@ViewInject(R.id.pull_refresh_list)
	private PullToRefreshListView pull_refresh_list;
	@ViewInject(R.id.practice)
	private TextView practice;// 练习场
	@ViewInject(R.id.ballsearch)
	private TextView ballsearch; // 球场
	@ViewInject(R.id.event)
	private ImageView event;// 最新活动
	@ViewInject(R.id.more_event)
	private RelativeLayout more_event;// 更多活动

	private MainAdapter adapter;
	private String time;
	private String page = "1";
	private String number = "10";
	private String version = "5";
	private String recommend = "1"; // 0代表普通活动1代表首页活动

	private RollViewPager Rpage; //
	private ArrayList<View> dotList; // 链接
	@ViewInject(R.id.dots_ll)
	private LinearLayout dots_ll; // 填充滚动viewpage点
	@ViewInject(R.id.shop_viewpager)
	private LinearLayout shop_viewpager; // 滚动图
	private List<String> titles;
	private ArrayList<String> urlList = new ArrayList<String>();
	private List<HomePageAdsEntity.DataEntity> data;
	private boolean isgetdata = true;// 是否是第一次拿数据
	private ArrayList<EventEntity> event_data;
	private Dialog dialog;
	public boolean isnoserver;// 连接不到服务器

	public IndexPage(Context context)
	{
		super(context);

	}

	@Override
	public View initView(LayoutInflater inflater) 
	{
		View view = inflater.inflate(R.layout.ac_index, null);
		
		View head = inflater.inflate(R.layout.head_main, null);
		
		View foot = inflater.inflate(R.layout.main_list_foot, null);
		
		ViewUtils.inject(this, view);
		
		ViewUtils.inject(this, head);
		
		ViewUtils.inject(this, foot);

		if (adapter == null)
		{
			adapter = new MainAdapter(context);
		} 
		else 
		{
			adapter.notifyDataSetChanged();
		}
		
		pull_refresh_list.setPullLoadEnabled(false);
		// 滚动到底自动加载可用
		pull_refresh_list.setScrollLoadEnabled(false);
		// 得到实际的ListView 设置点击
		pull_refresh_list.getRefreshableView().setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{

				if (CommonUtil.isNetworkAvailable(context) == 0) 
				{
					Toast.makeText(context, ConstantValues.NONETWORK, 0).show();
				} 
				else 
				{
					if (event_data != null) 
					{
						EventEntity bean = (EventEntity) adapter.getItem(position - 1);
						
						Intent intent = new Intent(context, EventDetailActivity.class);
						
						intent.putExtra("eventid", bean.getId() + "");
						
						intent.putExtra("submit", bean.getSubmit() + "");
						
						intent.putExtra("price", bean.getPrice() + "");
						
						intent.putExtra("logo", bean.getLogo());
						
						intent.putExtra("title", bean.getTitle());
						
						context.startActivity(intent);
					}
				}
			}
		});

		// 设置下拉刷新的listener
		pull_refresh_list.setOnRefreshListener(new OnRefreshListener<ListView>()
		{

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) 
			{
				// initData();
				if (CommonUtil.isNetworkAvailable(context) == 0 | isnoserver) 
				{
					Toast.makeText(context, ConstantValues.NONETWORK, 0).show();
				} 
				else 
				{
					isnoserver= false;
					
					urlList = new ArrayList<String>();
					
					getData();
				}

				onLoaded(); // 加载完 关闭加载框
				// initDot(urlList.size());// 初始化滚动图
				// initRoll();
				// refreshView.findViewById(R.id.pull_to_refresh_header_content).setVisibility(8);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				onLoaded(); // 加载完 关闭加载框

			}
		});
		
		pull_refresh_list.getRefreshableView().addHeaderView(head);
		
		pull_refresh_list.getRefreshableView().addFooterView(foot);
		
		pull_refresh_list.getRefreshableView().setAdapter(adapter);
		
		return view;
	}

	@Override
	public void initData()
	{
		dialog = DialogUtil.getDialog(context);
		
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		time = sDateFormat.format(new java.util.Date());
		
		practice.setOnClickListener(this);
		
		ballsearch.setOnClickListener(this);
		
		event.setOnClickListener(this);
		
		more_event.setOnClickListener(this);
		
		String cache_1 = SharedPreferencesUtils.getString(context, "index_list" + ConstantValues.versionCode);
		
		String cache_2 = SharedPreferencesUtils.getString(context, "index_adv" + ConstantValues.versionCode);
		
		if (urlList.size() == 0)
		{
			if (!TextUtils.isEmpty(cache_1)) 
			{
				ProcessListdata(cache_1.toString());
			}
			
			if (!CheckUtils.checkEmpty(cache_2)) 
			{
				ProcessAdVdata(cache_2.toString());
			} 
			else 
			{
				dialog.show();
			}
		}
		
		// 到服务器拿数据
		if (isgetdata)
		{
			if (CommonUtil.isNetworkAvailable(context) == 0)
			{
			} 
			else 
			{
				getData();
			}
			
		} 
		else if (!isnoserver) 
		{
			initDot(urlList.size());// 初始化滚动图
			
			initRoll();
		}
	}

	public void getData() 
	{

		// dialog.show();
		getEventData();
		
		getAdvData();

	}

	public void getEventData()
	{
		// 首页活动
		try 
		{
			ServiceFactory.getEventEngineInstatice().getEventList(time, page, number, version, recommend, new IOAuthCallBack() {

				@Override
				public void getIOAuthCallBack(String result) 
				{
					toCacheData(result, "index_list" + ConstantValues.versionCode);
					
					ProcessListdata(result);
				}
			});
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	
	
	public void getAdvData()
	{
		// 首页广告
		try 
		{
//			ServiceFactory.getIndexEngineInstatice().getIndexAdv(new IOAuthCallBack() 
			ServiceFactory.getIndexEngineInstatice().getIndexAdv(page, number, version, new IOAuthCallBack()
			{
				@Override
				public void getIOAuthCallBack(String result)
				{
					
					toCacheData(result, "index_adv" + ConstantValues.versionCode);
					
					ProcessAdVdata(result);
					
					isgetdata = false;
				}

			});
			// dialog.cancel();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void onClick(View v) 
	{
		if (CommonUtil.isNetworkAvailable(context) == 0) {
			Toast.makeText(context, ConstantValues.NONETWORK, 0).show();
		} else {
			switch (v.getId()) {
			case R.id.event:
				Intent event = new Intent(context, EventMainActivity.class);
				context.startActivity(event);
				break;
			case R.id.more_event:
				Intent more_event = new Intent(context, EventMainActivity.class);
				context.startActivity(more_event);
				break;
			case R.id.practice:
				Intent practice = new Intent(context, PracticeListActivity.class);
				context.startActivity(practice);
				break;
			case R.id.ballsearch:
				// Intent ball = new Intent(context,
				// BallPracSearchActivity.class);
				// context.startActivity(ball);
				Intent ball = new Intent(context, CollegePage.class);
				context.startActivity(ball);
				break;
			default:
				break;
			}
		}
	}

	public void initRoll() {

		// ================滚动图片数据============= 滚动图初始化数据
		Rpage = new RollViewPager(context, dotList, R.drawable.dot_focus, R.drawable.dot_normal, new OnPagerClickCallback() {
			@Override
			public void onPagerClick(int position) 
			{
				if (urlList.size() > 0) 
				{
					if (CommonUtil.isNetworkAvailable(context) == 0) 
					{
						Toast.makeText(context, ConstantValues.NONETWORK, 0).show();
					} 
					else 
					{
						if (data == null) 
						{
							return;
						}
						
						String advid = data.get(position).getAdv_id();
						
						int type = Integer.valueOf(data.get(position).getType());

						switch (type) 
						{
						case 0:
							
							Intent ball = new Intent(context, BallInfoActivity.class); // 球场ID跳转到球场
							
							ball.putExtra("ballid", advid);
							
							context.startActivity(ball);
							
							break;
							
						case 1:
							
							Intent practice = new Intent(context, PracticeInfoActivity.class);// 练习场ID跳转到练习场
							
							practice.putExtra("rgid", advid);
							
							practice.putExtra("cityname", ConstantValues.CITYNAME);
							
							context.startActivity(practice);
							
							break;
							
						case 2:
							
							Intent event = new Intent(context, EventDetailActivity.class);// 活动ID跳转到活动
							
							event.putExtra("eventid", advid);
							
							context.startActivity(event);
							
							break;
						case 3:
							
							Intent shop = new Intent(context, ShopProActivity.class);// 商品ID跳转到商品
							
							shop.putExtra("proid", advid);
							
							context.startActivity(shop);
							
							break;
							
						case 5:
							
//							String code = data.get(position).getCode();
							
							Intent event_notice = new Intent(context, EventnNoticeActivity.class);// 活动公告
							
							event_notice.putExtra("noticeid", advid);
							
//							event_notice.putExtra("code", code);
							
							context.startActivity(event_notice);
							
							break;
							
						default:
							break;
							
						}
					}
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
	private void initDot(int size) 
	{
		dotList = new ArrayList<View>();
		
		dots_ll.removeAllViews();
		
		for (int i = 0; i < size; i++) 
		{
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CommonUtil.dip2px(context, 6), CommonUtil.dip2px(context, 6));
			
			params.setMargins(5, 0, 5, 0);
			View m = new View(context);
			
			if (i == 0) 
			{
				m.setBackgroundResource(R.drawable.dot_focus);
			} 
			else 
			{
				m.setBackgroundResource(R.drawable.dot_normal);
			}
			
			m.setLayoutParams(params);
			
			dots_ll.addView(m);
			
			dotList.add(m);
		}
	}

	// 处理list数据
	public void ProcessListdata(String result)
	{
		if (result.equals(ConstantValues.FAILURE)) 
		{
			Toast.makeText(context, ConstantValues.NONETWORK, 0).show();
		} 
		else 
		{
			EventEntity bean = GsonTools.changeGsonToBean(result, EventEntity.class);
			
			if ("1".equals(bean.getStatus())) 
			{
				if (bean.getData() != null)
				{

					event_data = bean.getData();
					
					adapter.getList().clear();
					
					adapter.setList(event_data);
					
					adapter.notifyDataSetChanged();
				}
			}
		}
	}
	

	// 处理广告数据
	public void ProcessAdVdata(String result) 
	{
		if (result.equals(ConstantValues.FAILURE)) 
		{
			isnoserver = true;
			
			Toast.makeText(context, ConstantValues.NONETWORK, 0).show();
		} 
		else 
		{
			HomePageAdsEntity bean = GsonTools.changeGsonToBean(result, HomePageAdsEntity.class);
			
			if ("1".equals(bean.getStatus()))
			{
				data = bean.getData();
				
				urlList.clear();
				
				for (int i = 0; i < data.size(); i++) 
				{
					String path = data.get(i).getIcon();
					
//					if (!CheckUtils.checkEmpty(path)) 
//					{
//						String[] s = path.split(",");
//						
//						path = s[0].substring(0, s[0].lastIndexOf(".")) + "_640_395" + s[0].substring(s[0].lastIndexOf("."));
//					}
					
					urlList.add(path);

				}
				
				initDot(urlList.size());// 初始化滚动图
				
				initRoll();
			}

		}
		dialog.cancel();
	}

	// 本地缓存
	public void toCacheData(String result, String name) 
	{
		SharedPreferencesUtils.saveString(context, name, result);
	}

	private void setLastUpdateTime() 
	{
		String text = CommonUtil.getStringDate();
		pull_refresh_list.setLastUpdatedLabel(text);
	}

	private void onLoaded() {
		dismissLoadingView();
		pull_refresh_list.onPullDownRefreshComplete();
		pull_refresh_list.onPullUpRefreshComplete();
	}
}
