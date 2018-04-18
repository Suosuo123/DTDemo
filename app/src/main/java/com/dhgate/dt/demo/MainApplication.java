package com.dhgate.dt.demo;

import android.app.Activity;
import android.app.Application;

import com.dhgate.dt.demo.utils.CommonUtils;

import java.util.LinkedList;
import java.util.List;

public class MainApplication extends Application {
    private static MainApplication mContext;

    public static MainApplication getApplication() {
        return mContext;
    }

    public double usd_balance = 0;
    public double cny_balance = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    /**
     * 运用list来保存们每一个activity
     */
    public List<Activity> mActivityList = new LinkedList<Activity>();

    /**
     * add Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mActivityList.add(activity);
    }

    /**
     * remove Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        mActivityList.remove(activity);
    }

    /**
     * 关闭每一个list内的activity
     */
    public void finishAllActivities() {
        try {
            for (Activity activity : mActivityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUsdBalance(double usd_balance) {
        this.usd_balance = usd_balance;
    }

    public double getUsdBalanceDouble() {
        return this.usd_balance;
    }

    public String getUsdBalanceStr() {
        return CommonUtils.to2(usd_balance);
    }

    public double getCnyBalanceDouble() {
        return cny_balance;
    }

    public String getCnyBalanceStr() {
        return CommonUtils.to2(cny_balance);
    }

    public void setCnyBalance(double cny_balance) {
        this.cny_balance = cny_balance;
    }
}
