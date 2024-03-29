package me.wangolf.service.impl;

import com.lidroid.xutils.http.RequestParams;

import me.wangolf.ConstantValues;
import me.wangolf.service.IIndexService;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.Xutils;

public class IndexServiceImpl implements IIndexService 
{
	public RequestParams params = null;

	static String BaseUrl = ConstantValues.BaseApi;
	
	@Override
	public void getIndexAdv(String page, String number, String version, 
		IOAuthCallBack iOAuthCallBack) throws Exception 
	{
		params = new RequestParams();
		
		params.addBodyParameter("m", "ProductType");
		
		params.addBodyParameter("a", "indexAdv");
		
		params.addBodyParameter("page", page);
		
		params.addBodyParameter("page", page);
		
		params.addBodyParameter("number", number);
		
		params.addBodyParameter("version", version);
		
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	
	/**
	 * 
	 * getIndexAdv
	 * 
	 * 最新修改的获取首页广告数据方法
	 * 
	 * */
	@Override
	public void getIndexAdv(IOAuthCallBack iOAuthCallBack) throws Exception 
	{
		String api = BaseUrl + "webAdvertise/list?";
		
		Xutils.getDataFromServer(api, iOAuthCallBack);
		
	}

}
