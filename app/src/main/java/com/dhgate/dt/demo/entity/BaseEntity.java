package com.dhgate.dt.demo.entity;


import java.io.Serializable;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int status = 0;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
