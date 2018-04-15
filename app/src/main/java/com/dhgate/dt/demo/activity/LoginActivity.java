package com.dhgate.dt.demo.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dhgate.dt.demo.R;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by flora on 2018/4/13.
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.user_name_et)
    EditText user_name_et;

    @BindView(R.id.user_pwd_et)
    EditText user_pwd_et;

    @BindView(R.id.user_name_tv)
    TextView user_name_tv;

    @BindView(R.id.user_pwd_tv)
    TextView user_pwd_tv;

    @OnClick(R.id.login_btn)
    public void onLoginClick() {
        Intent intent = new Intent(LoginActivity.this, MyAccountActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        fullScreen();
    }

    @Override
    protected void initView() {
        super.initView();
        user_name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    hideUserNameTv();
                    user_name_et.setTextSize(14);
                } else {
                    showUserNameTv();
                    user_name_et.setTextSize(18);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        user_pwd_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    hideUserPwdTv();
                    user_pwd_et.setTextSize(14);
                } else {
                    showUserPwdTv();
                    user_pwd_et.setTextSize(18);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void hideUserNameTv() {
        if (user_name_tv.getVisibility() == View.VISIBLE) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(user_name_tv, "alpha", 1f, 0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(objectAnimator);
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    user_name_tv.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animatorSet.start();
        }
    }

    public void showUserNameTv() {
        if (user_name_tv.getVisibility() == View.INVISIBLE) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(user_name_tv, "alpha", 0f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(objectAnimator);
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    user_name_tv.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animatorSet.start();
        }
    }

    public void hideUserPwdTv() {
        if (user_pwd_tv.getVisibility() == View.VISIBLE) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(user_pwd_tv, "alpha", 1f, 0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(objectAnimator);
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    user_pwd_tv.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animatorSet.start();
        }
    }

    public void showUserPwdTv() {
        if (user_pwd_tv.getVisibility() == View.INVISIBLE) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(user_pwd_tv, "alpha", 0f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(objectAnimator);
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    user_pwd_tv.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animatorSet.start();
        }
    }
}
