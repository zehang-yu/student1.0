package com.java.roadstudent.roadjava.student1;
import com.java.roadstudent.roadjava.Handler.QueryTimetableViewHandler;
import com.java.roadstudent.roadjava.req.StudentRequest;
import com.java.roadstudent.roadjava.res.TableDTO;
import com.java.roadstudent.roadjava.service.QueryTimetableService;
import com.java.roadstudent.roadjava.service.impl.QueryTimetableServiceImpl;
import com.java.roadstudent.roadjava.student1.view.QueryTimetableTableModel;
import com.java.roadstudent.roadjava.student1.view.QueryTimetableViewTable;
import com.java.roadstudent.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Vector;

public class QueryTimetableView extends  JFrame{

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    QueryTimetableViewTable queryTimetableViewTable = new QueryTimetableViewTable();
    private int pageNow = 1;//当前是第几页，默认是第一页
    private int pageSize;//一页显示多少条数据库记录

    QueryTimetableViewHandler queryTimetableViewHandler;



    public QueryTimetableView() {
        super("学生教务管理系统-已选查询");


        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height,35);



        queryTimetableViewHandler = new QueryTimetableViewHandler(this);
        //设置中间的JTable
        CenterLayout(contentPane);
        //放置南边的组件
        southLayout(contentPane);


        //自定义图标
        URL imageUrl = QueryTimetableView.class.getClassLoader().getResource("logo.png");
        setIconImage(new ImageIcon(imageUrl).getImage());
        //根据屏幕大小设置主界面

        setBounds(bounds);
        //居中显示.
        //设置窗体完全充满整个屏幕的可见大小
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);


    }

    private void CenterLayout(Container contentPane) {
        TableDTO dto = getTableDTO();

        //data+totalCount
        QueryTimetableTableModel queryTimetableTableModel = QueryTimetableTableModel.assembleModel(dto.getData());
        //吧JTable和model关联
        queryTimetableViewTable.setModel(queryTimetableTableModel);
        queryTimetableViewTable.renderRule();

        JScrollPane jScrollPane = new JScrollPane(queryTimetableViewTable );
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());

    }

    private TableDTO getTableDTO() {
        QueryTimetableService queryTimetableService = (QueryTimetableService) new QueryTimetableServiceImpl();
        StudentRequest request = new StudentRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        TableDTO tableDTO = queryTimetableService.retrieveQueryTimetable(request);
//        Vector<Vector<Object>> data = tableDTO.getData();
        return tableDTO;
    }

    private void southLayout(Container contentPane) {
        preBtn.addActionListener(queryTimetableViewHandler);
        nextBtn.addActionListener(queryTimetableViewHandler);
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
        int pageCount;//总共有几页
        if(totalCount%pageSize==0){
            pageCount = totalCount/pageSize;
        }else{
            pageCount = totalCount/pageSize+1;
        }
        if(pageNow==pageCount){
            nextBtn.setVisible(false);
        }else {
            nextBtn.setVisible(true);
        }

    }


    public static void main(String[] args) {
        new QueryTimetableView();

    }
    public void setPageNow(int pageNow){
        this.pageNow= pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void reLoadClassTable() {
        QueryTimetableService queryTimetableService = new QueryTimetableServiceImpl();
        StudentRequest request = new StudentRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        TableDTO tableDTO = queryTimetableService.retrieveQueryTimetable(request);
        Vector<Vector<Object>> data = tableDTO.getData();
        QueryTimetableTableModel.updateQueryTimetableModel(data);
        queryTimetableViewTable.renderRule();
        showPreNext(tableDTO.getTotalCount());

    }

}
