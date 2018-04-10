package com.dhgate.dt.demo.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.activity.ProductListActivity;
import com.dhgate.dt.demo.utils.CommonUtils;
import com.dhgate.dt.demo.widget.WinToast;

import butterknife.Bind;

public class AddProductFragment extends BaseFragment {

    public static AddProductFragment newInstance() {
        AddProductFragment f = new AddProductFragment();
        return f;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_add_product;
    }

    @Bind(R.id.et_search)
    public EditText et_search;

    @Override
    protected void initView() {
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    String key = et_search.getText().toString().trim();

                    if (TextUtils.isEmpty(key)) {
                        WinToast.toast(mActivity, "请输入商品名称");
                        return true;
                    }

                    CommonUtils.hideInputMethod(mActivity);

                    Intent intent = new Intent(mActivity, ProductListActivity.class);
                    intent.putExtra("key", key);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {

    }

}