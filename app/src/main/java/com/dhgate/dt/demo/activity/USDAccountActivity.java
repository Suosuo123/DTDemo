package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import com.dhgate.dt.demo.R;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by flora on 2018/4/12.
 */

public class USDAccountActivity extends BaseActivity {

    @BindView(R.id.click_img1)
    ImageView click_img1;

    @BindView(R.id.click_img2)
    ImageView click_img2;

    @OnClick(R.id.btn_1)
    public void onBtn1Click() {
        Intent intent = new Intent(USDAccountActivity.this, SwapActivity1.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_2)
    public void onBtn2Click() {

    }

    @OnClick(R.id.click_img1)
    public void onImg1Click() {
        click_img1.setVisibility(View.GONE);
        click_img2.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.click_img2)
    public void onImg2Click() {
        click_img2.setVisibility(View.GONE);
        click_img1.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_usd_account;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("USD账户");
        setRightImg(R.mipmap.usd_account_8);
    }

}
