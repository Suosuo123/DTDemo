package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.dhgate.dt.demo.R;

import butterknife.OnClick;

public class SendApplyActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Nullable
    @OnClick(R.id.btn_start)
    public void start() {
        Intent intent = new Intent(mActivity, AddProductActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        setActionTitle("送仓申请");
    }
}
