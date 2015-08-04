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
import me.wangolf.adapter.EveSend2LvCBAdapter;
import me.wangolf.adapter.EveSend2LvCBAdapter.ViewHolder;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.InfoEntity;
import me.wangolf.bean.event.EventSend2DataEntity;
import me.wangolf.bean.event.SendEventBean;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ToastUtils;

public class EventSendActivity_2 extends BaseActivity implements OnClickListener, OnItemClickListener {
	@ViewInject(R.id.common_back)
	private Button common_back; // 后退
	@ViewInject(R.id.common_title)
	private TextView common_title;// 标题
	@ViewInject(R.id.common_bt)
	private TextView common_bt;// 地图
	@ViewInject(R.id.listView1)
	private ListView listView1;
	private EveSend2LvCBAdapter adapter;
	private ArrayList<EventSend2DataEntity> data;
	private SendEventBean bean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_event_send2);
		ViewUtils.inject(this);
		initData();
		if (adapter == null) {
			adapter = new EveSend2LvCBAdapter(EventSendActivity_2.this);
			listView1.setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}

	}

	@Override
	public void initData() {
		bean = (SendEventBean) getIntent().getSerializableExtra("event_bean");
		common_back.setVisibility(0);
		common_bt.setVisibility(0);
		common_title.setText("发起活动(2/2)");
		common_bt.setText("提交");
		listView1.setOnItemClickListener(this);
		common_back.setOnClickListener(this);
		common_bt.setOnClickListener(this);
		getData();

	}

	@Override
	public void getData() {

		try {
			ServiceFactory.getEventEngineInstatice().getOptionsKeys(new IOAuthCallBack() {

				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), ConstantValues.NONETWORK, 0).show();
					} else {
						EventSend2DataEntity bean = GsonTools.changeGsonToBean(result, EventSend2DataEntity.class);
						if ("1".equals(bean.getStatus())) {
							data = new ArrayList<EventSend2DataEntity>();

							EventSend2DataEntity item1 = new EventSend2DataEntity();
							item1.setId("-1");
							item1.setName("姓名");
							data.add(item1);
							EventSend2DataEntity item2 = new EventSend2DataEntity();
							item2.setId("-2");
							item2.setName("性别");
							data.add(item2);
							EventSend2DataEntity item3 = new EventSend2DataEntity();
							item3.setId("-3");
							item3.setName("取系方式");
							data.add(item3);
							for (int i = 0; i < bean.getData().size(); i++) {
								data.add(bean.getData().get(i));
							}
							adapter.setList(data);
							bean.getData();
							adapter.notifyDataSetChanged();
						} else {
							ToastUtils.showInfo(getApplicationContext(), bean.getInfo());
						}
					}

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
		case R.id.common_bt:
			toSendEvent();
			break;
		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		listView1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ViewHolder vHollder = (ViewHolder) view.getTag();
				// 在每次获取点击的item时将对于的checkbox状态改变，同时修改map的值。
				// 如过不是固定的选项（前5个之外）
				if (position > 4) {
					vHollder.cBox.toggle();
					EveSend2LvCBAdapter.isSelected.put(position, vHollder.cBox.isChecked());
				}
			}
		});
	}

	// 提交活动
	public void toSendEvent() {
		StringBuffer sb = new StringBuffer();// 保存用户选填需要提交到后台option
		// 获得可选的id
		for (int j = 0; j < EveSend2LvCBAdapter.isSelected.size(); j++) {
			if (j >= 5 && EveSend2LvCBAdapter.isSelected.get(j)) {
				sb.append(data.get(j).getId());
				sb.append(",");
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		if (sb.length() > 0) {
			bean.setOption(sb.toString().trim());
		}

		try {
			ServiceFactory.getEventEngineInstatice().sendEvent(bean, new IOAuthCallBack() {
				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), ConstantValues.FAILURE, 0).show();
					} else {
						InfoEntity bean = GsonTools.changeGsonToBean(result, InfoEntity.class);
						if ("1".equals(bean.getStatus())) {
							ToastUtils.showInfo(EventSendActivity_2.this, bean.getInfo());
							finish();
						} else {
							ToastUtils.showInfo(EventSendActivity_2.this, bean.getInfo());
						}
					}

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
