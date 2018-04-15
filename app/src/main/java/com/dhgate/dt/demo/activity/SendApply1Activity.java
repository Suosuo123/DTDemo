package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.adapter.SendApplyProductListAdapter;
import com.dhgate.dt.demo.entity.SendApplyProduct;
import com.dhgate.dt.demo.widget.WinToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SendApply1Activity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_apply1;
    }

    @BindView(R.id.cb_all)
    public CheckBox cb_all;

    @BindView(R.id.lv_product)
    public ListView lv_product;

    @OnClick(R.id.iv_make_package)
    public void start() {
        if (!mAllSelected) {
            WinToast.toast(mActivity, "请选择所有商品");
            return;
        }
        Intent intent = new Intent(mActivity, SendApply2Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rel_check)
    public void selectAll() {
        if (mAllSelected) {
            for (int i = 0; i < mAdapter.getCount(); i++) {
                lv_product.setItemChecked(i, false);
            }
        } else {
            for (int i = 0; i < mAdapter.getCount(); i++) {
                lv_product.setItemChecked(i, true);
            }
        }
        mAdapter.notifyDataSetChanged();
        mAllSelected = !mAllSelected;
        cb_all.setChecked(mAllSelected);
    }

    private SendApplyProductListAdapter mAdapter;
    private List<SendApplyProduct> mList = new ArrayList<>();

    private boolean mAllSelected = false;

    @Override
    protected void onCreate() {
        super.onCreate();

        setActionTitle("送仓申请");

    }


    @Override
    protected void initView() {
        super.initView();
        cb_all.setEnabled(false);
        mAdapter = new SendApplyProductListAdapter(mActivity, lv_product);
        lv_product.setAdapter(mAdapter);
        lv_product.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        mList.add(new SendApplyProduct(R.mipmap.img_send_product1, "蓝牙插卡电话智能手表", "MD-90112", 40));
        mList.add(new SendApplyProduct(R.mipmap.img_send_product2, "无线蓝牙降噪头戴式耳机", "PLAY H9", 70));
        mList.add(new SendApplyProduct(R.mipmap.img_send_product3, "苹果原装液态硅胶手机保护套", "iphoneX", 865));

        mAdapter.updateList(mList);

        lv_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
