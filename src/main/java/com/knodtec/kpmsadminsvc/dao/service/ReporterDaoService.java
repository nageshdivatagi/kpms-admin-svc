package com.knodtec.kpmsadminsvc.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.knodtec.kpmsadminsvc.model.Reporter;

@Service
public interface ReporterDaoService extends JpaRepository<Reporter, Long>{

	Reporter getByuserId(Long userId);
	
	Reporter getById(Long Id);
	

}
