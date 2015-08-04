package me.wangolf.bean.ball;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月14日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月14日
 * 
 * 描述 ：
 * 
 * 
 * 修订历史 ： 球场实体类
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class BallDetailEntity implements Serializable {

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BallDetailEntity(String ballimg, String ballname, double distance, int id, String latitude, String longitude, int price, String tbname,
			String address) {
		super();
		this.ballimg = ballimg;
		this.ballname = ballname;
		this.distance = distance;
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.price = price;
		this.tbname = tbname;
		this.address = address;
	}

	private static final long serialVersionUID = 1L;
	public String status;
	public String info;

	private String ballimg;// 球场图片

	private String ballname;// 球场名称;//球场ID
	private double distance;// 距离 ,
	private int id;
	private String latitude;// 纬度 ,
	private String longitude;// 经度 ,
	private int price;// 价格 ,
	private String tbname;// 球场缩写名称 ,
	private String courtdata;// 18洞72杆
	private ArrayList<BallDetailEntity> data;

	public String getCourtdata() {
		return courtdata;
	}

	public void setCourtdata(String courtdata) {
		this.courtdata = courtdata;
	}

	public BallDetailEntity() {
	}

	public String getBallimg() {
		return ballimg;
	}

	public String getBallname() {
		return ballname;
	}

	public double getDistance() {
		return distance;
	}

	public int getId() {
		return id;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public int getPrice() {
		return price;
	}

	public String getTbname() {
		return tbname;
	}

	public void setBallimg(String ballimg) {
		this.ballimg = ballimg;
	}

	public void setBallname(String ballname) {
		this.ballname = ballname;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setTbname(String tbname) {
		this.tbname = tbname;
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

	public ArrayList<BallDetailEntity> getData() {
		return data;
	}

	public void setData(ArrayList<BallDetailEntity> data) {
		this.data = data;
	}

}
