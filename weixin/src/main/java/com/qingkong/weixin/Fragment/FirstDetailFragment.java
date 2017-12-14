package com.qingkong.weixin.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yanghq on 2017/11/21.
 * 1653942463@qq.com
 */

public class FirstDetailFragment extends Fragment {
    public  TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         textView = new TextView(getActivity());
        textView.setText("FirstDetailFragment");
        return textView;
        //        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Bundle bundle = getArguments();
//        if (bundle!=null) {
//           textView.setText(bundle.getString("data"));
//        }
    }
    public void setText(String text) {

        textView.setText(text);
    }
}
