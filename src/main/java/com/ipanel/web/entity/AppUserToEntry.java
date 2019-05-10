package com.ipanel.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * @author fangg
 * 2017年5月15日 下午4:27:52
 */
@Entity
@Table(name="appUser_to_entry")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserToEntry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="appUser_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private AppUser appUser;
	
	@ManyToOne
	@JoinColumn(name="entry_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private EntryInfo entryInfo;
	
	@Column(name="recordType")
	private Integer recordType;

}
