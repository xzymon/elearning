package com.xzymon.elearning.login;

import com.xzymon.elearning.model.User;

public interface LoginController {
	/**
	 * Representation of the user authenticated by JAAS.
	 * @return logged in user (if the remote client is such user) or null otherwise
	 */
	public User getAuthenticatedUser();
	/**
	 * Checks wether the remote user have been authenticated successfully.
	 * @return true if the remote user have been authenticated, false otherwise.
	 */
	public boolean isLoggedIn();
	
	/**
	 * Stores the user of a given name in a bean.
	 * @param userNick - nick of the user.
	 * @return true if successs, otherwise false
	 * @throws LoginException
	 */
	public boolean logInUser(String userNick) throws LoginException;

}
