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
import android.util.Log;

import java.io.File;

import com.lidroid.xutils.http.RequestParams;

import me.wangolf.bean.usercenter.AddressBean;
import me.wangolf.bean.usercenter.OrderpayBean;
import me.wangolf.bean.usercenter.User;
import me.wangolf.bean.usercenter.UserInfoEntity;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.service.IUserService;
import me.wangolf.utils.Xutils;

public class UserServiceImpl implements IUserService {
	public RequestParams params = null;
	public String result = null;
	public IOAuthCallBack iOAuthCallBack;

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public void UserLogin(User user, IOAuthCallBack iOAuthCallBack) {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "login");
		params.addBodyParameter("username", user.getUsername());
		params.addBodyParameter("password", user.getPassword());
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void UserLogout(String uid, IOAuthCallBack iOAuthCallBack) {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "logout");
		params.addBodyParameter("uid", uid);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getUserInfo(String uid, IOAuthCallBack iOAuthCallBack) {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "userinfo");
		params.addBodyParameter("uid", uid);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void upUserInfo(UserInfoEntity userinfo, IOAuthCallBack iOAuthCallBack) {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "updateinfo");
		params.addBodyParameter("uid", userinfo.getUid() + "");
		params.addBodyParameter("nickname", userinfo.getNickname());
		params.addBodyParameter("sex", userinfo.getSex());
		params.addBodyParameter("photo", userinfo.getPhoto());
		params.addBodyParameter("birthday", userinfo.getBirthday());
		params.addBodyParameter("ballage", userinfo.getBallage() + "");
		params.addBodyParameter("summy", userinfo.getSummy());
		params.addBodyParameter("ballage", userinfo.getBallage() + "");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void upLoad(String uid, String path, IOAuthCallBack iOAuthCallBack) {
		params = new RequestParams();
		params.addBodyParameter("m", "UploadImage");
		params.addBodyParameter("a", "upload");
		params.addBodyParameter("uid", uid);
		params.addBodyParameter("avatar_file", new File(path));
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void upUserPassword(String uid, String opassword, String npassword, IOAuthCallBack iOAuthCallBack) {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "updatepwd");
		params.addBodyParameter("uid", uid);
		params.addBodyParameter("opassword", opassword);
		params.addBodyParameter("npassword", npassword);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void doResetPwd(String phone, String password, String code, IOAuthCallBack iOAuthCallBack) {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "resetPasswd");
		params.addBodyParameter("mobile", phone);
		params.addBodyParameter("password", password);
		params.addBodyParameter("code", code);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void doRegist(String mobile, String password, String code, String recommend, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "signUp");
		params.addBodyParameter("mobile", mobile);
		params.addBodyParameter("password", password);
		params.addBodyParameter("code", code);
		params.addBodyParameter("recommend", recommend);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void UpBindMobile(String uid, String mobile, IOAuthCallBack iOAuthCallBack) {
		params = new RequestParams();
		params.addBodyParameter("uid", uid);
		params.addBodyParameter("mobile", mobile);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void UpBindUserName(String uid, String username, IOAuthCallBack iOAuthCallBack) {
		params = new RequestParams();
		params.addBodyParameter("uid", uid);
		params.addBodyParameter("username", username);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getUserAddr(String uid, IOAuthCallBack iOAuthCallBack) {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "useraddr");
		params.addBodyParameter("uid", uid);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getOrderList(String user_id, int type, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "OrderList");
		params.addBodyParameter("a", "orderList");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("type", type + "");
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void toPrepay(OrderpayBean order_bean, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "OrderPrepay");
		params.addBodyParameter("a", "prepay");
		params.addBodyParameter("type", order_bean.getType());
		params.addBodyParameter("supplier_id", order_bean.getSupplier_id());
		params.addBodyParameter("user_id", order_bean.getUser_id());
		params.addBodyParameter("mobile", order_bean.getMobiel());
		params.addBodyParameter("consumer_name", order_bean.getConsumer_name());
		params.addBodyParameter("consumer_num", order_bean.getConsumer_num() + "");
		params.addBodyParameter("product_id", order_bean.getProduct_id() + "");
		params.addBodyParameter("order_amount", order_bean.getOrder_amount() + "");
		params.addBodyParameter("book", order_bean.getBook());
		params.addBodyParameter("arrival_time", order_bean.getArrival_time());
		params.addBodyParameter("court_id", order_bean.getCourt_id());
		params.addBodyParameter("gender", order_bean.getGender());
		params.addBodyParameter("option", order_bean.getOption());
		params.addBodyParameter("address", order_bean.getAddress());
		params.addBodyParameter("zip", order_bean.getZip());
		params.addBodyParameter("product_attr", order_bean.getProduct_attr());
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void topayExtra(String sn, Double self_amount, String vouchers_sn, Double vouchers_amount, Double order_amount, String user_id,
			IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "OrderPrepay");
		params.addBodyParameter("a", "payExtra");
		params.addBodyParameter("sn", sn);
		params.addBodyParameter("self_amount", self_amount + "");
		params.addBodyParameter("vouchers_sn", vouchers_sn);
		params.addBodyParameter("vouchers_amount", vouchers_amount + "");
		params.addBodyParameter("order_amount", order_amount + "");
		params.addBodyParameter("user_id", user_id);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void toPay(String orderid, String uid, String payment, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "OrderPayment");
		params.addBodyParameter("a", "pay");
		params.addBodyParameter("uid", uid);
		params.addBodyParameter("payment", payment);
		params.addBodyParameter("orderid", orderid);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void upAddrdata(AddressBean bean, IOAuthCallBack iOAuthCallBack) throws Exception {

		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "updateaddr");
		params.addBodyParameter("uid", bean.getUid());
		params.addBodyParameter("aid", bean.getAid());
		params.addBodyParameter("rid1", bean.getRid1());
		params.addBodyParameter("rid2", bean.getRid2());
		params.addBodyParameter("rid3", bean.getRid3());
		params.addBodyParameter("rid4", bean.getRid4());
		params.addBodyParameter("address", bean.getAddress());
		params.addBodyParameter("mobile", bean.getMobile());
		params.addBodyParameter("zip", bean.getZip());
		params.addBodyParameter("consignee", bean.getConsignee());
		params.addBodyParameter("type", bean.getType());
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void deleteAddrdata(int aid, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "delAddr");
		params.addBodyParameter("aid", aid + "");
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getUserOption(IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "UserOption");
		params.addBodyParameter("a", "userOptionValue");
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void toCheckcode(String phone, String code, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "checkcode");
		params.addBodyParameter("phone", phone);
		params.addBodyParameter("code", code);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getCode(String phone, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "sendcode");
		params.addBodyParameter("phone", phone);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void toCheckMobileRegist(String mobile,String type, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "checkMobileRegist");
		params.addBodyParameter("mobile", mobile);
		params.addBodyParameter("type", type);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getResetPwdCode(String mobile, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "sendResetPwdCode");
		params.addBodyParameter("mobile", mobile);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void sendSuggestion(String content, String uid, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "About");
		params.addBodyParameter("a", "suggesstion");
		params.addBodyParameter("content", content);
		params.addBodyParameter("uid", uid);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getAbout(String type, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "About");
		params.addBodyParameter("a", "index");
		params.addBodyParameter("type", type);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getConsumerDetail(String user_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "ConsumerDetail");
		params.addBodyParameter("a", "detail");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getVouchersList(String uid, String type, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Vouchers");
		params.addBodyParameter("a", "vouchersList");
		params.addBodyParameter("uid", uid);
		params.addBodyParameter("type", type);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void addVouchers(String uid, String sn, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "Vouchers");
		params.addBodyParameter("a", "vouchersAdd");
		params.addBodyParameter("uid", uid);
		params.addBodyParameter("sn", sn);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getPacksList(String uid, String type, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "RecommendFriend");
		params.addBodyParameter("a", "packsList");
		params.addBodyParameter("uid", uid);
		//params.addBodyParameter("type", type);
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void toOrderCancel(String sn, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "OrderList");
		params.addBodyParameter("a", "orderCancel");
		params.addBodyParameter("sn", sn);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void toEventJoin(String uid, String cid, String eid, String cusname, String sex, String option, String mobile,
			IOAuthCallBack iOAuthCallBack) throws Exception {
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
	public void getMyPublishEvent(String user_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "OrderList");
		params.addBodyParameter("a", "myPublishEvent");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("page", page + "");
		params.addBodyParameter("number", number + "");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getVersion(IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "PhoneVersion");
		params.addBodyParameter("a", "index");
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getWxAccessToken(String appid, String secret, String code, String grant_type, IOAuthCallBack iOAuthCallBack) throws Exception {
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type="+grant_type;
		Xutils.getDataFromServer(url, iOAuthCallBack);
	}

	@Override
	public void toWxLogin(String weixin_open_id, String weixin_avatar, String weixin_nick_name, String weixin_gender, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "weixinLogin");
		params.addBodyParameter("weixin_open_id", weixin_open_id);
		params.addBodyParameter("weixin_avatar", weixin_avatar);
		params.addBodyParameter("weixin_nick_name", weixin_nick_name);
		params.addBodyParameter("weixin_gender", weixin_gender);
		//Log.i("wangolf", "http://www.wangolf.me/m.php?m=UserInfo&a=weixinLogin&terminal=1&weixin_open_id=" + weixin_open_id + "&weixin_avatar=" + weixin_avatar + "&weixin_nick_name=" + weixin_nick_name + "&weixin_gender=" + weixin_gender);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void toBindMobile(int user_id, String mobile, String code, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "bindMobile");
		params.addBodyParameter("user_id", user_id+"");
		params.addBodyParameter("mobile", mobile);
		params.addBodyParameter("code", code);
		Xutils.getDataFromServer(params, iOAuthCallBack);
		//Log.i("wangolf","http://www.wangolf.me/m.php?m=UserInfo&a=bindMobile&user_id="+user_id+"&terminal=1&mobile="+mobile+"&code="+code);
	}

	@Override
	public void bindWeixin(String user_id, String password, String weixin_open_id, String weixin_avatar, String weixin_nick_name, String weixin_gender, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "bindWeixin");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("password", password);
		params.addBodyParameter("weixin_open_id", weixin_open_id);
		params.addBodyParameter("weixin_avatar", weixin_avatar);
		params.addBodyParameter("weixin_nick_name", weixin_nick_name);
		params.addBodyParameter("weixin_gender", weixin_gender);
		//Log.i("wangolf", "http://www.wangolf.me/m.php?m=UserInfo&a=weixinLogin&terminal=1&weixin_open_id=" + weixin_open_id + "&weixin_avatar=" + weixin_avatar + "&weixin_nick_name=" + weixin_nick_name + "&weixin_gender=" + weixin_gender);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void updateUserInfo(String user_id,String password, String my_intro, int gender, String nick_name, IOAuthCallBack iOAuthCallBack) throws Exception {
		params = new RequestParams();
		params.addBodyParameter("m", "UserInfo");
		params.addBodyParameter("a", "updateUserInfo");
		params.addBodyParameter("user_id", user_id);
		params.addBodyParameter("password", password);
		params.addBodyParameter("my_intro", my_intro);
		params.addBodyParameter("gender", gender + "");
		params.addBodyParameter("nick_name", nick_name);
		Xutils.getDataFromServer(params, iOAuthCallBack);
		//Log.i("wangolf", "http://www.wangolf.me/m.php?m=Community&a=editInfo&user_id=" + user_id +"&password=" + password + "&terminal=1&my_intro=" + my_intro +
			//"&gender=" + gender + "&nick_name=" + nick_name);
	}
}