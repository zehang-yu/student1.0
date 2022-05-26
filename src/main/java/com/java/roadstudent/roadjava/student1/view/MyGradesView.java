package com.java.roadstudent.roadjava.student1.view;

import com.java.roadstudent.roadjava.Handler.MyGradesViewHandler;
import com.java.roadstudent.roadjava.entity.StudentDO;
import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.service.impl.StudentServiceImpl;
import com.java.roadstudent.roadjava.student1.StudentMainView;

import javax.swing.*;
import java.awt.*;

public class MyGradesView extends JDialog {
    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel cnLabel = new JLabel("语文成绩：",JLabel.RIGHT);
    JTextField cnTxt = new JTextField();
    JLabel enLabel = new JLabel("英语成绩：",JLabel.RIGHT);
    JTextField enTxt = new JTextField();
    JLabel mathLabel = new JLabel("数学成绩：",JLabel.RIGHT);
    JTextField mathTxt = new JTextField();


    MyGradesViewHandler myGradesViewHandler;

    public MyGradesView(StudentMainView studentmainView, int selectStudentId){
        super(studentmainView,"学生成绩界面",true);

        myGradesViewHandler =new MyGradesViewHandler(this,studentmainView);
        //查询selectStudentId对应的记录并回显
        StudentService studentService = new StudentServiceImpl();
        StudentDO selectedStu = studentService.getByNo(selectStudentId);

        cnLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(cnLabel);
        cnTxt.setPreferredSize(new Dimension(200,30));
        cnTxt.setText(String.valueOf(selectedStu.getCnScore()));
        jPanel.add(cnTxt);

        enLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(enLabel);
        enTxt.setPreferredSize(new Dimension(200,30));
        enTxt.setText(String.valueOf(selectedStu.getEnScore()));
        jPanel.add(enTxt);

        mathLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(mathLabel);
        mathTxt.setPreferredSize(new Dimension(200,30));
        mathTxt.setText(String.valueOf(selectedStu.getMathScore()));
        jPanel.add(mathTxt);




        Container contentPane = getContentPane();
        contentPane.add(jPanel);

        setSize(350,300);//单位是像素
        //居中显示
        setLocationRelativeTo(null);
        //DISPOSE_ON_CLOSE:只销毁当前窗体对话框
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //获取修改后的学生对象
    public StudentDO buildUpdatedStudentDO() {

        StudentDO studentDO = new StudentDO();
        studentDO.setCnScore(Double.valueOf(cnTxt.getText()));
        studentDO.setEnScore(Double.valueOf(enTxt.getText()));
        studentDO.setMathScore(Double.valueOf(mathTxt.getText()));


        return studentDO;

    }

}
