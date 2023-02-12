package com.knodtec.kpmsadminsvc.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.knodtec.kpmsadminsvc.exception.DepartmentNotFoundException;
import com.knodtec.kpmsadminsvc.model.Departments;
import com.knodtec.kpmsadminsvc.service.DepartmentService;

@CrossOrigin(origins = "${ui.url}")
@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService depService;

	@GetMapping("/department/{id}")
	private Departments getDepartment(@PathVariable Long id) {
		Departments department = depService.findOne(id).orElse(null);
		if (department == null)
			throw new DepartmentNotFoundException("Department Details Not found for id=" + id);
		return department;
	}

	@PutMapping(value = "/save")
	private ResponseEntity<Object> createDepartment(@RequestBody Departments department) {
		Departments existingDepartment = depService.findDepartment(department.getDepartmentName()).orElse(null);
		Departments savedDepartment;
		if (existingDepartment == null)
			savedDepartment = depService.save(department);
		else
			throw new DepartmentNotFoundException("Department Already Exists=" + department.getDepartmentName());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedDepartment.getDepartmentId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/all")
	private List<Departments> getAllDepartments() {
		List<Departments> departments = depService.findAll();
		if (departments.isEmpty())
			throw new DepartmentNotFoundException("Departments are Not Available");
		return departments;
	}

	@DeleteMapping(value = "/delete/{id}")
	private void deleteDepartment(@PathVariable Long id) {
		Departments department = depService.findOne(id).orElse(null);
		if (department == null)
			throw new DepartmentNotFoundException("Department Details Not found for id=" + id);
		else
			depService.deleteDepartment(id);
	}

}
