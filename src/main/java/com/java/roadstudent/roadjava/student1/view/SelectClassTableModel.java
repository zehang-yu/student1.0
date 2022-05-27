package com.java.roadstudent.roadjava.student1.view;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;


public class SelectClassTableModel extends DefaultTableModel {

    static Vector<String> colums = new Vector<>();
    static {
        colums.addElement("编号");
        colums.addElement("课程名称");
        colums.addElement("授课教师");
        colums.addElement("可选人数");
        colums.addElement("已选人数");
        colums.addElement("所属学院");
        colums.addElement("选课状态");
    }
    private SelectClassTableModel(){
        super(null,colums);
    }
    private static  SelectClassTableModel selectClassTableModel= new SelectClassTableModel();
    public static SelectClassTableModel assembleModel(Vector<Vector<Object>>data){
        selectClassTableModel.setDataVector(data,colums);
        return selectClassTableModel;
    }
    public static void updateClassModel(Vector<Vector<Object>>data){
        selectClassTableModel.setDataVector(data,colums);

    }


    public static Vector<String> getColums() {
        return colums;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}


