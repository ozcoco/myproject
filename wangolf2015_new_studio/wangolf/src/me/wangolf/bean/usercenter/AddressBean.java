package me.wangolf.bean.usercenter;
/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月26日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月26日
 * 
 * 描述 ：
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;

public class AddressBean implements Serializable {

	/**
	 * 修改地址bean
	 */
	private static final long serialVersionUID = 1L;
	private String uid;// 用户ID
	private String aid;// 地址ID(若更新则填写地址ID,新增则为空)
	private String rid1;// 所属国家
	private String rid2;// 所属省份
	private String rid3;// 所属市
	private String rid4;// 所属区/县
	private String address;// 具体地址
	private String mobile;// 联系号码
	private String zip;// 联系邮编
	private String consignee;// 联系人
	private String type;// 接收人默认地址(0代表不是默认地址,1代表默认地址)

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}




	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getRid1() {
		return rid1;
	}

	public void setRid1(String rid1) {
		this.rid1 = rid1;
	}

	public String getRid2() {
		return rid2;
	}

	public void setRid2(String rid2) {
		this.rid2 = rid2;
	}

	public String getRid3() {
		return rid3;
	}

	public void setRid3(String rid3) {
		this.rid3 = rid3;
	}



	public String getRid4() {
		return rid4;
	}

	public void setRid4(String rid4) {
		this.rid4 = rid4;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
