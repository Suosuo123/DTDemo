package com.dhgate.dt.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
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
        bundle.putInt("type", count);
        f.setArguments(bundle);
        return f;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_product_management;
    }

    @BindView(R.id.lv_product)
    public ListView lv_product;

    @BindView(R.id.checkbox)
    public CheckBox checkbox;

    @OnClick(R.id.tv_add_to_order)
    public void addToOrderClick() {
        if (lv_product.getCheckedItemCount() <= 0) {
            WinToast.toast(mActivity, "请选择商品");
            return;
        }
        if (mType == 1) {
            Intent intent = new Intent(mActivity, CreateOrderActivity.class);
            startActivity(intent);
        } else if (mType == 2) {
            ((CreateOrderActivity) mActivity).changePageToOrder(lv_product.getCheckedItemCount());
        }

        selectAll(false);
    }

    private void selectAll(boolean selectAll) {
        for (int i = 0; i < lv_product.getCount(); i++) {
            lv_product.setItemChecked(i, selectAll);
        }
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_add)
    public void addProductClick() {
        Intent intent = new Intent(mActivity, EditProductActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.rel_select_all)
    public void selectAllClick() {
        if (mSelectAll) {
            selectAll(false);
        } else {
            selectAll(true);
        }
        mSelectAll = !mSelectAll;
        checkbox.setChecked(mSelectAll);
    }


    private boolean mSelectAll;

    private ProductManagementListAdapter mAdapter;
    private List<Product> mList = new ArrayList<>();

    //1:添加商品 2:创建订单
    private int mType;

    @Override
    protected void initView() {

        mType = getArguments().getInt("type", 0);

        mAdapter = new ProductManagementListAdapter(mActivity, lv_product);
        lv_product.setAdapter(mAdapter);
        lv_product.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        mList.add(new Product(R.mipmap.manage_product1, "蓝牙插卡电话智能手表", "MD-90112", 0));
        mList.add(new Product(R.mipmap.manage_product2, "苹果原装液态硅胶手机保护套", "iPhoneX", 0));
        mList.add(new Product(R.mipmap.manage_product3, "挂耳式无线音乐蓝牙耳机", "4.1", 0));
        mList.add(new Product(R.mipmap.manage_product4, "无线蓝牙降噪头戴式耳机", "PLAY H9", 0));
        mList.add(new Product(R.mipmap.manage_product5, "带灯插卡金属音箱", "a10", 0));
        mList.add(new Product(R.mipmap.manage_product1, "蓝牙插卡电话智能手表", "MD-90112", 0));
        mList.add(new Product(R.mipmap.manage_product2, "苹果原装液态硅胶手机保护套", "iPhoneX", 0));
        mList.add(new Product(R.mipmap.manage_product3, "挂耳式无线音乐蓝牙耳机", "4.1", 0));
        mList.add(new Product(R.mipmap.manage_product4, "无线蓝牙降噪头戴式耳机", "PLAY H9", 0));
        mList.add(new Product(R.mipmap.manage_product5, "带灯插卡金属音箱", "a10", 0));

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