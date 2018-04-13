package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.entity.JsonBean;
import com.dhgate.dt.demo.utils.GetJsonDataUtil;
import com.dhgate.dt.demo.utils.log.LogUtils;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class MakeLogisticsFormActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_make_logistics;
    }

    @Bind(R.id.iv_logistics_type1)
    ImageView iv_logistics_type1;

    @Bind(R.id.tv_city1)
    TextView tv_city1;

    @Bind(R.id.tv_city2)
    TextView tv_city2;

    @Bind(R.id.tv_city3)
    TextView tv_city3;

    @Bind(R.id.tv_select_contact1)
    TextView tv_select_contact1;

    @Bind(R.id.tv_select_contact2)
    TextView tv_select_contact2;

    @Bind(R.id.iv_select_contact1)
    ImageView iv_select_contact1;

    @Bind(R.id.iv_select_contact2)
    ImageView iv_select_contact2;

    @Bind(R.id.ll_select_contact)
    LinearLayout ll_select_contact;

    @Bind(R.id.ll_select_contact1)
    LinearLayout ll_select_contact1;

    @OnClick(R.id.iv_logistics_type1)
    public void start() {
        if (logisticsTypeSelected) {
            iv_logistics_type1.setImageResource(R.mipmap.img_logistics_select1);
        } else {
            iv_logistics_type1.setImageResource(R.mipmap.img_logistics_selected);
        }
        logisticsTypeSelected = !logisticsTypeSelected;
    }

    @OnClick(R.id.ll_city_select)
    public void citySelect() {
        showCityPickerView();
    }

    @OnClick(R.id.ll_select_contact)
    public void contactSelect() {
        Intent intent = new Intent(mActivity, SelectContactActivity.class);
        intent.putExtra("isContactSelected", isContactSelected);
        startActivityForResult(intent, OPEN_SELECT_CODE);
    }

    @OnClick(R.id.ll_select_contact1)
    public void contact1Select() {
        Intent intent = new Intent(mActivity, SelectContactActivity.class);
        intent.putExtra("isContactSelected", isContactSelected);
        startActivityForResult(intent, OPEN_SELECT_CODE);
    }

    @OnClick(R.id.iv_dt_logistics)
    public void dtClick() {
        Intent intent = new Intent(mActivity, ExportBillActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv_other_logistics)
    public void otherClick() {
        Intent intent = new Intent(mActivity, ExportBillActivity.class);
        startActivity(intent);
    }

    private boolean logisticsTypeSelected;
    private static final int OPEN_SELECT_CODE = 200;

    private boolean isContactSelected;

    @Override
    protected void onCreate() {
        super.onCreate();

        setActionTitle("国际运单制作");
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();

        initCityData();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_SELECT_CODE && resultCode == RESULT_OK) {

            ll_select_contact.setVisibility(View.GONE);
            ll_select_contact1.setVisibility(View.VISIBLE);

            isContactSelected = true;
        }
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
                tv_city1.setText(city1);
                tv_city2.setText(city2);
                tv_city3.setText(city3);
            }
        }).setTitleText("目的港")
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
}
