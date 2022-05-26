package com.java.roadstudent.roadjava.Handler;

import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.service.impl.StudentServiceImpl;
import com.java.roadstudent.roadjava.student1.MainView;
import com.java.roadstudent.roadjava.student1.view.AddStudentView;
import com.java.roadstudent.roadjava.student1.view.UpdateStudentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewHandler implements ActionListener {

    private MainView mainView;
    public MainViewHandler(MainView mainView){
        this.mainView = mainView;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("增加".equals(text))
        {
            new AddStudentView(mainView);
        }
        else if ("修改".equals(text))
        {
            int[] selectStudentIds = mainView.getSelectStudentIds();
            if(selectStudentIds.length!=1){
                JOptionPane.showMessageDialog(mainView,"一次只能修改一行");
                return;
            }
            new UpdateStudentView(mainView,selectStudentIds[0]);
        }
        else if ("删除".equals(text))
        {
            int[] selectStudentIds = mainView.getSelectStudentIds();
            if(selectStudentIds.length==0){
                JOptionPane.showMessageDialog(mainView,"请选择删除行");
                return;
            }
            int option = JOptionPane.showConfirmDialog(mainView,
                    "你确认要删除选择的" + selectStudentIds.length + "行吗?", "确认删除", JOptionPane.YES_NO_OPTION);
            if(option==JOptionPane.YES_OPTION){
                //确认
                //执行删除
                StudentService studentService = new StudentServiceImpl();
                boolean deleteResult = studentService.delete(selectStudentIds);
                if(deleteResult==true){
                    //重新加载表格查到最新数据
                    mainView.reLoadTable();

                }else {
                    JOptionPane.showMessageDialog(mainView,"删除失败");
                }
            }

        }
        else if ("查询".equals(text))
        {
            mainView.setPageNow(1);
            mainView.reLoadTable();
        }
        else if ("上一页".equals(text))
        {
            mainView.setPageNow(mainView.getPageNow()-1);
            mainView.reLoadTable();
        }
        else if ("下一页".equals(text))
        {
            mainView.setPageNow(mainView.getPageNow()+1);
            mainView.reLoadTable();
        }
    }


}
