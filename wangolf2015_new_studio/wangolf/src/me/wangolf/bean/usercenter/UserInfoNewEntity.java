package me.wangolf.bean.usercenter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 上午10:46:40
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 上午10:46:40
 * 
 * 描述 ：用户中心实体（新版）
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class UserInfoNewEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private String info;
	private ArrayList<UserInfoNewEntity> data;
	private int reply_count;
	private int posts_count;
	private String photo;
	private String nick_name;
	private String my_intro;
	private String mobile;
	private int gender;
	private ArrayList<UserInfoNewEntity> images;
	private int id;
	private String url;
	private int fans_count;
	private int attention_count;
	private int attention_status;// 字段 1 留言 2关注
	private String weixin_avatar;//微信头像
	public int getStatus() {
		return status;
	}

	public int getGender() {
		return gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setGender(int gender) {
		this.gender = gender;
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

	public ArrayList<UserInfoNewEntity> getData() {
		return data;
	}

	public void setData(ArrayList<UserInfoNewEntity> data) {
		this.data = data;
	}

	public int getReply_count() {
		return reply_count;
	}

	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}

	public int getPosts_count() {
		return posts_count;
	}

	public void setPosts_count(int posts_count) {
		this.posts_count = posts_count;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getMy_intro() {
		return my_intro;
	}

	public void setMy_intro(String my_intro) {
		this.my_intro = my_intro;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public ArrayList<UserInfoNewEntity> getImages() {
		return images;
	}

	public void setImages(ArrayList<UserInfoNewEntity> images) {
		this.images = images;
	}

	public int getFans_count() {
		return fans_count;
	}

	public void setFans_count(int fans_count) {
		this.fans_count = fans_count;
	}

	public int getAttention_count() {
		return attention_count;
	}

	public void setAttention_count(int attention_count) {
		this.attention_count = attention_count;
	}

	public int getAttention_status() {
		return attention_status;
	}

	public void setAttention_status(int attention_status) {
		this.attention_status = attention_status;

	}

	public String getWeixin_avatar() {
		return weixin_avatar;
	}

	public void setWeixin_avatar(String weixin_avatar) {
		this.weixin_avatar = weixin_avatar;
	}
}
