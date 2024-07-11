package org.example;

import org.example.entity.BookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        queryExample();
        mutationQueryExample();
    }

    private static void mutationQueryExample() {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(BookEntity.class)
                .buildSessionFactory();


        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            BookEntity bookEntity = new BookEntity();
            bookEntity.setTitle("new book");
            session.persist(bookEntity);

            session.createMutationQuery("update BookEntity set title='new book1' where title='new book'").executeUpdate();
            transaction.commit();

            Query<BookEntity> query = session.createQuery("from BookEntity", BookEntity.class);
            query.list().forEach(be -> System.out.println(be.getTitle()));
        }
    }

    private static void queryExample() {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(BookEntity.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
                BookEntity bookEntity = new BookEntity();
                bookEntity.setTitle("new book");
                session.persist(bookEntity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }

            Query<BookEntity> query = session.createQuery("from BookEntity", BookEntity.class);
            query.list().forEach(be -> System.out.println(be.getTitle()));
        }
    }

}