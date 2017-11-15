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
 * 新闻通知信息实体类 注解方式配置对应数据表
 * 
 * @author wdn
 *
 */
@Entity
@Table(name = "newsinfo")
public class NewsInfo {

	private String newsID; // 竞赛编号
	private String title;// 通知标题
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date newsTime; // 发布时间
	private String academy; // 发布学院
	private String uploadID; // 发布者工号
	private String username; // 发布者
	private String detail; // 内容
	private String accessory; // 附件
	private Integer status; // 审核状态
	private Long visitCount; // 访问量

	@Id
	@Column(name = "newsID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getNewsID() {
		return newsID;
	}

	public void setNewsID(String newsID) {
		this.newsID = newsID;
	}

	@Column(name = "title", nullable = true, length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "newsTime", nullable = true)
	public Date getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(Date newsTime) {
		this.newsTime = newsTime;
	}

	@Column(name = "academy", nullable = true, length = 20)
	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	@Column(name = "uploadID", nullable = true)
	public String getUploadID() {
		return uploadID;
	}

	public void setUploadID(String uploadID) {
		this.uploadID = uploadID;
	}

	@Column(name = "username", nullable = true, length = 20)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "detail", nullable = true)
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "accessory", nullable = true)
	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	@Column(name = "status", nullable = true)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "visitCount", nullable = false)
	public Long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}

}
