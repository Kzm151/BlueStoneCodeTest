package com.bluestone.blueStoneCodeTest.util;

import com.bluestone.blueStoneCodeTest.entity.StudentEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

	private String studentName;

	private String studentClass;

	private String rollNo;

	/*
	 * To convert entity to dto
	 */
	public StudentDto(StudentEntity entity) {
		this.studentName = entity.getStudentName();
		this.studentClass = entity.getStudentClass();
		this.rollNo = entity.getRollNo();
	}
}
