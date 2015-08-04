package me.wangolf.service.impl;

import com.lidroid.xutils.http.RequestParams;

import me.wangolf.service.IBallService;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.Xutils;

public class BallServiceImpl implements IBallService {

	private RequestParams params = null;

	@Override
	public void findBallSearch(String cityid, String date, String ballname, String longitude, String latitude, String order, String type,
			IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Ball");
		params.addBodyParameter("a", "ballsearch");
		params.addBodyParameter("cityid", cityid + "");
		params.addBodyParameter("date", date);
		params.addBodyParameter("longitude", longitude);
		params.addBodyParameter("latitude", latitude);
		params.addBodyParameter("ballname", ballname);
		params.addBodyParameter("order", order);
		params.addBodyParameter("type", type);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getBallInfo(String ballid, String date, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Ball");
		params.addBodyParameter("a", "ballinfo");
		params.addBodyParameter("ballid", ballid);
		params.addBodyParameter("date", date);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getBallDetail(String ballid, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Ball");
		params.addBodyParameter("a", "balldetail");
		params.addBodyParameter("ballid", ballid);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void findBallList(String ballname, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Ball");
		params.addBodyParameter("a", "findBallList");
		params.addBodyParameter("ballname", ballname);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

}
