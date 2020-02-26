package com.mycat.entity;

public class User implements java.io.Serializable{
	
	private String phone;
	private String username;
	private int gender;
	private String catId;
	private String code;//验证码
	private String eTime;//验证码有效时间
	private int newUser;//判断是否为新用户
	
	public int getNewUser() {
		return newUser;
	}
	public void setNewUser(int newUser) {
		this.newUser = newUser;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String geteTime() {
		return eTime;
	}
	public void seteTime(String eTime) {
		this.eTime = eTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	
}
