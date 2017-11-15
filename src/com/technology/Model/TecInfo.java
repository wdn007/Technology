package com.technology.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 教师信息实体类 注解方式配置对应数据表
 * 
 * @author wdn
 *
 */
@Entity
@Table(name = "tecinfo")
public class TecInfo {
	private String jobID;	//工号
	private String password;
	private String username;
	private String sex;
	private String telNo;
	private String email;
	private String academy;	//所在学院
	private String duties;	//职务
	private String remark; //备注，数据库中为text类型
	private Integer status; //审核状态
	
	
	public TecInfo() {
		super();
	}
	
	public TecInfo(String jobID, String password, String username, String sex,
			String telNo, String email, String academy, String duties,
			String remark) {
		super();
		this.jobID = jobID;
		this.password = password;
		this.username = username;
		this.sex = sex;
		this.telNo = telNo;
		this.email = email;
		this.academy = academy;
		this.duties = duties;
		this.remark = remark;
	}



	@Id
	@Column(name = "jobID", nullable = false, unique = true)
	public String getJobID() {
		return jobID;
	}
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	
	@Column(name = "password", nullable = true, length = 20)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "username", nullable = true, length = 20)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "sex", nullable = true, length = 20)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name = "telNo", nullable = true, length = 20)
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	
	@Column(name = "email", nullable = true, length = 20)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "academy", nullable = true, length = 20)
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	
	@Column(name = "duties", nullable = true, length = 20)
	public String getDuties() {
		return duties;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	
	@Column(name = "remark", nullable = true, length = 20)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "status", nullable = true)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
