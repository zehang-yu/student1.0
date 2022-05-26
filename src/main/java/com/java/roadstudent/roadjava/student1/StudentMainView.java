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

    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel centerlabel = new JLabel();
    JButton MyInformationBtn = new JButton("我的信息");
    JButton MyGradesBtn = new JButton("我的成绩");
    JButton TimetableQueryBtn =  new JButton("课表查询");
    JButton CourseSelectionBtn = new JButton("选课系统");

    JLabel nameLabel = new JLabel("姓名：",JLabel.RIGHT);
    JTextField nameTxt = new JTextField();
    JLabel noLabel = new JLabel("学号：",JLabel.RIGHT);
    JTextField noTxt = new JTextField();


    StudentMainViewHandler studentMainViewHandler;

    StudentMainViewTable studentMainViewTable =new StudentMainViewTable();
    public int id1=111;

    public StudentMainView(String no) {//传学号进来查询信息！！
        super("学生端-学生教务管理系统");


        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        StudentService me = new StudentServiceImpl();
        StudentDO studentDO= me.getByNo(Integer.parseInt(no));
        String name = studentDO.getName();
        String id = studentDO.getId().toString();
        id1=Integer.parseInt(no);
        studentMainViewHandler = new StudentMainViewHandler(this);
        //放置北边的组件
        northLayout(contentPane);
        //设置中间的jtable
        CenterLayout(contentPane,name,id);



        //自定义图标
        URL imageUrl = StudentMainView.class.getClassLoader().getResource("maotou.jpg");
        setIconImage(new ImageIcon(imageUrl).getImage());
        //根据屏幕大小设置主界面

        setBounds(bounds);
        //居中显示.
        //设置窗体完全充满整个屏幕的可见大小
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(900,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

    }

    private void CenterLayout(Container contentPane,String name,String ID) {
        URL imageUrl2 = StudentMainView.class.getClassLoader().getResource("huge.jpg");
        ImageIcon img = new ImageIcon(imageUrl2);// 创建图片对象
        centerlabel.setIcon(img);
        centerPanel.add(centerlabel);
/*        nameLabel.setPreferredSize(new Dimension(80,30));
        nameTxt.setPreferredSize(new Dimension(200,30));
        noLabel.setPreferredSize(new Dimension(80,30));
        noTxt.setPreferredSize(new Dimension(200,30));
        centerPanel.add(nameLabel);
        centerPanel.add(nameTxt);
        centerPanel.add(noLabel);
        centerPanel.add(noTxt);*/


        contentPane.add(centerPanel,BorderLayout.CENTER);
        Vector<Vector<Object>> data = new Vector<>();

        Vector<Object> rowVector1 = new Vector<>();


        rowVector1.addElement(name);
        rowVector1.addElement(ID);





        data.addElement(rowVector1);

        StudentMainViewTableModel studentMainViewTableModel = StudentMainViewTableModel.assembleModel(data);
        studentMainViewTable.setModel(studentMainViewTableModel);
        JScrollPane jScrollPane = new JScrollPane(studentMainViewTable);
        contentPane.add(jScrollPane,BorderLayout.LINE_END);



    }



    private void northLayout(Container contentPane) {
        //增加事件监听
        MyInformationBtn.addActionListener(studentMainViewHandler);
        MyGradesBtn.addActionListener(studentMainViewHandler);
        TimetableQueryBtn.addActionListener(studentMainViewHandler);
        CourseSelectionBtn.addActionListener(studentMainViewHandler);

        northPanel.add(MyInformationBtn);
        northPanel.add(MyGradesBtn);
        northPanel.add(TimetableQueryBtn);
        northPanel.add(CourseSelectionBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }


    public static void main(String[] args) {
        String no = "1";
        //new StudentMainView(no);

    }

}
