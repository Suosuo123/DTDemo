package com.dhgate.dt.demo.activity;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.widget.WinToast;

import butterknife.OnClick;

public class ExportManagementActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_export_management;
    }

    @OnClick(R.id.iv_action1)
    public void start() {
//        Intent intent = new Intent(mActivity, AddProductActivity.class);
//        startActivity(intent);
        WinToast.toast(mActivity, "发票");
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        setActionTitle("货物出口管理");
    }
}
