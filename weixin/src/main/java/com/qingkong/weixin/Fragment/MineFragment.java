package com.qingkong.weixin.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingkong.weixin.Activity.MyWalletActivity;
import com.qingkong.weixin.R;
import com.qingkong.weixin.utils.LogUtil;
import com.qingkong.weixin.utils.UiUtils;
import com.qingkong.weixin.utils.Util;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by yanghq on 2017/11/15.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private static final int REQUEST_IMAGE_PICK = 1;
    private View rootView;
    private ImageView ivHeadshot;
    private TextView tvAccountName;
    private ImageView ivQRCode;
    private LinearLayout llytAccountMsg;
    private LinearLayout llytWallet;
    private LinearLayout llytShare;
    private Bitmap bmpHeadshot;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_mine, container, false);

        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivHeadshot = view.findViewById(R.id.ivHeadshot);

        tvAccountName = view.findViewById(R.id.tvAccountName);
        ivQRCode = view.findViewById(R.id.ivQRCode);
        llytAccountMsg = view.findViewById(R.id.llytAccountMsg);
        llytAccountMsg.setOnClickListener(this);
        llytWallet = view.findViewById(R.id.llytWallet);
        llytWallet.setOnClickListener(this);
        llytShare = view.findViewById(R.id.llytShare);
        llytShare.setOnClickListener(this);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llytAccountMsg:
                pickImage();
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
//                startActivity(intent);
                break;
            case R.id.llytWallet:
                startActivity(new Intent(getActivity(), MyWalletActivity.class));
                break;
            case R.id.llytShare:

                break;
        }
    }

    /**
     * 选取本地照片
     */
    public void pickImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.logE("requestCode=="+requestCode);
        LogUtil.logE("resultCode=="+resultCode);
        LogUtil.logE("data=="+data);
//        LogUtil.logE("data.getData();=="+ data.getData());
        if (resultCode== Activity.RESULT_OK){
            switch (requestCode){
                case REQUEST_IMAGE_PICK:
                    if (data != null) {

                        try (InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());){
                            // recyle unused bitmaps
                            if (bmpHeadshot != null) {
                                bmpHeadshot.recycle();
                            }

                            bmpHeadshot = BitmapFactory.decodeStream(inputStream);
                            ivHeadshot.setImageBitmap(bmpHeadshot);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                    break;
            }
        }
       
    }
}
