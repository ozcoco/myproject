package me.wangolf.calender;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



import com.meigao.mgolf.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


public class CalendarGridViewAdapter extends BaseAdapter {

    private Calendar calStartDate = Calendar.getInstance();// 当前显示的日历
    private Calendar calSelected = Calendar.getInstance(); // 选择的日历

    public void setSelectedDate(Calendar cal) {
        calSelected = cal;
    }

    private Calendar calToday = Calendar.getInstance(); // 今日
    private int iMonthViewCurrentMonth = 0; // 当前视图月

    // 根据改变的日期更新日历
    // 填充日历控件用
    private void UpdateStartDateForMonth() {
        calStartDate.set(Calendar.DATE, 1); // 设置成当月第一天
        iMonthViewCurrentMonth = calStartDate.get(Calendar.MONTH);// 得到当前日历显示的月

        // 星期一是2 星期天是1 填充剩余天数
        int iDay = 0;
        int iFirstDayOfWeek = Calendar.MONDAY;
        int iStartDay = iFirstDayOfWeek;
        if (iStartDay == Calendar.MONDAY) {
            iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
            if (iDay < 0)
                iDay = 6;
        }
        if (iStartDay == Calendar.SUNDAY) {
            iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
            if (iDay < 0)
                iDay = 6;
        }
        calStartDate.add(Calendar.DAY_OF_WEEK, -iDay);

        calStartDate.add(Calendar.DAY_OF_MONTH, -1);// 周日第一位

    }

    ArrayList<java.util.Date> titles;

    private ArrayList<java.util.Date> getDates() {

        UpdateStartDateForMonth();

        ArrayList<java.util.Date> alArrayList = new ArrayList<java.util.Date>();

        for (int i = 1; i <= 42; i++) {
            alArrayList.add(calStartDate.getTime());
            calStartDate.add(Calendar.DAY_OF_MONTH, 1);
        }

        return alArrayList;
    }

    private Activity activity;
    Resources resources;

    // construct
    public CalendarGridViewAdapter(Activity a, Calendar cal) {
        calStartDate = cal;
        activity = a;
        resources = activity.getResources();
        titles = getDates();
    }

    public CalendarGridViewAdapter(Activity a) {
        activity = a;
        resources = activity.getResources();
    }


    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout iv = new LinearLayout(activity);
        iv.setId(position + 5000);
        LinearLayout imageLayout = new LinearLayout(activity);
        imageLayout.setOrientation(0);
        iv.setGravity(Gravity.CENTER);
        iv.setOrientation(1);
       // iv.setBackgroundColor(resources.getColor(R.color.white));

        Date myDate = (Date) getItem(position);
        Calendar calCalendar = Calendar.getInstance();
        calCalendar.setTime(myDate);

        final int iMonth = calCalendar.get(Calendar.MONTH);
        final int iDay = calCalendar.get(Calendar.DAY_OF_WEEK);


        // 判断周六周日
       // iv.setBackgroundColor(resources.getColor(R.color.white));
        if (iDay == 7) {
            // 周六
            //iv.setBackgroundColor(resources.getColor(R.color.text_6));
        } else if (iDay == 1) {
            // 周日
            //iv.setBackgroundColor(resources.getColor(R.color.text_7));
        } else {

        }
        // 判断周六周日结束

        /*TextView txtToDay = new TextView(activity);// 日本老黄历
        txtToDay.setGravity(Gravity.CENTER_HORIZONTAL);
        txtToDay.setTextSize(9);
        CalendarUtil calendarUtil = new CalendarUtil(calCalendar);
        if (equalsDate(calToday.getTime(), myDate)) {
            // 当前日期
            iv.setBackgroundColor(resources.getColor(R.color.event_center));
           // txtToDay.setText(calendarUtil.toString());
        } else {
            txtToDay.setText(calendarUtil.toString());
        }
         */
        
        // 设置背景颜色
        if (equalsDate(calSelected.getTime(), myDate)) {
            // 选择的
            iv.setBackgroundColor(resources.getColor(R.color.selection));
            
        } else {
            if (equalsDate(calToday.getTime(), myDate)) {
                // 当前日期
                iv.setBackgroundColor(resources.getColor(R.color.calendar_zhe_day));
            }
        }
        // 设置背景颜色结束

        // 日期开始
        TextView txtDay = new TextView(activity);// 日期
        txtDay.setTextSize(16);
        txtDay.setGravity(Gravity.CENTER_HORIZONTAL);
        txtDay.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        txtDay.setBackgroundColor(R.color.calendar_background);
        txtDay.setTextColor(R.color.gray);

        // 判断是否是当前月
        if (iMonth == iMonthViewCurrentMonth) {
            //txtToDay.setTextColor(resources.getColor(R.color.ToDayText));
            txtDay.setTextColor(resources.getColor(R.color.Text));
            	
        } /*else {
           // txtToDay.setTextColor(resources.getColor(R.color.noMonth));
            
            txtDay.setTextColor(resources.getColor(R.color.noMonth));
        }*/

        int day = myDate.getDate(); // 日期
        txtDay.setText(String.valueOf(day));
        txtDay.setId(position + 500);
        iv.setTag(myDate);
       /* 
        if(myDate.getTime()>=System.currentTimeMillis()){
        	iv.setBackgroundColor(resources.getColor(R.color.calendar_green));
        }else{
        	 txtDay.setTextColor(resources.getColor(R.color.noMonth));
        }
       */
        
        
        // TODO 得到本月最大日期
        Calendar c=Calendar.getInstance();
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE)); 
        int maxDate=c.getActualMaximum(Calendar.DATE);
        long maxtime=c.getTimeInMillis();
        int mySelecDate=calCalendar.get(Calendar.DATE);
        
        //得到当前date
        int curentDate=c.get(Calendar.DATE);
        //显示的时间大于现在时间并且小于于等本月
        if(myDate.getTime()>=System.currentTimeMillis()&&myDate.getTime()<maxtime&&mySelecDate<=maxDate){
        	txtDay.setTextColor(Color.BLACK);
        }else{
        	txtDay.setTextColor(resources.getColor(R.color.gray));
        }
        
        /*//显示的时间大于现在时间并且小于于等于一个星期
        if(myDate.getTime()>=System.currentTimeMillis()&&myDate.getTime()-(System.currentTimeMillis())<=(82598400*1000)){
        	//iv.setBackgroundColor(resources.getColor(R.color.calendar_green));
        	txtDay.setTextColor(Color.BLACK);
        }else{
        	 txtDay.setTextColor(resources.getColor(R.color.noMonth));
        }*/
       

        // TODO 设置日期的tv宽高
       /* LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        iv.addView(txtDay, lp);*/
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
        		LayoutParams.FILL_PARENT,60);
        iv.addView(txtDay, lp);

       /* LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        iv.addView(txtToDay, lp1);*/
        return iv;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    private Boolean equalsDate(Date date1, Date date2) {

        if (date1.getYear() == date2.getYear()
                && date1.getMonth() == date2.getMonth()
                && date1.getDate() == date2.getDate()) {
            return true;
        } else {
            return false;
        }

    }

}
