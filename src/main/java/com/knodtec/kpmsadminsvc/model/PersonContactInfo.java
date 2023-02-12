package com.knodtec.kpmsadminsvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "person_contact_info", schema = "kpms")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
public class PersonContactInfo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_id", nullable = false, unique = true)
	private Long userId;

	@Column(name = "primary_name")
	private String primaryName;

	@Column(name = "primary_relation_ship")
	private String primaryRelationShip;

	@Column(name = "primary_phone")
	private String primaryPhone;

	@Column(name = "primary_phone1")
	private String primaryPhone1;

	@Column(name = "secondary_name")
	private String secondaryName;

	@Column(name = "secondary_relation_ship")
	private String secondaryRelationShip;

	@Column(name = "secondary_phone")
	private String secondaryPhone;

	@Column(name = "secondary_phone1")
	private String secondaryPhone1;

}
