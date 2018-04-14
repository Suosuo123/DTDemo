package com.dhgate.dt.demo.wheelview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.dhgate.dt.demo.R;

/**
 * Created by you on 2017/10/13.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    public final ImageView iv_head;

    public ViewHolder(View itemView) {
        super(itemView);
        iv_head = (ImageView) itemView.findViewById(R.id.iv_head);
    }
}
