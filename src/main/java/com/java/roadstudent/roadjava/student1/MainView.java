package com.java.roadstudent.roadjava.student1;
import com.java.roadstudent.roadjava.LoginHandler.LoginHandler;
import com.java.roadstudent.roadjava.LoginHandler.MainViewHandler;
import com.java.roadstudent.roadjava.req.StudentRequest;
import com.java.roadstudent.roadjava.res.TableDTO;
import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.service.impl.StudentServiceImpl;
import com.java.roadstudent.roadjava.student1.exL.MainViewTable;
import com.java.roadstudent.roadjava.student1.exL.MainViewTableModel;
import com.java.roadstudent.roadjava.util.DimensionUtil;
import javafx.scene.control.Tab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Vector;

import static java.awt.Font.PLAIN;

public class MainView extends  JFrame{

    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addButton = new JButton("增加");
    JButton updateButton = new JButton("修改");
    JButton delButton =  new JButton("删除");
    JTextField searchTxt=new JTextField(15);
    JButton searchBtn = new JButton("查询");

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    MainViewTable mainViewTable=new MainViewTable();
    private int pageNow = 1;//当前是第几页，默认是第一页
    private int pageSize = 10;//一页显示多少条数据库记录

    MainViewHandler mainViewHandler;



    public MainView() {
        super("管理员端-学生成绩管理系统");


        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height,35);



        mainViewHandler = new MainViewHandler(this);
        //放置北边的组件
        northLayout(contentPane);
        //设置中间的jtable
        CenterLayout(contentPane);
        //放置南边的组件
        southLayout(contentPane);


        //自定义图标
        URL imageUrl = MainView.class.getClassLoader().getResource("maotou.jpg");
        setIconImage(new ImageIcon(imageUrl).getImage());
        //根据屏幕大小设置主界面

        setBounds(bounds);
        //居中显示.
        //设置窗体完全充满整个屏幕的可见大小
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

    }

    private void CenterLayout(Container contentPane) {
        TableDTO dto = getTableDTO();

        //data+totalCount
        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(dto.getData());
        //吧jtable和model关联
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();

        JScrollPane jScrollPane = new JScrollPane(mainViewTable );
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());

    }

    private TableDTO getTableDTO() {
        StudentService studentService = new StudentServiceImpl();
        StudentRequest request = new StudentRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = studentService.retrieveStudent(request);
        Vector<Vector<Object>> data = tableDTO.getData();
        return tableDTO;
    }

    private void southLayout(Container contentPane) {
        preBtn.addActionListener(mainViewHandler);
        nextBtn.addActionListener(mainViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);

    }
    //设置上一页下一页是否可见
    private void showPreNext(int totalCount){
        if(pageNow==1){
            preBtn.setVisible(false);
        }else {
            preBtn.setVisible(true);
        }
        int pageCount = 0;//总共有几页
        if(totalCount%pageSize==0){
            pageCount=totalCount/pageSize;
        }else{
            pageCount=totalCount/pageSize+1;
        }
        if(pageNow==pageCount){
            nextBtn.setVisible(false);
        }else {
            nextBtn.setVisible(true);
        }

    }

    private void northLayout(Container contentPane) {
        //增加事件监听
        addButton.addActionListener(mainViewHandler);
        updateButton.addActionListener(mainViewHandler);
        delButton.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);

        northPanel.add(addButton);
        northPanel.add(updateButton);
        northPanel.add(delButton);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }


    public static void main(String[] args) {
        new MainView();

    }
    public void setPageNow(int pageNow){
        this.pageNow= pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void reLoadTable() {
        StudentService studentService = new StudentServiceImpl();
        StudentRequest request = new StudentRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = studentService.retrieveStudent(request);
        Vector<Vector<Object>> data = tableDTO.getData();
        MainViewTableModel.updateModel(data);
        mainViewTable.renderRule();
        showPreNext(tableDTO.getTotalCount());

    }
    public int[] getSelectStudentIds(){
        int[] selectedRows = mainViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for(int i=0;i<selectedRows.length;i++){
            int rowIndex = selectedRows[i];
            Object idObj = mainViewTable.getValueAt(rowIndex, 0);
            ids[i] = Integer.valueOf(idObj.toString());
        }
        return ids;
    }




}