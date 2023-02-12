package com.knodtec.kpmsadminsvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "company", schema = "kpms")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "company_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyId;

	@Column(name = "company_name", nullable = false, unique = true)
	private String companyName;

	@Column(name = "created_date")
	private Date createdDate = new Date();

	@JsonIgnore
	@OneToMany(mappedBy = "company")
	private List<Users> user;

}
