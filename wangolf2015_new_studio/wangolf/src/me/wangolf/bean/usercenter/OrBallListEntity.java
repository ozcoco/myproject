package me.wangolf.bean.usercenter;

import java.io.Serializable;
import java.util.List;

/**
 * 我的订单
 * 
 */
public class OrBallListEntity extends BaseOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String sn;// "1",//订单号
	private String status;// "1",//订单状态 status: "3",//1未支付3已付款7已取消9正在处理10已消费
	private String name;// "深圳西丽高尔夫",//球场名字
	private String arrival_time;// "1970-01-01 08:00:01",//开球时间
	private String single_time;// 下单时间
	private String expire_time;// /若未支付则是付款时间若已付款则是过期时间
	private String icon;// LOGO
	private String order_amount;//总价
	private String npeople;// "1"//预定人数
	private String service;// 18洞果岭/僮/车/柜"
	private String timecount;// 过期时间
	private String consumer_name;// 消费人姓名
	private String consumer_num;// 消费数量
	private String product_id;// 详情id”id
	private String range_code;//验证码
	private String book;// 备注
	private String qrcode_url;
	private String mobile;//
	private String info;
	private List<OrBallListEntity> data;
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	public String getSingle_time() {
		return single_time;
	}
	public void setSingle_time(String single_time) {
		this.single_time = single_time;
	}
	public String getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(String order_amount) {
		this.order_amount = order_amount;
	}
	public String getExpire_time() {
		return expire_time;
	}
	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getNpeople() {
		return npeople;
	}
	public void setNpeople(String npeople) {
		this.npeople = npeople;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getTimecount() {
		return timecount;
	}
	public void setTimecount(String timecount) {
		this.timecount = timecount;
	}
	public String getConsumer_name() {
		return consumer_name;
	}
	public void setConsumer_name(String consumer_name) {
		this.consumer_name = consumer_name;
	}
	public String getConsumer_num() {
		return consumer_num;
	}
	public void setConsumer_num(String consumer_num) {
		this.consumer_num = consumer_num;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<OrBallListEntity> getData() {
		return data;
	}
	public void setData(List<OrBallListEntity> data) {
		this.data = data;
	}
	public String getRange_code() {
		return range_code;
	}
	public void setRange_code(String range_code) {
		this.range_code = range_code;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;

	}

	public String getQrcode_url() {
		return qrcode_url;
	}

	public void setQrcode_url(String qrcode_url) {
		this.qrcode_url = qrcode_url;
	}
}
