package com.knodtec.kpmsadminsvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knodtec.kpmsadminsvc.dao.service.CompanyDaoService;
import com.knodtec.kpmsadminsvc.model.Company;

@Service
public class CompanyService {

	@Autowired
	private CompanyDaoService companyDaoService;

	public Optional<Company> findOne(Long id) {

		return companyDaoService.findById(id);
	}

	public List<Company> findAll() {

		return companyDaoService.findAll();
	}

}
