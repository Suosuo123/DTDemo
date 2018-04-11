package com.dhgate.dt.demo.entity;

@SuppressWarnings("serial")
public class Order2 extends BaseEntity {

    private String action1;
    private String tv_logistics_type;

    public Order2(String action1, String tv_logistics_type) {
        this.action1 = action1;
        this.tv_logistics_type = tv_logistics_type;
    }

    public String getAction1() {
        return action1;
    }

    public void setAction1(String action1) {
        this.action1 = action1;
    }

    public String getTv_logistics_type() {
        return tv_logistics_type;
    }

    public void setTv_logistics_type(String tv_logistics_type) {
        this.tv_logistics_type = tv_logistics_type;
    }
}
