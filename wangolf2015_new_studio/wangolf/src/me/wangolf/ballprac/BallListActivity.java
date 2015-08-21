package me.wangolf.ballprac;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import me.wangolf.ConstantValues;
import me.wangolf.adapter.BallsAdapter;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.ball.BallDetailEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ToastUtils;

public class BallListActivity extends BaseActivity implements OnClickListener, OnItemClickListener
{
	@ViewInject(R.id.common_back)
	
	private Button common_back; // 后退
	
	@ViewInject(R.id.common_title)
	
	private TextView common_title;// 标题
	
	@ViewInject(R.id.common_bt)
	
	private TextView common_bt;// 地图
	
	@ViewInject(R.id.two)
	
	private LinearLayout two;// 价格排序
	
	@ViewInject(R.id.three)
	private LinearLayout three;// 距离排序
	
	@ViewInject(R.id.lv_ball)
	private ListView lv_ball;// 列表
	
	@ViewInject(R.id.tv2)
	private TextView tv2;// 格价高低
	
	@ViewInject(R.id.tv3)
	private TextView tv3;// 格价高低
	
	@ViewInject(R.id.iv2)
	private ImageView iv2;//
	
	@ViewInject(R.id.iv3)
	private ImageView iv3;//
	
    @ViewInject(R.id.relayout)
    private RelativeLayout mRelayout;
  
	private BallsAdapter adapter;
	
	private String cityid; // 城市ID
	
	private String date;// 打球时间(手机端需要将日期与时间组合)
	
	private String ballname;// 球场名称(模糊搜索)
	
	private String longitude;// 经度
	
	private String latitude;// 纬度
	
	private String order = "0";// 排序类型(0价格排序，1距离排序)
	
	private String type = "1";// 排序规则(0降序，1升序)
	

	private String cityname;
	
	private Dialog dialog;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ac_ball_list);
		
		ViewUtils.inject(this);

		if (adapter == null)
		{
			adapter = new BallsAdapter(this);
		} 
		else 
		{
			adapter.notifyDataSetChanged();
		}
		
		lv_ball.setAdapter(adapter);
		
		lv_ball.setOnItemClickListener(this);
		
		initData();
	}

	
	
	@Override
	public void initData() 
	{
		dialog = DialogUtil.getDialog(this);
		 
		dialog.show();
		 
		common_back.setVisibility(View.VISIBLE);
		
	//	common_bt.setVisibility(0);
		
		common_title.setText(ConstantValues.BALLLIST_TITLE);
		
		common_bt.setText(ConstantValues.BALLLIST_BT);
		
		longitude = ConstantValues.LONGITUDE;
		
		latitude = ConstantValues.LATITUDE;
		
		cityid = getIntent().getStringExtra("cityid");
		
		date = getIntent().getStringExtra("date");
		
		ballname = getIntent().getStringExtra("ballname");
		
		cityname = getIntent().getStringExtra("cityname");
		
		common_back.setOnClickListener(this);
		
		two.setOnClickListener(this);
		
		three.setOnClickListener(this);
		
		getData();
		
	}

	
	@Override
	public void getData() 
	{
		try 
		{
//			ServiceFactory.getBallEngineInstatice().findBallSearch(cityid, date, ballname, longitude, latitude, order, type, new IOAuthCallBack()
			ServiceFactory.getBallEngineInstatice().findBallSearch( cityid, date, longitude, latitude, new IOAuthCallBack()
			{

				@Override
				public void getIOAuthCallBack(String result) 
				{
					if (result.equals(ConstantValues.FAILURE))
					{

						ToastUtils.showInfo(BallListActivity.this,ConstantValues.NONETWORK);
					} 
					else 
					{
						BallDetailEntity data = GsonTools.changeGsonToBean(result, BallDetailEntity.class);
						
						List<BallDetailEntity.DataEntity> datas = data.getData();
						
						if (datas.size()>0) 
						{
							lv_ball.setVisibility(View.VISIBLE);
							
							mRelayout.setVisibility(View.GONE);
							
							adapter.setList(datas);
							
							adapter.notifyDataSetChanged();

						}
						else 
						{
							lv_ball.setVisibility(View.GONE);
							mRelayout.setVisibility(View.VISIBLE);
						}
						
						dialog.cancel();
					}

				}
			});
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId())
{
		case R.id.common_back:
			
			finish();
			
			break;
			
		case R.id.two:
			
			tv2.setTextColor(getResources().getColor(R.color.txt_green));
			
			tv3.setTextColor(getResources().getColor(R.color.txt_gray));
			
			if ("1".equals(order)) 
			{
				iv3.setImageResource("0".equals(type) ? R.drawable.down2b : R.drawable.up2b);
			}
			
			if ("0".equals(type))
			{
				type = "1";
				
				order = "0";
				
				tv2.setText("价格最低");
				
				iv2.setImageResource(R.drawable.up2);
				
			} 
			else if ("1".equals(type))
			{
				type = "0";
				
				order = "0";
				
				tv2.setText("价格最高");
				
				iv2.setImageResource(R.drawable.down2a);
			}
			
			getData();
			
			break;
			
		case R.id.three:
			
			tv2.setTextColor(getResources().getColor(R.color.txt_gray));
			
			tv3.setTextColor(getResources().getColor(R.color.txt_green));
			
			if ("0".equals(order)) 
			{
				iv2.setImageResource("0".equals(type) ? R.drawable.down2b : R.drawable.up2b);
				// if ("0".equals(type)) {
				// iv2.setImageResource(R.drawable.down2b);
				// } else {
				// iv2.setImageResource(R.drawable.up2b);
				// }
			}
			
			if ("0".equals(type))
			{
				type = "1";
				
				order = "1";
				
				tv3.setText("距离最近");
				
				iv3.setImageResource(R.drawable.up2);
				
			} 
			else if ("1".equals(type)) 
			{
				type = "0";
				
				order = "1";
				
				tv3.setText("距离最远");
				
				iv3.setImageResource(R.drawable.down2a);
				
			}
			
			getData();
			
		default:
			break;
			
		}

	}

	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{

		if (adapter.getItem(arg2) != null)
		{
			Intent intent = new Intent(this, BallInfoActivity.class);
			
			BallDetailEntity.DataEntity bean = adapter.getItem(arg2);
			
			intent.putExtra("BallDetailEntity", bean);
			
			intent.putExtra("ballname", bean.getCourt_name());
			
			intent.putExtra("date", date);
			
			intent.putExtra("cityname", cityname);
			
			this.startActivity(intent);

		}
	}
}
