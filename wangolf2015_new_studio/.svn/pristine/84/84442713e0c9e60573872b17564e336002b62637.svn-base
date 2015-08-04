package me.wangolf.ballprac;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.ball.BallDistriEntity;
import me.wangolf.bean.usercenter.OrderpayBean;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.usercenter.OrderScuessActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.TelUtils;
import me.wangolf.utils.ToastUtils;

public class BallToBuyActivity extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.common_back)
	private Button common_back; // 后退
	@ViewInject(R.id.common_title)
	private TextView common_title;// 标题
	@ViewInject(R.id.common_bt)
	private TextView common_bt;// 电话
	@ViewInject(R.id.subtraction)
	private TextView subtraction;// 减少
	@ViewInject(R.id.add)
	private TextView add;// 增加
	@ViewInject(R.id.tv_num)
	private TextView tv_num;// 购买数量
	@ViewInject(R.id.tv_contact)
	private EditText tv_contact;// 电话号码
	@ViewInject(R.id.tv_brief)
	private TextView tv_brief;// 球场名称
	@ViewInject(R.id.tv_total_price)
	private TextView tv_total_price;// 总价格
	@ViewInject(R.id.tv_person)
	private TextView tv_person;// 取系人
	@ViewInject(R.id.tv_content)
	private TextView tv_content;// 包含
	@ViewInject(R.id.tv_date)
	private TextView tv_date;// 时间
	@ViewInject(R.id.btpay)
	private Button btpay;// 确认购买
	@ViewInject(R.id.tv_remarkes)
	private EditText tv_remarkes;// 备注
	private Double price;// 单价
	private Double all_price;// 总价格
	private int buy_num = 1;// 默认购买
	private BallDistriEntity bean;
	private String ballid;
	private String date;
	private OrderpayBean order_bean;
	private String sn;// 返回的订单ID

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_ball_tobuy);
		ViewUtils.inject(this);
		initData();
	}

	@Override
	public void initData() {
		common_back.setVisibility(0);
		common_bt.setVisibility(0);
		common_title.setText(ConstantValues.PRACTICE_ORDER);
		common_bt.setBackgroundResource(R.drawable.bt_phone_selector);
		common_back.setOnClickListener(this);
		common_bt.setOnClickListener(this);
		add.setOnClickListener(this);
		subtraction.setOnClickListener(this);
		btpay.setOnClickListener(this);
		tv_contact.setText(ConstantValues.USER_MOBILE);
		String ballname = getIntent().getStringExtra("ballname");// 球场ID
		date = getIntent().getStringExtra("date");
		ballid = getIntent().getStringExtra("ballid");
		bean = (BallDistriEntity) getIntent().getSerializableExtra("balldestrientity");
		tv_brief.setText(ballname);
		tv_date.setText(date);
		price = (double) bean.getPrice();
		all_price = price;
		tv_content.setText(bean.getService());
		tv_total_price.setText("￥" + price);

	}

	@Override
	public void getData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.common_back:
			finish();
			break;
		case R.id.common_bt:
			TelUtils.tel(this, ConstantValues.TEL_PHONE);
			break;
		case R.id.add:
			buy_num = Integer.parseInt(tv_num.getText().toString());
			buy_num++;
			all_price = price * buy_num;
			tv_num.setText((buy_num) + "");
			tv_total_price.setText("￥" + all_price);
			break;
		case R.id.subtraction:
			buy_num = Integer.parseInt(tv_num.getText().toString());
			if (buy_num > 1) {
				buy_num--;
				all_price = price * buy_num;
				tv_num.setText((buy_num) + "");
				tv_total_price.setText("￥" + all_price);
			}
			break;
		case R.id.btpay:
			toBuy();
			break;
		default:
			break;
		}
	}

	public void toBuy() {
		if (CheckUtils.checkEmpty(tv_person.getText().toString().trim())) {
			ToastUtils.showInfo(this,"取系人姓名不能为空");
			return;
		}

		if (CheckUtils.checkEmpty(tv_contact.getText().toString().trim())|tv_contact.getText().toString().trim().length()!=11) {
			ToastUtils.showInfo(this, "请输入正确手机号");
		return;
		}
		order_bean = new OrderpayBean();
		order_bean.setType("1");// 1球场
		order_bean.setUser_id(ConstantValues.UID);// 用户ID
		order_bean.setSupplier_id(bean.getCid() + "");// 供应商ID
		order_bean.setMobiel(tv_contact.getText().toString().trim());// 电话号码
		order_bean.setConsumer_name(tv_person.getText().toString().trim());// 消费姓名
		order_bean.setConsumer_num(buy_num);// 消费数量
		order_bean.setProduct_id(bean.getId());// ???产品ID
		order_bean.setOrder_amount(all_price);// 总价格
		order_bean.setBook(tv_remarkes.getText().toString().trim());// 备注
		order_bean.setArrival_time(date);// 到达时间
		order_bean.setCourt_id(ballid);// 球场/练习场ID
		// Intent intent = new Intent(this, OrderPayActivity.class);
		// intent.putExtra("order_bean", order_bean);
		// startActivity(intent);
		Prepay();
	}

	// 生成订单
	public void Prepay() {
		try {
			ServiceFactory.getIUserEngineInstatice().toPrepay(order_bean, new IOAuthCallBack() {

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
								if ("1".equals(order_bean.getType())) {
									// 球场生成订单后 跳到成功等待后确认
									Intent intent = new Intent(getApplicationContext(), OrderScuessActivity.class);
									intent.putExtra("sn", sn);
									intent.putExtra("user_id", ConstantValues.UID);
									intent.putExtra("title", "订单已提交成功");
									intent.putExtra("message", "商家需要跟球会确认现在是否可预定,30分钟内会发短信通知您付款完成预定");
									intent.putExtra("type", (Integer.parseInt(order_bean.getType())) + "");// 用于查看订单列表
									startActivity(intent);
									finish();
								} else {

								}

							}

						}

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
