package me.wangolf.shop;

import java.util.ArrayList;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.ShopListAdapter;
import me.wangolf.base.Mo_BasePage;
import me.wangolf.bean.shop.Basebean;
import me.wangolf.bean.shop.Pro;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshBase.OnRefreshListener;
import me.wangolf.utils.viewUtils.PullToRefreshListView;

public class ShopPageItem extends Mo_BasePage implements OnClickListener {
	private int cate_id;
	private boolean ismore = false; // 断判是否有更多数据
	public boolean isLoadSuccess;
	private boolean ismoredata;
	private boolean isRefresh;// 是否刷新
	private int sort = 0; // 0按照价格排序1按照折扣排序
	private int rule = 0; // 排序规则0升序1降序
	private int page = 1; // 分页码
	private String number = "10";// 每页数目
	private int proid;// 商品ID
	private ArrayList<Pro> p_list; // 商品列表list
	@ViewInject(R.id.pull_refresh_list)
	private PullToRefreshListView pull_refresh_list;
	@ViewInject(R.id.tv2)
	private TextView tv2;
	private ShopListAdapter adapter;
	private Dialog dialog;

	public ShopPageItem(Context context) {
		super(context);

	}

	public ShopPageItem(Context context, int cate_id) {
		super(context);
		this.cate_id = cate_id;
	}

	@Override
	public View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.ac_shop_list, null);
		ViewUtils.inject(this, view);
		tv2.setOnClickListener(this);
		if (adapter == null) {
			adapter = new ShopListAdapter(context);
			pull_refresh_list.getRefreshableView().setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}
		pull_refresh_list.setPullLoadEnabled(false);
		// 滚动到底自动加载可用
		pull_refresh_list.setScrollLoadEnabled(true);
		// 得到实际的ListView 设置点击
		pull_refresh_list.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				if (CommonUtil.isNetworkAvailable(context) == 0) {

                    ToastUtils.showInfo(context,ConstantValues.NONETWORK);// 跳转前检查是否连网
				} else {
					Intent intent = new Intent(context, ShopProActivity.class);
					proid = adapter.getItem(position).getProid();
					intent.putExtra("proid", proid + "");
					context.startActivity(intent);
				}
			}
		});

		// 设置下拉刷新的listener
		pull_refresh_list.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				page = 1;
				getData(true);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				if (!ismore)
					ismoredata = true;
				page = page + 1;
				getData(false);
			}
		});
		return view;
	}

	@Override
	public void initData() {

		dialog = DialogUtil.getDialog(context);
		dialog.show();
		getData(isRefresh);
	}

	public void initData(Context context) {
		dialog = DialogUtil.getDialog(context);
		// dialog.show();
		getData(isRefresh);
	}

	public void getData(final boolean isR) {

		try {
			ServiceFactory.getShopEngineInstatice().getShopList(sort + "", rule + "", page + "", number, cate_id, new IOAuthCallBack() {

				@Override
				public void getIOAuthCallBack(String result) {
					//Log.i("wangolf",result);
					if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(context,ConstantValues.NONETWORK);// 跳转前检查是否连网
					} else {
						isLoadSuccess = true;
						Basebean bean = GsonTools.changeGsonToBean(result, Basebean.class);
						if (bean.getData().size() == 0) {
							ismore = true;
							onLoaded();
                            ToastUtils.showInfo(context,ConstantValues.NOMORE);// 跳转前检查是否连网
						} else {
							ArrayList<Pro> p = bean.getData();
							p_list = (ArrayList<Pro>) adapter.getList();
							if (isR) {
								p_list.clear();
								p_list.addAll(p);
							} else {
								if (p_list != null & ismoredata) {
									p_list.addAll(p);

								} else {
									adapter.setList(p);
								}
							}
							dialog.cancel();
							adapter.notifyDataSetChanged();

						}
						setLastUpdateTime();
					}
					dialog.cancel();
					onLoaded(); // 加载完 关闭加载框
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void processData() {
	}

	private void setLastUpdateTime() {
		String text = CommonUtil.getStringDate();
		pull_refresh_list.setLastUpdatedLabel(text);
	}

	private void onLoaded() {
		dismissLoadingView();
		pull_refresh_list.onPullDownRefreshComplete();
		pull_refresh_list.onPullUpRefreshComplete();
	}

	// *******排序事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv2:
			if (rule == 0) {
				rule = 1;

			} else {
				rule = 0;
			}
			page = 1;
			getData(true);
			break;

		default:
			break;
		}
	}

	@Override
	public void UpSort(int sorttype, int rule) {
		this.sort = sorttype;
		this.rule = rule;
		//getData(isRefresh);
		page = 1;
		getData(true);
	}

}
