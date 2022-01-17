package com.epam.training.epharmacy.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {
    private Integer id;
    private String fullName;
    private String login;
    private String password;
    private UserRole userRole;
    private List<Order> orders;
    private List<Prescription> prescriptions;
    private String email;
    private String telNumber;
    private String workPlace;
    private String specialization;
    private Integer age;



    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(fullName, user.fullName) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && userRole == user.userRole && Objects.equals(email, user.email) && Objects.equals(telNumber, user.telNumber) && Objects.equals(workPlace, user.workPlace) && Objects.equals(specialization, user.specialization) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, login, password, userRole, email, telNumber, workPlace, specialization, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", email='" + email + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", workPlace='" + workPlace + '\'' +
                ", specialization='" + specialization + '\'' +
                ", age=" + age +
                '}';
    }
}
