package me.wangolf.college;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月5日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月5日
 * 
 * 描述 ：学院-学院-list页
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.util.ArrayList;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.CollegeListAdapter;
import me.wangolf.base.Mo_BasePage;
import me.wangolf.bean.college.CollegeListEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshBase.OnRefreshListener;
import me.wangolf.utils.viewUtils.PullToRefreshListView;

public class CollegeCollegePage extends FragmentActivity implements OnClickListener
{

	private String							type;
	private boolean							ismore		= false;	// 断判是否有更多数据
	// public boolean isLoadSuccess;
	private boolean							ismoredata;
	private boolean							isRefresh;				// 是否刷新
	private ArrayList<CollegeListEntity>	list;					// 列表list
	@ViewInject(R.id.pull_refresh_list)
	private PullToRefreshListView			pull_refresh_list;
	@ViewInject(R.id.common_title)
	private TextView common_title;
	@ViewInject(R.id.common_back)
	private Button common_back;

	private CollegeListAdapter				adapter;
	private String							rangeid;				// 练习场ID
	private int								page		= 1;		// 页数
	private int								number		= 10;		// 每页显示数目
	private String							collegeid;				// 学院ID（显示所有学院则填写空字符）
	private String							latitude;				// 纬度
	private String							longitude;				// 经度
	private boolean							isR;
	private Dialog							dialog;
	private int								sort_type	= 0;		// 0按距离排序1按人气2按教龄
	private int								sort		= 0;		// 0升序1降序

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);

		setContentView(R.layout.activity_collgeg_list);

		ViewUtils.inject(this);

		initView();

		if (adapter == null)
		{
			adapter = new CollegeListAdapter(this);

			pull_refresh_list.getRefreshableView().setAdapter(adapter);
		}
		else
		{
			adapter.notifyDataSetChanged();
		}

		initData();

	}

	public void initView()
	{

		common_title.setVisibility(View.VISIBLE);
		
		common_title.setText("高尔夫学院");
		
		common_back.setVisibility(View.VISIBLE);
		
		common_back.setOnClickListener(this);
		
		pull_refresh_list.setPullLoadEnabled(false);
		// 滚动到底自动加载可用
		pull_refresh_list.setScrollLoadEnabled(true);
		// 得到实际的ListView 设置点击
		pull_refresh_list.getRefreshableView()
				.setOnItemClickListener(new OnItemClickListener()
				{
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id)
					{
						if (CommonUtil.isNetworkAvailable(getBaseContext()) == 0)
						{
							Toast.makeText(getBaseContext(), ConstantValues.NONETWORK, 0)
									.show();
						}
						else
						{
							CollegeListEntity bean = (CollegeListEntity) adapter
									.getItem(position);

							Intent intent = new Intent(getBaseContext(), CollegeinfoAcitvity.class);

							intent.putExtra("collegeid", bean.getCollegeid() + "");

							startActivity(intent);
						}
					}
				});

		// 设置下拉刷新的listener
		pull_refresh_list
				.setOnRefreshListener(new OnRefreshListener<ListView>()
				{
					@Override
					public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
					{
						isR = true;

						page = 1;

						getData();
						// ismoredata = false;
					}

					@Override
					public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
					{
						if (!ismore) ismoredata = true;
						page = page + 1;
						isR = false;
						getData();

					}
				});

	}

	
	public void initData()
	{
		dialog = DialogUtil.getDialog(this);
		dialog.show();
		latitude = ConstantValues.LATITUDE;
		longitude = ConstantValues.LONGITUDE;
		getData();
	}

	public void getData()
	{

		try
		{
			ServiceFactory
					.getCollegeEngineInstatice()
					.getCollegeList(sort_type, sort, latitude, longitude, page + "", number + "", new IOAuthCallBack()
					{

						@Override
						public void getIOAuthCallBack(String result)
						{
							if (result.equals(ConstantValues.FAILURE))
							{
								Toast.makeText(getBaseContext(), ConstantValues.NONETWORK, 0)
										.show();
							}
							else
							{

								CollegeListEntity bean = GsonTools
										.changeGsonToBean(result, CollegeListEntity.class);

								ArrayList<CollegeListEntity> beaninfo = bean
										.getData();

								if (beaninfo.size() == 0)
								{
									ismore = true;

									onLoaded();

									Toast.makeText(getBaseContext(), ConstantValues.NOMORE, 0)
											.show();

									dialog.cancel();

								}
								else
								{
									list = (ArrayList<CollegeListEntity>) adapter
											.getList();

									if (isR)
									{
										list.clear();

										list.addAll(beaninfo);

									}
									else
									{
										if (list != null & ismoredata)
										{
											list.addAll(beaninfo);
										}
										else
										{
											adapter.setList(beaninfo);
										}
									}

									adapter.notifyDataSetChanged();

								}

							}

							dialog.cancel();

							onLoaded();

							setLastUpdateTime();
						}
					});

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

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

	public void UpSort(int sorttype, int rule)
	{
		isR = true;
		this.sort_type = sorttype;
		this.sort = rule;
		page = 1;
		getData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.common_back:
			finish();
			break;

		default:
			break;
		}

	}


}
