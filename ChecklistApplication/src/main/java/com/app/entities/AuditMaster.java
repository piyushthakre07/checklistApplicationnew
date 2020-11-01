package com.app.entities;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
public class AuditMaster {
	
	@UpdateTimestamp
	private Date updatedDate;
	
	private String updatedBy;
	
	@CreationTimestamp
	private Date createdDate;
	
	private String createdBy;

}
