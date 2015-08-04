package me.wangolf.ballprac;

import com.meigao.mgolf.R;
import me.wangolf.bean.ball.BallDistriEntity;
import me.wangolf.bean.practice.PracDistriEntity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * 球场预定的dialog
 * 
 * @author Administrator
 * 
 */
public class OrderDialog extends Activity {

	BallDistriEntity balldestrientity;
	private TextView tvtitle;
	private TextView tvdate;
	private TextView tvservice;
	private PracDistriEntity pracDistriEntity;
	private String type;
	private TextView tvboughtbook;
	private TextView tvmoney;
	private TextView tvboughtbooklable;
	private TextView tvcanclebook;
	private TextView tvcanclebooklable;
	private TextView tvpricebook;
	private String date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderdialog);
		setViews();
		getData();
		setListeners();
		setData();
	}

	/**
	 * 取得点击后的数据
	 */
	private void getData() {
		Intent intent = getIntent();
		type = intent.getStringExtra("type");
		if ("ball".equals(type)) {
			this.balldestrientity = (BallDistriEntity) intent.getSerializableExtra("entity");
			date = getIntent().getStringExtra("date");
		} else if ("practice".equals(type)) {
			this.pracDistriEntity = (PracDistriEntity) intent.getSerializableExtra("entity");
			tvboughtbooklable.setText("下单说明");
		}

	}

	private void setViews() {
		tvtitle = (TextView) findViewById(R.id.title);
		tvdate = (TextView) findViewById(R.id.date);
		tvservice = (TextView) findViewById(R.id.service);
		tvboughtbook = (TextView) findViewById(R.id.tv_boughtbook);
		tvboughtbooklable = (TextView) findViewById(R.id.tv_boughtbook_lable);
		tvcanclebooklable = (TextView) findViewById(R.id.tv_canclebook_lable);
		tvcanclebook = (TextView) findViewById(R.id.tv_canclebook);
		tvmoney = (TextView) findViewById(R.id.tvmoney);
		tvpricebook = (TextView) findViewById(R.id.tv_price_book);
	}

	private void setListeners() {

	}

	public void setData() {
		if ("ball".equals(type)) {
			tvtitle.setText(balldestrientity.getDname());
			tvdate.setText(date);// 设时间
			tvservice.setText(balldestrientity.getService());
			String boughtbook = balldestrientity.getBook();
			String canclebook = balldestrientity.getCancelbook();
			if ("".equals(boughtbook)) {
				tvboughtbook.setVisibility(View.GONE);
				tvboughtbooklable.setVisibility(View.GONE);
			} else {
				tvboughtbook.setText(boughtbook);
			}
			if ("".equals("canclebook")) {
				tvcanclebook.setVisibility(View.GONE);
				tvcanclebooklable.setVisibility(View.GONE);
			} else {
				tvcanclebook.setText(canclebook);
			}
			tvmoney.setText("￥" + balldestrientity.getPrice());
			int pricetype = balldestrientity.getPricetype();
			String text = "";
			switch (pricetype) {
			case 2:
				text = "球场现付";
				break;
			case 3:
				text = "全额支付";
				break;
			case 4:
				text = "部分支付";
				break;

			default:
				break;
			}
			tvpricebook.setText(text);
		} else if ("practice".equals(type)) {
			tvtitle.setText(pracDistriEntity.getRgname());
			// tvdate.setText(GlobalConsts.dateTime);
			tvservice.setText(pracDistriEntity.getPrice());
			tvboughtbook.setText(pracDistriEntity.getBook());
			tvmoney.setText("￥" + pracDistriEntity.getPrice());
		}

	}

	/**
	 * 跳到订单
	 * 
	 * @param v
	 */
	public void toOrder(View v) {
		if ("ball".equals(type)) {
			String ballid = getIntent().getStringExtra("ballid");// 从DistributorListAdapter传过来的ballid
			String ballname = getIntent().getStringExtra("ballname");// 从DistributorListAdapter传过来的ballid
			Intent intent = new Intent(OrderDialog.this, BallToBuyActivity.class);
			intent.putExtra("balldestrientity", balldestrientity);
			intent.putExtra("ballid", ballid);// 传送球场id
			intent.putExtra("date", date);// 传送球场id
			intent.putExtra("ballname", ballname);// 传送球场id
			startActivity(intent);
			this.finish();
		} else if ("practice".equals(type)) {
			// String
			// rgid=getIntent().getStringExtra("rgid");//从DistributorListAdapter传过来的ballid
			// Intent intent=new
			// Intent(OrderDialog.this,PracToBuyActivity.class);
			// intent.putExtra("pracDistriEntity",pracDistriEntity );
			// intent.putExtra("rgid",rgid );//传送球场id
			// startActivity(intent);
			// this.finish();
		}
	}

	/**
	 * 关闭
	 * 
	 * @param v
	 */
	public void cancle(View v) {
		this.finish();
	}

	/**
	 * 返回键事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// 需要处理
			this.finish();
		}
		return false;
	}

	@Override
	protected void onResume() {
		super.onResume();

	}
}
