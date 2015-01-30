package com.xzymon.elearning.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import com.xzymon.elearning.model.Doc;
import com.xzymon.elearning.service.DocServiceBean;
import com.xzymon.elearning.service.DocServiceLocal;

@Named
@ConversationScoped
public class DocsBean implements Serializable {
	
	private static final long serialVersionUID = 4453150619277988081L;
	
	@Inject
	private Conversation conversation;

	@Inject
	private Logger logger;
	
	@Inject
	private SessionController sessionController;
	
	@Inject
	private FacesContext faces;
	
	@EJB
	private DocServiceLocal docService;
	
	private String webAppPath;
	
	private ListDataModel<Doc> myDocs;
	
	public ListDataModel<Doc> getMyDocs() {
		if(myDocs!=null){
			return myDocs;
		} else {
			beginConversation();
			myDocs = new ListDataModel<Doc>(listDocs()); 
			return myDocs;
		}
	}

	public void setMyDocs(ListDataModel<Doc> myDocs) {
		this.myDocs = myDocs;
	}

	public List<Doc> listDocs(){
		return docService.listDocsOwnedByUser(sessionController.getUserNick());
	}
	
	public void beginConversation(){
		if(conversation.isTransient()){
			conversation.begin();
		}
	}
	
	public void endConversation(){
		if(!conversation.isTransient()){
			conversation.end();
		}
	}
	
	public String deleteRow(Doc doc){
		logger.info("[CDI] Doc chosen to delete " + doc.toString());
		docService.deleteDoc(doc);
		setMyDocs(null);
		endConversation();
		return null;
	}
	
	public String getWebAppPath(){
		if(webAppPath==null){
			ExternalContext ectx = faces.getExternalContext();
			HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
			webAppPath = String.format("%1$s://%2$s:%3$s%4$s", request.getProtocol(), request.getServerName(), request.getServerPort(), request.getContextPath());
			logger.info("setting webAppPath : "+ webAppPath);
		}
		return webAppPath;
	}
}
