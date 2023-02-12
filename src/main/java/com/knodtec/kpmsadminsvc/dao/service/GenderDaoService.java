package com.knodtec.kpmsadminsvc.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.knodtec.kpmsadminsvc.model.Gender;

@Service
public interface GenderDaoService extends JpaRepository<Gender, Long> {
	
	

}
