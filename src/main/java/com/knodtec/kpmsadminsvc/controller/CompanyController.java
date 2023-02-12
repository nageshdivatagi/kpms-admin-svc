package com.knodtec.kpmsadminsvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knodtec.kpmsadminsvc.exception.CompanyNotFoundException;
import com.knodtec.kpmsadminsvc.model.Company;
import com.knodtec.kpmsadminsvc.service.CompanyService;

@CrossOrigin(origins = "${ui.url}")
@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@GetMapping("/{id}")
	private Company getCompany(@PathVariable Long id) {
		Company company = companyService.findOne(id).orElse(null);
		if (company == null)
			throw new CompanyNotFoundException("Company Details Not found for id=" + id);
		return company;
	}

	@GetMapping("/all")
	private List<Company> getAllDepartments() {
		List<Company> companies = companyService.findAll();
		if (companies.isEmpty())
			throw new CompanyNotFoundException("Company Details are Not Available");
		return companies;
	}

}
