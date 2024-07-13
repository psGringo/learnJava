package org.example;


import entity.Employee;
import entity.EmployeeTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
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
            findByOneParamExample(session);
            findByListOfParamsExample(session);
        }
    }

    private static void findByListOfParamsExample(Session session) {
        var manyEmployees = getEmployeeByNames(session, List.of("Stanley", "Ayna"));
        System.out.println("found many employees:");
        manyEmployees.forEach(employee -> System.out.println(employee.getName()));
    }

    private static void findByOneParamExample(Session session) {
        Employee employeeStanley = getEmployeeByName(session, "Stanley");
        System.out.println("found employee %s".formatted(employeeStanley.getName()));

        Employee employeeAyna = getEmployeeByName(session, "Ayna");
        System.out.println("found employee %s".formatted(employeeAyna.getName()));
    }

    private static void populateTables(Session session, List<String> employeeNames) {
        employeeNames.stream().forEach(name -> {
            Employee employee = populateEmployee(session, name);
            populateEmployeeTask(session, employee, "anotherTask");
        });
    }

    private static List<Employee> getEmployeeByNames(Session session, List<String> names) {
        Transaction transaction = session.beginTransaction();
        try {
            Query<Employee> query = session.createQuery("from Employee where name in :names", Employee.class);
            query.setParameterList("names", names, String.class);
            transaction.commit();
            return query.list();
        } catch (Exception e) {
            transaction.rollback();
        }

        return null;
    }

    private static Employee getEmployeeByName(Session session, String name) {
        Transaction transaction = session.beginTransaction();
        try {
            Query<Employee> query = session.createQuery("from Employee where name = :name", Employee.class);
            query.setParameter("name", name);
            query.setMaxResults(1);
            transaction.commit();
            return query.list().stream().findFirst().get();
        } catch (Exception e) {
            transaction.rollback();
        }

        return null;
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