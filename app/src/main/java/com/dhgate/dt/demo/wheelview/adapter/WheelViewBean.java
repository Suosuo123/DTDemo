package com.dhgate.dt.demo.wheelview.adapter;

import java.io.Serializable;

/**
 * Created by you on 2017/10/13.
 */

public class WheelViewBean implements Serializable {

    public static final int ICON_CNY = 1;

    public static final int ICON_USD = 2;

    public int icon;

    public WheelViewBean() {}

    public WheelViewBean(int icon) {
        this.icon = icon;
    }

}
