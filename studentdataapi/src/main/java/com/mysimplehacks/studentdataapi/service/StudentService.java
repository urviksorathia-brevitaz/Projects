package com.mysimplehacks.studentdataapi.service;

import com.mysimplehacks.studentdataapi.entity.Student;
import com.mysimplehacks.studentdataapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepo;

    @Autowired
    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    //Returns the list of all the students
    public List<Student> getStudents(){
        return new ArrayList<>(studentRepo.findAll());
    }

    //Getting individual student based on the different parameters
    public Optional<Student> getStudent(long id){
        return studentRepo.findById(id);
    }

    public List<Student> getStudentByFirstName(String firstName){
        return studentRepo.findByFirstName(firstName);
    }

    public List<Student> getStudentByPercentage(float percentage){
        return studentRepo.findByPercentageGreaterThanEqual(percentage);
    }

    //Adding the student to database
    public void addStudent(Student student){
        studentRepo.save(student);
    }

    //Update the student into database
    public void updateStudent(Student student){
        studentRepo.save(student);
    }

    //Delete the student from database
    public void deleteStudent(long id){
        studentRepo.deleteById(id);
    }

}
