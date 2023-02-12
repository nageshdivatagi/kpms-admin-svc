package com.knodtec.kpmsadminsvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knodtec.kpmsadminsvc.dao.service.GenderDaoService;
import com.knodtec.kpmsadminsvc.dao.service.UserDaoService;
import com.knodtec.kpmsadminsvc.model.Gender;
import com.knodtec.kpmsadminsvc.model.Users;

@Component
public class UserService {

	@Autowired
	UserDaoService userDaoService;

	@Autowired
	GenderDaoService genderDaoService;

	public List<Users> findAll() {
		return userDaoService.findAll();
	}

	public Optional<Users> findOne(Long id) {
		return userDaoService.findById(id);
	}

	public Users save(Users user) {
		return userDaoService.save(user);
	}

	public Optional<Users> findUser(String userName) {

		return userDaoService.findByUserName(userName);
	}

	public Optional<Users> findEmail(String email) {

		return userDaoService.findByEmailId(email);
	}

	public Optional<Users> findByUserId(Long userId) {

		return userDaoService.findByUserId(userId);
	}

	public boolean deleteUser(Long id) {
		userDaoService.deleteById(id);
		return true;
	}

	public List<Gender> findAllGender() {
		return genderDaoService.findAll();
	}

	public Users getUsersByReporterId(Long id) {
		// TODO Auto-generated method stub
		return userDaoService.getUsersByReporterId(id);
	}

}
