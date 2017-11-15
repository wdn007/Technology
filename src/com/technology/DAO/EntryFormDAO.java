package com.technology.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.technology.Model.EntryFormInfo;
import com.technology.Model.RaceInfo;

@Repository // 用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
public class EntryFormDAO {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public EntryFormInfo getByHql(String hql) {
		Query q = getSession().createQuery(hql);
		List<EntryFormInfo> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}
	
	public Serializable save(EntryFormInfo o) {
		if (o != null) {
			return getSession().save(o);
		}
		return null;
	}
	
	public int executeHql(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}
	
	public Long count(String hql) {
		// Query q = getCurrentSession().createQuery(hql).;
		// return (Long) q.uniqueResult();
		return (Long) getSession().createQuery(hql).list().get(0);
	}
	
	public List<EntryFormInfo> find(String hql, int page, int rows) {
		Query q = getSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
}
