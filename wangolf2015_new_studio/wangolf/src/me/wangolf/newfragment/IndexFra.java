package me.wangolf.newfragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.MainAdapter;
import me.wangolf.ballprac.BallInfoActivity;
import me.wangolf.ballprac.BallMainActivity;
import me.wangolf.base.BaseFragment;
import me.wangolf.bean.HomePageAdsEntity;
import me.wangolf.bean.event.EventEntity;
import me.wangolf.college.CollegeCollegePage;
import me.wangolf.college.CollegePage;
import me.wangolf.event.EventDetailActivity;
import me.wangolf.event.EventnNoticeActivity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.knowledge.KnowledgePageActivity;
import me.wangolf.myteam.MyTeamMainActivity;
import me.wangolf.practice.PracticeInfoActivity;
import me.wangolf.practice.PracticeListActivity;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.shop.ShopIndexActivity;
import me.wangolf.shop.ShopProActivity;
import me.wangolf.usercenter.RechargeActivity;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.SharedPreferencesUtils;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.viewUtils.PullToRefreshListView;
import me.wangolf.utils.viewUtils.RollViewPager;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.topnewgrid.bean.ChannelManage;
import com.example.topnewgrid.bean.KnowledgeChannelManage;
import com.example.topnewgrid.bean.ShopChannelManage;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

public class IndexFra extends BaseFragment implements OnClickListener
{

	@ViewInject(R.id.pull_refresh_list)
	private PullToRefreshListView				pull_refresh_list;

	@ViewInject(R.id.index_list)
	private ListView							mList;

	// @ViewInject(R.id.tv_Saleexchange)
	// private TextView mSaleexchange;

	@ViewInject(R.id.txt_action)
	private Button								txt_action;

	@ViewInject(R.id.txt_ballpark)
	private Button								txt_ballpark;

	@ViewInject(R.id.txt_college)
	private Button								txt_college;

	@ViewInject(R.id.txt_knowledge)
	private Button								txt_knowledge;

	@ViewInject(R.id.txt_myteam)
	private Button								txt_myteam;

	@ViewInject(R.id.txt_practice)
	private Button								txt_practice;

	@ViewInject(R.id.txt_store)
	private Button								txt_store;

	@ViewInject(R.id.btn_topup)
	private Button								btn_topup;

	private MainAdapter							adapter;

	private String								time;

	private String								page		= "1";

	private String								number		= "10";

	private String								version		= "5";

	private String								recommend	= "1";						// 0代表普通活动1代表首页活动

	private RollViewPager						Rpage;									//

	private ArrayList<View>						dotList;								// 链接

	@ViewInject(R.id.dots_ll)
	private LinearLayout						dots_ll;								// 填充滚动viewpage点

	@ViewInject(R.id.shop_viewpager)
	private LinearLayout						shop_viewpager;						// 滚动图

	private ArrayList<String>					titles;

	private ArrayList<String>					urlList		= new ArrayList<String>();

	private List<HomePageAdsEntity.DataEntity>	data;

	private boolean								isgetdata	= true;					// 是否是第一次拿数据

	private ArrayList<EventEntity>				event_data;

	private Dialog								dialog;

	public boolean								isnoserver;							// 连接不到服务器

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{


		View head = inflater.inflate(R.layout.fragment_index_new, null);

		ViewUtils.inject(this, head);

		initData();

		return head;
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void initData()
	{
		dialog = DialogUtil.getDialog(getActivity());

		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		time = sDateFormat.format(new java.util.Date());

		txt_action.setOnClickListener(this);

		txt_ballpark.setOnClickListener(this);

		txt_college.setOnClickListener(this);

		txt_knowledge.setOnClickListener(this);

		txt_myteam.setOnClickListener(this);

		txt_practice.setOnClickListener(this);

		txt_store.setOnClickListener(this);

		btn_topup.setOnClickListener(this);
		
		String cache_2 = SharedPreferencesUtils
				.getString(getActivity(), "index_adv" + ConstantValues.versionCode);

		// 到服务器拿数据
		if (isgetdata)
		{
			if (CommonUtil.isNetworkAvailable(getActivity()) == 0)
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

		getAdvData();

	}

	public void getAdvData()
	{
		// 首页广告
		try
		{

			ServiceFactory.getIndexEngineInstatice()
					.getIndexAdv(new IOAuthCallBack()
					{
						@Override
						public void getIOAuthCallBack(String result)
						{

							toCacheData(result, "index_adv" + ConstantValues.versionCode);

							ProcessAdVdata(result);

							isgetdata = false;
						}

					});

		}
		catch(Exception e)
		{
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
			switch (v.getId())
			{

				case R.id.txt_action:

					onAction(null);

					break;

				case R.id.txt_ballpark:

					onBallpark(null);

					break;

				case R.id.txt_college:

					onCollege(null);

					break;

				case R.id.txt_myteam:

					onMyTeam(null);

					break;

				case R.id.txt_practice:

					onPractice(null);

					break;

				case R.id.txt_store:

					onStore(null);

					break;

				case R.id.txt_knowledge:

					onKnowledge(null);

					break;
					
				case R.id.btn_topup:

					onTopUp(null);

					break;

				case R.id.tv_Saleexchange:

					Intent tv_Saleexchange = new Intent(getActivity(), SaleExchangeActivity.class);

					getActivity().startActivity(tv_Saleexchange);

					break;

				case R.id.event:

					// Intent event = new Intent(getActivity(),
					// EventMainActivity.class);
					// getActivity().startActivity(event);
					Intent dayday = new Intent(getActivity(), DaydayGiftActivity.class);

					getActivity().startActivity(dayday);

					break;

				case R.id.more_event:
					// Intent more_event = new Intent(getActivity(),
					// EventMainActivity.class);
					// getActivity().startActivity(more_event);

					break;

				case R.id.practice:

					Intent practice = new Intent(getActivity(), PracticeListActivity.class);

					getActivity().startActivity(practice);

					break;

				case R.id.ballsearch:

					// Intent ball = new Intent(getActivity(),
					// BallPracSearchActivity.class);
					// getActivity().startActivity(ball);
					if (ConstantValues.title_size == 0)
					{
						// 如果开始初始失败
						ChannelManage CollgegChannle = new ChannelManage();

						CollgegChannle.initData(getActivity());// 初始学院化频道数据存入数据库

					}

					if (ConstantValues.title_size > 0)
					{
						Intent ball = new Intent(getActivity(), CollegePage.class);

						getActivity().startActivity(ball);

					}
					else
					{
						ToastUtils.showInfo(getActivity(), "频道维护中,稍后再试");
					}

					break;

				case R.id.main_shop:

					if (ConstantValues.shop_title_size == 0)
					{
						// 如果开始初始失败
						ShopChannelManage ShopChannle = new ShopChannelManage();

						ShopChannle.initData(getActivity());// 初始学院化频道数据存入数据库
					}

					if (ConstantValues.knoledge_title_size > 0)
					{
						Intent shop = new Intent(getActivity(), ShopIndexActivity.class);

						getActivity().startActivity(shop);
					}

					break;

				case R.id.knowledge:

					if (ConstantValues.knoledge_title_size == 0)
					{
						// 如果开始初始失败
						KnowledgeChannelManage KonwledgeChannle = new KnowledgeChannelManage();

						KonwledgeChannle.initData(getActivity());// 初始高球常识频道数据存入数据库
					}

					if (ConstantValues.knoledge_title_size > 0)
					{
						Intent knowledge = new Intent(getActivity(), KnowledgePageActivity.class);

						getActivity().startActivity(knowledge);

					}
					else
					{
						ToastUtils.showInfo(getActivity(), "频道维护中,稍后再试");
					}
					break;

				case R.id.ball_main:

					Intent ball = new Intent(getActivity(), BallMainActivity.class);

					getActivity().startActivity(ball);

					break;

				default:
					break;
			}
		}
	}

	public void initRoll()
	{
		// ================滚动图片数据============= 滚动图初始化数据
		Rpage = new RollViewPager(getActivity(), dotList, R.drawable.dot_focus, R.drawable.dot_normal, new RollViewPager.OnPagerClickCallback()
		{
			@Override
			public void onPagerClick(int position)
			{
				if (urlList.size() > 0)
				{
					if (CommonUtil.isNetworkAvailable(getActivity()) == 0)
					{
						ToastUtils
								.showInfo(getActivity(), ConstantValues.NONETWORK);
					}
					else
					{
						if (data == null) { return; }

						String advid = data.get(position).getAdv_id();

						int type = Integer
								.valueOf(data.get(position).getType());

						switch (type)
						{
							case 0:
								
								Intent ball = new Intent(getActivity(), BallInfoActivity.class); // 球场ID跳转到球场

								ball.putExtra("ballid", advid);

								getActivity().startActivity(ball);

								break;

							case 1:

								Intent practice = new Intent(getActivity(), PracticeInfoActivity.class);// 练习场ID跳转到练习场

								practice.putExtra("rgid", advid);

								practice.putExtra("cityname", ConstantValues.CITYNAME);

								getActivity().startActivity(practice);

								break;

							case 2:

								Intent event = new Intent(getActivity(), EventDetailActivity.class);// 活动ID跳转到活动

								event.putExtra("eventid", advid);

								getActivity().startActivity(event);

								break;

							case 3:

								Intent shop = new Intent(getActivity(), ShopProActivity.class);// 商品ID跳转到商品

								shop.putExtra("proid", advid);

								getActivity().startActivity(shop);

								break;

							case 5:

								// String code = data.get(position).getType();

								Intent event_notice = new Intent(getActivity(), EventnNoticeActivity.class);// 活动公告

								event_notice.putExtra("noticeid", advid);

								// event_notice.putExtra("code", code);

								getActivity().startActivity(event_notice);

								break;

							default:
								break;

						}
					}
				}
			}
		});

		Rpage.setLayoutParams(new LinearLayout.LayoutParams(ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT));

		titles = new ArrayList<String>();

		// Rpage.setTitle(chzz_news_roll_title, titles);//标题
		Rpage.setUriList(urlList);

		Rpage.startRoll();

		shop_viewpager.removeAllViews(); // 使用前先清除所有的内容view

		shop_viewpager.addView(Rpage);

		ImageView img = new ImageView(getActivity());
		
		img.setImageResource(R.drawable.banner_05);
		
		img.setScaleType(ImageView.ScaleType.FIT_XY);
		
		shop_viewpager.addView(img);
		
	}

	// ===========滚动图片view=============
	private void initDot(int size)
	{
		dotList = new ArrayList<View>();

		dots_ll.removeAllViews();

		for (int i = 0; i < size; i++)
		{
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CommonUtil
					.dip2px(getActivity(), 6), CommonUtil
					.dip2px(getActivity(), 6));

			params.setMargins(5, 0, 5, 0);

			View m = new View(getActivity());

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
			ToastUtils.showInfo(getActivity(), ConstantValues.NONETWORK);
		}
		else
		{
			EventEntity bean = GsonTools
					.changeGsonToBean(result, EventEntity.class);

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

			ToastUtils.showInfo(getActivity(), ConstantValues.NONETWORK);
		}
		else
		{
			HomePageAdsEntity bean = GsonTools
					.changeGsonToBean(result, HomePageAdsEntity.class);
			
			if ("1".equals(bean.getStatus()))
			{
				data = bean.getData();

				urlList.clear();

				for (int i = 0; i < data.size(); i++)
				{
					String path = data.get(i).getIcon();

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
		SharedPreferencesUtils.saveString(getActivity(), name, result);
	}

	private void setLastUpdateTime()
	{
		String text = CommonUtil.getStringDate();

		pull_refresh_list.setLastUpdatedLabel(text);
	}

	private void onLoaded()
	{
		pull_refresh_list.onPullDownRefreshComplete();

		pull_refresh_list.onPullUpRefreshComplete();
	}

	/**
	 * @Title: onTopUp
	 * @Description: 充值按钮单击事件回调事件
	 * @param @param view 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void onTopUp(View view)
	{

		Intent opUp = new Intent(getActivity(), RechargeActivity.class);

		getActivity().startActivity(opUp);

	}

	/**
	 * @Title: onKnowledge
	 * @Description: 知识按钮单击事件回调事件
	 * @param @param view 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void onKnowledge(View view)
	{

		if (ConstantValues.knoledge_title_size == 0)
		{
			// 如果开始初始失败
			KnowledgeChannelManage KonwledgeChannle = new KnowledgeChannelManage();

			KonwledgeChannle.initData(getActivity());// 初始高球常识频道数据存入数据库
		}

		if (ConstantValues.knoledge_title_size > 0)
		{
			Intent knowledge = new Intent(getActivity(), KnowledgePageActivity.class);

			getActivity().startActivity(knowledge);

		}
		else
		{
			ToastUtils.showInfo(getActivity(), "频道维护中,稍后再试");
		}

	}

	/**
	 * @Title: onPractice
	 * @Description: 练习场按钮单击事件回调事件
	 * @param @param view 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void onPractice(View view)
	{

		Intent practice = new Intent(getActivity(), PracticeListActivity.class);

		getActivity().startActivity(practice);

	}

	
	/**
	 * @Title: onBallpark
	 * @Description: 球场按钮单击事件回调事件
	 * @param @param view 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void onBallpark(View view)
	{
		Intent ball = new Intent(getActivity(), BallMainActivity.class);

		getActivity().startActivity(ball);
	}

	
	
	/**
	 * @Title: onMyTeam
	 * @Description: 战队按钮单击事件回调事件
	 * @param @param view 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void onMyTeam(View view)
	{

		Intent myTeam = new Intent(getActivity(), MyTeamMainActivity.class);
		
		getActivity().startActivity(myTeam);
	}

	
	/**
	 * @Title: onAction
	 * @Description: 活动按钮单击事件回调事件
	 * @param @param view 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void onAction(View view)
	{
		Intent dayday = new Intent(getActivity(), DaydayGiftActivity.class);

		getActivity().startActivity(dayday);

	}

	/**
	 * @Title: onCollege
	 * @Description: 学院按钮单击事件回调事件
	 * @param @param view 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void onCollege(View view)
	{
		
		Intent college = new Intent(getActivity(), CollegePage.class);

		getActivity().startActivity(college);;

	}

	/**
	 * @Title: onStore
	 * @Description: 商城按钮单击事件回调事件
	 * @param @param view 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void onStore(View view)
	{
		if (ConstantValues.shop_title_size == 0)
		{
			// 如果开始初始失败
			ShopChannelManage ShopChannle = new ShopChannelManage();

			ShopChannle.initData(getActivity());// 初始学院化频道数据存入数据库
		}

		if (ConstantValues.knoledge_title_size > 0)
		{
			Intent shop = new Intent(getActivity(), ShopIndexActivity.class);

			getActivity().startActivity(shop);
		}

	}

}
