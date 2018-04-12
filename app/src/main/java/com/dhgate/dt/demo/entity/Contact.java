package com.dhgate.dt.demo.entity;

@SuppressWarnings("serial")
public class Contact extends BaseEntity {

	private int imgResId;

	public Contact(int imgResId) {
		this.imgResId = imgResId;
	}

	public int getImgResId() {
		return imgResId;
	}

	public void setImgResId(int imgResId) {
		this.imgResId = imgResId;
	}
}
