package org.example;

import Entity.AuthorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.List;
import java.util.Properties;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost:3307/library");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "root");

        SessionFactory sessionFactory = new Configuration()
                .setProperties(props)
                .addAnnotatedClass(AuthorEntity.class)
                .buildSessionFactory();



        try (Session session = sessionFactory.openSession()) {
            List<AuthorEntity> authors = session.createQuery("from AuthorEntity").list();
            AuthorEntity authorEntity = authors.get(0);
            System.out.println(authorEntity.getFirstName());

            Transaction transaction = session.beginTransaction();
            authorEntity.setFirstName("anotherName");
            session.save(authorEntity);
            transaction.commit();

            List<AuthorEntity> authorsAgain = session.createQuery("from AuthorEntity").list();
            AuthorEntity authorEntityAgain = authorsAgain.get(0);
            System.out.println(authorEntityAgain.getFirstName());
        }

    }
}