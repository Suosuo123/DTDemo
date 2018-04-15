package com.dhgate.dt.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.activity.CreateOrderActivity;
import com.dhgate.dt.demo.activity.EditProductActivity;
import com.dhgate.dt.demo.adapter.ProductManagementListAdapter;
import com.dhgate.dt.demo.entity.Product;
import com.dhgate.dt.demo.widget.WinToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductsManagementFragment extends BaseFragment {


    public static ProductsManagementFragment newInstance(int count) {
        ProductsManagementFragment f = new ProductsManagementFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count", count);
        f.setArguments(bundle);
        return f;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_product_management;
    }

    @BindView(R.id.lv_product)
    public ListView lv_product;

    @OnClick(R.id.tv_add_to_order)
    public void addToOrderClick() {
        if (lv_product.getCheckedItemCount() <= 0) {
            WinToast.toast(mActivity, "请选择商品");
            return;
        }

        ((CreateOrderActivity) mActivity).changePageToOrder(lv_product.getCheckedItemCount());
    }

    @OnClick(R.id.iv_add)
    public void addProductClick() {
        Intent intent = new Intent(mActivity, EditProductActivity.class);
        startActivity(intent);
    }


    private ProductManagementListAdapter mAdapter;
    private List<Product> mList = new ArrayList<>();

    private int mTotalCount = 3;


    @Override
    protected void initView() {

        mTotalCount = getArguments().getInt("count", mTotalCount);

        mAdapter = new ProductManagementListAdapter(mActivity, lv_product);
        lv_product.setAdapter(mAdapter);
        lv_product.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        for (int i = 0; i < mTotalCount; i++) {
            mList.add(new Product());
        }

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

    }

}