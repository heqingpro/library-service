package com.ipanel.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 保存图书的用户交互信息，
 * 
 * @author fangg
 * 2017年8月3日 下午6:30:26
 */
@Entity
@Table(name="entry_attach")
public class EntryAttachInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;//主键id
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="entryInfo_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private EntryInfo entryInfo;
	
	@Column(name="browse_count")
	private Integer browseCount;//浏览次数
	
	@Column(name="download_count")
	private Integer downloadCount;//下载次数
	
	@Column(name="sort_value")
	private Integer sortValue;
	
	@Column(name="audit_status") 
	private Integer auditStatus;//状态   （分类状态：0未审核 1已审核）

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EntryInfo getEntryInfo() {
		return entryInfo;
	}

	public void setEntryInfo(EntryInfo entryInfo) {
		this.entryInfo = entryInfo;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	public Integer getSortValue() {
		return sortValue;
	}

	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	
	
	
	
	

}
