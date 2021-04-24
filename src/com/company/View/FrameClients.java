package com.company.View;

import com.company.Main;
import com.company.Model.Clients;
import com.company.Model.TMClients;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrameClients extends JFrame {

    public static TMClients Clients=new TMClients();
    public static JTable tableClients=new JTable(Clients);

    public FrameClients(){
        super("Клиенты");

        JPanel table=new JPanel();
        JScrollPane SP=new JScrollPane(tableClients);
        SP.setBackground(Color.white);
        SP.setPreferredSize(new Dimension(750,300));
        table.add(SP);
        tableClients.getColumnModel().getColumn(0).setPreferredWidth(60);
        tableClients.getColumnModel().getColumn(1).setPreferredWidth(125);
        tableClients.getColumnModel().getColumn(2).setPreferredWidth(125);
        tableClients.getColumnModel().getColumn(3).setPreferredWidth(125);
        tableClients.getColumnModel().getColumn(4).setPreferredWidth(100);
        tableClients.getColumnModel().getColumn(5).setPreferredWidth(115);
        tableClients.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.setBackground(Color.white);

        JPanel button=new JPanel();
        JButton add=new JButton("Новый клиент");
        JButton delete=new JButton("Удалить клиента");
        JButton change=new JButton("Редактировать");
        JButton write=new JButton("Записать клиента");
        JButton information=new JButton("Сведения");
        JButton home=new JButton("На главную");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDialog(-1,new Clients("","","","","",0));
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] row= tableClients.getSelectedRows();
                if (row.length==0){
                    JOptionPane.showMessageDialog(button,
                            " Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int[] id=new int[row.length];
                for (int i = 0; i < row.length; i++) {
                    id[i]=(int) Clients.getValueAt(row[i],0);
                }
                Clients.deleteRow(id);
            }
        });
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row= tableClients.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(button,
                            " Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id =(int) Clients.getValueAt(row,0);
                CreateDialog(id,new Clients(
                        /*Integer.parseInt((String) Clients.getValueAt(row,0)),*/
                        (String) Clients.getValueAt(row,1),
                        (String) Clients.getValueAt(row,2),
                        (String) Clients.getValueAt(row,3),
                        (String) Clients.getValueAt(row,4),
                        (String) Clients.getValueAt(row,5),
                        (Long) Clients.getValueAt(row,6)));
            }
        });
        write.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        information.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        button.setLayout(new FlowLayout());
        button.add(add);
        button.add(delete);
        button.add(change);
        button.add(write);
        button.add(information);
        button.add(home);
        button.setBackground(Color.white);

        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        p.setBackground(Color.white);
        p.add(button);
        p.add(table);
        add(p);
        setSize(new Dimension(820,420));
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void CreateDialog(int id,Clients c){
        JDialog dialog=new JDialog();
        JPanel main=new JPanel();
        main.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        main.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField surname=new JTextField();
        JTextField name=new JTextField();
        JTextField middle=new JTextField();
        JTextField phone=new JTextField();
        JTextField email=new JTextField();

        // Определение маски и поля ввода даты
        DateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        // Форматирующий объект даты
        DateFormatter dateFormatter = new DateFormatter(date);
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);

        // Создание форматированного текстового поля даты
        JFormattedTextField birthdate = new JFormattedTextField(dateFormatter);
        birthdate.setColumns(32);
        birthdate.setValue(new Date());

        phone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    Integer.parseInt(phone.getText());
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(main,
                            " В поле телефон можно вводить только числа",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    phone.setText("");
                }
            }
        });
        surname.setText(c.getSurname());
        name.setText(c.getName());
        middle.setText(c.getMiddle());
        birthdate.setText(c.getBirthdate());
        email.setText(c.getEmail());
        if (String.valueOf(c.getPhone()).equals("0")){
            phone.setText("");
        }else phone.setText(String.valueOf(c.getPhone()));

        main.add(new JLabel("Фамилия:"));
        main.add(surname);
        main.add(new JLabel("Имя:"));
        main.add(name);
        main.add(new JLabel("Отчество:"));
        main.add(middle);
        main.add(new JLabel("День рождения:"));
        main.add(birthdate);
        main.add(new JLabel("Почта:"));
        main.add(email);
        main.add(new JLabel("Телефон:"));
        main.add(phone);

        JButton add=new JButton("Добавить");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Clients.addRow(new Clients(
                            surname.getText(),
                            name.getText(),
                            middle.getText(),
                            birthdate.getText(),
                            email.getText(),
                            Long.valueOf((String) phone.getText())
                    ));
                    surname.setText("");
                    name.setText("");
                    middle.setText("");
                    birthdate.setText("");
                    email.setText("");
                    phone.setText("");
                    dialog.dispose();
                    JOptionPane.showMessageDialog(main,
                            "Запись была добавлена",
                            "Сообщение",
                            JOptionPane.INFORMATION_MESSAGE);
                    phone.setText("");
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(main,
                            " В поле телефон можно вводить только числа",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    phone.setText("");
                }
            }
        });
        JButton change=new JButton("Сохранить изменения");
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clients.changeRow(id,new Clients(
                        surname.getText(),
                        name.getText(),
                        middle.getText(),
                        birthdate.getText(),
                        email.getText(),
                        Long.valueOf((String) phone.getText())
                ));
                dialog.dispose();
            }
        });
        JPanel b=new JPanel();
        if (id==-1) {   b.add(add);   }    else b.add(change);


        if (id==-1){   dialog.setTitle("Новый клиент"); } else dialog.setTitle("Редактирование");
        dialog.add(main,BorderLayout.CENTER);
        dialog.add(b,BorderLayout.SOUTH);
        dialog.setModal(true);
        dialog.setSize(new Dimension(400,400));
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);
    }

}
