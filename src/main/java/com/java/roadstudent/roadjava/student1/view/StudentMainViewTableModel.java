package com.java.roadstudent.roadjava.student1.view;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class StudentMainViewTableModel extends DefaultTableModel{
    //自定义tableModel

        static Vector<String> columns = new Vector<>();
        static {

            columns.addElement("姓名");
            columns.addElement("学号");

        }
        private StudentMainViewTableModel(){
            super(null, columns);
        }
        private static StudentMainViewTableModel studentMainViewTableModel= new StudentMainViewTableModel();
        public static StudentMainViewTableModel assembleModel(Vector<Vector<Object>>data){
            studentMainViewTableModel.setDataVector(data, columns);
            return studentMainViewTableModel;
        }

        public static Vector<String> getColumns() {
            return columns;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    }


