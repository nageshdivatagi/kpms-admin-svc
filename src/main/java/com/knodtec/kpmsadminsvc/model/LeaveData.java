package com.knodtec.kpmsadminsvc.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LeaveData {

	private Long empId;

	private int annual;
	
	private int sickLeave;
	
	private int hospitalisation;
	
	private int maternity;

	private int paternity;
	
	private int lop;
	
	private Long createdBy;

}
