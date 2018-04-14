package com.dhgate.dt.demo.activity;


import com.dhgate.dt.demo.R;
import butterknife.OnClick;

/**
 * Created by flora on 2018/4/14.
 */

public class PersonalInfoActivity extends BaseActivity {

    @OnClick(R.id.confirm_btn)
    public void onConfirmBtnClick() {
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("个人信息");
    }
}
