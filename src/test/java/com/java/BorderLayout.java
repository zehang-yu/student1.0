package com.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class BorderLayout extends JFrame{

    public BorderLayout(){
        //容器组件：jframe，jpanel，jscrollpane 非容器组件：jbutton ，jlabel，jtextfiel
        super("测试边界布局");


        Container contentPane = getContentPane();

        setSize(600,400);//单位是像素
        //居中显示
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


    }
    public static void main(String[] args) {
        new BorderLayout();
    }
}
