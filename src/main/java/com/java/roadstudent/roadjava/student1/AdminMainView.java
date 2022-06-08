package com.java.roadstudent.roadjava.student1;
import com.java.roadstudent.roadjava.Handler.AdminMainViewHandler;
import com.java.roadstudent.roadjava.req.StudentRequest;
import com.java.roadstudent.roadjava.res.TableDTO;
import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.service.impl.StudentServiceImpl;
import com.java.roadstudent.roadjava.student1.view.AdminMainViewTable;
import com.java.roadstudent.roadjava.student1.view.AdminMainViewTableModel;
import com.java.roadstudent.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Vector;

public class AdminMainView extends  JFrame{

    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton addButton = new JButton("添加");
    JButton updateButton = new JButton("修改");
    JButton delButton =  new JButton("删除");
    JTextField searchTxt=new JTextField(20);
    JButton searchBtn = new JButton("查询");

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    AdminMainViewTable mainViewTable = new AdminMainViewTable();
    private int pageNow = 1;//当前是第几页，默认是第一页
    private int pageSize;//一页显示多少条数据库记录

    AdminMainViewHandler mainViewHandler;



    public AdminMainView() {
        super("学生教务管理系统-教务员端");

        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height,50);

        mainViewHandler = new AdminMainViewHandler(this);
        //放置北边的组件
        northLayout(contentPane);
        //设置中间的JTable
        CenterLayout(contentPane);
        //放置南边的组件
        southLayout(contentPane);


        //自定义图标
        URL imageUrl = AdminMainView.class.getClassLoader().getResource("logo.png");
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
        AdminMainViewTableModel mainViewTableModel = AdminMainViewTableModel.assembleModel(dto.getData());
        //吧JTable和model关联
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
//        Vector<Vector<Object>> data = tableDTO.getData();
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
        if(pageNow == 1)
        {
            preBtn.setVisible(false);
        }
        else 
        {
            preBtn.setVisible(true);
        }
        int lastPage;//总共有几页
        if(totalCount%pageSize==0){
            lastPage = totalCount/pageSize;
        }
        else{
            lastPage = totalCount/pageSize+1;
        }
        if(pageNow == lastPage){
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
        new AdminMainView();

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
        AdminMainViewTableModel.updateModel(data);
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
