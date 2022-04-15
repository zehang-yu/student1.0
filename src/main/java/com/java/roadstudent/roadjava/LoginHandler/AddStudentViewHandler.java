package com.java.roadstudent.roadjava.LoginHandler;

import com.java.roadstudent.roadjava.entity.StudentDO;
import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.service.impl.StudentServiceImpl;
import com.java.roadstudent.roadjava.student1.LoginView;
import com.java.roadstudent.roadjava.student1.MainView;
import com.java.roadstudent.roadjava.student1.exL.AddStudentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentViewHandler implements ActionListener {

    private AddStudentView addStudentView;
    private MainView mainView;
    //private LoginView loginView;
    public AddStudentViewHandler(AddStudentView addStudentView, MainView mainView){
        this.addStudentView = addStudentView;
        this.mainView=mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("添加".equals(text))
        {
            StudentService studentService = new StudentServiceImpl();
            StudentDO studentDO = addStudentView.buildStudentDO();
            boolean addResult = studentService.add(studentDO);
            if(addResult==true){
                //重新加载表格查到最新数据
                mainView.reLoadTable();
                addStudentView.dispose();
            }else {
                JOptionPane.showMessageDialog(addStudentView,"添加失败");
            }
        }

    }


}
