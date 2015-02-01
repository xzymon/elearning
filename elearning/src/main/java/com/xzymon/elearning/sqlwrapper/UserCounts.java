package com.xzymon.elearning.sqlwrapper;

public class UserCounts {
	private Integer enabledAdmins;
	private Integer disabledAdmins;
	private Integer enabledTeachers;
	private Integer disabledTeachers;
	private Integer enabledStudents;
	private Integer disabledStudents;
	public UserCounts(){
		this.enabledAdmins = 0;
		this.disabledAdmins = 0;
		this.enabledTeachers = 0;
		this.disabledTeachers = 0;
		this.enabledStudents = 0;
		this.disabledStudents = 0;
	}
	public UserCounts(Integer enabledAdmins, Integer disabledAdmins,
			Integer enabledTeachers, Integer disabledTeachers,
			Integer enabledStudents, Integer disabledStudents) {
		super();
		this.enabledAdmins = enabledAdmins;
		this.disabledAdmins = disabledAdmins;
		this.enabledTeachers = enabledTeachers;
		this.disabledTeachers = disabledTeachers;
		this.enabledStudents = enabledStudents;
		this.disabledStudents = disabledStudents;
	}
	public Integer getEnabledAdmins() {
		return enabledAdmins;
	}
	public void setEnabledAdmins(Integer enabledAdmins) {
		this.enabledAdmins = enabledAdmins;
	}
	public Integer getDisabledAdmins() {
		return disabledAdmins;
	}
	public void setDisabledAdmins(Integer disabledAdmins) {
		this.disabledAdmins = disabledAdmins;
	}
	public Integer getEnabledTeachers() {
		return enabledTeachers;
	}
	public void setEnabledTeachers(Integer enabledTeachers) {
		this.enabledTeachers = enabledTeachers;
	}
	public Integer getDisabledTeachers() {
		return disabledTeachers;
	}
	public void setDisabledTeachers(Integer disabledTeachers) {
		this.disabledTeachers = disabledTeachers;
	}
	public Integer getEnabledStudents() {
		return enabledStudents;
	}
	public void setEnabledStudents(Integer enabledStudents) {
		this.enabledStudents = enabledStudents;
	}
	public Integer getDisabledStudents() {
		return disabledStudents;
	}
	public void setDisabledStudents(Integer disabledStudents) {
		this.disabledStudents = disabledStudents;
	}
	
	
}
