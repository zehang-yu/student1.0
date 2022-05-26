package com.java.roadstudent.roadjava.student1.view;

import com.java.roadstudent.roadjava.Handler.UpdateStudentViewHandler;
import com.java.roadstudent.roadjava.entity.StudentDO;
import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.service.impl.StudentServiceImpl;
import com.java.roadstudent.roadjava.student1.MainView;

import javax.swing.*;
import java.awt.*;

public class UpdateStudentView extends JDialog {
    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel idLabel = new JLabel("学生编号：");
    JTextField idTxt =new JTextField();
    JLabel nameLabel = new JLabel("姓名：",JLabel.RIGHT);
    JTextField nameTxt = new JTextField();
    JLabel noLabel = new JLabel("学号：",JLabel.RIGHT);
    JTextField noTxt = new JTextField();
    JLabel homeLabel = new JLabel("家乡：",JLabel.RIGHT);
    JTextField homeTxt = new JTextField();
    JLabel cnLabel = new JLabel("语文成绩：",JLabel.RIGHT);
    JTextField cnTxt = new JTextField();
    JLabel enLabel = new JLabel("英语成绩：",JLabel.RIGHT);
    JTextField enTxt = new JTextField();
    JLabel mathLabel = new JLabel("数学成绩：",JLabel.RIGHT);
    JTextField mathTxt = new JTextField();
    JLabel pwdLabel = new JLabel("密码：",JLabel.RIGHT);
    JTextField pwdTxt = new JTextField();
    JButton updateBtn=new JButton("修改");

    UpdateStudentViewHandler updateStudentViewHandler;

    public UpdateStudentView(MainView mainView, int selectStudentId){
        super(mainView,"修改学生",true);

        updateStudentViewHandler =new UpdateStudentViewHandler(this,mainView);
        //查询selectStudentId对应的记录并回显
        StudentService studentService = new StudentServiceImpl();
        StudentDO selectedStu = studentService.getById(selectStudentId);

        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        idTxt.setText(selectedStu.getId()+"");
        //设置ID不可编辑
        idTxt.setEnabled(false);
        jPanel.add(idTxt);


        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        nameTxt.setText(selectedStu.getName());

        jPanel.add(nameTxt);

        noLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(noLabel);
        noTxt.setPreferredSize(new Dimension(200,30));
        noTxt.setText(selectedStu.getNo());
        jPanel.add(noTxt);

        homeLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(homeLabel);
        homeTxt.setPreferredSize(new Dimension(200,30));
        homeTxt.setText(selectedStu.getHomeTown());
        jPanel.add(homeTxt);

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

        pwdLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(pwdLabel);
        pwdTxt.setPreferredSize(new Dimension(200,30));
        pwdTxt.setText(String.valueOf(selectedStu.getMathScore()));
        jPanel.add(pwdTxt);

        updateBtn.addActionListener(updateStudentViewHandler);
        jPanel.add(updateBtn);


        Container contentPane = getContentPane();
        contentPane.add(jPanel);

        setSize(350,500);//单位是像素
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
        studentDO.setId(Integer.valueOf(idTxt.getText()));
        studentDO.setName(nameTxt.getText());
        studentDO.setNo(noTxt.getText());
        studentDO.setHomeTown(homeTxt.getText());
        studentDO.setCnScore(Double.valueOf(cnTxt.getText()));
        studentDO.setEnScore(Double.valueOf(enTxt.getText()));
        studentDO.setMathScore(Double.valueOf(mathTxt.getText()));
        studentDO.setPwd(pwdTxt.getText());

        return studentDO;

    }
}
