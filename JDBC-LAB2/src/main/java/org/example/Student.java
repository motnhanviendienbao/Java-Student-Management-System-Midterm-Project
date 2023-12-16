package org.example;

public class Student {
    private String name;
    private String id;
    private String grade;
    private String dob;
    private String gender;
    private String contact;
    private String email;
    private String faculty;
    private String major;
    private String classSql;

    public Student() {
    }

    public Student(String name, String id, String grade, String dob, String gender, String contact, String email, String faculty, String major, String classSql) {
        this.name = name;
        this.id = id;
        this.grade = grade;
        this.dob = dob;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
        this.faculty = faculty;
        this.major = major;
        this.classSql = classSql;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassSql() {
        return classSql;
    }

    public void setClassSql(String classSql) {
        this.classSql = classSql;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", grade='" + grade + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", faculty='" + faculty + '\'' +
                ", major='" + major + '\'' +
                ", classSql='" + classSql + '\'' +
                '}';
    }
}

