package me.wangolf.bean.usercenter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 上午11:24:18
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 上午11:24:18
 * 
 * 描述 ：我的回复实体
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class UserMyReplyEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private String info;
	private ArrayList<UserMyReplyEntity> data;
	private String user_id;
	private String posts_user_id;
	private String posts_title;
	private String posts_time;
	private int posts_reply_count;
	private int posts_id;
	private int level;
	private int id;
	private String content;
	private String posts_content;
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

	public ArrayList<UserMyReplyEntity> getData() {
		return data;
	}

	public void setData(ArrayList<UserMyReplyEntity> data) {
		this.data = data;
	}

	public String getPosts_content() {
		return posts_content;
	}

	public void setPosts_content(String posts_content) {
		this.posts_content = posts_content;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getPosts_user_id() {
		return posts_user_id;
	}

	public void setPosts_user_id(String posts_user_id) {
		this.posts_user_id = posts_user_id;
	}

	public String getPosts_title() {
		return posts_title;
	}

	public void setPosts_title(String posts_title) {
		this.posts_title = posts_title;
	}

	public String getPosts_time() {
		return posts_time;
	}

	public void setPosts_time(String posts_time) {
		this.posts_time = posts_time;
	}

	public int getPosts_reply_count() {
		return posts_reply_count;
	}

	public void setPosts_reply_count(int posts_reply_count) {
		this.posts_reply_count = posts_reply_count;
	}

	public int getPosts_id() {
		return posts_id;
	}

	public void setPosts_id(int posts_id) {
		this.posts_id = posts_id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

}
