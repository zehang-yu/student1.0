package com.java.roadstudent.roadjava.service;

import com.java.roadstudent.roadjava.entity.SelectClassDO;

import com.java.roadstudent.roadjava.req.StudentRequest;
import com.java.roadstudent.roadjava.res.TableDTO;

public interface QueryTimetableService {
    TableDTO retrieveQueryTimetable(StudentRequest request);


}
