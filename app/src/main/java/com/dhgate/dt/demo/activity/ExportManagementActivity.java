package com.dhgate.dt.demo.activity;

import android.content.Intent;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.widget.WinToast;

import butterknife.OnClick;

public class ExportManagementActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_export_management;
    }

    @OnClick(R.id.iv_action7)
    public void start() {
        Intent intent = new Intent(mActivity, WarehouseApplicationActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        setActionTitle("货物出口管理");
    }
}
