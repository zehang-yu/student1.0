package com.java.roadstudent.roadjava.student1.view;

import com.java.roadstudent.roadjava.Handler.UpdateSelectClassViewHandler;
import com.java.roadstudent.roadjava.entity.SelectClassDO;
import com.java.roadstudent.roadjava.service.SelectClassService;
import com.java.roadstudent.roadjava.service.impl.SelectClassServiceImpl;
import com.java.roadstudent.roadjava.student1.SelectClassView;

import javax.swing.*;
import java.awt.*;

public class UpdateSelectClassView extends JDialog {
    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel idLabel = new JLabel("编号：",JLabel.RIGHT);
    JTextField idTxt =new JTextField();
    JLabel course_idLabel = new JLabel("课程名称：",JLabel.RIGHT);
    JTextField course_idTxt =new JTextField();
    JLabel teacher_nameLabel = new JLabel("授课教师：",JLabel.RIGHT);
    JTextField teacher_nameTxt =new JTextField();
    JLabel max_numberLabel = new JLabel("可选人数：",JLabel.RIGHT);
    JTextField max_numberTxt =new JTextField();
    JLabel picked_numberLabel = new JLabel("已选人数：",JLabel.RIGHT);
    JTextField picked_numberTxt =new JTextField();
    JLabel dept_nameLabel = new JLabel("所属院系：",JLabel.RIGHT);
    JTextField dept_nameTxt =new JTextField();
    JLabel pick_or_notLabel = new JLabel("是否选课：",JLabel.RIGHT);
    JTextField pick_or_notTxt = new JTextField();

    JButton updateBtn=new JButton("修改");

    UpdateSelectClassViewHandler updateSelectClassViewHandler;

    public UpdateSelectClassView(SelectClassView selectClassMainView, int selectClassId){
        super(selectClassMainView,"修改选课状态",true);

        updateSelectClassViewHandler =new UpdateSelectClassViewHandler(this,selectClassMainView);
        //查询selectStudentId对应的记录并回显
        SelectClassService selectClassService = new SelectClassServiceImpl();
        SelectClassDO selectedSelectClass = selectClassService.getById(selectClassId);

        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        idTxt.setText(selectedSelectClass.getId()+"");
        //设置不可编辑
        idTxt.setEditable(false);
        jPanel.add(idTxt);

        course_idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(course_idLabel);
        course_idTxt.setPreferredSize(new Dimension(200,30));
        course_idTxt.setText(selectedSelectClass.getCourse_id()+"");
        course_idTxt.setEditable(false);
        jPanel.add(course_idTxt);

        teacher_nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(teacher_nameLabel);
        teacher_nameTxt.setPreferredSize(new Dimension(200,30));
        teacher_nameTxt.setText(selectedSelectClass.getTeacher_name()+"");
        teacher_nameTxt.setEditable(false);
        jPanel.add(teacher_nameTxt);

        max_numberLabel.setPreferredSize(new Dimension(100,30));
        jPanel.add(max_numberLabel);
        max_numberTxt.setPreferredSize(new Dimension(200,30));
        max_numberTxt.setText(selectedSelectClass.getMax_number()+"");
        max_numberTxt.setEditable(false);
        jPanel.add(max_numberTxt);

        picked_numberLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(picked_numberLabel);
        picked_numberTxt.setPreferredSize(new Dimension(200,30));
        picked_numberTxt.setText(selectedSelectClass.getPicked_number()+"");
        picked_numberTxt.setEditable(false);
        jPanel.add(picked_numberTxt);

        dept_nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(dept_nameLabel);
        dept_nameTxt.setPreferredSize(new Dimension(200,30));
        dept_nameTxt.setText(selectedSelectClass.getDept_name()+"");
        dept_nameTxt.setEditable(false);
        jPanel.add(dept_nameTxt);

        pick_or_notLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(pick_or_notLabel);
        pick_or_notTxt.setPreferredSize(new Dimension(200,30));
        pick_or_notTxt.setText(String.valueOf(selectedSelectClass.getPick_or_not()));
        jPanel.add(pick_or_notTxt);

        updateBtn.addActionListener(updateSelectClassViewHandler);
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
    public SelectClassDO buildUpdatedSelectClassDO() {

        SelectClassDO selectClassDO = new SelectClassDO();
        selectClassDO.setId(Integer.valueOf(idTxt.getText()));
        selectClassDO.setCourse_id(course_idTxt.getText());
        selectClassDO.setTeacher_name(teacher_nameTxt.getText());
        selectClassDO.setMax_number(Integer.valueOf(max_numberTxt.getText()));
        selectClassDO.setPicked_number(Integer.valueOf(picked_numberTxt.getText()));
        selectClassDO.setDept_name(dept_nameTxt.getText());
        selectClassDO.setPick_or_not(pick_or_notTxt.getText());

        return selectClassDO;

    }
}
