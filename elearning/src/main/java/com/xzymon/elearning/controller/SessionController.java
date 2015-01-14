package com.xzymon.elearning.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.xzymon.elearning.model.User;

@Named
@SessionScoped
public class SessionController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1970097187074044942L;

	private User user;
}
