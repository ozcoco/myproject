package me.wangolf.service.impl;

import java.io.File;

import com.lidroid.xutils.http.RequestParams;

import me.wangolf.bean.event.SendEventBean;
import me.wangolf.service.IEventService;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.Xutils;

public class EventServiceImpl implements IEventService {
	public RequestParams params = null;

	@Override
	public void getEventList(String time, String page, String number, String version, String recommend, IOAuthCallBack iOAuthCallBack)
			throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "EventPro");
		params.addBodyParameter("a", "eventlist");
		params.addBodyParameter("time", time);
		params.addBodyParameter("page", page);
		params.addBodyParameter("number", number);
		params.addBodyParameter("version", version);
		params.addBodyParameter("recommend", recommend);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getEventDetail(String eveid, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "EventPro");
		params.addBodyParameter("a", "eventdetail");
		params.addBodyParameter("eveid", eveid);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getJoinEventList(String eventid, String page, String number, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "EventPublish");
		params.addBodyParameter("a", "joinEventList");
		params.addBodyParameter("eventid", eventid);
		params.addBodyParameter("page", page);
		params.addBodyParameter("number", number);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getOptionsValues(String eventid, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "EventPublish");
		params.addBodyParameter("a", "getOptionsValues");
		params.addBodyParameter("eventid", eventid);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void upEventLogo(String avatar_file, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Comment");
		params.addBodyParameter("a", "eventLogoUpload");
		params.addBodyParameter("avatar_file[0]", new File(avatar_file));
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getOptionsKeys(IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "EventPublish");
		params.addBodyParameter("a", "getOptionsKeys");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void sendEvent(SendEventBean bean, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "EventPublish");
		params.addBodyParameter("a", "publish");
		params.addBodyParameter("eventname", bean.getEventname());
		params.addBodyParameter("btime", bean.getBtime());
		params.addBodyParameter("etime", bean.getEtime());
		params.addBodyParameter("address", bean.getAddress());
		params.addBodyParameter("number", bean.getNumber());
		params.addBodyParameter("introduce", bean.getIntroduce());
		params.addBodyParameter("price", bean.getPrice());
		params.addBodyParameter("option", bean.getOption());
		params.addBodyParameter("logo", bean.getLogo());
		params.addBodyParameter("uid", bean.getUid());
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}



}
