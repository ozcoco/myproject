package me.wangolf.practice;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.practice.PracDistriEntity;
import me.wangolf.usercenter.LoginActivity;

public class OrderDialogPractice extends BaseActivity implements OnClickListener {

	@ViewInject(R.id.close_dilog)
	private ImageView close_dilog;
	@ViewInject(R.id.title)
	private TextView title; // 供应商
	@ViewInject(R.id.tv_book)
	private TextView tv_book;// 简介
	@ViewInject(R.id.tvmoney)
	private TextView tvmoney;// 价钱
	private String rgname;
	@ViewInject(R.id.bu_order)
	private Button bu_order;// 下订单
	private PracDistriEntity bean;
	private String rgid;// 练习场ID

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderdialog_practice);
		ViewUtils.inject(this);
		initData();
	}

	@Override
	public void initData() {
		close_dilog.setOnClickListener(this);
		bean = (PracDistriEntity) getIntent().getSerializableExtra("bean");
		rgname = getIntent().getStringExtra("rgname");
		rgid = getIntent().getStringExtra("rgid");
		title.setText(rgname);
		tv_book.setText(bean.getBook());
		tvmoney.setText("￥" + bean.getPrice());
		bu_order.setOnClickListener(this);

	}

	@Override
	public void getData() {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.close_dilog:
			finish();
			break;
		case R.id.bu_order:
			toBuy();
			break;
		default:
			break;
		}
	}

	public void toBuy() {
		if (ConstantValues.ISLOGIN) {
			if (bean != null) {
				Intent intent = new Intent(this, PracToBuyActivity.class);
				intent.putExtra("bean", bean);
				intent.putExtra("rgid", rgid);
				intent.putExtra("rgname", rgname);
				this.startActivity(intent);
				finish();
			}
		} else {
			// 支登录
			Intent toLogin = new Intent(this, LoginActivity.class);
			toLogin.putExtra("flag", "orderPrac");
			startActivityForResult(toLogin, ConstantValues.ORDERPRAC);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data == null) {
			return;
		}
		if (requestCode == ConstantValues.ORDERPRAC) {
			toBuy();
		}
	}

}
