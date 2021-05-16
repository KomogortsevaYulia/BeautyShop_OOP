package com.company.View;

import com.company.Essence.Clients;
import com.company.Essence.Employee;
import com.company.Essence.Record;
import com.company.Essence.Services;
import com.company.Model.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.Font;
import java.util.List;

public class MainFrame extends JFrame {

    public static JFrame f=new JFrame();
    //конструктор
    public MainFrame(){
        RewardsProgram.init();
        //панель верхняя
        JPanel titlePanel = new JPanel();
        String text = "EasyBeauty";
        Font font = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 25);
        JLabel htmlLabel = new JLabel();
        htmlLabel.setText(text);
        htmlLabel.setFont(font);
        titlePanel.add(htmlLabel);
        titlePanel.setBackground(new Color(93,222,211));

        timer();
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
    }

    private static JPanel TimerPanel(){

        //панель справа с уведомлениями
        JPanel actions=new JPanel();

        JPanel Notices=new JPanel();
        Notices.setLayout(new FlowLayout());
        Notices.setBackground(Color.white);

        JPanel bithday=new JPanel();
        bithday.setLayout(new FlowLayout());
        bithday.setBackground(new Color(180,240,235));

        Timer timer = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //часть для день рождений
                Date current = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                bithday.removeAll();
                String dayMain = formatter.format(current);
                bithday.add(new JLabel("Сегодня( "+dayMain+" ) день рождение:"));
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
                Notices.add(new JLabel("Уведомления:"));
                DBWorker.initDB();
                List<Record> list=DBWorker.selectNotices();
                DBWorker.closeDB();
                for (Record r:list) {
                    Notices.add(new JLabel("Скоро начнется запись:"+r.getTime()+" "+r.getServices().getName()+" "+r.getClients().getPhone()));
                }
                Notices.repaint();
                Notices.revalidate();
            }
        });
        timer.start();

        actions.setLayout(new GridLayout(2, 1, 20, 20));
        actions.add(bithday);
        actions.add(Notices);
        actions.setBorder(BorderFactory.createEmptyBorder(20,10,0,0));
        actions.setPreferredSize(new Dimension(400,500));
        actions.setBackground(Color.white);
        return actions;
    }

    private static void timer(){
        Timer timer = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
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
    }

    private static JPanel CreatePanelButton(){
        //метод возвращающий созданную  панели с кнопками
        //обьявление кнопок+листенеры+добавление
        JButton button1=new JButton("Клиенты");
        JButton button2=new JButton("Новый клиент");
        JButton button3=new JButton("Выполненные работы");
        JButton button4=new JButton("Оказание услуги");
        JButton button5=new JButton("Записи на день");
        JButton button6=new JButton("Сделать запись");
        JButton button7=new JButton("Услуги");
        JButton button8=new JButton("Сотрудники");
        JButton button9=new JButton("Все записи");
        JButton button10=new JButton("Бонусная программа");
        JButton button11=new JButton("Настройки");
        JButton button12=new JButton("Отчеты");
        button1.setBackground(new Color(180,240,235));
        button2.setBackground(new Color(180,240,235));
        button3.setBackground(new Color(180,240,235));
        button4.setBackground(new Color(180,240,235));
        button5.setBackground(new Color(180,240,235));
        button6.setBackground(new Color(180,240,235));
        button7.setBackground(new Color(180,240,235));
        button8.setBackground(new Color(180,240,235));
        button9.setBackground(new Color(180,240,235));
        button10.setBackground(new Color(180,240,235));
        button11.setBackground(new Color(180,240,235));
        button12.setBackground(new Color(180,240,235));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameClients f=new FrameClients();
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
                FramePerformedWork.addWork();
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
                fr.setVisible(true);
                f.dispose();
            }
        });
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RewardsProgram.DialogRewardsPrograms();
            }
        });
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogSettings d=new DialogSettings();
                f.dispose();
            }
        });
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameReport fr=new FrameReport();
                f.dispose();
            }
        });
        JPanel paneButton=new JPanel();
        paneButton.setLayout( new GridLayout(4, 3, 10, 10));
        paneButton.add(button1);
        paneButton.add(button2);
        paneButton.add(button3);
        paneButton.add(button4);
        paneButton.add(button5);
        paneButton.add(button6);
        paneButton.add(button7);
        paneButton.add(button8);
        paneButton.add(button9);
        paneButton.add(button10);
        paneButton.add(button11);
        paneButton.add(button12);

        paneButton.setBackground(Color.white);
        paneButton.setBorder(BorderFactory.createEmptyBorder(20,0,0,20));
        return  paneButton;
    }
}
