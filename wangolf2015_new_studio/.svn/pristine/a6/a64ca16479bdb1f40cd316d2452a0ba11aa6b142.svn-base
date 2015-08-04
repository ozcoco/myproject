package me.wangolf.community;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.CommunitySendTagAdapter;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.InfoEntity;
import me.wangolf.bean.community.CommunityTagEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ToastUtils;

public class CommunitySendActivity extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.common_back)
	private TextView common_back;
	@ViewInject(R.id.common_title)
	private TextView common_title;
	@ViewInject(R.id.common_bt)
	private TextView common_bt;
	@ViewInject(R.id.c_s_title)
	private EditText c_s_title;// 输入标题
	@ViewInject(R.id.c_s_content)
	private EditText c_s_content;// 输入的内容
	@ViewInject(R.id.c_d_ed)
	private TextView c_d_ed;// 显示地址
	@ViewInject(R.id.lsit_tag)
	private ListView mTag;//标签
	private String user_id; // 用户ID
	private String content;// 帖内容
	private String title;// 帖标题
	private String address;// 发帖地址
	private String img_list;// 图片地址列表
	private CommunitySendTagAdapter tagAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_community_send);
		ViewUtils.inject(this);
		if(tagAdapter==null){
			tagAdapter=new CommunitySendTagAdapter(this);
		}else{
			tagAdapter.notifyDataSetChanged();
		}
		mTag.setAdapter(tagAdapter);
		initData();
	}

	@Override
	public void initData() {
		common_title.setText("发帖");
		common_bt.setText("发布");
		common_back.setVisibility(View.VISIBLE);
		common_bt.setVisibility(View.VISIBLE);
		common_back.setOnClickListener(this);
		getTagsList();
	}

	@Override
	public void getData() {

	}
	//获取社区标签
	private void getTagsList(){
		try {
			ServiceFactory.getCommunityEngineInstatice().getTagsList(new IOAuthCallBack() {
				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {
						ToastUtils.showInfo(CommunitySendActivity.this, ConstantValues.NONETWORK);
					} else {
						CommunityTagEntity bean = GsonTools.changeGsonToBean(result, CommunityTagEntity.class);
						if (bean.getStatus()==1) {
							ArrayList<CommunityTagEntity> data = bean.getData();
							tagAdapter.setTag_list(data);
							tagAdapter.notifyDataSetChanged();

						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// *发帖
	public void sendData() {
		try {
			ServiceFactory.getCommunityEngineInstatice().sendPosts(user_id, content, title, address,"","","","", img_list, new IOAuthCallBack() {

				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), ConstantValues.NONETWORK, 0).show();
					} else {
						InfoEntity bean = GsonTools.changeGsonToBean(result, InfoEntity.class);
						if ("1".equals(bean.getStatus())) {
							ToastUtils.showInfo(CommunitySendActivity.this, bean.getInfo());
						} else {
							ToastUtils.showInfo(CommunitySendActivity.this, bean.getInfo());
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

		default:
			break;
		}

	}

}
