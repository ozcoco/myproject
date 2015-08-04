package me.wangolf.college;

import java.util.ArrayList;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.CoachListAdapter;
import me.wangolf.bean.college.CoachListEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshListView;
import me.wangolf.utils.viewUtils.PullToRefreshBase.OnRefreshListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CollegeCoachList extends Activity implements OnClickListener {
	private ArrayList<CoachListEntity> list; // 列表list
	private CoachListAdapter adapter;
	@ViewInject(R.id.pull_refresh_list)
	private PullToRefreshListView pull_refresh_list;
	@ViewInject(R.id.common_title)
	private TextView common_title;
	@ViewInject(R.id.common_back)
	private Button common_back;
	private String rangeid; // 练习场ID
	private String sorttype = "1"; // 0按距离排序1按人气2按教龄
	private int page = 1; // 页数
	private int number = 10; // 每页显示数目
	private String collegeid; // 学院ID（显示所有学院则填写空字符）
	private String latitude; // 纬度
	private String longitude; // 经度
	private boolean ismoredata;// 加载更多
	private boolean ismore = false; // 断判是否有更多数据
	private boolean isR;
	private Dialog dialog;
	private String sort = "0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_college_coach_list);
		ViewUtils.inject(this);
		if (adapter == null) {
			adapter = new CoachListAdapter(getApplicationContext());
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

				CoachListEntity bean = (CoachListEntity) adapter.getItem(position);
				if ("".equals(bean.getCoachid())) {
					Toast.makeText(getApplicationContext(), ConstantValues.COLLEGE_ID, 0).show();
				} else {
					Intent intent = new Intent(getApplicationContext(), CoachInfoActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.putExtra("coachid", bean.getCoachid() + "");
					getApplicationContext().startActivity(intent);
				}
			}
		});

		// 设置下拉刷新的listener
		pull_refresh_list.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				page = 1;
				isR = true;
				getData();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				if (!ismore) {
					page = page + 1;
					ismoredata = true;
					isR = false;
					getData();
				}
			}
		});
		initData();
	}

	public void initData() {
		dialog = DialogUtil.getDialog(this);
		dialog.show();
		latitude = ConstantValues.LATITUDE;
		longitude = ConstantValues.LONGITUDE;
		common_back.setVisibility(0);
		common_back.setOnClickListener(this);
		collegeid = getIntent().getStringExtra("collegeid");
		rangeid = getIntent().getStringExtra("rgid");
		common_title.setText(ConstantValues.COACH_TITLE);
		getData();
	}

	public void getData() {

		try {
			ServiceFactory.getCollegeEngineInstatice().getCoachList(rangeid, sorttype, sort, page + "", number + "", collegeid, latitude, longitude,
					new IOAuthCallBack() {

						@Override
						public void getIOAuthCallBack(String result) {
							if (result.equals(ConstantValues.FAILURE)) {
								Toast.makeText(CollegeCoachList.this, ConstantValues.NONETWORK, 0).show();
							} else {
								CoachListEntity bean = GsonTools.changeGsonToBean(result, CoachListEntity.class);
								ArrayList<CoachListEntity> beaninfo = bean.getData();
								if (beaninfo.size() == 0) {
									ismore = true;
									onLoaded();
									Toast.makeText(getApplicationContext(), ConstantValues.NOMORE, 0).show();
								} else {
									list = (ArrayList<CoachListEntity>) adapter.getList();
									if (isR) {
										list.clear();
										list.addAll(beaninfo);
									} else if (list != null & ismoredata) {
										list.addAll(beaninfo);
									} else {
										adapter.setList(beaninfo);
									}
									adapter.notifyDataSetChanged();

								}

							}
							dialog.cancel();
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
