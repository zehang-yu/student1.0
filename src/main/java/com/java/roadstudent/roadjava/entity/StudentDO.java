package com.java.roadstudent.roadjava.entity;

public class StudentDO {
    private Integer id;
    private String Name;
    private String no;
    private String department;
    private double cnScore;
    private double enScore;
    private double mathScore;
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getCnScore() {
        return cnScore;
    }

    public void setCnScore(double cnScore) {
        this.cnScore = cnScore;
    }

    public double getEnScore() {
        return enScore;
    }

    public void setEnScore(double enScore) {
        this.enScore = enScore;
    }

    public double getMathScore() {
        return mathScore;
    }

    public void setMathScore(double mathScore) {
        this.mathScore = mathScore;
    }


}
