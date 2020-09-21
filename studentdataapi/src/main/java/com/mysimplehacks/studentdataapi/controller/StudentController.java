package com.mysimplehacks.studentdataapi.controller;

import com.mysimplehacks.studentdataapi.entity.Student;
import com.mysimplehacks.studentdataapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/fetchAll")
    public List<Student> getAllStudents(){
        return studentService.getStudents();
    }

    @GetMapping("/fetchById/{id}")
    //@PostMapping("/fetch")
    public Optional<Student> getTheStudentById(@PathVariable long id){
        return studentService.getStudent(id);
    }

    @GetMapping("/fetchByName/{firstName}")
    public List<Student> getTheStudentByFirstName(@PathVariable String firstName){
        return studentService.getStudentByFirstName(firstName);
    }

    @GetMapping("/fetchByPercentage/{percentage}")
    public List<Student> getTheStudentByPercentage(@PathVariable float percentage){
        return studentService.getStudentByPercentage(percentage);
    }

    @PostMapping("/addOrUpdate")
    public void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @PutMapping("/addOrUpdate")
    public void updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable long id){
        studentService.deleteStudent(id);
    }

}
