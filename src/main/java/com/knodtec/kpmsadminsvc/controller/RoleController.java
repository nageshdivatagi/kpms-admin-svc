package com.knodtec.kpmsadminsvc.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.knodtec.kpmsadminsvc.exception.RoleNotFoundException;
import com.knodtec.kpmsadminsvc.model.Departments;
import com.knodtec.kpmsadminsvc.model.Roles;
import com.knodtec.kpmsadminsvc.service.DepartmentService;
import com.knodtec.kpmsadminsvc.service.RoleService;

@CrossOrigin(origins = "${ui.url}")
@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	RoleService roleService;

	@Autowired
	DepartmentService departmentService;

	@GetMapping("/roles/{id}")
	private Roles getRole(@PathVariable Long id) throws RoleNotFoundException {
		Roles role = roleService.findOne(id).orElse(null);
		if (role == null)
			throw new RoleNotFoundException("Role Not found for id=" + id);
		return role;
	}

	@GetMapping("/department/{id}")
	private List<Roles> getRoleByDepartment(@PathVariable Long id) throws RoleNotFoundException {
		List<Roles> roles = roleService.findByDepartmentId(id);
		if (roles.isEmpty())
			throw new RoleNotFoundException("Role Not found for departmnetid=" + id);
		return roles;
	}

	@GetMapping(value = "/all")
	private List<Roles> getAllRoles() {

		List<Roles> roles = roleService.findAll();
		if (roles.isEmpty())
			throw new RoleNotFoundException("No Roles Available");
		return roles;
	}

	@PutMapping(value = "/save/{departmentId}")
	private ResponseEntity<Object> saveRole(@PathVariable Long departmentId, @RequestBody Roles role) {

		Optional<Departments> departmentOptional = departmentService.findOne(departmentId);

		if (!departmentOptional.isPresent()) {
			throw new DepartmentNotFoundException("Department Not found for id -" + departmentId);
		}

		Departments department = departmentOptional.get();

		role.setDepartment(department);

		Roles existingRoles = roleService.findRole(role.getRoleName()).orElse(null);
		Roles savedRole;
		if (existingRoles == null)
			savedRole = roleService.save(role);
		else
			throw new RoleNotFoundException("Roles Already Exists=" + role.getRoleName());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedRole.getRoleId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(value = "/delete/{id}")
	private void deleteRole(@PathVariable Long id) {
		Roles role = roleService.findOne(id).orElse(null);
		if (role == null)
			throw new RoleNotFoundException("id=" + id);
		else
			roleService.deleteRole(id);
	}

}
