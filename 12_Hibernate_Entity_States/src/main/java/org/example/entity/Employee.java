package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(schema = "company", name = "employee")
@Getter
@Setter
@org.hibernate.annotations.NamedQuery(name = "findByName",
        query = "from Employee where name = :name")
@org.hibernate.annotations.NamedQuery(name = "sumOfAllSalaries",
        query = "select sum(salary) from Employee")
@NamedNativeQuery(name = "selectAll", query = "select * from company.employee", resultClass = Employee.class)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private int salary;

    @Column(name = "joinDate")
    private Date joinDate;
}
