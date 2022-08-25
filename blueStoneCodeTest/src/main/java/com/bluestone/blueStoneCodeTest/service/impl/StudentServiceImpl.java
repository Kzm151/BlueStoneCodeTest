package com.bluestone.blueStoneCodeTest.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluestone.blueStoneCodeTest.entity.StudentEntity;
import com.bluestone.blueStoneCodeTest.respository.StudentRepository;
import com.bluestone.blueStoneCodeTest.service.StudentService;
import com.bluestone.blueStoneCodeTest.util.Custom;
import com.bluestone.blueStoneCodeTest.util.StudentDto;

@Service
@Custom(name = "anno2")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void register(StudentDto dto) {
		StudentEntity entity = new StudentEntity();
		entity.setRollNo(dto.getRollNo());
		entity.setStudentClass(dto.getStudentClass());
		entity.setStudentName(dto.getStudentName());
		studentRepository.save(entity);

	}

	@Override
	public void deleteStudent(Long id) {
		studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
		studentRepository.deleteById(id);
	}

	@Override
	public List<StudentDto> getAllStudentsList() {
		List<StudentEntity> all = studentRepository.findAll();
		return all.stream().map(dto -> new StudentDto(dto)).collect(Collectors.toList());
	}

	@Override
	public StudentDto updateStudent(Long id, StudentDto dto) {
		StudentEntity searchResult = studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
		searchResult.setRollNo(dto.getRollNo());
		searchResult.setStudentClass(dto.getStudentClass());
		searchResult.setStudentName(dto.getStudentName());
		StudentEntity savedResult = studentRepository.save(searchResult);
		return new StudentDto(savedResult);
	}

	@Override
	public Long countStudents() {
		return studentRepository.count();
	}
}
