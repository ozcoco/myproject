package me.wangolf.service.impl;


import com.lidroid.xutils.http.RequestParams;

import me.wangolf.ConstantValues;
import me.wangolf.service.IKnowledgeService;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.Xutils;

/**
 * ============================================================

 * ============================================================
 */
public class KnowledgeServiceImpl implements IKnowledgeService 
{
    public RequestParams params = null;
    
    static String BaseUrl = ConstantValues.BaseApi;
    
    
    /***
     * 
     * getGeneralKnowledgeTags
     * 
     * 最新修改的获取高球通数据的方法
     * 
     * @author oz
     * 
     * */
    @Override
    public void getGeneralKnowledgeTags(IOAuthCallBack iOAuthCallBack) throws Exception 
    {
//        params = new RequestParams();
//        
//        params.addBodyParameter("m", "Information");
//        
//        params.addBodyParameter("a", "generalKnowledgeTags");
//        
//        Xutils.getDataFromServer(params, iOAuthCallBack);
        
        String api = BaseUrl + "webInformation/generalKnowledgeTags?";
		
		Xutils.getDataFromServer(api, iOAuthCallBack);
        
        
    }

    
    /***
     * 
     * getKnowledgeList
     * 
     * 最新修改的获取新闻列表(包括高球课堂跟高球常识)数据的方法
     * 
     * @author oz
     * 
     * */
    @Override
    public void getKnowledgeList(String tags_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception {
   	
    	  String api = BaseUrl + "webInformation/list?"
    	  		+ "&tags_id=" + tags_id
    	  		+ "&page=" + page
    	  		+ "&number=" + number;
 		
 		 Xutils.getDataFromServer(api, iOAuthCallBack);
    	
    }

    
    
    /***
     * 
     * getInformation
     * 
     * 最新修改的获取新闻详情(包括高球课堂跟高球常识)数据的方法
     * 
     * @author oz
     * 
     * */
    @Override
    public void getInformation(String knowid, IOAuthCallBack iOAuthCallBack) throws Exception 
    {
    	String api = BaseUrl + "webInformation/detail?"
    	  		  + "&information_id=" + knowid;
 		
 		 Xutils.getDataFromServer(api, iOAuthCallBack);
    	
    	
    }
    

}
