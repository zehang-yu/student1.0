package com;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class JframTest extends JFrame{
    JButton jButton;
    public JframTest(){
        //容器组件：jframe，jpanel，jscrollpane 非容器组件：jbutton ，jlabel，jtextfiel
        super("这是标题 ");

        jButton = new JButton("这是一个按钮");
        Container contentPane = getContentPane();
        contentPane.add(jButton);

        //设置窗口图标
        URL resourse = JframTest.class.getClassLoader().getResource("maotou.jpg");
        Image image = new ImageIcon(resourse).getImage();
        setIconImage(image);


        setSize(600,400);//单位是像素
        //居中显示
        setLocationRelativeTo(null);


        //关闭的时候退出程序
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //大小不可以改变
        setResizable(false);
        setVisible(true);


    }
    public static void main(String[] args) {
        new JframTest();
    }
}
