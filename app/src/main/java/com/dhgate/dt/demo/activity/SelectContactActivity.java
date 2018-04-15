package com.dhgate.dt.demo.activity;

import android.widget.AbsListView;
import android.widget.ListView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.adapter.SelectContactListAdapter;
import com.dhgate.dt.demo.entity.Contact;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SelectContactActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_contact;
    }

    @BindView(R.id.lv_contact)
    public ListView lv_contact;

    private SelectContactListAdapter mAdapter;
    private List<Contact> mList = new ArrayList<>();

    private boolean isContactSelected;

    @Override
    protected void onCreate() {
        super.onCreate();
        setActionTitle("选择收货人");

        isContactSelected = getIntent().getBooleanExtra("isContactSelected", false);
    }

    @Override
    protected void initView() {
        super.initView();

        mAdapter = new SelectContactListAdapter(mActivity, lv_contact, isContactSelected);
        lv_contact.setAdapter(mAdapter);
        lv_contact.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        Contact contact1 = new Contact(R.mipmap.img_contact1);
        Contact contact2 = new Contact(R.mipmap.img_contact2);
        Contact contact3 = new Contact(R.mipmap.img_contact3);
        Contact contact4 = new Contact(R.mipmap.img_contact4);
        Contact contact5 = new Contact(R.mipmap.img_contact5);
        Contact contact6 = new Contact(R.mipmap.img_contact6);

        mList.add(contact1);
        mList.add(contact2);
        mList.add(contact3);
        mList.add(contact4);
        mList.add(contact5);
        mList.add(contact6);

        mAdapter.updateList(mList);

    }

    @Override
    protected void initData() {
        super.initData();
    }
}
