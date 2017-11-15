package com.technology.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生信息实体类 注解方式配置对应数据表
 * 
 * @author wdn
 *
 */
@Entity
@Table(name = "stuinfo")
public class StuInfo {
	private String studentID;
	private String stuName;
	private String password;
	private String academy;
	private String major;
	private String telNo;
	private String sex;
	private String email;
	private Integer status; //审核状态
	
	
	
	public StuInfo() {
		super();
	}
	
	public StuInfo(String studentID, String stuName, String password,
			String academy, String major, String telNo, String sex, String email) {
		super();
		this.studentID = studentID;
		this.stuName = stuName;
		this.password = password;
		this.academy = academy;
		this.major = major;
		this.telNo = telNo;
		this.sex = sex;
		this.email = email;
	}



	@Id
	@Column(name = "studentID", nullable = false, unique = true)
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	
	@Column(name = "stuName", nullable = true, length = 20)
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	@Column(name = "password", nullable = true, length = 20)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "academy", nullable = true, length = 20)
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	
	@Column(name = "major", nullable = true, length = 20)
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	@Column(name = "telNo", nullable = true, length = 20)
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	
	@Column(name = "sex", nullable = true, length = 20)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name = "email", nullable = true, length = 30)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "status", nullable = true)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StuInfo [studentID=" + studentID + ", stuName=" + stuName
				+ ", password=" + password + ", academy=" + academy
				+ ", major=" + major + ", telNo=" + telNo + ", sex=" + sex
				+ ", email=" + email + "]";
	}
	
	
}
