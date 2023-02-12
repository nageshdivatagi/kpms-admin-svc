package com.knodtec.kpmsadminsvc.dao.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.knodtec.kpmsadminsvc.model.Departments;

@Service
public interface DepartmentDaoService extends JpaRepository<Departments, Long> {

	Optional<Departments> findByDepartmentName(String departmentName);

}
