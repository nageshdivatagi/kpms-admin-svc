package com.knodtec.kpmsadminsvc.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knodtec.kpmsadminsvc.model.MaritalStatus;

@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Long> {

}
