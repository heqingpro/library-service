package com.ipanel.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="access_record")
@Data
public class AccessRecord {
	
	@Id
	private String id;
	
	@Column(name="url")
	private String url;
	
	@Column(name="access_time")
	private Date accessTime;

}
