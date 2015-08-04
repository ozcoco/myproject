package me.wangolf.event;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.GlobalConsts;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.InfoEntity;
import me.wangolf.bean.event.SendEventBean;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.time.YeMoDaDialog;
import me.wangolf.utils.BitmapUtils;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.HttpUtils;
import me.wangolf.utils.ShowPickUtils;
import me.wangolf.utils.ToastUtils;

public class EventSendActivity_1 extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.common_back)
	private Button common_back; // 后退
	@ViewInject(R.id.common_title)
	private TextView common_title;// 标题
	@ViewInject(R.id.common_bt)
	private TextView common_bt;// 地图
	@ViewInject(R.id.ed_eventname)
	private EditText ed_eventname;// 活动名称
	@ViewInject(R.id.relaytoshowDate)
	private RelativeLayout relaytoshowDate;// 活动开始时间选择
	@ViewInject(R.id.tv_btime)
	private TextView tv_btime;// 活动开始时间
	@ViewInject(R.id.tv_endtime)
	private TextView tv_endtime;// 活动结束时间
	@ViewInject(R.id.ed_address)
	private EditText ed_address;// 活动地点
	@ViewInject(R.id.ed_price)
	private EditText ed_price;// 活动费用
	@ViewInject(R.id.ed_num)
	private EditText ed_num;// 活动人数
	@ViewInject(R.id.ed_introduce)
	private EditText ed_introduce;// 活动介绍
	@ViewInject(R.id.myphoto)
	private ImageButton myphoto;// 活动封面
	private String avatar_file;
	protected String logo = "";// 图片的网络地址
	private int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_event_send1);
		ViewUtils.inject(this);
		initData();
	}

	@Override
	public void initData() {
		common_back.setVisibility(0);
		common_bt.setVisibility(0);
		common_title.setText("发起活动(1/2)");
		common_bt.setText("下一步");
		common_back.setOnClickListener(this);
		common_bt.setOnClickListener(this);
		myphoto.setOnClickListener(this);
		tv_btime.setOnClickListener(this);
		tv_endtime.setOnClickListener(this);

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
		case R.id.myphoto:
			ShowPickUtils.ShowPickDialog(EventSendActivity_1.this);
			break;
		case R.id.tv_btime:
			Intent bt_intent = new Intent(EventSendActivity_1.this, YeMoDaDialog.class);
			index = 0;
			bt_intent.putExtra("index", index);
			startActivityForResult(bt_intent, GlobalConsts.eventYMDCode);
			break;
		case R.id.tv_endtime:
			index = 1;
			Intent end_intent = new Intent(EventSendActivity_1.this, YeMoDaDialog.class);
			end_intent.putExtra("index", index);
			startActivityForResult(end_intent, GlobalConsts.eventYMDCode);
			break;
		case R.id.common_bt:
			sendnext();
			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		/*************** 照片相关操作start ********************/
		if (requestCode != -1 && requestCode != GlobalConsts.eventYMDCode) {

			switch (requestCode) {
			// 如果是直接从相册获取
			case 1:
				if (data != null) {
					ShowPickUtils.startPhotoZoom(EventSendActivity_1.this, data.getData());
				}
				break;
			// 如果是调用相机拍照时
			case 2:
				File temp = new File(Environment.getExternalStorageDirectory() + "/avatar_file.jpg");
				ShowPickUtils.startPhotoZoom(EventSendActivity_1.this, Uri.fromFile(temp));

				break;
			// 取得裁剪后的图片
			case 3:
				if (data != null) {
					setPicToView(data);
				}
				break;
			}
			/*************** 照片相关操作end ********************/
		} else {
			if (data == null) {
				return;
			}
			int type = data.getIntExtra("type", 0);
			if (type == 1) {
				int index = data.getIntExtra("index", 0);
				String year = data.getStringExtra("year");
				String mon = data.getStringExtra("mon");
				String day = data.getStringExtra("day");
				String hour = data.getStringExtra("hour");
				String min = data.getStringExtra("min");

				StringBuffer sb = new StringBuffer(year);
				sb.append("-").append(mon).append("-").append(day);
				sb.append(" ").append(hour).append(":").append(min);
				switch (index) {
				case 0:
					// 设置开始时间
					tv_btime.setText(sb);
					break;
				case 1:
					// 设置结束时间
					tv_endtime.setText(sb);
					break;

				}
			}
		}
	}

	public void setPicToView(Intent picdata) {
		Bundle extras = picdata.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			photo = BitmapUtils.compressImage(photo);
			Drawable drawable = new BitmapDrawable(photo);
			myphoto.setImageBitmap(null);
			myphoto.setBackgroundDrawable(drawable);
			// TODO 保存到SDcard
			HttpUtils.SavePicInLocal(photo);
			File img = new File(Environment.getExternalStorageDirectory(), "avatar_file.jpg");
			final String imagepath = img.getPath(); // 图片路径
			avatar_file = imagepath;

		}
		try {
			ServiceFactory.getEventEngineInstatice().upEventLogo(avatar_file, new IOAuthCallBack() {

				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), ConstantValues.NONETWORK, 0).show();
					} else {
						InfoEntity bean = GsonTools.changeGsonToBean(result, InfoEntity.class);
						if ("1".equals(bean.getStatus())) {
							ToastUtils.showInfo(EventSendActivity_1.this, bean.getInfo());
						} else {
							ToastUtils.showInfo(EventSendActivity_1.this, bean.getInfo());
						}
					}

				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 下一步
	 * 
	 * @param v
	 */
	public void sendnext() {
		// 获得数据并验证
		String name = ed_eventname.getText().toString().trim();
		String btime = tv_btime.getText().toString().trim();
		String etime = tv_endtime.getText().toString().trim();
		String address = ed_address.getText().toString().trim();
		String num = ed_num.getText().toString().trim();
		String introduce = ed_introduce.getText().toString().trim();
		String price = ed_price.getText().toString().trim();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date sdate = df.parse(btime);
			Date edate = df.parse(etime);
			Date now = new Date();
			if (sdate.getTime() >= edate.getTime()) {
				ToastUtils.showInfo(EventSendActivity_1.this, "请填写正确的开始结束时间");
				return;
			} else if (sdate.getTime() <= now.getTime()) {
				ToastUtils.showInfo(EventSendActivity_1.this, "开始时间已过期");
				return;
			} else if (edate.getTime() <= now.getTime()) {
				ToastUtils.showInfo(EventSendActivity_1.this, "结束时间已过期");
				return;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (CheckUtils.checkEmpty(name)) {
			ToastUtils.showInfo(EventSendActivity_1.this, "请填写活动名称");
			return;
		} else if (CheckUtils.checkEmpty(btime)) {
			ToastUtils.showInfo(EventSendActivity_1.this, "请选择开始时间");
			return;
		} else if (CheckUtils.checkEmpty(etime)) {
			ToastUtils.showInfo(EventSendActivity_1.this, "请选择结束时间");
			return;
		} else if (CheckUtils.checkEmpty(address)) {
			ToastUtils.showInfo(EventSendActivity_1.this, "请填写活动地点");
			return;
		} else if (CheckUtils.checkEmpty(price)) {
			ToastUtils.showInfo(EventSendActivity_1.this, "请填写活动费用");
			return;
		} else if (CheckUtils.checkEmpty(num)) {
			ToastUtils.showInfo(EventSendActivity_1.this, "请填写参加人数");
			return;
		} else if (CheckUtils.checkEmpty(introduce)) {
			ToastUtils.showInfo(EventSendActivity_1.this, "请填写活动简介");
			return;
		} else if (introduce.length() < 10) {
			ToastUtils.showInfo(EventSendActivity_1.this, "活动介绍不能少于10字");
			return;
		}
		// 验证通过跳转到下一步
		Intent intent = new Intent(EventSendActivity_1.this, EventSendActivity_2.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		SendEventBean bean = new SendEventBean();
		bean.setEventname(name);
		bean.setBtime(btime);
		bean.setEtime(etime);
		bean.setAddress(address);
		bean.setIntroduce(introduce);
		bean.setPrice(price);
		bean.setLogo(logo);
		bean.setUid(ConstantValues.UID);
		intent.putExtra("event_bean", bean);
		startActivity(intent);
	}

}
