package com.xzymon.elearning.weblistener;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;

import com.xzymon.elearning.controller.ApplicationController;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

	@Inject
	private Logger logger;
	
	@Inject
	private ApplicationController appcntrl;
	
    /**
     * Default constructor. 
     */
    public ApplicationListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
        String ctxPath = sce.getServletContext().getContextPath(); 
    	logger.info("Passing value of contextPath to ApplicationController bean : " + ctxPath);
        appcntrl.setName(ctxPath); 
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
	
}
