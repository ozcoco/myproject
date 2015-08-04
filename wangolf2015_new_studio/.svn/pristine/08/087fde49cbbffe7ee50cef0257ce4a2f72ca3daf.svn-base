package me.wangolf.college;

import java.util.ArrayList;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.bean.college.CollegeInfoEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.Xutils;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CollegeinfoAcitvity extends Activity implements OnClickListener {
	@ViewInject(R.id.common_back)
	private Button common_back;
	@ViewInject(R.id.common_title)
	private TextView common_title;
	@ViewInject(R.id.ivpic)
	private ImageView ivpic;// 背景图片
	@ViewInject(R.id.tvname)
	private TextView tvname; // 学院名称
	@ViewInject(R.id.tvaddress)
	private TextView tvaddress; // 学院地址
	@ViewInject(R.id.tvcoachnum)
	private TextView tvcoachnum; // 学院教练
	@ViewInject(R.id.tvintroduce)
	private TextView tvintroduce;// 学院简介
	private String collegeid; // 学院ID
	@ViewInject(R.id.imageView1)
	private ImageView imageView1;
	@ViewInject(R.id.imageView2)
	private ImageView imageView2;
	@ViewInject(R.id.imageView3)
	private ImageView imageView3;
	@ViewInject(R.id.go_coach)
	private LinearLayout go_coach; // 去教练列表
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_college_info);
		ViewUtils.inject(this);
		initData();
	}

	public void initData() {
		dialog = DialogUtil.getDialog(this);
		dialog.show();
		common_title.setText(ConstantValues.COLLEGE_INFO_TITLE); // 设置标题
		common_back.setVisibility(0); // 后退键可见
		common_back.setOnClickListener(this);
		go_coach.setOnClickListener(this);
		collegeid = getIntent().getStringExtra("collegeid");
		getData(collegeid);

	}

	public void getData(String collegeid) {
		try {
			ServiceFactory.getCollegeEngineInstatice().getCollgetInfo(collegeid, new IOAuthCallBack() {

				private String path_2;
				private String path_3;

				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {

					} else {
						CollegeInfoEntity bean = GsonTools.changeGsonToBean(result, CollegeInfoEntity.class);
						CollegeInfoEntity data = bean.getData().get(0);
						String path = data.getPicture();
						if (!CheckUtils.checkEmpty(path)) {
							path = path.substring(0, path.lastIndexOf(".")) + "_640_395" + path.substring(path.lastIndexOf("."));
						}
						Xutils.getBitmap(CollegeinfoAcitvity.this, ivpic, path);
						// getBitmap(ivpic, data.getPicture());// 待处理
						tvname.setText(data.getName());
						tvaddress.setText(data.getAddress());
						tvcoachnum.setText(data.getCoachnumber() + "名");
						tvintroduce.setText(data.getIntroduce());
						ArrayList<CollegeInfoEntity> coach = data.getCoach();
						if (coach.size() > 0) {
							String path_1 = coach.get(0).getCoachphoto();
							if (!CheckUtils.checkEmpty(path_1)) {
								path_1 = path_1.substring(0, path_1.lastIndexOf(".")) + "_180_180" + path_1.substring(path_1.lastIndexOf("."));
							}
							Xutils.getBitmap(CollegeinfoAcitvity.this, imageView1, path_1);
							if (coach.size() > 1) {
								path_2 = coach.get(1).getCoachphoto();
								if (!CheckUtils.checkEmpty(path_2)) {
									path_2 = path_2.substring(0, path_2.lastIndexOf(".")) + "_180_180" + path_2.substring(path_2.lastIndexOf("."));
								}
								Xutils.getBitmap(CollegeinfoAcitvity.this, imageView2, path_2);
							}

							if (coach.size() > 2) {
								path_3 = coach.get(2).getCoachphoto();
								if (!CheckUtils.checkEmpty(path_3)) {
									path_3 = path_3.substring(0, path_3.lastIndexOf(".")) + "_180_180" + path_3.substring(path_3.lastIndexOf("."));
								}
								Xutils.getBitmap(CollegeinfoAcitvity.this, imageView3, path_3);
							}
						}
					}
					dialog.cancel();
				}
			});
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.common_back:
			finish(); // 退出
			break;
		case R.id.go_coach:
			Intent intent = new Intent(getApplicationContext(), CollegeCoachList.class);
			intent.putExtra("collegeid", collegeid);
			this.startActivity(intent);
			break;
		default:
			break;
		}
	}

	public void getBitmap(ImageView image, String url) {

		BitmapUtils bitmapUtils = new BitmapUtils(this);
		// 加载网络图片
		bitmapUtils.display(image, url);
	}
}
