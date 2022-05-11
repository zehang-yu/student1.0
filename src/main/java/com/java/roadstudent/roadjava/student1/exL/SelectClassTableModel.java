package com.java.roadstudent.roadjava.student1.exL;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;


public class SelectClassTableModel extends DefaultTableModel {

    static Vector<String> colums = new Vector<>();
    static {
        colums.addElement("课程编号");
        colums.addElement("课程名称");
        colums.addElement("教师名称");
        colums.addElement("人数限制");
        colums.addElement("已选人数");
        colums.addElement("学院限制");
        colums.addElement("操作");
    }
    private SelectClassTableModel(){
        super(null,colums);
    }
    private static  SelectClassTableModel selectClassTableModel= new SelectClassTableModel();
    public static SelectClassTableModel assembleModel(Vector<Vector<Object>>data){
        selectClassTableModel.setDataVector(data,colums);
        return selectClassTableModel;
    }



    public static Vector<String> getColums() {
        return colums;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}


