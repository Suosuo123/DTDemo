package com.dhgate.dt.demo.activity;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.utils.PickerViewAnimateUtil;
import com.dhgate.dt.demo.MainApplication;
import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.utils.AndroidBug54971Workaround;
import com.dhgate.dt.demo.utils.CommonUtils;
import com.dhgate.dt.demo.widget.uiView.UIImageView;
import com.jungly.gridpasswordview.GridPasswordView;
import butterknife.BindView;
import butterknife.OnClick;

public class TransferActivity extends BaseActivity {

    @BindView(R.id.confirm_btn)
    ImageView confirm_btn;

    @BindView(R.id.bank_account_1)
    ImageView bank_account_1;

    @BindView(R.id.bank_account_2)
    ImageView bank_account_2;

    @BindView(R.id.transfer_amount_text)
    TextView transfer_amount_text;

    LinearLayout ll_password;

    ImageView iv_transfer_success;

    GridPasswordView grid_password;

    UIImageView iv_pass_back;


    @OnClick(R.id.bank_account_2)
    public void onBtn2Click() {
        bank_account_1.setVisibility(View.VISIBLE);
        bank_account_2.setVisibility(View.GONE);
    }

    @OnClick(R.id.bank_account_1)
    public void onBtn1Click() {
        bank_account_2.setVisibility(View.VISIBLE);
        bank_account_1.setVisibility(View.GONE);
    }

    @OnClick(R.id.confirm_btn)
    public void onConfirmClick() {
        showShare();
    }

    private Animation outAnim;
    private Animation inAnim;
    private ViewGroup mDecorView;
    private View rootView;
    private View contentContainer;
    private boolean isShowing;
    private boolean dismissing;
    protected int animGravity = Gravity.BOTTOM;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_usdtransfer;
    }


    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("转账");
    }

    @Override
    protected void initView() {
        MainApplication application = (MainApplication) getApplication();
        transfer_amount_text.setText(application.getBalanceStr());
        initShare();
        grid_password.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                // add your code here
            }

            @Override
            public void onInputFinish(String psw) {
                ll_password.setVisibility(View.GONE);
                iv_transfer_success.setVisibility(View.VISIBLE);
                CommonUtils.hideInputMethod(TransferActivity.this);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1500);
            }
        });
    }

    private void initShare() {
        inAnim = getInAnimation();
        outAnim = getOutAnimation();

        if (mDecorView == null) {
            mDecorView = (ViewGroup) (mActivity).getWindow().getDecorView();
        }
        rootView = LayoutInflater.from(mActivity).inflate(R.layout.layout_transfer_password, mDecorView, false);
        contentContainer = rootView.findViewById(R.id.content_container);
        grid_password = (GridPasswordView) rootView.findViewById(R.id.grid_password);
        ll_password = (LinearLayout) rootView.findViewById(R.id.ll_password);
        iv_transfer_success = (ImageView) rootView.findViewById(R.id.iv_transfer_success);
        iv_pass_back = (UIImageView) rootView.findViewById(R.id.iv_pass_back);
        iv_pass_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissShare();
            }
        });
    }

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

    public Animation getInAnimation() {
        int res = PickerViewAnimateUtil.getAnimationResource(this.animGravity, true);
        return AnimationUtils.loadAnimation(mActivity, res);
    }

    public Animation getOutAnimation() {
        int res = PickerViewAnimateUtil.getAnimationResource(this.animGravity, false);
        return AnimationUtils.loadAnimation(mActivity, res);
    }
}
