package com.java.roadstudent.roadjava.student1.exL;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class StudentMainViewTable extends JTable {
    public StudentMainViewTable(){
        //设置表头
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.blue);
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
        Vector<String> colums = StudentMainViewTableModel.getColums();
        StudentMainViewTableCellRender render = new StudentMainViewTableCellRender();
        for(int i=0;i<colums.size();i++ )
        {
            TableColumn column = getColumn(colums.get(i));
            column.setCellRenderer(render);
            if(i==0){
                column.setPreferredWidth(50);
                column.setMaxWidth(50);
                column.setResizable(false);
            }
        }

    }
}