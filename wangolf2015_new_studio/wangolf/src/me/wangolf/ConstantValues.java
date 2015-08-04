package me.wangolf;

public class ConstantValues {
	public static final int ERROR = 100;
	public static final String ENCODING = "utf-8";
	public static final String IMAGE_PATH = null;
	public static int SUCCESS = 200;
	public static String CITYNAME;// 初始化城市名称
	public static int CITYID;// 初始化城市名称
	// ==================全局=============
	public static String versionCode;
	public static String WX_UID="";
	public static String UID="";
	public static String OPEN_ID="";
	public static String PASSWORD="";
	public static boolean ISLOGIN;
	public static boolean ISWXlOGIN;
	public static boolean ISCOMPLETEINFO;
	public static boolean HOME_ISLOGIN;
	public static boolean USERCENT_ISLOGIN;
	public static String USER_MOBILE;
	public static String UUID = "";
	public static boolean newsTags;
	public static boolean ballTags;
	public static int SCREENWIDTH;
	public static int SCREENHEIGHT;
	public static final int ADDRESS_CODE = 11;
	public static final int CITY_CODE = 12;
	public static final int dateCode = 13;
	public static final int timeCode = 14;
	public static final int nameCode = 15;
	public static final int NEWSTAGS = 16;
	public static final int ORDERPRAC = 17;
	public static final int USERLOGIN = 18;
	public static final int USERREGIST = 19;
	public static final int VOUCHERSCODE = 20;
	public static final String SORT = "排序";
	public static final String SHARE = "分享";
	public static final String TEL_PHONE = "13302311999";
	public static String LATITUDE = "22.556715"; // 纬度
	public static String LONGITUDE = "114.051021";// 经度
	public static String loactionadd = "深圳";
	public static boolean isloaction;
	// ===================写死的标题啊=======================
	// ---------学院相关-------------
	public static final String[] COLLEGE_TITLE = { "装备", "技巧", "教程", "视频", "教练", "学院" };
	public static final String COLLEGE = "高球教学";
	public static final String CONTENT_TITLE = "图文详情";
	public static final String COACH_TITLE = "教练资料";
	public static final String COLLEGE_INFO_TITLE = "学院资料";
	public static final String COLLEGE_ID = "暂无数据";

	public static String[] title_name ;
	public static Integer[] title_id ;
	public static int title_size;

    public static String[] shop_title_name ;
    public static Integer[] shop_title_id ;
    public static int shop_title_size;

    public static String[] knoledge_title_name ;
    public static Integer[]  knoledge_title_id ;
    public static int  knoledge_title_size;
	// ===================xutils失败=======================
	public static final String FAILURE = "failure";
	public static final String NONETWORK = "无网络，请检查网络连接！";
	public static final String NOMORE = "已无更多数据!";
	// ---------商城相关-------------
	public static final String[] SHOP_TITLE_S = { "球具", "服饰", "配件", "球票", "球旅", "礼包" };
	public static final String SHOP_TITLE = "高尔夫商城";
	public static final String SHOP_IMG = "图片浏览";
	public static final String SHOP_COMTENT_TITLE = "商品详情";
	public static final String SHOP_IMG_TITLE = "图文详情";
	public static final String SHOP_ATRRINFO = "填写订单";
	public static final String SHOP_ATRRINFO_PHONE = "电话";

	// ---------个人中心-------------
	public static final String ONLINEPLAY = "在线支付";
	public static final String ONLINEPLAY_PHONE = "电话";
	public static final String USER_LOGIN = "登录";
	public static final String MY_ORDER = "我的订单";
	public static final String NEWADDRESS = "新增地址";
	public static final String ADDRESS = "地址管理";
	public static final String SAVEADDRESS = "保存";
	public static final String REGIST = "注册";
	public static final String GETPWD = "找回密码";
	public static final String PROTOCOL = "注册协议";
	public static final String USERCENTER = "个人中心";
	public static final String USERSET = "设置";
	public static final String USERCOMMENT = "意见反馈";
	public static final String COOPERATION = "招商合作";
	public static final String ABOUT = "关于我们";
	public static final String USERACCOUNT = "我的账户";
	public static final String VOUCHERS = "我的代金券";
	public static final String USERCONSUMER = "消费明细";
	public static final String USERRECHARGE = "账户充值";
	public static final String USERREDPACK = "我的红包";
	public static final String ORDERINFO = "订单详情";
	public static boolean ISTOPAY;// 订单中心判断去支付回来的状态

	// ---------练习场-------------
	public static final String PRACTICE_TITLE = "练习场";
	public static final String SELECT_CITY = "请选择城市";
	public static final String PRACTICE_LOCATION = "当前位置";
	public static final String PRACTICE_TAG = "详情";
	public static final String PRACTICE_ORDER = "填写订单";

	// ---------球场-------------
	public static final String BALLSEARCH_TITLE = "球场预定";
	public static final String BALLSEARCH_BT = "常打";
	public static final String BALLLIST_TITLE = "当前位置";
	public static final String BALLLIST_BT = "地图";
	public static final String BALLLIST_INFO = "球场信息";
	public static String orderdate;
	// ---------活动-------------
	public static final String EVENT_TITLE = "活动首页";
	public static final String EVENT_ISSUE = "发布活动";
	public static final String EVENT_DETAIL = "活动详情";
	public static final String EVENT_PERSON = "报名人数";

	// -------------------球场搜索暂用---------
	public static String cityname = null;
	public static int cityid;
	public static String ballname = null;
	public static String dateStr = null;
	public static String showDate = null;
	public static String balltime = null;
	public static boolean ballcitytag;
	// 社区相关
	public static boolean isSendPosts;

}