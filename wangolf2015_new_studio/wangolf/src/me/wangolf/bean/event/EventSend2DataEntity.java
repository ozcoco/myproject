package me.wangolf.bean.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class EventSend2DataEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String status;
	private String info;
	private ArrayList<EventSend2DataEntity> data;
	private ArrayList<Map<String, Object>> datas;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ArrayList<EventSend2DataEntity> getData() {
		return data;
	}

	public void setData(ArrayList<EventSend2DataEntity> data) {
		this.data = data;
	}

	public ArrayList<Map<String, Object>> getDatas() {
		return datas;
	}

	public void setDatas(ArrayList<Map<String, Object>> datas) {
		this.datas = datas;
	}



}
