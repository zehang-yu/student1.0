package com.java.roadstudent.roadjava.student1.view;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;


public class QueryTimetableTableModel extends DefaultTableModel {

    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("课程");
        columns.addElement("授课教师");
        columns.addElement("教学楼");
        columns.addElement("教室");
        columns.addElement("上课时间");
        columns.addElement("年级");
        columns.addElement("年份");
    }
    private QueryTimetableTableModel(){
        super(null, columns);
    }
    private static QueryTimetableTableModel queryTimetableTableModel= new QueryTimetableTableModel();
    public static QueryTimetableTableModel assembleModel(Vector<Vector<Object>>data) {
        queryTimetableTableModel.setDataVector(data, columns);
        return queryTimetableTableModel;
    }
    public static void updateQueryTimetableModel(Vector<Vector<Object>>data){
        queryTimetableTableModel.setDataVector(data, columns);

    }
    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}


