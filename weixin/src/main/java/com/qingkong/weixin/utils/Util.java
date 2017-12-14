package com.qingkong.weixin.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

/**
 * Created by yanghq on 2017/11/20.
 * 1653942463@qq.com
 */

public class Util {
    public static boolean isIntentAvailable(Context ctx, Intent intent) {
        final PackageManager mgr = ctx.getPackageManager();
        List<ResolveInfo> list =
                mgr.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    //判断网络是否可用
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
//        ConnectivityManager cm = (ConnectivityManager) context
//                .getSystemService(context.CONNECTIVITY_SERVICE);
//        if (cm == null) {
//        } else {
//　　　　　　　//如果仅仅是用来判断网络连接
//　　　　　　 //则可以使用 cm.getactivenetworkinfo().isavailable();
//           NetworkInfo[] info = cm.getAllNetworkInfo();
//            if (info != null) {
//                for (int i = 0; i < info.length; i++) {
//                    if (info[i].getstate() == networkinfo.state.connected) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
    }
}
