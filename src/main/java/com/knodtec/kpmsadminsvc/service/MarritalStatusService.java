package com.knodtec.kpmsadminsvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knodtec.kpmsadminsvc.dao.service.MaritalStatusRepository;
import com.knodtec.kpmsadminsvc.model.MaritalStatus;

@Component
public class MarritalStatusService {

	@Autowired
	private MaritalStatusRepository maritalStatusRepository;

	public List<MaritalStatus> findAll() {
		return maritalStatusRepository.findAll();
	}

	public Optional<MaritalStatus> findOne(Long id) {
		return maritalStatusRepository.findById(id);
	}

	public MaritalStatus save(MaritalStatus role) {
		return maritalStatusRepository.save(role);
	}

	public boolean deletePersonInfo(Long id) {
		maritalStatusRepository.deleteById(id);
		return true;

	}

}
