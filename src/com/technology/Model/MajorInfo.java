package com.technology.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 专业信息实体类 注解方式配置对应数据表
 * 
 * @author wdn
 *
 */
@Entity
@Table(name = "majorinfo")
public class MajorInfo {
	private String majorName; //专业名
	private String academy; //所在学院
	
	
	
	public MajorInfo() {
		super();
	}
	@Id
	@Column(name = "majorName", nullable = false, unique = true)
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	
	@Column(name = "academy", nullable = true, length = 20)
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	
	
}
