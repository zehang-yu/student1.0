package com.java.roadstudent.roadjava.Handler;


import com.java.roadstudent.roadjava.student1.SelectClassMainView;
import com.java.roadstudent.roadjava.student1.view.UpdateSelectClassView;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectClassViewHandler implements ActionListener {

    private SelectClassMainView selectClassMainView;
    public SelectClassViewHandler(SelectClassMainView selectClassMainView){
        this.selectClassMainView = selectClassMainView;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();

        if ("查询".equals(text))
        {
            selectClassMainView.setPageNow(1);
            selectClassMainView.reLoadClassTable();
        }else if ("修改".equals(text))
        {
            int[] selectClassIds = selectClassMainView.getSelectClassIds();
            if(selectClassIds.length!=1){
                JOptionPane.showMessageDialog(selectClassMainView,"一次只能修改一行");
                return;
            }else{
                new UpdateSelectClassView(selectClassMainView,selectClassIds[0]);
            }

        }
        else if ("上一页".equals(text))
        {
            selectClassMainView.setPageNow(selectClassMainView.getPageNow()-1);
            selectClassMainView.reLoadClassTable();
        }
        else if ("下一页".equals(text))
        {
            selectClassMainView.setPageNow(selectClassMainView.getPageNow()+1);
            selectClassMainView.reLoadClassTable();
        }
    }


}
