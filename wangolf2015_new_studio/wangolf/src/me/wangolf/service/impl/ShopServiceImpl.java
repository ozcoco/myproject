package me.wangolf.service.impl;

import android.util.Log;

import com.lidroid.xutils.http.RequestParams;

import me.wangolf.bean.shop.OrderInfo;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.service.IShopService;
import me.wangolf.utils.Xutils;

public class ShopServiceImpl implements IShopService {
	public RequestParams params = null;

	@Override
	public void getShopList(String sort, String rule, String page, String number, int cate_id, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Commodity");
		params.addBodyParameter("a", "commodityList");
		params.addBodyParameter("sort", sort + "");
		params.addBodyParameter("rule", rule);
		params.addBodyParameter("page", page);
		params.addBodyParameter("number", number);
		params.addBodyParameter("cate_id", cate_id+"");
		Xutils.getDataFromServer(params, iOAuthCallBack);
		//Log.i("wangolf","m=Commodity&a=commodityList&sort="+sort+"&rule="+rule+"&page="+page+"&number="+number+"&cate_id="+cate_id);
	}

	@Override
	public void getProInfo(String proid, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Customer");
		params.addBodyParameter("a", "infopro");
		params.addBodyParameter("proid", proid);
		Xutils.getDataFromServer(params, iOAuthCallBack);

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

    @Override
    public void getCommodity(IOAuthCallBack iOAuthCallBack) throws Exception {
        params = new RequestParams();
        params.addBodyParameter("m", "Commodity");
        params.addBodyParameter("a", "category");
        Xutils.getDataFromServer(params, iOAuthCallBack);
    }
}
