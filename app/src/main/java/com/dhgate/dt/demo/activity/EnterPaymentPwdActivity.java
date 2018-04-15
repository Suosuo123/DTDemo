package com.dhgate.dt.demo.activity;

import android.content.Intent;

import com.dhgate.dt.demo.R;
import com.jungly.gridpasswordview.GridPasswordView;

import butterknife.Bind;

/**
 * Created by flora on 2018/4/11.
 */

public class EnterPaymentPwdActivity extends BaseActivity {

    @Bind(R.id.gridpassword)
    GridPasswordView gridPasswordView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_payment_pwd;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("请输入支付密码");
        gridPasswordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                // add your code here
            }

            @Override
            public void onInputFinish(String psw) {
                Intent intent = new Intent(EnterPaymentPwdActivity.this, PaymentSuccessActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
