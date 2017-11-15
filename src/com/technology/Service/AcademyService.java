package com.technology.Service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technology.DAO.AcademyDAO;
import com.technology.Model.AcademyInfo;

@Transactional
@Service  //用于标注业务层组件
public class AcademyService {
	@Autowired //使用@Autowired进行自动装配，而不需要get/set方法
	public AcademyDAO academyDAO;
	/**
	 * 根据工号查询
	 * 
	 * @param jobID
	 * @return
	 */
	public AcademyInfo getAcademyInfoByAcademy(String academy) {
		return academyDAO.getAcademyInfoByAcademy(academy);
	}
	
	//begin UserMge.jsp
		public Serializable save(AcademyInfo o) {  
			return academyDAO.save(o);
	    }  
	   
	    public AcademyInfo getByAcademy(Class<AcademyInfo> c, String academy) {  
	    	return academyDAO.getByAcademy(c, academy); 
	    }  
	   
	    public AcademyInfo getByHql(String hql) {  
	    	return academyDAO.getByHql(hql);
	    }  
	   
	    public void delete(AcademyInfo o) {  
	    	academyDAO.delete(o);
	    }  
	   
	    public void update(AcademyInfo o) {  
	    	academyDAO.update(o);
	    }  
	   
	    public List<AcademyInfo> find(String hql) {  
	    	return academyDAO.find(hql);
	    }  
	  
	    public List<AcademyInfo> find(String hql, int page, int rows) {  
	    	return academyDAO.find(hql, page, rows);
	    }  
	   
	    public Long count(String hql) {  
	    	return academyDAO.count(hql);
	    }  
	   
	    public int executeHql(String hql) {  
	    	return academyDAO.executeHql(hql);
	    }  
	  
	    public List<Map> findBySql(String sql) {  
	    	return academyDAO.findBySql(sql);
	    }  
	  
	    public List<Map> findBySql(String sql, int page, int rows) {  
	    	return academyDAO.findBySql(sql, page, rows);
	    }  
	  
	    public int executeSql(String sql) {  
	    	return academyDAO.executeSql(sql);
	    }  
	  
	    public BigInteger countBySql(String sql) {  
	    	return academyDAO.countBySql(sql);
	    }

}
