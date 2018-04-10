package com.dhgate.dt.demo.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.fragment.AddProductFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class AddProductActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_product;
    }

    @Bind(R.id.viewPager)
    public ViewPager viewPager;

    @Bind(R.id.iv_left)
    public ImageView iv_left;

    @Bind(R.id.iv_right)
    public ImageView iv_right;

    @OnClick(R.id.iv_left)
    public void leftClick() {
        iv_left.setImageResource(R.mipmap.left_selected);
        iv_right.setImageResource(R.mipmap.right_normal);
        viewPager.setCurrentItem(0,true);
    }

    @OnClick(R.id.iv_right)
    public void rightClick() {
        iv_left.setImageResource(R.mipmap.left_normal);
        iv_right.setImageResource(R.mipmap.right_selected);
        viewPager.setCurrentItem(1,true);
    }

    private MyPagerAdapter mPagerAdapter;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate() {
        super.onCreate();

        setActionTitle("订单");

        mFragments = new ArrayList<>();
        mFragments.add(AddProductFragment.newInstance());
        mFragments.add(AddProductFragment.newInstance());
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                iv_left.setImageResource(R.mipmap.left_selected);
                iv_right.setImageResource(R.mipmap.right_normal);
                break;
            case 1:
                iv_left.setImageResource(R.mipmap.left_normal);
                iv_right.setImageResource(R.mipmap.right_selected);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

    }
}
