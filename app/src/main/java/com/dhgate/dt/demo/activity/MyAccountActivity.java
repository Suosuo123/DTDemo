package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhgate.dt.demo.MainApplication;
import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.widget.slidingUpPanel.SlidingUpPanelLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by flora on 2018/4/12.
 */

public class MyAccountActivity extends BaseActivity {

    //    @BindView(R.id.sliding_layout)
    @BindView(R.id.sliding_layout)
    public SlidingUpPanelLayout sliding_layout;

    @BindView(R.id.rel_complete_order)
    RelativeLayout rel_complete_order;

    @BindView(R.id.account_balance)
    TextView account_balance;

    public MainApplication application;

    @OnClick(R.id.myaccount_img_2)
    public void onAccountClick() {
        Intent intent = new Intent(MyAccountActivity.this, USDAccountActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.myaccount_img_4)
    public void onReceiveClick() {
        Intent intent = new Intent(MyAccountActivity.this, AddProductActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.myaccount_img_5)
    public void onPayClick() {
        Intent intent = new Intent(MyAccountActivity.this, AddProductActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv_pay_back)
    public void payBackClick() {
        sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

    @OnClick(R.id.my_bisness_2)
    public void onBtn2Click() {
        Intent intent = new Intent(mActivity, OrderManagementActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.my_bisness_3)
    public void onBtn3Click() {

    }

    @OnClick(R.id.my_bisness_4)
    public void onBtn4Click() {

    }

    @OnClick(R.id.my_bisness_5)
    public void onBtn5Click() {

    }

    @OnClick(R.id.my_bisness_6)
    public void onBtn6Click() {

    }

    @OnClick(R.id.user_img_icon)
    public void OnUserImgIconClick() {
        Intent intent = new Intent(MyAccountActivity.this, MyAccountQRActivity.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myaccount;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setSwipeBackEnable(false);
        application = (MainApplication) getApplication();
    }

    @Override
    protected void initView() {
        super.initView();
        initSlidingLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        account_balance.setText(application.getBalanceStr());
    }

    private void initSlidingLayout() {
        sliding_layout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                rel_complete_order.setVisibility(View.GONE);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    rel_complete_order.setVisibility(View.GONE);
                    initWindow(R.color.text_blue2);
                } else if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    rel_complete_order.setVisibility(View.VISIBLE);
                    initWindow(R.color.main_gray);
                }
            }
        });
        sliding_layout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (rel_complete_order.getVisibility() == View.GONE) {
            sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}
