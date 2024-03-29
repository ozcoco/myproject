package me.wangolf.service.impl;

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
 * 描述 ：
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.renderscript.Sampler;
import android.util.Log;

import com.lidroid.xutils.http.RequestParams;

import me.wangolf.ConstantValues;
import me.wangolf.bean.usercenter.AddressBean;
import me.wangolf.bean.usercenter.OrderpayBean;
import me.wangolf.bean.usercenter.User;
import me.wangolf.bean.usercenter.UserInfoEntity;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.service.IUserService;
import me.wangolf.utils.Xutils;

public class UserServiceImpl implements IUserService
{
	public RequestParams	params	= null;

	public String			result	= null;

	public IOAuthCallBack	iOAuthCallBack;

	static String			BaseUrl	= ConstantValues.BaseApi;

	public void setResult(String result)
	{
		this.result = result;
	}

	/**
	 * 
	 * upLoadCommentImages
	 * 
	 * 评论图片上传接口
	 * 
	 * @author oz
	 * 
	 * **/
	@Override
	public void upLoadCommentImages(String user_id, String path, IOAuthCallBack iOAuthCallBack) throws Exception
	{

		params = new RequestParams();

		params.addBodyParameter("avatar_file", new File(path));

		String api = BaseUrl + "webImage/comment";

		Xutils.getImageDataFromServer(api, params, iOAuthCallBack);

	}

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
	@Override
	public void getPacksList(String uid, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{

		String api = BaseUrl + "webGift/list?" + "number=" + number + "&page=" + page;

		Xutils.getDataFromServer(api, iOAuthCallBack);
	}

	/***
	 * 
	 * UserLogin
	 * 
	 * 最新修改的登录接口
	 * 
	 * */
	@Override
	public void UserLogin(User user, IOAuthCallBack iOAuthCallBack)
	{
		// params = new RequestParams();
		// params.addBodyParameter("m", "UserInfo");
		// params.addBodyParameter("a", "login");
		// params.addBodyParameter("username", user.getUsername());
		// params.addBodyParameter("password", user.getPassword());
		// Xutils.getDataFromServer(params, iOAuthCallBack);

		String api = BaseUrl + "webUser/login?" + "mobile=" + user
				.getUsername() + "&password=" + user.getPassword();

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

	/***
	 * 
	 * UserLogout
	 * 
	 * 最新修改的注册接口
	 * 
	 * */
	@Override
	public void UserLogout(String uid, IOAuthCallBack iOAuthCallBack)
	{
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "logout");
		params.addBodyParameter("uid", uid);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	/***
	 * 
	 * getUserInfo
	 * 
	 * 最新修改的获取用户信息的接口
	 * 
	 * */
	@Override
	public void getUserInfo(String user_id, IOAuthCallBack iOAuthCallBack)
	{
		// params = new RequestParams();
		// params.addBodyParameter("m", "UserInfo");
		// params.addBodyParameter("a", "userinfo");
		// params.addBodyParameter("uid", uid);
		// Xutils.getDataFromServer(params, iOAuthCallBack);

		String api = BaseUrl + "webUser/info?";

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

	@Override
	public void upUserInfo(UserInfoEntity.DataEntity userinfo, IOAuthCallBack iOAuthCallBack)
	{
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "updateinfo");
		params.addBodyParameter("uid", userinfo.getUser_id());
		params.addBodyParameter("nickname", userinfo.getNick_name());
		params.addBodyParameter("sex", userinfo.getGender());
		params.addBodyParameter("photo", userinfo.getAvatar());
		// params.addBodyParameter("birthday", userinfo.getBirthday());
		// params.addBodyParameter("ballage", userinfo.getBallage());
		params.addBodyParameter("summy", userinfo.getMy_intro());
		// params.addBodyParameter("ballage", userinfo.getBallage());
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	
	
	/**
	 * 
	 * upLoad
	 * 
	 * 最新修改的头像上传的接口
	 * 
	 * @author oz
	 * 
	 * **/
	@Override
	public void upLoad(String user_id, String path, IOAuthCallBack iOAuthCallBack)
	{
//		String api = BaseUrl + "webImage/avatar";

		String api = "http://192.168.1.239:8080/golf/" + "webImage/avatar";
		
		api += "?terminal=1&user_id="+ ConstantValues.UID+"&unique_key=" + ConstantValues.UNIQUE_KEY;
				
		params.addBodyParameter("avatar_file", new File(path), "multipart/form-data");			
		
		Xutils.getImageDataFromServer(api, params, iOAuthCallBack);

	}

	
	
	/**
	 * 
	 * upUserPassword
	 * 
	 * 最新修改的修改密码的接口
	 * 
	 * @author oz
	 * 
	 * **/
	@Override
	public void upUserPassword(String user_id, String opassword, String npassword, IOAuthCallBack iOAuthCallBack)
	{

		String api = BaseUrl + "webUser/updatePasswd";

		params = new RequestParams();

		// params.addBodyParameter("user_id", user_id);

		params.addBodyParameter("opassword", opassword);

		params.addBodyParameter("npassword", npassword);

		Xutils.getDataFromServer(api, params, iOAuthCallBack);

	}

	
	
	/**
	 * 
	 * doResetPwd
	 * 
	 * 最新修改的重置密码的接口
	 * 
	 * **/
	@Override
	public void doResetPwd(String mobile, String password, String code, IOAuthCallBack iOAuthCallBack)
	{
		// params = new RequestParams();
		// params.addBodyParameter("m", "UserInfo");
		// params.addBodyParameter("a", "resetPasswd");
		// params.addBodyParameter("mobile", phone);
		// params.addBodyParameter("password", password);
		// params.addBodyParameter("code", code);
		// Xutils.getDataFromServer(params, iOAuthCallBack);

		String api = BaseUrl + "webUser/resetPasswd?" + "mobile=" + mobile + "&code=" + code + "&password=" + password;

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

	@Override
	public void doRegist(String mobile, String password, String code, String recommend, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		// params = new RequestParams();
		// params.addBodyParameter("m", "UserInfo");
		// params.addBodyParameter("a", "signUp");
		// params.addBodyParameter("mobile", mobile);
		// params.addBodyParameter("password", password);
		// params.addBodyParameter("code", code);
		// params.addBodyParameter("recommend", recommend);
		// Xutils.getDataFromServer(params, iOAuthCallBack);

		String api = BaseUrl + "webUser/regist?" + "mobile=" + mobile + "&password=" + password + "&code=" + code + "&recommend_mobile=" + recommend;

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

	@Override
	public void UpBindMobile(String uid, String mobile, IOAuthCallBack iOAuthCallBack)
	{
		params = new RequestParams();
		params.addBodyParameter("uid", uid);
		params.addBodyParameter("mobile", mobile);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void UpBindUserName(String uid, String username, IOAuthCallBack iOAuthCallBack)
	{
		params = new RequestParams();
		params.addBodyParameter("uid", uid);
		params.addBodyParameter("username", username);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getUserAddr(String user_id, IOAuthCallBack iOAuthCallBack)
	{

		String api = BaseUrl + "webUser/getUserAddress?";

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

	/**
	 * 
	 * getOrderList
	 * 
	 * 最新修改的订单列表的接口
	 * 
	 * @author oz
	 * 
	 * **/
	@Override
	public void getOrderList(String user_id, int type, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		// params = new RequestParams();
		// params.addBodyParameter("m", "OrderList");
		// params.addBodyParameter("a", "orderList");
		// params.addBodyParameter("user_id", user_id);
		// params.addBodyParameter("type", type + "");
		// params.addBodyParameter("page", page + "");
		// params.addBodyParameter("number", number + "");
		// Xutils.getDataFromServer(params, iOAuthCallBack);

		String api = BaseUrl + "webUserOrder/orderList?" + "type=" + type + "&page=" + page + "&number=" + number;

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

	/*
	 * (非 Javadoc)
	 * <p>Title: toPrepay</p>
	 * <p>Description: 最新修改的生成订单的接口</p>
	 * @param order_bean
	 * @param iOAuthCallBack
	 * @throws Exception
	 * @see me.wangolf.service.IUserService#toPrepay(me.wangolf.bean.usercenter.
	 * OrderpayBean, me.wangolf.service.IOAuthCallBack)
	 */
	@Override
	public void toPrepay(OrderpayBean order_bean, IOAuthCallBack iOAuthCallBack) throws Exception
	{	
		params = new RequestParams();

		params.addBodyParameter("type", order_bean.getType());

		params.addBodyParameter("supplier_id", order_bean.getSupplier_id());

		params.addBodyParameter("mobile", order_bean.getMobiel());

		params.addBodyParameter("consumer_name", order_bean.getConsumer_name());

		params.addBodyParameter("consumer_num", order_bean.getConsumer_num() == 0 ? "" : String
				.valueOf(order_bean.getConsumer_num()));

		params.addBodyParameter("product_id", order_bean.getProduct_id() == 0 ? "" : String
				.valueOf(order_bean.getProduct_id()));

		params.addBodyParameter("order_amount", order_bean.getOrder_amount() == 0 ? "" : String
				.valueOf(order_bean.getOrder_amount().intValue()));

		params.addBodyParameter("book", order_bean.getBook());

		params.addBodyParameter("arrival_time", order_bean.getArrival_time());

		params.addBodyParameter("court_id", order_bean.getCourt_id());

		params.addBodyParameter("gender", order_bean.getGender());

		params.addBodyParameter("option", order_bean.getOption());

		params.addBodyParameter("address", order_bean.getAddress());

		params.addBodyParameter("zip", order_bean.getZip());

		params.addBodyParameter("product_attr", order_bean.getProduct_attr());

//		params.addBodyParameter("recommend_code", order_bean
//				.getRecommend_code());
//
//		params.addBodyParameter("recommend_amount", order_bean
//				.getRecommend_amount());
//
//		params.addBodyParameter("return_amount", order_bean.getReturn_amount());

		String api = BaseUrl + "webOrderPrepay/prepay";

		Xutils.getDataFromServer(api, params, iOAuthCallBack);

	}

	
	/* (非 Javadoc) 
	* <p>Title: topayExtra</p> 
	* <p>Description: 最新修改的修改订单添加余额的接口</p> 
	* @param out_trade_no
	* @param self_amount
	* @param order_amount
	* @param iOAuthCallBack
	* @throws Exception 
	* @see me.wangolf.service.IUserService#topayExtra(java.lang.String, java.lang.Double, java.lang.Double, me.wangolf.service.IOAuthCallBack) 
	*/
	@Override
	public void topayExtra(String out_trade_no, Double self_amount, Double order_amount, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();

		params.addBodyParameter("out_trade_no", out_trade_no);

		params.addBodyParameter("self_amount", self_amount.intValue() + "");

		params.addBodyParameter("order_amount", order_amount.intValue() + "");
		
		String api = BaseUrl + "webOrderPrepay/payExtra";

		Xutils.getDataFromServer(api, params, iOAuthCallBack);
	}

	@Override
	public void topayExtra(String sn, Double self_amount, String vouchers_sn, Double vouchers_amount, Double order_amount, String user_id, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();

		params.addBodyParameter("m", "OrderPrepay");

		params.addBodyParameter("a", "payExtra");

		params.addBodyParameter("sn", sn);

		params.addBodyParameter("self_amount", self_amount + "");

		params.addBodyParameter("vouchers_sn", vouchers_sn);

		params.addBodyParameter("vouchers_amount", vouchers_amount + "");

		params.addBodyParameter("order_amount", order_amount + "");
		params.addBodyParameter("user_id", user_id);

		String api = BaseUrl + "webOrderPrepay/payExtra";

		Xutils.getDataFromServer(api, params, iOAuthCallBack);

	}

	/**
	 * 
	 * toPay
	 * 
	 * 最新修改的用户支付完成回调的接口
	 * 
	 * @author oz
	 * 
	 * **/
	@Override
	public void toPay(String out_trade_no, String uid, String payment, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();

		params.addBodyParameter("payment", payment);

		params.addBodyParameter("out_trade_no", out_trade_no);

		String api = BaseUrl + "webOrderPayment/pay";

		Xutils.getDataFromServer(api, params, iOAuthCallBack);
	}

	/**
	 * 
	 * upAddrdata
	 * 
	 * 最新修改的修改地址的接口
	 * 
	 * **/
	@Override
	public void upAddrdata(AddressBean bean, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		// params.addBodyParameter("m", "UserInfo");
		// params.addBodyParameter("a", "updateaddr");
		// params.addBodyParameter("uid", bean.getUid());
		// params.addBodyParameter("aid", bean.getAid());
		// params.addBodyParameter("rid1", bean.getRid1());
		// params.addBodyParameter("rid2", bean.getRid2());
		// params.addBodyParameter("rid3", bean.getRid3());
		// params.addBodyParameter("rid4", bean.getRid4());
		// params.addBodyParameter("address", bean.getAddress());
		// params.addBodyParameter("mobile", bean.getMobile());
		// params.addBodyParameter("zip", bean.getZip());
		// params.addBodyParameter("consignee", bean.getConsignee());
		// params.addBodyParameter("type", bean.getType());

		params.addBodyParameter("province_id", bean.getRid2());

		params.addBodyParameter("city_id", bean.getRid3());

		params.addBodyParameter("area_id", bean.getRid4());

		params.addBodyParameter("address", bean.getAddress());

		params.addBodyParameter("mobile", bean.getMobile());

		params.addBodyParameter("type", bean.getType());

		params.addBodyParameter("zip", bean.getZip());

		params.addBodyParameter("consignee", bean.getConsignee());

		params.addBodyParameter("address_id", bean.getAid());

		String api = BaseUrl + "webUser/updateUserAddress";

		Xutils.getDataFromServer(api, params, iOAuthCallBack);

	}

	@Override
	public void deleteAddrdata(int aid, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "delAddr");
		params.addBodyParameter("aid", aid + "");
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	
	
	/**
	 * 
	 * getUserOption
	 * 
	 * 最新修改的用户注册开启推荐人跟验证码的接口
	 * 
	 * @author oz
	 * 
	 * **/
	@Override
	public void getUserOption(IOAuthCallBack iOAuthCallBack) throws Exception
	{
		String api = BaseUrl + "webUser/registOption?";

		Xutils.getDataFromServer(api, iOAuthCallBack);
	}

	@Override
	public void toCheckcode(String phone, String code, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "checkcode");
		params.addBodyParameter("phone", phone);
		params.addBodyParameter("code", code);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getCode(String phone, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "sendcode");
		params.addBodyParameter("phone", phone);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	/**
	 * 
	 * toCheckMobileRegist
	 * 
	 * 最新修改的注册时发送验证码的接口
	 * 
	 * **/
	@Override
	public void toCheckMobileRegist(String mobile, String type, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		// params = new RequestParams();
		// params.addBodyParameter("m", "UserInfo");
		// params.addBodyParameter("a", "checkMobileRegist");
		// params.addBodyParameter("mobile", mobile);
		// params.addBodyParameter("type", type);
		// Xutils.getDataFromServer(params, iOAuthCallBack);

		String api = BaseUrl + "webUser/registSendCode?" + "&mobile=" + mobile + "&type=" + type;

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

	/**
	 * 
	 * getResetPwdCode
	 * 
	 * 最新修改的重置密码时发送验证码的接口
	 * 
	 * **/
	@Override
	public void getResetPwdCode(String mobile, String type, IOAuthCallBack iOAuthCallBack) throws Exception
	{

		String api = BaseUrl + "webUser/resetPasswordSendCode?" + "mobile=" + mobile + "&type=" + type;

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

	@Override
	public void getResetPwdCode(String mobile, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "sendResetPwdCode");
		params.addBodyParameter("mobile", mobile);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	/**
	 * 
	 * sendSuggestion
	 * 
	 * 最新修改的意见反馈的接口
	 * 
	 * @author oz
	 * 
	 * **/
	@Override
	public void sendSuggestion(String content, String user_id, IOAuthCallBack iOAuthCallBack) throws Exception
	{

		params = new RequestParams();

		params.addBodyParameter("content", content);

		String api = BaseUrl + "webAbout/addSuggesstion";

		Xutils.getDataFromServer(api, params, iOAuthCallBack);

	}

	/**
	 * 
	 * getAbout
	 * 
	 * 最新修改的关于我们的接口
	 * 
	 * @author oz
	 * 
	 * **/
	@Override
	public void getAbout(String type, IOAuthCallBack iOAuthCallBack) throws Exception
	{

		// params = new RequestParams();
		// params.addBodyParameter("m", "About");
		// params.addBodyParameter("a", "index");
		// params.addBodyParameter("type", type);
		// Xutils.getDataFromServer(params, iOAuthCallBack);

		String api = BaseUrl + "webAbout/index?" + "type=" + type;

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

	/**
	 * 
	 * getConsumerDetail
	 * 
	 * 最新修改的消费明细的接口
	 * 
	 * **/
	@Override
	public void getConsumerDetail(String user_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		// params = new RequestParams();
		// params.addBodyParameter("m", "ConsumerDetail");
		// params.addBodyParameter("a", "detail");
		// params.addBodyParameter("user_id", user_id);
		// params.addBodyParameter("page", page + "");
		// params.addBodyParameter("number", number + "");
		// Xutils.getDataFromServer(params, iOAuthCallBack);

		String api = BaseUrl + "webUserOrder/consumeList?" + "page=" + page + "&number=" + number;

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

	@Override
	public void getVouchersList(String uid, String type, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Vouchers");
		params.addBodyParameter("a", "vouchersList");
		params.addBodyParameter("uid", uid);
		params.addBodyParameter("type", type);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void addVouchers(String uid, String sn, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Vouchers");
		params.addBodyParameter("a", "vouchersAdd");
		params.addBodyParameter("uid", uid);
		params.addBodyParameter("sn", sn);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getPacksList(String uid, String type, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "RecommendFriend");
		params.addBodyParameter("a", "packsList");
		params.addBodyParameter("uid", uid);
		// params.addBodyParameter("type", type);
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	/**
	 * 
	 * toOrderCancel
	 * 
	 * 最新修改的取消订单的接口
	 * 
	 * @author oz
	 * 
	 * **/
	@Override
	public void toOrderCancel(String out_trade_no, IOAuthCallBack iOAuthCallBack) throws Exception
	{

		params = new RequestParams();
		// params.addBodyParameter("m", "OrderList");
		// params.addBodyParameter("a", "orderCancel");
		params.addBodyParameter("out_trade_no", out_trade_no);

		String api = BaseUrl + "webUserOrder/orderCancel";
		
		Xutils.getDataFromServer(api, params, iOAuthCallBack);
	}

	@Override
	public void toEventJoin(String uid, String cid, String eid, String cusname, String sex, String option, String mobile, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "OrderEvent");
		params.addBodyParameter("a", "orEventJoin");
		params.addBodyParameter("uid", uid);
		params.addBodyParameter("cid", cid);
		params.addBodyParameter("eid", eid);
		params.addBodyParameter("cusname", cusname);
		params.addBodyParameter("sex", sex);
		params.addBodyParameter("option", option);
		params.addBodyParameter("mobile", mobile);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getMyPublishEvent(String user_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "OrderList");
		params.addBodyParameter("a", "myPublishEvent");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getVersion(IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "PhoneVersion");
		params.addBodyParameter("a", "index");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getWxAccessToken(String appid, String secret, String code, String grant_type, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + secret + "&code=" + code + "&grant_type=" + grant_type;
		Xutils.getDataFromServer(url, iOAuthCallBack);
	}

	@Override
	public void toWxLogin(String weixin_open_id, String weixin_avatar, String weixin_nick_name, String weixin_gender, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "weixinLogin");
		params.addBodyParameter("weixin_open_id", weixin_open_id);
		params.addBodyParameter("weixin_avatar", weixin_avatar);
		params.addBodyParameter("weixin_nick_name", weixin_nick_name);
		params.addBodyParameter("weixin_gender", weixin_gender);
		// Log.i("wangolf",
		// "http://www.wangolf.me/m.php?m=UserInfo&a=weixinLogin&terminal=1&weixin_open_id="
		// + weixin_open_id + "&weixin_avatar=" + weixin_avatar +
		// "&weixin_nick_name=" + weixin_nick_name + "&weixin_gender=" +
		// weixin_gender);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void toBindMobile(int user_id, String mobile, String code, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "bindMobile");
		params.addBodyParameter("user_id", user_id + "");
		params.addBodyParameter("mobile", mobile);
		params.addBodyParameter("code", code);
		Xutils.getDataFromServer(params, iOAuthCallBack);
		// Log.i("wangolf","http://www.wangolf.me/m.php?m=UserInfo&a=bindMobile&user_id="+user_id+"&terminal=1&mobile="+mobile+"&code="+code);
	}

	@Override
	public void bindWeixin(String user_id, String password, String weixin_open_id, String weixin_avatar, String weixin_nick_name, String weixin_gender, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "bindWeixin");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("password", password);
		params.addBodyParameter("weixin_open_id", weixin_open_id);
		params.addBodyParameter("weixin_avatar", weixin_avatar);
		params.addBodyParameter("weixin_nick_name", weixin_nick_name);
		params.addBodyParameter("weixin_gender", weixin_gender);
		// Log.i("wangolf",
		// "http://www.wangolf.me/m.php?m=UserInfo&a=weixinLogin&terminal=1&weixin_open_id="
		// + weixin_open_id + "&weixin_avatar=" + weixin_avatar +
		// "&weixin_nick_name=" + weixin_nick_name + "&weixin_gender=" +
		// weixin_gender);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void updateUserInfo(String user_id, String password, String my_intro, int gender, String nick_name, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();

		params.addBodyParameter("m", "UserInfo");

		params.addBodyParameter("a", "updateUserInfo");

		params.addBodyParameter("user_id", user_id);

		params.addBodyParameter("password", password);

		params.addBodyParameter("my_intro", my_intro);

		params.addBodyParameter("gender", gender + "");

		params.addBodyParameter("nick_name", nick_name);

		Xutils.getDataFromServer(params, iOAuthCallBack);
		// Log.i("wangolf",
		// "http://www.wangolf.me/m.php?m=Community&a=editInfo&user_id=" +
		// user_id +"&password=" + password + "&terminal=1&my_intro=" + my_intro
		// +
		// "&gender=" + gender + "&nick_name=" + nick_name);
	}

	/**
	 * 
	 * updateUserInfo
	 * 
	 * 最新修改的修改用户信息的接口
	 * 
	 * **/
	@Override
	public void updateUserInfo(String user_id, String nick_name, String my_intro, int gender, IOAuthCallBack iOAuthCallBack) throws Exception
	{

		String api = BaseUrl + "webUser/updateUserInfo";

		params = new RequestParams();

		params.addBodyParameter("user_id", user_id);

		params.addBodyParameter("nick_name", nick_name);

		params.addBodyParameter("my_intro", my_intro);

		params.addBodyParameter("gender", String.valueOf(gender));

		Xutils.getDataFromServer(api, params, iOAuthCallBack);

	}

	/**
	 * 
	 * deleteAddrdata
	 * 
	 * 最新修改的删除地址的接口
	 * 
	 * **/
	@Override
	public void deleteAddrdata(String user_id, String address_id, IOAuthCallBack iOAuthCallBack) throws Exception
	{

		String api = BaseUrl + "webUser/deleteUserAddress";

		params = new RequestParams();

		params.addBodyParameter("address_id", address_id);

		Xutils.getDataFromServer(api, params, iOAuthCallBack);

	}

	
	
	/* (非 Javadoc) 
	* <p>Title: payRecommend</p> 
	* <p>Description: 充值订单额外支付推荐码</p> 
	* @param out_trade_no 订单号
	* @param order_amount 订单金额
	* @param recommend_amount 推荐金额
	* @param recommend_code 推荐码
	* @param return_amount 返利金额
	* @param user_id 用户id
	* @param mobile 用户电话号码
	* @throws Exception 
	* @see me.wangolf.service.IUserService#payRecommend(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
	*/
	@Override
	public void payRecommend(String out_trade_no, String order_amount, String recommend_amount, String recommend_code, String return_amount, String user_id, String mobile,IOAuthCallBack iOAuthCallBack) throws Exception
	{
		
		String api = BaseUrl + "webOrderPrepay/payRecommend";

		params = new RequestParams();

		params.addBodyParameter("out_trade_no", out_trade_no);

		params.addBodyParameter("order_amount", order_amount);

		params.addBodyParameter("recommend_amount", recommend_amount);

		params.addBodyParameter("recommend_code", recommend_code);
			
		params.addBodyParameter("return_amount", return_amount);

		params.addBodyParameter("mobile", mobile);
		
		Xutils.getDataFromServer(api, params, iOAuthCallBack);
				
	}

	
	
	/* (非 Javadoc) 
	* <p>Title: enter</p> 
	* <p>Description: 进入应用界面时调用接口(在已获取imei号时调用)</p> 
	* @param iOAuthCallBack
	* @throws Exception 
	* @see me.wangolf.service.IUserService#enter(me.wangolf.service.IOAuthCallBack) 
	*/
	@Override
	public void enter(IOAuthCallBack iOAuthCallBack) throws Exception
	{
		String api = BaseUrl + "webTourist/enter?";
		
		Xutils.getDataFromServer(api, iOAuthCallBack);		
	}

	/* (非 Javadoc) 
	* <p>Title: start</p> 
	* <p>Description: 启动应用时调用接口(在未获取imei号时调用)</p> 
	* @param iOAuthCallBack
	* @throws Exception 
	* @see me.wangolf.service.IUserService#start(me.wangolf.service.IOAuthCallBack) 
	*/
	@Override
	public void start(IOAuthCallBack iOAuthCallBack) throws Exception
	{
		
		String api = BaseUrl + "webTourist/start?";
		
		Xutils.getDataFromServer(api, iOAuthCallBack);
		
	}

	
	/* (非 Javadoc) 
	* <p>Title: verifyRecommendCode</p> 
	* <p>Description: 验证推荐码</p> 
	* @param mobile
	* @param recommend_code
	* @param iOAuthCallBack
	* @throws Exception 
	* @see me.wangolf.service.IUserService#verifyRecommendCode(java.lang.String, java.lang.String, me.wangolf.service.IOAuthCallBack) 
	*/
	@Override
	public void verifyRecommendCode(String mobile, String recommend_code, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		String api = BaseUrl + "webRecharge/verifyRecommendCode?" +
				"mobile=" + mobile
				+"&recommend_code=" + recommend_code;
		
		Xutils.getDataFromServer(api, iOAuthCallBack);
		
	}

	/* (非 Javadoc) 
	* <p>Title: sendRecommendCode</p> 
	* <p>Description: 获取推荐码</p> 
	* @param mobile
	* @param iOAuthCallBack
	* @throws Exception 
	* @see me.wangolf.service.IUserService#sendRecommendCode(java.lang.String, me.wangolf.service.IOAuthCallBack) 
	*/
	@Override
	public void sendRecommendCode(String mobile, IOAuthCallBack iOAuthCallBack) throws Exception
	{		
		String api = BaseUrl + "webRecharge/sendRecommendCode?" +
				"mobile="+mobile;
		
		Xutils.getDataFromServer(api, iOAuthCallBack);
	}

	/* (非 Javadoc) 
	* <p>Title: getRechargeList</p> 
	* <p>Description: 获取充值列表</p> 
	* @param user_id
	* @param iOAuthCallBack
	* @throws Exception 
	* @see me.wangolf.service.IUserService#getRechargeList(java.lang.String, me.wangolf.service.IOAuthCallBack) 
	*/
	@Override
	public void getRechargeList(String user_id, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		
		String api = BaseUrl + "webRecharge/list?";
		
		Xutils.getDataFromServer(api, iOAuthCallBack);
		
	}

	


}
