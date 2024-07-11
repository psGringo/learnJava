package org.example.Service;

import org.example.entity.AuthorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DBService {
    public void exampleOne() {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(org.example.entity.AuthorEntity.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            List<AuthorEntity> list = session.createQuery("from AuthorEntity a where a.firstName != 'Robert'", AuthorEntity.class).list();
            AuthorEntity authorEntity = list.get(0);
            System.out.println(authorEntity.getFirstName());

            var transaction = session.beginTransaction();
            authorEntity.setFirstName("Robert");
            session.save(authorEntity);
            transaction.commit();
        }
    }
}
