package org.mysimplehacks;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.mysimplehacks.model.Laptop;
import org.mysimplehacks.model.Student;
import org.mysimplehacks.model.StudentName;

import java.util.List;

public class App {

    public static void main( String[] args ) {

        StudentName sn1 = new StudentName();
        sn1.setFirstName("Urvik");
        sn1.setMiddleName("Shantilal");
        sn1.setLastName("Sorathia");

        StudentName sn2 = new StudentName();
        sn2.setFirstName("Sweta");
        sn2.setMiddleName("Shantilal");
        sn2.setLastName("Sorathia");

        Laptop laptop1 = new Laptop();
        laptop1.setLid(1);
        laptop1.setLaptopName("HP");

        Laptop laptop2 = new Laptop();
        laptop2.setLid(2);
        laptop2.setLaptopName("Mac");

        Laptop laptop3 = new Laptop();
        laptop3.setLid(3);
        laptop3.setLaptopName("Dell");

        Student s1 = new Student();
        s1.setId(101);
        s1.setStudentName(sn1);
        s1.setTech("Java");
        s1.getLaptop().add(laptop1);
        s1.getLaptop().add(laptop2);

        Student s2 = new Student();
        s2.setId(102);
        s2.setStudentName(sn2);
        s2.setTech("Python");
        s1.getLaptop().add(laptop2);
        s1.getLaptop().add(laptop3);

        laptop1.getStudents().add(s1);
        laptop2.getStudents().add(s1);
        laptop2.getStudents().add(s2);
        laptop3.getStudents().add(s2);

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                                                           .addAnnotatedClass(Student.class)
                                                           .addAnnotatedClass(Laptop.class)
                                                           .buildSessionFactory();

        //We need to follow the atomicity so we need to use transaction for the db operations
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.save(s2);
        session.save(laptop1);
        session.save(laptop2);
        session.save(laptop3);
        session.save(s1);

        tx.commit();

        //Before the mapping concept used to retrieve from db (cRud)
        //load(sessionFactory);

    }
    
    public static void load(SessionFactory sessionFactory){

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Student> studentList = session.createQuery("from Student").getResultList();

        System.out.println(studentList);

        System.out.println("Getting object by Id");
        Student student = session.get(Student.class, 101);
        System.out.println(student);

        tx.commit();
        
    }

}
