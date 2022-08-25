package com.bluestone.blueStoneCodeTest.service;

import java.util.List;
import com.bluestone.blueStoneCodeTest.util.StudentDto;

public interface StudentService {
	
	// method to register studentDto as studentEntity
	void register(StudentDto studentDto);

	// method to delete student record with given id
	void deleteStudent(Long id);

	// method to find all student as studentDto List
	List<StudentDto> getAllStudentsList();

	// method to update studentEntiy with given id
	StudentDto updateStudent(Long id, StudentDto studentDto);

	// method to count number of students
	Long countStudents();

}
