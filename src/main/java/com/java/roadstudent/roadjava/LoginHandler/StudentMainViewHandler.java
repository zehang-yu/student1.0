package com.java.roadstudent.roadjava.LoginHandler;


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

    }


}
