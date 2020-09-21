package com.mysimplehacks.studenth2project.dao;

import com.mysimplehacks.studenth2project.entity.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> findAll();

    Student findById(int id);

    void save(Student student);

    void deleteById(int id);

}
