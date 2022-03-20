package com.example.finalproject.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.finalproject.DTO.AccountItemDto;
import com.example.finalproject.entity.AccountEntity;
import com.example.finalproject.entity.AdminEntity;
import com.example.finalproject.entity.ApplicantEntity;
import com.example.finalproject.entity.CompanyEntity;
import com.example.finalproject.repository.AccountRepository;
import com.example.finalproject.repository.AdminRepository;
import com.example.finalproject.repository.ApplicantRepository;
import com.example.finalproject.repository.CompanyRepository;

@Service
public class AccountService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ApplicantRepository applicantRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountEntity account = accountRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Account not found with username:" + username));
		return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority(account.getRole().getName())));
	}

	public String getUsername(int id) {
		return accountRepository.getById(id).getUsername();
	}

	public List<AccountItemDto> getAllAccounts() {
		List<AccountItemDto> accounts = new ArrayList<>();
		List<ApplicantEntity> applicantList = applicantRepository.findAllByOrderByIdAsc();
		applicantList.forEach(applicant -> {
			AccountItemDto accountItemDto = new AccountItemDto();
			accountItemDto.setId(applicant.getAccount().getId());
			accountItemDto.setUsername(applicant.getAccount().getUsername());
			accountItemDto.setName(applicant.getName());
			accountItemDto.setRole(applicant.getAccount().getRole().getName());
			accounts.add(accountItemDto);
		});
		List<CompanyEntity> companyList = companyRepository.findAllByOrderByIdAsc();
		companyList.forEach(company -> {
			AccountItemDto accountItemDto = new AccountItemDto();
			accountItemDto.setId(company.getAccount().getId());
			accountItemDto.setUsername(company.getAccount().getUsername());
			accountItemDto.setName(company.getName());
			accountItemDto.setRole(company.getAccount().getRole().getName());
			accounts.add(accountItemDto);
		});
		List<AdminEntity> adminList = adminRepository.findAllByOrderByIdAsc();
		adminList.forEach(admin -> {
			AccountItemDto accountItemDto = new AccountItemDto();
			accountItemDto.setId(admin.getAccount().getId());
			accountItemDto.setUsername(admin.getAccount().getUsername());
			accountItemDto.setName(admin.getName());
			accountItemDto.setRole(admin.getAccount().getRole().getName());
			accounts.add(accountItemDto);
		});
		return accounts;
	}

}