package com.xzymon.elearning.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.xzymon.elearning.model.Admin;
import com.xzymon.elearning.model.Student;
import com.xzymon.elearning.model.Teacher;

@RequestScoped
public class UserListProducer {
	@Inject
    private UsersRepository usersRepository;

    private List<Admin> admins;
    private List<Teacher> teachers;
    private List<Student> students;
    
    @Produces
    @Named
    public List<Admin> getAdmins(){
    	return admins;
    }
    
    @Produces
    @Named
    public List<Teacher> getTeachers(){
    	return teachers;
    }
    
    @Produces
    @Named
    public List<Student> getStudents(){
    	return students;
    }
    
    @PostConstruct
    public void retrieveUsers(){
    	admins = usersRepository.findAdminsOrderedByName();
    	teachers = usersRepository.findTeacherOrderedByName();
    	students = usersRepository.findStudentsOrderedByName();
    }
}
