package com.shiliu.dragon.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;



public class User implements Serializable{

	public User() {
	}

	public User(String mobile, String password, String repassword, String origin, String userName, String school, String birthday, String majorIn, String smsCode, String sex, Map<String, Object> extendProperties) {
		this.mobile = mobile;
		this.password = password;
		this.repassword = repassword;
		this.origin = origin;
		this.userName = userName;
		this.school = school;
		this.birthday = birthday;
		this.majorIn = majorIn;
		this.smsCode = smsCode;
		this.sex = sex;
		this.extendProperties = extendProperties;
	}

	private String mobile;

	private String password;

	private String repassword;

	private String origin;

	private String userName;

	private String school;

	private String birthday;

	private String majorIn;

	private String smsCode;

	private String sex;

	private String name;

	private Map<String,Object> extendProperties;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajorIn() {
		return majorIn;
	}

	public void setMajorIn(String majorIn) {
		this.majorIn = majorIn;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getExtendProperties() {
		return extendProperties;
	}

	public void setExtendProperties(Map<String, Object> extendProperties) {
		this.extendProperties = extendProperties;
	}

}
