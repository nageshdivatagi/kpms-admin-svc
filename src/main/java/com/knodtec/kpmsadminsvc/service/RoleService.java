package com.knodtec.kpmsadminsvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knodtec.kpmsadminsvc.dao.service.RoleDaoService;
import com.knodtec.kpmsadminsvc.model.Roles;

@Component
public class RoleService {

	@Autowired
	RoleDaoService roleDaoService;

	public List<Roles> findAll() {
		return roleDaoService.findAll();
	}

	public Optional<Roles> findOne(Long id) {
		return roleDaoService.findById(id);
	}

	public Roles save(Roles role) {
		return roleDaoService.save(role);
	}

	public Optional<Roles> findRole(String roleName) {

		return roleDaoService.findByRoleName(roleName);
	}

	public boolean deleteRole(Long id) {
		roleDaoService.deleteById(id);
		return true;

	}

	public List<Roles> findByDepartmentId(Long id) {
		
		return roleDaoService.findByDepartmentDepartmentId(id);
	}
}
