package com.java;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.Vector;

public class JtableDemo extends JFrame {
    public JtableDemo(){
        super("测试jtable");


        Vector<Vector<Object>> data = new Vector<>();

        Vector<Object> rowVector1 = new Vector<>();
        rowVector1.addElement("1");
        rowVector1.addElement("张三");
        rowVector1.addElement("no1");
        rowVector1.addElement("甘肃");
        rowVector1.addElement("1");
        rowVector1.addElement("2");
        rowVector1.addElement("3");
        rowVector1.addElement("6");

        Vector<Object> rowVector2 = new Vector<>();
        rowVector2.addElement("2");
        rowVector2.addElement("李四");
        rowVector2.addElement("no2");
        rowVector2.addElement("青海");
        rowVector2.addElement("2");
        rowVector2.addElement("2");
        rowVector2.addElement("3");
        rowVector2.addElement("7");

        Vector<Object> rowVector3 = new Vector<>();
        rowVector3.addElement("3");
        rowVector3.addElement("王五");
        rowVector3.addElement("no3");
        rowVector3.addElement("广东");
        rowVector3.addElement("1");
        rowVector3.addElement("3");
        rowVector3.addElement("3");
        rowVector3.addElement("7");

        data.addElement(rowVector1);
        data.addElement(rowVector2);
        data.addElement(rowVector3);

        //tablemodel和jtable关联后， 只需要更新model就能把数据的变化反应到jtable中
        StudentTableModel studentTableModel = StudentTableModel.assembleModel(data);
        //Jtable和table关联
        JTable jtable = new JTable(studentTableModel);
        //设置表头
        JTableHeader tableHeader = jtable.getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.RED);
        //表格体
        jtable.setFont(new Font(null,Font.PLAIN,14));
        jtable.setForeground(Color.BLACK);
        jtable.setGridColor(Color.BLACK);
        jtable.setRowHeight(30);
        //设置多行选择
        jtable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //设置表格列的渲染方式
        Vector<String> colums = StudentTableModel.getColums();
        StudentCellRender render = new StudentCellRender();
        for(int i=0;i<colums.size();i++ )
        {
            TableColumn column = jtable.getColumn(colums.get(i));
            column.setCellRenderer(render);
            if(i==0){
            column.setPreferredWidth(50);
            column.setMaxWidth(50);
            column.setResizable(false);
            }
        }




        Container contentPane = getContentPane();
        //jtable 放在 jpanel  上的话，默认是不展示列头的，需要特殊设置，放在JScrollPane上面
        //默认是展示列头的
        JScrollPane jScrollPane = new JScrollPane(jtable);
        contentPane.add(jScrollPane);


        setSize(600,400);//单位是像素
        //居中显示
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {
        new JtableDemo();
    }
}
class StudentCellRender extends DefaultTableCellRenderer {
    //在每一行的每一列显示之前都会调用
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(row%2==0)
        {
            setBackground(Color.LIGHT_GRAY);
        }
        else
        {
            setBackground(Color.white);
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}





//自定义tableModel
class StudentTableModel extends DefaultTableModel{

    static Vector<String> colums = new Vector<>();
    static {
        colums.addElement("编号");
        colums.addElement("姓名");
        colums.addElement("学号");
        colums.addElement("家乡");
        colums.addElement("语文");
        colums.addElement("数学");
        colums.addElement("英语");
        colums.addElement("总分");
    }
    private StudentTableModel(){
        super(null,colums);
    }
    private static StudentTableModel studentTableModel= new StudentTableModel();
    public static StudentTableModel assembleModel(Vector<Vector<Object>>data){
        studentTableModel.setDataVector(data,colums);
        return studentTableModel;
    }

    public static Vector<String> getColums() {
        return colums;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
