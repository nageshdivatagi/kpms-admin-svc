package com.knodtec.kpmsadminsvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knodtec.kpmsadminsvc.dao.service.PersonContactInfoRepository;
import com.knodtec.kpmsadminsvc.model.PersonContactInfo;

@Component
public class PersonContactInfoService {

	@Autowired
	private PersonContactInfoRepository personContactInfoRepository;

	public List<PersonContactInfo> findAll() {
		return personContactInfoRepository.findAll();
	}

	public Optional<PersonContactInfo> findOne(Long id) {
		return personContactInfoRepository.findById(id);
	}

	public PersonContactInfo save(PersonContactInfo role) {
		return personContactInfoRepository.save(role);
	}
	
	public PersonContactInfo getByUserId(Long id) {
		
		return personContactInfoRepository.getByUserId(id);
	}

	public boolean deletePersonContactInfo(Long id) {
		personContactInfoRepository.deleteById(id);
		return true;

	}

}
