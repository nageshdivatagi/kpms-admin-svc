package com.knodtec.kpmsadminsvc.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knodtec.kpmsadminsvc.model.Company;

@Repository
public interface CompanyDaoService extends JpaRepository<Company, Long> {

}
