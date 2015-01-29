package com.xzymon.elearning.login;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

/**
 * Servlet implementation class DirectLoginServlet
 */
@WebServlet(description = "Invokation of this servlet forces invocation of JAAS", urlPatterns = { "/DirectLoginServlet" })
public class DirectLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	private Logger logger;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirectLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String moveOn = getInitParameter("afterLoginPage");
		logger.info("Login successful -> forwarding to " + moveOn);
		request.getRequestDispatcher(moveOn).forward(request, response);
	}
}
