package com.java.roadstudent.roadjava.Handler;


import com.java.roadstudent.roadjava.student1.QueryTimetableView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryTimetableViewHandler implements ActionListener {

    private QueryTimetableView queryTimetableMainView;
    public QueryTimetableViewHandler(QueryTimetableView queryTimetableMainView){
        this.queryTimetableMainView = queryTimetableMainView;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();


        if ("上一页".equals(text))
        {
            queryTimetableMainView.setPageNow(queryTimetableMainView.getPageNow()-1);
            queryTimetableMainView.reLoadClassTable();
        }
        else if ("下一页".equals(text))
        {
            queryTimetableMainView.setPageNow(queryTimetableMainView.getPageNow()+1);
            queryTimetableMainView.reLoadClassTable();
        }
    }


}
