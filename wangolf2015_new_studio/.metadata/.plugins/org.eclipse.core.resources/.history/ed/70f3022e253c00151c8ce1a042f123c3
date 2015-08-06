package me.wangolf.bean.ball;

import java.io.Serializable;
import java.util.ArrayList;

public class BallNameEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;// 城市id
	private String ballname;
	private String cityname;
	public String status;
	public String info;
	private ArrayList<BallNameEntity> data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBallname() {
		return ballname;
	}

	public void setBallname(String ballname) {
		this.ballname = ballname;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	@Override
	public boolean equals(Object o) {
		BallNameEntity b = (BallNameEntity) o;
		return id == b.getId() && ballname.equals(b.getBallname()) && cityname.equals(b.getCityname());
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

	public ArrayList<BallNameEntity> getData() {
		return data;
	}

	public void setData(ArrayList<BallNameEntity> data) {
		this.data = data;
	}
	
}
