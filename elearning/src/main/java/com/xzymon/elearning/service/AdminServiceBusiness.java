package com.xzymon.elearning.service;

import java.util.List;

import com.xzymon.elearning.model.User;
import com.xzymon.elearning.model.UserType;
import com.xzymon.elearning.sqlwrapper.UserCounts;

public interface AdminServiceBusiness {
	void createUser(User user);
	/**
	 * Gets sublist of Users of specified type. The sublist contains data from specified pageNo, assuming that page can contain at most pageSize rows.
	 * @param type 
	 * @param enabled
	 * @param pageNo
	 * @param pageSize
	 * @return a list containing users of specified type from given page
	 */
	List<User> findUsers(UserType type, Boolean enabled, Integer pageNo, Integer pageSize);
	/**
	 * Gets statistical data describing amount of users of each configuration of type and "enabled" property and encapsulates them using UserCounts instance.
	 * @return UserCounts
	 */
	UserCounts getUserCounts();
	
	Long getEnabledAdminsCount();
	Long getDisabledAdminsCount();
	Long getEnabledTeachersCount();
	Long getDisabledTeachersCount();
	Long getEnabledStudentsCount();
	Long getDisabledStudentsCount();
}
