package com.dhgate.dt.demo.activity;

import com.dhgate.dt.demo.R;

public class TransferActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate() {
        super.onCreate();

        setActionTitle("转账");
    }
}
