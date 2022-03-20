package com.example.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalproject.DTO.AccountDto;
import com.example.finalproject.DTO.LoginDto;
import com.example.finalproject.DTO.SignUpDto;
import com.example.finalproject.entity.AccountEntity;
import com.example.finalproject.entity.ApplicantEntity;
import com.example.finalproject.entity.CompanyEntity;
import com.example.finalproject.entity.RoleEntity;
import com.example.finalproject.repository.AccountRepository;
import com.example.finalproject.repository.ApplicantRepository;
import com.example.finalproject.repository.CompanyRepository;
import com.example.finalproject.repository.RoleRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ApplicantRepository applicantRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<AccountDto> authenticateUser(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		AccountEntity account = accountRepository.findByUsername(loginDto.getUsername()).get();
		AccountDto accountDto = new AccountDto();
		accountDto.setId(account.getId());
		accountDto.setRoleId(account.getRole().getId());
		return new ResponseEntity<>(accountDto, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
		if (accountRepository.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}
		AccountEntity account = new AccountEntity();
		account.setUsername(signUpDto.getUsername());
		account.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

		if (signUpDto.getRole() == 1) {
			ApplicantEntity applicant = new ApplicantEntity();
			applicant.setName(signUpDto.getName());
			applicant.setAge(signUpDto.getAge());
			applicant.setEmail(signUpDto.getEmail());
			applicant.setAddress(signUpDto.getAddress());
			applicant.setAccount(account);
			RoleEntity role = roleRepository.findByName("ROLE_APPLICANT").get();
			account.setRole(role);
			accountRepository.save(account);
			applicantRepository.save(applicant);
		} else {
			CompanyEntity company = new CompanyEntity();
			company.setName(signUpDto.getName());
			company.setWebsite(signUpDto.getWebsite());
			company.setPhone(signUpDto.getPhone());
			company.setEmail(signUpDto.getEmail());
			company.setAddress(signUpDto.getAddress());
			company.setLogo(
					"https://firebasestorage.googleapis.com/v0/b/final-project-8d1d3.appspot.com/o/img%2Fdefault_logo.jpg?alt=media&token=f7f3755d-2b3f-4b65-aefc-69fbea2e7a04");
			company.setDescription("");
			company.setAccount(account);
			RoleEntity role = roleRepository.findByName("ROLE_COMPANY").get();
			account.setRole(role);
			accountRepository.save(account);
			companyRepository.save(company);
		}
		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

	}
}
