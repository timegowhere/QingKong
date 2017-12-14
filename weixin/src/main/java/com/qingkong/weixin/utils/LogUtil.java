package com.qingkong.weixin.utils;

import android.util.Log;

import com.qingkong.weixin.BuildConfig;

/**
 * Created by yanghq on 2017/11/15.
 */

public class LogUtil {
    private static final String TAG = "晴空";
    public static void logE(String msg){
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg );

        }

    }
    public static void logE(String className,String msg){
        if (BuildConfig.DEBUG) {

            Log.e(className, "  "+msg );
        }

    }
}
