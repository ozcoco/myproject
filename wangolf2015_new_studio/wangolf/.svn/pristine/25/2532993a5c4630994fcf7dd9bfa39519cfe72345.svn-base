package me.wangolf.service;

public interface IPracService extends BaseService {
	/**
	 * 练习场搜索
	 * 
	 * @param cityid
	 *            城市id
	 * @param rgname
	 *            球场名称(模糊搜索)
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            纬度
	 * @param type
	 *            类型(0代表按会员收费，1代表按记球收费，2代表按记时收费,3显示所有)
	 * @param iOAuthCallBack
	 */
	public void findPracticeSearch(String cityid, String rgname, String longitude, String latitude, String type, IOAuthCallBack iOAuthCallBack);

	/**
	 * 练习场信息
	 * 
	 * @param rgid
	 *            练习场ID
	 * @param iOAuthCallBack
	 */
	public void getPracticeInfo(String rgid, IOAuthCallBack iOAuthCallBack);

	/**
	 * 练习场标签信息
	 * 
	 * @param rgid
	 *            练习场ID
	 * @param iOAuthCallBack
	 */
	public void getPracticeRgdetail(String rgid, IOAuthCallBack iOAuthCallBack);

	/**
	 * 显示城市列表接口
	 * 
	 * @param iOAuthCallBack
	 */
	public void getBallCityList(IOAuthCallBack iOAuthCallBack);

	/**
	 * 获取天气信息
	 * @param url
	 *            路径
	 * @param iOAuthCallBack
	 */
	public void getWeather(String url, IOAuthCallBack iOAuthCallBack);

}
