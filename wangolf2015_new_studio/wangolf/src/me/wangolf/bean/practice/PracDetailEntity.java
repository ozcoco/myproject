package me.wangolf.bean.practice;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月10日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月10日
 * 
 * 描述 ：练习场详细信息
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class PracDetailEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int rgid;

	public int getRgid() {
		return rgid;
	}

	public void setRgid(int rgid) {
		this.rgid = rgid;
	}

	private String rgimg;// 图片路径
	private String rgname; // 练习场名称
	private String address; // 练习场详细地址
	private String rgtag;
	private String mobile;// 联系电话
	private String stime;// 9:00-23:00营业时间
	private String latitude;// 纬度
	private String longitude;// 经度
	private String city;// 所在城市id
	private String coach;//0代表没有教练1代表有
	private String status;
	private String info;
	private ArrayList<PracDistriEntity> distributors;
	private ArrayList<PracDetailEntity> data;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStime() {
		return stime;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getRgimg() {
		return rgimg;
	}

	public void setRgimg(String rgimg) {
		this.rgimg = rgimg;
	}

	public String getRgname() {
		return rgname;
	}

	public void setRgname(String rgname) {
		this.rgname = rgname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRgtag() {
		return rgtag;
	}

	public void setRgtag(String rgtag) {
		this.rgtag = rgtag;
	}

	public ArrayList<PracDistriEntity> getDistributors() {
		return distributors;
	}

	public void setDistributors(ArrayList<PracDistriEntity> distributors) {
		this.distributors = distributors;
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

	public ArrayList<PracDetailEntity> getData() {
		return data;
	}

	public void setData(ArrayList<PracDetailEntity> data) {
		this.data = data;
	}

}
