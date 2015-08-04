package me.wangolf.bean.usercenter;

import java.util.ArrayList;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月26日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月26日
 * 
 * 描述 ： 返回的状态
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
public class RegistEntity {
	private String status;
	private String info;
	private ArrayList<RegistEntity> data;
	private String owninfo;
	private  String otherinfo;
	private String ownprice;
	private int regstatus;//0代表要显示注册礼物1代表不需要显示注册礼物


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

	public ArrayList<RegistEntity> getData() {
		return data;
	}

	public void setData(ArrayList<RegistEntity> data) {
		this.data = data;
	}

	public String getOtherinfo() {
		return otherinfo;
	}

	public void setOtherinfo(String otherinfo) {
		this.otherinfo = otherinfo;
	}

	public String getOwninfo() {
		return owninfo;
	}

	public void setOwninfo(String owninfo) {
		this.owninfo = owninfo;
	}

	public String getOwnprice() {
		return ownprice;
	}

	public void setOwnprice(String ownprice) {
		this.ownprice = ownprice;
	}

	public int getRegstatus() {
		return regstatus;
	}

	public void setRegstatus(int regstatus) {
		this.regstatus = regstatus;
	}
}
