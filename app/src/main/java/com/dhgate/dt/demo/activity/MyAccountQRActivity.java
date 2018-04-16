package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.dhgate.dt.demo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by flora on 2018/4/12.
 */

public class MyAccountQRActivity extends BaseActivity {

    @OnClick(R.id.join_icon)
    public void onJoinIconClick() {
        qrcode_layout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.exist_icon)
    public void onExistIconClick() {

    }

    @OnClick(R.id.iv_help)
    public void onHelpIconClick() {

    }

    @OnClick(R.id.iv_back)
    public void onBackIconClick() {
        finish();
    }

    @OnClick(R.id.layout_1)
    public void onLayout1Click() {
        Intent intent = new Intent(mActivity, ForeignBankActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layout_2)
    public void onLayout2Click() {
        Intent intent = new Intent(mActivity, AccountManagementActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layout_3)
    public void onLayout3Click() {
        Intent intent = new Intent(mActivity, PersonalInfoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layout_4)
    public void onLayout4Click() {
        Intent intent = new Intent(mActivity, CompanyInfoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layout_5)
    public void onLayout5Click() {

    }

    @OnClick(R.id.layout_6)
    public void onLayout6Click() {
        Intent intent = new Intent(mActivity, SelectContactActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.qrcode_layout)
    public void OnQRLayoutClick() {
        qrcode_layout.setVisibility(View.GONE);
    }

    @BindView(R.id.qrcode_layout)
    RelativeLayout qrcode_layout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myaccount_qr;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
//        initWindow(R.color.text_blue3);
        fullScreen();
    }
}
