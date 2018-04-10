package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.utils.PickerViewAnimateUtil;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.entity.JsonBean;
import com.dhgate.dt.demo.fragment.CreateOrderFragment;
import com.dhgate.dt.demo.fragment.ProductsManagementFragment;
import com.dhgate.dt.demo.utils.CommonUtils;
import com.dhgate.dt.demo.utils.GetJsonDataUtil;
import com.dhgate.dt.demo.utils.log.LogUtils;
import com.dhgate.dt.demo.widget.WinToast;
import com.dhgate.dt.demo.widget.slidingUpPanel.SlidingUpPanelLayout;
import com.dhgate.dt.demo.widget.slidingUpPanel.SlidingUpPanelLayout.PanelSlideListener;
import com.dhgate.dt.demo.widget.slidingUpPanel.SlidingUpPanelLayout.PanelState;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class CreateOrderActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_order;
    }

    @Bind(R.id.sliding_layout)
    public SlidingUpPanelLayout sliding_layout;

    @Bind(R.id.viewPager)
    public ViewPager viewPager;

    @Bind(R.id.iv_left)
    public ImageView iv_left;

    @Bind(R.id.iv_right)
    public ImageView iv_right;

    @Bind(R.id.rel_complete_order)
    public RelativeLayout rel_complete_order;

    @Bind(R.id.tv_date_1_1)
    public TextView tv_date_1_1;
    @Bind(R.id.tv_date_1_2)
    public TextView tv_date_1_2;
    @Bind(R.id.tv_date_1_3)
    public TextView tv_date_1_3;

    @Bind(R.id.tv_date_2_1)
    public TextView tv_date_2_1;
    @Bind(R.id.tv_date_2_2)
    public TextView tv_date_2_2;
    @Bind(R.id.tv_date_2_3)
    public TextView tv_date_2_3;

    @Bind(R.id.et_money)
    public EditText et_money;

    @Bind(R.id.tv_day_range)
    public TextView tv_day_range;


    @Bind(R.id.ll_date_content)
    public LinearLayout ll_date_content;

    @Bind(R.id.tv_city1)
    public TextView tv_city1;

    @Bind(R.id.tv_city2)
    public TextView tv_city2;

    @OnClick(R.id.iv_left)
    public void leftClick() {
        iv_left.setImageResource(R.mipmap.left_selected);
        iv_right.setImageResource(R.mipmap.right_normal);
        viewPager.setCurrentItem(0, true);
    }

    @OnClick(R.id.iv_right)
    public void rightClick() {
        iv_left.setImageResource(R.mipmap.left_normal);
        iv_right.setImageResource(R.mipmap.right_selected);
        viewPager.setCurrentItem(1, true);
    }

    @OnClick(R.id.iv_pay_back)
    public void payBackClick() {
        sliding_layout.setPanelState(PanelState.COLLAPSED);
    }

    @OnClick(R.id.ll_date1)
    public void date1Click() {
        selectTime(1);
    }

    @OnClick(R.id.ll_date2)
    public void date2Click() {
        selectTime(2);
    }

    @OnClick(R.id.ll_city_select)
    public void citySelect() {
        showCityPickerView();
    }

    @OnClick(R.id.iv_share)
    public void shareClick() {
        showShare();
    }

    @OnClick(R.id.tv_confirm_order)
    public void confirmOrderClick() {
        Intent intent = new Intent(mActivity, ConfirmOrderOfShareActivity.class);
        intent.putExtra("count", mProductNum);
        startActivity(intent);
    }

    private MyPagerAdapter mPagerAdapter;
    private List<Fragment> mFragments = new ArrayList<>();

    public static int ORDER_PRODUCT_COUNT=0;

//    private CreateOrderFragment mCreateOrderFragment;

    private int mProductNum = 0;

    @Override
    protected void onCreate() {
        super.onCreate();

        mProductNum = getIntent().getIntExtra("count", 3);
        ORDER_PRODUCT_COUNT=mProductNum;
    }

    @Override
    protected void initView() {
        super.initView();

        setActionTitle("订单");

        iv_left.setImageResource(R.mipmap.left_selected);
        iv_right.setImageResource(R.mipmap.right_normal);
        viewPager.setCurrentItem(0, true);

        initViewpager(ORDER_PRODUCT_COUNT);

        initSlidingLayout();

        initDate();

        initMoneyEdit();

        initCityData();

        initShare();
    }

    private void initMoneyEdit() {
        et_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString().trim();
                if (!TextUtils.isEmpty(text) && !s.toString().contains("¥")) {
                    et_money.setText("¥" + s.toString());
                    et_money.setSelection(et_money.getText().toString().trim().length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et_money.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    String money = et_money.getText().toString().trim().replace("¥", "");

                    if (TextUtils.isEmpty(money)) {
                        WinToast.toast(mActivity, "请输入定金");
                        return true;
                    }

                    CommonUtils.hideInputMethod(mActivity);

                    ll_date_content.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });
    }

    private void initCityData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 子线程中解析省市区数据
                initJsonData();
            }
        }).start();
    }

    private void initDate() {
        Calendar cal1 = Calendar.getInstance();
        int year = cal1.get(cal1.YEAR);
        int month = cal1.get(cal1.MONTH) + 1;
        int day = cal1.get(cal1.DAY_OF_MONTH);
        tv_date_1_1.setText(year + "年");
        tv_date_1_2.setText(month + "月");
        tv_date_1_3.setText(day + "日");
        tv_date_2_1.setText(year + "年");
        tv_date_2_2.setText(month + "月");
        tv_date_2_3.setText(day + "日");
    }

    private void initSlidingLayout() {
        sliding_layout.addPanelSlideListener(new PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
//                LogUtils.d("onPanelSlide, offset " + slideOffset);
                rel_complete_order.setVisibility(View.GONE);
            }

            @Override
            public void onPanelStateChanged(View panel, PanelState previousState, PanelState newState) {
//                LogUtils.d("onPanelStateChanged " + newState);
                if (newState == PanelState.EXPANDED) {
                    rel_complete_order.setVisibility(View.GONE);
                } else if (newState == PanelState.COLLAPSED) {
                    rel_complete_order.setVisibility(View.VISIBLE);
                }
            }
        });
        sliding_layout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sliding_layout.setPanelState(PanelState.COLLAPSED);
            }
        });
    }

    private void initViewpager(int productNum) {
        LogUtils.d(productNum);

        mFragments.clear();
        mFragments.add(CreateOrderFragment.newInstance(productNum));
        mFragments.add(ProductsManagementFragment.newInstance(10));

        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        mPagerAdapter.update(mFragments);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        iv_left.setImageResource(R.mipmap.left_selected);
                        iv_right.setImageResource(R.mipmap.right_normal);

                        setActionTitle("订单");
                        break;
                    case 1:
                        iv_left.setImageResource(R.mipmap.left_normal);
                        iv_right.setImageResource(R.mipmap.right_selected);

                        setActionTitle("商品管理");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> list = new ArrayList<>();

        public void update(List<Fragment> list) {
            this.list = list;
            notifyDataSetChanged();
        }


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
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

    private long mTime1 = new Date().getTime();
    private long mTime2 = 0;

    private TimePickerView pvCustomTime;

    /**
     * 选择时间
     *
     * @param type
     */
    private void selectTime(final int type) {
        pvCustomTime = new TimePickerBuilder(mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(date);

                int year = cal1.get(cal1.YEAR);
                int month = cal1.get(cal1.MONTH) + 1;
                int day = cal1.get(cal1.DAY_OF_MONTH);

                if (type == 1) {
                    mTime1 = date.getTime();
                    tv_date_1_1.setText(year + "年");
                    tv_date_1_2.setText(month + "月");
                    tv_date_1_3.setText(day + "日");
                } else if (type == 2) {
                    mTime2 = date.getTime();
                    if (mTime2 < mTime1) {
                        WinToast.toast(mActivity, "日期选择有误");
                        return;
                    }
                    tv_date_2_1.setText(year + "年");
                    tv_date_2_2.setText(month + "月");
                    tv_date_2_3.setText(day + "日");

                    tv_day_range.setText(((mTime2 - mTime1) / (1000 * 60 * 60 * 24)) + "");
                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false})//默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("完成")//确认按钮文字
                .setContentTextSize(16)//滚轮文字大小
                .setTitleSize(16)//标题文字大小
                .setTitleText("选择时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.text_blue1))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.text_blue1))//取消按钮文字颜色
//                        .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                        .setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR) + 20)//默认是1900-2100年
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(Calendar.getInstance(), null)//起始终止年月日设定
//                        .setLabel("年","月","日","时","分","秒")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .isDialog(true)//是否显示为对话框样式
                .build();
        pvCustomTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvCustomTime.show();

    }

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);

        }
        LogUtils.d("解析城市数据成功" + options3Items.size());
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    /**
     * 弹出城市选择器
     */
    private void showCityPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String city1 = options1Items.get(options1).getPickerViewText();
                String city2 = options2Items.get(options1).get(options2);
                String city3 = options3Items.get(options1).get(options2).get(options3);
                tv_city1.setText(city2);
                tv_city2.setText(city3);
            }
        }).setTitleText("交付仓")
//                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(16)
                .setCancelColor(getResources().getColor(R.color.text_blue1))
                .setSubmitColor(getResources().getColor(R.color.text_blue1))
                .build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
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

    public void changePageToOrder(int addCount) {
        ORDER_PRODUCT_COUNT+=addCount;

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //把所有缓存碎片都删了。
        for (Fragment childFragment : fm.getFragments()) {
            transaction.remove(childFragment);
        }
        transaction.commit();

        initView();
    }

}
