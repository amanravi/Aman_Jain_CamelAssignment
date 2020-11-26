package com.cts.model;

import java.io.Serializable;

public class Students implements Serializable{
	private String stuId;
	private String stuName;
	
	public Students() {
		super();
	}
	
	public Students(String stuId, String stuName) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
	}
	
	public String getStuId() {
		return stuId; 
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", stuName=" + stuName + "]";
	}
}