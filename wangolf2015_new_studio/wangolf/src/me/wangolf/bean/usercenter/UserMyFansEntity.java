package me.wangolf.bean.usercenter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 下午2:39:58
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 下午2:39:58
 * 
 * 描述 ：粉丝实体
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class UserMyFansEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private String info;
	private ArrayList<UserMyFansEntity> data;
	private String user_id;
	private int id;
	private String fans_name;
	private int fans_id;
	private int friend_id;
	private String fans_avatar;
	private String addtime;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public ArrayList<UserMyFansEntity> getData() {
		return data;
	}

	public void setData(ArrayList<UserMyFansEntity> data) {
		this.data = data;
	}

	public int getFans_id() {
		return fans_id;
	}

	public void setFans_id(int fans_id) {
		this.fans_id = fans_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFans_name() {
		return fans_name;
	}

	public void setFans_name(String fans_name) {
		this.fans_name = fans_name;
	}

	public int getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}

	public String getFans_avatar() {
		return fans_avatar;
	}

	public void setFans_avatar(String fans_avatar) {
		this.fans_avatar = fans_avatar;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

}
