package com.xzymon.elearning.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.xzymon.elearning.model.Doc;
import com.xzymon.elearning.model.User;

@Stateless
@Local(DocServiceLocal.class)
public class DocServiceBean implements DocServiceBusiness {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void uploadDoc(User owner, String fileName, String mimeType, byte[] fileData) {
		if(owner!=null && fileName!=null && mimeType!=null && fileData!=null){
			Doc doc = new Doc();
			doc.setOwner(owner);
			doc.setFileName(fileName);
			doc.setMimeType(mimeType);
			doc.setFileLength(fileData.length);
			doc.setUploadTime(new Date());
			doc.setBinaryData(fileData);
			try{
				em.persist(doc);
			} catch(EJBTransactionRolledbackException ex){
				System.out.println("Throwable from uploadDoc: " + ex.getMessage());
			}
		}
	}

	@Override
	public Doc ownerDownloadDoc(String ownerNick, String fileName, Date date) {
		if(ownerNick!=null && fileName != null & date != null){
			TypedQuery<Doc> tquery = em.createQuery("from Doc doc where doc.owner.nickName=:ownerNick and doc.fileName=:fileName and doc.uploadTime=:date", Doc.class);
			tquery.setParameter("ownerNick", ownerNick);
			tquery.setParameter("fileName", fileName);
			tquery.setParameter("date", date);
			Doc result = tquery.getSingleResult();
			//jawne załadowanie pliku przez JPA Providera
			result.getBinaryData();
			return result;
		}
		return null;
	}
	
	@Override
	public void deleteDoc(Doc doc) {
		Doc toRemove = em.merge(doc);
		toRemove.setOwner(null);
		em.remove(toRemove);
	}

	@Override
	public List<Doc> listAllDocs() {
		TypedQuery<Doc> tquery = em.createQuery("from Doc", Doc.class);
		return tquery.getResultList();
	}

	@Override
	public List<Doc> listDocsOwnedByUser(String userNick) {
		TypedQuery<Doc> tquery = em.createQuery("from Doc doc where doc.owner.nickName=:userNick", Doc.class);
		tquery.setParameter("userNick", userNick);
		return tquery.getResultList();
	}

	@Override
	public Doc getDoc(Long docID) {
		TypedQuery<Doc> tquery = em.createQuery("from Doc doc where doc.id=:id", Doc.class);
		tquery.setParameter("id", docID);
		return tquery.getSingleResult();
	}

	@Override
	public Doc getDoc(String userNick, String docName) {
		TypedQuery<Doc> tquery = em.createQuery("from Doc doc where doc.nickName=:userNick and doc.fileName=:docName", Doc.class);
		tquery.setParameter("userNick", userNick);
		tquery.setParameter("docName", docName);
		return tquery.getSingleResult();
	}
	
	

}
