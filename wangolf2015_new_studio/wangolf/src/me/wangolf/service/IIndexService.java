package me.wangolf.service;

public interface IIndexService extends BaseService {
	/**
	 * 首页广告(version four)
	 * 
	 * @param page
	 *            加载的页数
	 * @param number
	 *            每次显示的数目
	 * @param version
	 *            4 (默认值1，代表版本1)
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getIndexAdv(String page, String number, String version, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 首页广告(version four)
	 * 
	 *  最新修改的获取广告数据的接口
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getIndexAdv(IOAuthCallBack iOAuthCallBack) throws Exception;

	
}
