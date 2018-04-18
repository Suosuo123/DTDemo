package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.widget.EditText;

import com.dhgate.dt.demo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by flora on 2018/4/11.
 */

public class PaymentActivity extends BaseActivity {

    @BindView(R.id.payment_amount_et)
    EditText payment_amount_et;

    @OnClick(R.id.confirm_payment_btn)
    public void onConfirmClick() {
        Intent intent = new Intent(PaymentActivity.this, EnterPaymentPwdActivity.class);
        String payment_amount_str = payment_amount_et.getText().toString();
        if (payment_amount_str.length() > 0) {
            double payment_amout_double = Double.parseDouble(payment_amount_str);
            intent.putExtra(EnterPaymentPwdActivity.PAYMENT_AMOUNT, payment_amout_double);
        }
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("订单支付");
    }
}
