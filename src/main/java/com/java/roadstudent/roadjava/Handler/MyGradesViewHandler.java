package com.java.roadstudent.roadjava.Handler;

import com.java.roadstudent.roadjava.student1.StudentMainView;
import com.java.roadstudent.roadjava.student1.view.MyGradesView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGradesViewHandler implements ActionListener {

    private MyGradesView myGradesView;
    private StudentMainView studentmainView;
    public MyGradesViewHandler(MyGradesView myGradesView, StudentMainView studentmainView){

        this.myGradesView = myGradesView;
        this.studentmainView=studentmainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();


    }


}
