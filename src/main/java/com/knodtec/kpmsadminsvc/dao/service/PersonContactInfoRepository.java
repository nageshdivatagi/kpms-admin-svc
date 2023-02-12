package com.knodtec.kpmsadminsvc.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knodtec.kpmsadminsvc.model.PersonContactInfo;

@Repository
public interface PersonContactInfoRepository extends JpaRepository<PersonContactInfo, Long>{

	PersonContactInfo getByUserId(Long id);

}
