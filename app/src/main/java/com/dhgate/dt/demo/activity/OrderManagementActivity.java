package com.dhgate.dt.demo.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.fragment.AddProductFragment;
import com.dhgate.dt.demo.fragment.OrderListFragment1;
import com.dhgate.dt.demo.fragment.OrderListFragment2;
import com.dhgate.dt.demo.utils.CommonUtils;
import com.dhgate.dt.demo.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class OrderManagementActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_management;
    }

    @Bind(R.id.tabs)
    public PagerSlidingTabStrip mPagerSlidingTabStrip;

    @Bind(R.id.pager)
    public ViewPager mViewPager;

    private MyPagerAdapter mPagerAdapter;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate() {
        super.onCreate();

        setActionTitle("订单管理");
    }

    @Override
    protected void initView() {
        super.initView();

        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mFragments.add(OrderListFragment1.newInstance());
        mFragments.add(OrderListFragment2.newInstance());
        mViewPager.setAdapter(mPagerAdapter);
        mPagerAdapter.update(mFragments);

        mPagerSlidingTabStrip.setViewPager(mViewPager);

        initTabsValue();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    /**
     * mPagerSlidingTabStrip默认值配置
     */
    private void initTabsValue() {
        // 底部游标颜色
        mPagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.text_blue1));
        // tab的分割线颜色
        mPagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);
        // tab背景
        mPagerSlidingTabStrip.setBackgroundColor(getResources().getColor(R.color.white));
        // tab底线高度
        mPagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()));
        // 游标高度
        mPagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
        // 选中的文字颜色
        mPagerSlidingTabStrip.setSelectedTextColor(getResources().getColor(R.color.text_blue1));
        // 正常文字颜色
        mPagerSlidingTabStrip.setTextColor(getResources().getColor(R.color.text_gray));
        mPagerSlidingTabStrip.setTextSize(CommonUtils.dip2pixel(mActivity,15));
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"未出运订单", "已出运订单"};

        private List<Fragment> list = new ArrayList<>();

        public void update(List<Fragment> list) {
            this.list = list;
            notifyDataSetChanged();
        }


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

    }
}
