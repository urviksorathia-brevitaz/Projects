package org.mysimplehacks;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mysimplehacks.model.Person;

import javax.persistence.Query;

public class CacheApplication {

    public static void main(String[] args) {

        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml")
                                               .addAnnotatedClass(Person.class)
                                               .buildSessionFactory();

        Person p1 = new Person();
        p1.setAge(15);
        p1.setId(101);
        p1.setName("Dushyant");

        Person p2 = new Person();
        p2.setAge(22);
        p2.setId(102);
        p2.setName("Urvik");

        Session session1 = sf.openSession();
        session1.beginTransaction();

        session1.save(p1);
        session1.save(p2);

        session1.getTransaction().commit();
        session1.close();

        System.out.println("-----------------\n");

        Session session2 = sf.openSession();
        session2.beginTransaction();

        Query query = session2.createQuery("from Person where id=101");
        Person temp = (Person) query.getSingleResult();

        //Person temp = session2.get(Person.class, 101);
        System.out.println(temp);

        session2.getTransaction().commit();
        session2.close();

        System.out.println("<--------Different Sessions same select query before 2nd level cache--------->");

        Session session3 = sf.openSession();
        session3.beginTransaction();

        query = session3.createQuery("from Person where id=101");
        temp = (Person) query.getSingleResult();

        //temp = session3.get(Person.class, 101);
        System.out.println(temp);

        session3.getTransaction().commit();
        session3.close();

    }

}
