package com.java.roadstudent.roadjava.LoginHandler;

import com.java.roadstudent.roadjava.entity.AdminDO;
import com.java.roadstudent.roadjava.entity.StudentDO;
import com.java.roadstudent.roadjava.service.AdminService;
import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.service.impl.AdminServiceImpl;
import com.java.roadstudent.roadjava.service.impl.StudentServiceImpl;
import com.java.roadstudent.roadjava.student1.LoginView;
import com.java.roadstudent.roadjava.student1.MainView;
import com.java.roadstudent.roadjava.student1.StudentMainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginHandler extends KeyAdapter implements ActionListener {

    private LoginView loginView;
    public LoginHandler(LoginView loginView){
        this.loginView = loginView;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("重置".equals(text))
        {
            loginView.getUserText().setText("");
            loginView.getPwdField().setText("");
        }
        else if ("管理员登录".equals(text))
        {
            login();
        }
        else if("学生登录".equals(text)){

            stulogin();
            //new StudentRegisterView(loginView);
        }
    }

    private void login() {
        String user = loginView.getUserText().getText();
        char[] chars = loginView.getPwdField().getPassword();
        if(user==null||"".equals(user.trim())||chars==null){
            JOptionPane.showMessageDialog(loginView,"用户名密码必填");
            return;
        }
        String pwd = new String(chars);
        System.out.println(user+":"+pwd);
        AdminService adminService = new AdminServiceImpl();
        AdminDO adminDO = new AdminDO();
        adminDO.setUserName(user);
        adminDO.setPwd(pwd);
        //查询db
        boolean flag = adminService.validateAdmin(adminDO);
        if(flag)
        {
            //跳转到主界面并销毁登录界面
            new MainView();
            loginView.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(loginView,"用户名密码错误");
        }
    }
    private void stulogin() {
        String user = loginView.getUserText().getText();
        char[] chars = loginView.getPwdField().getPassword();
        if(user==null||"".equals(user.trim())||chars==null){
            JOptionPane.showMessageDialog(loginView,"用户名密码必填");
            return;
        }
        String pwd = new String(chars);
        System.out.println(user+":"+pwd);
        StudentService studentLoginService = new StudentServiceImpl();
        StudentDO Student = new StudentDO();
        Student.setName(user);
        Student.setPwd(pwd);
        //查询db
        boolean flag = studentLoginService.validateStudentLogin(Student);
        if(flag)
        {
            //跳转到主界面并销毁登录界面
            new StudentMainView();
            loginView.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(loginView,"用户名密码错误");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if( KeyEvent.VK_ENTER==  e.getKeyCode())
        {
            login();
        }
    }
}
