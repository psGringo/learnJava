package org.example;


import org.example.entity.Employee;
import org.example.entity.EmployeeTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(EmployeeTask.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            clearTables(session);
            populateTables(session, List.of("Stanley", "Ayna"));
            namedQueryExample(session);
            namedNativeQueryExample(session);
        }
    }

    private static void namedNativeQueryExample(Session session) {
        Query<Employee> query = session.createNamedQuery("selectAll", Employee.class);
        List<Employee> employeeList = query.list();
        employeeList.forEach(employee -> System.out.println(employee.getName()));
    }


    private static void namedQueryExample(Session session) {
        Query<Employee> query = session.createNamedQuery("findByName", Employee.class);
        query.setParameter("name", "Stanley");
        Employee employee = query.uniqueResult();
        System.out.println(employee.getName());
    }

    private static void clearTables(Session session) {
        Transaction transaction = session.beginTransaction();
        try {
            session.createMutationQuery("delete from Employee").executeUpdate();
            session.createMutationQuery("delete from EmployeeTask").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    private static void populateTables(Session session, List<String> employeeNames) {
        employeeNames.stream().forEach(name -> {
            Employee employee = populateEmployee(session, name);
            populateEmployeeTask(session, employee, "anotherTask");
        });
    }

    private static Employee populateEmployee(Session session, String name) {
        Transaction transaction = session.beginTransaction();
        try {
            Employee employee = new Employee();
            employee.setName(name);
            session.persist(employee);
            transaction.commit();
            return employee;
        } catch (Exception e) {
            transaction.rollback();
        }

        return null;
    }

    private static EmployeeTask populateEmployeeTask(Session session, Employee employee, String name) {
        Transaction transaction = session.beginTransaction();
        try {
            EmployeeTask employeeTask = new EmployeeTask();
            employeeTask.setEmployee(employee);
            employeeTask.setName(name);
            session.persist(employee);
            transaction.commit();
            return employeeTask;
        } catch (Exception e) {
            transaction.rollback();
        }

        return null;
    }
}