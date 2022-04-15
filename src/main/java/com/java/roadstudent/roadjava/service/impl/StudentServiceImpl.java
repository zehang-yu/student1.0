package com.java.roadstudent.roadjava.service.impl;

import com.java.roadstudent.roadjava.entity.StudentDO;
import com.java.roadstudent.roadjava.req.StudentRequest;
import com.java.roadstudent.roadjava.res.TableDTO;
import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StudentServiceImpl implements StudentService {


    @Override
    public TableDTO retrieveStudent(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from student ");
        if(request.getSearchKey()!=null && !"".equals(request.getSearchKey().trim())){
            sql.append(" where name like '%"+request.getSearchKey().trim()+"%' ");
        }
        sql.append("order by id asc limit ").append(request.getStart()).append(",").append(request.getPageSize());
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
            sql.append("select count(*) from student ");
            if(request.getSearchKey()!=null && !"".equals(request.getSearchKey().trim())){
                sql.append("where name like '%"+request.getSearchKey().trim()+"%'");
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
    public boolean add(StudentDO studentDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into  student(name,no,home_town,cn_score,en_score,math_score,pwd) ");
        sql.append("values(?,?,?,?,?,?,?) ");

        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs=null;


        try {
            conn=  DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,studentDO.getName());
            ps.setString(2,studentDO.getNo());
            ps.setString(3,studentDO.getHomeTown());
            ps.setDouble(4,studentDO.getCnScore());
            ps.setDouble(5,studentDO.getEnScore());
            ps.setDouble(6,studentDO.getMathScore());
            ps.setString(7,studentDO.getPwd());
            return ps.executeUpdate()==1;


        }catch (Exception e){
            e.printStackTrace();
        }finally {

            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }

        return false;
    }

    @Override
    public StudentDO getById(int selectStudentId) {

        StringBuilder sql = new StringBuilder("select * from student where id = ?");
        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs=null;
        StudentDO studentDO = new StudentDO();

        try {
            conn=  DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,selectStudentId);
            rs = ps.executeQuery();
            while (rs.next()){
                //处理查出的每一条记录
                Vector<Object> oneRecord = new Vector<>();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String no = rs.getString("no");
                String homeTown = rs.getString("home_town");
                Double cnScore = rs.getDouble("cn_score");
                Double enScore = rs.getDouble("en_score");
                Double mathScore = rs.getDouble("math_score");
                String pwd = rs.getString("pwd");
                studentDO.setId(id);
                studentDO.setName(name);
                studentDO.setNo(no);
                studentDO.setHomeTown(homeTown);
                studentDO.setCnScore(cnScore);
                studentDO.setEnScore(enScore);
                studentDO.setMathScore(mathScore);
                studentDO.setPwd(pwd);



            }

            return studentDO;
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
    public boolean update(StudentDO studentDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update student set name=?,no=?,home_town=?,cn_score=?,en_score=?,math_score=? ,pwd=?");
        sql.append("where id=? ");

        Connection conn=null;
        PreparedStatement ps =null;
        //ResultSet rs=null;


        try {
            conn=  DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,studentDO.getName());
            ps.setString(2,studentDO.getNo());
            ps.setString(3,studentDO.getHomeTown());
            ps.setDouble(4,studentDO.getCnScore());
            ps.setDouble(5,studentDO.getEnScore());
            ps.setDouble(6,studentDO.getMathScore());
            ps.setString(7,studentDO.getPwd());
            ps.setInt(8,studentDO.getId());

            return ps.executeUpdate()==1;


        }catch (Exception e){
            e.printStackTrace();
        }finally {

            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }



        return false;
    }

    @Override
    public boolean delete(int[] selectStudentIds) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from student where id in(");
        int length = selectStudentIds.length;
        for(int i  =0;i<length;i++){
            if(i==(length-1)){
                sql.append("?");
            }else {
                sql.append("?,");
            }
        }
        sql.append(")");

        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs=null;


        try {
            conn=  DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            for(int i  =0;i<length;i++){
                //设置参数从1开始
                ps.setInt(i+1,selectStudentIds[i]);
            }
            return ps.executeUpdate()==length;


        }catch (Exception e){
            e.printStackTrace();
        }finally {

            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }



        return false;
    }

    @Override
    public boolean validateStudentLogin(StudentDO studentDO) {
        String userName = studentDO.getName();
        String pwdParam = studentDO.getPwd();

        String sql="select pwd from student where no=?";
        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs =null;

        try{
            conn = DBUtil.getConn();
            if(conn==null){
                return  false;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1,studentDO.getName());
            rs = ps.executeQuery();
            while (rs.next()){
                String pwd = rs.getString(1);
                if(studentDO.getPwd().equals(pwd)){
                    return  true;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.closeRs(rs);
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
            String name = rs.getString("name");
            String no = rs.getString("no");
            String homeTown = rs.getString("home_town");
            double cnScore = rs.getDouble("cn_score");
            double enScore = rs.getDouble("en_score");
            double mathScore = rs.getDouble("math_score");
            double totalScore = cnScore+enScore+mathScore;
            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(no);
            oneRecord.addElement(homeTown);
            oneRecord.addElement(cnScore);
            oneRecord.addElement(enScore);
            oneRecord.addElement(mathScore);
            oneRecord.addElement(totalScore);
            data.addElement(oneRecord);

        }
        return data;
    }
}
