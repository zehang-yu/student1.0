package com.java.roadstudent.roadjava.student1.view;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;


public class QueryTimetableTableModel extends DefaultTableModel {

    static Vector<String> colums = new Vector<>();
    static {
        colums.addElement("课程");
        colums.addElement("授课教师");
        colums.addElement("教学楼");
        colums.addElement("教室");
        colums.addElement("上课时间");
        colums.addElement("年级");
        colums.addElement("年份");
    }
    private QueryTimetableTableModel(){
        super(null,colums);
    }
    private static QueryTimetableTableModel queryTimetableTableModel= new QueryTimetableTableModel();
    public static QueryTimetableTableModel assembleModel(Vector<Vector<Object>>data){
        queryTimetableTableModel.setDataVector(data,colums);
        return queryTimetableTableModel;
    }
    public static void updateQueryTabletimeModel(Vector<Vector<Object>>data){
        queryTimetableTableModel.setDataVector(data,colums);

    }


    public static Vector<String> getColums() {
        return colums;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}


