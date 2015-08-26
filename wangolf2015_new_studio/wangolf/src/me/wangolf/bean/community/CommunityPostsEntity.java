package me.wangolf.bean.community;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年3月6日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年3月6日
 * 
 * 描述 ：帖子列表实体
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class CommunityPostsEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long				serialVersionUID	= 1L;
	private String							status;
	private String							info;
	private ArrayList<CommunityPostsEntity>	data;
	private String							address;					// 发布地点
	private String							addtime;
	private String							content;					// 内容
	private int								id;						// id
	private String							img_list;					// 图片列表
	private String							is_delete;
	private String							name;						// 用户昵称
	private int								recommend;
	private int								reply_count;				// 回复数量
	private String							reply_update_time;
	private String							title;						// 标题
	private String							update_time;				// 最后更新时间
	private int								user_id;					// 楼主用户id
	private String							user_update_time;
	private String							visit_count;				// 访问数量
	private String							avatar;
	private int								praise_count;
	private ArrayList<PraiseInfoEntity>		praise_info;
	private ArrayList<CommunityPostsEntity>	pageinfo;
	private int								pageIndex;
	private int								pageSize;
	private int								totalpages;
	private int								totalRecords;
	private int								is_praise;
	private String							tags_name;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

	public ArrayList<CommunityPostsEntity> getData()
	{
		return data;
	}

	public void setData(ArrayList<CommunityPostsEntity> data)
	{
		this.data = data;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getAddtime()
	{
		return addtime;
	}

	public void setAddtime(String addtime)
	{
		this.addtime = addtime;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getImg_list()
	{
		return img_list;
	}

	public void setImg_list(String img_list)
	{
		this.img_list = img_list;
	}

	public String getIs_delete()
	{
		return is_delete;
	}

	public void setIs_delete(String is_delete)
	{
		this.is_delete = is_delete;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getRecommend()
	{
		return recommend;
	}

	public void setRecommend(int recommend)
	{
		this.recommend = recommend;
	}

	public int getReply_count()
	{
		return reply_count;
	}

	public void setReply_count(int reply_count)
	{
		this.reply_count = reply_count;
	}

	public String getReply_update_time()
	{
		return reply_update_time;
	}

	public void setReply_update_time(String reply_update_time)
	{
		this.reply_update_time = reply_update_time;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getUpdate_time()
	{
		return update_time;
	}

	public void setUpdate_time(String update_time)
	{
		this.update_time = update_time;
	}

	public int getUser_id()
	{
		return user_id;
	}

	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}

	public String getUser_update_time()
	{
		return user_update_time;
	}

	public void setUser_update_time(String user_update_time)
	{
		this.user_update_time = user_update_time;
	}

	public String getVisit_count()
	{
		return visit_count;
	}

	public void setVisit_count(String visit_count)
	{
		this.visit_count = visit_count;
	}

	public ArrayList<CommunityPostsEntity> getPageinfo()
	{
		return pageinfo;
	}

	public void setPageinfo(ArrayList<CommunityPostsEntity> pageinfo)
	{
		this.pageinfo = pageinfo;
	}

	public int getPageIndex()
	{
		return pageIndex;
	}

	public void setPageIndex(int pageIndex)
	{
		this.pageIndex = pageIndex;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getTotalpages()
	{
		return totalpages;
	}

	public void setTotalpages(int totalpages)
	{
		this.totalpages = totalpages;
	}

	public int getTotalRecords()
	{
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords)
	{
		this.totalRecords = totalRecords;
	}

	public ArrayList<PraiseInfoEntity> getPraise_info()
	{
		return praise_info;
	}

	public void setPraise_info(ArrayList<PraiseInfoEntity> praise_info)
	{
		this.praise_info = praise_info;
	}

	public int getPraise_count()
	{
		return praise_count;
	}

	public void setPraise_count(int praise_count)
	{
		this.praise_count = praise_count;
	}

	public String getAvatar()
	{
		return avatar;
	}

	public void setAvatar(String avatar)
	{
		this.avatar = avatar;
	}

	public int getIs_praise()
	{
		return is_praise;
	}

	public void setIs_praise(int is_praise)
	{
		this.is_praise = is_praise;
	}

	public String getTags_name()
	{
		return tags_name;
	}

	public void setTags_name(String tags_name)
	{
		this.tags_name = tags_name;
	}
}
