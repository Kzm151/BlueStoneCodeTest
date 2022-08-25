package com.bluestone.blueStoneCodeTest.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluestone.blueStoneCodeTest.exception.RegistrationFailException;
import com.bluestone.blueStoneCodeTest.exception.StudentNotFoundException;
import com.bluestone.blueStoneCodeTest.service.StudentService;
import com.bluestone.blueStoneCodeTest.util.Constants;
import com.bluestone.blueStoneCodeTest.util.Custom;
import com.bluestone.blueStoneCodeTest.util.StudentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
@Custom(name = "anno1")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> getAllStudents() {
		List<StudentDto> dtoList = studentService.getAllStudentsList();
		if (dtoList.isEmpty()) {
			throw new StudentNotFoundException(Constants.NO_STUDENT_REG);
		}
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}

	@PostMapping("/saveStudent")
	public ResponseEntity<String> registerStudent(@RequestBody StudentDto studentDto) {
		if (Strings.isBlank(studentDto.getRollNo()) || Strings.isBlank(studentDto.getStudentClass())
				|| Strings.isBlank(studentDto.getStudentName())) {
			throw new RegistrationFailException(Constants.REGISTRATION_FAIL);
		}
		studentService.register(studentDto);
		return new ResponseEntity<>(Constants.REGISTRATION_SUCCESS, HttpStatus.OK);

	}

	@PutMapping("/{id}/updateStudent")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto studentDto) {
		if (Strings.isBlank(studentDto.getRollNo()) || Strings.isBlank(studentDto.getStudentClass())
				|| Strings.isBlank(studentDto.getStudentName())) {
			throw new RegistrationFailException(Constants.REGISTRATION_FAIL);
		}
		try {
			StudentDto result = studentService.updateStudent(id, studentDto);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			throw new StudentNotFoundException(Constants.DELETION_FAIL);
		}

	}

	@DeleteMapping(value = "/{id}/deleteStudent")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) {
		try {
			studentService.deleteStudent(id);
		} catch (NoSuchElementException e) {
			throw new StudentNotFoundException(Constants.DELETION_FAIL);
		}
		return new ResponseEntity<>(Constants.DELETION_SUCCESS, HttpStatus.OK);
	}

	/*
	 * This method will be run 9:10 of the day 26 every month
	 */
	@Scheduled(cron = "0 10 9 26 * ?")
	public void cronTest() {
		Long numOfStudents = studentService.countStudents();
		log.info("Total Students in our school : " + numOfStudents.toString());
	}
}
