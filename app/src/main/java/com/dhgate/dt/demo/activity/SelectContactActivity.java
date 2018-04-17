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

    //1:选择联系人  2：人脉管理
    private int mType;

    @Override
    protected void onCreate() {
        super.onCreate();

        mType = getIntent().getIntExtra("type", -1);
        isContactSelected = getIntent().getBooleanExtra("isContactSelected", false);

        if (mType == 1) {
            setActionTitle("选择联系人");
        } else if (mType == 2) {
            setActionTitle("人脉管理");
        }

    }

    @Override
    protected void initView() {
        super.initView();

        mAdapter = new SelectContactListAdapter(mActivity, lv_contact, isContactSelected, mType);
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
