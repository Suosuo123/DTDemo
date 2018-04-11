package com.dhgate.dt.demo.activity;

import android.widget.ImageView;

import com.dhgate.dt.demo.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by flora on 2018/4/11.
 */

public class PaymentActivity extends BaseActivity {

    @OnClick(R.id.confirm_payment_btn)
    public void onConfirmClick() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("订单支付");
    }
}
