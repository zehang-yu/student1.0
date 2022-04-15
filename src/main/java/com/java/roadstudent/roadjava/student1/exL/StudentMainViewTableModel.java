package com.java.roadstudent.roadjava.student1.exL;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class StudentMainViewTableModel extends DefaultTableModel{
    //自定义tableModel

        static Vector<String> colums = new Vector<>();
        static {

            colums.addElement("姓名");
            colums.addElement("学号");

        }
        private StudentMainViewTableModel(){
            super(null,colums);
        }
        private static StudentMainViewTableModel studentMainViewTableModel= new StudentMainViewTableModel();
        public static StudentMainViewTableModel assembleModel(Vector<Vector<Object>>data){
            studentMainViewTableModel.setDataVector(data,colums);
            return studentMainViewTableModel;
        }

        public static Vector<String> getColums() {
            return colums;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }


