package me.wangolf.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtils {
	private static String CONFIG = "configs";
	private static SharedPreferences sp;

	public static void saveString(Context c, String key, String value) {

		sp = c.getSharedPreferences(CONFIG, 0);
		Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();
	}

	public static String getString(Context c, String key) {

		sp = c.getSharedPreferences(CONFIG, 0);
		String value = sp.getString(key, "");
		return value;
	}
}
