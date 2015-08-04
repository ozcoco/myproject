package com.example.topnewgrid.dao;

import java.util.List;
import java.util.Map;

import com.example.topnewgrid.bean.ChannelItem;

import android.content.ContentValues;

public interface ChannelDaoInface {
	public boolean addCache(ChannelItem item,String TableName);

	public boolean deleteCache(String whereClause, String[] whereArgs,String TableName);

	public boolean updateCache(ContentValues values, String whereClause,
			String[] whereArgs,String TableName);

	public Map<String, String> viewCache(String selection,
			String[] selectionArgs,String TableName);

	public List<Map<String, String>> listCache(String selection,
			String[] selectionArgs,String TableName);

	public void clearFeedTable(String TableName);
}
