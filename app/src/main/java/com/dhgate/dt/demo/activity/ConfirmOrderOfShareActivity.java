package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.adapter.ConfirmOrderProductListAdapter;
import com.dhgate.dt.demo.entity.Product;
import com.dhgate.dt.demo.widget.WrapContentListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfirmOrderOfShareActivity extends BaseActivity {

    @BindView(R.id.lv_product)
    public WrapContentListView lv_product;

    @BindView(R.id.ll_bottom2)
    public LinearLayout ll_bottom2;

    @OnClick(R.id.btn_edit)
    public void editClick() {
    }

    @OnClick(R.id.btn_agree)
    public void agreeClick() {
        finish();
    }

    @OnClick(R.id.btn_refuse)
    public void refuseClick() {
        finish();
    }


    @OnClick(R.id.btn_confirm)
    public void confirmClick() {
        Intent intent = new Intent(mActivity, EnterPaymentPwdActivity.class);
        intent.putExtra(EnterPaymentPwdActivity.PAYMENT_AMOUNT, 7643.00);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm_order_share;
    }

    private ConfirmOrderProductListAdapter mAdapter;
    private List<Product> mList = new ArrayList<>();

    private int mTotalCount = 3;

    private int mType;

    @Override
    protected void onCreate() {
        super.onCreate();

        mTotalCount = getIntent().getIntExtra("count", 3);
        mType = getIntent().getIntExtra("type", -1);

        if (mType == 1) {
            ll_bottom2.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void initView() {
        super.initView();

        setActionTitle("确认订单");

        mAdapter = new ConfirmOrderProductListAdapter(mActivity);
        lv_product.setAdapter(mAdapter);

        for (int i = 0; i < mTotalCount; i++) {
            mList.add(new Product());
        }

        mAdapter.bindData(mList);
    }
}
