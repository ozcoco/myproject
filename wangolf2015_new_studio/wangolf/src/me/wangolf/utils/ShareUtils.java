package me.wangolf.utils;

import me.wangolf.GlobalConsts;

import android.content.Context;
import android.util.Log;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.meigao.mgolf.R;

public class ShareUtils
{
	public static void showShare(String sharetitle, Context context, String picfile)
	{
		ShareSDK.initSDK(context);
		
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字
		oks.setNotification(R.drawable.logo_new, context
				.getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(context.getString(R.string.share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(GlobalConsts.DOWN_APK_URL);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(sharetitle + "  " + "___优惠尽在【打球App】.");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		oks.setImagePath("/sdcard/wangolf/" + picfile);// 确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(GlobalConsts.DOWN_APK_URL);
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(context.getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl(GlobalConsts.DOWN_APK_URL);

		// 启动分享GUI
		oks.show(context);
	}

	public static void showShareandUrl(String sharetitle, String url, Context context, String picfile)
	{
		ShareSDK.initSDK(context);
		
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		// 分享时Notification的图标和文字
		oks.setNotification(R.drawable.logo_new, context
				.getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(CheckUtils.checkEmpty(sharetitle) ? context
				.getString(R.string.share) : sharetitle);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(CheckUtils.checkEmpty(url) ? GlobalConsts.DOWN_APK_URL : url);

		// text是分享文本，所有平台都需要这个字段
		oks.setText(sharetitle + "  ");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// Log.i("wangolf",picfile+"picfile");
		if (!CheckUtils.checkEmpty(picfile))
		{
			oks.setImagePath("/sdcard/wangolf/" + picfile);// 确保SDcard下面存在此张图片
		}
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(CheckUtils.checkEmpty(url) ? GlobalConsts.DOWN_APK_URL : url);
		LogUtils.i(url);
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(context.getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl(GlobalConsts.DOWN_APK_URL);

		// 启动分享GUI
		oks.show(context);
	}

	public static void showShareandUrl(String sharetitle, String content, String url, Context context, String picfile)
	{
		ShareSDK.initSDK(context);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字
		oks.setNotification(R.drawable.ic_launcher, context
				.getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(CheckUtils.checkEmpty(sharetitle) ? context
				.getString(R.string.share) : sharetitle);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(CheckUtils.checkEmpty(url) ? GlobalConsts.DOWN_APK_URL : url);

		// text是分享文本，所有平台都需要这个字段
		oks.setText(content);
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// Log.i("wangolf",picfile+"picfile");
		if (!CheckUtils.checkEmpty(picfile))
		{
			oks.setImagePath("/sdcard/wangolf/" + picfile);// 确保SDcard下面存在此张图片
		}

		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(CheckUtils.checkEmpty(url) ? GlobalConsts.DOWN_APK_URL : url);
		LogUtils.i(url);
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(context.getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl(GlobalConsts.DOWN_APK_URL);

		// 启动分享GUI
		oks.show(context);
	}
}
