package me.wangolf.service.impl;

import com.lidroid.xutils.http.RequestParams;

import me.wangolf.ConstantValues;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.service.IPracService;
import me.wangolf.utils.Xutils;

public class PracServiceImpl implements IPracService
{
	public RequestParams params = null;

	/* 基础链接http://192.168.1.222/golf/ */
	static String BaseUrl = ConstantValues.BaseApi;
	
   /**
    * 
    *  最新修改加入的方法
    * 
    * @author oz
    * */
	@Override	
	public void findPracticeSearch(String cityId, String rangeName, String longitude, String latitude, IOAuthCallBack iOAuthCallBack)
	{
		
		String api = BaseUrl + "webRange/list?"
		+"city_id="+cityId
		+"&latitude="+latitude
		+"&longitude="+longitude
		+ "&range_name="+rangeName;
		
		Xutils.getDataFromServer(api, iOAuthCallBack);
		
	}
	
	
	@Override
	public void findPracticeSearch(String cityid, String rgname, String longitude, String latitude, String type, IOAuthCallBack iOAuthCallBack)
	{
		params = new RequestParams();
		
		params.addBodyParameter("m", "Range");
		
		params.addBodyParameter("a", "rgsearch");
		
		params.addBodyParameter("cityid", cityid);
		
		params.addBodyParameter("rgname", rgname);
		
		params.addBodyParameter("longitude", longitude);
		
		params.addBodyParameter("latitude", latitude);
		
		params.addBodyParameter("type", type);
		
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	
	
	/***
	 * 
	 * 最新修改的getPracticeInfo方法
	 * 
	 * 
	 **/
	
	@Override
	public void getPracticeInfo(String rgid, IOAuthCallBack iOAuthCallBack)
	{
//		params = new RequestParams();
//		
//		params.addBodyParameter("m", "Range");
//		
//		params.addBodyParameter("a", "rangeInfo");
//		
//		params.addBodyParameter("range_id", rgid);
		
//		Xutils.getDataFromServer(params, iOAuthCallBack);
		
		String api = BaseUrl + "webRange/detail?"
				   + "range_id="+ rgid;
		
		Xutils.getDataFromServer(api, iOAuthCallBack);
		
	}

	
	
	@Override
	public void getPracticeRgdetail(String rgid, IOAuthCallBack iOAuthCallBack)
	{
		params = new RequestParams();
		
		params.addBodyParameter("m", "Range");
		
		params.addBodyParameter("a", "rgdetail");
		
		params.addBodyParameter("rgid", rgid);
		
		// System.out.println("http://www.wangolf.me/m.php?m=Range&"+"a=rgdetail"+"&rgid="+rgid+"&terminal=1");
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	
	
	@Override
	public void getBallCityList(IOAuthCallBack iOAuthCallBack) {
		params = new RequestParams();
		params.addBodyParameter("m", "Ball");
		params.addBodyParameter("a", "ballCityList");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getWeather(String url, IOAuthCallBack iOAuthCallBack) {
		Xutils.getDataFromServer(url, iOAuthCallBack);
	}

}
