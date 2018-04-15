package com.dhgate.dt.demo.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.activity.CreateOrderActivity;
import com.dhgate.dt.demo.activity.EditProductActivity;
import com.dhgate.dt.demo.adapter.OrderProductListAdapter;
import com.dhgate.dt.demo.entity.Product;
import com.dhgate.dt.demo.utils.CommonUtils;
import com.dhgate.dt.demo.utils.log.LogUtils;
import com.dhgate.dt.demo.widget.swipeMenuListView.MySwipeMenuListView;
import com.dhgate.dt.demo.widget.swipeMenuListView.SwipeMenu;
import com.dhgate.dt.demo.widget.swipeMenuListView.SwipeMenuCreator;
import com.dhgate.dt.demo.widget.swipeMenuListView.SwipeMenuItem;
import com.dhgate.dt.demo.widget.swipeMenuListView.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateOrderFragment extends BaseFragment {


    public static CreateOrderFragment newInstance(int count) {
        CreateOrderFragment f = new CreateOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count", count);
        f.setArguments(bundle);
        return f;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_create_order;
    }

    @BindView(R.id.lv_product)
    public MySwipeMenuListView lv_product;

    public TextView tv_money;

    private OrderProductListAdapter mAdapter;
    private List<Product> mList = new ArrayList<>();

//    private int mTotalCount = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mTotalCount = getArguments().getInt("count", 3);
    }

    @Override
    protected void initView() {

        lv_product.setNestedpParent(((CreateOrderActivity) mActivity).viewPager);

        View footerView = LayoutInflater.from(mActivity).inflate(R.layout.layouorder_product_list_footer, lv_product, false);
        tv_money = (TextView) footerView.findViewById(R.id.tv_money);
        ImageView iv_add = (ImageView) footerView.findViewById(R.id.iv_add);
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, EditProductActivity.class);
                startActivity(intent);
            }
        });

        lv_product.addFooterView(footerView);

        mAdapter = new OrderProductListAdapter(mActivity);
        lv_product.setAdapter(mAdapter);

        mList.add(new Product(R.mipmap.order_product1, "蓝牙插卡电话智能手表", "MD-90112", 40, 135, 5400));
        mList.add(new Product(R.mipmap.order_product2, "无线蓝牙降噪头戴式耳机", "PLAY H9", 70, 225, 15750));
        mList.add(new Product(R.mipmap.order_product3, "iPhone 6p 手机壳", "iPhone 6p 手机壳", 5, 865, 4325));

        mAdapter.updateList(mList);

        initMenu();

    }


    @Override
    protected void initData() {

    }

    public void addProduct(int count) {
        for (int i = 0; i < count; i++) {
            mList.add(new Product(R.mipmap.manage_product5, "带灯插卡金属音箱", "a10", 10, 800, 8000));
        }
        mAdapter.updateList(mList);

        initMenu();
    }

    private void initMenu() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mActivity);
                deleteItem.setBackground(R.color.red1);
                deleteItem.setWidth(CommonUtils.dip2pixel(mActivity, 60));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(16);
                deleteItem.setTitleColor(Color.WHITE);
                //deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        lv_product.setMenuCreator(creator);

        // step 2. listener item click event
        lv_product.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:// delete
                        mAdapter.removeOneItem(position);
                        break;
                }
                return false;
            }
        });
    }

}