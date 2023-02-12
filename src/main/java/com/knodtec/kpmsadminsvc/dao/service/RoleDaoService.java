package com.knodtec.kpmsadminsvc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.knodtec.kpmsadminsvc.model.Roles;

@Service
public interface RoleDaoService extends JpaRepository<Roles, Long> {

	Optional<Roles> findByRoleName(String roleName);

	List<Roles> findByDepartmentDepartmentId(Long id);

}
