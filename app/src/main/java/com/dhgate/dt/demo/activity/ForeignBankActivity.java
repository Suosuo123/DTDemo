package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bigkoo.pickerview.utils.PickerViewAnimateUtil;
import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.utils.AndroidBug54971Workaround;

import butterknife.OnClick;

public class ForeignBankActivity extends BaseActivity {

    @OnClick(R.id.iv_share)
    public void shareClick() {
        showShare();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_foreign_bank;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        setActionTitle("渣打银行账户");
    }

    @Override
    protected void initView() {
        super.initView();

        initShare();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    /**
     * 初始化分享页面
     */
    private void initShare() {
        inAnim = getInAnimation();
        outAnim = getOutAnimation();

        if (mDecorView == null) {
            mDecorView = (ViewGroup) (mActivity).getWindow().getDecorView();
        }
        rootView = LayoutInflater.from(mActivity).inflate(R.layout.layout_share, mDecorView, false);
        contentContainer = rootView.findViewById(R.id.content_container);

        contentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        View outView = rootView.findViewById(R.id.outmost_container);
        outView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dismissShare();
                }
                return false;
            }
        });

        View closeView = rootView.findViewById(R.id.iv_close_share);
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissShare();
            }
        });


    }


    private View rootView;
    private View contentContainer;

    private ViewGroup mDecorView;

    private boolean isShowing;

    private boolean dismissing;

    private Animation outAnim;
    private Animation inAnim;

    protected int animGravity = Gravity.BOTTOM;

    public Animation getInAnimation() {
        int res = PickerViewAnimateUtil.getAnimationResource(this.animGravity, true);
        return AnimationUtils.loadAnimation(mActivity, res);
    }

    public Animation getOutAnimation() {
        int res = PickerViewAnimateUtil.getAnimationResource(this.animGravity, false);
        return AnimationUtils.loadAnimation(mActivity, res);
    }


    /**
     * 打开share页面
     */
    public void showShare() {

        if (rootView.getParent() != null || isShowing) {
            return;
        }
        isShowing = true;
        mDecorView.addView(rootView);
        contentContainer.startAnimation(inAnim);
        rootView.requestFocus();

        AndroidBug54971Workaround.assistActivity(rootView);
    }

    /**
     * 关闭share页面
     */
    public void dismissShare() {
        if (dismissing) {
            return;
        }
        //消失动画
        outAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDecorView.post(new Runnable() {
                    @Override
                    public void run() {
                        //从根视图移除
                        mDecorView.removeView(rootView);
                        isShowing = false;
                        dismissing = false;

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        contentContainer.startAnimation(outAnim);
        dismissing = true;
    }
}
