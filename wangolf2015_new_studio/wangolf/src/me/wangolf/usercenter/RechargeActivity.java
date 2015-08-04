package me.wangolf.usercenter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月28日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月28日
 * 
 * 描述 ：用户账户充值
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.util.Date;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

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
import me.wangolf.bean.usercenter.OrderpayBean;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.DateFormatUtils;
import me.wangolf.utils.ToastUtils;

public class RechargeActivity extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.common_back)
	private Button common_back; // 后退
	@ViewInject(R.id.common_title)
	private TextView common_title;// 标题

	@ViewInject(R.id.radio_1000)
	private RadioButton radio_1000;
	@ViewInject(R.id.radio_2000)
	private RadioButton radio_2000;
	@ViewInject(R.id.radio_5000)
	private RadioButton radio_5000;
	@ViewInject(R.id.radio_10000)
	private RadioButton radio_10000;
	@ViewInject(R.id.radio_20000)
	private RadioButton radio_20000;
	@ViewInject(R.id.radio_n)
	private RadioButton radio_n;

	@ViewInject(R.id.money_1000)
	private LinearLayout money_1000;
	@ViewInject(R.id.money_2000)
	private LinearLayout money_2000;
	@ViewInject(R.id.money_5000)
	private LinearLayout money_5000;
	@ViewInject(R.id.money_10000)
	private LinearLayout money_10000;
	@ViewInject(R.id.money_20000)
	private LinearLayout money_20000;
	@ViewInject(R.id.money_n)
	private LinearLayout money_n;
	@ViewInject(R.id.ed_otherprice)
	private EditText ed_otherprice;// 其他金额
	@ViewInject(R.id.bt_next)
	private Button bt_next;// 提交充值
	private Double order_amount = 0.0;// 充值金额
	private String type = "6";
	private String uid;// 用户iD
	private boolean isother;// 选择其他金额

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_recharge);
		ViewUtils.inject(this);
		initData();
	}

	@Override
	public void initData() {
		uid = ConstantValues.UID;
		common_back.setVisibility(View.VISIBLE);
		common_title.setText(ConstantValues.USERRECHARGE);
		common_back.setOnClickListener(this);
		money_1000.setOnClickListener(this);
		money_2000.setOnClickListener(this);
		money_5000.setOnClickListener(this);
		money_10000.setOnClickListener(this);
		money_20000.setOnClickListener(this);
		money_n.setOnClickListener(this);
		bt_next.setOnClickListener(this);

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
		case R.id.money_1000:
			RadioButton();
			order_amount = 1000.0;
			radio_1000.setChecked(true);
			break;
		case R.id.money_2000:
			RadioButton();
			order_amount = 2000.0;
			radio_2000.setChecked(true);
			break;
		case R.id.money_5000:
			RadioButton();
			order_amount = 5000.0;
			radio_5000.setChecked(true);
			break;
		case R.id.money_10000:
			RadioButton();
			order_amount = 10000.0;
			radio_10000.setChecked(true);
			break;
		case R.id.money_20000:
			RadioButton();
			order_amount = 20000.0;
			radio_20000.setChecked(true);
			break;
		case R.id.money_n:
			RadioButton();
			ed_otherprice.setVisibility(View.VISIBLE);
			radio_n.setChecked(true);
			isother = true;
			break;
		case R.id.bt_next:
			toRechare();
			break;
		default:
			break;
		}
	}

	public void toRechare() {

		if (isother) {
			if (CheckUtils.checkEmpty(ed_otherprice.getText().toString().trim())) {
				//Toast.makeText(getApplicationContext(), "充值金额不能为空", 0).show();
				ToastUtils.showInfo(RechargeActivity.this, "充值金额不能为空");
				return;
			}
			order_amount = Double.parseDouble(ed_otherprice.getText().toString().trim());
		}
		if (order_amount <= 0) {
			ToastUtils.showInfo(this, "请选择充值金额");
			return;
		}
		String date = null;// 当前时间
		try {
			date = DateFormatUtils.formatDetail(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OrderpayBean order_bean = new OrderpayBean();
		order_bean.setType(type);
		order_bean.setUser_id(uid);
		order_bean.setConsumer_num(1);
		order_bean.setMobiel(ConstantValues.USER_MOBILE);
		order_bean.setArrival_time(date);
		order_bean.setOrder_amount(order_amount);
		// order_bean.setOrder_amount(0.1);

		Intent order = new Intent(this, OrderPayActivity.class);
		order.putExtra("order_bean", order_bean);
		order.putExtra("type", "recharge");
		startActivity(order);
	}

	public void RadioButton() {
		radio_1000.setChecked(false);
		radio_2000.setChecked(false);
		radio_5000.setChecked(false);
		radio_10000.setChecked(false);
		radio_20000.setChecked(false);
		radio_n.setChecked(false);
		ed_otherprice.setVisibility(View.GONE);
		isother = false;
	}
}
