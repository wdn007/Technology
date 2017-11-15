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

@Repository // 用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
public class StudentDAO {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 根据学号查询
	 * 
	 * @param studentID
	 * @return
	 */
	public StuInfo getStuInfoById(String studentID) {
		return (StuInfo) this.getSession().createQuery("from StuInfo where studentID=?").setParameter(0, studentID).uniqueResult();
	}
	
	/**
	 * 根据姓名查询
	 * 
	 * @param stuName
	 * @return
	 */
	public StuInfo getStuInfoByUsername(String stuName) {
		return (StuInfo) this.getSession().createQuery("from StuInfo where stuName=?").setParameter(0, stuName)
				.uniqueResult();
	}

	/**
	 * 添加
	 * 
	 * @param user
	 */
	public void addStuInfo(StuInfo o) {
		this.getSession().save(o);
		// this.getSession().beginTransaction().commit();
	}

	/**
	 * 更新
	 * 
	 * @param user
	 */
	public void updateStuInfo(StuInfo o) {
		this.getSession().update(o);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteStuInfoById(String studentID) {
		this.getSession().createQuery("delete StuInfo where studentID=?").setParameter(0, studentID).executeUpdate();
	}

	/**
	 * 查询所有
	 * 
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public List<StuInfo> getStuInfo() {
		return (List<StuInfo>) this.getSession().createCriteria(StuInfo.class).list();
	}

	// begin UserMge.jsp

	public Serializable save(StuInfo o) {
		if (o != null) {
			return sessionFactory.getCurrentSession().save(o);
		}
		return null;
	}

	public StuInfo getById(Class<StuInfo> c, String studentID) {
		return (StuInfo) sessionFactory.getCurrentSession().get(c, studentID);
	}

	public StuInfo getByHql(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		List<StuInfo> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	public void delete(StuInfo o) {
		if (o != null) {
			sessionFactory.getCurrentSession().delete(o);
		}
	}

	public void update(StuInfo o) {
		if (o != null) {
			sessionFactory.getCurrentSession().update(o);
		}
	}

	public List<StuInfo> find(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}

	public List<StuInfo> find(String hql, int page, int rows) {
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
