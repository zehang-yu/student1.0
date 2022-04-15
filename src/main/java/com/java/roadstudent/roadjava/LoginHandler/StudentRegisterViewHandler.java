package com.java.roadstudent.roadjava.LoginHandler;

import com.java.roadstudent.roadjava.entity.StudentDO;
import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.service.impl.StudentServiceImpl;
import com.java.roadstudent.roadjava.student1.LoginView;
import com.java.roadstudent.roadjava.student1.MainView;
import com.java.roadstudent.roadjava.student1.exL.AddStudentView;
import com.java.roadstudent.roadjava.student1.exL.StudentRegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegisterViewHandler implements ActionListener {

    private StudentRegisterView studentRegisterView;
    //private MainView mainView;
    private LoginView loginView;
    public StudentRegisterViewHandler(StudentRegisterView studentRegisterView, LoginView loginView){
        this.studentRegisterView = studentRegisterView;
        this.loginView=loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("注册".equals(text))
        {
            StudentService studentService = new StudentServiceImpl();
            StudentDO studentDO = studentRegisterView.buildStudentDO();
            boolean addResult = studentService.add(studentDO);
            if(addResult==true){
                //重新加载表格查到最新数据
                ///mainView.reLoadTable();
                JOptionPane.showMessageDialog(studentRegisterView,"添加成功");
            }else {
                JOptionPane.showMessageDialog(studentRegisterView,"添加失败");
            }
        }

    }


}
