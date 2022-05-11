package com.java.roadstudent.roadjava.student1;
import com.java.roadstudent.roadjava.LoginHandler.MainViewHandler;
import com.java.roadstudent.roadjava.LoginHandler.SelectClassViewHandler;
import com.java.roadstudent.roadjava.req.StudentRequest;
import com.java.roadstudent.roadjava.res.TableDTO;
import com.java.roadstudent.roadjava.service.SelectClassService;
import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.service.impl.SelectClassServiceImpl;
import com.java.roadstudent.roadjava.service.impl.StudentServiceImpl;
import com.java.roadstudent.roadjava.student1.exL.MainViewTable;
import com.java.roadstudent.roadjava.student1.exL.MainViewTableModel;
import com.java.roadstudent.roadjava.student1.exL.SelectClassTableModel;
import com.java.roadstudent.roadjava.student1.exL.SelectClassViewTable;
import com.java.roadstudent.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Vector;

public class SelectClassMainView extends  JFrame{

    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JTextField searchTxt=new JTextField(15);
    JButton searchBtn = new JButton("查询");

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    SelectClassViewTable selectClassViewTable=new SelectClassViewTable();
    private int pageNow = 1;//当前是第几页，默认是第一页
    private int pageSize = 10;//一页显示多少条数据库记录

    SelectClassViewHandler selectClassViewHandler;



    public SelectClassMainView() {
        super("选课界面-学生成绩管理系统");


        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height,35);



        selectClassViewHandler = new SelectClassViewHandler(this);
        //放置北边的组件
        northLayout(contentPane);
        //设置中间的jtable
        CenterLayout(contentPane);
        //放置南边的组件
        southLayout(contentPane);


        //自定义图标
        URL imageUrl = SelectClassMainView.class.getClassLoader().getResource("maotou.jpg");
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
        SelectClassTableModel selectClassTableModel = SelectClassTableModel.assembleModel(dto.getData());
        //吧jtable和model关联
        selectClassViewTable.setModel(selectClassTableModel);
        selectClassViewTable.renderRule();

        JScrollPane jScrollPane = new JScrollPane(selectClassViewTable );
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());

    }

    private TableDTO getTableDTO() {
        SelectClassService selectClassService = new SelectClassServiceImpl();
        StudentRequest request = new StudentRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = selectClassService.retrieveSelectClass(request);
        Vector<Vector<Object>> data = tableDTO.getData();
        return tableDTO;
    }

    private void southLayout(Container contentPane) {
        preBtn.addActionListener(selectClassViewHandler);
        nextBtn.addActionListener(selectClassViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);

    }
    //设置上一页下一页是否可见
    private void showPreNext(int totalCount){
        if(pageNow == 1){
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
        searchBtn.addActionListener(selectClassViewHandler);

        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }


    public static void main(String[] args) {
        new SelectClassMainView();

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
        selectClassViewTable.renderRule();
        showPreNext(tableDTO.getTotalCount());

    }
    public int[] getSelectStudentIds(){
        int[] selectedRows = selectClassViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for(int i=0;i<selectedRows.length;i++){
            int rowIndex = selectedRows[i];
            Object idObj = selectClassViewTable.getValueAt(rowIndex, 0);
            ids[i] = Integer.valueOf(idObj.toString());
        }
        return ids;
    }




}
