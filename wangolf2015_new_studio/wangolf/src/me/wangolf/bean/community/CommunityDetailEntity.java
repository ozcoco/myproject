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
 * 描述 ：帖子详情接口 实体
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class CommunityDetailEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String info;
	private String status;
	private ArrayList<CommunityDetailEntity> data;
	private String address;
	private String avatar; //头像路径 //评论人头像
	private String content; //帖子内容
	private int id;//帖子id //一级评论id
	private String img_list; //评论图片列表
	private String name; //用户名//评论人用户名
	private ArrayList<CommunityDetailEntity> reply; //评论
	private String addtime; //评论时间
	private ArrayList<CommunityDetailEntity> level_two_reply;//二级评论
	private int receive_id;//接收评论人id
	private String receive_name; //接收评论人用户名
	private int reply_id;//评论人id
	private int user_id; //发帖人ID
	private String title;//帖子标题
	private String update_time; //最后更新时间
	private String is_praise;
	private String tags_name;

	private ArrayList<PraiseInfoEntity> praise_info;


	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<CommunityDetailEntity> getData() {
		return data;
	}
	public void setData(ArrayList<CommunityDetailEntity> data) {
		this.data = data;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImg_list() {
		return img_list;
	}
	public void setImg_list(String img_list) {
		this.img_list = img_list;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<CommunityDetailEntity> getReply() {
		return reply;
	}
	public void setReply(ArrayList<CommunityDetailEntity> reply) {
		this.reply = reply;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public ArrayList<CommunityDetailEntity> getLevel_two_reply() {
		return level_two_reply;
	}
	public void setLevel_two_reply(ArrayList<CommunityDetailEntity> level_two_reply) {
		this.level_two_reply = level_two_reply;
	}
	public int getReceive_id() {
		return receive_id;
	}
	public void setReceive_id(int receive_id) {
		this.receive_id = receive_id;
	}
	public String getReceive_name() {
		return receive_name;
	}
	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public ArrayList<PraiseInfoEntity> getPraise_info() {
		return praise_info;
	}

	public void setPraise_info(ArrayList<PraiseInfoEntity> praise_info) {
		this.praise_info = praise_info;
	}

	public String getIs_praise() {
		return is_praise;
	}

	public void setIs_praise(String is_praise) {
		this.is_praise = is_praise;
	}

	public String getTags_name() {
		return tags_name;
	}

	public void setTags_name(String tags_name) {
		this.tags_name = tags_name;
	}
}
