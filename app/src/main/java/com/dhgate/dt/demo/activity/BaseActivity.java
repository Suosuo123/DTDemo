package com.dhgate.dt.demo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhgate.dt.demo.MainApplication;
import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.utils.AndroidBug54971Workaround;
import com.dhgate.dt.demo.widget.swipeBackLayout.ui.SwipeBackActivity;
import com.dhgate.dt.demo.widget.uiView.UIImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


@SuppressLint("NewApi")
public abstract class BaseActivity extends SwipeBackActivity {

    protected Activity mActivity;

    /**
     * 导航条
     */
    @Nullable
    @Bind(R.id.rel_bar)
    public RelativeLayout rel_bar;
    /**
     * 返回
     */
    @Nullable
    @Bind(R.id.iv_back)
    public UIImageView iv_back;
    /**
     * 标题
     */
    @Nullable
    @Bind(R.id.tv_title)
    public TextView tv_title;

    @Nullable
    @OnClick(R.id.iv_back)
    public void back() {
        finish();
    }

    @Nullable
    @Bind(R.id.iv_right)
    public UIImageView iv_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;

        initWindow(R.color.main_gray);

        setContentView(getLayoutId());

        ButterKnife.bind(mActivity);

        setSwipeBackEnable(true);//禁止滑动关闭界面

        onCreate();
        initView();
        initData();

        //统一管理activity
        MainApplication.getApplication().addActivity(mActivity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);

        //统一管理activity
        MainApplication.getApplication().removeActivity(mActivity);
    }


    protected abstract int getLayoutId();


    protected void onCreate() {
    }

    protected void initView() {
    }

    protected void initData() {
    }

    /**
     * 设置actionbar颜色
     */
    public void setActionBarWhite() {
        if (rel_bar != null) {
            rel_bar.setBackgroundColor(getColor(R.color.white));
        }
        initWindow(R.color.white);
    }

    /**
     * 设置actionbar title
     *
     * @param title 页面标题
     */
    public void setActionTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
//            if (tv_title != null) {
            tv_title.setText(title);
//            }
        }

    }

    public void setRightImg(int drawableId) {
        if (iv_right != null) {
            iv_right.setVisibility(View.VISIBLE);
            iv_right.setImageResource(drawableId);
        }
    }


    public void initWindow(int colorResId) {

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 19) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(getColor(colorResId));
//            window.setNavigationBarColor(getColor(colorResId));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

//     window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    public void fullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

}
