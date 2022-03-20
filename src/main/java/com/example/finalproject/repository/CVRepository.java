package com.example.finalproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalproject.entity.CVEntity;

@Repository
public interface CVRepository extends JpaRepository<CVEntity, Integer> {
	List<CVEntity> findAllByApplicantIdOrderByIdAsc(int applicantId);

	List<CVEntity> findAllByJobsId(int jobId);
}