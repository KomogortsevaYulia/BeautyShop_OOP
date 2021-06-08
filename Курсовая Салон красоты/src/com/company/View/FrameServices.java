package com.company.View;

import com.company.Essence.Services;
import com.company.Model.TableModelServices;
import com.company.Model.UpdateTM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameServices extends JFrame {
    public static TableModelServices TMServices=new TableModelServices();
    public static JTable tableServices=new JTable(TMServices);

    public FrameServices(){
        //метод создающий окно с услугами
        //кнопки+листенеры+панель с таблицей

        /*tableServices.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableServices.getColumnModel().getColumn(1).setPreferredWidth(200);*/

        JFrame frame=new JFrame();
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новая услуга");
        JButton delete=new JButton("Удалить услугу");
        JButton change=new JButton("Редактировать услугу");
        JButton home=new JButton("На главную");
        add.setBackground(new Color(176,224,230));
        delete.setBackground(new Color(176,224,230));
        change.setBackground(new Color(176,224,230));
        home.setBackground(new Color(176,224,230));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameServices.addServices();
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
                    String [] name = new String[i.length];
                    for (int j = 0; j < i.length; j++) {
                        name[j]= String.valueOf( TMServices.getValueAt(i[j],0));
                    }
                    TMServices.deleteRow(name);
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
                       FrameServices.changeServices();
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
        panel.add(new JScrollPane(tableServices),BorderLayout.CENTER);//добавление таблицы в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                MainFrame.f.setVisible(true);
            }
        });
        frame.add(panel);
        frame.setTitle("Услуги");
        frame.setSize(600,450);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    public static void addServices(){
        JDialog dialog=new JDialog();
        dialog.setTitle("Новая услуга");
        JPanel panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        JTextField name=new JTextField();
        JTextField price=new JTextField();
        panel.add(new JLabel("Название услуги:"));
        panel.add(name);
        panel.add(new JLabel("Стоимость:"));
        panel.add(price);

        JButton add=new JButton("Добавить");
        add.setBackground(new Color(176,224,230));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(Float.parseFloat(price.getText())<0){
                        JOptionPane.showMessageDialog(panel,
                                " В поле стоимость можжно вводить только положительные числа!",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                        price.setText("");
                    }
                    else {
                        boolean exist=TMServices.addRow(new Services(name.getText(), Float.parseFloat(price.getText())));
                        if (!exist) {
                            JOptionPane.showMessageDialog(dialog,
                                    " Уже существует такая услуга!",
                                    "Ошибка",
                                    JOptionPane.ERROR_MESSAGE);
                            dialog.dispose();
                            name.setText("");
                            price.setText("");
                        } else {
                            dialog.dispose();
                            name.setText("");
                            price.setText("");
                            JOptionPane.showMessageDialog(dialog,
                                    " Услуга успешно добавлена!",
                                    "Уведомление",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(panel,
                            " В поле стоимость можжно вводить только цифры!",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    price.setText("");
                }
            }
        });

        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        p.add(panel,BorderLayout.CENTER);
        p.add(add,BorderLayout.SOUTH);
        p.setBackground(Color.white);
        p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        dialog.setLayout(new BorderLayout());
        dialog.add(p);
        dialog.setModal(true);
        dialog.setSize(400,200);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void changeServices(){

        Services s=TMServices.getRow(String.valueOf(TMServices.getValueAt(tableServices.getSelectedRow(),0)));

        JDialog dialog=new JDialog();
        dialog.setTitle("Редактирование услуги");
        JPanel panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        JTextField name=new JTextField();
        name.setText(s.getName());
        JTextField price=new JTextField();
        price.setText(String.valueOf(s.getPrice()));
        panel.add(new JLabel("Название услуги:"));
        panel.add(name);
        panel.add(new JLabel("Стоимость:"));
        panel.add(price);

        JButton change=new JButton("Изменить");
        change.setBackground(new Color(176,224,230));
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(Float.parseFloat(price.getText())<0){
                        JOptionPane.showMessageDialog(panel,
                                " В поле стоимость можжно вводить только положительные числа!",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                        price.setText("");
                    }
                    else {
                        boolean exist = false;
                        int count = -1;
                        for (int i = 0; i < TMServices.getRowCount(); i++) {
                            if (
                                    name.getText().equals(String.valueOf(TMServices.getValueAt(i, 0))) &
                                            Float.parseFloat(price.getText()) == Float.parseFloat(String.valueOf(TMServices.getValueAt(i, 1)))
                            ) {
                                exist = true;
                            }
                        }
                        if (exist) {
                            JOptionPane.showMessageDialog(dialog,
                                    " Уже существует такая услуга!",
                                    "Ошибка",
                                    JOptionPane.ERROR_MESSAGE);
                            dialog.dispose();
                            name.setText("");
                            price.setText("");
                        } else {
                            TMServices.changeRow(s.getName(), new Services(name.getText(), Float.parseFloat(price.getText())));
                            dialog.dispose();
                            name.setText("");
                            price.setText("");
                        }
                    }
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(panel,
                            " В поле стоимость можно вводить только цифры!",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    price.setText("");
                }
            }
        });

        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        p.add(panel,BorderLayout.CENTER);
        p.add(change,BorderLayout.SOUTH);
        p.setBackground(Color.white);
        p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        dialog.setLayout(new BorderLayout());
        dialog.add(p);
        dialog.setModal(true);
        dialog.setSize(400,200);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
