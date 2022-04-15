package com.java.roadstudent.roadjava.service.impl;

import com.java.roadstudent.roadjava.entity.AdminDO;
import com.java.roadstudent.roadjava.service.AdminService;
import com.java.roadstudent.roadjava.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminServiceImpl implements AdminService {

    @Override
    public boolean validateAdmin(AdminDO adminDO) {
        String userName = adminDO.getUserName();
        String pwdParam = adminDO.getPwd();

        String sql="select pwd from manager where user_name=?";
        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs =null;

        try{
            conn = DBUtil.getConn();
            if(conn==null){
                return  false;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1,adminDO.getUserName());
             rs = ps.executeQuery();
            while (rs.next()){
                String pwd = rs.getString(1);
                if(adminDO.getPwd().equals(pwd)){
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
}
