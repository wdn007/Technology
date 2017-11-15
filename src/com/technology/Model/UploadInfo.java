package com.technology.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 文件上传信息实体类 注解方式配置对应数据表
 * 
 * @author wdn
 *
 */
@Entity
@Table(name = "uploadinfo")
public class UploadInfo {
	private String id;
	private String username;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadTime; // 上传时间
	private String path; // 文件存放路径
	private Integer status; //审核状态
	
	
	
	public UploadInfo() {
		super();
	}
	
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "username", nullable = true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "uploadTime", nullable = true)
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	@Column(name = "path", nullable = true)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name = "status", nullable = true)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
