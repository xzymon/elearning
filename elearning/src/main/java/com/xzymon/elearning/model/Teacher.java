package com.xzymon.elearning.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Teacher")
public class Teacher extends User implements Serializable {
	private static final long serialVersionUID = -1060932387181434395L;
	@OneToMany(mappedBy="owner",fetch=FetchType.LAZY,orphanRemoval=true)
	private Set<Subject> subjects = new HashSet<Subject>();
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Teacher: [").append(super.toString()).append("]");
		return sb.toString();
	}
}
