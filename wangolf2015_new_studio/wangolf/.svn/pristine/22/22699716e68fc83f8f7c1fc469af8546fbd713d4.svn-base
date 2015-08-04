package me.wangolf.practice;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.practice.PracTagDetailEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ToastUtils;

public class PracTagDetailActivity extends BaseActivity implements OnClickListener {
	private String rgid; // 球场ID
	@ViewInject(R.id.tv_teenum)
	private TextView tv_teenum;// 打球个数
	@ViewInject(R.id.tv_teeflo)
	private TextView tv_teeflo;// 打球个数
	@ViewInject(R.id.tv_type)
	private TextView tv_type;// 场地类型
	@ViewInject(R.id.tv_teegre)
	private TextView tv_teegre;// 果岭打
	@ViewInject(R.id.tv_vip)
	private TextView tv_vip;// vip房间
	@ViewInject(R.id.tv_teebun)
	private TextView tv_teebun;// 沙坑打位
	@ViewInject(R.id.tv_putgre)
	private TextView tv_putgre;// 果岭推杆
	@ViewInject(R.id.tv_supporting)
	private TextView tv_supporting;// 相关配置
	@ViewInject(R.id.tv_summy)
	private TextView tv_summy;// 球场简介

	@ViewInject(R.id.common_title)
	private TextView common_title;
	@ViewInject(R.id.common_back)
	private Button common_back;
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_practag_detail);
		ViewUtils.inject(this);
		initData();
	}

	@Override
	public void initData() {
		 dialog = DialogUtil.getDialog(this);
		 dialog.show();
		common_back.setVisibility(View.VISIBLE);
		common_title.setText(ConstantValues.PRACTICE_TAG);
		rgid = getIntent().getStringExtra("rgid");
		getData();
		common_back.setOnClickListener(this);
	}

	@Override
	public void getData() {
		ServiceFactory.getPracEngineInstatice().getPracticeRgdetail(rgid, new IOAuthCallBack() {

			@Override
			public void getIOAuthCallBack(String result) {
				if (result.equals(ConstantValues.FAILURE)) {

					ToastUtils.showInfo(PracTagDetailActivity.this,ConstantValues.NONETWORK);
				} else {
					PracTagDetailEntity bean = GsonTools.changeGsonToBean(result, PracTagDetailEntity.class);
					if (bean.getData() != null) {
						PracTagDetailEntity data = bean.getData().get(0);
						tv_teenum.setText(data.getTeenum()+"个");
						tv_teeflo.setText(data.getTeeflo()+"层");
						tv_supporting.setText(data.getSupporting());
						tv_summy.setText(data.getSummy());
						//tv_type.setText(data.getType());
						//tv_teegre.setText(data.getTeegre());
						//tv_vip.setText(data.getVip());
						//tv_teebun.setText(data.getTeebun());
						//tv_putgre.setText(data.getPutgre());
					}
					dialog.cancel();
				}

			}
		});

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
