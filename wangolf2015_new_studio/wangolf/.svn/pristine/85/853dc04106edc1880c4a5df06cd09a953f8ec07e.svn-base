package me.wangolf.service;

public interface ICollegeService extends BaseService {
	/**
	 * 高球生活分类(学院)
	 * 
	 * @param type
	 *            0代表高球技巧1代表高球生活2代表高球学院3代表视频专区,4高球装备(介绍装备)5所有分类
	 * @param size
	 *            每次加载的数目(默认15条)
	 * @param lastid
	 *            最后的数据ID
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getKnowledge(String type, String size, String lastid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 新手资讯根据标签显示列表(新版)
	 * 
	 * @param tags_id
	 *            tags_id
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getNewsList(String tags_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 
	 * @param knowid
	 *            资讯ID
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getInfoDetail(String knowid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 获取教练列表
	 * 
	 * @param rangeid
	 *            练习场ID
	 * @param sorttype
	 *            0按距离排序1按人气2按教龄
	 * @param page
	 *            页数
	 * @param number
	 *            每页显示数目
	 * @param collegeid
	 *            学院ID（显示所有学院则填写空字符）
	 * @param latitude
	 *            纬度
	 * @param longitude
	 *            经度
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getCoachList(String rangeid, String sorttype,String sort, String page, String number, String collegeid, String latitude, String longitude,
			IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 获取教练详细信息
	 * 
	 * @param coachid
	 *            教练ID
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getCoachInfo(String coachid, String uuid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 学院 学院 列表接口
	 * 
	 * @param latitude
	 *            纬度
	 * @param longitude
	 *            经度
	 * @param page
	 *            分页数
	 * @param number
	 *            每页显示数目
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getCollegeList(int sort_type, int sort, String latitude, String longitude, String page, String number, IOAuthCallBack iOAuthCallBack)
			throws Exception;

	/**
	 * 学院 学院详细信息
	 * 
	 * @param collegeid
	 *            学院ID
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getCollgetInfo(String collegeid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 更新教练赞数据
	 * 
	 * @param coachid
	 *            教练id
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void UpCoachZan(String coachid, String uuid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 学院资讯标签
	 * 
	 * @param iOAuthCallBack
	 */
	public void getNewsTags(IOAuthCallBack iOAuthCallBack) throws Exception;

}
