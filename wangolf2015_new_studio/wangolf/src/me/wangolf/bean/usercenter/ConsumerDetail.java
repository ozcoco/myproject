package me.wangolf.bean.usercenter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月28日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月28日
 * 
 * 描述 ：消费明细实体
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class ConsumerDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	public String serial_number;// "4444444444",//流水号
	public String money;// 交易金额
	public String addtime;// 时间
	public String status;// 状态
	private String info;//
	private String content;
	private String modify;//”0”支出1充值
	private ArrayList<ConsumerDetail> data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public ArrayList<ConsumerDetail> getData() {
		return data;
	}

	public void setData(ArrayList<ConsumerDetail> data) {
		this.data = data;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}
	
}
