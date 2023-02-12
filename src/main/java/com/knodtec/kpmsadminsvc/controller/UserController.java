package com.knodtec.kpmsadminsvc.controller;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.knodtec.kpmsadminsvc.dao.service.ReporterDaoService;
import com.knodtec.kpmsadminsvc.exception.UserNotFoundException;
import com.knodtec.kpmsadminsvc.model.Gender;
import com.knodtec.kpmsadminsvc.model.LeaveData;
import com.knodtec.kpmsadminsvc.model.MaritalStatus;
import com.knodtec.kpmsadminsvc.model.PersonContactInfo;
import com.knodtec.kpmsadminsvc.model.PersonalInfo;
import com.knodtec.kpmsadminsvc.model.Reporter;
import com.knodtec.kpmsadminsvc.model.Users;
import com.knodtec.kpmsadminsvc.service.MarritalStatusService;
import com.knodtec.kpmsadminsvc.service.PersonContactInfoService;
import com.knodtec.kpmsadminsvc.service.PersonalInfoService;
import com.knodtec.kpmsadminsvc.service.SendMailService;
import com.knodtec.kpmsadminsvc.service.UserService;

@CrossOrigin(origins = "${ui.url}")
@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	ReporterDaoService reporterDaoService;

	@Autowired
	SendMailService sendMailService;

	@Autowired
	MarritalStatusService marritalStatusService;

	@Autowired
	PersonalInfoService personalInfoService;

	@Autowired
	PersonContactInfoService personContactInfoService;

	@Autowired
	BCryptPasswordEncoder pwdEncoder;

	@Autowired
	RestTemplate restTemplate;
	
    @Value("${lms.url}")
	String LMS_URL;

	@GetMapping("/all")
	public List<Users> getAllUsers() {

		LOGGER.info("getting All the User Details");

		List<Users> users = userService.findAll();
		if (users.isEmpty())
			throw new UserNotFoundException("Users are Not Available");
		LOGGER.debug("All User Details:{}", users);
		return users;

	}
	
	@GetMapping("/totalEmp")
	public Long getAllEmployeeCount() {

		LOGGER.info("getting All the User Details");

		List<Users> users = userService.findAll();
		Long totalEmp = (long) users.size();
		if (users.isEmpty())
			throw new UserNotFoundException("Users are Not Available");
		LOGGER.debug("All User Details:{}", users);
		return totalEmp;
	}

	@GetMapping("/user/{id}")
	public Users getUser(@PathVariable Long id) {
		Users user = userService.findOne(id).orElse(null);

		LOGGER.info("getting User Details for id:{}", id);

		if (user == null)
			throw new UserNotFoundException("User Details Not found for id=" + id);
		return user;
	}

	@GetMapping("/users/{id}")
	public Users getUserDetails(@PathVariable Long id) {
		Users user = userService.findOne(id).orElse(null);

		LOGGER.info("getting User Details for id:{}", id);
		user.setImage("");
		if (user == null)
			throw new UserNotFoundException("User Details Not found for id=" + id);
		return user;
	}

	@GetMapping("/person/{id}")
	public PersonalInfo getPersonByUserId(@PathVariable Long id) {
		PersonalInfo personInfo = personalInfoService.findByUserId(id).orElse(null);

		LOGGER.info("getting User Details for id:{}", id);

		if (personInfo == null) {
			MaritalStatus str = new MaritalStatus();
			str.setId(1l);
			personInfo = new PersonalInfo();
			personInfo.setAadharNo("NA");
			personInfo.setBloodGroup("NA");
			personInfo.setEmploymentOfSpouse("NA");
			personInfo.setNationality("NA");
			personInfo.setNoOfChildren(0l);
			personInfo.setPanNo("NA");
			personInfo.setPassportExpiryDate("NA");
			personInfo.setPassportNo("NA");
			personInfo.setMaritalstatus(str);
			personInfo.setReligion("NA");
		}

		return personInfo;
	}

	@GetMapping("/contactInfo/{id}")
	public PersonContactInfo getPersonContactByUser(@PathVariable Long id) {
		PersonContactInfo contactInfo = personContactInfoService.findOne(id).orElse(null);

		LOGGER.info("Person Contact Info Details for id:{}", id);

		if (contactInfo == null)
			throw new UserNotFoundException("Person Contact Info Details Not found for id=" + id);
		return contactInfo;
	}

	@GetMapping("/contact/{id}")
	public PersonContactInfo getPersonContactByUserId(@PathVariable Long id) {
		PersonContactInfo contactInfo = personContactInfoService.getByUserId(id);

		LOGGER.info("Person Contact Info Details for id:{}", id);

		if (contactInfo == null) {
			contactInfo = new PersonContactInfo();
			contactInfo.setPrimaryName("NA");
			contactInfo.setPrimaryPhone("NA");
			contactInfo.setPrimaryPhone1("NA");
			contactInfo.setPrimaryRelationShip("NA");
			contactInfo.setSecondaryName("NA");
			contactInfo.setSecondaryPhone("NA");
			contactInfo.setSecondaryPhone1("NA");
			contactInfo.setSecondaryRelationShip("NA");
		}
		return contactInfo;
	}

	@GetMapping("/marrital/all")
	public List<MaritalStatus> getMarritalStatus() {
		List<MaritalStatus> status = marritalStatusService.findAll();

		LOGGER.info("getting Marrital Status Details for id:{}");

		if (status == null)
			throw new UserNotFoundException("Marrital status Details Not found");
		return status;
	}

	@GetMapping("/personInfo/{id}")
	public PersonalInfo getPersonInfo(@PathVariable Long id) {
		PersonalInfo person = personalInfoService.findOne(id).orElse(null);

		LOGGER.info("getting Personal Information for id:{}" + id);

		if (person == null)
			throw new UserNotFoundException("Person Information Details Not found");
		return person;
	}

	@DeleteMapping(value = "/delete/{id}")
	public void deleteUser(@PathVariable Long id) {
		Users user = userService.findOne(id).orElse(null);
		LOGGER.info("Getting User Details for id:{}", id);
		if (user == null)
			throw new UserNotFoundException("User Details not found for id=" + id);
		else
			userService.deleteUser(id);
	}

	@PutMapping(value = "/save")
	public ResponseEntity<Object> saveUsers(@RequestBody Users user) {

		LOGGER.info("finding existing User Details for username:{}", user.getUserName());
		String username = user.getUserName().toLowerCase();
		String email = user.getEmailId().toLowerCase();
		Users existingUser = userService.findUser(username).orElse(null);
		Users savedUser;

		if (existingUser == null) {

			String encPassword = pwdEncoder.encode(user.getPassword());
			user.setPassword(encPassword);
			user.setUserName(username);
			user.setEmailId(email);
			savedUser = userService.save(user);
			try {
				addLeaves(savedUser);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new UserNotFoundException("Leaves not added " +e);
			}
			Reporter exist = new Reporter();
			if (savedUser.isReportTo()) {
				Reporter report = new Reporter();

				report.setUserId(savedUser.getUserId());
				report.setEmail(savedUser.getEmailId());
				report.setReporterName(savedUser.getFirstName() + " " + savedUser.getLastName());
				exist = reporterDaoService.getByuserId(savedUser.getUserId());
				if (exist == null) {
					reporterDaoService.save(report);
				}
			} else if (!savedUser.isReportTo()) {
				exist = reporterDaoService.getByuserId(savedUser.getUserId());
				reporterDaoService.deleteById(exist.getId());
			}
		} else {
			LOGGER.error("User Details Exists for username:{}", user.getUserName());
			throw new UserNotFoundException("User Already Exists=" + user.getUserName());

		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getUserId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping(value = "person/save")
	public ResponseEntity<Object> savePersonInfo(@RequestBody PersonalInfo personInfo) {

		LOGGER.info("finding existing User Details for username:{}", personInfo.getUserId());

		PersonalInfo existingPerson = personalInfoService.findByUserId(personInfo.getUserId()).orElse(null);
		PersonalInfo savedPerson;

		if (existingPerson == null) {

			savedPerson = personalInfoService.save(personInfo);

		} else {
			LOGGER.error("Person Details Exists for username:{}", personInfo.getUserId());
			throw new UserNotFoundException("Person Already Exists=" + personInfo.getUserId());

		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPerson.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping(value = "contact/save")
	public ResponseEntity<Object> saveContactInfo(@RequestBody PersonContactInfo personContactInfo) {

		LOGGER.info("finding existing Person Contact Info Details for UserId:{}", personContactInfo.getUserId());

		PersonContactInfo existingContactPerson = personContactInfoService.getByUserId(personContactInfo.getUserId());
		PersonContactInfo savedContactPerson;

		if (existingContactPerson == null) {

			savedContactPerson = personContactInfoService.save(personContactInfo);

		} else {
			LOGGER.error("Person Contact Details Exists for username:{}", personContactInfo.getUserId());
			throw new UserNotFoundException("Person Contact Details Already Exists=" + personContactInfo.getUserId());
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(personContactInfo.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping(value = "/updatePass")
	public ResponseEntity<Object> updateUsersPassword(@RequestBody Users user) {
		LOGGER.info("finding existing User Details for userId:{}", user.getUserId());

		Users existingUser = userService.findByUserId(user.getUserId()).orElse(null);
		Users savedUser;
		if (existingUser == null) {
			throw new UserNotFoundException("User Not Exists=" + user.getUserId());
		} else {
			String encPassword = pwdEncoder.encode(user.getPassword());
			user.setPassword(encPassword);
			savedUser = userService.save(user);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getUserId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping(value = "/update")
	public ResponseEntity<Object> updateUsers(@RequestBody Users user) {
		LOGGER.info("finding existing User Details for userId:{}", user.getUserId());

		Users existingUser = userService.findByUserId(user.getUserId()).orElse(null);
		Users savedUser;
		if (existingUser == null) {
			throw new UserNotFoundException("User Not Exists=" + user.getUserId());
		} else {

			Reporter exist = new Reporter();
			if (user.isReportTo()) {
				Reporter report = new Reporter();
				report.setUserId(user.getUserId());
				report.setReporterName(user.getFirstName() + " " + user.getLastName());
				exist = reporterDaoService.getByuserId(user.getUserId());
				if (exist == null) {
					reporterDaoService.save(report);
				}
			} else if (!user.isReportTo()) {
				exist = reporterDaoService.getByuserId(user.getUserId());
				if (exist != null) {
					List<Users> usr = userService.findAll();
					Long checkId = exist.getId();
					boolean contains = usr.stream().anyMatch(o -> o.getReporter().getId().equals(checkId));
					user.setReportTo(true);
					if (!contains) {
						user.setReportTo(false);
						reporterDaoService.deleteById(exist.getId());
					}
				}
			}
			savedUser = userService.save(user);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getUserId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping(value = "person/update")
	public ResponseEntity<Object> updatePersonInfo(@RequestBody PersonalInfo personalInfo) {
		LOGGER.info("finding existing Person Details for userId:{}", personalInfo.getUserId());

		PersonalInfo existingPerson = personalInfoService.findByUserId(personalInfo.getUserId()).orElse(null);
		PersonalInfo savedPerson;

		if (existingPerson == null) {

			throw new UserNotFoundException("User Not Exists=" + personalInfo.getUserId());

		} else {
			savedPerson = personalInfoService.save(personalInfo);

		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPerson.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "contact/update")
	private ResponseEntity<Object> updateContactInfo(@RequestBody PersonContactInfo personContactInfo) {

		LOGGER.info("finding existing Person Contact Info Details for UserId:{}", personContactInfo.getUserId());

		PersonContactInfo existingContactPerson = personContactInfoService.getByUserId(personContactInfo.getUserId());
		PersonContactInfo savedContactPerson;

		if (existingContactPerson == null) {

			throw new UserNotFoundException("This User Contact Info Not Exists=" + personContactInfo.getUserId());

		} else {
			LOGGER.error("Person Contact Details Exists for username:{}", personContactInfo.getUserId());

			savedContactPerson = personContactInfoService.save(personContactInfo);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(personContactInfo.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@GetMapping("/login")
	private Users getUser(@RequestParam String userName, @RequestParam String password) {
		Users user = null;
		if (userName.contains("@")) {
			user = userService.findEmail(userName.toLowerCase()).orElse(null);
		} else {
			user = userService.findUser(userName.toLowerCase()).orElse(null);
		}

		LOGGER.info("Getting User Details for username:{}", userName);

		if (user == null) {
			throw new UserNotFoundException("User Details Not found for username=" + userName);
		} else {
			LOGGER.info("Checking for User Password for username:{}", userName);

			if (pwdEncoder.matches(password, user.getPassword())) {

				return user;
			} else {
				throw new UserNotFoundException("Password not matching for username=" + userName);
			}
		}

	}

	@GetMapping("/gender")
	public List<Gender> getGender() {
		return userService.findAllGender();
	}

	@GetMapping("/reporter")
	public List<Reporter> getReports() {
		return reporterDaoService.findAll();
	}

	@GetMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String email) throws AddressException, MessagingException, IOException {
		sendmail(email);
		return "Mail Sent Successfully.....!!!!";
	}

	public void sendmail(String email) throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("knodtec.donotreply@gmail.com", "uvrunmdoivzxqrdg");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("knodtec.donotreply@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		Users user = userService.findEmail(email).orElse(null);

		msg.setSubject("KnodTech Solutions Password Retrive..");
		String message = "Dear KnodTechies,\n Your password is : " + user.getPassword()
				+ "\n Thanks and Regard's \n KnodTech Solution Pvt. Ltd.,";
		String s = "<b>Dear KnodTechies</b>,<br></br><br><i>Your password is : " + user.getPassword()
				+ "</i></br><br></br><br>Thanks and Regard's</br><br>KnodTech Solution Pvt. Ltd.,</br>";
		msg.setContent(s, "text/html");

		msg.setSentDate(new Date());

		Transport.send(msg);
	}

	private void addLeaves(Users savedUser) throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		LeaveData leaves = new LeaveData();
		LocalDate myLocal = LocalDate.now();
		int quarter = myLocal.get(IsoFields.QUARTER_OF_YEAR);
        leaves.setCreatedBy((long)1);
		leaves.setAnnual(getErnedLeaves(quarter));

		leaves.setEmpId(savedUser.getUserId());
		leaves.setHospitalisation(5);
		leaves.setLop(0);
		leaves.setSickLeave(getSickleave(quarter));
		if (savedUser.getGender().getId() == 1) {
			leaves.setPaternity(5);
			leaves.setMaternity(0);
		} else {
			leaves.setMaternity(180);
			leaves.setPaternity(0);
		}
		
		
		HttpEntity<LeaveData> httpEntity = new HttpEntity<>(leaves, headers);

		ResponseEntity<String> response = restTemplate.exchange(LMS_URL+"/management/saveTOCreate", HttpMethod.PUT, httpEntity, String.class);
		if (response.getStatusCodeValue() == 201) {
			System.out.println("Employee Leaves Added Successfully..");
		}
	}

	private int getSickleave(int quarter) {
		if (quarter == 1) {
			return 6;
		} else if (quarter == 2) {
			return 4;
		} else if (quarter == 3) {
			return 3;
		} else {
			return 2;
		}
	}

	private int getErnedLeaves(int quarter) {
		if (quarter == 1) {
			return 12;
		} else if (quarter == 2) {
			return 8;
		} else if (quarter == 3) {
			return 4;
		} else {
			return 2;
		}
	}

}
