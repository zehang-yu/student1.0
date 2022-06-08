package com.java.roadstudent.roadjava.student1;

import com.java.roadstudent.roadjava.Handler.StudentMainViewHandler;
import com.java.roadstudent.roadjava.entity.StudentDO;
import com.java.roadstudent.roadjava.service.StudentService;
import com.java.roadstudent.roadjava.service.impl.StudentServiceImpl;
import com.java.roadstudent.roadjava.student1.view.StudentMainViewTable;
import com.java.roadstudent.roadjava.student1.view.StudentMainViewTableModel;
import com.java.roadstudent.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Vector;


public class StudentMainView extends  JFrame{

    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel centerLabel = new JLabel();
    JButton MyInformationBtn = new JButton("我的信息");
    JButton MyGradesBtn = new JButton("我的成绩");
    JButton CourseSelectionBtn = new JButton("选课系统");
    JButton TimetableQueryBtn =  new JButton("已选查询");

    JLabel nameLabel = new JLabel("姓名：",JLabel.CENTER);
    JTextField nameTxt = new JTextField();
    JLabel noLabel = new JLabel("学号：",JLabel.CENTER);
    JTextField noTxt = new JTextField();


    StudentMainViewHandler studentMainViewHandler;

    StudentMainViewTable studentMainViewTable =new StudentMainViewTable();
    public int id = 0;

    public StudentMainView(String no) { //传学号进来查询信息
        super("学生教务管理系统-学生端");

        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        StudentService me = new StudentServiceImpl();
        StudentDO studentDO= me.getByNo(Integer.parseInt(no));
        String name = studentDO.getName();
        String id = studentDO.getId().toString();
        this.id =Integer.parseInt(no);
        studentMainViewHandler = new StudentMainViewHandler(this);
        //放置北边的组件
        northLayout(contentPane);
        //设置中间的JTable
        CenterLayout(contentPane,name,no);



        //自定义图标
        URL imageUrl = StudentMainView.class.getClassLoader().getResource("logo.png");
        setIconImage(new ImageIcon(imageUrl).getImage());
        //根据屏幕大小设置主界面

        setBounds(bounds);
        //居中显示
        setSize(400,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    private void CenterLayout(Container contentPane,String name,String no) {

//        URL imageUrl2 = StudentMainView.class.getClassLoader().getResource("huge.jpg");
//        ImageIcon img = new ImageIcon(imageUrl2);// 创建图片对象
//        centerLabel.setIcon(img);
//        centerPanel.add(centerLabel);

        contentPane.add(centerPanel,BorderLayout.CENTER);
        Vector<Vector<Object>> data = new Vector<>();
        Vector<Object> rowVector1 = new Vector<>();


        rowVector1.addElement(name);
        rowVector1.addElement(no);
        data.addElement(rowVector1);

        StudentMainViewTableModel studentMainViewTableModel = StudentMainViewTableModel.assembleModel(data);
        studentMainViewTable.setModel(studentMainViewTableModel);
        studentMainViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(studentMainViewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);


    }



    private void northLayout(Container contentPane) {
        //增加事件监听
        MyInformationBtn.addActionListener(studentMainViewHandler);
        MyGradesBtn.addActionListener(studentMainViewHandler);
        CourseSelectionBtn.addActionListener(studentMainViewHandler);
        TimetableQueryBtn.addActionListener(studentMainViewHandler);

        northPanel.add(MyInformationBtn);
        northPanel.add(MyGradesBtn);
        northPanel.add(CourseSelectionBtn);
        northPanel.add(TimetableQueryBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }


    public static void main(String[] args) {
        String no = "1";

    }

}
