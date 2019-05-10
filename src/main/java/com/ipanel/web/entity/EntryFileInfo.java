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
 * 图书文件信息
 * @author fangg
 * 2017年8月3日 下午6:01:04
 */
@Entity
@Table(name="entry_file")
public class EntryFileInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;//主键id
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="entryInfo_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private EntryInfo entryInfo;
	
	@Column(name="file_name")
	private String fileName;//音频上传源文件名称
	
	@Column(name="unique_name")
	private String uniqueName;//音频唯一名称，去系统当前毫秒时间
	
	@Column(name="file_path")
	private String filePath;//音频存储的服务器路径
	
	@Column(name="file_type")
	private String fileType;//音频格式(后缀)
	
	@Column(name="file_size")
	private String fileSize;//音频占内存大小
	
	@Column(name="file_content")
	private byte[] fileContent;//音频内容，二进制美容
	
	@Column(name="file_width")
	private Integer fileWidth;//音频宽度
	
	@Column(name="file_Height")
	private Integer fileHeight;//音频高度
	
	@Column(name="add_time")
	private String addTime;//上传时间
	
	@Column(name="operate_userId")
	private Integer operateUserId;//操作管理员id

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public Integer getFileWidth() {
		return fileWidth;
	}

	public void setFileWidth(Integer fileWidth) {
		this.fileWidth = fileWidth;
	}

	public Integer getFileHeight() {
		return fileHeight;
	}

	public void setFileHeight(Integer fileHeight) {
		this.fileHeight = fileHeight;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(Integer operateUserId) {
		this.operateUserId = operateUserId;
	}

	
	
	
	
	
}