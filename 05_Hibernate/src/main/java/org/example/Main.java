package org.example;

import org.example.entity.AuthorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(org.example.entity.AuthorEntity.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
         List<AuthorEntity> list = session.createQuery("from AuthorEntity", AuthorEntity.class).list();
            AuthorEntity authorEntity = list.get(0);
            System.out.println(authorEntity.getFirstName());

            var transaction = session.beginTransaction();
            authorEntity.setFirstName("Robert");
            session.save(authorEntity);
            transaction.commit();
        }
    }
}