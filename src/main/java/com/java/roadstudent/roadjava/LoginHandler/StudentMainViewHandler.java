package com.java.roadstudent.roadjava.LoginHandler;


import com.java.roadstudent.roadjava.student1.SelectClassMainView;
import com.java.roadstudent.roadjava.student1.StudentMainView;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMainViewHandler implements ActionListener {

    private StudentMainView studentMainView;
    public StudentMainViewHandler(StudentMainView studentMainView){
        this.studentMainView = studentMainView;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("我的信息".equals(text))
        {

        }
        else if ("我的成绩".equals(text))
        {
            //login();
        }
        else if("课表查询".equals(text)){

            //stulogin();
            //new StudentRegisterView(loginView);
        }
        else if ("选课系统".equals(text))
        {
            new SelectClassMainView();
        }
    }


}
