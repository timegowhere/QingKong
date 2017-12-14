package com.qingkong.weixin;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import android.util.Log;
import android.view.ActionMode;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.ShareActionProvider;


import com.qingkong.weixin.Fragment.ContactsFragment;
import com.qingkong.weixin.Fragment.DiscoverFragment;
import com.qingkong.weixin.Fragment.ChatListFragment;
import com.qingkong.weixin.Fragment.MineFragment;
import com.qingkong.weixin.utils.LogUtil;
import com.qingkong.weixin.utils.ToastUtil;
import com.qingkong.weixin.utils.UiUtils;
import com.qingkong.weixin.view.ChangeColorIconWithText;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener {

    private static final int REQUESTCODE_WRITE = 1;
    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();
    private String[] mTitles = new String[]
            {"First Fragment !", "Second Fragment !", "Third Fragment !",
                    "Fourth Fragment !"};
    private FragmentPagerAdapter mAdapter;

    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();
    private String tag = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.logE(tag,"onCreate");
        LogUtil.logE(tag,"onCreate savedInstanceState=="+savedInstanceState);
        setContentView(R.layout.activity_main);
//        setOverflowButtonAlways();
//            getActionBar().setDisplayShowHomeEnabled(false);

        initView();
        initPermission();
        initDatas();
        mViewPager.setAdapter(mAdapter);
//        mViewPager.setOffscreenPageLimit(3);
        initEvent();


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        LogUtil.logE(tag,"onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtil.logE(tag,"onRestoreInstanceState");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.logE(tag,"onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.logE(tag,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.logE(tag,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.logE(tag,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.logE(tag,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.logE(tag,"onDestroy");
    }


    private void initPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.i("晴空", "请求权限");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.i("晴空", "请求权限111");
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                Log.i("晴空", "请求权限2222");
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                                Manifest.permission.ACCESS_COARSE_LOCATION
                        },
                        REQUESTCODE_WRITE);


                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            Log.i("晴空", "有权限");
        }
    }

    /**
     * 初始化所有事件
     */
    private void initEvent() {

//            mViewPager.setOnPageChangeListener(this);
        mViewPager.addOnPageChangeListener(this);
    }

    private void initDatas() {
        ChatListFragment messageFragment = new ChatListFragment();
        ContactsFragment contactsFragment = new ContactsFragment();
        DiscoverFragment discoverFragment = new DiscoverFragment();

        MineFragment mineFragment = new MineFragment();
        List<Fragment> list = new ArrayList<>();
        list.add(messageFragment);
        list.add(contactsFragment);
        list.add(discoverFragment);
        list.add(mineFragment);





        for (Fragment fragment : list) {
//                TabFragment tabFragment = new TabFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString(TabFragment.TITLE, title);
//                tabFragment.setArguments(bundle);
//                mTabs.add(tabFragment);


            mTabs.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }
        };
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        ChangeColorIconWithText one = (ChangeColorIconWithText) findViewById(R.id.id_indicator_one);
        mTabIndicators.add(one);
        ChangeColorIconWithText two = (ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
        mTabIndicators.add(two);
        ChangeColorIconWithText three = (ChangeColorIconWithText) findViewById(R.id.id_indicator_three);
        mTabIndicators.add(three);
        ChangeColorIconWithText four = (ChangeColorIconWithText) findViewById(R.id.id_indicator_four);
        mTabIndicators.add(four);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        one.setIconAlpha(1.0f);

        registerForContextMenu(one);
//        UiUtils.hideSystemUI(getWindow());

    }
//    ShareActionProvider provider;
//ShareActionProvider provider;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
//        provider = (ShareActionProvider) menu.findItem(R.id.menu_share)
//                .getActionProvider();
//        provider = (ShareActionProvider) menu.findItem(R.id.menu_share).getActionProvider();
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_group_chat:
                ToastUtil.showToast(getResources().getString(R.string.menu_group_chat));
                break;
            case R.id.action_add_friend:
                ToastUtil.showToast(getResources().getString(R.string.menu_addfriend));
                break;
            case R.id.action_scan:
                ToastUtil.showToast(getResources().getString(R.string.menu_scan));
                break;
            case R.id.action_feedback:
                ToastUtil.showToast(getResources().getString(R.string.menu_feedback));
                break;

//            case R.id.menu_share:
//
//                // populate the share intent with data
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_TEXT, "This is a message for you");
//                provider.setShareIntent(intent);
//
//                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_group_chat:
                ToastUtil.showToast(getResources().getString(R.string.menu_group_chat));
                break;
            case R.id.action_add_friend:
                ToastUtil.showToast(getResources().getString(R.string.menu_addfriend));
                break;
            case R.id.action_scan:
                ToastUtil.showToast(getResources().getString(R.string.menu_scan));
                break;
            case R.id.action_feedback:
                ToastUtil.showToast(getResources().getString(R.string.menu_feedback));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //    private void setOverflowButtonAlways() {
//        try {
//            ViewConfiguration config = ViewConfiguration.get(this);
//            Field menuKey = ViewConfiguration.class
//                    .getDeclaredField("sHasPermanentMenuKey");
//            menuKey.setAccessible(true);
//            menuKey.setBoolean(config, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 设置menu显示icon
//     */
//    @Override
//    public boolean onMenuOpened(int featureId, Menu menu) {
//
//        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
//            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
//                try {
//                    Method m = menu.getClass().getDeclaredMethod(
//                            "setOptionalIconsVisible", Boolean.TYPE);
//                    m.setAccessible(true);
//                    m.invoke(menu, true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return super.onMenuOpened(featureId, menu);
//    }

    @Override
    public void onClick(View v) {
        clickTab(v);

    }

    /**
     * 点击Tab按钮
     *
     * @param v
     */
    private void clickTab(View v) {
        resetOtherTabs();

        switch (v.getId()) {
            case R.id.id_indicator_one:
                mTabIndicators.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_three:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.id_indicator_four:
                mTabIndicators.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3, false);
                break;
        }
    }

    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicators.size(); i++) {
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        // Log.e("TAG", "position = " + position + " ,positionOffset =  "
        // + positionOffset);
        if (positionOffset > 0) {
            ChangeColorIconWithText left = mTabIndicators.get(position);
            ChangeColorIconWithText right = mTabIndicators.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }

    }

    @Override
    public void onPageSelected(int position) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // TODO Auto-generated method stub

    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.i("晴空", "onRequestPermissionsResult: requestCode===="+requestCode);
        switch (requestCode) {
            case REQUESTCODE_WRITE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

}

