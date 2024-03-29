package me.wangolf.service;

public interface IBallService extends BaseService 
{
	
	/**
	 * 球场搜索
	 * 
	 * @param cityid
	 *            城市ID
	 * @param date
	 *            打球日期
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            纬度
	 * @param iOAuthCallBack
	 *            回调值
	 * @throws Exception
	 */
	public void findBallSearch(String cityid, String date, String longitude, String latitude,
			IOAuthCallBack iOAuthCallBack) throws Exception;
	

	/**
	 * 球场搜索
	 * 
	 * @param cityid
	 *            城市ID
	 * @param date
	 *            打球日期
	 * @param ballname
	 *            球场名称
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            纬度
	 * @param order
	 *            排序类型(0价格排序，1距离排序)
	 * @param type
	 *            排序规则(0降序，1升序)
	 * @param iOAuthCallBack
	 *            回调值
	 * @throws Exception
	 */
	public void findBallSearch(String cityid, String date, String ballname, String longitude, String latitude, String order, String type,
			IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 球场详细信息
	 * 
	 * @param ballid
	 *            球场ID
	 * @param date
	 *            打球日期
	 * @throws Exception
	 */
	public void getBallInfo(String ballid, String date, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 
	 * @param ballid
	 *            球场id
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getBallDetail(String ballid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 模糊搜索球场列表接口
	 * 
	 * @param ballname
	 *            球场名字
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void findBallList(String ballname, IOAuthCallBack iOAuthCallBack) throws Exception;
}
