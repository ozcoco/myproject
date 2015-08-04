package com.meigao.mgolf.wxapi;

import me.wangolf.usercenter.OrderScuessActivity;
import me.wangolf.utils.ToastUtils;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * 微信支付后自动调用Activity
 * 
 * @author Administrator
 * 
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		//Log.i("wangolf",MyWeiPayUtils.Code+"code");
		// 判断来之那个界面的支付
		if (MyWeiPayUtils.from == null) {
			if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
				if (BaseResp.ErrCode.ERR_OK == resp.errCode) {
					ToastUtils.showInfo(WXPayEntryActivity.this, "支付成功");
					// TODO 支付成功
					Intent intent = new Intent(WXPayEntryActivity.this, OrderScuessActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.putExtra("title", "支付成功");
					intent.putExtra("payment", MyWeiPayUtils.payment);
					intent.putExtra("sn", MyWeiPayUtils.sn);
					intent.putExtra("type", MyWeiPayUtils.type+"");
					intent.putExtra("flag", MyWeiPayUtils.flag);
					intent.putExtra("message", "您的订单号(" + MyWeiPayUtils.sn + ")已支付成功，请到个人中心我的订单查看！");
					startActivity(intent);
					finish();

				} else if (BaseResp.ErrCode.ERR_USER_CANCEL == resp.errCode) {
					ToastUtils.showInfo(WXPayEntryActivity.this, "用户已取消");
					Intent intent = new Intent(WXPayEntryActivity.this, OrderScuessActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.putExtra("title", "支付已取消");
					intent.putExtra("sn", MyWeiPayUtils.sn);
					intent.putExtra("type", MyWeiPayUtils.type+"");
					intent.putExtra("flag", MyWeiPayUtils.flag);
					intent.putExtra("message", "您的订单号(" + MyWeiPayUtils.sn + ")已取消支付。");
					startActivity(intent);
					finish();
				} else if (BaseResp.ErrCode.ERR_SENT_FAILED == resp.errCode) {
					ToastUtils.showInfo(WXPayEntryActivity.this, "发送失败");
					Intent intent = new Intent(WXPayEntryActivity.this, OrderScuessActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.putExtra("title", "支付失败");
					intent.putExtra("sn", MyWeiPayUtils.sn);
					intent.putExtra("type", MyWeiPayUtils.type+"");
					intent.putExtra("flag", MyWeiPayUtils.flag);
					intent.putExtra("message", "您的订单号(" + MyWeiPayUtils.sn + ")已支付失败。");
					startActivity(intent);
					finish();
				} else if (BaseResp.ErrCode.ERR_COMM == resp.errCode) {
					ToastUtils.showInfo(WXPayEntryActivity.this, "一般错误,请重新安装最新版本的微信");
					Intent intent = new Intent(WXPayEntryActivity.this, OrderScuessActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.putExtra("title", "支付失败");
					intent.putExtra("sn", MyWeiPayUtils.sn);
					intent.putExtra("type", MyWeiPayUtils.type+"");
					intent.putExtra("flag", MyWeiPayUtils.flag);
					intent.putExtra("message", "您的订单号(" + MyWeiPayUtils.sn + ")已支付失败。");
					startActivity(intent);
					finish();
				}
			}
			/** 个人中心的开通会员界面 **/
		}

		// else
		// if(UserCenterActivity.class.getSimpleName().equals(MyWeiPayUtils.from)){
		// if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
		// if(BaseResp.ErrCode.ERR_OK==resp.errCode){
		// ToastUtils.showInfo(WXPayEntryActivity.this, "支付成功");
		// // TODO 支付成功
		// Intent intent=new
		// Intent(WXPayEntryActivity.this,OptionScucessOpenMemberActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// intent.putExtra("title", "支付成功");
		// intent.putExtra("payment", MyWeiPayUtils.payment);
		// intent.putExtra("sn", MyWeiPayUtils.sn);
		// intent.putExtra("message",
		// "您的订单号("+MyWeiPayUtils.sn+")已支付成功，请到个人中心的会员中心查看！");
		// intent.putExtra("from", UserCenterActivity.class.getSimpleName());
		// intent.putExtra("rgid", MyWeiPayUtils.rgid);
		// startActivity(intent);
		// finish();
		//
		// }else if(BaseResp.ErrCode.ERR_USER_CANCEL==resp.errCode){
		// ToastUtils.showInfo(WXPayEntryActivity.this, "用户已取消");
		// Intent intent=new
		// Intent(WXPayEntryActivity.this,OptionScucessOpenMemberActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// intent.putExtra("title", "支付已取消");
		// intent.putExtra("sn", MyWeiPayUtils.sn);
		// intent.putExtra("message", "您的订单号("+MyWeiPayUtils.sn+")已取消支付。");
		// intent.putExtra("from", UserCenterActivity.class.getSimpleName());
		// startActivity(intent);
		// finish();
		// }else if(BaseResp.ErrCode.ERR_SENT_FAILED ==resp.errCode){
		// ToastUtils.showInfo(WXPayEntryActivity.this, "发送失败");
		// Intent intent=new
		// Intent(WXPayEntryActivity.this,OptionScucessOpenMemberActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// intent.putExtra("title", "支付失败");
		// intent.putExtra("sn", MyWeiPayUtils.sn);
		// intent.putExtra("message", "您的订单号("+MyWeiPayUtils.sn+")已支付失败。");
		// intent.putExtra("from", UserCenterActivity.class.getSimpleName());
		// startActivity(intent);
		// finish();
		// }else if(BaseResp.ErrCode.ERR_COMM ==resp.errCode){
		// ToastUtils.showInfo(WXPayEntryActivity.this, "一般错误,请重新安装最新版本的微信");
		// Intent intent=new
		// Intent(WXPayEntryActivity.this,OptionScucessOpenMemberActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// intent.putExtra("title", "支付失败");
		// intent.putExtra("sn", MyWeiPayUtils.sn);
		// intent.putExtra("message", "您的订单号("+MyWeiPayUtils.sn+")已支付失败。");
		// intent.putExtra("from", UserCenterActivity.class.getSimpleName());
		// startActivity(intent);
		// finish();
		// }
		// }
		// /**练习场的开通会员界面**/
		// }

	}
}