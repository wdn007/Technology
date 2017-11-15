package com.technology.Service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technology.DAO.RaceDAO;
import com.technology.Model.RaceInfo;

@Transactional
@Service // 用于标注业务层组件
public class RaceService {
	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public RaceDAO raceDAO;

	/**
	 * 根据工号查询
	 * 
	 * @param jobID
	 * @return
	 */
	public RaceInfo getAcademyInfoByAcademy(String raceID) {
		return raceDAO.getAcademyInfoByAcademy(raceID);
	}

	// begin UserMge.jsp
	public Serializable save(RaceInfo o) {
		return raceDAO.save(o);
	}

	public RaceInfo getByAcademy(Class<RaceInfo> c, String academy) {
		return raceDAO.getByAcademy(c, academy);
	}

	public RaceInfo getByHql(String hql) {
		return raceDAO.getByHql(hql);
	}

	public void delete(RaceInfo o) {
		raceDAO.delete(o);
	}

	public void update(RaceInfo o) {
		raceDAO.update(o);
	}

	public List<RaceInfo> find(String hql) {
		return raceDAO.find(hql);
	}

	// 显示7条记录到index
	public List<RaceInfo> findLimit(String hql) {
		return raceDAO.findLimit(hql);
	}

	public List<RaceInfo> find(String hql, int page, int rows) {
		return raceDAO.find(hql, page, rows);
	}

	public Long count(String hql) {
		return raceDAO.count(hql);
	}

	public int executeHql(String hql) {
		return raceDAO.executeHql(hql);
	}

	public List<Map> findBySql(String sql) {
		return raceDAO.findBySql(sql);
	}

	public List<Map> findBySql(String sql, int page, int rows) {
		return raceDAO.findBySql(sql, page, rows);
	}

	public int executeSql(String sql) {
		return raceDAO.executeSql(sql);
	}

	public BigInteger countBySql(String sql) {
		return raceDAO.countBySql(sql);
	}
}
