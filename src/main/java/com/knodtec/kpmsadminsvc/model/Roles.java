package com.knodtec.kpmsadminsvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "roles", schema = "kpms")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Roles implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "role_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_dep_id")
	private Departments department;

	@Column(name = "role_name", nullable = false, unique = true)
	private String roleName;

	@Column(name = "created_date")
	private Date createdDate = new Date();

	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<Users> user;

}
