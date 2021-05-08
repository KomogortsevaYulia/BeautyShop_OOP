package com.company.View;

import com.company.Essence.Services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyDialog {
    public static void Cliens(){

    }
    public static void Employee(){

    }
    public static void Services(){
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
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.TMServices.addRow(new Services(name.getText(),Float.valueOf(price.getText())));
            }
        });

        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        p.add(panel,BorderLayout.CENTER);
        p.add(add,BorderLayout.SOUTH);
        p.setBackground(Color.white);
        p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JDialog dialog=new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.add(p);
        dialog.setModal(true);
        dialog.setSize(400,200);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
