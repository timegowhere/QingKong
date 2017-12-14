package com.qingkong.weixin.Activity;






import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.qingkong.weixin.Fragment.FirstDetailFragment;
import com.qingkong.weixin.Fragment.FirstFragment;
import com.qingkong.weixin.Fragment.MyAlertDialogFragment;
import com.qingkong.weixin.R;
import com.qingkong.weixin.utils.LogUtil;
import com.qingkong.weixin.utils.ToastUtil;

public class MyWalletActivity extends AppCompatActivity implements FirstFragment.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.fly,new FirstFragment(),"firstFragment");
//        transaction.add(R.id.flyDetail, new FirstDetailFragment(),"firstDetailFragment");
//        transaction.commit();

//        FirstFragment firstFragment  = (FirstFragment) getFragmentManager().findFragmentById(R.id.firstFragment);



    }

    @Override
    public void updateText(String string) {
        LogUtil.logE("updateText=string="+string);
        FirstDetailFragment fragment = (FirstDetailFragment) getSupportFragmentManager().findFragmentById(R.id.firstDetailFragment);
        fragment.setText(string);

//        MyAlertDialogFragment myAlertDialogFragment = new MyAlertDialogFragment();
//        myAlertDialogFragment.show(getSupportFragmentManager(),"myAlertDialogFragment");


        ToastUtil.showSnackBar(getWindow().getDecorView(),string);
    }
}
