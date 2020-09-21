package org.mysimplehacks;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.mysimplehacks.model.Book;
import org.mysimplehacks.model.Reader;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml")
                                               .addAnnotatedClass(Reader.class)
                                               .addAnnotatedClass(Book.class)
                                               .buildSessionFactory();

        Session session = sf.openSession();

        Book b1 = new Book();
        b1.setBookId(1);
        b1.setBookName("JavaBook");

        Book b2 = new Book();
        b2.setBookId(2);
        b2.setBookName("PythonBook");

        Book b3 = new Book();
        b3.setBookId(3);
        b3.setBookName("SQLBook");

        Reader r1 = new Reader();
        r1.setReaderId(101);
        r1.setReaderName("Raju");
        r1.getBooks().add(b1);
        r1.getBooks().add(b2);

        Reader r2 = new Reader();
        r2.setReaderId(102);
        r2.setReaderName("Rani");
        r2.getBooks().add(b3);

        Transaction tx = session.beginTransaction();

        session.save(r1);
        session.save(r2);
        session.save(b1);
        session.save(b2);
        session.save(b3);

        System.out.println("-------------------------------");

        Reader temp = session.get(Reader.class, 101);
        System.out.println(temp);

        System.out.println("-------------------------------");

        List<Book> bookList = temp.getBooks();
        for (Book b : bookList) {
            System.out.println(b);
        }

        tx.commit();

    }

}
