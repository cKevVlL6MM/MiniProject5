package hibernate.model;
// Generated 6 janv. 2016 19:29:58 by Hibernate Tools 4.0.0.Final

import java.math.BigDecimal;
import java.sql.Blob;

/**
 * TableDocuments generated by hbm2java
 */
public class TableDocuments implements java.io.Serializable {

	private BigDecimal uploadId;
	private String fileName;
	private Blob fileData;

	public TableDocuments() {
	}

	public TableDocuments(BigDecimal uploadId, Blob fileData) {
		this.uploadId = uploadId;
		this.fileData = fileData;
	}

	public TableDocuments(BigDecimal uploadId, String fileName, Blob fileData) {
		this.uploadId = uploadId;
		this.fileName = fileName;
		this.fileData = fileData;
	}

	public BigDecimal getUploadId() {
		return this.uploadId;
	}

	public void setUploadId(BigDecimal uploadId) {
		this.uploadId = uploadId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Blob getFileData() {
		return this.fileData;
	}

	public void setFileData(Blob fileData) {
		this.fileData = fileData;
	}

}
