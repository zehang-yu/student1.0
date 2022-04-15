package com.java.roadstudent.roadjava.service;

import com.java.roadstudent.roadjava.entity.StudentDO;
import com.java.roadstudent.roadjava.req.StudentRequest;
import com.java.roadstudent.roadjava.res.TableDTO;

public interface StudentService {
    TableDTO retrieveStudent(StudentRequest request);

    boolean add(StudentDO studentDO);

    StudentDO getById(int selectStudentId);

    boolean update(StudentDO studentDO);

    boolean delete(int[] selectStudentIds);

    boolean validateStudentLogin(StudentDO studentDO);





}
