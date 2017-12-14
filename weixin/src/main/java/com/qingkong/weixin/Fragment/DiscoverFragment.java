package com.qingkong.weixin.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingkong.weixin.Activity.MomentsActivity;
import com.qingkong.weixin.R;

/**
 * Created by yanghq on 2017/11/15.
 */

public class DiscoverFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private LinearLayout llytMoments;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_discover, container, false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        llytMoments = view.findViewById(R.id.llytMoments);
        llytMoments.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llytMoments:
                startActivity(new Intent(getActivity(), MomentsActivity.class));
                break;
        }
    }
}
