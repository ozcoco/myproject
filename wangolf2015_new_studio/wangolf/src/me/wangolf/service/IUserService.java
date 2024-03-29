package me.wangolf.service;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2014年12月29日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2014年12月29日
 * 
 * 描述 ： 用户信息接口
 * 1、登录 、退出、获取用户信息 、修改用户信息、上传头像、修改密码、重置密码、注册用户、修改绑定的手机号、修改用户名
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import me.wangolf.bean.usercenter.AddressBean;
import me.wangolf.bean.usercenter.OrderpayBean;
import me.wangolf.bean.usercenter.User;
import me.wangolf.bean.usercenter.UserInfoEntity;


public interface IUserService extends BaseService
{

	
	/** 
	* @Title: enter 
	* @Description: 进入应用界面时调用接口(在已获取imei号时调用) 
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void enter(IOAuthCallBack iOAuthCallBack)throws Exception;
	
	/** 
	* @Title: start 
	* @Description: 启动应用时调用接口(在未获取imei号时调用) 
	* @param @throws Exception    
	* @return void    返回类型 
	* @throws 
	*/
	public void start(IOAuthCallBack iOAuthCallBack)throws Exception;
	
	/** 
	* @Title: verifyRecommendCode 
	* @Description: 验证推荐码 
	* @param @param mobile
	* @param @param recommend_code
	* @param @throws Exception     
	* @return void    返回类型 
	* @throws 
	*/
	public void verifyRecommendCode(String  mobile, String recommend_code, IOAuthCallBack iOAuthCallBack)throws Exception;
	
	/** 
	* @Title: sendRecommendCode 
	* @Description: 获取推荐码
	* @param @param mobile 电话号码
	* @param @throws Exception 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendRecommendCode(String mobile, IOAuthCallBack iOAuthCallBack)throws Exception;
	
	/** 
	* @Title: recharge 
	* @Description: 获取充值列表
	* @param @param user_id 用户ID
	* @param @throws Exception    
	* @return void    返回类型 
	* @throws 
	*/
	public void getRechargeList(String user_id, IOAuthCallBack iOAuthCallBack)throws Exception;
	
	
	
	/** 
	* @Title: payRecommend 
	* @Description: 充值订单额外支付推荐码
	* @param @param out_trade_no 订单号
	* @param @param order_amount 订单金额
	* @param @param recommend_amount 推荐金额
	* @param @param recommend_code 推荐码
	* @param @param return_amount 返利金额
	* @param @param user_id 用户id
	* @param @param mobile 用户电话号码
	* @param @throws Exception   
	* @return void    返回类型 
	* @throws 
	*/
	public void payRecommend(String out_trade_no, String order_amount, String recommend_amount, String recommend_code, String return_amount, String user_id, String mobile,IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 评论图片上传
	 * 
	 * @param path
	 *            本地头像路径
	 * @param iOAuthCallBack
	 */

	public void upLoadCommentImages(String uid, String path, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 订单生成成功调用接口
	 * 
	 * @param out_trade_no
	 *            订单号
	 * @param self_amount
	 *            自己的余额
	 * @param order_amount
	 *            订单总价
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void topayExtra(String out_trade_no, Double self_amount, Double order_amount, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 最新修改的红包接口
	 * 
	 * @param uid
	 *            用户id
	 * @param page
	 *            页码
	 * @param number
	 *            条数
	 * @param iOAuthCallBack
	 * @throws Exception
	 * 
	 * @author oz
	 * 
	 */
	public void getPacksList(String uid, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 
	 * @author oz
	 * 
	 *         最新修的删除地址的接口
	 * 
	 * @param address_id
	 *            地址ID
	 * 
	 * @param user_id
	 *            用户ID
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void deleteAddrdata(String user_id, String address_id, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 
	 * @author oz
	 * 
	 *         最新修改的修改用户密码接口
	 * @param mobile
	 *            手机号码
	 * @param type
	 *            验证类型
	 * @throws Exception
	 * 
	 */

	public void getResetPwdCode(String mobile, String type, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 
	 * @author oz
	 * 
	 *         最新修改用户信息接口
	 * @param user_id
	 * @param my_intro
	 * @param gender
	 * @param nick_name
	 * @param iOAuthCallBack
	 * @throws Exception
	 * 
	 */
	public void updateUserInfo(String user_id, String nick_name, String my_intro, int gender, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 1、用户登录验证
	 * 
	 * @param user
	 * @return 返回用户信息
	 */

	public void UserLogin(User user, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 2、用户退出
	 * 
	 * @param uid
	 *            用户的ID
	 * @param iOAuthCallBack
	 */
	public void UserLogout(String uid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 3、获取用户信息
	 * 
	 * @param uid
	 *            用户的ID
	 * @param iOAuthCallBack
	 *            回调返回用户信息
	 */
	public void getUserInfo(String uid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 4、更新用户信息
	 * 
	 * @param userinfo
	 * @param iOAuthCallBack
	 */
	public void upUserInfo(UserInfoEntity.DataEntity userinfo, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 5、更新用户头像
	 * 
	 * @param path
	 *            本地头像路径
	 * @param iOAuthCallBack
	 */

	public void upLoad(String uid, String path, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 6、 修改用户密码
	 * 
	 * @param uid
	 *            用户ID
	 * @param opassword
	 *            旧密码
	 * @param npassword
	 *            新密码
	 * @param iOAuthCallBack
	 */
	public void upUserPassword(String uid, String opassword, String npassword, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 7、重置用户密码
	 * 
	 * @param phone
	 *            用户手机号
	 * @param password
	 *            新密码
	 * @param password
	 *            验证码
	 * @param iOAuthCallBack
	 */
	public void doResetPwd(String phone, String password, String code, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 
	 * @param mobile
	 *            电话号码(用户名)
	 * @param password
	 *            密码
	 * @param code
	 *            验证码
	 * @param recommend
	 *            推荐人号码
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void doRegist(String mobile, String password, String code, String recommend, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 9、修改绑定的手机号
	 * 
	 * @param uid
	 *            用户ID
	 * @param mobile
	 *            新手机号
	 * @param iOAuthCallBack
	 */
	public void UpBindMobile(String uid, String mobile, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 10、修改绑定的用户名
	 * 
	 * @param uid
	 *            用户ID
	 * @param
	 * 
	 * @param iOAuthCallBack
	 */
	public void UpBindUserName(String uid, String username, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 获取用户的地址
	 * 
	 * @param uid
	 *            用户ID
	 * @param iOAuthCallBack
	 */
	public void getUserAddr(String uid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 个人中心订单列表接口
	 * 
	 * @param user_id
	 *            用户ID
	 * @param type
	 *            0练习场1球场2商品
	 * @param page
	 * @param number
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getOrderList(String user_id, int type, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 生成订单接口(新版)
	 * 
	 * @param order_bean
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void toPrepay(OrderpayBean order_bean, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 订单生成成功调用接口
	 * 
	 * @param sn
	 *            订单号
	 * @param self_amount
	 *            自己的余额
	 * @param vouchers_sn
	 *            代金券代码
	 * @param vouchers_amount
	 *            代金券价值
	 * @param order_amount
	 *            订单总价
	 * @param user_id
	 *            用户ID
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void topayExtra(String sn, Double self_amount, String vouchers_sn, Double vouchers_amount, Double order_amount, String user_id, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 订单付款成功调用接口()
	 * 
	 * @param orderid
	 *            订单号
	 * @param uid
	 *            用户ID
	 * @param payment
	 *            支付方式（1、银联付款2、支付宝3、微信支付,4余额支付,5代金券支付）
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void toPay(String orderid, String uid, String payment, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 修改地址或更新地址
	 * 
	 * @param bean
	 *            修改地址对应的bean
	 * @param iOAuthCallBack
	 * @throws Exception
	 */

	public void upAddrdata(AddressBean bean, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 删除地址
	 * 
	 * @param aid
	 *            地址ID
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void deleteAddrdata(int aid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 101用户短信礼包设置开关
	 * 
	 * @param iOAuthCallBack
	 * @throws Exception
	 *             返回值 pack: "0",//推荐人0开启1关闭 regist: "0"//短信验证 0开启1关闭
	 */
	public void getUserOption(IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 12验证验证码是否正确
	 * 
	 * @param phone
	 *            手机号码
	 * @param code
	 *            验证码
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void toCheckcode(String phone, String code, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 11 发送验证码到手机
	 * 
	 * @param phone
	 *            手机号码
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getCode(String phone, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 123验证电话号码是否注册
	 * 
	 * @param mobile
	 *            电话号码
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void toCheckMobileRegist(String mobile, String type, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 126密码重置发送验证码
	 * 
	 * @param mobile
	 *            电话号码
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getResetPwdCode(String mobile, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 74 意见反馈
	 * 
	 * @param content
	 *            内容
	 * @param uid
	 *            用户ID
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void sendSuggestion(String content, String uid, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 75 关于我们
	 * 
	 * @param type
	 *            0代表使用指南，1代表支付帮助2代表招商合作3代表关于我们
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getAbout(String type, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 118 消费明细(新版)
	 * 
	 * @param user_id
	 *            用户ID
	 * @param page
	 *            分页数
	 * @param number
	 *            每页显示数目
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getConsumerDetail(String user_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 48获取代金券列表
	 * 
	 * @param uid
	 *            用户id
	 * @param type
	 *            代金券类型0代表可用代金券,1代表已过期或者未到期或者已使用完毕的代金券
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getVouchersList(String uid, String type, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 49添加代金券
	 * 
	 * @param uid
	 *            用户id
	 * @param sn
	 *            代金券代码
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void addVouchers(String uid, String sn, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 104红包列表
	 * 
	 * @param uid
	 *            用户id
	 * @param type
	 *            1我获得的红包2我分享的红包//此参数已移除
	 * @param page
	 *            页码
	 * @param number
	 *            条数
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getPacksList(String uid, String type, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception;

	
	/**
	 * 115 取消订单接口
	 * 
	 * @param sn
	 *            订单号
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void toOrderCancel(String sn, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 105 活动直接参加接口
	 * 
	 * @param uid
	 *            用户id
	 * @param cid
	 *            供应商ID
	 * @param eid
	 *            活动ID
	 * @param cusname
	 *            消费人名字
	 * @param sex
	 *            性别
	 * @param option
	 *            可选填写的内容
	 * @param mobile
	 *            电话号码
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void toEventJoin(String uid, String cid, String eid, String cusname, String sex, String option, String mobile, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 121 我发布的活动
	 * 
	 * @param user_id
	 *            用户ID
	 * @param page
	 *            分页码
	 * @param number
	 *            每页显示数目
	 * @throws Exception
	 */
	public void getMyPublishEvent(String user_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 65获取版本
	 * 
	 * @param iOAuthCallBack
	 * @throws Exception
	 */

	public void getVersion(IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 通过code获取access_token
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 * @param grant_type
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void getWxAccessToken(String appid, String secret, String code, String grant_type, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 1，微信登陆
	 * 
	 * @param weixin_open_id
	 * @param weixin_avatar
	 * @param weixin_nick_name
	 * @param weixin_gender
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void toWxLogin(String weixin_open_id, String weixin_avatar, String weixin_nick_name, String weixin_gender, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 3绑定手机号码
	 * 
	 * @param user_id
	 * @param mobile
	 * @param code
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void toBindMobile(int user_id, String mobile, String code, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 4，用户绑定微信号
	 * 
	 * @param user_id
	 * @param password
	 * @param weixin_open_id
	 * @param weixin_avatar
	 * @param weixin_nick_name
	 * @param weixin_gender
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void bindWeixin(String user_id, String password, String weixin_open_id, String weixin_avatar, String weixin_nick_name, String weixin_gender, IOAuthCallBack iOAuthCallBack) throws Exception;

	/**
	 * 编辑信息接口 新
	 * 
	 * @param user_id
	 * @param password
	 * @param my_intro
	 * @param gender
	 * @param nick_name
	 * @param iOAuthCallBack
	 * @throws Exception
	 */
	public void updateUserInfo(String user_id, String password, String my_intro, int gender, String nick_name, IOAuthCallBack iOAuthCallBack) throws Exception;
}
