package org.example;


import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Employee.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createMutationQuery("delete from Employee");
            transaction.commit();

            // Transient
            Employee employee = new Employee();

            // persistent
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();

//            //detached
//            session.evict(employee);
//
//            //persistent again
//            employee = session.load(Employee.class, 1);

            // removed
            session.remove(employee);

            transaction = session.beginTransaction();
            session.flush();
            transaction.commit();


        }

    }
}