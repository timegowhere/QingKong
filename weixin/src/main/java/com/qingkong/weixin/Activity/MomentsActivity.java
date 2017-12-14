package com.qingkong.weixin.Activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.qingkong.weixin.R;
import com.qingkong.weixin.utils.UiUtils;

public class MomentsActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moments);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("朋友圈");
        actionBar.setDisplayHomeAsUpEnabled(true);
        swipeRefresh = UiUtils.findView(this,R.id.swipeRefrsh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               swipeRefresh.setRefreshing(false);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
