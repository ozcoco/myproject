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
 * 描述 ：15关注列表实体
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class UserMyFriendsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private String info;
	private ArrayList<UserMyFriendsEntity> data;
	private String user_id;
	private int id;
	private String friend_name;
	private int friend_id;
	private String friend_avatar;
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

	public ArrayList<UserMyFriendsEntity> getData() {
		return data;
	}

	public void setData(ArrayList<UserMyFriendsEntity> data) {
		this.data = data;
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

	public String getFriend_name() {
		return friend_name;
	}

	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}

	public int getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}

	public String getFriend_avatar() {
		return friend_avatar;
	}

	public void setFriend_avatar(String friend_avatar) {
		this.friend_avatar = friend_avatar;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

}
