package com.java.roadstudent.roadjava.Handler;


import com.java.roadstudent.roadjava.student1.QueryTimetableView;
import com.java.roadstudent.roadjava.student1.SelectClassView;
import com.java.roadstudent.roadjava.student1.StudentMainView;
import com.java.roadstudent.roadjava.student1.view.MyGradesView;
import com.java.roadstudent.roadjava.student1.view.MyInfoView;


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
            int no = studentMainView.id;
            new MyInfoView(studentMainView,no);
        }
        else if ("我的成绩".equals(text))
        {
            int no = studentMainView.id;
            new MyGradesView(studentMainView,no);
        }
        else if("已选查询".equals(text))
        {

            new QueryTimetableView();
        }
        else if ("选课系统".equals(text))
        {
            new SelectClassView();
        }
    }


}
