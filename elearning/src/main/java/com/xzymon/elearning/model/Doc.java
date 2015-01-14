package com.xzymon.elearning.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(name="DOCS", uniqueConstraints={@UniqueConstraint(columnNames={"FILE_NAME","USER_ID"})})
public class Doc implements Serializable{
	private static final long serialVersionUID = -6363713331727067584L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DOC_ID")
	private Long id;
	@Version
	@Column(name="VERSION")
	private Integer version;
	@Column(name="FILE_NAME", length=64, nullable=false)
	private String fileName;
	@Column(name="MIME_TYPE", length=32)
	private String mimeType;
	@Lob
	@Column(name="BINARY_DATA", nullable=false)
	private byte[] binaryData;
	@Column(name="FILE_LENGTH", nullable=false)
	private Integer fileLength;
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
		sb.append("User:");
		if(id!=null){
			sb.append(" id:").append(id);
		}
		if(version!=null){
			sb.append(" version:").append(version);
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
		return sb.toString();
	}
}
