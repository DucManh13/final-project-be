package com.example.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalproject.DTO.AccountDto;
import com.example.finalproject.DTO.CVDto;
import com.example.finalproject.DTO.RequestCVDto;
import com.example.finalproject.service.CVService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cv")
public class CVController {
	@Autowired
	private CVService cvService;

	@PostMapping("/new")
	public ResponseEntity<String> create(@RequestBody RequestCVDto requestCvDto) {
		cvService.createCV(requestCvDto);
		return new ResponseEntity<>("Create CV successfully!.", HttpStatus.OK);
	}

	@PostMapping
	public List<CVDto> getCVs(@RequestBody AccountDto accountDto) {
		return cvService.getCVs(accountDto.getId());
	}

	@GetMapping("/{id}")
	public CVDto getCV(@PathVariable int id) {
		return cvService.getCVById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCV(@PathVariable int id, @RequestBody RequestCVDto requestCvDto) {
		cvService.updateCV(requestCvDto, id);
		return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
	}
}
