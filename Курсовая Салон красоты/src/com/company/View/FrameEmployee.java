package com.company.View;

import com.company.Essence.Employee;
import com.company.Model.TableModelEmployee;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FrameEmployee extends JFrame {

    public static TableModelEmployee TMEmployee=new TableModelEmployee();
    public static JTable tableEmployee=new JTable(TMEmployee);

    public  FrameEmployee(){

        tableEmployee.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableEmployee.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableEmployee.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableEmployee.getColumnModel().getColumn(3).setPreferredWidth(100);
        tableEmployee.getColumnModel().getColumn(4).setPreferredWidth(90);
        tableEmployee.getColumnModel().getColumn(5).setPreferredWidth(200);
        tableEmployee.getColumnModel().getColumn(6).setPreferredWidth(110);

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
               addEmployee();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (FrameClients.tableClients.getSelectedRow()==0){
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
                        changeEmployee();
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
        panel.add(new JScrollPane(tableEmployee),BorderLayout.CENTER);//добавили таблицу в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        frame.add(panel);
        frame.setSize(850,500);
        frame.setTitle("Сотрудники");
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
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
        panel.add(new JLabel("Должность:"));
        panel.add(post);
        panel.add(new JLabel("Телефон:"));
        panel.add(ftfPhone);

        JButton add=new JButton("Добавить");
        add.setBackground(new Color(93,222,211));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TMEmployee.addRow(new Employee(
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
    public static void changeEmployee(){
        Employee c=new Employee(
                Integer.parseInt(String.valueOf(TMEmployee.getValueAt(tableEmployee.getSelectedRow(),0))),
                String.valueOf(TMEmployee.getValueAt(tableEmployee.getSelectedRow(),1)),
                String.valueOf(TMEmployee.getValueAt(tableEmployee.getSelectedRow(),2)),
                String.valueOf(TMEmployee.getValueAt(tableEmployee.getSelectedRow(),3)),
                String.valueOf(TMEmployee.getValueAt(tableEmployee.getSelectedRow(),4)),
                String.valueOf(TMEmployee.getValueAt(tableEmployee.getSelectedRow(),5)),
                String.valueOf(TMEmployee.getValueAt(tableEmployee.getSelectedRow(),6))
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
                TMEmployee.changeRow(c.getId(),new Employee(
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
}
