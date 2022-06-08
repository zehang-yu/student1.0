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
        tableHeader.setForeground(Color.darkGray);
        tableHeader.setReorderingAllowed(false);
        //表格体
        setFont(new Font(null,Font.PLAIN,14));
        setForeground(Color.BLACK);
        setGridColor(Color.BLACK);
        setRowHeight(30);
        //设置不可选择
        setRowSelectionAllowed(false);
    }


    public void renderRule(){
        //设置表格列的渲染方式
        Vector<String> columns = QueryTimetableTableModel.getColumns();
        QueryTimetableViewCellRender render = new QueryTimetableViewCellRender();
        for(int i=0;i<columns.size();i++ )
        {
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(render);
            column.setResizable(false);
            if(i==0){
                column.setPreferredWidth(180);
                column.setMaxWidth(180);
            }
        }
    }
}
