package com.edu.wit.dronapp.controller;

import com.edu.wit.dronapp.model.Student;
import com.edu.wit.dronapp.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000") // adres Url Aplikacji React (http://localhost:3000)
public class StudentController {
    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.FOUND);

    }
    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id){
        return studentService.updateStudent(student, id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

}
