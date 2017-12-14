package com.qingkong.weixin.utils;

import android.app.Activity;
import android.view.View;
import android.view.Window;

/**
 * Created by yanghq on 2017/11/17.
 * 1653942463@qq.com
 */

public class UiUtils {

    /**实例化视图
     * @param root
     * @param id
     * @param <T>
     * @return
     */
    public static <T extends View> T findView(View root, int id) {
        return (T) root.findViewById(id);
    }

    /**实例化视图
     * @param activity
     * @param id
     * @param <T>
     * @return
     */
    public static <T extends View> T findView(Activity activity, int id) {
        return (T) activity.getWindow().getDecorView().getRootView().findViewById(id);
    }

    /**全屏模式
     * @param window
     */
    public static void hideSystemUI(Window window) {
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        // remove the following flag for version < API 19
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }
}

