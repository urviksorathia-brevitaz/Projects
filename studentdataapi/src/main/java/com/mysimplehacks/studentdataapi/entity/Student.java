package com.mysimplehacks.studentdataapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    private long rollNo;
    private String firstName;
    private float percentage;

    public Student() {
    }

    public Student(long rollNo, String firstName, float percentage) {
        this.rollNo = rollNo;
        this.firstName = firstName;
        this.percentage = percentage;
    }

    public long getRollNo() {
        return rollNo;
    }

    public void setRollNo(long rollNo) {
        this.rollNo = rollNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", firstName='" + firstName + '\'' +
                ", percentage=" + percentage +
                '}';
    }

}
