package me.wangolf.service.impl;

import com.lidroid.xutils.http.RequestParams;

import me.wangolf.service.ICollegeService;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.Xutils;

public class CollegeServiceImpl implements ICollegeService {
	public RequestParams params = null;

	@Override
	public void getKnowledge(String type, String size, String lastid, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Information");
		params.addBodyParameter("a", "knowledge");
		params.addBodyParameter("size", size);
		if (!"-1".equals(lastid)) {
			params.addBodyParameter("lastid", lastid);
		}
		params.addBodyParameter("type", type);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getInfoDetail(String knowid, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Information");
		params.addBodyParameter("a", "infoDetail");
		params.addBodyParameter("knowid", knowid);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getCoachList(String rangeid, String sorttype, String sort, String page, String number, String collegeid, String latitude,
			String longitude, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Coach");
		params.addBodyParameter("a", "CoachList");
		params.addBodyParameter("rangeid", rangeid);
		params.addBodyParameter("sorttype", sorttype);
		params.addBodyParameter("sort", sort);
		params.addBodyParameter("page", page);
		params.addBodyParameter("number", number);
		params.addBodyParameter("collegeid", collegeid);
		params.addBodyParameter("latitude", latitude);
		params.addBodyParameter("longitude", longitude);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getCoachInfo(String coachid, String uuid, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Coach");
		params.addBodyParameter("a", "CoachInfo");
		params.addBodyParameter("uuid", uuid);
		params.addBodyParameter("coachid", coachid);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getCollegeList(int sort_type, int sort, String latitude, String longitude, String page, String number, IOAuthCallBack iOAuthCallBack)
			throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Coach");
		params.addBodyParameter("a", "CollegeList");
		params.addBodyParameter("sorttype", sort_type + "");
		params.addBodyParameter("sort", sort + "");
		params.addBodyParameter("latitude", latitude);
		params.addBodyParameter("longitude", longitude);
		params.addBodyParameter("page", page);
		params.addBodyParameter("number", number);
//		System.out.println("m=" + "Coach" + "a=" + "CollegeList" + "&sort_type=" + sort_type + "&sort=" + sort + "&latitude=" + latitude + "&longitude="
//				+ longitude + "&page=" + page + "&number=" + number);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getCollgetInfo(String collegeid, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Coach");
		params.addBodyParameter("a", "College");
		params.addBodyParameter("collegeid", collegeid);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void UpCoachZan(String coachid, String uuid, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Coach");
		params.addBodyParameter("a", "praise");
		params.addBodyParameter("coachid", coachid);
		params.addBodyParameter("uuid", uuid);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getNewsTags(IOAuthCallBack iOAuthCallBack) {
		params = new RequestParams();
		params.addBodyParameter("m", "Information");
		params.addBodyParameter("a", "newsTags");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getNewsList(String tags_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Information");
		params.addBodyParameter("a", "newsList");
		params.addBodyParameter("tags_id", tags_id);
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

}
