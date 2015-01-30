package com.xzymon.elearning.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;

import com.xzymon.elearning.controller.SessionController;
import com.xzymon.elearning.model.User;
import com.xzymon.elearning.service.DocServiceLocal;
import com.xzymon.elearning.util.HttpPartUtils;

/**
 * Servlet implementation class FileUploadServlet
 */
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	
	@Inject
	private SessionController sessionController;
	
	@EJB
	private DocServiceLocal docService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Im FileUploadServlet.");
		
		Map<String, Map<String, String>> partHeadersMap = null;
		Map<String, String> singleHeaderMap = null;
		
		Part part = request.getPart("binaryFileUpload");
		partHeadersMap = HttpPartUtils.headersMapProducer(part);
		singleHeaderMap = partHeadersMap.get("content-type");
		String contentType = singleHeaderMap.get("content-type");
		singleHeaderMap = partHeadersMap.get("content-disposition");
		String filename = singleHeaderMap.get("filename");
		byte[] binaryData = extractPartBodyAsArrayOfBytes(part);
		
		User owner = sessionController.getAuthenticatedUser();
		try{
			docService.uploadDoc(owner, filename, contentType, binaryData);
		} catch (EJBTransactionRolledbackException ex){
			logger.info("EJBTransactionRolledbackException while processing file upload");
		}
		
		part = request.getPart("afterUpload");
		String afterUploadValue = extractPartBodyAsString(part);
		String forwardString = getInitParameter(afterUploadValue);
		request.getRequestDispatcher(forwardString).forward(request, response);
	}

	private void printHeaders(HttpServletRequest request) throws ServletException, IOException {
		for(Part part: request.getParts()){
			logger.info("Next part...");
			HttpPartUtils.headersMapProducer(part);
		}
	}
	
	private String extractPartBodyAsString(Part part) throws IOException{
		int BUFFER_SIZE = 1024;
		StringBuffer sb = new StringBuffer(BUFFER_SIZE);
		char[] buffer = new char[BUFFER_SIZE];
		BufferedReader reader = null;
		int read=0, iloop;
		try{
			reader = new BufferedReader(new InputStreamReader(part.getInputStream()));
			while((read = reader.read(buffer))!=-1){
				for(iloop=0; iloop<read; iloop++){
					sb.append(buffer[iloop]);
				}
			}
		} finally {
		}
		return sb.toString();
	}
	
	private byte[] extractPartBodyAsArrayOfBytes(Part part) throws IOException{
		byte[] array = null;
		InputStream is = null;
		try{
			is = part.getInputStream();
			array = new byte[is.available()];
			is.read(array);
		} finally {
			
		}
		return array;
	}
}
