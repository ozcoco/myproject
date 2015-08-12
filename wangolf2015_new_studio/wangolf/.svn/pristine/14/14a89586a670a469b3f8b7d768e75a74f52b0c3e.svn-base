package me.wangolf.dao;


import java.util.ArrayList;
import java.util.HashMap;

import me.wangolf.bean.CityEntity;
import me.wangolf.utils.CheckUtils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 未使用
 *
 */
public class CityDao {
	private Context context;

	private  DbHelper helper;

	private String DB_PATH="/data/data/com.meigao.mgolf/databases/";

	private String DB_NAME="mgolf.db3";

	public CityDao(Context context) 
	{
		helper = new DbHelper(context);
		
		this.context=context;
	}
	/**
	 * 将城市实体保存到数据库
	 * @param context
	 * @param city
	 * @return
	 */
	public boolean save(Context context,CityEntity city)
	{
		boolean isSuccess=false;
		SQLiteDatabase db=helper.getWritableDatabase();
		try {

			ContentValues values=new ContentValues();
			values.put("id",city.getId()+"");
			values.put("pid", city.getPid()+"");
			values.put("name", city.getName());
			values.put("regtion_level", city.getRegtion_level()+"");
			int count=(int)db.insert("city", null, values);
		} catch (Exception e) {
			// TODO: handle exception
			//ExceptionUtils.print(e);
		}finally
		{
			if (db!=null)
			{
				db.close();
			}
		}
		return isSuccess;
	}

	/**
	 * 查询所有城市
	 * @return
	 */
	public ArrayList<CityEntity> getAllCity(){
		ArrayList<CityEntity> list=null;
		SQLiteDatabase db=helper.getWritableDatabase();
		try {
			Cursor cursor = db.query("city", null, null, null, null, null, null);  
			if(cursor != null){  
				list = new ArrayList<CityEntity>();  
				while(cursor.moveToNext()){  
					CityEntity city = new CityEntity();  
					int id = cursor.getInt(cursor.getColumnIndex("id"));  
					String name = cursor.getString(cursor.getColumnIndex("name"));  
					int pid = cursor.getInt(cursor.getColumnIndex("pid"));  
					int regtion_level = cursor.getInt(cursor.getColumnIndex("regtion_level"));  
					city.setId(id);
					city.setName(name);
					city.setPid(pid);
					city.setRegtion_level(regtion_level);
					list.add(city);
				}  
			}    
			cursor.close();
		} catch (Exception e) {
			//ExceptionUtils.print(e);
		}
		if (db!=null)
		{
			db.close();
		}
		return list;
	}
	/**
	 * 查询三级城市实体
	 * @return
	 */
	public ArrayList<CityEntity> getAllCityThree(){
		ArrayList<CityEntity> list=null;
		SQLiteDatabase db=helper.getWritableDatabase();
		try {
			Cursor cursor =db.rawQuery("select * from city where regtion_level=3", null);
			if(cursor != null){  
				list = new ArrayList<CityEntity>();  
				while(cursor.moveToNext()){  
					CityEntity city = new CityEntity();  
					int id = cursor.getInt(cursor.getColumnIndex("id"));  
					String name = cursor.getString(cursor.getColumnIndex("name"));  
					int pid = cursor.getInt(cursor.getColumnIndex("pid"));  
					int regtion_level = cursor.getInt(cursor.getColumnIndex("regtion_level"));  
					city.setId(id);
					city.setName(name);
					city.setPid(pid);
					city.setRegtion_level(regtion_level);
					list.add(city);
				}  
			}    
			cursor.close();
		} catch (Exception e) {
			//ExceptionUtils.print(e);
		}
		if (db!=null)
		{
			db.close();
		}
		return list;
	}
	/**
	 * 查询三级城市的名称
	 * @return
	 */
	public ArrayList<String> getAllCityNameThree(){
		ArrayList<String> list=null;
		SQLiteDatabase db=helper.getWritableDatabase();
		try {
			Cursor cursor =db.rawQuery("select name from city where regtion_level=3", null);
			if(cursor != null){  
				list = new ArrayList<String>();  
				while(cursor.moveToNext()){  
					String name = cursor.getString(cursor.getColumnIndex("name"));  
					list.add(name);
				}  
			}    
			cursor.close();
		} catch (Exception e) {
		//	ExceptionUtils.print(e);
		}
		if (db!=null)
		{
			db.close();
		}
		return list;
	}
	/**
	 * 根据输入查询匹配城市
	 * @return
	 */
	public ArrayList<CityEntity> getCityByName(String cityName){
		ArrayList<CityEntity> list=null;
		SQLiteDatabase db=helper.getWritableDatabase();
		try {
			Cursor cursor =db.rawQuery("select * from city where name like '%"+cityName+"%' and regtion_level=3", null);
			if(cursor != null){  
				list = new ArrayList<CityEntity>();  
				while(cursor.moveToNext()){  
					CityEntity city = new CityEntity();  
					int id = cursor.getInt(cursor.getColumnIndex("id"));  
					String name = cursor.getString(cursor.getColumnIndex("name"));  
					int pid = cursor.getInt(cursor.getColumnIndex("pid"));  
					int regtion_level = cursor.getInt(cursor.getColumnIndex("regtion_level"));  
					city.setId(id);
					city.setName(name);
					city.setPid(pid);
					city.setRegtion_level(regtion_level);
					list.add(city);
				}  
			}    
			cursor.close();
		} catch (Exception e) {
			//ExceptionUtils.print(e);
		}
		if (db!=null)
		{
			db.close();
		}
		return list;
	}

	/**
	 * 根据id查找城市名称
	 * @param id
	 * @return
	 */
	public String getCityNameById(String id){
		SQLiteDatabase db=helper.getWritableDatabase();
		String name = null;
		try {
			Cursor cursor = db.rawQuery("select * from city where id=?", new String[]{id}); 
			 if(cursor.moveToFirst()) {
				 name = cursor.getString(cursor.getColumnIndex("name"));
			}
		} catch (Exception e) {
			//ExceptionUtils.print(e);
		}
		if (db!=null)
		{
			db.close();
		}
		return name;
	}
	/**
	 * 根据输入查询城市
	 * @return
	 */
	public  CityEntity getCityDetailByName(String cityName){
		SQLiteDatabase db=helper.getWritableDatabase();
		CityEntity city=null;
		try {
			Cursor cursor =db.rawQuery("select * from city where name like '%"+cityName+"%' and regtion_level=3", null);
			if(cursor != null){  
				while(cursor.moveToNext()){  
					city = new CityEntity();  
					int id = cursor.getInt(cursor.getColumnIndex("id"));  
					String name = cursor.getString(cursor.getColumnIndex("name"));  
					int pid = cursor.getInt(cursor.getColumnIndex("pid"));  
					int regtion_level = cursor.getInt(cursor.getColumnIndex("regtion_level"));  
					city.setId(id);
					city.setName(name);
					city.setPid(pid);
					city.setRegtion_level(regtion_level);
				}  
			}    
			cursor.close();
		} catch (Exception e) {
		//	ExceptionUtils.print(e);
		}
		if (db!=null)
		{
			db.close();
		}
		return city;
	}
	
	/**
	 * 查询用户的所在的国家省市区
	 * @return
	 */
	public  HashMap<String, String> getResUserAdrr(String contryid,String proviceid,String cityid,String xianid){
		HashMap<String, String> map=new HashMap<String, String>();
		
		SQLiteDatabase db=helper.getWritableDatabase();
		String country=null;
		String provice=null;
		String city=null;
		String xian=null;
		
		try {
			//国家
			Cursor cursor =db.rawQuery("select name from city where id="+contryid, null);
			if(cursor != null){  
				if(cursor.moveToNext()){  
					map.put("contry",cursor.getString(cursor.getColumnIndex("name")));  
				} 
				cursor=db.rawQuery("select name from city where id="+proviceid, null);
				if(cursor.moveToNext()){
					map.put("province", cursor.getString(cursor.getColumnIndex("name")));
				}
				
				cursor=db.rawQuery("select name from city where id="+cityid, null);
				if(cursor.moveToNext()){
					map.put("city", cursor.getString(cursor.getColumnIndex("name")));
				}
				cursor=db.rawQuery("select name from city where id="+xianid, null);
				if(cursor.moveToNext()){
					map.put("xian", cursor.getString(cursor.getColumnIndex("name")));
				}
			}   
			if(cursor!=null)
			    cursor.close();
		} catch (Exception e) {
			//ExceptionUtils.print(e);
		}
		if (db!=null)
		{
			db.close();
		}
		return map;
	}
	/**
	 * 根据用户的输入的国家省市区查找相关的id
	 * @return
	 */
	public  HashMap<String, String> getUpdateAddr(String contry,String provice,String city,String xian){
		HashMap<String, String> map=new HashMap<String, String>();
		
		SQLiteDatabase db=helper.getWritableDatabase();
		String countryid=null;
		String proviceid=null;
		String cityid=null;
		String xianid=null;
		Cursor cursor =null;
		try {
			if(!CheckUtils.checkEmpty(contry)){
				cursor =db.rawQuery("select id from city where contry='"+contry+"'", null);
				//国家
				if(cursor != null){  
					if(cursor.moveToNext()){  
						map.put("rid1",cursor.getString(cursor.getColumnIndex("id")));  
					} 
				}
			}
			if(!CheckUtils.checkEmpty(provice)){
				cursor=db.rawQuery("select id from city where name='"+provice+"'", null);
				if(cursor.moveToNext()){
					map.put("rid2", cursor.getString(cursor.getColumnIndex("id")));
				}
				
			}
			if(!CheckUtils.checkEmpty(city)){
				cursor=db.rawQuery("select id from city where name='"+city+"'", null);
				if(cursor.moveToNext()){
					map.put("rid3", cursor.getString(cursor.getColumnIndex("id")));
				}
			}
			if(!CheckUtils.checkEmpty(xian)){
				cursor=db.rawQuery("select id from city where name='"+xian+"'", null);
				if(cursor.moveToNext()){
					map.put("rid4", cursor.getString(cursor.getColumnIndex("id")));
				}  
			}
			if(cursor!=null)
				cursor.close();
		} catch (Exception e) {
			//ExceptionUtils.print(e);
		}
		if (db!=null)
		{
			db.close();
		}
		return map;
	}
	
}
