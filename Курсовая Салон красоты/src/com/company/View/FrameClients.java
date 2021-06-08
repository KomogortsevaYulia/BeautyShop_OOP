package com.company.View;

import com.company.Essence.Clients;
import com.company.Model.TableModelClients;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FrameClients extends JFrame{

    public static TableModelClients TMClients=new TableModelClients();
    public static JTable tableClients=new JTable(TMClients);
    public FrameClients(){
        tableClients.removeColumn(tableClients.getColumnModel().getColumn(0));
        //tableClients.getColumnModel().getColumn(0).setPreferredWidth(40);
       /* tableClients.getColumnModel().getColumn(1).setPreferredWidth(110);
        tableClients.getColumnModel().getColumn(2).setPreferredWidth(110);
        tableClients.getColumnModel().getColumn(3).setPreferredWidth(110);
        tableClients.getColumnModel().getColumn(4).setPreferredWidth(90);
        tableClients.getColumnModel().getColumn(5).setPreferredWidth(100);*/
        //tableClients.getColumnModel().getColumn(6).setPreferredWidth(120);

        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новый клиент");
        JButton delete=new JButton("Удалить клиента");
        JButton change=new JButton("Редактировать");
        JButton home=new JButton("На главную");
        add.setBackground(new Color(176,224,230));
        delete.setBackground(new Color(176,224,230));
        change.setBackground(new Color(176,224,230));
        home.setBackground(new Color(176,224,230));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCliens();
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
                        changeCliens();
                    }
                }

            }
        });
        JFrame frame=new JFrame();
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
        panel.add(new JScrollPane(tableClients),BorderLayout.CENTER);//добавление таблицы в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                MainFrame.f.setVisible(true);
            }
        });

        frame.add(panel);
        frame.setTitle("Клиенты");
        frame.setSize(740,500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

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
        add.setBackground(new Color(176,224,230));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(surname.getText().equals("") || name.getText().equals("") || midllename.getText().equals("") ||ftfPhone.getText().equals("+0-000-000-00-00")) {
                    JOptionPane.showMessageDialog(dialog,
                            " Поля Фамилия,Имя ,Отчество и телефон должны быть заполнены!",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    boolean b=TMClients.addRow(new Clients(
                            surname.getText(),
                            name.getText(),
                            midllename.getText(),
                            ftfDate.getText(),
                            email.getText(),
                            ftfPhone.getText(),
                            0
                    ));
                    if(b){
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
                   else {
                        JOptionPane.showMessageDialog(dialog,
                                " Уже есть такая запись!",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
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
    public static void changeCliens() {

        Clients c=TMClients.getRow(Integer.parseInt(String.valueOf(TMClients.getValueAt(tableClients.getSelectedRow(),0))));
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
        add.setBackground(new Color(176,224,230));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(surname.getText().equals("") || name.getText().equals("") || midllename.getText().equals("") ||ftfPhone.getText().equals("+0-000-000-00-00")) {
                    JOptionPane.showMessageDialog(dialog,
                            " Поля Фамилия,Имя ,Отчество и телефон должны быть заполнены!",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    boolean b=TMClients.changeRow(c.getId(), new Clients(
                            surname.getText(),
                            name.getText(),
                            midllename.getText(),
                            ftfDate.getText(),
                            email.getText(),
                            ftfPhone.getText(),
                            c.getPoint()
                    ));
                    if(b){
                        dialog.dispose();
                        JOptionPane.showMessageDialog(dialog,
                                " Данные о клиенте успешно изменены!",
                                "Уведомление",
                                JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(dialog,
                                " Уже есть такая запись!",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }

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
        dialog.setSize(400,400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
