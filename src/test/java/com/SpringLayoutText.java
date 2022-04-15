package com;

import com.sun.prism.j2d.print.J2DPrinterJob;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SpringLayoutText extends JFrame{
    //设置JPanel的布局管理器为SpringLay
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);


    JLabel titleLabel = new JLabel("文章标题：");
    JTextField titleText = new JTextField();
    JLabel authorLabel = new JLabel("作者：");
    JTextField authorText = new JTextField();
    JLabel contLabel = new JLabel("请输入内容：");
    JTextArea contArea = new JTextArea(4,10) ;


    public SpringLayoutText(){
        super("弹簧布局");//先定位好一个组件，其他组件都参照其定位就行
        Container contentPane = getContentPane();
        //容器组件：jframe，jpanel，jscrollpane 非容器组件：jbutton ，jlabel，jtextfiel

        //加入到Jpanel中
        jPanel.add(titleLabel);
        titleText.setPreferredSize(new Dimension(200,30));
        jPanel.add(titleText);
        jPanel.add(authorLabel);
        authorText.setPreferredSize(new Dimension(200,30));
        jPanel.add(authorText);
        jPanel.add(contLabel);
        jPanel.add(contArea);




        contentPane.add(jPanel);
        /*
        * SpringLay:布局管理器
        * SpringLayout.Constraints：使用弹簧布局的容器的组件的布局约束，每个组件对应一个
        * Spring：可以理解为一个能够进行四则运算的整数
        * */
        Spring titleLabrWith = Spring.width(titleLabel);
        Spring titleTextWith = Spring.width(titleText);
        Spring spaceWith = Spring.constant(20);
        Spring childWith = Spring.sum(Spring.sum(titleTextWith, titleLabrWith), spaceWith);
        int offSetX = childWith.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST,titleLabel,-offSetX,SpringLayout.HORIZONTAL_CENTER,jPanel);
        SpringLayout.Constraints titleLabelC = springLayout.getConstraints(titleLabel);
        //titleLabelC.setX(Spring.constant(100));
        titleLabelC.setY(Spring.constant(50));
        /*
        * 设置约束的第一种写法，比较复杂
        *
        * */
        //设置标题文本框：titleText 西边距离为titleLabel 的东边20px，北边相同
        SpringLayout.Constraints titleTextC = springLayout.getConstraints(titleText);
        //edgename：东南西北 s：值
        Spring titleLabelEastSpring = titleLabelC.getConstraint(SpringLayout.EAST);
        titleTextC.setConstraint(SpringLayout.WEST,Spring.sum(titleLabelEastSpring,Spring.constant(20)));
        titleTextC.setConstraint(SpringLayout.NORTH,titleLabelC.getConstraint(SpringLayout.NORTH));
        /*
        * 设置约束的第二种写法相对简单
        * e1:要设置组件的那个边界（edgname）
        * c1：要设置的组件
        * pad：距离值
        * e2：参照组件的边界名
        * c2：参照组件
        * */
        //设置作者label：authorLabel，东边和titleLable对齐，北边距离titlLabel20px
        springLayout.putConstraint(SpringLayout.EAST,authorLabel,0,SpringLayout.EAST,titleLabel);
        springLayout.putConstraint( SpringLayout.NORTH,authorLabel,20,SpringLayout.SOUTH,titleLabel);
        //设置authorText authorText 西边距离为authorLabel 的东边20px，北边相同
        springLayout.putConstraint(SpringLayout.WEST,authorText,20,SpringLayout.EAST,authorLabel);
        springLayout.putConstraint(SpringLayout.NORTH,authorText,0,SpringLayout.NORTH,authorLabel);
        //内容label
        springLayout.putConstraint(SpringLayout.EAST,contLabel,0,SpringLayout.EAST,authorLabel);
        springLayout.putConstraint( SpringLayout.NORTH,contLabel,20,SpringLayout.SOUTH,authorLabel);
        //内容Area
        springLayout.putConstraint(SpringLayout.WEST,contArea,20,SpringLayout.EAST,contLabel);
        springLayout.putConstraint(SpringLayout.NORTH,contArea,0,SpringLayout.NORTH,contLabel);
        //contArea的南边和东边参照jpanel
        springLayout.putConstraint(SpringLayout.SOUTH,contArea,-20,SpringLayout.SOUTH,jPanel);
        springLayout.putConstraint(SpringLayout.EAST,contArea,-20,SpringLayout.EAST, jPanel);


        setSize(600,400);//单位是像素
        //居中显示
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);


    }
    public static void main(String[] args) {
        new SpringLayoutText();
    }
}
