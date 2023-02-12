package com.knodtec.kpmsadminsvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knodtec.kpmsadminsvc.dao.service.PersonalInfoRepository;
import com.knodtec.kpmsadminsvc.model.PersonalInfo;

@Component
public class PersonalInfoService {

	@Autowired
	private PersonalInfoRepository personalInfoRepository;

	public List<PersonalInfo> findAll() {
		return personalInfoRepository.findAll();
	}

	public Optional<PersonalInfo> findOne(Long id) {
		return personalInfoRepository.findById(id);
	}

	public Optional<PersonalInfo> findByUserId( Long userId){
		
		return personalInfoRepository.findByUserId(userId);
	}
	
	public PersonalInfo save(PersonalInfo role) {
		return personalInfoRepository.save(role);
	}

	public boolean deletePersonInfo(Long id) {
		personalInfoRepository.deleteById(id);
		return true;

	}

}
