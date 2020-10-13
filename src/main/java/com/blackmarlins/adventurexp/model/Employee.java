package com.blackmarlins.adventurexp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "cpr")
    private String cpr;

    @Column(name = "salary")
    private double salary;

    @Column(name = "position")
    private String position;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Employee(String firstName, String lastName, String cpr, double salary, String position, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpr = cpr;
        this.salary = salary;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }

    public Employee () {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cpr='" + cpr + '\'' +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
