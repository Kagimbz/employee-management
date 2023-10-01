package com.emtech.employeemanagement.model.employee;

import com.emtech.employeemanagement.model.department.Department;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.SerializableString;
import lombok.*;
import lombok.extern.java.Log;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@ToString
@DynamicUpdate
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(name = "employee_id", columnNames = "id"),
        @UniqueConstraint(name = "employee_email", columnNames = "email")})
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", updatable = false, nullable = false, length = 50)
    private String name;

    @Column(name = "email", length = 30)
    private String email;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @Column
    private String departmentName;

    @CreationTimestamp
    @JsonFormat(pattern = "dd:MMM:yyyy HH:mm:ss")
    @Column(name = "creation_date")
    private Timestamp creationDate;

    @UpdateTimestamp
    @JsonFormat(pattern = "dd:MMM:yyyy HH:mm:ss")
    @Column(name = "update_date")
    private Timestamp updateDate;

    @Column(name = "status")
    private Integer status;
}
