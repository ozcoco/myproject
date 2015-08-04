package me.wangolf.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 未使用
 * @author haie
 *
 */
public class DbHelper extends SQLiteOpenHelper{

	public DbHelper(Context context) {
		super(context, "mgolf.db3", null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		/*String sql="create table if not exists userinfo(uid integer,username text,nickname text,realname text,sex text," +
				"age integer,photo text,birthday text,address text,ballage integer,account text,rmoney,text,vouchers text," +
				"integration text,reletag text,summy text,job text)";
		db.execSQL(sql);*/
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 /*
			db.execSQL("DROP TABLE IF EXISTS userinfo");
			String sql="create table if not exists userinfo(uid integer,username text,nickname text,realname text,sex text," +
					"age integer,photo text,birthday text,address text,ballage integer,account text,rmoney,text,vouchers text," +
					"integration text,reletag text,summy text,job text)";
			db.execSQL(sql);
			 */
		}

}
