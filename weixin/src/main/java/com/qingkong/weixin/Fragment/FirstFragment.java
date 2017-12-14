package com.qingkong.weixin.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingkong.weixin.utils.LogUtil;

import java.util.logging.SimpleFormatter;

/**
 * Created by yanghq on 2017/11/21.
 * 1653942463@qq.com
 */

public class FirstFragment extends Fragment {
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void updateText(String string);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.logE("listener=onAttach");
        if (context instanceof OnItemClickListener){
            listener = (OnItemClickListener) context;
            LogUtil.logE("listener=1=="+listener);
        }else {
            LogUtil.logE("listener=2=="+listener);

            throw new ClassCastException(context.toString()
                    + " must implement FirstFragment.OnItemClickListener");

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LogUtil.logE("onCreateView");
        TextView textView = new TextView(getActivity());
        textView.setText("FirstFragment");

        return textView;
        //        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.updateText("获取详情"+System.currentTimeMillis());

            }
        });
    }
}
