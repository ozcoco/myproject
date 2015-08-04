package me.wangolf.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.ballprac.BallPracSearchActivity;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 时间dialog
 * 
 * @author Administrator
 * 
 */
public class TimeSingleActivity extends Activity implements OnWheelChangedListener {

	/**
	 * 省的WheelView控件
	 */
	private WheelView mProvince;

	/**
	 * 所有省
	 */
	private String[] mProvinceDatas = { "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30",
			"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00",
			"17:30", "18:00", "18:30", "19:00", "19:30", "20:00" };;
	/**
	 * key - 省 value - 市s
	 */
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();

	/**
	 * 当前省的名称
	 */
	private String mCurrentProviceName;
	private TextView btnCancle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_single);
		initData();
		iniDialog();

		mProvince = (WheelView) findViewById(R.id.id_province);
		mProvince.setWheelForeground(R.drawable.select_bg);// TODO 设置中间的图片

		btnCancle = (TextView) findViewById(R.id.bt_cancle);
		btnCancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		mProvince.setViewAdapter(new ArrayWheelAdapter<String>(this, mProvinceDatas));
		// 添加change事件
		mProvince.addChangingListener(this);

		mProvince.setVisibleItems(5);

	}

	// 根据来源调整数据
	private void initData() {
		Intent intent = getIntent();
		boolean today = intent.getBooleanExtra("today", false);
		String time = intent.getStringExtra("time");
		if (today) {
			int startindex = 0;
			for (int i = 0; i < mProvinceDatas.length; i++) {
				String s = mProvinceDatas[i];
				if (s.equals(time)) {
					startindex = i;
				}
			}

			List<String> list = new ArrayList<String>();
			for (int i = startindex; i < mProvinceDatas.length; i++) {
				list.add(mProvinceDatas[i]);
			}
			mProvinceDatas = (String[]) list.toArray(new String[list.size()]);
		}

	}

	public void showChoose(View view) {
		if (mCurrentProviceName == null) {
			mCurrentProviceName = mProvinceDatas[0];
		}
		String reprovice = mCurrentProviceName;

		Intent timeIntent = new Intent(TimeSingleActivity.this, BallPracSearchActivity.class);
		timeIntent.putExtra("time", reprovice);
		TimeSingleActivity.this.setResult(ConstantValues.timeCode, timeIntent);
		ConstantValues.balltime = reprovice;
		ConstantValues.ballTags = true;
		finish();
	}

	/**
	 * set dialog width and height
	 */
	private void iniDialog() {
		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay();
		android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
		// p.height=(int) (d.getHeight() * 0.5); //高度设置为屏幕的1.0
		p.width = (int) (d.getWidth() * 1.0) + 1; // 宽度设置为屏幕
		getWindow().setAttributes(p); // 设置生效
		getWindow().setGravity(Gravity.BOTTOM);
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mProvince) {
			updateCities();
		}
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
	}

	@Override
	protected void onResume() {
		super.onResume();

	}
}
