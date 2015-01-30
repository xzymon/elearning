package com.xzymon.elearning.login;

import java.io.IOException;
import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xzymon.elearning.controller.SessionController;

/**
 * Servlet Filter implementation class LoginGuardFilter
 */
@WebFilter(description = "Performs session update to correspond with JAAS Principal.", urlPatterns = { "*" })
public class LoginGuardFilter implements Filter {

	@Inject
	private SessionController sessionController;

	/**
	 * Default constructor.
	 */
	public LoginGuardFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Principal principal = httpRequest.getUserPrincipal();
		if (principal != null && sessionController.isLoggedIn() == false) {
			try {
				sessionController.logInUser(principal.getName());
			} catch (LoginException ex) {
				ex.printStackTrace();
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
