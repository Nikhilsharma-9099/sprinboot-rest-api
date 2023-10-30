package net.nikhilsharma.sprinbootrestapi.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.nikhilsharma.sprinbootrestapi.Bean.Student;

@RestController
@RequestMapping("students")
public class StudentController {
	
	@GetMapping("student")
	public ResponseEntity<Student> getStudent() {
		
		Student student = new Student(1,"Nikhil","Sharma");
		//return new ResponseEntity<>(student,HttpStatus.OK);	
		return ResponseEntity.ok().header("custom-header","Nikhil").body(student);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getStudents(){
		List<Student> students = new ArrayList<>();
		students.add(new Student(1,"Nikhil","Sharma"));
		students.add(new Student(2,"Akhil","Sharma"));
		students.add(new Student(3,"Paras","Sharma"));
		students.add(new Student(4,"Rohit","Sharma"));
		return ResponseEntity.ok(students);
	}
	
	// RSET API with Path Variable
	//{id}- URI template variable
	// http://localhost:8080/students/1/nikhil/sharma
	@GetMapping("{id}/{first-name}/{last-name}")
	public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,@PathVariable("first-name") String firstName,
			@PathVariable("last-name") String lastName) {
		Student student = new Student(studentId, firstName, lastName);
		return ResponseEntity.ok(student);
	}
	
	//REST API using RequestParam
	// http://localhost:8080/students/query?id=1&firstName=Nikhil&lastName=Sharma
	@GetMapping("query")
	public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName) {
		Student student = new Student(id, firstName, lastName);
		return ResponseEntity.ok(student);
	}
	
	// Spring Boot REST API that handles HTTP POST Request - creating a new resource
	// @PostMapping and @RequestMapping
	@PostMapping("create")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		System.out.println(student.getId());
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
	
	// Spring boot REST API that handles HTTP PUT Request - Updating existing resource
	@PutMapping("{id}/update")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable("id") int studentId) {
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return ResponseEntity.ok(student);		
	}
	
	// REST API that handles HTTP DELETE Request - deleting the existing resource
	@DeleteMapping("{id}/delete")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
		System.out.println(studentId);
		return ResponseEntity.ok( "Student deleted successfully!");
	}

}
