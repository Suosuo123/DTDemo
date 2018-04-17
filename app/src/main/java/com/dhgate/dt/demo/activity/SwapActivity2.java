package com.dhgate.dt.demo.activity;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dhgate.dt.demo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by flora on 2018/4/14.
 */

public class SwapActivity2 extends BaseActivity {

    @BindView(R.id.usd_et)
    EditText usd_et;

    @BindView(R.id.confirm_btn_gray)
    ImageView confirm_btn_gray;

    @BindView(R.id.confirm_btn_blue)
    ImageView confirm_btn_blue;

    @BindView(R.id.swap_success_layout)
    RelativeLayout swap_success_layout;

    @OnClick(R.id.confirm_btn_blue)
    public void onConfirmBtnClick() {
        swap_success_layout.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_swap2;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("换汇");
    }

    @Override
    protected void initView() {
        super.initView();
        usd_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    confirm_btn_gray.setVisibility(View.GONE);
                    confirm_btn_blue.setVisibility(View.VISIBLE);
                } else {
                    confirm_btn_gray.setVisibility(View.VISIBLE);
                    confirm_btn_blue.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
