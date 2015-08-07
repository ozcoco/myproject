package me.wangolf.service.impl;

import android.util.Log;

import com.lidroid.xutils.http.RequestParams;

import me.wangolf.service.IKnowledgeService;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.Xutils;

/**
 * ============================================================

 * ============================================================
 */
public class KnowledgeServiceImpl implements IKnowledgeService {
    public RequestParams params = null;
    @Override
    public void getGeneralKnowledgeTags(IOAuthCallBack iOAuthCallBack) throws Exception {
        params = new RequestParams();
        params.addBodyParameter("m", "Information");
        params.addBodyParameter("a", "generalKnowledgeTags");
        Xutils.getDataFromServer(params, iOAuthCallBack);
    }

    @Override
    public void getKnowledgeList(String tags_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception {
        params = new RequestParams();
        params.addBodyParameter("m", "Information");
        params.addBodyParameter("a", "newsList");
        params.addBodyParameter("tags_id", tags_id);
        params.addBodyParameter("page", page + "");
        params.addBodyParameter("number", number + "");
        //Log.i("wangolf","m=Information&a=newsList&tags_id="+tags_id+"&page="+page+"&number="+number);
        Xutils.getDataFromServer(params, iOAuthCallBack);
    }

    @Override
    public void getInformation(String knowid, IOAuthCallBack iOAuthCallBack) throws Exception {

    }

}
