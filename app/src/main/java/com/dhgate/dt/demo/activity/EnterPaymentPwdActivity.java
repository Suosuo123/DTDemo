package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.widget.TextView;

import com.dhgate.dt.demo.MainApplication;
import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.utils.CommonUtils;
import com.jungly.gridpasswordview.GridPasswordView;

import butterknife.BindView;

/**
 * Created by flora on 2018/4/11.
 */

public class EnterPaymentPwdActivity extends BaseActivity {

    public static final String PAYMENT_AMOUNT = "payment_amount";
    public double amount;

    @BindView(R.id.gridpassword)
    GridPasswordView gridPasswordView;

    @BindView(R.id.payment_amount)
    TextView payment_amount_tv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_payment_pwd;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("请输入支付密码");

        amount = getIntent().getDoubleExtra(PAYMENT_AMOUNT, 0);
        payment_amount_tv.setText(CommonUtils.to2(amount));

        gridPasswordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                // add your code here
            }

            @Override
            public void onInputFinish(String psw) {
                MainApplication application = (MainApplication) getApplication();
                double usdBalance = application.getUsdBalanceDouble();
                double leftUsdBalance = usdBalance - amount;
                application.setUsdBalance(leftUsdBalance);

                Intent intent = new Intent(EnterPaymentPwdActivity.this, PaymentSuccessActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
