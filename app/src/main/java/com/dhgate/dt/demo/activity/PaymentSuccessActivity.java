package com.dhgate.dt.demo.activity;

import android.content.Intent;

import com.dhgate.dt.demo.R;

import butterknife.OnClick;

/**
 * Created by flora on 2018/4/11.
 */

public class PaymentSuccessActivity extends BaseActivity {

    @OnClick(R.id.check_order_icon)
    public void onCheckOrderClick() {
        Intent intent = new Intent(mActivity, OrderDetailActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.return_icon)
    public void onReturnClick() {
        Intent intent = new Intent(mActivity, MyAccountActivity.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_payment_success;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }
}
