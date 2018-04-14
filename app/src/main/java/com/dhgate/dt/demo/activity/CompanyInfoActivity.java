package com.dhgate.dt.demo.activity;

import android.view.View;
import android.widget.ImageView;

import com.dhgate.dt.demo.R;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by flora on 2018/4/14.
 */

public class CompanyInfoActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_companyinfo;
    }

    @Bind(R.id.confirm_btn)
    ImageView confirm_btn;

    @Bind(R.id.return_btn)
    ImageView return_btn;

    @Bind(R.id.company_img_2)
    ImageView company_img_2;

    @OnClick(R.id.confirm_btn)
    public void onConfirmClick() {
        company_img_2.setBackgroundResource(R.mipmap.company_info_5);
        confirm_btn.setVisibility(View.GONE);
        return_btn.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.return_btn)
    public void onReturnClick() {
        finish();
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("企业信息");
    }
}
