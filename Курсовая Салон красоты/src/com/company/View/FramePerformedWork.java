package com.company.View;

import com.company.Essence.Clients;
import com.company.Essence.Employee;
import com.company.Essence.Services;
import com.company.Essence.Work;
import com.company.Model.TableModelPerformedWork;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FramePerformedWork extends JFrame {
    public static  JPanel main=new JPanel();
    public static TableModelPerformedWork TMWork=new TableModelPerformedWork();
    public static JTable tableWork=new JTable(TMWork);
    public static float pointsClient;
    public static float pointsLaterPay;
    public static float income ;
    public static float price ;
    public static float usePoint ;
    public static JLabel pointC=new JLabel();
    public static JLabel pointLP=new JLabel();
    public static JLabel pr=new JLabel();
    public static JLabel in=new JLabel();
    public static JLabel useP=new JLabel();


    public FramePerformedWork(){
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
                addWork();
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
                        changeWork();
                    }
                }
            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainFrame.f.setVisible(true);
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

    public static void update(){

        income=price-usePoint;
        float t=income*RewardsProgram.getPercent()/100;
        pointsLaterPay=income*RewardsProgram.getPercent()/100;

        pointC.setText("Баллы клиента:  "+pointsClient);
        pointLP.setText("Баллов начислиться:"+pointsLaterPay);
        in.setText("Доход: "+income+" p.");
        pr.setText("Стоимость:   "+price+"  р.");

        main.repaint();
        main.revalidate();
    }

    public static void addWork() {

        DefaultComboBoxModel DCBMServices = new DefaultComboBoxModel();
        for (int i = 0; i < FrameServices.TMServices.getRowCount(); i++) {
            String s= String.valueOf( FrameServices.TMServices.getValueAt(i,0));
            DCBMServices.addElement(s);
        }
        JComboBox CBServices = new JComboBox(DCBMServices);

        CBServices.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                price = Float.parseFloat(String.valueOf( FrameServices.TMServices.getValueAt(CBServices.getSelectedIndex(),1)));
                update();
            }
        });
        //CBServices.setSelectedIndexnull);

        DefaultComboBoxModel DCBMClients = new DefaultComboBoxModel();
        for (int i = 0; i < FrameClients.TMClients.getRowCount(); i++) {
            String s=FrameClients.TMClients.getValueAt(i,1)+"  "+FrameClients.TMClients.getValueAt(i,2)+"  "+FrameClients.TMClients.getValueAt(i,3);
            DCBMClients.addElement(s);
        }
        JComboBox CBClients = new JComboBox(DCBMClients);
        CBClients.setSelectedIndex(0);
        CBClients.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                pointsClient=Float.parseFloat(String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),7)));
                update();
            }
        });

        DefaultComboBoxModel DCBMEmployee = new DefaultComboBoxModel();
        for (int i = 0; i < FrameEmployee.TMEmployee.getRowCount(); i++) {
            String s=FrameEmployee.TMEmployee.getValueAt(i,5)+"  "+FrameEmployee.TMEmployee.getValueAt(i,1)+"  "+FrameEmployee.TMEmployee.getValueAt(i,2)+"  "+FrameEmployee.TMEmployee.getValueAt(i,3);
            DCBMEmployee.addElement(s);
        }
        JComboBox CBEmployee = new JComboBox(DCBMEmployee);
        CBEmployee.setSelectedIndex(0);

        // Форматирующий объект даты
        DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("dd.MM.yyyy"));
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);
        // Создание форматированного текстового поля даты
        JFormattedTextField ftfDate = new JFormattedTextField(dateFormatter);
        ftfDate.setColumns(10);
        ftfDate.setValue(new Date());

        // Форматирующий объект даты
        DateFormatter timeFormatter = new DateFormatter(new SimpleDateFormat("HH:mm"));
        timeFormatter.setAllowsInvalid(false);
        timeFormatter.setOverwriteMode(true);
        // Создание форматированного текстового поля даты
        JFormattedTextField ftfTime = new JFormattedTextField(timeFormatter);
        ftfTime.setColumns(6);
        ftfTime.setValue(new Date());

        JPanel pM1=new JPanel(new FlowLayout());
        pM1.add(new JLabel("Услуга:  "));
        pM1.add(new JScrollPane(CBServices));

        JPanel pM2=new JPanel(new FlowLayout());
        pM2.add(new JLabel("Клиент:  "));
        pM2.add(new JScrollPane(CBClients));

        JPanel pM5=new JPanel(new FlowLayout());
        pM5.add(new JLabel("Сотрудник:  "));
        pM5.add(new JScrollPane(CBEmployee));

        JPanel pM6=new JPanel(new FlowLayout());
        pM6.add(new JLabel("Дата: "));
        pM6.add(ftfDate);
        pM6.add(new JLabel("  Время: "));
        pM6.add(ftfTime);

        JPanel pM8=new JPanel(new FlowLayout());
        pM8.add(new JLabel("Комментарий:  "));
        JTextField comment=new JTextField();
        comment.setColumns(15);
        pM8.add(comment);

        JPanel panelMain=new JPanel();
        panelMain.setBackground(Color.white);
        panelMain.setLayout(new GridLayout(6, 1, 0, 0));

        JButton addClients=new JButton("Новый клиент");
        addClients.setBackground(new Color(93,222,211));
        addClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameClients.addCliens();
                DCBMClients.removeAllElements();
                for (int i = 0; i < FrameClients.TMClients.getRowCount(); i++) {
                    String s=FrameClients.TMClients.getValueAt(i,1)+"  "+FrameClients.TMClients.getValueAt(i,2)+"  "+FrameClients.TMClients.getValueAt(i,3);
                    DCBMClients.addElement(s);
                }
                CBClients.setSelectedIndex(DCBMClients.getSize()-1);
            }
        });
        JPanel pM3=new JPanel(new FlowLayout());
        pM3.add(addClients);

        {
            pM1.setBackground(Color.white);
            pM2.setBackground(Color.white);
            pM3.setBackground(Color.white);
            pM5.setBackground(Color.white);
            pM6.setBackground(Color.white);
            pM8.setBackground(Color.white);

        }
        panelMain.add(pM1);
        panelMain.add(pM2);
        panelMain.add(pM3);
        panelMain.add(pM5);
        panelMain.add(pM6);
        panelMain.add(pM8);


        JDialog dialog=new JDialog();

        JPanel panelAdd=new JPanel(new FlowLayout());
        panelAdd.setBackground(Color.white);
        JButton add=new JButton("Сохранить");
        add.setBackground(new Color(180,240,235));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TMWork.addRow(new Work(
                        new Services(
                                String.valueOf(FrameServices.TMServices.getValueAt(CBServices.getSelectedIndex(),0)),
                                Float.parseFloat(String.valueOf(FrameServices.TMServices.getValueAt(CBServices.getSelectedIndex(),1)))),
                        usePoint,
                        income,
                        new Clients(
                                Integer.valueOf(String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),0))),
                                String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),1)),
                                String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),2)),
                                String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),3)),
                                String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),4)),
                                String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),5)),
                                String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),6)),
                                Float.valueOf(String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),7)))
                        ),
                        new Employee(
                                Integer.parseInt(String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),0))),
                                String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),1)),
                                String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),2)),
                                String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),3)),
                                String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),4)),
                                String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),5)),
                                String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),6))),
                        ftfDate.getText(),
                        ftfTime.getText(),
                        comment.getText())
                );

                dialog.dispose();
                JOptionPane.showMessageDialog(dialog,
                        " Запись успешно добавлена!",
                        "Уведомление",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panelAdd.add(add);


        JPanel panelMainPay =new JPanel(new GridLayout(5, 1, 0, 0));
        JPanel pM4=new JPanel(new FlowLayout());
        pointC.setText("Баллы клиента:  "+pointsClient);
        pM4.add(pointC);
        JPanel p1=new JPanel(new FlowLayout());
        pr.setText("Стоимость:   "+price+"  р.");
        p1.add(pr);

        JPanel p3=new JPanel(new FlowLayout());
        p3.setBackground(Color.white);
        JCheckBox cb=new JCheckBox("Баллы ");
        cb.setHorizontalTextPosition(JCheckBox.LEFT);
        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if(price * RewardsProgram.getPercentDiscount()/100 <= pointsClient){
                        usePoint= Float.parseFloat(String.valueOf(price * RewardsProgram.getPercentDiscount()/100));
                        useP.setText("   Использовать "+ usePoint+" баллов");
                    }
                    else{
                        usePoint=pointsClient;
                        useP.setText("   Использовать "+ pointsClient+" баллов ");
                    }
                    update();
                    p3.repaint();
                    p3.revalidate();
                } else {
                    usePoint=0;
                    update();
                    useP.setText("   Использовать "+0+" баллов ");
                    p3.repaint();
                    p3.revalidate();
                }
            }
        });
        p3.add(cb);
        p3.add(useP);

        JPanel p2=new JPanel(new FlowLayout());
        pointLP.setText("Баллов начислиться:"+pointsLaterPay);
        p2.add(pointLP);

        JPanel p5=new JPanel(new FlowLayout());
        in.setText("Доход: "+income+" p.");
        p5.add(in);

        pM4.setBackground(Color.white);
        p1.setBackground(Color.white);
        p2.setBackground(Color.white);
        p3.setBackground(Color.white);
        p5.setBackground(Color.white);
        panelMainPay.add(pM4);
        panelMainPay.add(p1);
        panelMainPay.add(p2);
        panelMainPay.add(p3);
        panelMainPay.add(p5);

        main.setLayout(new BorderLayout());
        main.add(panelMain,BorderLayout.NORTH);
        main.add(panelMainPay,BorderLayout.CENTER);
        main.add(panelAdd,BorderLayout.SOUTH);
        main.setBackground(Color.white);
        main.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        dialog.setTitle("Новая выполненная работа");
        dialog.setLayout(new BorderLayout());
        dialog.add(main);
        dialog.setModal(true);
        dialog.setSize(650,500);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    public static void changeWork() {
        int index=-1;
        DefaultComboBoxModel DCBMServices = new DefaultComboBoxModel();
        for (int i = 0; i < FrameServices.TMServices.getRowCount(); i++) {
            String s=FrameServices.TMServices.getValueAt(i,1)+"  "+FrameServices.TMServices.getValueAt(i,2)+" р.";
            DCBMServices.addElement(s);
            String str= (String) TMWork.getValueAt(tableWork.getSelectedRow(),1);
            if (s.equals(str)){
                index=i;
            }
        }
        JComboBox CBServices = new JComboBox(DCBMServices);
        CBServices.setSelectedIndex(index);

        DefaultComboBoxModel DCBMClients = new DefaultComboBoxModel();
        for (int i = 0; i < FrameClients.TMClients.getRowCount(); i++) {
            String s=FrameClients.TMClients.getValueAt(i,1)+"  "+FrameClients.TMClients.getValueAt(i,2)+"  "+FrameClients.TMClients.getValueAt(i,3);
            DCBMClients.addElement(s);
            String str=TMWork.getValueAt(tableWork.getSelectedRow(),3)+"  "+TMWork.getValueAt(tableWork.getSelectedRow(),4)+"  "+TMWork.getValueAt(tableWork.getSelectedRow(),5);
            if (s.equals(str)){
                index=i;
            }
        }
        JComboBox CBClients = new JComboBox(DCBMClients);
        CBClients.setSelectedIndex(index);

        DefaultComboBoxModel DCBMEmployee = new DefaultComboBoxModel();
        for (int i = 0; i < FrameEmployee.TMEmployee.getRowCount(); i++) {
            String s=FrameEmployee.TMEmployee.getValueAt(i,5)+"  "+FrameEmployee.TMEmployee.getValueAt(i,1)+"  "+FrameEmployee.TMEmployee.getValueAt(i,2)+"  "+FrameEmployee.TMEmployee.getValueAt(i,3);
            DCBMEmployee.addElement(s);
            String str=TMWork.getValueAt(tableWork.getSelectedRow(),6)+"  "+TMWork.getValueAt(tableWork.getSelectedRow(),7)+"  "+TMWork.getValueAt(tableWork.getSelectedRow(),8);
            if (s.equals(str)){
                index=i;
            }
        }
        JComboBox CBEmployee = new JComboBox(DCBMEmployee);
        CBEmployee.setSelectedIndex(index);

        // Форматирующий объект даты
        DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("dd.MM.yyyy"));
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);
        // Создание форматированного текстового поля даты
        JFormattedTextField ftfDate = new JFormattedTextField(dateFormatter);
        ftfDate.setColumns(32);
        ftfDate.setValue(new Date());
        Calendar calendar = new GregorianCalendar(Integer.valueOf(String.valueOf(tableWork.getValueAt(tableWork.getSelectedRow(),9)).substring(6,10) ),
                Integer.valueOf(String.valueOf(tableWork.getValueAt(tableWork.getSelectedRow(),9)).substring(3,5) )-1 ,
               Integer.valueOf(String.valueOf(tableWork.getValueAt(tableWork.getSelectedRow(),9)).substring(0,2) ),
                Integer.valueOf(String.valueOf(tableWork.getValueAt(tableWork.getSelectedRow(),10)).substring(0,2) ),
                Integer.valueOf(String.valueOf(tableWork.getValueAt(tableWork.getSelectedRow(),10)).substring(3,5) )
        );
        Date date1 = calendar.getTime();
        ftfDate.setValue(date1);

        // Форматирующий объект даты
        DateFormatter timeFormatter = new DateFormatter(new SimpleDateFormat("HH:mm"));
        timeFormatter.setAllowsInvalid(false);
        timeFormatter.setOverwriteMode(true);
        // Создание форматированного текстового поля даты
        JFormattedTextField ftfTime = new JFormattedTextField(timeFormatter);
        ftfTime.setColumns(32);
        ftfTime.setValue(date1);

        JPanel panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.add(new JLabel("Услуга:"));
        panel.add(new JScrollPane(CBServices));

        JButton addClients=new JButton("Добавить клиента");
        addClients.setBackground(new Color(93,222,211));
        addClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameClients.addCliens();
                DCBMClients.removeAllElements();
                for (int i = 0; i < FrameClients.TMClients.getRowCount(); i++) {
                    String s=FrameClients.TMClients.getValueAt(i,1)+"  "+FrameClients.TMClients.getValueAt(i,2)+"  "+FrameClients.TMClients.getValueAt(i,3);
                    DCBMClients.addElement(s);
                }
                CBClients.setSelectedIndex(DCBMClients.getSize()-1);
            }
        });
        JPanel pan=new JPanel(new BorderLayout());
        pan.setBackground(Color.white);
        pan.add(new JLabel("Клиент:"),BorderLayout.WEST);
        pan.add(addClients,BorderLayout.EAST);
        panel.add(pan);
        panel.add(new JScrollPane(CBClients));

        panel.add(new JLabel("Сотрудник:"));
        panel.add(new JScrollPane(CBEmployee));
        panel.add(new JLabel("Дата:"));
        panel.add(ftfDate);
        panel.add(new JLabel("Время:"));
        panel.add(ftfTime);
        panel.add(new JLabel("Комментарий:"));
        JTextField comment=new JTextField();comment.setText(String.valueOf(tableWork.getValueAt(tableWork.getSelectedRow(),11)));
        panel.add(comment);

        JDialog dialog=new JDialog();

        JButton add=new JButton("Изменить");
        add.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TMWork.changeRow(Integer.parseInt(String.valueOf(TMWork.getValueAt(tableWork.getSelectedRow(),0))),
                        new Work(
                        new Services(
                                String.valueOf(FrameServices.TMServices.getValueAt(CBServices.getSelectedIndex(),1)),
                                Float.valueOf(String.valueOf(FrameServices.TMServices.getValueAt(CBServices.getSelectedIndex(),2)))),
                        0,//Integer.parseInt(String.valueOf()),
                        Integer.parseInt(String.valueOf(FrameServices.TMServices.getValueAt(CBServices.getSelectedIndex(),2))),//Integer.parseInt(String.valueOf()),
                        new Clients(
                                Integer.valueOf(String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),0))),
                                String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),1)),
                                String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),2)),
                                String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),3)),
                                String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),4)),
                                String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),5)),
                                String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),6)),
                                Integer.parseInt(String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),7)))
                        ),
                        new Employee(
                                Integer.valueOf(String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),0))),
                                String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),1)),
                                String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),2)),
                                String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),3)),
                                String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),4)),
                                String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),5)),
                                String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),6))),
                        ftfDate.getText(),
                        ftfTime.getText(),
                        comment.getText())
                );
                dialog.dispose();
                JOptionPane.showMessageDialog(dialog,
                        " Запись успешно изменена!",
                        "Уведомление",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        p.add(panel,BorderLayout.CENTER);
        p.add(add,BorderLayout.SOUTH);
        p.setBackground(Color.white);
        p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        dialog.setTitle("Новая выполненная работа");
        dialog.setLayout(new BorderLayout());
        dialog.add(p);
        dialog.setModal(true);
        dialog.setSize(1000,450);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
