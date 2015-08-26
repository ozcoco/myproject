package me.wangolf.service.impl;

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
 * 描述 ： 社区接口实现类
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.File;
import java.util.ArrayList;

import android.util.Log;

import com.lidroid.xutils.http.RequestParams;

import me.wangolf.service.ICommunityService;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.Xutils;

public class CommunityServiceImpl implements ICommunityService
{
	public RequestParams	params	= null;

	@Override
	public void getHotPosts(IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "index");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	
	/* (非 Javadoc) 
	* <p>Title: getPostsList</p> 
	* <p>Description: 获取社区列表</p> 
	* @param user_id
	* @param praise_user_id
	* @param tags_id
	* @param page
	* @param number
	* @param iOAuthCallBack
	* @throws Exception 
	* @see me.wangolf.service.ICommunityService#getPostsList(java.lang.String, java.lang.String, java.lang.String, int, int, me.wangolf.service.IOAuthCallBack) 
	*/
	@Override
	public void getPostsList(String user_id, String praise_user_id, String tags_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{			
		params = new RequestParams();
		
		params.addBodyParameter("m", "Community");
		
		params.addBodyParameter("a", "posts");
		
		params.addBodyParameter("user_id", user_id);
		
		params.addBodyParameter("praise_user_id", praise_user_id);
		
		params.addBodyParameter("tags_id", tags_id);
		
		params.addBodyParameter("page", page + "");
		
		params.addBodyParameter("number", number + "");
		
		Xutils.getAllPostsListDataFromServer(params, iOAuthCallBack);
	}

	
	
	/* (非 Javadoc) 
	* <p>Title: getPostsList</p> 
	* <p>Description: 获取自己的社区列表</p> 
	* @param user_id
	* @param praise_user_id
	* @param tags_id
	* @param page
	* @param number
	* @param iOAuthCallBack
	* @throws Exception 
	* @see me.wangolf.service.ICommunityService#getPostsList(java.lang.String, java.lang.String, java.lang.String, int, int, me.wangolf.service.IOAuthCallBack) 
	*/
	@Override
	public void getSelfPostsList(String user_id, String praise_user_id, String tags_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{			
		params = new RequestParams();
		
		params.addBodyParameter("m", "Community");
		
		params.addBodyParameter("a", "posts");
		
		params.addBodyParameter("user_id", user_id);
		
		params.addBodyParameter("praise_user_id", praise_user_id);
		
		params.addBodyParameter("tags_id", tags_id);
		
		params.addBodyParameter("page", page + "");
		
		params.addBodyParameter("number", number + "");
		
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);
	}
	
	
	@Override
	public void getPostsDetail(String posts_id, String user_id, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "postsDetail");
		params.addBodyParameter("posts_id", posts_id);
		params.addBodyParameter("user_id", user_id);
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void toComplain(String user_id, String posts_id, String reson, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "complain");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("posts_id", posts_id + "");
		params.addBodyParameter("reson", reson);
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void sendPosts(String user_id, String content, String title, String address, String longitude, String latitude, String tags_id, String tags_name, String img_list, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "writePosts");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("content", content);
		params.addBodyParameter("title", title);
		params.addBodyParameter("address", address);
		params.addBodyParameter("longitude", longitude);
		params.addBodyParameter("latitude", latitude);
		params.addBodyParameter("tags_id", tags_id);
		params.addBodyParameter("tags_name", tags_name);
		params.addBodyParameter("img_list", img_list);
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void loadPostsImg(ArrayList<String> avatar_file, int i, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Comment");
		params.addBodyParameter("a", "eventLogoUpload");

		for (int a = 0; a < i; a++)
		{
			params.addBodyParameter("avatar_file[" + a + "]", new File(avatar_file
					.get(a)));

		}
		
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void toPostsReply(String user_id, int posts_user_id, String posts_id, String content, String img_list, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "postsReply");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("posts_id", posts_id);
		params.addBodyParameter("posts_user_id", posts_user_id + "");
		params.addBodyParameter("content", content);
		if (!CheckUtils.checkEmpty(img_list))
		{
			params.addBodyParameter("img_list", img_list);
		}

		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void toMommentReply(String user_id, int receive_id, int reply_id, String content, String img_list, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "commentReply");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("receive_id", receive_id + "");
		params.addBodyParameter("reply_id", reply_id + "");
		params.addBodyParameter("content", content);
		if (!CheckUtils.checkEmpty(img_list))
		{
			params.addBodyParameter("img_list", img_list);
		}

		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getNotification(String user_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "notification");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getLeaveMessageList(String user_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "leaveMessageList");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getLeaveMessageDetail(String user_id, int friend_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "leaveMessageDetail");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("friend_id", friend_id + "");
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void clearMessage(String user_id, int friend_id, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "clearMessage");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("friend_id", friend_id + "");
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void writeMessage(String user_id, int friend_id, String content, String img_list, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "writeMessage");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("friend_id", friend_id + "");
		params.addBodyParameter("content", content);
		if (!CheckUtils.checkEmpty(img_list))
			params.addBodyParameter("img_list", img_list);
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void toAttentionFriends(String user_id, int friend_id, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "attentionFriends");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("friend_id", friend_id + "");
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void CancelFriends(String user_id, int friend_id, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "cancelFriends");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("friend_id", friend_id + "");
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getFriendsList(String user_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "friendsList");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getFansList(String user_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "fansList");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getUserInfo(String user_id, int friend_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "userInfo");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("friend_id", friend_id + "");
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);
		// Log.i("wangolf",
		// "http://www.wangolf.me/m.php?m=Community&a=editInfo&user_id=" +
		// user_id + "&terminal=1");

	}

	@Override
	public void upUserInfo(String user_id, String my_intro, int gender, String nick_name, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "editInfo");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("my_intro", my_intro);
		params.addBodyParameter("gender", gender + "");
		params.addBodyParameter("nick_name", nick_name);
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);
		// Log.i("wangolf",
		// "http://www.wangolf.me/m.php?m=Community&a=editInfo&user_id=" +
		// user_id + "&terminal=1&my_intro=" + my_intro +
		// "&gender=" + gender + "&nick_name=" + nick_name);
	}

	@Override
	public void getMyReply(String user_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "myReply");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void upDateImages(String user_id, String images, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "updateImages");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("images", images);
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void deleteImages(String user_id, int images_id, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "deleteImages");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("images_id", images_id + "");
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getTagsList(IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "tagsList");
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void toPraise(String user_id, int posts_id, String type, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "praise");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("posts_id", posts_id + "");
		params.addBodyParameter("type", type);
		Xutils.getCommunityDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getPraiseList(int posts_id, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Community");
		params.addBodyParameter("a", "praiseList");
		params.addBodyParameter("posts_id", posts_id + "");
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getRangeName(String city_id, String longitude, String latitude, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Range");
		params.addBodyParameter("a", "rangeNameSearch");
		params.addBodyParameter("city_id", city_id);
		params.addBodyParameter("longitude", longitude);
		params.addBodyParameter("latitude", latitude);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}
}
