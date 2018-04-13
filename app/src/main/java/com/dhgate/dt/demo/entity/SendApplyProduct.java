package com.dhgate.dt.demo.entity;

@SuppressWarnings("serial")
public class SendApplyProduct extends BaseEntity {
    private int iconResId;
    private String name;
    private String mode;
    private int count;

    public SendApplyProduct(int iconResId, String name, String mode, int count) {
        this.iconResId = iconResId;
        this.name = name;
        this.mode = mode;
        this.count = count;
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
}
