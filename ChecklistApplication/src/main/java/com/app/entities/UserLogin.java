package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_login")
public class UserLogin extends AuditMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_login_id")
	private Long userLoginId;

	@Column(name = "user_name", length = 30)
	private String userName;

	@Column(name = "password", length = 30)
    private String password;
	
	@Column(name = "user_type", length = 30)
    private String userType;
	
	private boolean active;
	
	private boolean block;
	


}
