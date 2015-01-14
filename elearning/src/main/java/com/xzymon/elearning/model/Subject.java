package com.xzymon.elearning.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="SUBJECTS")
public class Subject implements Serializable{
	private static final long serialVersionUID = 2629856267706225067L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SUBJECT_ID")
	private Long id;
	@Version
	@Column(name="VERSION")
	private Integer version;
	@ManyToOne
	@JoinTable(name="TEACHER_SUBJECTS",joinColumns={@JoinColumn(name="SUBJECT_ID")})
	@JoinColumn(name="USER_ID")
	private Teacher owner;
	@Column(name="NAME")
	private String name;
	@Column(name="DESCRIPTION")
	private String description;
	@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(joinColumns={@JoinColumn(name="SUBJECT_ID")},inverseJoinColumns={@JoinColumn(name="DOC_ID")})
	private Set<Doc> docs = new HashSet<Doc>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Teacher getOwner() {
		return owner;
	}
	public void setOwner(Teacher owner) {
		this.owner = owner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Doc> getDocs() {
		return docs;
	}
	public void setDocs(Set<Doc> docs) {
		this.docs = docs;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		Subject other = (Subject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Subject:");
		if(id!=null){
			sb.append(" id:").append(id);
		}
		if(version!=null){
			sb.append(" version:").append(version);
		}
		if(owner!=null){
			sb.append(" owner:").append(owner);
		}
		if(name!=null){
			sb.append(" name:").append(name);
		}
		if(description!=null){
			sb.append(" description:").append(description);
		}
		return sb.toString();
	}
}
