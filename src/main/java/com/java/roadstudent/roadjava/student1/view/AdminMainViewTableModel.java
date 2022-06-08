package com.java.roadstudent.roadjava.student1.view;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;



public class AdminMainViewTableModel extends DefaultTableModel {

    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("序号");
        columns.addElement("学号");
        columns.addElement("姓名");
        columns.addElement("学院");
        columns.addElement("C++");
        columns.addElement("大英");
        columns.addElement("工数");
        columns.addElement("总分");
    }
    private AdminMainViewTableModel(){
        super(null, columns);
    }
    private static AdminMainViewTableModel mainViewTableModel= new AdminMainViewTableModel();
    public static AdminMainViewTableModel assembleModel(Vector<Vector<Object>>data){
        mainViewTableModel.setDataVector(data, columns);
        return mainViewTableModel;
    }
    public static void updateModel(Vector<Vector<Object>>data){
        mainViewTableModel.setDataVector(data, columns);

    }


    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}


