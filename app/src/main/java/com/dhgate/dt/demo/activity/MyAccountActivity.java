package com.dhgate.dt.demo.activity;

import android.content.Intent;

import com.dhgate.dt.demo.R;
import butterknife.OnClick;

/**
 * Created by flora on 2018/4/12.
 */

public class MyAccountActivity extends BaseActivity {

    @OnClick(R.id.myaccount_img_2)
    public void onAccountClick() {
        Intent intent = new Intent(MyAccountActivity.this, USDAccountActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.myaccount_img_4)
    public void onReceiveClick() {

    }

    @OnClick(R.id.myaccount_img_5)
    public void onPayClick() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myaccount;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }
}
