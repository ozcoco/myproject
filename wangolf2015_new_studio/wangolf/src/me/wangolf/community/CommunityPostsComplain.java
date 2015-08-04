package me.wangolf.community;

import me.wangolf.ConstantValues;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.InfoEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ToastUtils;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CommunityPostsComplain extends BaseActivity implements OnClickListener {

	private String user_id;
	private String posts_id;
	private String reson;
	@ViewInject(R.id.complain_content)
	private EditText mComplain_content;
	@ViewInject(R.id.send)
	private TextView mSend;
	@ViewInject(R.id.cancel)
	private TextView mCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_complain);
		ViewUtils.inject(this);
		posts_id = getIntent().getStringExtra("posts_id");
		user_id = ConstantValues.UID;
		mSend.setOnClickListener(this);
		mCancel.setOnClickListener(this);

	}

	// 举报
	public void toComplain() {
		try {
			ServiceFactory.getCommunityEngineInstatice().toComplain(user_id, posts_id, reson, new IOAuthCallBack() {

				@Override
				public void getIOAuthCallBack(String result) {


					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), ConstantValues.NONETWORK, 0).show();
					} else {
						InfoEntity bean = GsonTools.changeGsonToBean(result, InfoEntity.class);
						if ("1".equals(bean.getStatus())) {
							ToastUtils.showInfo(CommunityPostsComplain.this, bean.getInfo());
							finish();
						}
					}

				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// finish();
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send:
			reson = mComplain_content.getText().toString().trim();
			toComplain();
			break;
		case R.id.cancel:
			finish();
			break;
		default:
			break;
		}
	}

}
