package com.qingkong.weixin.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qingkong.weixin.Activity.ChatActivity;
import com.qingkong.weixin.R;
import com.qingkong.weixin.adapter.ChatListAdapter;
import com.qingkong.weixin.adapter.DialogListAdapter;
import com.qingkong.weixin.base.BaseApplication;
import com.qingkong.weixin.utils.LogUtil;
import com.qingkong.weixin.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by yanghq on 2017/11/15.
 */

public class ChatListFragment extends Fragment {
    private View rootView;
    private RecyclerView listView;
    private Socket mSocket;
    private String mUsername;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.logE("onAttach 1");
//       BaseApplication app =  (BaseApplication)getActivity().getApplication();
//        mSocket = app.getSocket();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.logE("onCreate 2");
//        mSocket.on(Socket.EVENT_CONNECT,onConnect);
//        mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
//        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
//        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
//
//        mSocket.on("login", onLogin);
////        mSocket.on("new message", onNewMessage);
////        mSocket.on("user joined", onUserJoined);
////        mSocket.on("user left", onUserLeft);
////        mSocket.on("typing", onTyping);
////        mSocket.on("stop typing", onStopTyping);
//        mSocket.connect();




    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        super.onCreateView(inflater, container, savedInstanceState);
        LogUtil.logE("onCreateView 3");
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_chat_list, container, false);
//            initView();
        }
        return rootView;
    }

    private void initView() {
        listView = rootView.findViewById(R.id.listView);
        listView.setHasFixedSize(true);
        ChatListAdapter messageAdapter = new ChatListAdapter();
        messageAdapter.setOnItemClickListen(new ChatListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                LogUtil.logE("position=="+position);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(linearLayoutManager);
        listView.setAdapter(messageAdapter);
    }
    PopupWindow popupWindow = null;

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.logE("onViewCreated view=="+view);
        LogUtil.logE("onViewCreated savedInstanceState=="+savedInstanceState);

        if (popupWindow == null) {
            View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_list, null);
            ListView listView = contentView.findViewById(R.id.listView);

//            ListView listView = new ListView(getActivity());
            listView.setBackgroundColor(Color.WHITE);

//            listView.setAdapter(new ArrayAdapter<String>(getActivity(),
//                    android.R.layout.simple_list_item_1,
//                    android.R.id.text2,new String[]{"复制","发送给朋友","收藏","翻译","删除","更多"}));
            DialogListAdapter dialogListAdapter = new DialogListAdapter(Arrays.asList(new String[]{"复制","发送给朋友","收藏","翻译","删除","更多"}));
            listView.setAdapter(dialogListAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    LogUtil.logE("i=="+i);
                    popupWindow.dismiss();
                }
            });
            popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
        }


//        if (view == null) {

            listView = view.findViewById(R.id.listView);
            listView.setHasFixedSize(true);
            ChatListAdapter messageAdapter = new ChatListAdapter();
            messageAdapter.setOnItemClickListen(new ChatListAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    LogUtil.logE("position=="+position);
                    if (position == 0) {
//                        mUsername = "GG";
//                        mSocket.emit("add user", mUsername);
                        startActivity(new Intent(getActivity(), ChatActivity.class));
                    } else {
//                        if (position % 2 == 0) {
                            popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
//                        }else {
//                            popupWindow.dismiss();
//                        }

                    }

                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            listView.setLayoutManager(linearLayoutManager);
            listView.setAdapter(messageAdapter);
//        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.logE("onActivityCreated 4 savedInstanceState=="+savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.logE("onStart 5");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.logE("onResume 6");

    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.logE("onPause 7");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.logE("onStop 8");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.logE("onDestroyView 9");


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.logE("onDestroy 10");
//        mSocket.disconnect();
//        mSocket.off(Socket.EVENT_CONNECT,onConnect);
//        mSocket.off(Socket.EVENT_DISCONNECT,onDisconnect);
//        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
//        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
//        mSocket.off("login", onLogin);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.logE("onDetach 11");
    }
    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            LogUtil.logE("onConnect==="+Arrays.toString(args));
        }
    };
    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            LogUtil.logE("onDisconnect==="+Arrays.toString(args));
        }
    };
    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            LogUtil.logE("onConnectError==="+Arrays.toString(args));
        }
    };
    private Emitter.Listener onLogin = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            LogUtil.logE("onLogin==="+Arrays.toString(args));
            JSONObject data = (JSONObject) args[0];
            LogUtil.logE("data==="+data.toString());
            int numUsers;
            try {
                numUsers = data.getInt("numUsers");
            } catch (JSONException e) {
                return;
            }

            Intent intent = new Intent();
            intent.putExtra("username", mUsername);
            intent.putExtra("numUsers", numUsers);
            intent.setClass(getActivity(), ChatActivity.class);
            startActivity(intent);
//            setResult(RESULT_OK, intent);
//            finish();
        }
    };
}
