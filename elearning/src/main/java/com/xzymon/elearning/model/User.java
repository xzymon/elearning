package com.xzymon.elearning.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.xzymon.elearning.util.HashUtils;

@Entity
@Table(name="USERS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING, name="ROLE")
public abstract class User implements Serializable{
	private static final long serialVersionUID = -6978273779053967896L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private Long id;
	@Version
	@Column(name="VERSION")
	private Integer version;
	@Column(name="FIRST_NAME", length=32, nullable=false, updatable=false)
	private String firstName;
	@Column(name="LAST_NAME", length=32, nullable=false, updatable=false)
	private String lastName;
	@Column(name="NICK", unique=true, nullable=false, updatable=false, length=32)
	private String nickName;
	@Column(name="PASSWORD", length=44, nullable=false)
	private String passwordHash;
	@OneToMany(mappedBy="owner", cascade={CascadeType.ALL})
	private Set<Doc> docs = new HashSet<Doc>();
	/**
	 * Zwraca id instancji klasy.
	 * @return identyfikator uzytkownika
	 */
	public Long getId() {
		return id;
	}
	private void setId(Long id) {
		this.id = id;
	}
	/**
	 * Zwraca imie reprezentowanego uzytkownika.
	 * @return imie uzytkownika
	 */
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Zwraca nazwisko reprezentowanego uzytkownika.
	 * @return nazwisko uzytkownika
	 */
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Zwraca pseudonim reprezentowanego uzytkownika.
	 * @return pseudonim uzytkownika
	 */
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * Zwraca hash hasla reprezentowanego uzytkownika. Algorytm hashowania to SHA-256 
	 * @return nazwisko uzytkownika
	 */
	public String getPasswordHash() {
		return passwordHash;
	}
	/**
	 * Ustawia hash hasla - 
	 * @param passwordHash
	 */
	public void setPasswordHash(String password) {
		this.passwordHash = HashUtils.getSHA256HashAsBase64String(password);
	}
	
	public Set<Doc> getDocs() {
		return docs;
	}
	
	public void setDocs(Set<Doc> docs) {
		this.docs = docs;
	}

	@Transient
	public UserType getTypeFromDiscriminatorValue(){
		String typeString = null;
		UserType result = UserType.Student;
		DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);
		
		typeString = val == null ? null : val.value();
		switch(typeString){
			case "Admin": result = UserType.Admin;
				break;
			case "Teacher": result = UserType.Teacher;
				break;
			default:
		}
		return result;
	}
	
	@Transient
	public String getNameString(){
		return String.format("%1$s %2$s", lastName, firstName);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nickName == null) ? 0 : nickName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("User:");
		if(id!=null){
			sb.append(" id:").append(id);
		}
		if(version!=null){
			sb.append(" version:").append(version);
		}
		if(firstName!=null && !firstName.trim().equals("")){
			sb.append(" firstName:").append(firstName);
		}
		if(lastName!=null && !lastName.trim().equals("")){
			sb.append(" lastName:").append(lastName);
		}
		if(nickName!=null && !nickName.trim().equals("")){
			sb.append(" nickName:").append(nickName);
		}
		if(passwordHash!=null && !passwordHash.trim().equals("")){
			sb.append(" passwordHash:").append(passwordHash);
		}
		return sb.toString();
	}
	
	
}
