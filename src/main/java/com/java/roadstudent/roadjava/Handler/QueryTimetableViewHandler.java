package com.java.roadstudent.roadjava.Handler;


import com.java.roadstudent.roadjava.student1.QueryTimetableMainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryTimetableViewHandler implements ActionListener {

    private QueryTimetableMainView queryTimetableMainView;
    public QueryTimetableViewHandler(QueryTimetableMainView queryTimetableMainView){
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
