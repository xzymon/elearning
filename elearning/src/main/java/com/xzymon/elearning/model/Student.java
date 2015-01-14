package com.xzymon.elearning.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Student")
public class Student extends User {

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Student: [").append(super.toString()).append("]");
		return sb.toString();
	}
}
