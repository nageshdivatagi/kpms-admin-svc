package com.knodtec.kpmsadminsvc.dao.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.knodtec.kpmsadminsvc.model.Users;

@Repository
public interface UserDaoService extends JpaRepository<Users, Long> {

	Optional<Users> findByUserName(String userName);

	Optional<Users> findByEmailId(@RequestParam String email);
	
	Optional<Users> findByUserId(Long userId);

	Users getUsersByReporterId(Long id);
	

}
