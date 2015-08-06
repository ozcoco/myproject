package me.wangolf.city;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.bean.CityEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ToastUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;

public class CityActivity extends Activity implements OnChildClickListener, OnClickListener, AssortView.OnTouchAssortListener {

	/** Called when the activity is first created. */
	private PinyinAdapter adapter;
	private ExpandableListView eListView;
	private AssortView assortView;

	private View searchButton;

	protected HashMap<String, CityEntity> namesHash;

	private Dialog dialog;
	private CityEntity parent;
	private LinearLayout layoutHistory;

	private TextView tvHistoryTip;

	@ViewInject(R.id.common_title)
	private TextView common_title;
	@ViewInject(R.id.common_back)
	private Button common_back;
	@ViewInject(R.id.elist)
	private ExpandableListView elist;
	@ViewInject(R.id.auto)
	private AutoCompleteTextView autoCompleteTextView;
    @ViewInject(R.id.assort)
    private AssortView mAview;
	@ViewInject(R.id.bt_ok)
	private Button bt_ok;
	@ViewInject(R.id.tv_sz)
	private TextView tv_sz;
	@ViewInject(R.id.tv_gz)
	private TextView tv_gz;
	@ViewInject(R.id.tv_bj)
	private TextView tv_bj;
	@ViewInject(R.id.tv_sh)
	private TextView tv_sh;
	private String type;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ac_search_city);
		
		ViewUtils.inject(this);
		
		initData();

	}

	private void initData() 
	{
		LinearLayout head_city = (LinearLayout) View.inflate(this, R.layout.head_city, null);
		
		ViewUtils.inject(this, head_city);
		
		elist.addHeaderView(head_city);
		
		dialog = new Dialog(this, R.style.MyDialogTheme);
		
		common_title.setText(ConstantValues.SELECT_CITY);
		
		common_back.setVisibility(View.VISIBLE);
		
		getData();
		
		type = getIntent().getStringExtra("type");
		
		common_back.setOnClickListener(this);
		
		elist.setOnChildClickListener(this);
		
		bt_ok.setOnClickListener(this);
		
		tv_sz.setOnClickListener(this);
		
		tv_gz.setOnClickListener(this);
		
		tv_bj.setOnClickListener(this);
		
		tv_sh.setOnClickListener(this);
		
        mAview.setOnTouchAssortListener(this);
        
	}

	public void getData() {
		ServiceFactory.getPracEngineInstatice().getBallCityList(new IOAuthCallBack() {

			@Override
			public void getIOAuthCallBack(String result) {
				if (result.equals(ConstantValues.FAILURE)) {

                    ToastUtils.showInfo(CityActivity.this,ConstantValues.NONETWORK);
				} else {
					CityEntity bean = GsonTools.changeGsonToBean(result, CityEntity.class);
					ArrayList<CityEntity> data = bean.getData();
					List<String> names = new ArrayList<String>();
					for (int i = 0; i < data.get(0).getName().length(); i++) {
						names = Arrays.asList(data.get(0).getName().split(","));

					}
					
					adapter = new PinyinAdapter(getApplicationContext(), names);
					// adapter.setStrList(names);
					elist.setAdapter(adapter);
					
					for (int i = 0, length = adapter.getGroupCount(); i < length; i++)
					{
						elist.expandGroup(i);
					}
					
					adapter.notifyDataSetChanged();

				}

			}
		});
	}

	/**
	 * 保存历史城市到偏好
	 * 
	 * @param cityentity
	 */
	@SuppressLint("ResourceAsColor")
	public void saveHistoryCity(CityEntity cityentity) {
		SharedPreferences sp = getSharedPreferences("cityhisory", Context.MODE_PRIVATE);
		ArrayList<CityEntity> oldlist = null;// 以前的城市集合
		ArrayList<CityEntity> newlist = new ArrayList<CityEntity>();// 现在的城市集合
		oldlist = getCityHistory();
		if (oldlist != null && oldlist.size() == 3) {
			oldlist.remove(2);//
		}
		newlist.add(cityentity);
		if (oldlist != null && oldlist.size() > 0) {
			/*
			 * if(oldlist.contains(cityentity)){ newlist.add(cityentity); }
			 */
			for (int i = 0; i < oldlist.size(); i++) {
				if (!newlist.contains(oldlist.get(i))) {
					newlist.add(oldlist.get(i));
				}
			}
		}

		// 保存新选的城市
		Editor ed = sp.edit();
		for (int i = 0; i < newlist.size(); i++) {
			CityEntity c = newlist.get(i);
			StringBuilder sb = new StringBuilder();
			sb.append(c.getId());
			sb.append(",");
			sb.append(c.getName());
			sb.append(",");
			sb.append(c.getPid());
			sb.append(",");
			sb.append(c.getRegtion_level());
			String sbStr = sb.toString();
			ed.putString(i + "", sbStr);
		}
		ed.commit();

		// 得到最近的历史城市更新UI
		getHishisoryShow();

	}

	@SuppressLint("ResourceAsColor")
	public void getHishisoryShow() {
		ArrayList<CityEntity> showlist = getCityHistory();
		for (int i = 0; i < showlist.size(); i++) {

			final CityEntity en = showlist.get(i);
			TextView tv = new TextView(CityActivity.this);
			tv.setId(3000 + i);
			tv.setText(en.getName());
			tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 100));
			tv.setTextColor(Color.BLACK);
			tv.setClickable(true);
			tv.setGravity(Gravity.CENTER_VERTICAL);
			tv.setPadding(100, 0, 0, 0);
			tv.setTextAppearance(CityActivity.this, android.R.style.TextAppearance_Medium);
			tv.setBackgroundColor(getResources().getColor(R.color.white));
			tv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
				}
			});

			View v = new View(CityActivity.this);
			v.setId(4000 + i);
			v.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1));
			layoutHistory.addView(tv);
			layoutHistory.addView(v);
			tvHistoryTip.setVisibility(View.GONE);
		}
	}

	/**
	 * 得到偏好设置的历史城市
	 */
	public ArrayList<CityEntity> getCityHistory() {
		SharedPreferences sp = getSharedPreferences("cityhisory", Context.MODE_PRIVATE);
		ArrayList<CityEntity> oldlist = new ArrayList<CityEntity>();
		// 得到三个城市的字符串
		for (int i = 0; i < 3; i++) {
		}

		return oldlist;
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2, int arg3, long arg4)
	{
		String ChildText = (String) adapter.getChild(arg2, arg3);
		
		autoCompleteTextView.setText(ChildText);
		// getIDFromDatabase(ChildText) ;
		return false;
	}

	/**
	 * 通过城市名称查找ID
	 * 
	 * @param cityname
	 *            ] 城市名称
	 */
	public void getIDFromDatabase(String cityname) 
	{
		DbUtils db = DbUtils.create(this, "mgolf.db3");
		
		db.configAllowTransaction(true);
		
		db.configDebug(true);

		try 
		{
			parent = db.findFirst(Selector.from(CityEntity.class).where("name", "=", cityname));
		} 
		catch (DbException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_ok:
			cityResult();
			break;
		case R.id.common_back:
			finish();
			break;
		case R.id.tv_sz:
			setCity("深圳", 77);
			break;
		case R.id.tv_gz:
			setCity("广州", 76);
			break;
		case R.id.tv_sh:
			setCity("上海", 25);
			break;
		case R.id.tv_bj:
			setCity("北京", 2);
			break;
		default:
			break;
		}
	}

	public void cityResult() 
	{
		if ("parcity".equals(type))
		{
			getIDFromDatabase(autoCompleteTextView.getText().toString());
			
			if (parent != null) 
			{
				Intent in = new Intent(this, CityActivity.class);
				
				in.putExtra("city", parent);
				
				setResult(ConstantValues.CITY_CODE, in);
				
				finish();
				
			} 
			else 
			{

                ToastUtils.showInfo(CityActivity.this,"没有该城市相关信息！");
			}
			
		} 
		else if ("ballcity".equals(type)) 
		{
			getIDFromDatabase(autoCompleteTextView.getText().toString());
			
			if (parent != null) 
			{
				Intent in = new Intent(this, CityActivity.class);
				
				in.putExtra("city", parent);
				setResult(ConstantValues.CITY_CODE, in);
				ConstantValues.cityname = parent.getName();
				ConstantValues.cityid = parent.getId();
				ConstantValues.ballTags = true;
				ConstantValues.ballcitytag = true;
				finish();
			} else {
                ToastUtils.showInfo(CityActivity.this,"没有该城市相关信息！");
			}
		}
	}

	public void setCity(String cityname, int cityid) {
		if ("parcity".equals(type)) {
			parent = new CityEntity();
			parent.setName(cityname);
			parent.setId(cityid);
			Intent in = new Intent(this, CityActivity.class);
			in.putExtra("city", parent);
			setResult(ConstantValues.CITY_CODE, in);
			finish();
		} else if ("ballcity".equals(type)) {
            parent =new CityEntity();
            parent.setId(cityid);
            parent.setName(cityname);
            Intent in = new Intent(this, CityActivity.class);
            in.putExtra("city", parent);
            setResult(ConstantValues.CITY_CODE, in);
			finish();
		}
	}

    @Override
    public void onTouchAssortListener(String s) {
        int index=adapter.getAssort().getHashList().indexOfKey(s);
        if(index!=-1)
        {
            elist.setSelectedGroup(index);;
        }

    }

    @Override
    public void onTouchAssortUP() {

    }
}
