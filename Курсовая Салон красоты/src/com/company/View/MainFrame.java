package com.company.View;

import com.company.Essence.Clients;
import com.company.Essence.Employee;
import com.company.Essence.Record;
import com.company.Essence.Services;
import com.company.Model.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;
import java.awt.Font;
import java.util.List;

public class MainFrame extends JFrame {

    public static JFrame f=new JFrame();
    public static Color c=new Color(255,255,255);
    public static Color c2=new Color(176,224,230);

    //конструктор
    public MainFrame(){
        RewardsProgram.init();
        JButton button10=new JButton("Бонусная программа");
        JButton button12=new JButton("Отчеты");
        JButton button=new JButton("Log");
        button10.setBackground(c2);
        button12.setBackground(c2);
        button.setBackground(c2);
        button10.setBorderPainted(false);
        button12.setBorderPainted(false);
        button.setBorderPainted(false);
        button10.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 15));
        button12.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 15));
        button.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 15));
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RewardsProgram.DialogRewardsPrograms();
            }
        });
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameReport fr=new FrameReport();
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Сancellation.DialogСancellation();
            }
        });
        JPanel b=new JPanel(new FlowLayout());
        b.setBackground(c2);
        b.add(button10);
        b.add(button12);
        b.add(button);
        //панель верхняя
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(c2);

        String text = "EasyBeauty";
        Font font = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 25);
        JLabel Label = new JLabel(text);
        Label.setHorizontalAlignment(SwingConstants.CENTER);
        Label.setFont(font);
        titlePanel.add(Label,BorderLayout.CENTER);

        titlePanel.add(b,BorderLayout.EAST);
        //панель которая кладется в frame , сделано что бы можно было редактироваь отступы,цвета
        JPanel main=new JPanel();
        main.setLayout(new BorderLayout());
        main.add(CreatePanelButton(),BorderLayout.CENTER);//панель с кнопками
        main.add( titlePanel,BorderLayout.NORTH);//панель верхняя
        main.add(TimerPanel(),BorderLayout.EAST);//панель справа
        main.setBackground(Color.white);
        main.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        
        f.add(main, BorderLayout.CENTER);
        f.setSize( 1200,700);
        f.setVisible(true);
        f.setTitle("EasyBeauty");
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setLocationRelativeTo(null);
        f.setResizable(false);
    }

    private static JPanel TimerPanel(){

        //панель справа с уведомлениями
        JPanel actions=new JPanel();

        JPanel Notices=new JPanel();
        Notices.setLayout(new BoxLayout(Notices,BoxLayout.Y_AXIS));
        Notices.setBackground(Color.white);

        JPanel bithday=new JPanel();
        bithday.setLayout(new BoxLayout(bithday,BoxLayout.Y_AXIS));
        bithday.setBackground(c);

        Timer timer = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //часть для день рождений
                Date current = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                bithday.removeAll();
                String dayMain = formatter.format(current);
                bithday.add(new JLabel("          "+dayMain));
                DBWorker.initDB();
                List<Clients> l=DBWorker.selectBithday();
                List<Employee> lE=DBWorker.selectBithdayEmployee();
                DBWorker.closeDB();
                for (Clients c:l) {
                    bithday.add(new JLabel("Клиент: "+c.getSurname()+" "+c.getName()+" "+c.getPhone()+"\n"));
                }
                for (Employee e:lE) {
                    bithday.add(new JLabel("Сотрудник: "+e.getSurname()+" "+e.getName()+ "\n"));
                }
                bithday.repaint();
                bithday.revalidate();
                //часть для уведомлений
                Notices.removeAll();
                //Notices.add(new JLabel("Уведомления:"));
                DBWorker.initDB();
                List<Record> list=DBWorker.selectNotices();
                Notices.add(new JLabel("        Скоро начнется запись:      "));
                DBWorker.closeDB();
                for (Record r:list) {
                    String s=" "+r.getTime()+"   "+r.getServices().getName()+"  Телефон: "+FrameClients.TMClients.selectRowPhone(r.getClients().getId());
                    Notices.add(new JLabel(s));
                }
                Notices.repaint();
                Notices.revalidate();


                //часть для удаления старых записей
                Date date = new Date();
                for (int i = 0; i < FrameRecord.TMRecord.getRowCount(); i++) {
                    String d=String.valueOf(FrameRecord.TMRecord.getValueAt(i,9));
                    String t=String.valueOf(FrameRecord.TMRecord.getValueAt(i,10));
                    Calendar calendar=new GregorianCalendar(
                            Integer.parseInt(d.substring(6,10)),
                            Integer.parseInt(d.substring(3,5))-1,
                            Integer.parseInt(d.substring(0,2)),
                            Integer.parseInt(t.substring(0,2)),
                            Integer.parseInt(t.substring(3,5))
                    );
                    Date date1 = calendar.getTime();
                    if(date.after(date1)){
                        FrameRecord.TMRecord.deleteRow(new int[]{Integer.parseInt(String.valueOf(FrameRecord.TMRecord.getValueAt(i,0)))});
                    }
                }
            }
        });
        timer.start();

        Font font2 = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 18);
        JPanel pane=new JPanel(new BorderLayout());
        TitledBorder t32=new TitledBorder("Уведомления");
        t32.setTitleFont(font2);
        pane.setBorder(t32);
        pane.setBackground(c);
        JScrollPane jsp=new JScrollPane(Notices);
        pane.add(jsp,BorderLayout.CENTER);


        JPanel panel=new JPanel(new BorderLayout());
        TitledBorder t3=new TitledBorder("Дни рождения");
        t3.setTitleFont(font2);
        panel.setBorder(t3);
        panel.setBackground(c);
        JScrollPane jsp2=new JScrollPane(bithday);
        //jsp2.setSize(new Dimension(100,300));
        panel.add(jsp2,BorderLayout.CENTER);
        //bithday.setBackground(c2);

        actions.setLayout(new GridLayout(2, 1, 20, 20));

        actions.add(panel);
        actions.add(pane);
        actions.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        actions.setPreferredSize(new Dimension(450,500));
        actions.setBackground(c2);
        return actions;
    }

    private static JPanel CreatePanelButton(){
        //метод возвращающий созданную  панели с кнопками
        //обьявление кнопок+листенеры+добавление
        Font font = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 15);
        JButton button1=new JButton("Клиенты");
        JButton button2=new JButton("Новый клиент");
        JButton button3=new JButton("Выполненные работы");
        JButton button4=new JButton("Оказание услуги");
        JButton button5=new JButton("Записи на день");
        JButton button6=new JButton("Сделать запись");
        JButton button7=new JButton("Услуги");
        JButton button8=new JButton("Сотрудники");
        JButton button9=new JButton("Все записи");
        JButton button13=new JButton("Новый сотрудник");
            button1.setFont(font);
            button2.setFont(font);
            button3.setFont(font);
            button4.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 15));
            button5.setFont(font);
            button6.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 15));
            button7.setFont(font);
            button8.setFont(font);
            button9.setFont(font);
            button13.setFont(font);
            button1.setBackground(c2);
            button2.setBackground(c2);
            button3.setBackground(c2);
            button4.setBackground(c2);
            button5.setBackground(c2);
            button6.setBackground(c2);
            button7.setBackground(c2);
            button8.setBackground(c2);
            button9.setBackground(c2);
            button13.setBackground(c2);
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FrameClients fr=new FrameClients();
                    f.dispose();
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    FrameClients.addCliens();
                }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FramePerformedWork fr=new FramePerformedWork();
                    f.dispose();
                }
            });
            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DialogAddPW d=new DialogAddPW();
                }
            });
            button5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FrameRecordDay fr=new FrameRecordDay();
                    f.dispose();
                }
            });
            button6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FrameRecord.addRecord();
                }
            });
            button7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FrameServices fr=new FrameServices();
                    f.dispose();
                }
            });
            button8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FrameEmployee fr=new FrameEmployee();
                    f.dispose();
                }
            });
            button9.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FrameRecord fr=new FrameRecord();
                    f.dispose();
                }
            });
            button13.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FrameEmployee.addEmployee();
                }
            });

        Font font2 = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 18);
        JPanel paneWork=new JPanel(new BorderLayout());
        TitledBorder t3=new TitledBorder("Услуги");
        t3.setTitleFont(font2);
        paneWork.setBorder(t3);
        paneWork.setBackground(c);
        JPanel p4=new JPanel(new GridLayout(3,1,20,20));
        p4.setBorder(new EmptyBorder(10,15,10,15));
        p4.setBackground(c);
        p4.add(button4);
        p4.add(button3);
        p4.add(button7);
        paneWork.add(p4,BorderLayout.CENTER);

        JPanel paneRecord=new JPanel(new BorderLayout());
        paneRecord.setBackground(c);
        TitledBorder t=new TitledBorder("Записи");
        t.setTitleFont(font2);
        paneRecord.setBorder(t);
        JPanel p3=new JPanel(new GridLayout(3,1,20,20));
        p3.setBorder(new EmptyBorder(10,15,10,15));
        p3.setBackground(c);
        p3.add(button6);
        p3.add(button5);
        p3.add(button9);
        paneRecord.add(p3,BorderLayout.CENTER);


        JPanel paneClients=new JPanel(new BorderLayout());
        TitledBorder t1=new TitledBorder("Клиенты");
        t1.setTitleFont(font2);
        paneClients.setBorder(t1);
        paneClients.setBackground(c);
        JPanel p2=new JPanel(new GridLayout(1,2,20,20));
        p2.setBackground(c);
        p2.setBorder(new EmptyBorder(10,15,10,15));
        p2.add(button1);
        p2.add(button2);
        paneClients.add(p2,BorderLayout.CENTER);

        JPanel paneEmpl=new JPanel(new BorderLayout());
        TitledBorder t2=new TitledBorder("Сотрудники");
        t2.setTitleFont(font2);
        paneEmpl.setBorder(t2);
        paneEmpl.setBackground(c);
        JPanel p=new JPanel(new GridLayout(1,2,20,20));
        p.setBackground(c);
        p.setBorder(new EmptyBorder(10,15,10,15));
        p.add(button8);
        p.add(button13);
        paneEmpl.add(p,BorderLayout.CENTER);

        JPanel paneTop=new JPanel(new GridLayout(1,2,20,20));
        paneTop.setBackground(c);
        paneTop.add(paneRecord);
        paneTop.add(paneWork);

        JPanel paneLow=new JPanel(new GridLayout(2,1,20,20));
        paneLow.setPreferredSize(new Dimension(300,250));
        paneLow.setBackground(c);
        paneLow.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        paneLow.add(paneClients);
        paneLow.add(paneEmpl);


        JPanel paneButton=new JPanel();
        paneButton.setLayout( new BorderLayout());
        paneButton.add(paneTop,BorderLayout.CENTER);
        paneButton.add(paneLow,BorderLayout.SOUTH);
        paneButton.setBackground(c);
        paneButton.setBorder(BorderFactory.createEmptyBorder(20,0,0,20));
        return  paneButton;
    }


}
