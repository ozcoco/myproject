package me.wangolf.service.impl;


import com.lidroid.xutils.http.RequestParams;

import me.wangolf.ConstantValues;
import me.wangolf.bean.shop.OrderInfo;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.service.IShopService;
import me.wangolf.utils.Xutils;

public class ShopServiceImpl implements IShopService 
{
	static String BaseUrl = ConstantValues.BaseApi;
	
	public RequestParams params = null;

	
	/**
	 * 
	 * getShopList
	 * 
	 * 最新修改的获取商品列表方法
	 * 
	 * */

	@Override
	public void getShopList(String sort, String rule, String page, String number, int cate_id, IOAuthCallBack iOAuthCallBack) throws Exception 
	{
		
	String api = BaseUrl + "webCommodity/list?"
	+ "&category_id="+cate_id
	+ "&sort_type="+sort
	+ "&sort_rule="+rule
	+ "&page="+page
	+ "&number="+number;

    Xutils.getDataFromServer(api, iOAuthCallBack);
		
		
	}
	
	/**
	 * 
	 * getProInfo
	 * 
	 * 最新修改的获取商品详情方法
	 * 
	 * */
	@Override
	public void getProInfo(String commodity_id, IOAuthCallBack iOAuthCallBack) throws Exception 
	{
//		params = new RequestParams();
//		params.addBodyParameter("m", "Customer");
//		params.addBodyParameter("a", "infopro");
//		params.addBodyParameter("proid", proid);
//		Xutils.getDataFromServer(params, iOAuthCallBack);
		
		String api = BaseUrl + "webCommodity/detail?"				
				+ "commodity_id=" + commodity_id;
		
		Xutils.getDataFromServer(api, iOAuthCallBack);
		
	}

	/**
	 * 
	 * getCommodity
	 * 
	 * 最新修改的商品标签列表方法
	 * 
	 * */
    @Override
    public void getCommodity(IOAuthCallBack iOAuthCallBack) throws Exception {
//        params = new RequestParams();
//        params.addBodyParameter("m", "Commodity");
//        params.addBodyParameter("a", "category");
//        Xutils.getDataFromServer(params, iOAuthCallBack);
    	
    	String api = BaseUrl + "webCommodity/category?";
    	
    	Xutils.getDataFromServer(api, iOAuthCallBack);
    	
    }
	
    
    
	@Override
	public void getproImgDetail(String proid, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Customer");
		params.addBodyParameter("a", "proImgDetail");
		params.addBodyParameter("proid", proid);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void OrGoodsAdd(OrderInfo order, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "OrderGoods");
		params.addBodyParameter("a", "orGoodsAdd");
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}



	
	

}
