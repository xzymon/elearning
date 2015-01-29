package com.xzymon.elearning.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="DOCS", uniqueConstraints={@UniqueConstraint(columnNames={"FILE_NAME","USER_ID","UPLOAD_TIME"})})
public class Doc implements Serializable{
	private static final long serialVersionUID = -6363713331727067584L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DOC_ID")
	private Long id;
	@Column(name="FILE_NAME", length=255, nullable=false)
	private String fileName;
	@Column(name="MIME_TYPE", length=128)
	private String mimeType;
	@Lob
	@Column(name="BINARY_DATA", nullable=false)
	private byte[] binaryData;
	@Column(name="FILE_LENGTH", nullable=false)
	private Integer fileLength;
	@Column(name="UPLOAD_TIME", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadTime;
	/**
	 * Wymaga zeby user mial juz tozsamosc bazodanowa.
	 */
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User owner;
	
	public Long getId() {
		return id;
	}
	private void setId(Long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public byte[] getBinaryData() {
		return binaryData;
	}
	public void setBinaryData(byte[] binaryData) {
		this.binaryData = binaryData;
	}
	public Integer getFileLength() {
		return fileLength;
	}
	public void setFileLength(Integer fileLength) {
		this.fileLength = fileLength;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Long getUploadTimeAsLong(){
		return this.getUploadTime().getTime();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doc other = (Doc) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Doc:");
		if(id!=null){
			sb.append(" id:").append(id);
		}
		if(fileName!=null && !fileName.trim().equals("")){
			sb.append(" fileName:").append(fileName);
		}
		if(mimeType!=null && !mimeType.trim().equals("")){
			sb.append(" mimeType:").append(mimeType);
		}
		if(fileLength!=null){
			sb.append(" fileLength:").append(fileLength);
		}
		if(uploadTime!=null){
			sb.append(" uploadTime:").append(uploadTime);
		}
		return sb.toString();
	}
}
