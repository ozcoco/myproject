package me.wangolf.bean.college;
/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月5日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月5日
 * 
 * 描述 ： 教练list实休类
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class CoachListEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	private String status;
	private String info;
	private ArrayList<CoachListEntity> data;
	
	private int coachid;//"1",
	private String photo;//"www.wangolf.me/public/avatar/pic/52/712eb89289829cccf2140b6e59bff566.jpg",
	private String username;//"张三",
	private int sex;//"1",
	private String ballage;//"10",
	private String praise;//"25",
	private String price;//"1500元/课时，13800元/10课时",
	private String distance;//"1.3",
	private String badges;//职业教练
	
	public int getCoachid() {
		return coachid;
	}
	public void setCoachid(int coachid) {
		this.coachid = coachid;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getBallage() {
		return ballage;
	}
	public void setBallage(String ballage) {
		this.ballage = ballage;
	}
	public String getPraise() {
		return praise;
	}
	public void setPraise(String praise) {
		this.praise = praise;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getBadges() {
		return badges;
	}
	public void setBadges(String badges) {
		this.badges = badges;
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
	public ArrayList<CoachListEntity> getData() {
		return data;
	}
	public void setData(ArrayList<CoachListEntity> data) {
		this.data = data;
	}
	
}
