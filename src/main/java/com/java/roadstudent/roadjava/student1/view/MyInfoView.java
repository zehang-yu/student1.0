package com.java.roadstudent.roadjava.student1.view;

import com.java.roadstudent.roadjava.Handler.MyInfoViewHandler;
import com.java.roadstudent.roadjava.entity.StudentDO;
import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.service.impl.StudentServiceImpl;
import com.java.roadstudent.roadjava.student1.StudentMainView;

import javax.swing.*;
import java.awt.*;

public class MyInfoView extends JDialog {
    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
//    JLabel idLabel = new JLabel("学生编号：");
//    JTextField idTxt =new JTextField();
    JLabel nameLabel = new JLabel("姓名：",JLabel.RIGHT);
    JTextField nameTxt = new JTextField();
    JLabel noLabel = new JLabel("学号：",JLabel.RIGHT);
    JTextField noTxt = new JTextField();
    JLabel deptLabel = new JLabel("学院：",JLabel.RIGHT);
    JTextField deptTxt = new JTextField();


    MyInfoViewHandler MyInfoViewHandler;

    public MyInfoView(StudentMainView studentmainView, int selectStudentId){
        super(studentmainView,"学生信息界面",true);

        MyInfoViewHandler =new MyInfoViewHandler(this,studentmainView);
        //查询selectStudentId对应的记录并回显
        StudentService studentService = new StudentServiceImpl();
        StudentDO selectedStu = studentService.getByNo(selectStudentId);

        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        nameTxt.setText(selectedStu.getName());
        jPanel.add(nameTxt);
        nameTxt.setHorizontalAlignment(JTextField.CENTER);
        nameTxt.setEditable(false);

        noLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(noLabel);
        noTxt.setPreferredSize(new Dimension(200,30));
        noTxt.setText(selectedStu.getNo());
        jPanel.add(noTxt);
        noTxt.setHorizontalAlignment(JTextField.CENTER);
        noTxt.setEditable(false);

        deptLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(deptLabel);
        deptTxt.setPreferredSize(new Dimension(200,30));
        deptTxt.setText(selectedStu.getDepartment());
        jPanel.add(deptTxt);
        deptTxt.setHorizontalAlignment(JTextField.CENTER);
        deptTxt.setEditable(false);




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
//        studentDO.setId(Integer.valueOf(idTxt.getText()));
        studentDO.setName(nameTxt.getText());
        studentDO.setNo(noTxt.getText());
        studentDO.setDepartment(deptTxt.getText());


        return studentDO;

    }

}
