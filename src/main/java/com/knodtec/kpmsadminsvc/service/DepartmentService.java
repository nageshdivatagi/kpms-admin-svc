package com.knodtec.kpmsadminsvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knodtec.kpmsadminsvc.dao.service.DepartmentDaoService;
import com.knodtec.kpmsadminsvc.model.Departments;

@Component
public class DepartmentService {

	@Autowired
	DepartmentDaoService depDaoService;

	public List<Departments> findAll() {
		return depDaoService.findAll();
	}

	public Optional<Departments> findOne(Long id) {
		return depDaoService.findById(id);
	}

	public Departments save(Departments role) {
		return depDaoService.save(role);
	}

	public Optional<Departments> findDepartment(String departmentName) {

		return depDaoService.findByDepartmentName(departmentName);
	}

	public boolean deleteDepartment(Long id) {
		depDaoService.deleteById(id);
		return true;

	}

}
