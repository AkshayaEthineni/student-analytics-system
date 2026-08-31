package com.student.studentanalytics;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String department;
    private String email;
    private String course;
    private int age;
    private double cgpa;
    private double attendance;
    private int aptitudeScore;
    private int certifications;
    private boolean placed;
    private String placedCompany;
    private double packageLpa;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public double getAttendance() { return attendance; }
    public void setAttendance(double attendance) { this.attendance = attendance; }

    public int getAptitudeScore() { return aptitudeScore; }
    public void setAptitudeScore(int aptitudeScore) { this.aptitudeScore = aptitudeScore; }

    public int getCertifications() { return certifications; }
    public void setCertifications(int certifications) { this.certifications = certifications; }

    public boolean isPlaced() { return placed; }
    public void setPlaced(boolean placed) { this.placed = placed; }

    public String getPlacedCompany() { return placedCompany; }
    public void setPlacedCompany(String placedCompany) { this.placedCompany = placedCompany; }

    public double getPackageLpa() { return packageLpa; }
    public void setPackageLpa(double packageLpa) { this.packageLpa = packageLpa; }
}