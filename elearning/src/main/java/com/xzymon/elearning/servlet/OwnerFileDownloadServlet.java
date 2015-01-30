package com.xzymon.elearning.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Date;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

import com.xzymon.elearning.model.Doc;
import com.xzymon.elearning.service.DocServiceLocal;

/**
 * Servlet implementation class OwnerFileDownloadServlet
 */
@WebServlet("/ownerDownload.do")
public class OwnerFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private DocServiceLocal docService;
	
	@Inject
	private Logger logger;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerFileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("n");
		String dateLongString = request.getParameter("u");
		Long dateLong = null;
		Doc resultDoc = null;
		Principal principal = request.getUserPrincipal();
		if(dateLongString != null){
			dateLong = Long.parseLong(dateLongString);
		}
		if(principal!=null && fileName!=null && dateLong!=null){
			Date date = new Date(dateLong);
			logger.info("principal.getName() : " + principal.getName());
			logger.info("fileName : " + fileName);
			logger.info("date : " + date);
			logger.info("docService : " + docService);
			resultDoc = docService.ownerDownloadDoc(principal.getName(), fileName, date);
		}
		if(resultDoc!=null){
			response.setContentType(resultDoc.getMimeType());
			response.setContentLength(resultDoc.getFileLength());
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", resultDoc.getFileName()));
			OutputStream ostream = response.getOutputStream();
			ostream.write(resultDoc.getBinaryData());
			ostream.close();
		}
	}

}
