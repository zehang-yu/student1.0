package com.java.roadstudent.roadjava.service.impl;

import com.java.roadstudent.roadjava.entity.TakesDO;
import com.java.roadstudent.roadjava.req.StudentRequest;
import com.java.roadstudent.roadjava.res.TableDTO;
import com.java.roadstudent.roadjava.service.QueryTimetableService;
import com.java.roadstudent.roadjava.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class QueryTimetableServiceImpl implements QueryTimetableService {
    @Override
    public TableDTO retrieveQueryTimetable(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select takes.course_id,teacher_name,building,room_number,time_slot_id,grade,year from takes,section,student where takes.no = student.no and takes.course_id = section.course_id ");
        if(request.getSearchKey()!=null && !"".equals(request.getSearchKey().trim())){
            sql.append(" where course_id like '%"+request.getSearchKey().trim()+"% or teacher_name like '%"+request.getSearchKey().trim()+"%' ");
        }
        sql.append("order by null asc limit ").append(request.getStart()).append(",").append(request.getPageSize());
        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs=null;
        TableDTO returnDTO = new TableDTO();

        try {
            conn=  DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            //查询记录
            returnDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from takes ");
            if(request.getSearchKey()!=null && !"".equals(request.getSearchKey().trim()))
            {
                sql.append("where course_id like '%"+request.getSearchKey().trim()+"%'");
            }
            ps=conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();

            while (rs.next()){
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }

        return null;
    }



    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()){
            //处理查出的每一条记录
            Vector<Object> oneRecord = new Vector<>();
            String course_id = rs.getString("course_id");
            String teacher_name = rs.getString("teacher_name");
            String building = rs.getString("building");
            String room_number = rs.getString("room_number");
            String time_slot_id = rs.getString("time_slot_id");
            String grade = rs.getString("grade");
            String year = rs.getString("year");

            oneRecord.addElement(course_id);
            oneRecord.addElement(teacher_name);
            oneRecord.addElement(building);
            oneRecord.addElement(room_number);
            oneRecord.addElement(time_slot_id);
            oneRecord.addElement(grade);
            oneRecord.addElement(year);
            data.addElement(oneRecord);

        }
        return data;
    }
}
