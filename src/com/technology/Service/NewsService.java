package com.technology.Service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technology.DAO.NewsDAO;
import com.technology.Model.NewsInfo;
import com.technology.Model.RaceInfo;

@Transactional
@Service  //用于标注业务层组件
public class NewsService {
	@Autowired //使用@Autowired进行自动装配，而不需要get/set方法
	public NewsDAO newsDAO;
	/**
	 * 根据工号查询
	 * 
	 * @param jobID
	 * @return
	 */
	public NewsInfo getAcademyInfoByAcademy(String newsID) {
		return newsDAO.getAcademyInfoByAcademy(newsID);
	}
	
	//begin UserMge.jsp
		public Serializable save(NewsInfo o) {  
			return newsDAO.save(o);
	    }  
	   
	    public NewsInfo getByAcademy(Class<NewsInfo> c, String newsID) {  
	    	return newsDAO.getByAcademy(c, newsID); 
	    }  
	   
	    public NewsInfo getByHql(String hql) {  
	    	return newsDAO.getByHql(hql);
	    }  
	   
	    public void delete(NewsInfo o) {  
	    	newsDAO.delete(o);
	    }  
	   
	    public void update(NewsInfo o) {  
	    	newsDAO.update(o);
	    }  
	   
	    public List<NewsInfo> find(String hql) {  
	    	return newsDAO.find(hql);
	    }  

		  //显示7条记录到index
		public List<NewsInfo> findLimit(String hql) {
			return newsDAO.findLimit(hql);
		}	    
	    
	    public List<NewsInfo> find(String hql, int page, int rows) {  
	    	return newsDAO.find(hql, page, rows);
	    }  
	   
	    public Long count(String hql) {  
	    	return newsDAO.count(hql);
	    }  
	   
	    public int executeHql(String hql) {  
	    	return newsDAO.executeHql(hql);
	    }  
	  
	    public List<Map> findBySql(String sql) {  
	    	return newsDAO.findBySql(sql);
	    }  
	  
	    public List<Map> findBySql(String sql, int page, int rows) {  
	    	return newsDAO.findBySql(sql, page, rows);
	    }  
	  
	    public int executeSql(String sql) {  
	    	return newsDAO.executeSql(sql);
	    }  
	  
	    public BigInteger countBySql(String sql) {  
	    	return newsDAO.countBySql(sql);
	    }
}
