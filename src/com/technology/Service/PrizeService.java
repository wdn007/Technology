package com.technology.Service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technology.DAO.PrizeDAO;
import com.technology.Model.PrizeInfo;
import com.technology.Model.RaceInfo;



@Transactional
@Service // 用于标注业务层组件
public class PrizeService {
	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public PrizeDAO prizeDAO;
	
	public PrizeInfo getByHql(String hql) {
		return prizeDAO.getByHql(hql);
	}
	
	public Serializable save(PrizeInfo o) {
		return prizeDAO.save(o);
	}
	public List<PrizeInfo> find(String hql) {
		return prizeDAO.find(hql);
	}

}
