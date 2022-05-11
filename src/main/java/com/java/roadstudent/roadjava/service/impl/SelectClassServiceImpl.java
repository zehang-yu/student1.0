package com.java.roadstudent.roadjava.service.impl;

import com.java.roadstudent.roadjava.entity.SelectClassDO;
import com.java.roadstudent.roadjava.req.StudentRequest;
import com.java.roadstudent.roadjava.res.TableDTO;
import com.java.roadstudent.roadjava.service.SelectClassService;
import com.java.roadstudent.roadjava.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SelectClassServiceImpl implements SelectClassService {


    @Override
    public TableDTO retrieveSelectClass(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from section ");
        if(request.getSearchKey()!=null && !"".equals(request.getSearchKey().trim())){
            sql.append(" where course_id like '%"+request.getSearchKey().trim()+"%' ");
        }
        sql.append("order by course_id asc limit ").append(request.getStart()).append(",").append(request.getPageSize());
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
            sql.append("select count(*) from section ");
            if(request.getSearchKey()!=null && !"".equals(request.getSearchKey().trim())){
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



    @Override
    public SelectClassDO getById(int selectSelectClassId) {

        StringBuilder sql = new StringBuilder("select * from section where id = ?");
        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs=null;
        SelectClassDO selectClassDO = new SelectClassDO();

        try {
            conn=  DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,selectSelectClassId);
            rs = ps.executeQuery();
            while (rs.next()){
                //处理查出的每一条记录
                Vector<Object> oneRecord = new Vector<>();
                int id = rs.getInt("id");
                String course_id = rs.getString("course_id");
                String teacher_name = rs.getString("teacher_name");
                int max_number = rs.getInt("max_number");
                int picked_number = rs.getInt("picked_number");
                String dept_name = rs.getString("dept_name");
                Boolean pick_or_not = rs.getBoolean("pick_or_not");
                selectClassDO.setId(id);
                selectClassDO.setCourse_id(course_id);
                selectClassDO.setTeacher_name(teacher_name);
                selectClassDO.setMax_number(max_number);
                selectClassDO.setPicked_number(picked_number);
                selectClassDO.setDept_name(dept_name);
                selectClassDO.setPick_or_not(pick_or_not);



            }

            return selectClassDO;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }

        return null;
    }

    @Override
    public boolean update(SelectClassDO selectClassDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update section set pick_or_not=?");
        sql.append("where id=? ");

        Connection conn=null;
        PreparedStatement ps =null;
        //ResultSet rs=null;


        try {
            conn=  DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setBoolean(1,selectClassDO.getPick_or_not());
            ps.setInt(8,selectClassDO.getId());

            return ps.executeUpdate()==1;


        }catch (Exception e){
            e.printStackTrace();
        }finally {

            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }



        return false;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()){
            //处理查出的每一条记录
            Vector<Object> oneRecord = new Vector<>();
            int id = rs.getInt("id");
            String course_id = rs.getString("course_id");
            String teacher_name = rs.getString("teacher_name");
            int max_number = rs.getInt("max_number");
            int picked_number = rs.getInt("picked_number");
            String dept_name = rs.getString("dept_name");
            Boolean pick_or_not = rs.getBoolean("pick_or_not");
            oneRecord.addElement(id);
            oneRecord.addElement(course_id);
            oneRecord.addElement(teacher_name);
            oneRecord.addElement(max_number);
            oneRecord.addElement(picked_number);
            oneRecord.addElement(dept_name);
            oneRecord.addElement(pick_or_not);
            data.addElement(oneRecord);

        }
        return data;
    }
}
