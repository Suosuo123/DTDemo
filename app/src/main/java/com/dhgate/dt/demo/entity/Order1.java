package com.dhgate.dt.demo.entity;

@SuppressWarnings("serial")
public class Order1 extends BaseEntity {

	private String name;
	private String action1;
	private String action2;
	private String action3;

	public Order1(String name, String action1, String action2, String action3) {
		this.name = name;
		this.action1 = action1;
		this.action2 = action2;
		this.action3 = action3;
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
