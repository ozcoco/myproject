package me.wangolf.event;

import java.util.ArrayList;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
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
import me.wangolf.adapter.EventJoinPersonsAdapter;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.event.EventJoinPersonsEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshListView;
import me.wangolf.utils.viewUtils.PullToRefreshBase.OnRefreshListener;

public class EventJoinPersonsListActivity extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.common_back)
	private Button common_back; // 后退
	@ViewInject(R.id.common_title)
	private TextView common_title;// 标题
	@ViewInject(R.id.common_bt)
	private TextView common_bt;// 地图
	@ViewInject(R.id.pull_refresh_list)
	private PullToRefreshListView pull_refresh_list;
	private EventJoinPersonsAdapter adapter;
	private String eventid;
	private int page = 1;
	private String number = "15";
	private boolean ismoredata;
	private boolean ismore;
	private boolean isR;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_event_joinperson_list);
		ViewUtils.inject(this);
		if (adapter == null) {
			adapter = new EventJoinPersonsAdapter(this);
		} else {
			adapter.notifyDataSetChanged();
		}
		pull_refresh_list.getRefreshableView().setAdapter(adapter);
		pull_refresh_list.setPullLoadEnabled(true);
		// 滚动到底自动加载可用
		pull_refresh_list.setScrollLoadEnabled(true);
		// 得到实际的ListView 设置点击
		pull_refresh_list.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			}
		});

		// 设置下拉刷新的listener
		pull_refresh_list.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				page = 1;
				ismore = false;
				isR = true;
				getData();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				if (!ismore) {
					isR = false;
					page = page + 1;
					ismoredata = true;
					getData();
				}

			}
		});
		initData();
	}

	@Override
	public void initData() {
		common_back.setVisibility(0);
		// common_bt.setVisibility(0);
		common_title.setText(ConstantValues.EVENT_PERSON);
		// common_bt.setText(ConstantValues.SHARE);
		common_back.setOnClickListener(this);
		eventid = getIntent().getStringExtra("eventid");
		getData();

	}

	@Override
	public void getData() {
		try {
			ServiceFactory.getEventEngineInstatice().getJoinEventList(eventid, page + "", number, new IOAuthCallBack() {

				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), ConstantValues.NONETWORK, 0).show();
					} else {
						EventJoinPersonsEntity bean = GsonTools.changeGsonToBean(result, EventJoinPersonsEntity.class);
						if ("1".equals(bean.getStatus())) {
							ArrayList<EventJoinPersonsEntity> data = bean.getData();
							if (data.size() == 0) {
								ismore = true;
								onLoaded();
								Toast.makeText(getApplicationContext(), ConstantValues.NOMORE, 0).show();
							} else {
								ArrayList<EventJoinPersonsEntity> list = adapter.getList();
								if (isR) {
									list.clear();
									list.addAll(data);
								} else {
									if (list != null & ismoredata) {
										list.addAll(data);
									} else {
										adapter.setList(data);
									}
								}
								adapter.notifyDataSetChanged();
							}
						}
					}
					onLoaded();
					setLastUpdateTime();
				}
			});
		} catch (Exception e) {

			e.printStackTrace();
		}

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

	private void setLastUpdateTime() {
		String text = CommonUtil.getStringDate();
		pull_refresh_list.setLastUpdatedLabel(text);
	}

	private void onLoaded() {
		pull_refresh_list.onPullDownRefreshComplete();
		pull_refresh_list.onPullUpRefreshComplete();
	}

}
