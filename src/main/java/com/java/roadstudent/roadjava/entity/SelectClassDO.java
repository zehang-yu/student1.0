package com.java.roadstudent.roadjava.entity;

public class SelectClassDO {
    private Integer id;
    private String course_id;
    private String teacher_name;
    private int max_number;
    private int picked_number;
    private String dept_name;
    private String pick_or_not;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public int getMax_number() {
        return max_number;
    }

    public void setMax_number(int max_number) {
        this.max_number = max_number;
    }

    public int getPicked_number() {
        return picked_number;
    }

    public void setPicked_number(int picked_number) {
        this.picked_number = picked_number;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getPick_or_not() {
        return pick_or_not;
    }

    public void setPick_or_not(String pick_or_not) {
        this.pick_or_not = pick_or_not;
    }


}
