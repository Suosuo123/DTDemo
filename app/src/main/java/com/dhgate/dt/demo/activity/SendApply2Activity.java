package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.bigkoo.pickerview.utils.PickerViewAnimateUtil;
import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.utils.AndroidBug54971Workaround;
import com.dhgate.dt.demo.widget.slidingUpPanel.SlidingUpPanelLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class SendApply2Activity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_apply2;
    }

    @BindView(R.id.sliding_layout)
    public SlidingUpPanelLayout sliding_layout;

    @BindView(R.id.rel_complete_order)
    public RelativeLayout rel_complete_order;

    @BindView(R.id.rel_package)
    public RelativeLayout rel_package;


    @OnClick(R.id.iv_success_back1)
    public void back1Click() {
        Intent intent = new Intent(mActivity, OrderManagementActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv_success_back2)
    public void back2Click() {
        Intent intent = new Intent(mActivity, MyAccountActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv_success_back)
    public void payBackClick() {
        sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }


    @Override
    protected void onCreate() {
        super.onCreate();

        setActionTitle("送仓申请");

    }


    @Override
    protected void initView() {
        super.initView();

        initShare();

        initSlidingLayout();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            showShare();
        }
    }

    private void initSlidingLayout() {
        sliding_layout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
//                LogUtils.d("onPanelSlide, offset " + slideOffset);
                rel_complete_order.setVisibility(View.GONE);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
//                LogUtils.d("onPanelStateChanged " + newState);
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    rel_complete_order.setVisibility(View.GONE);
                } else if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    rel_complete_order.setVisibility(View.VISIBLE);
                }
            }
        });
        sliding_layout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
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
        rootView = LayoutInflater.from(mActivity).inflate(R.layout.layout_create_package, mDecorView, false);
        contentContainer = rootView.findViewById(R.id.content_container);

        contentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        View iv_create = rootView.findViewById(R.id.iv_create);
        iv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissShare();
                rel_package.setVisibility(View.VISIBLE);
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
