package me.wangolf.bean.college;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月6日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月6日
 * 
 * 描述 ：学院详情实体类
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class CollegeInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String status;
	private String info;
	private ArrayList<CollegeInfoEntity> data;
	private String picture;// "http://www.wangolf.me/public/avatar/pic/52/712eb89289829cccf2140b6e59bff566.jpg,http://www.wangolf.me/public/avatar/pic/52/712eb89289829cccf2140b6e59bff566.jpg",
	private String name;// "呵呵呵呵学院",
	private String address;// "北京北京",
	private String mobile;// "电话",
	private String introduce;// "嘿嘿",
	private String coachnumber;// "10",
	
   private ArrayList<CollegeInfoEntity> coach;//
   private String coachid;  //教练ID
   private String coachname; //教练名称
   private String coachphoto; //教练头像

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

	public ArrayList<CollegeInfoEntity> getData() {
		return data;
	}

	public void setData(ArrayList<CollegeInfoEntity> data) {
		this.data = data;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getCoachnumber() {
		return coachnumber;
	}

	public void setCoachnumber(String coachnumber) {
		this.coachnumber = coachnumber;
	}

	public ArrayList<CollegeInfoEntity> getCoach() {
		return coach;
	}

	public void setCoach(ArrayList<CollegeInfoEntity> coach) {
		this.coach = coach;
	}

	public String getCoachid() {
		return coachid;
	}

	public void setCoachid(String coachid) {
		this.coachid = coachid;
	}

	public String getCoachname() {
		return coachname;
	}

	public void setCoachname(String coachname) {
		this.coachname = coachname;
	}

	public String getCoachphoto() {
		return coachphoto;
	}

	public void setCoachphoto(String coachphoto) {
		this.coachphoto = coachphoto;
	}

}
