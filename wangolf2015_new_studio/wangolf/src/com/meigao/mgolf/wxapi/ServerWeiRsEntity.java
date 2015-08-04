package com.meigao.mgolf.wxapi;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 向后台请求道微信支付相关的数据实体
 * 
 * @author Administrator
 * 
 */
public class ServerWeiRsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;
	private String info;
	private ArrayList<ServerWeiRsEntity> data;
	private int retcode;// 1;
	private String retmsg;// ok;
	private String appid;// wxabfbba326a013705;
	private String noncestr;// d0cab3346526cd0dc1b0559448e5b2c2;
	private String wpackage;// WXPay;
	private String prepayid;// 110100000014082652ef110254522c1b;
	private String timestamp;// 1409015254;
	private String sign;// ec676312bdd61f1ff2e63e8d2cd8636b0d447a23;

	public int getRetcode() {
		return retcode;
	}

	public void setRetcode(int retcode) {
		this.retcode = retcode;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getWpackage() {
		return wpackage;
	}

	public void setWpackage(String wpackage) {
		this.wpackage = wpackage;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public ArrayList<ServerWeiRsEntity> getData() {
		return data;
	}

	public void setData(ArrayList<ServerWeiRsEntity> data) {
		this.data = data;
	}

}
