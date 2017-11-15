package com.technology.DAO;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;



import com.technology.Model.StuInfo;
import com.technology.Model.TecInfo;

@Repository // 用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
public class TeacherDAO {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 根据工号查询
	 * 
	 * @param jobID
	 * @return
	 */
	public TecInfo getTecInfoById(String jobID) {
		return (TecInfo) this.getSession().createQuery("from TecInfo where jobID=?").setParameter(0, jobID).uniqueResult();
	}

	// begin UserMge.jsp

	public Serializable save(TecInfo o) {
		if (o != null) {
			return sessionFactory.getCurrentSession().save(o);
		}
		return null;
	}

	public TecInfo getById(Class<TecInfo> c, String jobID) {
		return (TecInfo) sessionFactory.getCurrentSession().get(c, jobID);
	}

	public TecInfo getByHql(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		List<TecInfo> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	public void delete(TecInfo o) {
		if (o != null) {
			sessionFactory.getCurrentSession().delete(o);
		}
	}

	public void update(TecInfo o) {
		if (o != null) {
			sessionFactory.getCurrentSession().update(o);
		}
	}

	public List<TecInfo> find(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}

	public List<TecInfo> find(String hql, int page, int rows) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public Long count(String hql) {
		// Query q = getCurrentSession().createQuery(hql).;
		// return (Long) q.uniqueResult();
		return (Long) sessionFactory.getCurrentSession().createQuery(hql).list().get(0);
	}

	public int executeHql(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Map> findBySql(String sql) {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public List<Map> findBySql(String sql, int page, int rows) {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public int executeSql(String sql) {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return q.executeUpdate();
	}

	public BigInteger countBySql(String sql) {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return (BigInteger) q.uniqueResult();
	}
	
	
}
