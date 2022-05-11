package com.java.roadstudent.roadjava.student1.exL;

import com.java.roadstudent.roadjava.LoginHandler.UpdateSelectClassViewHandler;
import com.java.roadstudent.roadjava.entity.SelectClassDO;
import com.java.roadstudent.roadjava.service.SelectClassService;
import com.java.roadstudent.roadjava.service.impl.SelectClassServiceImpl;
import com.java.roadstudent.roadjava.student1.MainView;

import javax.swing.*;
import java.awt.*;

public class UpdateSelectClassView extends JDialog {
    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel idLabel = new JLabel("课程编号：");
    JTextField idTxt =new JTextField();
    JLabel pick_or_notLabel = new JLabel("是否选课：",JLabel.RIGHT);
    JTextField pick_or_notTxt = new JTextField();

    JButton updateBtn=new JButton("修改");

    UpdateSelectClassViewHandler updateSelectClassViewHandler;

    public UpdateSelectClassView(MainView mainView, int selectClassId){
        super(mainView,"修改选课状态",true);

        updateSelectClassViewHandler =new UpdateSelectClassViewHandler(this,mainView);
        //查询selectStudentId对应的记录并回显
        SelectClassService selectClassService = new SelectClassServiceImpl();
        SelectClassDO selectedSelectClass = selectClassService.getById(selectClassId);

        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        idTxt.setText(selectedSelectClass.getId()+"");
        //设置ID不可编辑
        idTxt.setEnabled(false);
        jPanel.add(idTxt);


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
        selectClassDO.setPick_or_not(Boolean.valueOf(pick_or_notTxt.getText()));

        return selectClassDO;

    }
}
