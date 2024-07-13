package org.example;

import org.example.Entity.Employee;
import org.example.Entity.EmployeeTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.management.Query;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(EmployeeTask.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from Employee").executeUpdate();
            session.createQuery("delete from EmployeeTask ").executeUpdate();
            transaction.commit();


            transaction.begin();
            Employee employee = new Employee();
            employee.setName("stanley");
            employee.setSalary(5000);
            session.persist(employee);
            transaction.commit();

            System.out.printf("this is employee id %s", employee.getId());

            transaction.begin();
            EmployeeTask employeeTask = new EmployeeTask();
            employeeTask.setName("anotherTask");
            employeeTask.setEmployee(employee);
            employeeTask.setDeadLine(Date.valueOf(LocalDate.now()));
            session.persist(employeeTask);
            transaction.commit();


            // #1 list all task of employee
            System.out.println("list all tasks of person");
            List<EmployeeTask> employeeTasks = session.createQuery("from EmployeeTask where employee.name = 'stanley'")
                    .list();

            employeeTasks.forEach(employeeTask1 -> System.out.println(employeeTask1.getName()));

            // #2 list all late tasks
            System.out.println("employeeWithTasks");
            List<Employee> employeeWithTasks = session.createQuery("select distinct employee from EmployeeTask where current_date() >= deadLine").list();
            employeeWithTasks.forEach(employee1 -> System.out.println(employee1.getName()));


            // #3 update
            transaction.begin();
            System.out.println("update employee with tasks");
            var query = session.createQuery("update EmployeeTask set deadLine = :deadLine where name = :name");
            var date = LocalDate.now().plusDays(2);
            System.out.println("this is date " + date);
            query.setParameter("deadLine", Date.valueOf(date));
            query.setParameter("name", "anotherTask");
            query.executeUpdate();
            transaction.commit();


            transaction.begin();
            session.refresh(employeeTasks.get(0));
            List<EmployeeTask> allTasks = session.createQuery("from EmployeeTask").list();
            System.out.println("this is the new deadLines");
            allTasks.forEach(employeeTask1 -> System.out.println(employeeTask1.getDeadLine()));
            transaction.commit();

        }
    }
}