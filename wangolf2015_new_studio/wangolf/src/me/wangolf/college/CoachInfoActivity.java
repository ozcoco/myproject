package me.wangolf.college;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月5日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月5日
 * 
 * 描述 ： 学院---教练---详细信息
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.bean.college.CoachInfoEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ImageViewUtil;
import me.wangolf.utils.TelUtils;
import me.wangolf.utils.Xutils;

public class CoachInfoActivity extends Activity implements OnClickListener {
	@ViewInject(R.id.common_title)
	private TextView title;
	@ViewInject(R.id.common_back)
	private Button common_back;
	// @ViewInject(R.id.ivpic)
	// private ImageView ivpic; // 背景图
	@ViewInject(R.id.ivphoto)
	private de.hdodenhof.circleimageview.CircleImageView ivphoto; // 头像
	@ViewInject(R.id.tvname)
	private TextView tvname;// 姓名
	@ViewInject(R.id.tvage)
	private TextView tvage; // 性别
	@ViewInject(R.id.iv_zan)
	private ImageView iv_zan; // 点赞图标
	@ViewInject(R.id.tvpraise)
	private TextView tvpraise;// 赞的次数
	@ViewInject(R.id.tvprice)
	private TextView tvprice; // 收费标准
	@ViewInject(R.id.tvaddress)
	private TextView tvaddress;// 教练等级
	@ViewInject(R.id.tvbelong)
	private TextView tvbelong;// 所属学院
	@ViewInject(R.id.tvballage)
	private TextView tvballage;// 教龄
	@ViewInject(R.id.tvbestmark)
	private TextView tvbestmark;// 最好成绩
	@ViewInject(R.id.tvcoachfield)
	private TextView tvcoachfield;// 场地
	@ViewInject(R.id.layout_zan)
	private LinearLayout layout_zan; // 点赞
	@ViewInject(R.id.tel_phone)
	private LinearLayout tel_phone;
	@ViewInject(R.id.tvphone)
	private TextView tvphone;// 打电话
	@ViewInject(R.id.go_college)
	private RelativeLayout go_college; // 去学院
	@ViewInject(R.id.bg_image)
	private ImageView bg_image;// 背景图
	@ViewInject(R.id.bg_LL)
	private LinearLayout bg_LL;
	private String coachid; // 教练id intent传过来
	private CoachInfoEntity coachinfo; //
	private String uuid = ConstantValues.UUID;
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_coach_info);
		ViewUtils.inject(this); // 注入view和事件
		initData();

	}

	public void initData() {
		dialog = DialogUtil.getDialog(this);
		dialog.show();
		title.setText(ConstantValues.COACH_TITLE);
		common_back.setVisibility(0);
		common_back.setOnClickListener(this);
		tvphone.setOnClickListener(this);
		go_college.setOnClickListener(this);
		tvpraise.setOnClickListener(this);
		coachid = getIntent().getStringExtra("coachid");
		getData(coachid);
	}

	public void getData(String coachid) {
		try {
			ServiceFactory.getCollegeEngineInstatice().getCoachInfo(coachid, uuid, new IOAuthCallBack() {
				@Override
				public void getIOAuthCallBack(String result) {

					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), "请确保已联网", 0).show();
					} else {
						CoachInfoEntity bean = GsonTools.changeGsonToBean(result, CoachInfoEntity.class);
						coachinfo = bean.getData().get(0);
						// getBitmap(ivpic, coachinfo.getPicture());
						// getBitmap(ivphoto, coachinfo.getPhoto());
						ImageViewUtil.loadimg(coachinfo.getPhoto(), ivphoto, getApplicationContext());
						tvname.setText(coachinfo.getUsername());
						tvage.setText("0".equals(coachinfo.getSex()) ? "女" : "男");
						tvpraise.setText(coachinfo.getPraise());
						tvprice.setText(coachinfo.getPrice());
						tvaddress.setText(coachinfo.getBadges());
						tvbelong.setText(coachinfo.getBelong());
						tvballage.setText(coachinfo.getBallage() + "年");
						tvbestmark.setText(coachinfo.getBestmark() + "杆");
						tvcoachfield.setText(coachinfo.getCoachfield());

						String images[] = coachinfo.getPicture().split(",");
						if (images.length > 0)
							bg_LL.setVisibility(0);
						String path = images[0];
						if (!CheckUtils.checkEmpty(path)) {
							path = path.substring(0, path.lastIndexOf(".")) + "_640_395" + path.substring(path.lastIndexOf("."));
						}
						Xutils.getBitmap(CoachInfoActivity.this, bg_image, path);
						initZan();// 初始化点赞
						dialog.cancel();
					}
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
		case R.id.tvphone:
			if (coachinfo == null) {
				return;
			}
			if (!CheckUtils.checkEmpty(coachinfo.getMobile()))
				TelUtils.tel(this, coachinfo.getMobile() + "");
			// tel();
			break;
		case R.id.go_college:
			goCollege();
			break;
		case R.id.tvpraise:
			UpZan();
			break;
		default:
			break;
		}
	}

	public void initZan() {
		if ("0".equals(coachinfo.getIsPraise())) {
			Drawable drawable = getResources().getDrawable(R.drawable.icon_zan);
			drawable.setBounds(0, 0, 32, 32);
			tvpraise.setClickable(true);
			tvpraise.setCompoundDrawables(drawable, null, null, null);
		} else {
			Drawable drawable = getResources().getDrawable(R.drawable.icon_szan);
			drawable.setBounds(0, 0, 32, 32);
			tvpraise.setClickable(false);
			tvpraise.setCompoundDrawables(drawable, null, null, null);
		}
	}

	public void UpZan() {
		try {
			ServiceFactory.getCollegeEngineInstatice().UpCoachZan(coachid, uuid, new IOAuthCallBack() {
				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), "请确保已联网", 0).show();
					} else {
						JSONObject jsonObject;
						try {
							jsonObject = new JSONObject(result);
							String status = jsonObject.getString("status");
							String info = jsonObject.getString("info");
							if ("1".equals(status)) {
								// 赞成功，+1并且禁用点赞功能
								int count = Integer.parseInt(tvpraise.getText().toString());
								count++;
								tvpraise.setText(count + "");
								tvpraise.setClickable(false);
								Drawable drawable = getResources().getDrawable(R.drawable.icon_szan);
								drawable.setBounds(0, 0, 32, 32);
								tvpraise.setClickable(false);
								tvpraise.setCompoundDrawables(drawable, null, null, null);
								Toast.makeText(getApplicationContext(), info, 0).show();
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goCollege() {
		Intent coegeinfo = new Intent(this, CollegeinfoAcitvity.class);
		if (coachinfo == null) {
			return;
		}
		if (coachinfo.getCollegeid() > 0) {
			coegeinfo.putExtra("collegeid", coachinfo.getCollegeid() + "");
			startActivity(coegeinfo);
		}
	}

	public void tel() {
		final Dialog dialog = new Dialog(CoachInfoActivity.this, R.style.MyDialogTheme);
		if (coachinfo == null) {
			return;
		}
		final String phoneNum = coachinfo.getMobile();
		String title = "拨打教练电话";
		View view = View.inflate(CoachInfoActivity.this, R.layout.dialog_phone, null);
		dialog.setContentView(view);
		TextView tvdTitle = (TextView) view.findViewById(R.id.dialog_title);
		TextView tvdMsgitle = (TextView) view.findViewById(R.id.dialog_msg);
		tvdMsgitle.setText(phoneNum);
		tvdTitle.setText(title);
		Button btcancle = (Button) view.findViewById(R.id.bt_cancle);
		btcancle.setText("取消");
		Button btok = (Button) view.findViewById(R.id.bt_ok);
		btok.setText("拨打");
		btcancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.cancel();
			}
		});
		btok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.cancel();
				toPhone(phoneNum);
			}
		});
		dialog.show();
	}

	/**
	 * 手机拨打电功能功能
	 * 
	 * @param phoneNum
	 */
	protected void toPhone(String phoneNum) {
		Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNum));
		startActivity(phoneIntent);

	}

	public void getBitmap(ImageView image, String url) {

		BitmapUtils bitmapUtils = new BitmapUtils(this);
		// 加载网络图片
		bitmapUtils.display(image, url);
	}
}
