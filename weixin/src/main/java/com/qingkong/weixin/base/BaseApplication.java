package com.qingkong.weixin.base;

import android.app.Application;

import com.qingkong.weixin.utils.Constants;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by yanghq on 2017/11/16.
 * 1653942463@qq.com
 */

public class BaseApplication extends Application {


    private static BaseApplication instance;
    public static BaseApplication getInstance(){
        if (instance == null) {
            instance = new BaseApplication();

        }
        return instance;
    }

    public BaseApplication(){
        instance = this;

    }



    private Socket mSocket;
    {

        try {
            mSocket = IO.socket(Constants.CHAT_SERVER_URL);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
    @Override
    public void onCreate() {
        super.onCreate();

    }
}
