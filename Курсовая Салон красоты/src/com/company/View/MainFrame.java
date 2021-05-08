package com.company.View;

import com.company.Model.TableModelClients;
import com.company.Model.TableModelEmployee;
import com.company.Model.TableModelServices;

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


    public MainFrame(){


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
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameClients();
            }
        });
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
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameEmployee();
            }
        });
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
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новая услуга");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog.Services();
            }
        });
        JButton delete=new JButton("Удалить услугу");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableClients.getSelectedRow()==0){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int [] i=tableServices.getSelectedRows();
                    int [] id = new int[i.length];
                    for (int j = 0; j < i.length; j++) {
                        id[j]= (int) TMServices.getValueAt(i[j],0);
                    }
                    TMServices.deleteRow(id);
                }
            }
        });
        JButton change=new JButton("Редактировать услугу");
        JButton info=new JButton("Сведения");
        JButton home=new JButton("На главную");

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(change);
        buttonPanel.add(info);
        buttonPanel.add(home);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.add(buttonPanel,BorderLayout.NORTH);
        panel.add(new JScrollPane(tableServices),BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JFrame frame=new JFrame();
        frame.add(panel);
        frame.setSize(700,450);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void FrameClients(){
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новый клиент");
        JButton delete=new JButton("Удалить клиента");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableClients.getSelectedRow()==0){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int [] i=tableClients.getSelectedRows();
                    int [] id = new int[i.length];
                    for (int j = 0; j < i.length; j++) {
                        id[j]= (int) TMClients.getValueAt(i[j],0);
                    }
                    TMClients.deleteRow(id);
                }
            }
        });
        JButton change=new JButton("Редактировать");
        JButton info=new JButton("Сведения");
        JButton home=new JButton("На главную");

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(change);
        buttonPanel.add(info);
        buttonPanel.add(home);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.add(buttonPanel,BorderLayout.NORTH);
        panel.add(new JScrollPane(tableClients),BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JFrame frame=new JFrame();
        frame.add(panel);
        frame.setSize(700,500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void FrameEmployee(){
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новый сотрудник");
        JButton delete=new JButton("Удалить сотрудника");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableClients.getSelectedRow()==0){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int [] i=tableEmployee.getSelectedRows();
                    int [] id = new int[i.length];
                    for (int j = 0; j < i.length; j++) {
                        id[j]= (int) TMEmployee.getValueAt(i[j],0);
                    }
                    TMEmployee.deleteRow(id);
                }
            }
        });
        JButton change=new JButton("Редактировать");
        JButton info=new JButton("Сведения");
        JButton home=new JButton("На главную");

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(change);
        buttonPanel.add(info);
        buttonPanel.add(home);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.add(buttonPanel,BorderLayout.NORTH);
        panel.add(new JScrollPane(tableEmployee),BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JFrame frame=new JFrame();
        frame.add(panel);
        frame.setSize(700,500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
