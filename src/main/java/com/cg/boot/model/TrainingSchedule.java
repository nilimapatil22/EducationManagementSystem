package com.cg.boot.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Priyanka. This class includes declaration of parameters of training
 *         schedule class, default constructor, parameterized constructors,
 *         getter and setter methods of parameters and toString method to
 *         display.
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "training_schedule")
public class TrainingSchedule implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "schedule_id")
	private int scheduleId;
	
	@Column(name="course_name")
	@NotEmpty(message ="Course Name should not empty")
	private String courseName;
	
	@NotEmpty(message = "Date should not empty")
	@Pattern(regexp = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "Date should be in yyyy-MM-dd format")
	private String startDate;
	
	@NotEmpty(message = "Date should not empty")
	@Pattern(regexp = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "Date should be in yyyy-MM-dd format")
	private String endDate;
	
	@Column(name = "student_id")
	@NotNull(message = "Student Id should not empty")
	private int studentId;
	
	@Column(name = "trainer_name")
	@NotEmpty(message = "Trainer name should not empty")
	private String trainerName;

	public TrainingSchedule() {

	}
	

	public TrainingSchedule(int scheduleId, @NotEmpty(message = "Course Name should not empty") String courseName,
			@NotEmpty(message = "Date should not empty") @Pattern(regexp = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "Date should be in yyyy-MM-dd format") String startDate,
			@NotEmpty(message = "Date should not empty") @Pattern(regexp = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "Date should be in yyyy-MM-dd format") String endDate,
			@NotNull(message = "Student Id should not empty") int studentId,
			@NotEmpty(message = "Trainer name should not empty") String trainerName) {
		super();
		this.scheduleId = scheduleId;
		this.courseName = courseName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.studentId = studentId;
		this.trainerName = trainerName;
	}

	public TrainingSchedule(@NotEmpty(message = "Course Name should not empty") String courseName,
			@NotEmpty(message = "Date should not empty") @Pattern(regexp = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "Date should be in yyyy-MM-dd format") String startDate,
			@NotEmpty(message = "Date should not empty") @Pattern(regexp = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "Date should be in yyyy-MM-dd format") String endDate,
			@NotNull(message = "Student Id should not empty") int studentId,
			@NotEmpty(message = "Trainer name should not empty") String trainerName) {
		super();
		this.courseName = courseName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.studentId = studentId;
		this.trainerName = trainerName;
	}


	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getTrainerName() {
		return trainerName;
	}


	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}


	@Override
	public String toString() {
		return "TrainingSchedule [scheduleId=" + scheduleId + ", courseName=" + courseName + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", studentId=" + studentId + ", trainerName=" + trainerName + "]";
	}
	






}
