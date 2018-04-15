package com.dhgate.dt.demo.entity;

@SuppressWarnings("serial")
public class Order1 extends BaseEntity {

    private String name;
    private String company;
    private String money;
    private String action1;
    private String action2;
    private String action3;

    public Order1(String name, String company, String money, String action1, String action2, String action3) {
        this.name = name;
        this.company = company;
        this.money = money;
        this.action1 = action1;
        this.action2 = action2;
        this.action3 = action3;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction1() {
        return action1;
    }

    public void setAction1(String action1) {
        this.action1 = action1;
    }

    public String getAction2() {
        return action2;
    }

    public void setAction2(String action2) {
        this.action2 = action2;
    }

    public String getAction3() {
        return action3;
    }

    public void setAction3(String action3) {
        this.action3 = action3;
    }
}
