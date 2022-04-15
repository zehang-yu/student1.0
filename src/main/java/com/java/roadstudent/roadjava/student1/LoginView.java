package com.java.roadstudent.roadjava.student1;
import com.java.roadstudent.roadjava.LoginHandler.LoginHandler;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import static java.awt.Font.PLAIN;

public class LoginView extends  JFrame{





    JLabel nameLabel = new JLabel("学生管理系统",JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPane = new JPanel(springLayout);
    JLabel userNameLabel = new JLabel("用户名：");
    JTextField userText = new JTextField();
    JLabel pwdLabel = new JLabel("密码：");
    JPasswordField pwdField = new JPasswordField();
    JButton loginBtn = new JButton("管理员登录");
    //JButton resetBtn = new JButton("重置");
    JButton stuLoginbtn = new JButton("学生登录");
    TrayIcon trayIcon;
    SystemTray systemTray;
    LoginHandler loginHandler;





    public LoginView() {
        super("学生成绩管理系统");

        loginHandler= new LoginHandler(this);
        Container contentPane = getContentPane();
        nameLabel.setFont(new Font("华文斜体",Font.PLAIN,40));
        nameLabel.setPreferredSize(new Dimension(0,80));
        Font centerFont = new Font("楷体", PLAIN,20);
        userNameLabel.setFont(centerFont);
        userText.setPreferredSize(new Dimension(200,30));
        pwdLabel.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(200,30));
        loginBtn.setFont(centerFont);
        //resetBtn.setFont(centerFont);
        stuLoginbtn.setFont(centerFont);
        //把组件加入面板
        centerPane.add(nameLabel);
        centerPane.add(userNameLabel);
        centerPane.add(userText);
        centerPane.add(pwdLabel);
        centerPane.add(pwdField);
        loginBtn.addActionListener(loginHandler);
        //增加按键事件
        loginBtn.addKeyListener(loginHandler);
        centerPane.add(loginBtn);
        //resetBtn.addActionListener(loginHandler);
       // centerPane.add(resetBtn);
        stuLoginbtn.addActionListener(loginHandler);
        centerPane.add(stuLoginbtn);
        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPane,BorderLayout.CENTER);
        //弹簧布局
        layoutCenter();

        if(SystemTray.isSupported())
        {
            systemTray = SystemTray.getSystemTray();
            URL imageUrl = LoginView.class.getClassLoader().getResource("moyu.png");
            trayIcon = new TrayIcon(new ImageIcon(imageUrl).getImage());
            //设置托盘图片大小自动缩放
            trayIcon.setImageAutoSize(true);

            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }

            //增加最小化时销毁资源
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    LoginView.this.dispose();
                }
            });
            //托盘监听
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCount = e.getClickCount();
                    if(clickCount==1){
                     LoginView.this.setExtendedState(JFrame.NORMAL);
                    }
                    LoginView.this.setVisible(true);
                }
            });
        }

        //设置longinBtn为默认按钮
        getRootPane().setDefaultButton(loginBtn);




        //自定义图标
        URL imageUrl = LoginView.class.getClassLoader().getResource("maotou.jpg");
        setIconImage(new ImageIcon(imageUrl).getImage());
        setSize(600,400);//单位是像素
        //居中显示
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }
    //弹簧布局
    private void layoutCenter() {

        //布局userNameLabel
        Spring childwith = Spring.sum(Spring.sum(Spring.width(userNameLabel), Spring.width(userText)), Spring.constant(20));
        int offSetX  = childwith.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offSetX,SpringLayout.HORIZONTAL_CENTER,centerPane);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,20,SpringLayout.NORTH,centerPane);

        //userText
        springLayout.putConstraint(SpringLayout.WEST,userText,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userText,0,SpringLayout.NORTH,userNameLabel);
        //pwdLabel
        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,20,SpringLayout.SOUTH,userNameLabel);
        //pwdField
        springLayout.putConstraint(SpringLayout.WEST,pwdField,20,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdField,0,SpringLayout.NORTH,pwdLabel);
        //loginBtn
        springLayout.putConstraint(SpringLayout.WEST,loginBtn,0,SpringLayout.WEST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,20,SpringLayout.SOUTH,pwdLabel);
        //resetBtn
        //springLayout.putConstraint(SpringLayout.WEST,resetBtn,50,SpringLayout.EAST,loginBtn);
        //springLayout.putConstraint(SpringLayout.NORTH,resetBtn,0,SpringLayout.NORTH,loginBtn);
        //stuLoginbtn
        springLayout.putConstraint(SpringLayout.WEST,stuLoginbtn,50,SpringLayout.EAST,loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH,stuLoginbtn,0,SpringLayout.NORTH,loginBtn);
    }


    public static void main(String[] args) {
//        try {
 //           BeautyEyeLNFHelper.launchBeautyEyeLNF();
 //       } catch (Exception var2) {
   //         System.out.println("加载炫彩皮肤失败！");
   //     }
        new LoginView();
    }

    public JTextField getUserText() {
        return userText;
    }

    public JPasswordField getPwdField() {
        return pwdField;
    }

}
