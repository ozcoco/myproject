package me.wangolf.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.meigao.mgolf.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

/**
 * 日期选择对话框
 * 
 * @author Administrator
 * 
 */
public class MyDateDialog extends Activity {
	private int year;
	private int month;
	private int day;
	private NumberPicker pikeryear;
	private NumberPicker pikermon;
	protected int currentDate;
	private NumberPicker pikerday;
	protected int currentYear;
	protected int currentDay;
	private TextView btok;
	private TextView btnCancle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_date_dialog);
		iniDialog();

		setViews();
		setListeners();
	}

	private void setViews() {
		btok = (TextView) findViewById(R.id.btok);
		btnCancle = (TextView) findViewById(R.id.btnCancle);

		// 从日历中取得的时间
		Calendar c = Calendar.getInstance(Locale.CHINA);
		Date mydate = new Date();
		c.setTime(mydate);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		pikeryear = (NumberPicker) findViewById(R.id.pikeryear);
		pikeryear.setMaxValue(year);
		pikeryear.setValue(year);
		pikeryear.setMinValue(1900);
		pikeryear.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
				// ToastUtils.showInfo(MyDateDialog.this,
				// "arg1="+arg1+";arg2="+arg2);
				if (arg2 > year) {
					pikeryear.setValue(year);
					currentYear = year;
				} else if (arg2 < 1900) {
					pikeryear.setValue(1900);
					currentYear = 1900;
				}

			}
		});
		pikermon = (NumberPicker) findViewById(R.id.pikermon);
		pikermon.setMaxValue(12);
		pikermon.setValue(month + 1);
		pikermon.setMinValue(1);
		pikerday = (NumberPicker) findViewById(R.id.pikerday);

		pikermon.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
				// ToastUtils.showInfo(MyDateDialog.this,
				// "arg1="+arg1+";arg2="+arg2);

				int pyear = pikeryear.getValue();
				int cm = month;

				if (arg2 > month && pyear >= year) {// 年不能超过现在的时间
					pikermon.setValue(month + 1);// 超过将现在的时间赋予它
					currentDate = month + 1;
				} else {
					currentDate = arg2;
				}

				if (currentDate == 1 || currentDate == 3 || currentDate == 5 || currentDate == 7 || currentDate == 8 || currentDate == 10
						|| currentDate == 12) {
					pikerday.setMaxValue(31);
				} else if (currentDate == 2 && currentYear % 4 == 0) {
					pikerday.setMaxValue(29);
				} else if (currentDate == 2 && currentYear % 4 != 0) {
					pikerday.setMaxValue(28);
				} else {
					pikerday.setMaxValue(30);
				}
			}
		});

		pikerday.setMaxValue(day + 1);
		pikerday.setMinValue(1);
		pikerday.setValue(day + 1);
		pikerday.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
				// ToastUtils.showInfo(MyDateDialog.this,
				// "arg1="+arg1+";arg2="+arg2);

				if (arg2 < 1) {
					pikerday.setValue(1);
					pikerday.setMinValue(1);
					currentDay = 1;
				} else if (arg2 > day) {
					pikerday.setValue(day + 1);
					currentDay = day + 1;
				} else {
					currentDay = arg2;
				}
			}
		});

	}

	public void setListeners() {

		btnCancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				Intent data = new Intent(MyDateDialog.this, UpdateMyInfoActivity.class);
//				data.putExtra("date", "");
//				setResult(GlobalConsts.requestDateCode, data);
//				MyDateDialog.this.finish();
			}
		});

		btok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String date = "";
				String y = "";
				String m = "";
				String d = "";
				if (currentYear == 0) {
					currentYear = year;
				}
				if (currentDate == 0) {
					currentDate = month + 1;
				}
				if (currentDay == 0) {
					currentDay = day + 1;
				}

				y = currentYear + "";
				if (currentDate < 10) {
					m = "0" + currentDate;
				} else {
					m = currentDate + "";
				}
				if (currentDay < 10) {
					d = "0" + currentDay;
				} else {
					d = currentDay + "";
				}

				date = y + "-" + m + "-" + d;
				// TODO 获得选择时间
				// ToastUtils.showInfo(MyDateDialog.this, date);
//				Intent data = new Intent(MyDateDialog.this, UpdateMyInfoActivity.class);
//				data.putExtra("date", date);
//				setResult(ConstantValues.timeCode, data);
//				finish();

			}
		});
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// 需要处理
//			Intent data = new Intent(MyDateDialog.this, UpdateMyInfoActivity.class);
//			data.putExtra("date", "");
//			setResult(ConstantValues.timeCode, data);
//			MyDateDialog.this.finish();
//			this.finish();
		}
		return false;
	}

	@Override
	protected void onResume() {
		super.onResume();

	}
}
