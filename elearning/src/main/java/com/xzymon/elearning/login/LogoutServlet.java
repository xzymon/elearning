package com.xzymon.elearning.login;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.xzymon.elearning.controller.SessionController;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Logger logger;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		String moveOn = getInitParameter("afterLogoutPage");
		//String redirectAddress = String.format("%2$s:%3$s%4$s/%5$s", request.getProtocol(), request.getServerName(), request.getServerPort(), request.getContextPath(), moveOn);
		String redirectAddress = String.format("%1$s%2$s", request.getContextPath(), moveOn);
		logger.info("Logging out: sending redirect to " + redirectAddress);
		response.sendRedirect(redirectAddress);
	}

}
