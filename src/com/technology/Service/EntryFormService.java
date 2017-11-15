package com.technology.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technology.DAO.EntryFormDAO;
import com.technology.Model.EntryFormInfo;
import com.technology.Model.RaceInfo;

@Transactional
@Service // 用于标注业务层组件
public class EntryFormService {
	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public EntryFormDAO entryFormDAO;
	
	public EntryFormInfo getByHql(String hql) {
		return entryFormDAO.getByHql(hql);
	}
	
	public Serializable save(EntryFormInfo o) {
		return entryFormDAO.save(o);
	}
	
	public Long count(String hql) {
		return entryFormDAO.count(hql);
	}
	
	public int executeHql(String hql) {
		return entryFormDAO.executeHql(hql);
	}
	
	public List<EntryFormInfo> find(String hql, int page, int rows) {
		return entryFormDAO.find(hql, page, rows);
	}

}
