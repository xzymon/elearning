package com.xzymon.elearning.controller;

import java.io.Serializable;
import java.util.Enumeration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

import com.xzymon.elearning.login.LoginController;
import com.xzymon.elearning.login.LoginException;
import com.xzymon.elearning.model.User;

@Named("sessionController")
@SessionScoped
public class SessionController implements Serializable, LoginController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1970097187074044942L;

	@Inject
	private Logger logger;
	
	@Inject
	private EntityManager em;

	@Inject
	private FacesContext faces;
	
	private User user;

	public String getUserNick() {
		if (user != null) {
			return user.getNickName();
		}
		return null;
	}

	public String getUserName() {
		if (user != null) {
			return user.getNameString();
		}
		return null;
	}

	@Override
	public User getAuthenticatedUser() {
		return user;
	}

	@Override
	public boolean isLoggedIn() {
		return user == null ? false : true;
	}

	@Override
	public boolean logInUser(String userNick) throws LoginException {
		if (user != null) {
			throw new LoginException(
					"Can't log in while some user is logged in already!");
		} else {
			if (userNick != null) {
				TypedQuery<User> tquery = em.createQuery(
						"from User u where u.nickName=:nick", User.class);
				tquery.setParameter("nick", userNick);
				try {
					user = tquery.getSingleResult();
				} catch (NoResultException ex) {
				}
			}
		}
		return user != null ? true : false;
	}

	/**
	 * Only to get info about session state.
	 */
	@PostConstruct
	private void imConstructed() {
		logger.debug("SessionController constructed");
		Enumeration<String> attrEnum = ((HttpSession) faces.getExternalContext()
				.getSession(false)).getAttributeNames();
		while (attrEnum.hasMoreElements()) {
			logger.debug("Inside session attribute map: " + attrEnum.nextElement());
		}
	}
}
