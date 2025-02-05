package com.NativeJPQLQuery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.NativeJPQLQuery.entity.Student;
import com.NativeJPQLQuery.repo.StudentRepo;

@RestController
public class Controller {
 
	
	@Autowired
	private StudentRepo studentRepo;

	//save student
	
	@PostMapping("/student")
	public void saveStudent(@RequestBody Student student)
	{
		studentRepo.save(student);
	}
	
	// get all Student record 
	
	@GetMapping("/student")
	public List<Student> getAllStudent()
	{
		return studentRepo.findAll();
	}
	

        @GetMapping("/studentgetbynameAndCourse/{name}/{course}")
		public List<Student> getByName(@PathVariable String name,@PathVariable String course)
		{
			return studentRepo.getByNameAndCourse(name,course);
		}
		
		
		@PostMapping("/insertStudent")
		public void insertStudent(@RequestBody Student student)
		{
			studentRepo.insertStudent(student.getName(), student.getCourse(), student.getAge());
			
		}
		
		
		@PutMapping("/updateStudent")
		public void updateStudent(@RequestBody Student student) {
			
			studentRepo.updateStudent(student.getName(), student.getAge(), student.getCourse());
		}
		
		
		//get by course
		@GetMapping("/by-course/{course}")
	    public List<Student> getByCourseJPQL(@PathVariable String course) {
	        return studentRepo.findByCourseJPQL(course);
	    }
		
		// Get students older than a specific age (JPQL)
		//@GetMapping("/older-than/{age}")
	    //public List<Student> getByAgeGreaterThanJPQL(@PathVariable int age) {
	        //return studentRepo.findByAgeGreaterThanJPQL(age);
	    //}
		
		@DeleteMapping("/deleteMapping/{name}")
		public void deleteStudent(@PathVariable String name)
		{
			studentRepo.deleteStudent(name);
		}
		
	
	
}
