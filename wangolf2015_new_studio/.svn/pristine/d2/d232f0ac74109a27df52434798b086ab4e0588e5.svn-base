package me.wangolf.fragment;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meigao.mgolf.R;

import me.wangolf.base.Mo_BasePage;
import me.wangolf.utils.TelUtils;

public class CustomerPage extends Mo_BasePage implements OnClickListener {
	@ViewInject(R.id.common_title)
	private TextView common_title;
	@ViewInject(R.id.LinearLayout2)
	private LinearLayout tle;

	public CustomerPage(Context context) {
		super(context);

	}

	@Override
	public View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.ac_custome, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData() {
		common_title.setText("客服中心");
		tle.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.LinearLayout2:
			TelUtils.tel(context, "13302311999");
			break;

		default:
			break;
		}

	}

}
