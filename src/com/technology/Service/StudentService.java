package com.technology.Service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technology.DAO.StudentDAO;
import com.technology.Model.StuInfo;

@Transactional
@Service  //用于标注业务层组件
public class StudentService {
	@Autowired //使用@Autowired进行自动装配，而不需要get/set方法
	public StudentDAO studentDAO;
	
	/**
	 * 添加
	 * @param user
	 */
	public void addStuInfo(StuInfo user) {
		studentDAO.addStuInfo(user);
	}
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public StuInfo getStuInfoById(String id){
		return studentDAO.getStuInfoById(id);
	}
	
	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	public StuInfo getStuInfoByUsername(String username){
		return studentDAO.getStuInfoByUsername(username);
	}
	
	/**
	 * 更新
	 * @param user
	 */
	public void updateStuInfo(StuInfo user) {
		studentDAO.updateStuInfo(user);
	}
	/**
	 * 删除
	 * @param id
	 */
	public void deleteStuInfoById(String  id) {
		studentDAO.deleteStuInfoById(id);
	}
	/**
	 * 查询所有
	 * @return
	 */
	public List<StuInfo> getStuInfo() {
		return studentDAO.getStuInfo();
	}
	
	
	//begin UserMge.jsp
	public Serializable save(StuInfo o) {  
		return studentDAO.save(o);
    }  
   
    public StuInfo getById(Class<StuInfo> c, String id) {  
    	return studentDAO.getById(c, id); 
    }  
   
    public StuInfo getByHql(String hql) {  
    	return studentDAO.getByHql(hql);
    }  
   
    public void delete(StuInfo o) {  
    	studentDAO.delete(o);
    }  
   
    public void update(StuInfo o) {  
    	studentDAO.update(o);
    }  
   
    public List<StuInfo> find(String hql) {  
    	return studentDAO.find(hql);
    }  
  
    public List<StuInfo> find(String hql, int page, int rows) {  
    	return studentDAO.find(hql, page, rows);
    }  
   
    public Long count(String hql) {  
    	return studentDAO.count(hql);
    }  
   
    public int executeHql(String hql) {  
    	return studentDAO.executeHql(hql);
    }  
  
    public List<Map> findBySql(String sql) {  
    	return studentDAO.findBySql(sql);
    }  
  
    public List<Map> findBySql(String sql, int page, int rows) {  
    	return studentDAO.findBySql(sql, page, rows);
    }  
  
    public int executeSql(String sql) {  
    	return studentDAO.executeSql(sql);
    }  
  
    public BigInteger countBySql(String sql) {  
    	return studentDAO.countBySql(sql);
    }

}
