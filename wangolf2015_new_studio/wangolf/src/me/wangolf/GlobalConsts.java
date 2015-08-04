package me.wangolf;
public class GlobalConsts {
	////http://192.168.1.106/m.php?m=Login&a=login&&username=%E6%99%A8%E6%99%A8&password=123456
	//public static final String BASE_URL = "http://192.168.1.222/m.php";// url地址
   public static final String BASE_URL ="http://www.wangolf.me/m.php";//url地址
	 //public static final String BASE_URL ="http://mgolfid.xicp.net:43609/m.php";
	//url地址
	// public static final String BASE_URL ="http://192.168.1.239/m.php";//url地址
	/**
	 * 服务器银联获取tn地址
	 */
	public static final String YinLianURL = BASE_URL.replace("m.php", "yinlian/purchase.php");
	/**
	 * 服务器微信支付地址
	 */
	public static final String WeiXinURL = BASE_URL.replace("m.php", "weixin/index.php");
	/**
	 * 注册协议地址
	 */
	public static final String RegistURL = BASE_URL.replace("m.php", "register_explain.html");
	// 支付失败发送消息到后台的URL
	// http://www.wangolf.me/yinlian/query.php?orderid=1408041737378691&uid=79
	public static final String YinLianFialURL = BASE_URL.replace("m.php", "yinlian/query.php");
	/**
	 * 分享的apk下载地址 这是默认的下载地址 如果MainActivity取得有微信的子地址将其赋值为取来的地址
	 */
	public static String DOWN_APK_URL = BASE_URL.replace("m.php", "download/golf/");
	public static final String DOWN_APK_IMAGE_URL = BASE_URL.replace("m.php", "public/avatar/mgolf_2014_08_12_logo.jpg");
	// public static final String DOWN_APK_IMAGE_URL =
	// "http://www.wangolf.me/public/avatar/mgolf_2014_08_12_logo.jpg";

	/*****************************************************************
	 * mMode参数解释： "00" - 启动银联正式环境 "01" - 连接银联测试环境
	 *****************************************************************/
	public static final String mMode = "00";
	public static final int RQF_PAY = 1;
	public static final int RQF_LOGIN = 2;
	public static final int PLUGIN_VALID = 0;
	public static final int PLUGIN_NOT_INSTALLED = -1;
	public static final int PLUGIN_NEED_UPGRADE = 2;

	// TODO 保存练习场的城市
	public static String praccity = "";

	/**
	 * 支付宝notify_url
	 */
	public static final String notify_url = "http://www.wangolf.me/alipay/notify_url.php";

	public static final int MSG_WHAT_PROGRESS_UPDATE = 1000;
	public static final int MAX_SIZE_PER_PAGE = 10;
	public static final int WHAT_NODATA = 0;
	public static final int WHAT_FIRST = 22;
	public static final int WHAT_MORE = 23;
	public static final int WHAT_SUCCESS = 200;
	public static final int WHAT_EXCEPTION = 500;
	public static final int WHAT_GET_CHECKNUM_SUCCESS = 2000;
	public static final int WHAT_GET_CHECKNUM_EXCEPTION = 5000;
	public static final int WHAT_TIMEOUT = -1;
	public static final int WHAT_FAILL = 0;

	public static final int cityCode = 0;
	public static final int dateCode = 1;
	public static final int timeCode = 2;
	public static final int ballCode = 3;
	public static final int frindCode = 4;
	public static final int priceCode = 5;
	public static final int remakersCode = 6;
	public static final int vouchersCode = 7;// 代金卷
	public static final int addVoucherCode = 8;// 添加代金卷
	public static final int proCityAreaCode = 9;// 省市区
	public static final int requestDateCode = 10;
	public static final int requestBackCode = -1;// 用户点击返回的和不正常的操作
	public static final int addressCode = 11;
	public static final int blankCode = 12;// 银行
	public static final int mainLogin = 13;
	public static final int eventYMDCode = 14;

	/**
	 * 电话号码 暂时未填写
	 */
	public static final String phoneNum = "";

	/**
	 * 订单类型
	 */
	public static final String ordertype = "type";
	public static final String Order_Ball = "ball";
	public static final String Order_Package_Ball = "ballpackage";// 球场套餐
	public static final String Order_Practice = "practice";// 练习场
	public static final String Order_event = "event";
	public static final String Order_preferbuy = "preferbuy";
	public static final String Order_Chonzhi = "chongzhi";// 充值

	/**
	 * 红包值dialog
	 */
	public static final int redPackDialogCode = 16;// 支付成功红包dialog

	public static String cityid = null;
	public static String dateTime;// 打球时间
	public static String BALL_SEACHDATE;
	public static String BALL_SHOW_DATE;// 显示的时间
	public static String BALL_SEACHTIME;
	public static String BALL_SEACHCITY;
	public static boolean ISLOGIN;
	public static String PHONE = "";
	public static String PWD = "";
	public static int UID;

	public static class Config {
		public static final boolean DEVELOPER_MODE = false;
	}

	public static class Extra {
		public static final String IMAGES = "com.nostra13.example.universalimageloader.IMAGES";
		public static final String IMAGE_POSITION = "com.nostra13.example.universalimageloader.IMAGE_POSITION";
	}

}
