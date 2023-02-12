package com.knodtec.kpmsadminsvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "users", schema = "kpms")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "user_name", nullable = false, unique = true)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "employee_id", nullable = false, unique = true)
	private String empoyeeId;

	@Column(name = "joining_date", nullable = false)
	private String joiningDate;

	@Column(name = "phone_no")
	private String phoneNo;

	@Column(name = "email_id")
	private String emailId;

	@ManyToOne
	private Company company;
	
	@ManyToOne
	private Gender gender;
	
	@ManyToOne
	private Reporter reporter;
	
	@Column(name = "report_to")
	private boolean reportTo = false;
	
	@ManyToOne
	private Departments department;

	@ManyToOne
	private Roles role;

	@Column(name = "prospects_permission")
	private String prospectsPermission;

	@Column(name = "employees_permission")
	private String employeesPermission;
	
	@Column(name = "leave_permission")
	private String leavePermission;

	@Column(name = "created_date")
	private Date createdDate = new Date();
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "birth_day",nullable = false)
	private String birthDay;
	
	
	@Column(name = "image")
	private String image;

}
