package com.example.finalproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalproject.entity.ApplicantEntity;

@Repository
public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Integer> {
	ApplicantEntity findByAccountId(int accountId);

	List<ApplicantEntity> findAllByOrderByIdAsc();
}
