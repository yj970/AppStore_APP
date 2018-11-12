package com.yj.appstore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.yj.appstore.App;

public class SpUtil {

    //存储的sharedpreferences文件名
    private static final String FILE_NAME = "setting";

    /**
     * 保存数据到文件
     *
     * @param key
     * @param data
     */
    public static void saveData(String key, Object data) {
        try {
            String type = data.getClass().getSimpleName();
            SharedPreferences sharedPreferences = App.getContext()
                    .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if ("Integer".equals(type)) {
                editor.putInt(key, (Integer) data);
            } else if ("Boolean".equals(type)) {
                editor.putBoolean(key, (Boolean) data);
            } else if ("String".equals(type)) {
                editor.putString(key, (String) data);
            } else if ("Float".equals(type)) {
                editor.putFloat(key, (Float) data);
            } else if ("Long".equals(type)) {
                editor.putLong(key, (Long) data);
            }

            editor.commit();
        } catch (Exception e) {
            Log.e("SharedPreferencesUtil", "XML配置文件保存操作异常" + e.getMessage());
        }
    }

    /**
     * 从文件中读取数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public static Object getData(String key, Object defValue) {
        try {
            String type = defValue.getClass().getSimpleName();
            SharedPreferences sharedPreferences = App.getContext().getSharedPreferences
                    (FILE_NAME, Context.MODE_PRIVATE);

            //defValue为为默认值，如果当前获取不到数据就返回它
            if ("Integer".equals(type)) {
                return sharedPreferences.getInt(key, (Integer) defValue);
            } else if ("Boolean".equals(type)) {
                return sharedPreferences.getBoolean(key, (Boolean) defValue);
            } else if ("String".equals(type)) {
                return sharedPreferences.getString(key, (String) defValue);
            } else if ("Float".equals(type)) {
                return sharedPreferences.getFloat(key, (Float) defValue);
            } else if ("Long".equals(type)) {
                return sharedPreferences.getLong(key, (Long) defValue);
            }

            return null;
        } catch (Exception e) {
            return defValue;
        }
    }

    public static Object getData(String key) {
            SharedPreferences sharedPreferences = App.getContext().getSharedPreferences
                    (FILE_NAME, Context.MODE_PRIVATE);
                return sharedPreferences.getString(key, null);
    }

    /**
     * 初始化数据到文件
     * 有就跳过，没有就新增
     * @param key
     * @param data
     */
    public static void initialData(String key, Object data) {
        try {
            if (getData( key, "HasNoInitial").toString().equals("HasNoInitial")) {
                saveData( key, data);
            }
        } catch (Exception e) {
            Log.e("SharedPreferencesUtil", "XML配置文件初始化操作异常" + e.getMessage());
            //CommFunc.ToastPromptMsg("XML配置文件初始化操作失败");
        }
    }

}

