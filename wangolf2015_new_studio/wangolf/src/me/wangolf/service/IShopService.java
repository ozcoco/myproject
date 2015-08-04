package me.wangolf.service;

import me.wangolf.bean.shop.OrderInfo;

public interface IShopService extends BaseService {
	/**
	 * 商城商品列表
	 * 
	 * @param sort
	 *            0按照价格排序1按照折扣排序
	 * @param rule
	 *            排序规则0升序1降序
	 * @param page
	 *            分页码
	 * @param number
	 *            每页数目
	 * @param type
	 *            0代表球具1代表服饰2代表配件3代表求票 4代表球旅5代表礼包6所有分类
	 * @throws Exception
	 */
	public void getShopList(String sort, String rule, String page, String number, int cate_id, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 商品详细信息
	 * 
	 * @param proid
	 *            商品ID
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getProInfo(String proid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 商品商品图片详情
	 * 
	 * @param proid
	 *            商品ID
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getproImgDetail(String proid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 生成订单
	 * 
	 * @param order
	 *            定单bean
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void OrGoodsAdd(OrderInfo order, IOAuthCallBack iOAuthCallBack) throws Exception;

    /**
     * 3 获取商品分类
     * @param iOAuthCallBack
     * @throws Exception
     */
    public void getCommodity(IOAuthCallBack iOAuthCallBack)throws Exception;
}
