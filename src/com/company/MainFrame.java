package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public static TableModelClients TMClients=new TableModelClients();
    public static JTable tableClients=new JTable(TMClients);
    public static TableModelServices TMServices=new TableModelServices();
    public static JTable tableServices=new JTable(TMServices);
    public static TableModelEmployee TMEmployee=new TableModelEmployee();
    public static JTable tableEmployee=new JTable(TMEmployee);


    MainFrame(){


        JPanel title=new JPanel();
        title.add(new JLabel("title"));
        title.setBackground(Color.CYAN);

        JPanel actions=new JPanel();
        JButton JB=new JButton("День рождение");
        JButton JB1=new JButton("Уведомления");
        actions.setLayout(new GridLayout(2, 1, 20, 20));
        actions.add(JB);
        actions.add(JB1);
        actions.setBorder(BorderFactory.createEmptyBorder(20,10,0,0));
        actions.setPreferredSize(new Dimension(300,500));
        actions.setBackground(Color.WHITE);

        JPanel main=new JPanel();
        main.setLayout(new BorderLayout());
        main.add(CreatePanelButton(),BorderLayout.CENTER);
        main.add(title,BorderLayout.NORTH);
        main.add(actions,BorderLayout.EAST);
        main.setBackground(Color.white);
        main.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        add(main, BorderLayout.CENTER);
        setSize( 1000,600);
        setVisible(true);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo(null);
    }
    private static JPanel CreatePanelButton(){
        JButton button1=new JButton("Клиенты");
        JButton button2=new JButton("Новый клиент");
        JButton button3=new JButton("кнопка");
        JButton button4=new JButton("Оказание услуги");
        JButton button5=new JButton("Записи на день");
        JButton button6=new JButton("Записи на месяц");
        JButton button7=new JButton("Услуги");
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameServices();
            }
        });
        JButton button8=new JButton("Сотрудники");
        JButton button9=new JButton("кнопка");

        JPanel paneButton=new JPanel();
        paneButton.setLayout( new GridLayout(3, 3, 20, 20));
        paneButton.add(button1);
        paneButton.add(button2);
        paneButton.add(button3);
        paneButton.add(button4);
        paneButton.add(button5);
        paneButton.add(button6);
        paneButton.add(button7);
        paneButton.add(button8);
        paneButton.add(button9);
        paneButton.setBackground(Color.WHITE);
        paneButton.setBorder(BorderFactory.createEmptyBorder(20,0,0,20));
        return  paneButton;
    }
    public static void FrameServices(){


        JPanel button=new JPanel();
        button.setLayout(new FlowLayout());
        button.add(new JLabel("button"));

        JPanel data=new JPanel();
        JScrollPane sp=new JScrollPane(tableServices);
        //sp.setSize();
        data.add(sp);

        JPanel panel=new JPanel();
        panel.add(button);
        panel.add(data);
        panel.setLayout(new GridLayout(2,1,20,20));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JFrame frame=new JFrame();
        frame.add(panel);
        frame.setSize(600,600);
        frame.setVisible(true);
    }
}
