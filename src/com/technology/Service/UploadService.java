package com.technology.Service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





import com.technology.DAO.UploadDAO;
import com.technology.Model.RaceInfo;
import com.technology.Model.UploadInfo;

@Transactional
@Service // 用于标注业务层组件
public class UploadService {
	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public UploadDAO uploadDAO;
	
	public Serializable save(UploadInfo o) {
		return uploadDAO.save(o);
	}
	
	public void delete(UploadInfo o) {
		uploadDAO.delete(o);
	}
	
	public void deleteById(String id) {
		uploadDAO.deleteById(id);
	}
	
	public List<UploadInfo> find(String hql, int page, int rows) {
		return uploadDAO.find(hql, page, rows);
	}

	public Long count(String hql) {
		return uploadDAO.count(hql);
	}
	
	public int executeHql(String hql) {
		return uploadDAO.executeHql(hql);
	}
	
}
