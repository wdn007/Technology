package com.technology.Service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technology.DAO.TeacherDAO;
import com.technology.Model.TecInfo;

@Transactional
@Service  //用于标注业务层组件
public class TecService {
	@Autowired //使用@Autowired进行自动装配，而不需要get/set方法
	public TeacherDAO teacherDAO;
	/**
	 * 根据工号查询
	 * 
	 * @param jobID
	 * @return
	 */
	public TecInfo getTecInfoById(String jobID) {
		return teacherDAO.getTecInfoById(jobID);
	}
	
	//begin UserMge.jsp
		public Serializable save(TecInfo o) {  
			return teacherDAO.save(o);
	    }  
	   
	    public TecInfo getById(Class<TecInfo> c, String jobID) {  
	    	return teacherDAO.getById(c, jobID); 
	    }  
	   
	    public TecInfo getByHql(String hql) {  
	    	return teacherDAO.getByHql(hql);
	    }  
	   
	    public void delete(TecInfo o) {  
	    	teacherDAO.delete(o);
	    }  
	   
	    public void update(TecInfo o) {  
	    	teacherDAO.update(o);
	    }  
	   
	    public List<TecInfo> find(String hql) {  
	    	return teacherDAO.find(hql);
	    }  
	  
	    public List<TecInfo> find(String hql, int page, int rows) {  
	    	return teacherDAO.find(hql, page, rows);
	    }  
	   
	    public Long count(String hql) {  
	    	return teacherDAO.count(hql);
	    }  
	   
	    public int executeHql(String hql) {  
	    	return teacherDAO.executeHql(hql);
	    }  
	  
	    public List<Map> findBySql(String sql) {  
	    	return teacherDAO.findBySql(sql);
	    }  
	  
	    public List<Map> findBySql(String sql, int page, int rows) {  
	    	return teacherDAO.findBySql(sql, page, rows);
	    }  
	  
	    public int executeSql(String sql) {  
	    	return teacherDAO.executeSql(sql);
	    }  
	  
	    public BigInteger countBySql(String sql) {  
	    	return teacherDAO.countBySql(sql);
	    }

}
