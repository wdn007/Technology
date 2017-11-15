package com.technology.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 报名信息实体类 注解方式配置对应数据表
 * 
 * @author wdn
 *
 */
@Entity
@Table(name = "entryforminfo")
public class EntryFormInfo {
	private Integer id;
	private Integer raceID;	//竞赛编号
	private String leaderStuID;	//队长学号
	private String leaderStuName;	//队长姓名
	private String stuID;	//队员学号
	private String stuName;	//队员姓名
	private String diretorTecName;	//指导老师
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date entryDate;	//报名时间
	private String mark;	//竞赛成绩
	private String rank;	//排名
	private String ramark;	//备注
	
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
//	@ManyToOne(targetEntity = RaceInfo.class)
//	@JoinColumn(name = "raceID", referencedColumnName = "raceID", nullable = true)
	@Column(name = "raceID", nullable = false)
	public Integer getRaceID() {
		return raceID;
	}
	public void setRaceID(Integer raceID) {
		this.raceID = raceID;
	}
	
	@Column(name = "stuID", nullable = true, length = 20)
	public String getStuID() {
		return stuID;
	}
	public void setStuID(String stuID) {
		this.stuID = stuID;
	}
	
	@Column(name = "diretorTecName", nullable = true, length = 20)
	public String getDiretorTecName() {
		return diretorTecName;
	}
	public void setDiretorTecName(String diretorTecName) {
		this.diretorTecName = diretorTecName;
	}
	
	@Column(name = "mark", nullable = true, length = 20)
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	@Column(name = "rank", nullable = true, length = 20)
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	@Column(name = "ramark", nullable = true, length = 50)
	public String getRamark() {
		return ramark;
	}
	public void setRamark(String ramark) {
		this.ramark = ramark;
	}
	
	@Column(name = "leaderStuID", nullable = false)
	public String getLeaderStuID() {
		return leaderStuID;
	}
	public void setLeaderStuID(String leaderStuID) {
		this.leaderStuID = leaderStuID;
	}
	
	@Column(name = "leaderStuName", nullable = false)
	public String getLeaderStuName() {
		return leaderStuName;
	}
	public void setLeaderStuName(String leaderStuName) {
		this.leaderStuName = leaderStuName;
	}
	
	@Column(name = "stuName", nullable = true)
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	@Column(name = "entryDate", nullable = true)
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	

}
