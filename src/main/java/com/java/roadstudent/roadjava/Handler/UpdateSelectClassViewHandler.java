package com.java.roadstudent.roadjava.Handler;

import com.java.roadstudent.roadjava.entity.SelectClassDO;
import com.java.roadstudent.roadjava.service.SelectClassService;
import com.java.roadstudent.roadjava.service.impl.SelectClassServiceImpl;
import com.java.roadstudent.roadjava.student1.SelectClassView;
import com.java.roadstudent.roadjava.student1.view.UpdateSelectClassView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSelectClassViewHandler implements ActionListener {

    private UpdateSelectClassView updateSelectClassView;
    private SelectClassView selectClassMainView;
    public UpdateSelectClassViewHandler(UpdateSelectClassView updateSelectClassView, SelectClassView selectClassMainView){
        this.updateSelectClassView = updateSelectClassView;
        this.selectClassMainView=selectClassMainView;
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
            if(updateResult==true){
                //重新加载表格查到最新数据
                selectClassMainView.reLoadClassTable();
                updateSelectClassView.dispose();
            }else {
                JOptionPane.showMessageDialog(updateSelectClassView,"修改失败");
            }
        }

    }


}
