package com.mysimplehacks.studentdataapi.repository;

import com.mysimplehacks.studentdataapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);
    List<Student> findByPercentageGreaterThanEqual(float percentage);

}
