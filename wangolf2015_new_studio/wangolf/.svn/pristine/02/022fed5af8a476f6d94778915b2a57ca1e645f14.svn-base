package me.wangolf.service;

import me.wangolf.bean.event.SendEventBean;

public interface IEventService extends BaseService {
	/**
	 * 活动列表
	 * 
	 * @param time
	 *            参加时间
	 * @param page
	 *            分页数
	 * @param number
	 *            每页显示数目
	 * @param version
	 *            版本号4
	 * @param recommend
	 *            0代表普通活动1代表首页活动
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getEventList(String time, String page, String number, String version, String recommend, IOAuthCallBack iOAuthCallBack)
			throws Exception;

	/**
	 * 活动详情
	 * 
	 * @param eveid
	 *            活动ID
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getEventDetail(String eveid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 参加活动人列表接口
	 * 
	 * @param eventid
	 *            活动ID
	 * @param page
	 * @param number
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getJoinEventList(String eventid, String page, String number, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 88用户参加活动时需要添加选项接口
	 * 
	 * @param eventid
	 *            活动ID
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getOptionsValues(String eventid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 99活动图片上传
	 * 
	 * @throws Exception
	 */
	public void upEventLogo(String avatar_file, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 87发布活动时加载的需要让用户填写的选项接口
	 * 
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getOptionsKeys(IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 86发布活动接口
	 * 
	 * @param bean
	 *            发布活动bean
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void sendEvent(SendEventBean bean, IOAuthCallBack iOAuthCallBack) throws Exception;


}
