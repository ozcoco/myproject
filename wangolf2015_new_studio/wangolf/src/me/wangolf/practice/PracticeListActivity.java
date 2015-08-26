package me.wangolf.practice;

import java.util.ArrayList;
import java.util.List;

import m.framework.ui.widget.pulltorefresh.Scrollable;
import me.wangolf.ConstantValues;
import me.wangolf.adapter.PracticesAdapter;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.CityEntity;
import me.wangolf.bean.practice.PracticeEntity;
import me.wangolf.bean.practice.PracticeEntity.DataEntity;
import me.wangolf.city.CityActivity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.TelUtils;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshBase.OnRefreshListener;
import me.wangolf.utils.viewUtils.PullToRefreshListView;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

/***
 * 
 * PracticeListActivity
 * 
 * 练习场列表界面
 * 
 * 
 * */
public class PracticeListActivity extends BaseActivity implements
		OnClickListener, TextWatcher
{

	@ViewInject(R.id.common_back)
	private Button					common_back;		// 后退

	@ViewInject(R.id.common_title)
	private TextView				common_title;		// 标题

	@ViewInject(R.id.common_bt)
	private TextView				common_bt;			// 城市

	@ViewInject(R.id.pull_refresh_list)
	private PullToRefreshListView	pull_refresh_list;

	@ViewInject(R.id.auto_edit)
	private EditText				auto_edit;			// 搜索框

	private String					cityid;

	private String					rgname	= "";

	private String					longitude;

	private String					latitude;

	private String					type	= "3";

	private List<DataEntity>		list;

	private boolean					isR;

	private boolean					ismoredata;

	private PracticesAdapter		adapter;

	private String					cityname;			// 城市名称

	private Dialog					dialog;

	@ViewInject(R.id.relayout)
	private RelativeLayout			relayout;			// 无相应数

	private InputMethodManager		manager	= null;

	
	
	/* (非 Javadoc) 
	* <p>Title: onTouchEvent</p> 
	* <p>Description: 点击空白处软盘隐藏</p> 
	* @param event
	* @return 
	* @see android.app.Activity#onTouchEvent(android.view.MotionEvent) 
	*/
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null)
			{
				manager.hideSoftInputFromWindow(getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
		return super.onTouchEvent(event);
	}

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ac_practice_list);

		ViewUtils.inject(this);

		if (adapter == null)
		{
			adapter = new PracticesAdapter(this);

			pull_refresh_list.getRefreshableView().setAdapter(adapter);

		}
		else
		{
			adapter.notifyDataSetChanged();
		}

		initData();

		manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

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
						String rgid = adapter.getItem(position).getId();

						if (CommonUtil.isNetworkAvailable(getApplicationContext()) == 0 & Integer.valueOf(rgid) != -1)
						{
							ToastUtils
									.showInfo(PracticeListActivity.this, ConstantValues.NONETWORK);
						}
						else
						{
							Intent intent = new Intent(getApplicationContext(), PracticeInfoActivity.class);

							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

							intent.putExtra("rgid", rgid);

							intent.putExtra("rgname", adapter.getItem(position)
									.getRange_name());

							intent.putExtra("cityname", cityname);

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
						getData();
					}

					@Override
					public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
					{
						// getData();
					}
				});
		
			pull_refresh_list.setOnScrollListener(new OnScrollListener()
			{
				
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState)
				{
					
					manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
					
				}
				
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
				{
				
				}
			});
		

	}

	@Override
	public void initData()
	{
		dialog = DialogUtil.getDialog(this);

		dialog.show();

		cityname = ConstantValues.LOACTIONCITY;

		cityid = ConstantValues.cityid + "";

		common_back.setVisibility(View.VISIBLE);

		common_bt.setVisibility(View.VISIBLE);

		common_title.setText(ConstantValues.PRACTICE_TITLE);

		common_bt.setText(cityname);

		common_back.setOnClickListener(this);

		auto_edit.addTextChangedListener(this);

		common_bt.setOnClickListener(this);

		latitude = ConstantValues.LATITUDE;

		longitude = ConstantValues.LONGITUDE;

		getData();

	}

	/**
     * 
     * 
     * */
	@Override
	public void getData()
	{

		ServiceFactory
				.getPracEngineInstatice()
				.findPracticeSearch(cityid, rgname, longitude, latitude, new IOAuthCallBack()
				{

					@Override
					public void getIOAuthCallBack(String result)
					{
						if (result.equals(ConstantValues.FAILURE))
						{

							ToastUtils
									.showInfo(PracticeListActivity.this, ConstantValues.NONETWORK);

							dialog.cancel();

							onLoaded();

						}
						else
						{

							PracticeEntity bean = GsonTools
									.changeGsonToBean(result, PracticeEntity.class);

							if (bean.getData().size() == 0 & !isR)
							{
								ToastUtils
										.showInfo(PracticeListActivity.this, ConstantValues.NONETWORK);
							}
							else
							{
								relayout.setVisibility(View.GONE);

								List<DataEntity> data = bean.getData();

								list = (ArrayList<PracticeEntity.DataEntity>) adapter
										.getList();

								if (bean.getData().size() == 0)

								relayout.setVisibility(View.VISIBLE);

								if (isR)
								{
									list.clear();

									list.addAll(data);

								}
								else
								{
									if (list != null & ismoredata)
									{
										list.addAll(data);

									}
									else
									{
										adapter.setList(data);
									}
								}

								adapter.notifyDataSetChanged();

								dialog.cancel();
							}

							setLastUpdateTime();

							onLoaded();
						}
					}
				});

	}

	public void onTel(String mobile)
	{
		TelUtils.tel(this, mobile);
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

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.common_back:

				finish();

				break;

			case R.id.common_bt:

				Intent cityIntent = new Intent(this, CityActivity.class);

				cityIntent.putExtra("type", "parcity");

				startActivityForResult(cityIntent, ConstantValues.CITY_CODE);

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

		if (requestCode == ConstantValues.CITY_CODE)
		{
			CityEntity city = (CityEntity) data.getSerializableExtra("city");

			cityname = city.getName();

			common_bt.setText(city.getName());

			cityid = city.getId() + "";

			isR = true;

			list.clear();

			adapter.notifyDataSetChanged();

			getData();

		}
	}

	@Override
	public void afterTextChanged(Editable arg0)
	{
		// 输入内容后

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3)
	{

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3)
	{

		String ed_text = auto_edit.getText().toString().trim();

		if (!CheckUtils.checkEmpty(ed_text))
		{
			rgname = ed_text;

			isR = true;

			if (list != null) list.clear();

			adapter.notifyDataSetChanged();

			getData();

		}
		else
		{
			rgname = "";

			isR = true;

			list.clear();

			adapter.notifyDataSetChanged();

			getData();
		}
	}
}
