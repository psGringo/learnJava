package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(catalog = "company", name = "employeeTask")
@Getter
@Setter
public class EmployeeTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = true)
    private Employee employee;

    @Column(name = "deadLine")
    private Date deadLine;
}
