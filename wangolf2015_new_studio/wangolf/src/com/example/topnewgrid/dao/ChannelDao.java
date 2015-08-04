package com.example.topnewgrid.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.topnewgrid.bean.ChannelItem;
import com.example.topnewgrid.db.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ChannelDao implements ChannelDaoInface {
	private SQLHelper helper = null;

	public ChannelDao(Context context) {
		helper = new SQLHelper(context);
	}

	@Override
	public boolean addCache(ChannelItem item ,String TableName) {
		// TODO Auto-generated method stub

        boolean flag = false;
		SQLiteDatabase database = null;
		long id = -1;
		try {
			database = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("name", item.getName());
			values.put("id", item.getId());
			values.put("orderId", item.getOrderId());
			values.put("selected", item.getSelected());
			id = database.insert(TableName, null, values);
			flag = (id != -1 ? true : false);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return flag;
	}

	@Override
	public boolean deleteCache(String whereClause, String[] whereArgs,String TableName) {
		// TODO Auto-generated method stub
		boolean flag = false;
		SQLiteDatabase database = null;
		int count = 0;
		try {
			database = helper.getWritableDatabase();
			count = database.delete(TableName, whereClause, whereArgs);
			flag = (count > 0 ? true : false);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return flag;
	}

	@Override
	public boolean updateCache(ContentValues values, String whereClause,
			String[] whereArgs,String TableName) {
		// TODO Auto-generated method stub
		boolean flag = false;
		SQLiteDatabase database = null;
		int count = 0;
		try {
			database = helper.getWritableDatabase();
			count = database.update(TableName, values, whereClause, whereArgs);
			flag = (count > 0 ? true : false);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return flag;
	}

	@Override
	public Map<String, String> viewCache(String selection,
			String[] selectionArgs,String TableName) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = null;
		Cursor cursor = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			database = helper.getReadableDatabase();
			cursor = database.query(true, TableName, null, selection,
					selectionArgs, null, null, null, null);
			int cols_len = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				for (int i = 0; i < cols_len; i++) {
					String cols_name = cursor.getColumnName(i);
					String cols_values = cursor.getString(cursor
							.getColumnIndex(cols_name));
					if (cols_values == null) {
						cols_values = "";
					}
					map.put(cols_name, cols_values);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return map;
	}

	@Override
	public List<Map<String, String>> listCache(String selection,String[] selectionArgs,String TableName) {
		// TODO Auto-generated method stub
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		SQLiteDatabase database = null;
		Cursor cursor = null;
		try {
			database = helper.getReadableDatabase();
			cursor = database.query(false, TableName, null, selection,selectionArgs, null, null, null, null);
			int cols_len = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < cols_len; i++) {

					String cols_name = cursor.getColumnName(i);
					String cols_values = cursor.getString(cursor
							.getColumnIndex(cols_name));
					if (cols_values == null) {
						cols_values = "";
					}
					map.put(cols_name, cols_values);
				}
				list.add(map);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return list;
	}

	public void clearFeedTable(String TableName) {
		String sql = "DELETE FROM " + TableName + ";";
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(sql);
		revertSeq(TableName);
	}

	public void revertSeq(String TableName) {
		String sql = "update sqlite_sequence set seq=0 where name='"
				+ TableName + "'";
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(sql);
	}

}
