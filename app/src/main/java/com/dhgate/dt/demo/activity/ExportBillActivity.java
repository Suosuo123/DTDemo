package com.dhgate.dt.demo.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bigkoo.pickerview.utils.PickerViewAnimateUtil;
import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.adapter.ExportBillProductListAdapter;
import com.dhgate.dt.demo.entity.SendApplyProduct;
import com.dhgate.dt.demo.utils.AndroidBug54971Workaround;
import com.dhgate.dt.demo.widget.WrapContentListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ExportBillActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_export_bill;
    }

    @BindView(R.id.ll_content1)
    public LinearLayout ll_content1;

    @BindView(R.id.ll_content2)
    public LinearLayout ll_content2;

    @BindView(R.id.lv_product)
    public WrapContentListView lv_product;

    @BindView(R.id.iv_content1)
    public ImageView iv_content1;

    @BindView(R.id.iv_content2)
    public ImageView iv_content2;

    @OnClick(R.id.iv_quick_create)
    public void createClick() {

        for (int i = 0; i < mAdapter.getCount(); i++) {
            lv_product.setItemChecked(i, true);
        }
        mAdapter.notifyDataSetChanged();

        showShare();
    }


    @OnClick(R.id.rel_product)
    public void productClick() {
        if (mDetailShow) {
            iv_content1.setVisibility(View.VISIBLE);
            iv_content2.setVisibility(View.GONE);
        } else {
            iv_content1.setVisibility(View.GONE);
            iv_content2.setVisibility(View.VISIBLE);
        }
        mDetailShow = !mDetailShow;
    }

    @OnClick(R.id.iv_cancel)
    public void cancelClick() {
        ll_content1.setVisibility(View.VISIBLE);
        ll_content2.setVisibility(View.GONE);
    }

    @OnClick(R.id.iv_complete)
    public void completeClick() {
        mSuccessDialog = new AlertDialog.Builder(mActivity, R.style.AlertDialog)
                .setView(LayoutInflater.from(mActivity).inflate(R.layout.layout_bill_success, null))
                .setCancelable(false)
                .show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSuccessDialog != null) {
                    mSuccessDialog.dismiss();
                }

                Intent intent = new Intent(mActivity, ExportManagementActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }

    private ExportBillProductListAdapter mAdapter;
    private List<SendApplyProduct> mList = new ArrayList<>();

    private boolean mDetailShow = false;

    private AlertDialog mSuccessDialog;

    @Override
    protected void onCreate() {
        super.onCreate();

        setActionTitle("出口发票");
    }

    @Override
    protected void initView() {
        super.initView();

        mAdapter = new ExportBillProductListAdapter(mActivity, lv_product);
        lv_product.setAdapter(mAdapter);
        lv_product.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        mList.add(new SendApplyProduct("蓝牙插卡电话智能手表", 40, 135, 5400));
        mList.add(new SendApplyProduct("iPhone 7p 手机壳", 70, 225, 1575));
        mList.add(new SendApplyProduct("iPhone x 手机壳", 865, 5, 4325));

        mAdapter.updateList(mList);

        lv_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.notifyDataSetChanged();
            }
        });

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
        rootView = LayoutInflater.from(mActivity).inflate(R.layout.layout_export_bill, mDecorView, false);
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

                ll_content1.setVisibility(View.GONE);
                ll_content2.setVisibility(View.VISIBLE);
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
