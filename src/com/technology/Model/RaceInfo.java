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
 * 竞赛信息实体类 注解方式配置对应数据表
 * 
 * @author wdn
 *
 */
@Entity
@Table(name = "raceinfo")
public class RaceInfo {
	private String raceID; // 竞赛编号
	private String title; // 竞赛标题
	private String sponsor; // 竞赛主办学院
	private String grade; // 竞赛级别
	private String location; // 比赛地点
	private String raceDate; // 竞赛时间
	private String entryDate; // 报名时间
	private String information; // 竞赛信息
	private String entryForm; // 竞赛形式
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadTime; // 发布时间
	private String uploadID; // 发布者的工号
	private String uploadUsername; // 竞赛发布者
	private String status; // 竞赛状态
	private String accessory; // 竞赛信息附件
	private Integer applyStatus; // 审核状态
	private Long visitCount; // 访问量

	public RaceInfo() {
		super();
	}

	@Id
	@Column(name = "raceID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getRaceID() {
		return raceID;
	}

	public void setRaceID(String raceID) {
		this.raceID = raceID;
	}

	@Column(name = "title", nullable = true, length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "information", nullable = true)
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Column(name = "uploadTime", nullable = true)
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Column(name = "grade", nullable = true, length = 20)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "sponsor", nullable = true, length = 20)
	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	@Column(name = "uploadID", nullable = true)
	public String getUploadID() {
		return uploadID;
	}

	public void setUploadID(String uploadID) {
		this.uploadID = uploadID;
	}

	@Column(name = "uploadUsername", nullable = true, length = 20)
	public String getUploadUsername() {
		return uploadUsername;
	}

	public void setUploadUsername(String uploadUsername) {
		this.uploadUsername = uploadUsername;
	}

	@Column(name = "status", nullable = true, length = 20)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "accessory", nullable = true)
	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	@Column(name = "location", nullable = true)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "raceDate", nullable = true)
	public String getRaceDate() {
		return raceDate;
	}

	public void setRaceDate(String raceDate) {
		this.raceDate = raceDate;
	}

	@Column(name = "entryDate", nullable = true)
	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	@Column(name = "entryForm", nullable = true)
	public String getEntryForm() {
		return entryForm;
	}

	public void setEntryForm(String entryForm) {
		this.entryForm = entryForm;
	}

	@Column(name = "applyStatus", nullable = true)
	public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	@Column(name = "visitCount", nullable = false)
	public Long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}

}
