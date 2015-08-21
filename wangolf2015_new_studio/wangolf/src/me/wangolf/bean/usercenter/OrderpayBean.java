package me.wangolf.bean.usercenter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月21日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月21日
 * 
 * 描述 ：生成订单接口(新版) bean
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;

/**
 * 生成订单接口(新版) bean
 * 
 * @author Administrator
 * 
 */
public class OrderpayBean implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String				type				= "";	// 0练习场1球场2套餐3商品4活动,5开通会员,6充值
	private String				supplier_id			= "";	// 供应商ID(练习场无供应商ID可以不传)
	private String				user_id				= "";	// 用户ID
	private String				mobiel				= "";	// 电话号码
	private String				consumer_name		= "";	// 消费姓名
	private int					consumer_num;				// 消费数量(界面若无数量要求，默认传1)
	private int					product_id;					// 产品ID
	private Double				order_amount;				// 总价格
	private String				book				= "";	// 备注
	private String				arrival_time		= "";	// 到达时间(球场、活动需要，格式yyyy-MM-dd
															// HH:ii)其它类型填当前时间即可
	private String				court_id			= "";	// 球场/练习场ID(球场/练习场选填项)
	private String				gender				= "";	// 性别0女1男(活动选填项)
	private String				option				= "";	// 活动选填内容(活动选填项)
	private String				address				= "";	// 地址(购买商品选填项
	private String				zip					= "";	// 邮编(购买商品选填项)
	private String				product_attr		= "";	// 商品属性(购买商品选填项)
	private String				recommend_code		= "";
	private String				recommend_amount	= "";
	private String				return_amount		= "";   //返利金额

	@Override
	public String toString()
	{
		return "OrderpayBean [type=" + type + ", supplier_id=" + supplier_id + ", user_id=" + user_id + ", mobiel=" + mobiel + ", consumer_name=" + consumer_name + ", consumer_num=" + consumer_num + ", product_id=" + product_id + ", order_amount=" + order_amount + ", book=" + book + ", arrival_time=" + arrival_time + ", court_id=" + court_id + ", gender=" + gender + ", option=" + option + ", address=" + address + ", zip=" + zip + ", product_attr=" + product_attr + ", recommend_code=" + recommend_code + ", recommend_amount=" + recommend_amount + ", return_amount=" + return_amount + "]";
	}

	public String getRecommend_code()
	{
		return recommend_code;
	}

	public void setRecommend_code(String recommend_code)
	{
		this.recommend_code = recommend_code;
	}

	public String getRecommend_amount()
	{
		return recommend_amount;
	}

	public void setRecommend_amount(String recommend_amount)
	{
		this.recommend_amount = recommend_amount;
	}

	public String getReturn_amount()
	{
		return return_amount;
	}

	public void setReturn_amount(String return_amount)
	{
		this.return_amount = return_amount;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getSupplier_id()
	{
		return supplier_id;
	}

	public void setSupplier_id(String supplier_id)
	{
		this.supplier_id = supplier_id;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getMobiel()
	{
		return mobiel;
	}

	public void setMobiel(String mobiel)
	{
		this.mobiel = mobiel;
	}

	public String getConsumer_name()
	{
		return consumer_name;
	}

	public void setConsumer_name(String consumer_name)
	{
		this.consumer_name = consumer_name;
	}

	public int getConsumer_num()
	{
		return consumer_num;
	}

	public void setConsumer_num(int consumer_num)
	{
		this.consumer_num = consumer_num;
	}

	public int getProduct_id()
	{
		return product_id;
	}

	public void setProduct_id(int product_id)
	{
		this.product_id = product_id;
	}

	public Double getOrder_amount()
	{
		return order_amount;
	}

	public void setOrder_amount(Double order_amount)
	{
		this.order_amount = order_amount;
	}

	public String getBook()
	{
		return book;
	}

	public void setBook(String book)
	{
		this.book = book;
	}

	public String getArrival_time()
	{
		return arrival_time;
	}

	public void setArrival_time(String arrival_time)
	{
		this.arrival_time = arrival_time;
	}

	public String getCourt_id()
	{
		return court_id;
	}

	public void setCourt_id(String court_id)
	{
		this.court_id = court_id;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getOption()
	{
		return option;
	}

	public void setOption(String option)
	{
		this.option = option;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getProduct_attr()
	{
		return product_attr;
	}

	public void setProduct_attr(String product_attr)
	{
		this.product_attr = product_attr;
	}

}
