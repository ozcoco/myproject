package me.wangolf.bean;

import java.io.Serializable;

/**
 * 天气实体类
 * @author Administrator
 *
 */
public class WeatherEntity implements Serializable{

	private String city;//搜索的城市
	
	private String date;
	private String templer;
	private String weather;
	private String pic;
	private String wind;//风力
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTempler() {
		return templer;
	}
	public void setTempler(String templer) {
		this.templer = templer;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
}
