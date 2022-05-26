package com.java.roadstudent.roadjava.Handler;

import com.java.roadstudent.roadjava.student1.StudentMainView;
import com.java.roadstudent.roadjava.student1.view.MyInfoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyInfoViewHandler implements ActionListener {

    private MyInfoView MyInfoView;
    private StudentMainView studentmainView;
    public MyInfoViewHandler(MyInfoView MyInfoView, StudentMainView studentmainView){

        this.MyInfoView = MyInfoView;
        this.studentmainView=studentmainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();


    }


}
