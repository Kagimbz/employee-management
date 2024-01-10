package com.emtech.employeemanagement.model.department;

import com.emtech.employeemanagement.model.employee.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@Entity
@Table(name = "department", uniqueConstraints = {
        @UniqueConstraint(name = "department_id", columnNames = "id"),
        @UniqueConstraint(name = "department_name", columnNames = "departmentName"),
        @UniqueConstraint(name = "department_code", columnNames = "code")
})
@DynamicUpdate
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "departmentName", nullable = false, length = 20)
    private String departmentName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Employee> employees = new ArrayList<>();

    @Column(name = "code", nullable = false)
    private Integer code;

    @JsonFormat(pattern = "dd:MMM:yyyy HH:mm:ss")
    @Column(name = "creationDate")
    @CreationTimestamp
    private Timestamp creationDate;

    @JsonFormat(pattern = "dd:MMM:yyyy HH:mm:ss")
    @Column(name = "updateDate")
    @UpdateTimestamp
    private Timestamp updateDate;
}
