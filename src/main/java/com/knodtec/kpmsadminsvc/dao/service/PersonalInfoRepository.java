package com.knodtec.kpmsadminsvc.dao.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knodtec.kpmsadminsvc.model.PersonalInfo;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {

	Optional<PersonalInfo> findByUserId(Long userId);

}
