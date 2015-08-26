package me.wangolf.bean.usercenter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月27日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月27日
 * 
 * 描述 ：用户短信礼包设置开关
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.ArrayList;

public class UserOptionEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long			serialVersionUID	= 1L;
	private String						status;
	private String						info;
	private ArrayList<UserOptionEntity>	data;
	private int							pack;						// 推荐人0开启1关闭
	private int							regist;						// 短信验证
																	// 0开启1关闭

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

	public ArrayList<UserOptionEntity> getData()
	{
		return data;
	}

	public void setData(ArrayList<UserOptionEntity> data)
	{
		this.data = data;
	}

	public int getPack()
	{
		return pack;
	}

	public void setPack(int pack)
	{
		this.pack = pack;
	}

	public int getRegist()
	{
		return regist;
	}

	public void setRegist(int regist)
	{
		this.regist = regist;
	}

}
