package me.wangolf.community;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import java.io.File;
import java.util.ArrayList;

import me.wangolf.base.BaseActivity;
import me.wangolf.utils.CommonDefine;
import me.wangolf.utils.FileUtils;
import me.wangolf.utils.ImageUtils;

public class CommunityPostsShare extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.btn_complain)
private Button mComplain;
	@ViewInject(R.id.btn_cancel)
	private Button mCancle;
	@ViewInject(R.id.bt_share)
	private Button mShare;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_dialog);
		ViewUtils.inject(this);
		initData();
	}


	@Override
	public void initData() {
		mComplain.setOnClickListener(this);
		mCancle.setOnClickListener(this);
		mShare.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btn_complain:
				Intent complain =new Intent();
				setResult(1008,complain);
				finish();
				break;
			case R.id.bt_share:
				Intent share =new Intent();
				setResult(1009,share);
				finish();
				break;
			case R.id.btn_cancel:
				finish();
				break;
		}

	}
	@Override
		 public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
