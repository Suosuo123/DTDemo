package com.dhgate.dt.demo.entity;

@SuppressWarnings("serial")
public class Order2 extends BaseEntity {

    private String billNumber;
    private String company;
    private String date;
    private String money;
    private String action1;
    private String tv_logistics_type;

    public Order2(String billNumber, String company, String date, String money, String action1, String tv_logistics_type) {
        this.billNumber = billNumber;
        this.company = company;
        this.date = date;
        this.money = money;
        this.action1 = action1;
        this.tv_logistics_type = tv_logistics_type;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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
