package com.qingkong.weixin.utils;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.qingkong.weixin.base.BaseApplication;

/**
 * Created by yanghq on 2017/11/22.
 * 1653942463@qq.com
 */

public class ToastUtil {
    private static Toast toast;


    /**Toast
     * @param msg
     */
    public static void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance(), msg, Toast.LENGTH_SHORT);
        }else {
            toast.setText(msg);
        }
       toast.show();

    }

    /**Toast
     * @param resId
     */
    public static void showToast(int resId) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance(), resId, Toast.LENGTH_SHORT);
        }else {
            toast.setText(resId);
        }
        toast.show();

    }
    public static void showSnackBar(View view,String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
