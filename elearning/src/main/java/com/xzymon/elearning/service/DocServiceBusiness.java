package com.xzymon.elearning.service;

import java.util.Date;
import java.util.List;

import com.xzymon.elearning.model.Doc;
import com.xzymon.elearning.model.User;

public interface DocServiceBusiness {
	void uploadDoc(User owner, String fileName, String mimeType, byte[] fileData);
	Doc ownerDownloadDoc(String ownerNick, String fileName, Date date);
	void deleteDoc(Doc doc);
	List<Doc> listAllDocs();
	List<Doc> listDocsOwnedByUser(String userNick);
	Doc getDoc(Long docID);
	Doc getDoc(String userNick, String docName);
}
