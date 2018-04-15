package com.dhgate.dt.demo.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dhgate.dt.demo.R;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by flora on 2018/4/10.
 */

public class EditProductActivity extends BaseActivity {

    public int categoryHeight;
    public String choosedStr;

    @Bind(R.id.choose_layout_1_text)
    TextView choose_layout_1_text;

    @Bind(R.id.category_content_layout)
    LinearLayout category_content_layout;

    @Bind(R.id.shadow)
    View shadow;

    @Bind(R.id.category_layout1_check)
    ImageView category_layout1_check;

    @Bind(R.id.category_layout2_check)
    ImageView category_layout2_check;

    @OnClick(R.id.choose_layout_1)
    public void onChooseLayout2Click() {
        showAnim();
    }

    @OnClick({R.id.shadow, R.id.close_icon})
    public void closeChooseLayout() {
        hideAnim();
    }

    @OnClick(R.id.category_layout1)
    public void onCategoryLayout1Click() {
        category_layout1_check.setVisibility(View.VISIBLE);
        category_layout2_check.setVisibility(View.GONE);
        choose_layout_1_text.setText("塑料及其它制品");
        hideAnim();
    }

    @OnClick(R.id.category_layout2)
    public void onCategoryLayout2Click() {
        category_layout1_check.setVisibility(View.GONE);
        category_layout2_check.setVisibility(View.VISIBLE);
        choose_layout_1_text.setText("橡胶及其它制品");
        hideAnim();
    }

    @OnClick(R.id.save_btn)
    public void onSaveClick() {
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_product;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("添加商品");
        categoryHeight = (int) getResources().getDimension(R.dimen.category_height);
        hideAnim();
    }

    public void showAnim() {
        ObjectAnimator animTrans = ObjectAnimator.ofFloat(category_content_layout, "translationY", 0);
        AnimatorSet set = new AnimatorSet();
        set.play(animTrans);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                shadow.setVisibility(View.VISIBLE);
                category_content_layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }

    public void hideAnim() {
        ObjectAnimator animTrans = ObjectAnimator.ofFloat(category_content_layout, "translationY", categoryHeight);
        LinearInterpolator ll = new LinearInterpolator();
        animTrans.setInterpolator(ll);
        AnimatorSet set = new AnimatorSet();
        set.play(animTrans);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                shadow.setVisibility(View.GONE);
                category_content_layout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }
}
