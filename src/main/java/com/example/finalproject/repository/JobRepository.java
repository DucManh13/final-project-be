package com.example.finalproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalproject.entity.JobEntity;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Integer> {
	List<JobEntity> findAllByCompanyIdOrderByIdAsc(int companyId);

	List<JobEntity> findAllByOrderByIdAsc();

	Boolean existsByIdAndCvsApplicantId(int jobId, int applicantId);
}