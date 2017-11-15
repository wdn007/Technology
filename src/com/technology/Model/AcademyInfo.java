package com.technology.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学院信息实体类 注解方式配置对应数据表
 * 
 * @author wdn
 *
 */
@Entity
@Table(name = "academyinfo")
public class AcademyInfo {
	private String academy; //学院名
	private String principalID; //学院负责人
	
	public AcademyInfo() {
		super();
	}
	@Id
	@Column(name = "academy", nullable = false, unique = true)
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	
	@Column(name = "principalID", nullable = true, length = 20)
	public String getPrincipalID() {
		return principalID;
	}
	public void setPrincipalID(String principalID) {
		this.principalID = principalID;
	}
	
	
}
