package org.mysimplehacks;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.mysimplehacks.model.Stud;

import javax.persistence.Query;
import java.util.List;
import java.util.Random;

public class HQLApplication {

    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Stud.class);

        //The main purpose of a service registry is to hold, manage and provide access to services.
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        SessionFactory sf = configuration.buildSessionFactory(serviceRegistry);

        Session session = sf.openSession();
        session.beginTransaction();

        Random random = new Random();

        for (int i = 1; i <= 10; i++) {

            Stud s = new Stud();
            s.setId(i);
            s.setName("Name :" + i);
            s.setMarks(random.nextInt(100));
            session.save(s);

        }

        session.getTransaction().commit();

        Session session2 = sf.openSession();
        session2.beginTransaction();

        Query q = session2.createQuery("from Stud where marks > 60");

        @SuppressWarnings("unchecked") List<Stud> studs = q.getResultList();

        for (Stud s : studs) {
            System.out.println(s);
        }

        session2.getTransaction().commit();

    }

}
