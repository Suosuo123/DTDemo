package com.dhgate.dt.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;

import com.dhgate.dt.demo.R;
import com.dhgate.dt.demo.activity.SelectContactActivity;
import com.dhgate.dt.demo.entity.Contact;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SelectContactListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;

    private List<Contact> mList = new ArrayList<>();

    private ListView mListView;

    private boolean mIsContactSelected;

    public SelectContactListAdapter(Context context, ListView listView, boolean isContactSelected) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mListView = listView;
        mIsContactSelected = isContactSelected;
    }

    public void bindData(List<Contact> list) {
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public void updateList(List<Contact> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int arg0) {
        return mList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public class ViewHolder {

        @Bind(R.id.iv_content)
        public ImageView iv_content;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        Contact contact = mList.get(position);

        View view = convertView;
        if (view == null) {
            ViewHolder vh = new ViewHolder();
            view = mInflater.inflate(R.layout.item_select_contact_list, arg2, false);
            ButterKnife.bind(vh, view);
            view.setTag(vh);
        }
        ViewHolder vh = (ViewHolder) view.getTag();

        if (position == 1 && mIsContactSelected) {
            vh.iv_content.setImageResource(R.mipmap.img_contact_selected);
        } else {
            vh.iv_content.setImageResource(contact.getImgResId());
        }

        vh.iv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 1) {
                    ((ImageView) v).setImageResource(R.mipmap.img_contact_selected);
                    ((SelectContactActivity) mContext).setResult(Activity.RESULT_OK);
                    ((SelectContactActivity) mContext).finish();
                }
            }
        });
        return view;
    }

}
