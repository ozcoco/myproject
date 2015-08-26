package me.wangolf.fragment;

import java.util.ArrayList;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.GlobalConsts;
import me.wangolf.LocationApplication;
import me.wangolf.utils.BMapUtil;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MKMapTouchListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.map.TransitOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKRoute;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 导航路径规划的功能 自定覆盖物（蓝色）添加pop弹出代理商信息
 * 
 * @author Administrator
 * 
 */
public class RoutePlan extends Activity implements OnClickListener
{
	// UI相关
	private LocationClient			mLocClient;
	@ViewInject(R.id.common_back)
	private Button					common_back;				// 后退
	@ViewInject(R.id.common_title)
	private TextView				common_title;				// 标题
	@ViewInject(R.id.common_bt)
	private TextView				common_bt;					// 地图
	// 浏览路线节点相关
	Button							mBtnPre			= null;		// 上一个节点
	Button							mBtnNext		= null;		// 下一个节点
	int								nodeIndex		= -2;		// 节点索引,供浏览节点时使用
	MKRoute							route			= null;		// 保存驾车/步行路线数据的变量，供浏览节点时使用
	TransitOverlay					transitOverlay	= null;		// 保存公交路线图层数据的变量，供浏览节点时使用
	RouteOverlay					routeOverlay	= null;
	boolean							useDefaultIcon	= false;
	int								searchType		= -1;		// 记录搜索的类型，区分驾车/步行和公交
	private PopupOverlay			pop				= null;		// 弹出泡泡图层，浏览节点时使用
	private TextView				popupText		= null;		// 泡泡view
	private View					viewCache		= null;

	// 地图相关，使用继承MapView的MyRouteMapView目的是重写touch事件实现泡泡处理
	// 如果不处理touch事件，则无需继承，直接使用MapView即可
	MapView							mMapView		= null;	// 地图View
	// 搜索相关
	MKSearch						mSearch			= null;	// 搜索模块，也可去掉地图模块独立使用
	private MapController			mMapController;
	private String					city;
	public GeoPoint					startGeoPoint;

	private GeoPoint				endPoint;

	private ArrayList<OverlayItem>	listOverlay;

	private View					popupInfo;

	private TextView				popdown;

	private Button					button;

	private MyOverlay				mOverlay;

	public OverlayItem				mCurItem;

	private String					currentLocation;

	private String					endStr;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(new MyLocationListener());
		mLocClient.start();
		mLocClient.stop();

		/**
		 * 使用地图sdk前需先初始化BMapManager. BMapManager是全局的，可为多个MapView共用，它需要地图模块创建前创建，
		 * 并在地图地图模块销毁后销毁，只要还有地图模块在使用，BMapManager就不应该销毁
		 */
		LocationApplication app = (LocationApplication) this.getApplication();
		if (app.mBMapManager == null)
		{
			app.mBMapManager = new BMapManager(getApplicationContext());
			/**
			 * 如果BMapManager没有初始化则初始化BMapManager
			 */
			app.mBMapManager.init(new LocationApplication.MyGeneralListener());
		}

		// TODO c测试

		setContentView(R.layout.ac_rount);
		ViewUtils.inject(this);
		common_back.setVisibility(0);
		common_title.setText("路线规划");
		common_back.setOnClickListener(this);
		CharSequence titleLable = "路线规划功能";
		setTitle(titleLable);

		// 初始化地图
		mMapView = (MapView) findViewById(R.id.bmapView);
		mMapView.setBuiltInZoomControls(false);
		mMapView.getController().setZoom(12);
		mMapView.getController().enableClick(true);

		/**
		 * 获取地图控制器
		 */
		mMapController = mMapView.getController();
		/**
		 * 设置地图是否响应点击事件 .
		 */
		mMapController.enableClick(true);
		/**
		 * 设置地图缩放级别
		 */
		mMapController.setZoom(12);
		/**
		 * 显示内置缩放控件
		 */
		// 不显示 mMapView.setBuiltInZoomControls(true);

		initOverlay();

		// 初始化按键
		mBtnPre = (Button) findViewById(R.id.pre);
		mBtnNext = (Button) findViewById(R.id.next);
		mBtnPre.setVisibility(View.INVISIBLE);
		mBtnNext.setVisibility(View.INVISIBLE);

		// 按键点击事件
		OnClickListener clickListener = new OnClickListener()
		{
			public void onClick(View v)
			{
				// 发起搜索
				SearchButtonProcess(v);
			}
		};
		OnClickListener nodeClickListener = new OnClickListener()
		{
			public void onClick(View v)
			{
				// 浏览路线节点
				nodeClick(v);
			}
		};

		OnClickListener changeRouteIconListener = new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				changeRouteIcon();
			}

		};

		mBtnPre.setOnClickListener(nodeClickListener);
		mBtnNext.setOnClickListener(nodeClickListener);
		// 创建 弹出泡泡图层
		createPaopao();

		// 地图点击事件处理
		mMapView.regMapTouchListner(new MKMapTouchListener()
		{

			@Override
			public void onMapClick(GeoPoint point)
			{
				// 在此处理地图点击事件
				// 消隐pop
				if (pop != null)
				{
					pop.hidePop();
				}
			}

			@Override
			public void onMapDoubleClick(GeoPoint point)
			{

			}

			@Override
			public void onMapLongClick(GeoPoint point)
			{

			}

		});
		// 初始化搜索模块，注册事件监听
		mSearch = new MKSearch();
		mSearch.init(app.mBMapManager, new MKSearchListener()
		{

			public void onGetDrivingRouteResult(MKDrivingRouteResult res, int error)
			{
				// 起点或终点有歧义，需要选择具体的城市列表或地址列表
				if (error == MKEvent.ERROR_ROUTE_ADDR)
				{
					// 遍历所有地址
					// ArrayList<MKPoiInfo> stPois =
					// res.getAddrResult().mStartPoiList;
					// ArrayList<MKPoiInfo> enPois =
					// res.getAddrResult().mEndPoiList;
					// ArrayList<MKCityListInfo> stCities =
					// res.getAddrResult().mStartCityList;
					// ArrayList<MKCityListInfo> enCities =
					// res.getAddrResult().mEndCityList;
					return;
				}
				// 错误号可参考MKEvent中的定义
				if (error != 0 || res == null)
				{
					Toast.makeText(RoutePlan.this, "请先允许GPS定位请求，再重试！",

					Toast.LENGTH_SHORT).show();
					return;
				}

				searchType = 0;
				routeOverlay = new RouteOverlay(RoutePlan.this, mMapView);
				// 此处仅展示一个方案作为示例
				routeOverlay.setData(res.getPlan(0).getRoute(0));
				// 清除其他图层
				// mMapView.getOverlays().clear();
				// 添加路线图层
				mMapView.getOverlays().add(routeOverlay);
				// 执行刷新使生效
				mMapView.refresh();
				// 使用zoomToSpan()绽放地图，使路线能完全显示在地图上
				mMapView.getController()
						.zoomToSpan(routeOverlay.getLatSpanE6(),

						routeOverlay.getLonSpanE6());
				// 移动地图到起点
				mMapView.getController().animateTo(res.getStart().pt);
				// 将路线数据保存给全局变量
				route = res.getPlan(0).getRoute(0);
				// 重置路线节点索引，节点浏览时使用
				nodeIndex = -1;
				mBtnPre.setVisibility(View.VISIBLE);
				mBtnNext.setVisibility(View.VISIBLE);
			}

			public void onGetTransitRouteResult(MKTransitRouteResult res, int error)
			{
				// 起点或终点有歧义，需要选择具体的城市列表或地址列表
				if (error == MKEvent.ERROR_ROUTE_ADDR)
				{
					// 遍历所有地址
					// ArrayList<MKPoiInfo> stPois =
					// res.getAddrResult().mStartPoiList;
					// ArrayList<MKPoiInfo> enPois =
					// res.getAddrResult().mEndPoiList;
					// ArrayList<MKCityListInfo> stCities =
					// res.getAddrResult().mStartCityList;
					// ArrayList<MKCityListInfo> enCities =
					// res.getAddrResult().mEndCityList;
					return;
				}
				if (error != 0 || res == null)
				{
					Toast.makeText(RoutePlan.this, "抱歉，未找到结果",

					Toast.LENGTH_SHORT).show();
					return;
				}

				searchType = 1;
				transitOverlay = new TransitOverlay(RoutePlan.this, mMapView);
				// 此处仅展示一个方案作为示例
				transitOverlay.setData(res.getPlan(0));
				// 清除其他图层
				mMapView.getOverlays().clear();
				// 添加路线图层
				mMapView.getOverlays().add(transitOverlay);
				// 执行刷新使生效
				mMapView.refresh();
				// 使用zoomToSpan()绽放地图，使路线能完全显示在地图上
				mMapView.getController().zoomToSpan(transitOverlay
						.getLatSpanE6(),

				transitOverlay.getLonSpanE6());
				// 移动地图到起点
				mMapView.getController().animateTo(res.getStart().pt);
				// 重置路线节点索引，节点浏览时使用
				nodeIndex = 0;
				mBtnPre.setVisibility(View.VISIBLE);
				mBtnNext.setVisibility(View.VISIBLE);
			}

			public void onGetWalkingRouteResult(MKWalkingRouteResult res, int error)
			{
				// 起点或终点有歧义，需要选择具体的城市列表或地址列表
				if (error == MKEvent.ERROR_ROUTE_ADDR)
				{
					// 遍历所有地址
					// ArrayList<MKPoiInfo> stPois =
					// res.getAddrResult().mStartPoiList;
					// ArrayList<MKPoiInfo> enPois =
					// res.getAddrResult().mEndPoiList;
					// ArrayList<MKCityListInfo> stCities =
					// res.getAddrResult().mStartCityList;
					// ArrayList<MKCityListInfo> enCities =
					// res.getAddrResult().mEndCityList;
					return;
				}
				if (error != 0 || res == null)
				{
					Toast.makeText(RoutePlan.this, "抱歉，未找到结果",

					Toast.LENGTH_SHORT).show();
					return;
				}

				searchType = 2;
				routeOverlay = new RouteOverlay(RoutePlan.this, mMapView);
				// 此处仅展示一个方案作为示例
				routeOverlay.setData(res.getPlan(0).getRoute(0));
				// 清除其他图层
				mMapView.getOverlays().clear();
				// 添加路线图层
				mMapView.getOverlays().add(routeOverlay);
				// 执行刷新使生效
				mMapView.refresh();
				// 使用zoomToSpan()绽放地图，使路线能完全显示在地图上
				mMapView.getController()
						.zoomToSpan(routeOverlay.getLatSpanE6(),

						routeOverlay.getLonSpanE6());
				// 移动地图到起点
				mMapView.getController().animateTo(res.getStart().pt);
				// 将路线数据保存给全局变量
				route = res.getPlan(0).getRoute(0);
				// 重置路线节点索引，节点浏览时使用
				nodeIndex = -1;
				mBtnPre.setVisibility(View.VISIBLE);
				mBtnNext.setVisibility(View.VISIBLE);

			}

			public void onGetAddrResult(MKAddrInfo res, int error)
			{
			}

			public void onGetPoiResult(MKPoiResult res, int arg1, int arg2)
			{
			}

			public void onGetBusDetailResult(MKBusLineResult result, int iError)
			{
			}

			@Override
			public void onGetSuggestionResult(MKSuggestionResult res, int arg1)
			{
			}

			@Override
			public void onGetPoiDetailSearchResult(int type, int iError)
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void onGetShareUrlResult(MKShareUrlResult result, int type, int error)
			{

			}
		});

	}

	/**
	 * 将文本转化为DrawableBitpmap
	 * 
	 * @return
	 */
	private BitmapDrawable getMyBitMapDrable(String name, String detail)
	{
		LayoutInflater inflater = LayoutInflater.from(RoutePlan.this);
		View view = inflater.inflate(R.layout.marker, null);
		TextView tvTitle = (TextView) view.findViewById(R.id.marker_title);
		tvTitle.setText(name);
		TextView tvText = (TextView) view.findViewById(R.id.marker_text);
		tvText.setText(detail);
		Bitmap btm = BMapUtil.getBitmapFromView(view);
		BitmapDrawable btd = new BitmapDrawable(btm);
		return btd;
	}

	/**
	 * 位置监听
	 * 
	 * @author Administrator
	 * 
	 */
	class MyLocationListener implements BDLocationListener
	{

		@Override
		public void onReceiveLocation(BDLocation location)
		{
			if (location == null) { return; }
			String c = location.getCity();
			city = c;
			String d = location.getDistrict();
			String s = location.getStreet();
			double la = location.getLatitude();
			double lon = location.getLongitude();

			// 得到当前位置
			double s_latok = Double.parseDouble(ConstantValues.LATITUDE);
			double s_lonok = Double.parseDouble(ConstantValues.LONGITUDE);
			startGeoPoint = new GeoPoint((int) (s_latok * 1E6), (int) (s_lonok * 1E6));
			// 终点位置
			MKPlanNode stNode = new MKPlanNode();
			// enNode.name = editEn.getText().toString();
			stNode.pt = startGeoPoint;

			// 得到终点的经纬度
			Intent in = getIntent();
			String latitude = in.getStringExtra("latitude");
			String longitude = in.getStringExtra("longitude");
			double latok = Double.parseDouble(latitude);
			double lonok = Double.parseDouble(longitude);
			// 设置终点纬度，经度
			endPoint = new GeoPoint((int) (latok * 1E6), (int) (lonok * 1E6));
			MKPlanNode enNode = new MKPlanNode();
			// enNode.name = editEn.getText().toString();
			enNode.pt = endPoint;
			// TODO搜索
			mSearch.drivingSearch(null, stNode, GlobalConsts.BALL_SEACHCITY, enNode);

		}

		public void onReceivePoi(BDLocation arg0)
		{

		}
	}

	/**
	 * 发起路线规划搜索示例
	 * 
	 * @param v
	 */
	void SearchButtonProcess(View v)
	{
		// 重置浏览节点的路线数据
		route = null;
		routeOverlay = null;
		transitOverlay = null;
		mBtnPre.setVisibility(View.INVISIBLE);
		mBtnNext.setVisibility(View.INVISIBLE);
		// 处理搜索按钮响应

		// TODO 对起点终点的name进行赋值，也可以直接对坐标赋值，赋值坐标则将根据坐标进行搜索
		// 对起点终点的name进行赋值，也可以直接对坐标赋值，赋值坐标则将根据坐标进行搜索
		MKPlanNode stNode = new MKPlanNode();
		stNode.name = currentLocation;
		MKPlanNode enNode = new MKPlanNode();
		enNode.name = endStr;
		// MKPlanNode stNode = new MKPlanNode();
		// stNode.name =editSt.getText().toString().trim();
		// 设置终点纬度，经度
		MKPlanNode stNode1 = new MKPlanNode();
		// enNode.name = editEn.getText().toString();
		stNode1.pt = startGeoPoint;

		// 设置终点纬度，经度
		endPoint = new GeoPoint((int) (22.532387 * 1E6), (int) (114.040729 * 1E6));
		MKPlanNode enNode1 = new MKPlanNode();
		// enNode.name = editEn.getText().toString();
		enNode1.pt = endPoint;

	}

	/**
	 * 节点浏览示例
	 * 
	 * @param v
	 */
	public void nodeClick(View v)
	{
		viewCache = getLayoutInflater()
				.inflate(R.layout.custom_text_view, null);
		popupText = (TextView) viewCache.findViewById(R.id.textcache);
		if (searchType == 0 || searchType == 2)
		{
			// 驾车、步行使用的数据结构相同，因此类型为驾车或步行，节点浏览方法相同
			if (nodeIndex < -1 || route == null || nodeIndex >= route
					.getNumSteps()) return;

			// 上一个节点
			if (mBtnPre.equals(v) && nodeIndex > 0)
			{
				// 索引减
				nodeIndex--;
				// 移动到指定索引的坐标
				mMapView.getController().animateTo(route.getStep(nodeIndex)
						.getPoint());
				// 弹出泡泡
				popupText.setBackgroundResource(R.drawable.popup);
				popupText.setText(route.getStep(nodeIndex).getContent());
				pop.showPopup(BMapUtil.getBitmapFromView(popupText), route
						.getStep(nodeIndex).getPoint(), 5);
			}
			// 下一个节点
			if (mBtnNext.equals(v) && nodeIndex < (route.getNumSteps() - 1))
			{
				// 索引加
				nodeIndex++;
				// 移动到指定索引的坐标
				mMapView.getController().animateTo(route.getStep(nodeIndex)
						.getPoint());
				// 弹出泡泡
				popupText.setBackgroundResource(R.drawable.popup);
				popupText.setText(route.getStep(nodeIndex).getContent());
				pop.showPopup(BMapUtil.getBitmapFromView(popupText), route
						.getStep(nodeIndex).getPoint(), 5);
			}
		}
		if (searchType == 1)
		{
			// 公交换乘使用的数据结构与其他不同，因此单独处理节点浏览
			if (nodeIndex < -1 || transitOverlay == null || nodeIndex >=

			transitOverlay.getAllItem().size()) return;

			// 上一个节点
			if (mBtnPre.equals(v) && nodeIndex > 1)
			{
				// 索引减
				nodeIndex--;
				// 移动到指定索引的坐标
				mMapView.getController().animateTo(transitOverlay.getItem

				(nodeIndex).getPoint());
				// 弹出泡泡
				popupText.setBackgroundResource(R.drawable.popup);
				popupText.setText(transitOverlay.getItem(nodeIndex).getTitle());
				pop.showPopup(BMapUtil.getBitmapFromView(popupText), transitOverlay
						.getItem(nodeIndex).getPoint(), 5);
			}
			// 下一个节点
			if (mBtnNext.equals(v) && nodeIndex < (transitOverlay.getAllItem()
					.size() - 2))
			{
				// 索引加
				nodeIndex++;
				// 移动到指定索引的坐标
				mMapView.getController().animateTo(transitOverlay.getItem

				(nodeIndex).getPoint());
				// 弹出泡泡
				popupText.setBackgroundResource(R.drawable.popup);
				popupText.setText(transitOverlay.getItem(nodeIndex).getTitle());
				pop.showPopup(BMapUtil.getBitmapFromView(popupText), transitOverlay
						.getItem(nodeIndex).getPoint(), 5);
			}
		}

	}

	/**
	 * 创建弹出泡泡图层
	 */
	public void createPaopao()
	{

		// 泡泡点击响应回调
		PopupClickListener popListener = new PopupClickListener()
		{
			@Override
			public void onClickedPopup(int index)
			{
				Log.v("click", "clickapoapo");
			}
		};
		pop = new PopupOverlay(mMapView, popListener);
	}

	/**
	 * 切换路线图标，刷新地图使其生效 注意： 起终点图标使用中心对齐.
	 */
	protected void changeRouteIcon()
	{
		if (routeOverlay == null && transitOverlay == null) { return; }
		if (useDefaultIcon)
		{
			if (routeOverlay != null)
			{
				routeOverlay.setStMarker(null);
				routeOverlay.setEnMarker(null);
			}
			if (transitOverlay != null)
			{
				transitOverlay.setStMarker(null);
				transitOverlay.setEnMarker(null);
			}
			Toast.makeText(this, "将使用系统起终点图标", Toast.LENGTH_SHORT).show();
		}
		else
		{
			if (routeOverlay != null)
			{
				routeOverlay.setStMarker(getResources()
						.getDrawable(R.drawable.icon_st));
				routeOverlay.setEnMarker(getResources()
						.getDrawable(R.drawable.icon_en));
			}
			if (transitOverlay != null)
			{
				transitOverlay.setStMarker(getResources()
						.getDrawable(R.drawable.icon_st));
				transitOverlay.setEnMarker(getResources()
						.getDrawable(R.drawable.icon_en));
			}
			Toast.makeText(this, "将使用自定义起终点图标", Toast.LENGTH_SHORT).show();
		}
		useDefaultIcon = !useDefaultIcon;
		mMapView.refresh();

	}

	@Override
	protected void onPause()
	{
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onDestroy()
	{
		mMapView.destroy();
		mSearch.destory();
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
		mMapView.onRestoreInstanceState(savedInstanceState);
	}

	/**
	 * 设置底图显示模式
	 * 
	 * @param view
	 */
	public void setMapMode(View view)
	{
		boolean checked = ((RadioButton) view).isChecked();
		switch (view.getId())
		{
			case R.id.normal:
				if (checked) mMapView.setSatellite(false);
				break;
			case R.id.statellite:
				if (checked) mMapView.setSatellite(true);
				break;
		}
	}

	/**
	 * 设置是否显示交通图
	 * 
	 * @param view
	 */
	public void setTraffic(View view)
	{
		mMapView.setTraffic(((CheckBox) view).isChecked());
	}

	/**
	 * 返回键事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
		{
			// 需要处理
			this.finish();
		}
		return false;
	}

	/**
	 * 标记球场的部分
	 */
	// ===========================================================================================================
	/**
	 * overlay 位置坐标
	 */
	double	mLon1	= 116.400244;
	double	mLat1	= 39.963175;

	/**
	 * 由于MapView在setContentView()中初始化,所以它需要在BMapManager初始化之后
	 */
	public void initOverlay()
	{
		/**
		 * 创建自定义overlay
		 */
		mOverlay = new MyOverlay(getResources()
				.getDrawable(R.drawable.nav_turn_via_1), mMapView); // TODO
																	// cccccccccccccc
		/**
		 * 准备overlay 数据
		 */
		// TODO 得到终点的经纬度以及球场名称与地址
		Intent in = getIntent();
		String latitude = in.getStringExtra("latitude");
		String longitude = in.getStringExtra("longitude");
		String ballname = in.getStringExtra("ballname");
		String address = in.getStringExtra("balladdress");

		double latok = Double.parseDouble(latitude);
		double lonok = Double.parseDouble(longitude);
		// 设置终点纬度，经度
		GeoPoint p1 = new GeoPoint((int) (latok * 1E6), (int) (lonok * 1E6));
		OverlayItem item1 = new OverlayItem(p1, ballname, address);
		/**
		 * 设置overlay图标，如不设置，则使用创建ItemizedOverlay时的默认图标.
		 */
		item1.setMarker(getResources().getDrawable(R.drawable.nav_turn_via_1));// TODO
																				// cccccccccccccc
		/**
		 * 将item 添加到overlay中 注意： 同一个itme只能add一次
		 */
		mOverlay.addItem(item1);

		/**
		 * 将overlay 添加至MapView中
		 */
		mMapView.getOverlays().add(mOverlay);
		/**
		 * 刷新地图
		 */
		mMapView.refresh();
		/**
		 * 向地图添加自定义View.
		 */
		viewCache = getLayoutInflater()
				.inflate(R.layout.custom_text_view3, null);
		popupInfo = (View) viewCache.findViewById(R.id.popinfo);
		popupText = (TextView) viewCache.findViewById(R.id.textcache);
		popdown = (TextView) viewCache.findViewById(R.id.popdown);

		button = new Button(this);
		button.setBackgroundResource(R.drawable.popup);

		/**
		 * 创建一个popupoverlay
		 */
		PopupClickListener popListener = new PopupClickListener()
		{
			@Override
			public void onClickedPopup(int index)
			{
				// 更新图标
				mCurItem.setMarker(getResources()
						.getDrawable(R.drawable.nav_turn_via_1));
				mOverlay.updateItem(mCurItem);
				mMapView.refresh();
			}
		};
		pop = new PopupOverlay(mMapView, popListener);

		mMapView.refresh();
	}

	public class MyOverlay extends ItemizedOverlay
	{

		public MyOverlay(Drawable defaultMarker, MapView mapView)
		{
			super(defaultMarker, mapView);
		}

		@Override
		public boolean onTap(int index)
		{
			OverlayItem item = getItem(index);
			mCurItem = item;
			popupText.setText(getItem(index).getTitle());
			popdown.setText(getItem(index).getSnippet());
			Bitmap[] bitMaps =
			{ BMapUtil.getBitmapFromView(popupInfo),

			};
			pop.showPopup(bitMaps, item.getPoint(), 32);
			return true;
		}

		@Override
		public boolean onTap(GeoPoint pt, MapView mMapView)
		{
			if (pop != null)
			{
				pop.hidePop();
				mMapView.removeView(button);
			}
			return false;
		}

	}

	@Override
	protected void onResume()
	{
		mMapView.onResume();
		LocationApplication app = (LocationApplication) getApplication();

		super.onResume();
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.common_back:
				this.finish();
				break;

			default:
				break;
		}

	}
}
