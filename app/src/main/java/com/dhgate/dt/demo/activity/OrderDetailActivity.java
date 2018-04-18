package com.dhgate.dt.demo.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dhgate.dt.demo.R;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @BindView(R.id.iv_content_image1)
    public ImageView iv_content_image1;

    @BindView(R.id.iv_content_image2)
    public ImageView iv_content_image2;

    @BindView(R.id.ll_more)
    public LinearLayout ll_more;

    @BindView(R.id.iv_order_detail4)
    public ImageView iv_order_detail4;

    @BindView(R.id.iv_order_detail5)
    public ImageView iv_order_detail5;

    @OnClick(R.id.rel_open)
    public void openClick() {
        if (isOpen) {
            iv_order_detail5.setVisibility(View.GONE);
            iv_order_detail4.setVisibility(View.VISIBLE);
            ll_more.setVisibility(View.GONE);
        } else {
            iv_order_detail4.setVisibility(View.GONE);
            iv_order_detail5.setVisibility(View.VISIBLE);
            ll_more.setVisibility(View.VISIBLE);
        }
        isOpen = !isOpen;
    }

    private boolean isOpen = false;

    private int mType;

    @Override
    protected void onCreate() {
        super.onCreate();

        mType = getIntent().getIntExtra("type", -1);

        setActionTitle("查看订单");

    }

    @Override
    protected void initView() {
        super.initView();

        if (mType == 1) {
            iv_content_image1.setVisibility(View.GONE);
            iv_content_image2.setVisibility(View.VISIBLE);
        } else {
            iv_content_image1.setVisibility(View.VISIBLE);
            iv_content_image2.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
