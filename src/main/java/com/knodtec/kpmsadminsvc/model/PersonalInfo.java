package com.knodtec.kpmsadminsvc.model;

import java.io.Serializable;

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
@Table(name = "personal_info", schema = "kpms")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
public class PersonalInfo implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id
		@Column(name = "id", nullable = false)
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@Column(name = "user_id", nullable = false, unique = true)
		private Long userId;
		
		@Column(name = "passport_no", nullable = false, unique = true)
		private String passportNo;
		
		@Column(name = "passport_expiry_date", nullable = false)
		private String passportExpiryDate;
		
		@Column(name = "aadhar_no", nullable = false)
		private String aadharNo;
		
		@Column(name = "pan_no", nullable = false)
		private String panNo;
		
		@Column(name = "blood_group", nullable = false)
		private String bloodGroup;
		
		@Column(name = "nationality")
		private String nationality;
		
		@Column(name = "religion")
		private String religion;
		
		@ManyToOne
		private MaritalStatus maritalstatus;
		
		@Column(name = "employment_of_spouse")
		private String employmentOfSpouse;
		
		@Column(name = "no_of_children")
		private Long noOfChildren;
		
}
