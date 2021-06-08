package com.company.View;

import com.company.Essence.Clients;
import com.company.Essence.Employee;
import com.company.Essence.Services;
import com.company.Essence.Record;
import com.company.Model.TableModelRecord;
import com.company.Model.UpdateTM;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FrameRecord extends JFrame {
    public static TableModelRecord TMRecord=new TableModelRecord();
    public static JTable tableRecord=new JTable(TMRecord);
    public FrameRecord(){
        tableRecord.removeColumn(tableRecord.getColumnModel().getColumn(0));
        /*tableRecord.getColumnModel().getColumn(0).setPreferredWidth(30);
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
        tableRecord.getColumnModel().getColumn(11).setPreferredWidth(100);*/

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
        add.setBackground(new Color(176,224,230));
        delete.setBackground(new Color(176,224,230));
        change.setBackground(new Color(176,224,230));
        home.setBackground(new Color(176,224,230));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecord();
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
                        changeRecord();
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
        panel.add(new JScrollPane(tableRecord),BorderLayout.CENTER);//добавление таблицы в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                MainFrame.f.setVisible(true);
            }
        });
        frame.add(panel);
        frame.setTitle("Все записи");
        frame.setSize(1200,500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    public static void addRecord() {
        DefaultComboBoxModel DCBMServices = new DefaultComboBoxModel();
        for (int i = 0; i < FrameServices.TMServices.getRowCount(); i++) {
            String s=FrameServices.TMServices.getValueAt(i,0)+"  "+FrameServices.TMServices.getValueAt(i,1)+" р.";
            DCBMServices.addElement(s);
        }
        JComboBox CBServices = new JComboBox(DCBMServices);
        CBServices.setSelectedIndex(0);

        DefaultComboBoxModel DCBMClients = new DefaultComboBoxModel();
        for (int i = 0; i < FrameClients.TMClients.getRowCount(); i++) {
            String s=FrameClients.TMClients.getValueAt(i,1)+"  "+FrameClients.TMClients.getValueAt(i,2)+"  "+FrameClients.TMClients.getValueAt(i,3);
            DCBMClients.addElement(s);
        }
        JComboBox CBClients = new JComboBox(DCBMClients);
        CBClients.setSelectedIndex(0);

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
        ftfDate.setColumns(32);
        ftfDate.setValue(new Date());

        // Форматирующий объект даты
        DateFormatter timeFormatter = new DateFormatter(new SimpleDateFormat("HH:mm"));
        timeFormatter.setAllowsInvalid(false);
        timeFormatter.setOverwriteMode(true);
        // Создание форматированного текстового поля даты
        JFormattedTextField ftfTime = new JFormattedTextField(timeFormatter);
        ftfTime.setColumns(32);
        ftfTime.setValue(new Date());

        JPanel panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.add(new JLabel("Услуга:"));
        panel.add(new JScrollPane(CBServices));

        JButton addClients=new JButton("Добавить клиента");
        addClients.setBackground(new Color(176,224,230));
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
        JTextField comment=new JTextField();
        panel.add(comment);

        JDialog dialog=new JDialog();

        JButton add=new JButton("Добавить");
        add.setBackground(new Color(176,224,230));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date=new Date();
                Calendar calendar = new GregorianCalendar(Integer.valueOf(String.valueOf(ftfDate.getText()).substring(6,10) ),
                        Integer.valueOf(String.valueOf(ftfDate.getText()).substring(3,5) )-1 ,
                        Integer.valueOf(String.valueOf(ftfDate.getText()).substring(0,2) ),
                        Integer.valueOf(String.valueOf(ftfTime.getText()).substring(0,2) ),
                        Integer.valueOf(String.valueOf(ftfTime.getText()).substring(3,5) )
                );
                Date date2=calendar.getTime();
                if(date.after(date2)){
                    JOptionPane.showMessageDialog(dialog,
                            " Нельзя записывать на прошлое !",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }else {
                    TMRecord.addRow(new Record(
                            FrameServices.TMServices.getRow(String.valueOf(FrameServices.TMServices.getValueAt(CBServices.getSelectedIndex(), 0))),
                            FrameClients.TMClients.getRow(Integer.valueOf(String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(), 0)))),
                            FrameEmployee.TMEmployee.getRow(Integer.valueOf(String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(), 0)))),
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
            }
        });

        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        p.add(panel,BorderLayout.CENTER);
        p.add(add,BorderLayout.SOUTH);
        p.setBackground(Color.white);
        p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        dialog.setTitle("Новая запись");
        dialog.setLayout(new BorderLayout());
        dialog.add(p);
        dialog.setModal(true);
        dialog.setSize(1000,450);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void changeRecord() {
        int index=-1;
        DefaultComboBoxModel DCBMServices = new DefaultComboBoxModel();
        for (int i = 0; i < FrameServices.TMServices.getRowCount(); i++) {
            String s=FrameServices.TMServices.getValueAt(i,0)+"  "+FrameServices.TMServices.getValueAt(i,1)+" р.";
            DCBMServices.addElement(s);
            String str=TMRecord.getValueAt(tableRecord.getSelectedRow(),1)+"  "+TMRecord.getValueAt(tableRecord.getSelectedRow(),2)+" р.";
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
            String str=TMRecord.getValueAt(tableRecord.getSelectedRow(),3)+"  "+TMRecord.getValueAt(tableRecord.getSelectedRow(),4)+"  "+TMRecord.getValueAt(tableRecord.getSelectedRow(),5);
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
            String str=TMRecord.getValueAt(tableRecord.getSelectedRow(),6)+"  "+TMRecord.getValueAt(tableRecord.getSelectedRow(),7)+"  "+TMRecord.getValueAt(tableRecord.getSelectedRow(),8);
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
        Calendar calendar = new GregorianCalendar(Integer.valueOf(String.valueOf(tableRecord.getValueAt(tableRecord.getSelectedRow(),9)).substring(6,10) ),
                Integer.valueOf(String.valueOf(tableRecord.getValueAt(tableRecord.getSelectedRow(),9)).substring(3,5) )-1 ,
                Integer.valueOf(String.valueOf(tableRecord.getValueAt(tableRecord.getSelectedRow(),9)).substring(0,2) ),
                Integer.valueOf(String.valueOf(tableRecord.getValueAt(tableRecord.getSelectedRow(),10)).substring(0,2) ),
                Integer.valueOf(String.valueOf(tableRecord.getValueAt(tableRecord.getSelectedRow(),10)).substring(3,5) )
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
        addClients.setBackground(new Color(176,224,230));
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
        JTextField comment=new JTextField();
        comment.setText(String.valueOf(tableRecord.getValueAt(tableRecord.getSelectedRow(),11)));
        panel.add(comment);

        JDialog dialog=new JDialog();

        JButton add=new JButton("Изменить");
        add.setBackground(new Color(176,224,230));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TMRecord.changeRow(Integer.parseInt(String.valueOf(TMRecord.getValueAt(tableRecord.getSelectedRow(),0))),new Record(
                        FrameServices.TMServices.getRow(String.valueOf(FrameServices.TMServices.getValueAt(CBServices.getSelectedIndex(),0))),
                        FrameClients.TMClients.getRow(Integer.valueOf(String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),0)))),
                        FrameEmployee.TMEmployee.getRow(Integer.valueOf(String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),0)))),
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

        dialog.setTitle("Редактирование записи");
        dialog.setLayout(new BorderLayout());
        dialog.add(p);
        dialog.setModal(true);
        dialog.setSize(1000,450);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
