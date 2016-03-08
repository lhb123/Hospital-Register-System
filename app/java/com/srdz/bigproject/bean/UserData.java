package com.srdz.bigproject.bean;

import cn.bmob.v3.BmobUser;

public class UserData extends BmobUser {

	private static final long serialVersionUID = 1L;
	private String userPersonName;
	private boolean userSex;
	private String userIdcard;
	private String userTel;
	private String userProvince;
	private String userCity;

	public UserData(String userName, String userPwd) {
		super();
		this.setUsername(userName);
		this.setPassword(userPwd);
	}

	public UserData(String userPersonName, boolean userSex, String userIdcard,
			String userTel, String userProvince, String userCity) {
		super();
		this.userPersonName = userPersonName;
		this.userSex = userSex;
		this.userIdcard = userIdcard;
		this.userTel = userTel;
		this.userProvince = userProvince;
		this.userCity = userCity;
	}

	public String getUserPersonName() {
		return userPersonName;
	}

	public void setUserPersonName(String userPersonName) {
		this.userPersonName = userPersonName;
	}

	public boolean isUserSex() {
		return userSex;
	}

	public void setUserSex(boolean userSex) {
		this.userSex = userSex;
	}

	public String getUserIdcard() {
		return userIdcard;
	}

	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserProvince() {
		return userProvince;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
}