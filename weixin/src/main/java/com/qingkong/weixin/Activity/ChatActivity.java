package com.qingkong.weixin.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qingkong.weixin.R;
import com.qingkong.weixin.adapter.ChatListAdapter;
import com.qingkong.weixin.adapter.MessageAdapter;
import com.qingkong.weixin.base.BaseApplication;
import com.qingkong.weixin.bean.Message;
import com.qingkong.weixin.utils.Event;
import com.qingkong.weixin.utils.LogUtil;
import com.qingkong.weixin.utils.UiUtils;
import com.qingkong.weixin.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    private  String TAG = ChatActivity.this.getClass().getSimpleName();
    private Socket mSocket;
    private RecyclerView listView;
    private  MessageAdapter messageAdapter;
    private List<Message> messageList = new ArrayList<>();
    private Button btnSend;
    private EditText etMessage;
    private  String mUsername = "GG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.logE("onCreate");
        setContentView(R.layout.activity_chat);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("聊天室");
        actionBar.setDisplayHomeAsUpEnabled(true);
        BaseApplication app = (BaseApplication) getApplication();
        mSocket = app.getSocket();
        initView();

    }


    private void initView() {
        listView = (RecyclerView) findViewById(R.id.listView);
        listView.setHasFixedSize(true);
        messageAdapter = new MessageAdapter(messageList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);
        listView.setAdapter(messageAdapter);

        btnSend = UiUtils.findView(this, R.id.btnSend);
        btnSend.setOnClickListener(this);
        etMessage = UiUtils.findView(this, R.id.etMessage);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                if (null == mUsername) return;
                if (!mSocket.connected()) return;
                String message = etMessage.getText().toString();
               LogUtil.logE(TAG,"message===="+message);
                if (message.trim().length()!=0){
                    mSocket.emit(Event.EVENT_NEW_MESSAGE,message);
                    Message messageBean = new Message.Builder(Message.TYPE_MESSAGE).username(mUsername).message(message).build();
                    addMessage(messageBean);
                    etMessage.setText("");
                }

                break;
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.logE("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.logE("onStart");
        initSocketEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.logE("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.logE("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.logE("onStop");

        closeSocketEvent();
    }
    private void initSocketEvent() {
        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.on(Event.EVENT_LOGIN, onLogin);
        mSocket.on(Event.EVENT_NEW_MESSAGE, onNewMessage);
        mSocket.on(Event.EVENT_USER_JOINED, onUserJoined);
        mSocket.on(Event.EVENT_USER_LEFT, onUserLeft);
        mSocket.on(Event.EVENT_TYPING, onTyping);
        mSocket.on(Event.EVENT_STOP_TYPING, onStopTyping);
        mSocket.connect();
        //登录

        mSocket.emit("add user", mUsername);
    }

    private void closeSocketEvent() {
        mSocket.off(Socket.EVENT_CONNECT,onConnect);
        mSocket.off(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.off(Event.EVENT_LOGIN, onLogin);
        mSocket.off(Event.EVENT_NEW_MESSAGE, onNewMessage);
        mSocket.off(Event.EVENT_USER_JOINED, onUserJoined);
        mSocket.off(Event.EVENT_USER_LEFT, onUserLeft);
        mSocket.off(Event.EVENT_TYPING, onTyping);
        mSocket.off(Event.EVENT_STOP_TYPING, onStopTyping);
        mSocket.disconnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.logE("onDestroy");
    }
    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            LogUtil.logE(TAG,"onConnect==="+ Arrays.toString(args));
        }
    };
    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            LogUtil.logE(TAG,"onDisconnect==="+Arrays.toString(args));
        }
    };
    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            LogUtil.logE(TAG,"onConnectError==="+Arrays.toString(args));
        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject jsonObject = (JSONObject) args[0];
            String username = jsonObject.optString("username");
            String message = jsonObject.optString("message");
            final Message messageBean = new Message.Builder(Message.TYPE_MESSAGE).username(username).message(message).build();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    addMessage(messageBean);
                }
            });
            LogUtil.logE(TAG,"onNewMessage==="+ Arrays.toString(args));
        }
    };

    private void addMessage(Message message) {
        messageAdapter.insertMessage(message);
        scrollToBottom();

    }

    private void scrollToBottom() {
        listView.scrollToPosition(messageAdapter.getItemCount() - 1);
    }
    private Emitter.Listener onLogin = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            LogUtil.logE(TAG,"onLogin==="+Arrays.toString(args));
            final JSONObject data = (JSONObject) args[0];
            LogUtil.logE(TAG,"data==="+data.toString());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int numUsers;
                        numUsers = data.getInt("numUsers");
                        getSupportActionBar().setTitle("聊天室("+numUsers+")");
                    } catch (JSONException e) {
                        return;
                    }

                }
            });
//            Intent intent = new Intent();
//            intent.putExtra("username", mUsername);
//            intent.putExtra("numUsers", numUsers);
//            intent.setClass(getActivity(), ChatActivity.class);
//            startActivity(intent);
//            setResult(RESULT_OK, intent);
//            finish();
        }
    };
    private Emitter.Listener onUserJoined = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            LogUtil.logE(TAG,"onUserJoined==="+Arrays.toString(args));

            final JSONObject data = (JSONObject) args[0];
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int numUsers;
                        numUsers = data.getInt("numUsers");
                        getSupportActionBar().setTitle("聊天室("+numUsers+")");
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };
    private Emitter.Listener onUserLeft = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            LogUtil.logE(TAG,"onUserLeft==="+Arrays.toString(args));
            final JSONObject data = (JSONObject) args[0];
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int numUsers;
                        numUsers = data.getInt("numUsers");
                        getSupportActionBar().setTitle("聊天室("+numUsers+")");
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };
    private Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            LogUtil.logE(TAG,"onTyping==="+Arrays.toString(args));
        }
    };
    private Emitter.Listener onStopTyping = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            LogUtil.logE(TAG,"onStopTyping==="+Arrays.toString(args));
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void finish() {
        LogUtil.logE("finish");
        super.finish();
    }


}
