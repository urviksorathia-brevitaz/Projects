package com.mysimplehacks.studenth2project.controller;

import com.mysimplehacks.studenth2project.entity.Student;
import com.mysimplehacks.studenth2project.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/fetchAll")
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/fetch/{id}")
    public Student getStudent(@PathVariable int id){
        return studentService.findById(id);
    }

    @PostMapping("/addOrUpdate")
    public void addStudent(@RequestBody Student student){
        studentService.save(student);
    }

    @PutMapping("/addOrUpdate")
    public void updateStudent(@RequestBody Student student){
        studentService.save(student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable int id){

        Student tempStudent = studentService.findById(id);

        if (tempStudent == null) {
            throw new RuntimeException("Student with id : " + id + " not found.");
        }

        studentService.deleteById(id);

    }

}
