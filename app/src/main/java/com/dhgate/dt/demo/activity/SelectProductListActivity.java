package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.adapter.SelectProductListAdapter;
import com.dhgate.dt.demo.entity.Product;
import com.dhgate.dt.demo.widget.WinToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectProductListActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_list;
    }

    @BindView(R.id.et_search)
    public EditText et_search;

    @BindView(R.id.lv_product)
    public ListView lv_product;

    @Nullable
    @OnClick(R.id.iv_clear)
    public void clear() {
        et_search.setText("");
    }

    @OnClick(R.id.tv_add)
    public void add() {
        if (lv_product.getCheckedItemCount() <= 0) {
            WinToast.toast(mActivity, "请选择商品");
            return;
        }
        Intent intent = new Intent(mActivity, CreateOrderActivity.class);
        intent.putExtra("count", lv_product.getCheckedItemCount());
        startActivity(intent);
    }

    private SelectProductListAdapter madAdapter;
    private List<Product> mList = new ArrayList<>();

    private String mKey;

    @Override
    protected void onCreate() {
        super.onCreate();

        setActionBarWhite();

        mKey = getIntent().getStringExtra("key");

        if (!TextUtils.isEmpty(mKey)) {
            et_search.setText(mKey);
            et_search.setSelection(mKey.length());
        }

        madAdapter = new SelectProductListAdapter(mActivity, lv_product);
        lv_product.setAdapter(madAdapter);
        lv_product.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        mList.add(new Product(R.mipmap.search_product1, "无线蓝牙降噪头戴式耳机", "PLAY H9", 0));
        mList.add(new Product(R.mipmap.search_product2, "Bose QuietComfort 35", "QC35", 0));
        mList.add(new Product(R.mipmap.search_product3, "Hi-Res蓝牙耳机", "WH-1000XM2", 0));
        mList.add(new Product(R.mipmap.search_product4, "挂耳式无线音乐蓝牙耳机", "4.1", 0));

        madAdapter.bindData(mList);

        lv_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                madAdapter.notifyDataSetChanged();
            }
        });

    }
}
