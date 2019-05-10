package com.ipanel.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * @author: lvchao
 * @mail: chao9038@hnu.edu.cn
 * @time: 2018年4月19日下午4:04:08
 */
@Entity
@Table(name="bookmark")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="stb_id")
	private String stbID; //用户唯一标识
	
	@Column(name="entry_id")
	private Integer entryId; // 图书唯一id
	
	@Column(name="flag", columnDefinition="INT default 0")
	private Integer flag = 0; //用于区分文字图书 还是 音频图书，0是文字，1是音频
	
	@Column(name="font_size", columnDefinition="INT default 10")
	private Integer fontSize = 24; //文字图书的字体大小
	
	@Column(name="total_pages", columnDefinition="INT default 0")
	private Integer totalPages = 0; //图书总页数，该值有终端定
	
	@Column(name="current_page", columnDefinition="INT default 0")
	private Integer currentPage = 0; //当前阅读页码，该值由终端定
	
	@Column(name="total_seconds", columnDefinition="INT default 0")
	private Integer totalSeconds = 0; //音频图书总时长，单位秒，该值有终端定
	
	@Column(name="current_seconds", columnDefinition="INT default 0")
	private Integer currentSeconds = 0; //音频图书当前阅读时间点，单位秒，该值有终端定

}
