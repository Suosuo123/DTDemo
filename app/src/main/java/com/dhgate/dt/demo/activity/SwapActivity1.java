package com.dhgate.dt.demo.activity;

import android.content.Intent;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.wheelview.adapter.SwapAdapter;
import com.dhgate.dt.demo.wheelview.adapter.WheelViewBean;
import com.dhgate.dt.demo.wheelview.wheel.WheelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by flora on 2018/4/14.
 */

public class SwapActivity1 extends BaseActivity {

    @Bind(R.id.wv1)
    WheelView wheelView;

    @Bind(R.id.wv2)
    WheelView wheelView2;

    @OnClick(R.id.swap_btn)
    public void onBtnClick() {
        Intent intent = new Intent(SwapActivity1.this, SwapActivity2.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_swap;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("换汇");
    }

    @Override
    protected void initView() {
        super.initView();
        final List<WheelViewBean> wheelViewBeans = new ArrayList<>();
        wheelViewBeans.add(new WheelViewBean(WheelViewBean.ICON_CNY));
        wheelViewBeans.add(new WheelViewBean(WheelViewBean.ICON_USD));
        wheelView.setAdapter(new SwapAdapter(wheelViewBeans));
        wheelView.setPosition(1);

        final List<WheelViewBean> wheelViewBeans2 = new ArrayList<>();
        wheelViewBeans2.add(new WheelViewBean(WheelViewBean.ICON_CNY));
        wheelViewBeans2.add(new WheelViewBean(WheelViewBean.ICON_USD));
        wheelView2.setAdapter(new SwapAdapter(wheelViewBeans2));
    }
}
