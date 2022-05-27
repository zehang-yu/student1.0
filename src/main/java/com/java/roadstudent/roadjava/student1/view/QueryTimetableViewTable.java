package com.java.roadstudent.roadjava.student1.view;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class QueryTimetableViewTable extends JTable {
    public QueryTimetableViewTable(){


        //设置表头

        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.RED);
        //表格体
        setFont(new Font(null,Font.PLAIN,14));
        setForeground(Color.BLACK);
        setGridColor(Color.BLACK);
        setRowHeight(30);
        //设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }





    public void renderRule(){
        //设置表格列的渲染方式
        Vector<String> colums = QueryTimetableTableModel.getColums();
        QueryTimetableViewCellRender render = new QueryTimetableViewCellRender();
        for(int i=0;i<colums.size();i++ )
        {
            TableColumn column = getColumn(colums.get(i));
            column.setCellRenderer(render);
            if(i==0){
                column.setPreferredWidth(100);
                column.setMaxWidth(100);
                column.setResizable(false);
            }
        }
    }
}
