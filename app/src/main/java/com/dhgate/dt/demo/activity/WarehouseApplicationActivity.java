package com.dhgate.dt.demo.activity;

import com.dhgate.dt.demo.R;

import butterknife.OnClick;

/**
 * Created by flora on 2018/4/12.
 */

public class WarehouseApplicationActivity extends BaseActivity {

    @OnClick(R.id.btn_1)
    public void onBtnClick() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_warehouse_application;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("出仓申请");
    }
}
