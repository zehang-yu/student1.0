package com.java.roadstudent.roadjava.Handler;

import com.java.roadstudent.roadjava.entity.SelectClassDO;
import com.java.roadstudent.roadjava.service.SelectClassService;
import com.java.roadstudent.roadjava.service.impl.SelectClassServiceImpl;
import com.java.roadstudent.roadjava.student1.MainView;
import com.java.roadstudent.roadjava.student1.view.UpdateSelectClassView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSelectClassViewHandler implements ActionListener {

    private UpdateSelectClassView updateSelectClassView;
    private MainView mainView;
    public UpdateSelectClassViewHandler(UpdateSelectClassView updateSelectClassView, MainView mainView){
        this.updateSelectClassView = updateSelectClassView;
        this.mainView=mainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("修改".equals(text))
        {
            SelectClassService selectClassService = new SelectClassServiceImpl();
            SelectClassDO selectClassDO = updateSelectClassView.buildUpdatedSelectClassDO();
            boolean updateResult = selectClassService.update(selectClassDO);
            if(updateResult){
                //重新加载表格查到最新数据
                mainView.reLoadTable();
                updateSelectClassView.dispose();
            }else {
                JOptionPane.showMessageDialog(updateSelectClassView,"修改失败");
            }
        }

    }


}
