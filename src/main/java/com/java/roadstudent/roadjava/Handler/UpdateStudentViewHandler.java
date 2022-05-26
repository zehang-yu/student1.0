package com.java.roadstudent.roadjava.Handler;

import com.java.roadstudent.roadjava.entity.StudentDO;
import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.service.impl.StudentServiceImpl;
import com.java.roadstudent.roadjava.student1.MainView;
import com.java.roadstudent.roadjava.student1.view.UpdateStudentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateStudentViewHandler implements ActionListener {

    private UpdateStudentView updateStudentView;
    private MainView mainView;
    public UpdateStudentViewHandler(UpdateStudentView updateStudentView, MainView mainView){
        this.updateStudentView = updateStudentView;
        this.mainView=mainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("修改".equals(text))
        {
            StudentService studentService = new StudentServiceImpl();
            StudentDO studentDO = updateStudentView.buildUpdatedStudentDO();
            boolean updateResult = studentService.update(studentDO);
            if(updateResult==true){
                //重新加载表格查到最新数据
                mainView.reLoadTable();
                updateStudentView.dispose();
            }else {
                JOptionPane.showMessageDialog(updateStudentView,"修改失败");
            }
        }

    }


}
