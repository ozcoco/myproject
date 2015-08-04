package com.meigao.mgolf.wxapi;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

/**
 *  向后台请求道微信支付相关的数据业务类
 * @author Administrator
 *
 */
public class ServerWeiRsBiz {

	public HashMap<String, Object> getServerWeiResult(String t) throws Exception{
		HashMap<String, Object> hash=new HashMap<String, Object>();
		JSONObject jsonObj=new JSONObject(t);
		
		hash.put("status", jsonObj.get("status"));
		hash.put("info", jsonObj.getString("info"));
		JSONArray arr=jsonObj.getJSONArray("data");
		if(arr!=null&&arr.length()>0){
			JSONObject tmp=arr.getJSONObject(0);
			ServerWeiRsEntity serverWeiRsEntity=new ServerWeiRsEntity();
			serverWeiRsEntity.setAppid(tmp.getString("appid"));
			serverWeiRsEntity.setNoncestr(tmp.getString("noncestr"));
			serverWeiRsEntity.setPrepayid(tmp.getString("prepayid"));
			serverWeiRsEntity.setRetcode(tmp.getInt("retcode"));
			serverWeiRsEntity.setRetmsg(tmp.getString("retmsg"));
			serverWeiRsEntity.setSign(tmp.getString("sign"));
			serverWeiRsEntity.setTimestamp(tmp.getString("timestamp"));
			serverWeiRsEntity.setWpackage(tmp.getString("package"));
			hash.put("serverWeiRsEntity", serverWeiRsEntity);
		}
		return hash;
	}
}
