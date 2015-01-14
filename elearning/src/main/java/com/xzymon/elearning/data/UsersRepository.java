package com.xzymon.elearning.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.xzymon.elearning.model.Admin;
import com.xzymon.elearning.model.Student;
import com.xzymon.elearning.model.Teacher;
import com.xzymon.elearning.model.User;

@ApplicationScoped
public class UsersRepository {
	
	@Inject
	private EntityManager em;
	
	public User findById(Long id) {
        return em.find(User.class, id);
    }
	
	public List<User> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(user).orderBy(cb.asc(user.get("nickName")));
        return em.createQuery(criteria).getResultList();
    }
	
	public List<Admin> findAdminsOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Admin> criteria = cb.createQuery(Admin.class);
        Root<Admin> user = criteria.from(Admin.class);
        criteria.select(user).orderBy(cb.asc(user.get("nickName")));
        return em.createQuery(criteria).getResultList();
    }
	
	public List<Teacher> findTeacherOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> criteria = cb.createQuery(Teacher.class);
        Root<Teacher> user = criteria.from(Teacher.class);
        criteria.select(user).orderBy(cb.asc(user.get("nickName")));
        return em.createQuery(criteria).getResultList();
    }
	
	public List<Student> findStudentsOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = cb.createQuery(Student.class);
        Root<Student> user = criteria.from(Student.class);
        criteria.select(user).orderBy(cb.asc(user.get("nickName")));
        return em.createQuery(criteria).getResultList();
    }
}
