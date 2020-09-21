package com.mysimplehacks.studenth2project.dao;

import com.mysimplehacks.studenth2project.entity.Student;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    //EntityManager can provide the instance of hibernate Session class
    //We can also use the hibernate configuration with SessionFactory for hibernate specific operation
    private final EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public List<Student> findAll() {
        
        //Get the current hibernate session instance
        Session session = entityManager.unwrap(Session.class);

        //Create query
        //noinspection unchecked
        return (List<Student>) session.createQuery("from Student").getResultList();

    }

    @Override
    public Student findById(int id) {

        //Get the current hibernate session
        Session session = entityManager.unwrap(Session.class);

        return session.get(Student.class, id);

    }

    @Override
    public void save(Student student) {

        //Get the current hibernate session
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(student);

    }

    @Override
    public void deleteById(int id) {

        //Get the current hibernate session
        Session session = entityManager.unwrap(Session.class);

        session.createQuery("delete from Student where id=:studentId")
               .setParameter("studentId", id)
               .executeUpdate();

    }
}
