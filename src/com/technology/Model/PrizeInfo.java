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
 * 获奖信息实体类 注解方式配置对应数据表
 * 
 * @author wdn
 *
 */
@Entity
@Table(name = "prizeinfo")
public class PrizeInfo {
	
	private String id;
	private String raceID;		
	private String title;	//赛事标题
	private Integer prizeType; //获奖类型
	private String userID; //获奖者学号或工号
	private String username; //获奖者姓名
	private String uploadID; //获奖者学号或工号
	private String uploadUsername; //获奖者姓名
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadTime; // 上传时间
	
	
	
	
	
	public PrizeInfo() {
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
	
	@Column(name = "raceID", nullable = true)
	public String getRaceID() {
		return raceID;
	}
	public void setRaceID(String raceID) {
		this.raceID = raceID;
	}
	
	@Column(name = "title", nullable = true)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "prizeType", nullable = true)
	public Integer getPrizeType() {
		return prizeType;
	}
	public void setPrizeType(Integer prizeType) {
		this.prizeType = prizeType;
	}
	
	@Column(name = "userID", nullable = true)
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	@Column(name = "username", nullable = true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "uploadID", nullable = true)
	public String getUploadID() {
		return uploadID;
	}
	public void setUploadID(String uploadID) {
		this.uploadID = uploadID;
	}
	
	@Column(name = "uploadUsername", nullable = true)
	public String getUploadUsername() {
		return uploadUsername;
	}
	public void setUploadUsername(String uploadUsername) {
		this.uploadUsername = uploadUsername;
	}
	
	@Column(name = "uploadTime", nullable = true)
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}	

}
