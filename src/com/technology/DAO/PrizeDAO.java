package com.technology.DAO;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.technology.Model.PrizeInfo;
import com.technology.Model.RaceInfo;


@Repository // 用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
public class PrizeDAO {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public PrizeInfo getByHql(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		List<PrizeInfo> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}
	
	public Serializable save(PrizeInfo o) {
		if (o != null) {
			return sessionFactory.getCurrentSession().save(o);
		}
		return null;
	}
	
	public List<PrizeInfo> find(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}

}
