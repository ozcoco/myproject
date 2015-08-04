package me.wangolf.event;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.InfoEntity;
import me.wangolf.bean.event.EventSend2DataEntity;
import me.wangolf.bean.usercenter.OrderpayBean;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.usercenter.OrderPayActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.DialogUtils;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ToastUtils;

public class EventEnrolingActivity extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.common_back)
	private Button common_back; // 后退
	@ViewInject(R.id.common_title)
	private TextView common_title;// 标题
	@ViewInject(R.id.common_bt)
	private TextView common_bt;// 地图
	@ViewInject(R.id.ed_name)
	private EditText ed_name;// 姓名
	@ViewInject(R.id.ed_mobile)
	private EditText ed_mobile;// 联系电话
	@ViewInject(R.id.rdman)
	private RadioButton rdman;// 男
	@ViewInject(R.id.rdwoman)
	private RadioButton rdwoman;// 女
	@ViewInject(R.id.layoutMain)
	private LinearLayout layoutMain;//
	@ViewInject(R.id.tv_total_price)
	private TextView tv_total_price;// 总价格
	private ArrayList<EditText> editList;
	protected ArrayList<EventSend2DataEntity> backList;
	private String eventid;// 活动ID
	private LinearLayout layout;
	private OrderpayBean bean;
	private Double price;// 价格
	private String gender = "1";// 性别
	private String uid;
	private String cid;
	private String mobile;
	private String eid;
	private String cusname;
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_event_enrolling);
		ViewUtils.inject(this);
		initData();
	}

	@Override
	public void initData() {
		bean = (OrderpayBean) getIntent().getSerializableExtra("bean");
		eventid = getIntent().getStringExtra("eveid");
		price = bean.getOrder_amount();
		common_back.setVisibility(0);
		common_bt.setVisibility(0);
		common_title.setText("活动报名");
		common_bt.setText("确认");
		tv_total_price.setText("￥" + price);
		rdman.setOnClickListener(this);
		rdwoman.setOnClickListener(this);
		common_back.setOnClickListener(this);
		common_bt.setOnClickListener(this);
		getData();
	}

	@Override
	public void getData() {
		try {
			ServiceFactory.getEventEngineInstatice().getOptionsValues(eventid, new IOAuthCallBack() {

				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), ConstantValues.NONETWORK, 0).show();
					} else {
						EventSend2DataEntity bean = GsonTools.changeGsonToBean(result, EventSend2DataEntity.class);
						if ("1".equals(bean.getStatus())) {
							backList = bean.getData();
							makeViews();
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

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.common_back:
			finish();
			break;
		case R.id.rdman:
			gender = "1";
			rdwoman.setTextColor(getResources().getColor(R.color.common_text));
			rdman.setTextColor(getResources().getColor(R.color.white));
			rdman.setBackground(getResources().getDrawable(R.drawable.bt_red_rdman));
			rdwoman.setBackgroundColor(getResources().getColor(R.color.transparent));
			break;
		case R.id.rdwoman:
			gender = "0";
			rdman.setTextColor(getResources().getColor(R.color.common_text));
			rdwoman.setTextColor(getResources().getColor(R.color.white));
			rdwoman.setBackground(getResources().getDrawable(R.drawable.bt_red_rdwoman));
			rdman.setBackgroundColor(getResources().getColor(R.color.transparent));
			break;
		case R.id.common_bt:
			if (CheckUtils.checkEmpty(ed_name.getText().toString().trim())) {
				ToastUtils.showInfo(EventEnrolingActivity.this, "请填写姓名");
				return;
			} else if (CheckUtils.checkEmpty(ed_mobile.getText().toString()) | ed_mobile.getText().toString().length() != 11) {
				ToastUtils.showInfo(EventEnrolingActivity.this, "请填写正确的联系方式");
				return;
			}

			if (price > 0) {
				dialog = DialogUtil.getDialog(this);
				dialog.show();
				// 要钱 去付款
				toPay();
			} else {
				// 不要钱 去参加
				DialogUtils.tel(this, "是否确定参加活动?", "enroling");
			}

			break;
		default:
			break;
		}
	}

	@SuppressLint("NewApi")
	protected void makeViews() {
		editList = new ArrayList<EditText>();
		for (int i = 0; i < backList.size(); i++) {
			layout = (LinearLayout) View.inflate(this, R.layout.item_event_enrolling, null);
			layout.setTop(10);
			layout.setId(1000 + i);
			EventSend2DataEntity bean = backList.get(i);
			TextView tv = (TextView) layout.findViewById(R.id.tv);
			tv.setId(2000 + i);
			if (bean != null) {
				String name = bean.getName();
				if (!CheckUtils.checkEmpty(name)) {
					if (name.length() == 2) {
						name = name.substring(0, 1) + "\t\t\t\t" + name.substring(1, name.length());
					} else if (name.length() > 10) {
						name = name.substring(0, 5) + "..";
					}
				}
				tv.setText(name + "：");
				EditText ed = (EditText) layout.findViewById(R.id.ed);
				tv.setId(3000 + i);
				ed.setTag(bean);
				editList.add(ed);
				layoutMain.addView(layout);
			}
		}
	}

	// 去支付
	public void toPay() {

		String option = "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < editList.size(); i++) {
			EditText ed = editList.get(i);
			String text = ed.getText().toString();
			String id = "";
			if (!CheckUtils.checkEmpty(text)) {
				EventSend2DataEntity tag = (EventSend2DataEntity) ed.getTag();
				id = tag.getId();
			}
			if (!CheckUtils.checkEmpty(text)) {
				sb.append(id).append("=").append(text).append(",");
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.lastIndexOf(","));
			option = sb.toString();
		}
		bean.setConsumer_name(ed_name.getText().toString());
		bean.setMobiel(ed_mobile.getText().toString().trim());
		bean.setOption(option);
		bean.setGender(gender);

		Prepay(bean);

	}

	// 直接参加活动
	public void toEventJoin() {
		cusname = ed_name.getText().toString();
		uid = ConstantValues.UID;
		cid = bean.getSupplier_id();
		eid = bean.getProduct_id() + "";
		mobile = ConstantValues.USER_MOBILE;
		String option = "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < editList.size(); i++) {
			EditText ed = editList.get(i);
			String text = ed.getText().toString();
			String id = "";
			if (!CheckUtils.checkEmpty(text)) {
				EventSend2DataEntity tag = (EventSend2DataEntity) ed.getTag();
				id = tag.getId();
			}
			if (!CheckUtils.checkEmpty(text)) {
				sb.append(id).append("=").append(text).append(",");
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.lastIndexOf(","));
			option = sb.toString();
		}
		try {
			ServiceFactory.getIUserEngineInstatice().toEventJoin(uid, cid, eid, cusname, gender, option, mobile, new IOAuthCallBack() {

				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), ConstantValues.FAILURE, 0).show();
					} else {
						InfoEntity bean = GsonTools.changeGsonToBean(result, InfoEntity.class);
						if ("1".equals(bean.getStatus())) {
							ToastUtils.showInfo(getApplicationContext(), bean.getInfo());
							finish();
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

	// 生成订单
	public void Prepay(OrderpayBean order_bean) {
		try {
			ServiceFactory.getIUserEngineInstatice().toPrepay(order_bean, new IOAuthCallBack() {

				private String sn;

				@Override
				public void getIOAuthCallBack(String result) {
					JSONObject jsonObj;
					try {
						jsonObj = new JSONObject(result);
						String status = jsonObj.getString("status");
						if ("1".equals(status)) {
							JSONArray jsonArray = jsonObj.getJSONArray("data");
							if (jsonArray.length() > 0) {
								JSONObject obj = jsonArray.getJSONObject(0);
								sn = obj.getString("sn");
							}
							if (sn != null) {
								Intent order_bean = new Intent(getApplicationContext(), OrderPayActivity.class);
								order_bean.putExtra("order_bean", bean);
								order_bean.putExtra("sn", sn);
								order_bean.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								getApplicationContext().startActivity(order_bean);

							}

						} else {
							ToastUtils.showInfo(getApplicationContext(), jsonObj.getString("info"));
						}
						dialog.cancel();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
