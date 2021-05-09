package com.company.View;

import com.company.Essence.Clients;
import com.company.Essence.Employee;
import com.company.Essence.Record;
import com.company.Essence.Services;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyDialog {
    //класс для диалоговых окон,которые вылазят при добавление или редактирование записи
    public static void addCliens() {

        JDialog dialog=new JDialog();

        JPanel panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        JTextField surname=new JTextField();
        JTextField name=new JTextField();
        JTextField midllename=new JTextField();

        // Определение маски и поля ввода даты
        DateFormat date = new SimpleDateFormat("dd.MM");
        // Форматирующий объект даты
        DateFormatter dateFormatter = new DateFormatter(date);
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);
        // Создание форматированного текстового поля даты
        JFormattedTextField ftfDate = new JFormattedTextField(dateFormatter);
        ftfDate.setColumns(32);
        ftfDate.setValue(new Date());

        JTextField email=new JTextField();

        // Определение маски и содание поля ввода мобильного телефона
        MaskFormatter phoneFormatter = null;
        try {
            phoneFormatter = new MaskFormatter("+#-###-###-##-##");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        phoneFormatter.setPlaceholderCharacter('0');
        JFormattedTextField ftfPhone = new JFormattedTextField(phoneFormatter);
        ftfPhone.setColumns(16);

        panel.add(new JLabel("Фамилия:"));
        panel.add(surname);
        panel.add(new JLabel("Имя:"));
        panel.add(name);
        panel.add(new JLabel("Отчество:"));
        panel.add(midllename);
        panel.add(new JLabel("День рождения:"));
        panel.add(ftfDate);
        panel.add(new JLabel("Почта:"));
        panel.add(email);
        panel.add(new JLabel("Телефон:"));
        panel.add(ftfPhone);

        JButton add=new JButton("Добавить");
        add.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.TMClients.addRow(new Clients(
                        surname.getText(),
                        name.getText(),
                        midllename.getText(),
                        ftfDate.getText(),
                        email.getText(),
                        ftfPhone.getText()
                ));
                dialog.dispose();
                name.setText("");
                surname.setText("");
                midllename.setText("");
                email.setText("");
                JOptionPane.showMessageDialog(dialog,
                        " Клиент успешно добавлен!",
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

        dialog.setTitle("Новый клиент");
        dialog.setLayout(new BorderLayout());
        dialog.add(p);
        dialog.setModal(true);
        dialog.setSize(400,400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    public static void addEmployee(){
        JDialog dialog=new JDialog();

        JPanel panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        JTextField surname=new JTextField();
        JTextField name=new JTextField();
        JTextField midllename=new JTextField();

        // Определение маски и поля ввода даты
        DateFormat date = new SimpleDateFormat("dd.MM");
        // Форматирующий объект даты
        DateFormatter dateFormatter = new DateFormatter(date);
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);
        // Создание форматированного текстового поля даты
        JFormattedTextField ftfDate = new JFormattedTextField(dateFormatter);
        ftfDate.setColumns(32);
        ftfDate.setValue(new Date());

        JTextField post=new JTextField();

        // Определение маски и содание поля ввода мобильного телефона
        MaskFormatter phoneFormatter = null;
        try {
            phoneFormatter = new MaskFormatter("+#-###-###-##-##");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        phoneFormatter.setPlaceholderCharacter('0');
        JFormattedTextField ftfPhone = new JFormattedTextField(phoneFormatter);
        ftfPhone.setColumns(16);

        panel.add(new JLabel("Фамилия:"));
        panel.add(surname);
        panel.add(new JLabel("Имя:"));
        panel.add(name);
        panel.add(new JLabel("Отчество:"));
        panel.add(midllename);
        panel.add(new JLabel("День рождения:"));
        panel.add(ftfDate);
        panel.add(new JLabel("Почта:"));
        panel.add(post);
        panel.add(new JLabel("Телефон:"));
        panel.add(ftfPhone);

        JButton add=new JButton("Добавить");
        add.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.TMEmployee.addRow(new Employee(
                        surname.getText(),
                        name.getText(),
                        midllename.getText(),
                        ftfDate.getText(),
                        post.getText(),
                        ftfPhone.getText()
                ));
                dialog.dispose();
                name.setText("");
                surname.setText("");
                midllename.setText("");
                post.setText("");
                JOptionPane.showMessageDialog(dialog,
                        " Сотрудник успешно добавлен!",
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

        dialog.setTitle("Новый сотрудник");
        dialog.setLayout(new BorderLayout());
        dialog.add(p);
        dialog.setModal(true);
        dialog.setSize(400,400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
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
        add.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean exist=false;
                    int count=-1;
                    for (int i=0;i<MainFrame.TMServices.getRowCount();i++) {
                        if (
                                name.getText().equals(String.valueOf(MainFrame.TMServices.getValueAt(i, 1))) &
                                Float.parseFloat(price.getText())==Float.parseFloat(String.valueOf(MainFrame.TMServices.getValueAt(i, 2)))
                        ){
                            exist=true;
                            count=i;
                        }
                    }
                    if(exist){
                        JOptionPane.showMessageDialog(dialog,
                                " Уже существует такая услуга!",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                        dialog.dispose();
                        name.setText("");
                        price.setText("");
                    }else{
                        MainFrame.TMServices.addRow(new Services(name.getText(), Float.parseFloat(price.getText())));
                        dialog.dispose();
                        name.setText("");
                        price.setText("");
                        JOptionPane.showMessageDialog(dialog,
                                " Услуга успешно добавлена!",
                                "Уведомление",
                                JOptionPane.INFORMATION_MESSAGE);
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
    public static void addWork() {

        DefaultComboBoxModel DCBMServices = new DefaultComboBoxModel();
        for (int i = 0; i < MainFrame.TMServices.getRowCount(); i++) {
            String s=MainFrame.TMServices.getValueAt(i,1)+"  "+MainFrame.TMServices.getValueAt(i,2)+" р.";
            DCBMServices.addElement(s);
        }
        JComboBox CBServices = new JComboBox(DCBMServices);
        CBServices.setSelectedIndex(0);

        DefaultComboBoxModel DCBMClients = new DefaultComboBoxModel();
        for (int i = 0; i < MainFrame.TMClients.getRowCount(); i++) {
            String s=MainFrame.TMClients.getValueAt(i,1)+"  "+MainFrame.TMClients.getValueAt(i,2)+"  "+MainFrame.TMClients.getValueAt(i,3);
            DCBMClients.addElement(s);
        }
        JComboBox CBClients = new JComboBox(DCBMClients);
        CBClients.setSelectedIndex(0);

        DefaultComboBoxModel DCBMEmployee = new DefaultComboBoxModel();
        for (int i = 0; i < MainFrame.TMEmployee.getRowCount(); i++) {
            String s=MainFrame.TMEmployee.getValueAt(i,5)+"  "+MainFrame.TMEmployee.getValueAt(i,1)+"  "+MainFrame.TMEmployee.getValueAt(i,2)+"  "+MainFrame.TMEmployee.getValueAt(i,3);
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
        addClients.setBackground(new Color(93,222,211));
        addClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog.addCliens();
                DCBMClients.removeAllElements();
                for (int i = 0; i < MainFrame.TMClients.getRowCount(); i++) {
                    String s=MainFrame.TMClients.getValueAt(i,1)+"  "+MainFrame.TMClients.getValueAt(i,2)+"  "+MainFrame.TMClients.getValueAt(i,3);
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
        add.setBackground(new Color(180,240,235));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.TMWork.addRow(new Record(
                        new Services(
                               Integer.valueOf(String.valueOf(MainFrame.TMServices.getValueAt(CBServices.getSelectedIndex(),0))),
                               String.valueOf(MainFrame.TMServices.getValueAt(CBServices.getSelectedIndex(),1)),
                                Float.valueOf(String.valueOf(MainFrame.TMServices.getValueAt(CBServices.getSelectedIndex(),2)))),
                        new Clients(
                                Integer.valueOf(String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),0))),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),1)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),2)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),3)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),4)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),5)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),6))),
                        new Employee(
                                Integer.valueOf(String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),0))),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),1)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),2)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),3)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),4)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),5)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),6))),
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
    public static void addRecord() {

        DefaultComboBoxModel DCBMServices = new DefaultComboBoxModel();
        for (int i = 0; i < MainFrame.TMServices.getRowCount(); i++) {
            String s=MainFrame.TMServices.getValueAt(i,1)+"  "+MainFrame.TMServices.getValueAt(i,2)+" р.";
            DCBMServices.addElement(s);
        }
        JComboBox CBServices = new JComboBox(DCBMServices);
        CBServices.setSelectedIndex(0);

        DefaultComboBoxModel DCBMClients = new DefaultComboBoxModel();
        for (int i = 0; i < MainFrame.TMClients.getRowCount(); i++) {
            String s=MainFrame.TMClients.getValueAt(i,1)+"  "+MainFrame.TMClients.getValueAt(i,2)+"  "+MainFrame.TMClients.getValueAt(i,3);
            DCBMClients.addElement(s);
        }
        JComboBox CBClients = new JComboBox(DCBMClients);
        CBClients.setSelectedIndex(0);

        DefaultComboBoxModel DCBMEmployee = new DefaultComboBoxModel();
        for (int i = 0; i < MainFrame.TMEmployee.getRowCount(); i++) {
            String s=MainFrame.TMEmployee.getValueAt(i,5)+"  "+MainFrame.TMEmployee.getValueAt(i,1)+"  "+MainFrame.TMEmployee.getValueAt(i,2)+"  "+MainFrame.TMEmployee.getValueAt(i,3);
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
        addClients.setBackground(new Color(93,222,211));
        addClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog.addCliens();
                DCBMClients.removeAllElements();
                for (int i = 0; i < MainFrame.TMClients.getRowCount(); i++) {
                    String s=MainFrame.TMClients.getValueAt(i,1)+"  "+MainFrame.TMClients.getValueAt(i,2)+"  "+MainFrame.TMClients.getValueAt(i,3);
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
        add.setBackground(new Color(180,240,235));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.TMRecord.addRow(new Record(
                        new Services(
                                Integer.valueOf(String.valueOf(MainFrame.TMServices.getValueAt(CBServices.getSelectedIndex(),0))),
                                String.valueOf(MainFrame.TMServices.getValueAt(CBServices.getSelectedIndex(),1)),
                                Float.valueOf(String.valueOf(MainFrame.TMServices.getValueAt(CBServices.getSelectedIndex(),2)))),
                        new Clients(
                                Integer.valueOf(String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),0))),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),1)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),2)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),3)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),4)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),5)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),6))),
                        new Employee(
                                Integer.valueOf(String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),0))),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),1)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),2)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),3)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),4)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),5)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),6))),
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

    public static void changeServices(){

        Services s=new Services(
                Integer.parseInt(String.valueOf(MainFrame.TMServices.getValueAt(MainFrame.tableServices.getSelectedRow(),0))),
                String.valueOf(MainFrame.TMServices.getValueAt(MainFrame.tableServices.getSelectedRow(),1)),
                Float.parseFloat(String.valueOf(MainFrame.TMServices.getValueAt(MainFrame.tableServices.getSelectedRow(),2)))
        );

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
        change.setBackground(new Color(93,222,211));
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean exist=false;
                    int count=-1;
                    for (int i=0;i<MainFrame.TMServices.getRowCount();i++) {
                        if (
                                name.getText().equals(String.valueOf(MainFrame.TMServices.getValueAt(i, 1))) &
                                        Float.parseFloat(price.getText())==Float.parseFloat(String.valueOf(MainFrame.TMServices.getValueAt(i, 2)))
                        ){
                            exist=true;
                            count=i;
                        }
                    }
                    if(exist){
                        JOptionPane.showMessageDialog(dialog,
                                " Уже существует такая услуга!",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                        dialog.dispose();
                        name.setText("");
                        price.setText("");
                    }else{
                        MainFrame.TMServices.changeRow(s.getId(),new Services(name.getText(), Float.parseFloat(price.getText())));
                        dialog.dispose();
                        name.setText("");
                        price.setText("");
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
    public static void changeCliens() {

        Clients c=new Clients(
                Integer.parseInt(String.valueOf(MainFrame.TMClients.getValueAt(MainFrame.tableClients.getSelectedRow(),0))),
                String.valueOf(MainFrame.TMClients.getValueAt(MainFrame.tableClients.getSelectedRow(),1)),
                String.valueOf(MainFrame.TMClients.getValueAt(MainFrame.tableClients.getSelectedRow(),2)),
                String.valueOf(MainFrame.TMClients.getValueAt(MainFrame.tableClients.getSelectedRow(),3)),
                String.valueOf(MainFrame.TMClients.getValueAt(MainFrame.tableClients.getSelectedRow(),4)),
                String.valueOf( MainFrame.TMClients.getValueAt(MainFrame.tableClients.getSelectedRow(),5)),
                String.valueOf( MainFrame.TMClients.getValueAt(MainFrame.tableClients.getSelectedRow(),6))
        );

        JDialog dialog=new JDialog();
        dialog.setTitle("Редактирование клиента");

        JPanel panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        JTextField surname=new JTextField();surname.setText(c.getSurname());
        JTextField name=new JTextField();name.setText(c.getName());
        JTextField midllename=new JTextField();midllename.setText(c.getMiddle());

        // Определение маски и поля ввода даты
        DateFormat date = new SimpleDateFormat("dd.MM");
        // Форматирующий объект даты
        DateFormatter dateFormatter = new DateFormatter(date);
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);
        // Создание форматированного текстового поля даты
        JFormattedTextField ftfDate = new JFormattedTextField(dateFormatter);
        ftfDate.setColumns(32);
        Calendar calendar = new GregorianCalendar(2021,Integer.valueOf(c.getBirthdate().substring(3,5) )-1 , Integer.valueOf(c.getBirthdate().substring(0,2) ) );
        Date date1 = calendar.getTime();
        ftfDate.setValue(date1);

        JTextField email=new JTextField();
        email.setText(c.getEmail());

        // Определение маски и содание поля ввода мобильного телефона
        MaskFormatter phoneFormatter = null;
        try {
            phoneFormatter = new MaskFormatter("+#-###-###-##-##");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        phoneFormatter.setPlaceholderCharacter('0');
        JFormattedTextField ftfPhone = new JFormattedTextField(phoneFormatter);
        ftfPhone.setColumns(16);
        ftfPhone.setValue(c.getPhone());

        panel.add(new JLabel("Фамилия:"));
        panel.add(surname);
        panel.add(new JLabel("Имя:"));
        panel.add(name);
        panel.add(new JLabel("Отчество:"));
        panel.add(midllename);
        panel.add(new JLabel("День рождения:"));
        panel.add(ftfDate);
        panel.add(new JLabel("Почта:"));
        panel.add(email);
        panel.add(new JLabel("Телефон:"));
        panel.add(ftfPhone);

        JButton add=new JButton("Изменить");
        add.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.TMClients.changeRow(c.getId(),new Clients(
                        surname.getText(),
                        name.getText(),
                        midllename.getText(),
                        ftfDate.getText(),
                        email.getText(),
                        ftfPhone.getText()
                ));
                dialog.dispose();
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
        dialog.setSize(400,400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    public static void changeEmployee(){
        Employee c=new Employee(
                Integer.parseInt(String.valueOf(MainFrame.TMEmployee.getValueAt(MainFrame.tableEmployee.getSelectedRow(),0))),
                String.valueOf(MainFrame.TMEmployee.getValueAt(MainFrame.tableEmployee.getSelectedRow(),1)),
                String.valueOf(MainFrame.TMEmployee.getValueAt(MainFrame.tableEmployee.getSelectedRow(),2)),
                String.valueOf(MainFrame.TMEmployee.getValueAt(MainFrame.tableEmployee.getSelectedRow(),3)),
                String.valueOf(MainFrame.TMEmployee.getValueAt(MainFrame.tableEmployee.getSelectedRow(),4)),
                String.valueOf( MainFrame.TMEmployee.getValueAt(MainFrame.tableEmployee.getSelectedRow(),5)),
                String.valueOf( MainFrame.TMEmployee.getValueAt(MainFrame.tableEmployee.getSelectedRow(),6))
        );

        JDialog dialog=new JDialog();
        dialog.setTitle("Редактирование сотрудника");

        JPanel panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        JTextField surname=new JTextField();surname.setText(c.getSurname());
        JTextField name=new JTextField();name.setText(c.getName());
        JTextField midllename=new JTextField();midllename.setText(c.getMiddle());

        // Определение маски и поля ввода даты
        DateFormat date = new SimpleDateFormat("dd.MM");
        // Форматирующий объект даты
        DateFormatter dateFormatter = new DateFormatter(date);
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);
        // Создание форматированного текстового поля даты
        JFormattedTextField ftfDate = new JFormattedTextField(dateFormatter);
        ftfDate.setColumns(32);
        Calendar calendar = new GregorianCalendar(2021,Integer.valueOf(c.getBirthdate().substring(3,5) )-1 , Integer.valueOf(c.getBirthdate().substring(0,2) ) );
        Date date1 = calendar.getTime();
        ftfDate.setValue(date1);


        JTextField post=new JTextField();post.setText(c.getPost());

        // Определение маски и содание поля ввода мобильного телефона
        MaskFormatter phoneFormatter = null;
        try {
            phoneFormatter = new MaskFormatter("+#-###-###-##-##");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        phoneFormatter.setPlaceholderCharacter('0');
        JFormattedTextField ftfPhone = new JFormattedTextField(phoneFormatter);
        ftfPhone.setColumns(16);
        ftfPhone.setValue(c.getPhone());

        panel.add(new JLabel("Фамилия:"));
        panel.add(surname);
        panel.add(new JLabel("Имя:"));
        panel.add(name);
        panel.add(new JLabel("Отчество:"));
        panel.add(midllename);
        panel.add(new JLabel("День рождения:"));
        panel.add(ftfDate);
        panel.add(new JLabel("Должность:"));
        panel.add(post);
        panel.add(new JLabel("Телефон:"));
        panel.add(ftfPhone);

        JButton add=new JButton("Изменить");
        add.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.TMEmployee.changeRow(c.getId(),new Employee(
                        surname.getText(),
                        name.getText(),
                        midllename.getText(),
                        ftfDate.getText(),
                        post.getText(),
                        ftfPhone.getText()
                ));
                dialog.dispose();
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
        dialog.setSize(400,400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    public static void changeWork() {
        int index=-1;
        DefaultComboBoxModel DCBMServices = new DefaultComboBoxModel();
        for (int i = 0; i < MainFrame.TMServices.getRowCount(); i++) {
            String s=MainFrame.TMServices.getValueAt(i,1)+"  "+MainFrame.TMServices.getValueAt(i,2)+" р.";
            DCBMServices.addElement(s);
            String str=MainFrame.TMWork.getValueAt(MainFrame.tableWork.getSelectedRow(),1)+"  "+MainFrame.TMWork.getValueAt(MainFrame.tableWork.getSelectedRow(),2)+" р.";
            if (s.equals(str)){
                index=i;
            }
        }
        JComboBox CBServices = new JComboBox(DCBMServices);
        CBServices.setSelectedIndex(index);

        DefaultComboBoxModel DCBMClients = new DefaultComboBoxModel();
        for (int i = 0; i < MainFrame.TMClients.getRowCount(); i++) {
            String s=MainFrame.TMClients.getValueAt(i,1)+"  "+MainFrame.TMClients.getValueAt(i,2)+"  "+MainFrame.TMClients.getValueAt(i,3);
            DCBMClients.addElement(s);
            String str=MainFrame.TMWork.getValueAt(MainFrame.tableWork.getSelectedRow(),3)+"  "+MainFrame.TMWork.getValueAt(MainFrame.tableWork.getSelectedRow(),4)+"  "+MainFrame.TMWork.getValueAt(MainFrame.tableWork.getSelectedRow(),5);
            if (s.equals(str)){
                index=i;
            }
        }
        JComboBox CBClients = new JComboBox(DCBMClients);
        CBClients.setSelectedIndex(index);

        DefaultComboBoxModel DCBMEmployee = new DefaultComboBoxModel();
        for (int i = 0; i < MainFrame.TMEmployee.getRowCount(); i++) {
            String s=MainFrame.TMEmployee.getValueAt(i,5)+"  "+MainFrame.TMEmployee.getValueAt(i,1)+"  "+MainFrame.TMEmployee.getValueAt(i,2)+"  "+MainFrame.TMEmployee.getValueAt(i,3);
            DCBMEmployee.addElement(s);
            String str=MainFrame.TMWork.getValueAt(MainFrame.tableWork.getSelectedRow(),6)+"  "+MainFrame.TMWork.getValueAt(MainFrame.tableWork.getSelectedRow(),7)+"  "+MainFrame.TMWork.getValueAt(MainFrame.tableWork.getSelectedRow(),8);
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
        Calendar calendar = new GregorianCalendar(Integer.valueOf(String.valueOf(MainFrame.tableWork.getValueAt(MainFrame.tableWork.getSelectedRow(),9)).substring(6,10) ),
                Integer.valueOf(String.valueOf(MainFrame.tableWork.getValueAt(MainFrame.tableWork.getSelectedRow(),9)).substring(3,5) )-1 ,
               Integer.valueOf(String.valueOf(MainFrame.tableWork.getValueAt(MainFrame.tableWork.getSelectedRow(),9)).substring(0,2) ),
                Integer.valueOf(String.valueOf(MainFrame.tableWork.getValueAt(MainFrame.tableWork.getSelectedRow(),10)).substring(0,2) ),
                Integer.valueOf(String.valueOf(MainFrame.tableWork.getValueAt(MainFrame.tableWork.getSelectedRow(),10)).substring(3,5) )
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
                MyDialog.addCliens();
                DCBMClients.removeAllElements();
                for (int i = 0; i < MainFrame.TMClients.getRowCount(); i++) {
                    String s=MainFrame.TMClients.getValueAt(i,1)+"  "+MainFrame.TMClients.getValueAt(i,2)+"  "+MainFrame.TMClients.getValueAt(i,3);
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
        JTextField comment=new JTextField();comment.setText(String.valueOf(MainFrame.tableWork.getValueAt(MainFrame.tableWork.getSelectedRow(),11)));
        panel.add(comment);

        JDialog dialog=new JDialog();

        JButton add=new JButton("Изменить");
        add.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.TMWork.changeRow(Integer.parseInt(String.valueOf(MainFrame.TMWork.getValueAt(MainFrame.tableWork.getSelectedRow(),0))),
                        new Record(
                        new Services(
                                Integer.valueOf(String.valueOf(MainFrame.TMServices.getValueAt(CBServices.getSelectedIndex(),0))),
                                String.valueOf(MainFrame.TMServices.getValueAt(CBServices.getSelectedIndex(),1)),
                                Float.valueOf(String.valueOf(MainFrame.TMServices.getValueAt(CBServices.getSelectedIndex(),2)))),
                        new Clients(
                                Integer.valueOf(String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),0))),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),1)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),2)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),3)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),4)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),5)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),6))),
                        new Employee(
                                Integer.valueOf(String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),0))),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),1)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),2)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),3)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),4)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),5)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),6))),
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
    public static void changeRecord() {

        int index=-1;
        DefaultComboBoxModel DCBMServices = new DefaultComboBoxModel();
        for (int i = 0; i < MainFrame.TMServices.getRowCount(); i++) {
            String s=MainFrame.TMServices.getValueAt(i,1)+"  "+MainFrame.TMServices.getValueAt(i,2)+" р.";
            DCBMServices.addElement(s);
            String str=MainFrame.TMRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),1)+"  "+MainFrame.TMRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),2)+" р.";
            if (s.equals(str)){
                index=i;
            }
        }
        JComboBox CBServices = new JComboBox(DCBMServices);
        CBServices.setSelectedIndex(index);

        DefaultComboBoxModel DCBMClients = new DefaultComboBoxModel();
        for (int i = 0; i < MainFrame.TMClients.getRowCount(); i++) {
            String s=MainFrame.TMClients.getValueAt(i,1)+"  "+MainFrame.TMClients.getValueAt(i,2)+"  "+MainFrame.TMClients.getValueAt(i,3);
            DCBMClients.addElement(s);
            String str=MainFrame.TMRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),3)+"  "+MainFrame.TMRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),4)+"  "+MainFrame.TMRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),5);
            if (s.equals(str)){
                index=i;
            }
        }
        JComboBox CBClients = new JComboBox(DCBMClients);
        CBClients.setSelectedIndex(index);

        DefaultComboBoxModel DCBMEmployee = new DefaultComboBoxModel();
        for (int i = 0; i < MainFrame.TMEmployee.getRowCount(); i++) {
            String s=MainFrame.TMEmployee.getValueAt(i,5)+"  "+MainFrame.TMEmployee.getValueAt(i,1)+"  "+MainFrame.TMEmployee.getValueAt(i,2)+"  "+MainFrame.TMEmployee.getValueAt(i,3);
            DCBMEmployee.addElement(s);
            String str=MainFrame.TMRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),6)+"  "+MainFrame.TMRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),7)+"  "+MainFrame.TMRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),8);
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
        Calendar calendar = new GregorianCalendar(Integer.valueOf(String.valueOf(MainFrame.tableRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),9)).substring(6,10) ),
                Integer.valueOf(String.valueOf(MainFrame.tableRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),9)).substring(3,5) )-1 ,
                Integer.valueOf(String.valueOf(MainFrame.tableRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),9)).substring(0,2) ),
                Integer.valueOf(String.valueOf(MainFrame.tableRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),10)).substring(0,2) ),
                Integer.valueOf(String.valueOf(MainFrame.tableRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),10)).substring(3,5) )
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
                MyDialog.addCliens();
                DCBMClients.removeAllElements();
                for (int i = 0; i < MainFrame.TMClients.getRowCount(); i++) {
                    String s=MainFrame.TMClients.getValueAt(i,1)+"  "+MainFrame.TMClients.getValueAt(i,2)+"  "+MainFrame.TMClients.getValueAt(i,3);
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
        comment.setText(String.valueOf(MainFrame.tableRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),11)));
        panel.add(comment);

        JDialog dialog=new JDialog();

        JButton add=new JButton("Изменить");
        add.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.TMRecord.changeRow(Integer.parseInt(String.valueOf(MainFrame.TMRecord.getValueAt(MainFrame.tableRecord.getSelectedRow(),0))),new Record(
                        new Services(
                                Integer.valueOf(String.valueOf(MainFrame.TMServices.getValueAt(CBServices.getSelectedIndex(),0))),
                                String.valueOf(MainFrame.TMServices.getValueAt(CBServices.getSelectedIndex(),1)),
                                Float.valueOf(String.valueOf(MainFrame.TMServices.getValueAt(CBServices.getSelectedIndex(),2)))),
                        new Clients(
                                Integer.valueOf(String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),0))),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),1)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),2)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),3)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),4)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),5)),
                                String.valueOf(MainFrame.TMClients.getValueAt(CBClients.getSelectedIndex(),6))),
                        new Employee(
                                Integer.valueOf(String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),0))),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),1)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),2)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),3)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),4)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),5)),
                                String.valueOf(MainFrame.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(),6))),
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

        dialog.setTitle("Новая запись");
        dialog.setLayout(new BorderLayout());
        dialog.add(p);
        dialog.setModal(true);
        dialog.setSize(1000,450);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
