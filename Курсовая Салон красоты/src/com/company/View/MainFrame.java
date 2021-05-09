package com.company.View;

import com.company.Essence.Clients;
import com.company.Essence.Employee;
import com.company.Essence.Services;
import com.company.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.Font;

public class MainFrame extends JFrame {

    //создаем таблицы и их модели
    public static TableModelClients TMClients=new TableModelClients();
    public static JTable tableClients=new JTable(TMClients);
    public static TableModelServices TMServices=new TableModelServices();
    public static JTable tableServices=new JTable(TMServices);
    public static TableModelEmployee TMEmployee=new TableModelEmployee();
    public static JTable tableEmployee=new JTable(TMEmployee);
    public static TableModelPerformedWork TMWork=new TableModelPerformedWork();
    public static JTable tableWork=new JTable(TMWork);
    public static TableModelRecord TMRecord=new TableModelRecord();
    public static JTable tableRecord=new JTable(TMRecord);
    public static TableModelRecordDay TMRecordDay=new TableModelRecordDay();
    public static JTable tableRecordDay=new JTable(TMRecordDay);

    public static JFrame f=new JFrame();

    //конструктор
    public MainFrame(){

        {
            tableServices.getColumnModel().getColumn(0).setPreferredWidth(150);
            tableServices.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableServices.getColumnModel().getColumn(2).setPreferredWidth(150);

            tableClients.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableClients.getColumnModel().getColumn(1).setPreferredWidth(110);
            tableClients.getColumnModel().getColumn(2).setPreferredWidth(110);
            tableClients.getColumnModel().getColumn(3).setPreferredWidth(110);
            tableClients.getColumnModel().getColumn(4).setPreferredWidth(90);
            tableClients.getColumnModel().getColumn(5).setPreferredWidth(100);
            tableClients.getColumnModel().getColumn(6).setPreferredWidth(120);

            tableEmployee.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableEmployee.getColumnModel().getColumn(1).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(4).setPreferredWidth(90);
            tableEmployee.getColumnModel().getColumn(5).setPreferredWidth(200);
            tableEmployee.getColumnModel().getColumn(6).setPreferredWidth(110);

            tableRecord.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableRecord.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableRecord.getColumnModel().getColumn(2).setPreferredWidth(50);
            tableRecord.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableRecord.getColumnModel().getColumn(4).setPreferredWidth(100);
            tableRecord.getColumnModel().getColumn(5).setPreferredWidth(100);
            tableRecord.getColumnModel().getColumn(6).setPreferredWidth(100);
            tableRecord.getColumnModel().getColumn(7).setPreferredWidth(100);
            tableRecord.getColumnModel().getColumn(8).setPreferredWidth(100);
            tableRecord.getColumnModel().getColumn(9).setPreferredWidth(50);
            tableRecord.getColumnModel().getColumn(10).setPreferredWidth(50);
            tableRecord.getColumnModel().getColumn(11).setPreferredWidth(100);

            tableWork.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableWork.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableWork.getColumnModel().getColumn(2).setPreferredWidth(50);
            tableWork.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableWork.getColumnModel().getColumn(4).setPreferredWidth(100);
            tableWork.getColumnModel().getColumn(5).setPreferredWidth(100);
            tableWork.getColumnModel().getColumn(6).setPreferredWidth(100);
            tableWork.getColumnModel().getColumn(7).setPreferredWidth(100);
            tableWork.getColumnModel().getColumn(8).setPreferredWidth(100);
            tableWork.getColumnModel().getColumn(9).setPreferredWidth(50);
            tableWork.getColumnModel().getColumn(10).setPreferredWidth(50);
            tableWork.getColumnModel().getColumn(11).setPreferredWidth(100);
        }

        //панель верхняя
        JPanel titlePanel = new JPanel();
        String text = "EasyBeauty";
        Font font = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 25);
        JLabel htmlLabel = new JLabel();
        htmlLabel.setText(text);
        htmlLabel.setFont(font);
        titlePanel.add(htmlLabel);
        titlePanel.setBackground(new Color(93,222,211));

        //панель справа с уведомлениями
        JPanel actions=new JPanel();
        JPanel bithday=new JPanel();
        bithday.setLayout(new FlowLayout());
        bithday.setBackground(new Color(180,240,235));
        Date current = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String day2 = formatter.format(current);
        bithday.add(new JLabel("Сегодня( "+day2+" ) день рождение:"));
        String mes="";
        DBWorker.initDB();
        List<Clients> l=DBWorker.selectBithday();
        List<Employee> lE=DBWorker.selectBithdayEmployee();
        DBWorker.closeDB();
        for (Clients c:l) {
            mes="Клиент: "+c.getSurname()+" "+c.getName()+" "+c.getPhone()+"\n";
            bithday.add(new JLabel(mes));
        }
        for (Employee e:lE) {
            mes="Сотрудник: "+e.getSurname()+" "+e.getName()+ "\n";
            bithday.add(new JLabel(mes));
        }

        JLabel JL=new JLabel("Уведомления");
        JL.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 20));
        JL.setAlignmentX(Component.CENTER_ALIGNMENT);
        actions.setLayout(new GridLayout(2, 1, 20, 20));
        actions.add(bithday);
        actions.add(JL);
        actions.setBorder(BorderFactory.createEmptyBorder(20,10,0,0));
        actions.setPreferredSize(new Dimension(350,500));
        actions.setBackground(Color.white);
        //панель которая кладется в frame , сделано что бы можно было редактироваь отступы,цвета
        JPanel main=new JPanel();
        main.setLayout(new BorderLayout());
        main.add(CreatePanelButton(),BorderLayout.CENTER);//панель с кнопками
        main.add( titlePanel,BorderLayout.NORTH);//панель верхняя
        main.add(actions,BorderLayout.EAST);//панель справа
        main.setBackground(Color.white);
        main.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));


        f.add(main, BorderLayout.CENTER);
        f.setSize( 1000,600);
        f.setVisible(true);
        f.setTitle("EasyBeauty");
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setLocationRelativeTo(null);
    }

    private static JPanel CreatePanelButton(){
        //метод возвращающий созданную  панели с кнопками
        //обьявление кнопок+листенеры+добавление
        JButton button1=new JButton("Клиенты");
        JButton button2=new JButton("Новый клиент");
        JButton button3=new JButton("Выполненные работы");
        JButton button4=new JButton("Оказание услуги");
        JButton button5=new JButton("Записи на день");
        JButton button6=new JButton("Записи на месяц");
        JButton button7=new JButton("Услуги");
        JButton button8=new JButton("Сотрудники");
        JButton button9=new JButton("Все записи");
        button1.setBackground(new Color(180,240,235));
        button2.setBackground(new Color(180,240,235));
        button3.setBackground(new Color(180,240,235));
        button4.setBackground(new Color(180,240,235));
        button5.setBackground(new Color(180,240,235));
        button6.setBackground(new Color(180,240,235));
        button7.setBackground(new Color(180,240,235));
        button8.setBackground(new Color(180,240,235));
        button9.setBackground(new Color(180,240,235));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameClients();
                f.dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog.addCliens();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FramePerformedWork();
                f.dispose();
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog.addWork();
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameRecordDay();
                f.dispose();
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameRecordMonth();
                f.dispose();
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameServices();
                f.dispose();
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameEmployee();
                f.dispose();
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameRecord();
                f.dispose();
            }
        });

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
        paneButton.setBackground(Color.white);
        paneButton.setBorder(BorderFactory.createEmptyBorder(20,0,0,20));
        return  paneButton;
    }

    public static void FramePerformedWork(){
        //метод создающий окно с выполненными работами
        //кнопки+листенеры+панель с таблицей
        JFrame frame=new JFrame();
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новая запись");
        JButton delete=new JButton("Удалить запись");
        JButton change=new JButton("Редактировать запись");
        JButton home=new JButton("На главную");
        add.setBackground(new Color(93,222,211));
        delete.setBackground(new Color(93,222,211));
        change.setBackground(new Color(93,222,211));
        home.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog.addWork();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableWork.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int [] i=tableWork.getSelectedRows();
                    int [] id = new int[i.length];
                    for (int j = 0; j < i.length; j++) {
                        id[j]= (int) TMWork.getValueAt(i[j],0);
                    }
                    TMWork.deleteRow(id);
                }
            }
        });
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableWork.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    if (tableWork.getSelectedRowCount()>1) {
                        JOptionPane.showMessageDialog(buttonPanel,
                                " Можно выбрать только одну запись",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        MyDialog.changeWork();
                    }
                }
            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                f.setVisible(true);
            }
        });

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(change);
        buttonPanel.add(home);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.add(buttonPanel,BorderLayout.NORTH);
        panel.add(new JScrollPane(tableWork),BorderLayout.CENTER);//добавление таблицы в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        frame.add(panel);
        frame.setTitle("Выполненные работы");
        frame.setSize(1200,450);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void FrameRecord(){
        //метод создающий окно с выполненными работами
        //кнопки+листенеры+панель с таблицей
        JFrame frame=new JFrame();
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новая запись");
        JButton delete=new JButton("Удалить запись");
        JButton change=new JButton("Редактировать запись");
        JButton home=new JButton("На главную");
        add.setBackground(new Color(93,222,211));
        delete.setBackground(new Color(93,222,211));
        change.setBackground(new Color(93,222,211));
        home.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog.addRecord();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableRecord.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int [] i=tableRecord.getSelectedRows();
                    int [] id = new int[i.length];
                    for (int j = 0; j < i.length; j++) {
                        id[j]= (int) TMRecord.getValueAt(i[j],0);
                    }
                    TMRecord.deleteRow(id);
                }
            }
        });
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableRecord.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    if (tableRecord.getSelectedRowCount()>1) {
                        JOptionPane.showMessageDialog(buttonPanel,
                                " Можно выбрать только одну запись",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        MyDialog.changeRecord();
                    }
                }
            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                f.setVisible(true);
            }
        });

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(change);
        buttonPanel.add(home);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.add(buttonPanel,BorderLayout.NORTH);
        panel.add(new JScrollPane(tableRecord),BorderLayout.CENTER);//добавление таблицы в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        frame.add(panel);
        frame.setTitle("Все записи");
        frame.setSize(1200,500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void FrameRecordMonth(){
        //метод создающий окно с выполненными работами
        //кнопки+листенеры+панель с таблицей
        JFrame frame=new JFrame();
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новая запись");
        JButton delete=new JButton("Удалить запись");
        JButton change=new JButton("Редактировать запись");
        JButton home=new JButton("На главную");
        add.setBackground(new Color(93,222,211));
        delete.setBackground(new Color(93,222,211));
        change.setBackground(new Color(93,222,211));
        home.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MyDialog.addRecord();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               /* if (tableRecord.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int [] i=tableRecord.getSelectedRows();
                    int [] id = new int[i.length];
                    for (int j = 0; j < i.length; j++) {
                        id[j]= (int) TMRecord.getValueAt(i[j],0);
                    }
                    TMRecord.deleteRow(id);
                }*/
            }
        });
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (tableServices.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    MyDialog.changeServices();
                }*/
            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                f.setVisible(true);
            }
        });

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(change);
        buttonPanel.add(home);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.add(buttonPanel,BorderLayout.NORTH);
        panel.add(new JScrollPane(tableRecord),BorderLayout.CENTER);//добавление таблицы в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        frame.add(panel);
        frame.setTitle("Все записи");
        frame.setSize(900,500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void FrameRecordDay(){
        //метод создающий окно с выполненными работами
        //кнопки+листенеры+панель с таблицей
        JFrame frame=new JFrame();
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новая запись");
        JButton delete=new JButton("Удалить запись");
        JButton change=new JButton("Редактировать запись");
        JButton home=new JButton("На главную");
        add.setBackground(new Color(93,222,211));
        delete.setBackground(new Color(93,222,211));
        change.setBackground(new Color(93,222,211));
        home.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog.addRecord();
                TMRecordDay.update();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableRecordDay.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int [] i=tableRecordDay.getSelectedRows();
                    int [] id = new int[i.length];
                    for (int j = 0; j < i.length; j++) {
                        id[j]= (int) TMRecordDay.getValueAt(i[j],0);
                    }
                    TMRecordDay.deleteRow(id);
                }
            }
        });
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (tableServices.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    MyDialog.changeServices();
                }*/
            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                f.setVisible(true);
            }
        });

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(change);
        buttonPanel.add(home);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.add(buttonPanel,BorderLayout.NORTH);
        panel.add(new JScrollPane(tableRecordDay),BorderLayout.CENTER);//добавление таблицы в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        frame.add(panel);
        frame.setTitle("Записи на день");
        frame.setSize(1200,500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void FrameServices(){
        //метод создающий окно с услугами
        //кнопки+листенеры+панель с таблицей
        JFrame frame=new JFrame();
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новая услуга");
        JButton delete=new JButton("Удалить услугу");
        JButton change=new JButton("Редактировать услугу");
        JButton home=new JButton("На главную");
        add.setBackground(new Color(93,222,211));
        delete.setBackground(new Color(93,222,211));
        change.setBackground(new Color(93,222,211));
        home.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog.addServices();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableServices.getSelectedRow()==-1){
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
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableServices.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    if (tableServices.getSelectedRowCount()>1) {
                        JOptionPane.showMessageDialog(buttonPanel,
                                " Можно выбрать только одну запись",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        MyDialog.changeServices();
                    }
                }

            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                f.setVisible(true);
            }
        });

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(change);
        buttonPanel.add(home);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.add(buttonPanel,BorderLayout.NORTH);
        panel.add(new JScrollPane(tableServices),BorderLayout.CENTER);//добавление таблицы в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        frame.add(panel);
        frame.setTitle("Услуги");
        frame.setSize(600,450);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void FrameClients(){
        //метод создающий окно с клиентами
        //кнопки+листенеры+панель с таблицей
        JFrame frame=new JFrame();
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новый клиент");
        JButton delete=new JButton("Удалить клиента");
        JButton change=new JButton("Редактировать");
        JButton home=new JButton("На главную");
        add.setBackground(new Color(93,222,211));
        delete.setBackground(new Color(93,222,211));
        change.setBackground(new Color(93,222,211));
        home.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog.addCliens();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableClients.getSelectedRow()==-1){
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
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableClients.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    if (tableClients.getSelectedRowCount()>1) {
                        JOptionPane.showMessageDialog(buttonPanel,
                                " Можно выбрать только одну запись",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        MyDialog.changeCliens();
                    }
                }

            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                f.setVisible(true);
            }
        });

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(change);
        buttonPanel.add(home);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.add(buttonPanel,BorderLayout.NORTH);
        panel.add(new JScrollPane(tableClients),BorderLayout.CENTER);//добавление таблицы в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        frame.add(panel);
        frame.setTitle("Клиенты");
        frame.setSize(740,500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void FrameEmployee(){
        //метод создающий окно с сотрудниками
        //кнопки+листенеры+панель с таблицей
        JFrame frame=new JFrame();
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новый сотрудник");
        JButton delete=new JButton("Удалить сотрудника");
        JButton change=new JButton("Редактировать");
        JButton home=new JButton("На главную");
        add.setBackground(new Color(93,222,211));
        delete.setBackground(new Color(93,222,211));
        change.setBackground(new Color(93,222,211));
        home.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog.addEmployee();
            }
        });
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
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableEmployee.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    if (tableEmployee.getSelectedRowCount()>1) {
                        JOptionPane.showMessageDialog(buttonPanel,
                                " Можно выбрать только одну запись",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        MyDialog.changeEmployee();
                    }
                }

            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                f.setVisible(true);
            }
        });

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(change);
        buttonPanel.add(home);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.add(buttonPanel,BorderLayout.NORTH);
        panel.add(new JScrollPane(tableEmployee),BorderLayout.CENTER);//добавили таблицу в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        frame.add(panel);
        frame.setSize(850,500);
        frame.setTitle("Сотрудники");
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
