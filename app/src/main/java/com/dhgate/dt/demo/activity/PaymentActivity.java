package com.dhgate.dt.demo.activity;

/**
 * Created by flora on 2018/4/11.
 */

public class PaymentActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("订单支付");
    }
}
