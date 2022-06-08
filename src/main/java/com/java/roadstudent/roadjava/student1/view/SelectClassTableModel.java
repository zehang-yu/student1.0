package com.java.roadstudent.roadjava.student1.view;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;


public class SelectClassTableModel extends DefaultTableModel {

    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("编号");
        columns.addElement("课程名称");
        columns.addElement("授课教师");
        columns.addElement("容量");
        columns.addElement("已选");
        columns.addElement("所属学院");
        columns.addElement("选课状态");
    }
    private SelectClassTableModel(){
        super(null, columns);
    }
    private static  SelectClassTableModel selectClassTableModel= new SelectClassTableModel();
    public static SelectClassTableModel assembleModel(Vector<Vector<Object>>data){
        selectClassTableModel.setDataVector(data, columns);
        return selectClassTableModel;
    }
    public static void updateClassModel(Vector<Vector<Object>>data){
        selectClassTableModel.setDataVector(data, columns);

    }


    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}


