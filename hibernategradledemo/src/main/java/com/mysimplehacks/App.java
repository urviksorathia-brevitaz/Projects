package com.mysimplehacks;

import com.mysimplehacks.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class App {

    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Student s1 = new Student();
        s1.setId(101);
        s1.setName("Urvik");
        s1.setTech("Java");

        Student s2 = new Student();
        s2.setId(102);
        s2.setName("Parth");
        s2.setTech("Java");

        Student s3 = new Student();
        s3.setId(103);
        s3.setName("Taral");
        s3.setTech("Python");

        Student s4 = new Student();
        s4.setId(104);
        s4.setName("Arth");
        s4.setTech("LifeRay");

        Session session1 = sessionFactory.openSession();

        session1.beginTransaction();
        session1.save(s1);
        session1.save(s2);
        session1.save(s3);
        session1.save(s4);
        session1.getTransaction().commit();

        session1.close();

        System.out.println("------------------------------");

        Session session2 = sessionFactory.openSession();

        session2.beginTransaction();
        Student existingStudent = session2.get(Student.class, 101); //Get vs Load check-out and find() too..
        System.out.println(existingStudent.getId());
        System.out.println(existingStudent.getName());
        System.out.println(existingStudent.getTech());

        existingStudent.setTech("Java Spring");

        System.out.println("--------------Updating the data----------------");

        session2.update(existingStudent);

        existingStudent = session2.get(Student.class, 101); //Get vs Load check-out and find() too..
        System.out.println(existingStudent.getId());
        System.out.println(existingStudent.getName());
        System.out.println(existingStudent.getTech());

        session2.getTransaction().commit();

        session2.close();

        System.out.println("------------------------------");

        Session session3 = sessionFactory.openSession();

        session3.beginTransaction();
        session3.delete(existingStudent);
        session3.getTransaction().commit();

        session3.close();

        System.out.println("-----------------------------------------");

        Session session4 = sessionFactory.openSession();

        session4.beginTransaction();

        List<Student> students = session4.createQuery("from Student", Student.class)
                .getResultList();

        for (Student s : students) {
            System.out.println(s.getId() + " " + s.getName() + " " + s.getTech());
        }

        session4.getTransaction().commit();
        session4.close();
        System.out.println("-----------------------------------------");

    }

}
