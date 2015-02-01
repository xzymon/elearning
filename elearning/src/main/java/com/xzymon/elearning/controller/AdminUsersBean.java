package com.xzymon.elearning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;

import com.xzymon.elearning.model.Admin;
import com.xzymon.elearning.model.Student;
import com.xzymon.elearning.model.Teacher;
import com.xzymon.elearning.model.User;
import com.xzymon.elearning.model.UserType;
import com.xzymon.elearning.service.AdminServiceLocal;

@Named
@RequestScoped
public class AdminUsersBean {
	@Inject
	private Logger logger;
	
	@EJB
	private AdminServiceLocal adminService;
	
	@NotNull
	@Size(min=3,max=32)
	private String newFirstName;
	@NotNull
	@Size(min=3,max=32)
	private String newLastName;
	@NotNull
	@Size(min=3,max=32)
	private String newNickName;
	@NotNull
	private UserType role;
	
	private int adminsPage=1;
	private int adminsDisabledPage=1;
	private int teachersPage=1;
	private int teachersDisabledPage=1;
	private int studentsPage=1;
	private int studentsDisabledPage=1;
	
	private Integer onPage = 2;
	
	public String getNewFirstName() {
		return newFirstName;
	}
	public void setNewFirstName(String newFirstName) {
		this.newFirstName = newFirstName;
	}
	public String getNewLastName() {
		return newLastName;
	}
	public void setNewLastName(String newLastName) {
		this.newLastName = newLastName;
	}
	public String getNewNickName() {
		return newNickName;
	}
	public void setNewNickName(String newNickName) {
		this.newNickName = newNickName;
	}
	public UserType getRole() {
		return role;
	}
	public void setRole(UserType role){
		this.role = role;
	}
	public Integer getRoleByNo(){
		return getViewRolesMap().get(this.role);
	}
	public void setRoleByNo(Integer roleNo) {
		logger.info("roleNo is: " + roleNo);
		this.role = getUserTypesMap().get(roleNo);
		logger.info("role value set to: "+this.role);
	}
	public UserType[] getRoles(){
		return UserType.values();
	}
	
	public Integer getAdminsPage() {
		return adminsPage;
	}
	public void setAdminsPage(Integer adminsPage) {
		this.adminsPage = adminsPage;
	}
	public Integer getAdminsDisabledPage() {
		return adminsDisabledPage;
	}
	public void setAdminsDisabledPage(Integer adminsDisabledPage) {
		this.adminsDisabledPage = adminsDisabledPage;
	}
	public Integer getTeachersPage() {
		return teachersPage;
	}
	public void setTeachersPage(Integer teachersPage) {
		this.teachersPage = teachersPage;
	}
	public Integer getTeachersDisabledPage() {
		return teachersDisabledPage;
	}
	public void setTeachersDisabledPage(Integer teachersDisabledPage) {
		this.teachersDisabledPage = teachersDisabledPage;
	}
	public Integer getStudentsPage() {
		return studentsPage;
	}
	public void setStudentsPage(Integer studentsPage) {
		this.studentsPage = studentsPage;
	}
	public Integer getStudentsDisabledPage() {
		return studentsDisabledPage;
	}
	public void setStudentsDisabledPage(Integer studentsDisabledPage) {
		this.studentsDisabledPage = studentsDisabledPage;
	}
	
	public Integer getOnPage() {
		return onPage;
	}
	public void setOnPage(Integer onPage) {
		this.onPage = onPage;
	}
	
	public boolean renderNextEnabledAdmins(){
		return adminService.getEnabledAdminsCount()>adminsPage*onPage;
	}
	
	public boolean renderNextDisabledAdmins(){
		return adminService.getDisabledAdminsCount()>adminsDisabledPage*onPage;
	}
	
	public boolean renderNextEnabledTeachers(){
		return adminService.getEnabledTeachersCount()>teachersPage*onPage;
	}
	
	public boolean renderNextDisabledTeachers(){
		return adminService.getDisabledTeachersCount()>teachersDisabledPage*onPage;
	}
	
	public boolean renderNextEnabledStudents(){
		return adminService.getEnabledStudentsCount()>studentsPage*onPage;
	}
	
	public boolean renderNextDisabledStudents(){
		return adminService.getDisabledStudentsCount()>studentsDisabledPage*onPage;
	}
	
	public Map<Integer, UserType> getUserTypesMap(){
		Map<Integer, UserType> map = new HashMap<Integer, UserType>();
		UserType[] userTypes = UserType.values();
		for(int iloop=0;iloop<userTypes.length;iloop++){
			map.put(iloop, userTypes[iloop]);
		}
		return map;
	}
	
	public Map<String, Integer> getViewRolesMap(){
		Map<String, Integer> viewMap = new HashMap<String, Integer>();
		UserType[] userTypes = UserType.values();
		for(int iloop=0;iloop<userTypes.length;iloop++){
			viewMap.put(userTypes[iloop].name(), iloop);
		}
		return viewMap;
	}
	
	public List<User> getEnabledAdmins(){
		return adminService.findUsers(UserType.Admin, true, adminsPage, onPage);
	}
	
	public List<User> getDisabledAdmins(){
		return adminService.findUsers(UserType.Admin, false, adminsDisabledPage, onPage);
	}
	
	public List<User> getEnabledTeachers(){
		return adminService.findUsers(UserType.Teacher, true, teachersPage, onPage);
	}
	
	public List<User> getDisabledTeachers(){
		return adminService.findUsers(UserType.Teacher, false, teachersDisabledPage, onPage);
	}
	
	public List<User> getEnabledStudents(){
		return adminService.findUsers(UserType.Student, true, studentsPage, onPage);
	}
	
	public List<User> getDisabledStudents(){
		return adminService.findUsers(UserType.Student, false, studentsDisabledPage, onPage);
	}
	
	public String createNewUser(){
		User user = null;
		switch(role){
		case Admin: 
			user = new Admin();
			break;
		case Teacher:
			user = new Teacher();
			break;
		case Student:
			user = new Student();
			break;
		}
		user.setFirstName(newFirstName);
		user.setLastName(newLastName);
		user.setNickName(newNickName);
		user.setPasswordHash(newNickName);
		adminService.createUser(user);
		return "manageUsers";
	}
}
