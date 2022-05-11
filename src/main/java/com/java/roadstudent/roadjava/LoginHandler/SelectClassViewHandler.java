package com.java.roadstudent.roadjava.LoginHandler;


import com.java.roadstudent.roadjava.student1.SelectClassMainView;



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
            selectClassMainView.reLoadTable();
        }
        else if ("上一页".equals(text))
        {
            selectClassMainView.setPageNow(selectClassMainView.getPageNow()-1);
            selectClassMainView.reLoadTable();
        }
        else if ("下一页".equals(text))
        {
            selectClassMainView.setPageNow(selectClassMainView.getPageNow()+1);
            selectClassMainView.reLoadTable();
        }
    }


}
