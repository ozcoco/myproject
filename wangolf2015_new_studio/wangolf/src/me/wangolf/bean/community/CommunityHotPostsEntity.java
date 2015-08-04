package me.wangolf.bean.community;
/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年3月5日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年3月5日
 * 
 * 描述 ：置顶帖实体
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class CommunityHotPostsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String status;
	private String info;
	private ArrayList<CommunityHotPostsEntity> data;
	private String title;
	private int id;
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
	public ArrayList<CommunityHotPostsEntity> getData() {
		return data;
	}
	public void setData(ArrayList<CommunityHotPostsEntity> data) {
		this.data = data;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
