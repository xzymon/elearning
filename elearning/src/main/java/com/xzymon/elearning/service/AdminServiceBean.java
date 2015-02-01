package com.xzymon.elearning.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.xzymon.elearning.model.Admin;
import com.xzymon.elearning.model.Student;
import com.xzymon.elearning.model.Teacher;
import com.xzymon.elearning.model.User;
import com.xzymon.elearning.model.UserType;
import com.xzymon.elearning.sqlwrapper.UserCounts;

@Stateless
@Local(AdminServiceLocal.class)
public class AdminServiceBean implements AdminServiceBusiness {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void createUser(User user) {
		em.persist(user);
	}

	@Override
	public List<User> findUsers(UserType type, Boolean enabled, Integer pageNo, Integer pageSize) {
		if(type!=null && pageNo!=null && pageSize!=null && pageNo>0 && pageSize>0){
			Integer firstResult = (pageNo-1)*pageSize;
			TypedQuery tquery = null;
			switch(type){
				case Admin:
					tquery = em.createQuery("from Admin user where user.enabled=:enabled order by user.nickName asc", Admin.class);
					break;
				case Teacher:
					tquery = em.createQuery("from Teacher user where user.enabled=:enabled order by user.nickName asc", Teacher.class);
					break;
				case Student:
					tquery = em.createQuery("from Student user where user.enabled=:enabled order by user.nickName asc", Student.class);
					break;
			}
			System.out.format("enabled=%1$s firstResult=%2$d maxResults=%3$d%n", enabled, firstResult, pageSize);
			tquery.setParameter("enabled", enabled);
			tquery.setFirstResult(firstResult);
			tquery.setMaxResults(pageSize);
			return tquery.getResultList();
		}
		return null;
	}

	@Override
	public UserCounts getUserCounts() {
		UserCounts uc = new UserCounts();
		Session session = em.unwrap(Session.class);
		Integer ea = (Integer) session.createQuery("select count(user) from Admin user where user.enabled=true").uniqueResult();
		Integer da = (Integer) session.createQuery("select count(user) from Admin user where user.enabled=false").uniqueResult();
		Integer et = (Integer) session.createQuery("select count(user) from Teacher user where user.enabled=true").uniqueResult();
		Integer dt = (Integer) session.createQuery("select count(user) from Teacher user where user.enabled=false").uniqueResult();
		Integer es = (Integer) session.createQuery("select count(user) from Student user where user.enabled=true").uniqueResult();
		Integer ds = (Integer) session.createQuery("select count(user) from Student user where user.enabled=false").uniqueResult();
		uc.setEnabledAdmins(ea);
		uc.setDisabledAdmins(da);
		uc.setEnabledTeachers(et);
		uc.setDisabledTeachers(dt);
		uc.setEnabledStudents(es);
		uc.setDisabledStudents(ds);
		return uc;
	}

	@Override
	public Long getEnabledAdminsCount() {
		Session session = em.unwrap(Session.class);
		return (Long) session.createQuery("select count(user) from Admin user where user.enabled=true").uniqueResult();
	}

	@Override
	public Long getDisabledAdminsCount() {
		Session session = em.unwrap(Session.class);
		return (Long) session.createQuery("select count(user) from Admin user where user.enabled=false").uniqueResult();
	}

	@Override
	public Long getEnabledTeachersCount() {
		Session session = em.unwrap(Session.class);
		return (Long) session.createQuery("select count(user) from Teacher user where user.enabled=true").uniqueResult();
	}

	@Override
	public Long getDisabledTeachersCount() {
		Session session = em.unwrap(Session.class);
		return (Long) session.createQuery("select count(user) from Teacher user where user.enabled=false").uniqueResult();
	}

	@Override
	public Long getEnabledStudentsCount() {
		Session session = em.unwrap(Session.class);
		return (Long) session.createQuery("select count(user) from Student user where user.enabled=true").uniqueResult();
	}

	@Override
	public Long getDisabledStudentsCount() {
		Session session = em.unwrap(Session.class);
		return (Long) session.createQuery("select count(user) from Student user where user.enabled=false").uniqueResult();
	}

}
