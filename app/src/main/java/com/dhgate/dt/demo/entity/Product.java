package com.dhgate.dt.demo.entity;

@SuppressWarnings("serial")
public class Product extends BaseEntity {
    private int iconResId;
    private String name;
    private String mode;
    private int count;
    private float singlePrice;
    private float totalPrice;

    public Product() {
    }

    public Product(int iconResId, String name, String mode, int count) {
        this.iconResId = iconResId;
        this.name = name;
        this.mode = mode;
        this.count = count;
    }

    public Product(String name, int count, float singlePrice, float totalPrice) {
        this.name = name;
        this.count = count;
        this.singlePrice = singlePrice;
        this.totalPrice = totalPrice;
    }

    public Product(int iconResId, String name, String mode, int count, float singlePrice, float totalPrice) {
        this.iconResId = iconResId;
        this.name = name;
        this.mode = mode;
        this.count = count;
        this.singlePrice = singlePrice;
        this.totalPrice = totalPrice;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(int singlePrice) {
        this.singlePrice = singlePrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

}
