package com.xzymon.elearning.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Admin: [").append(super.toString()).append("]");
		return sb.toString();
	}
}
