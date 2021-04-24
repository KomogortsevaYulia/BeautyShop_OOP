package com.company.View;

import com.company.Model.Services;
import com.company.Model.TMServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameServices extends JFrame {

    public static TMServices Services =new TMServices();
    public static JTable tableServices =new JTable(Services);

    public FrameServices(){

        JPanel table=new JPanel();
        JScrollPane SP=new JScrollPane(tableServices);
        SP.setBackground(Color.white);
        SP.setPreferredSize(new Dimension(450,300));
        table.add(SP);
        tableServices.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableServices.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableServices.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.setBackground(Color.white);

        JPanel button=new JPanel();
        JButton add=new JButton("Новая услуга");
        JButton delete=new JButton("Удалить услугу");
        JButton change=new JButton("Редактировать");
        JButton information=new JButton("Сведения");
        JButton home=new JButton("На главную");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDialog(-1,new Services("",0));
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] row= tableServices.getSelectedRows();
                if (row.length==0){
                    JOptionPane.showMessageDialog(button,
                            " Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int[] id=new int[row.length];
                for (int i = 0; i < row.length; i++) {
                    id[i]=(int) Services.getValueAt(row[i],0);
                }
                Services.deleteRow(id);
            }
        });
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row= tableServices.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(button,
                            " Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id =(int) Services.getValueAt(row,0);
                CreateDialog(id,new Services(
                        (String) Services.getValueAt(row,1),
                        (float) Services.getValueAt(row,2)
                ));
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
        setTitle("Услуги");
        setSize(new Dimension(700,400));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void CreateDialog(int id,Services c){
        JDialog dialog=new JDialog();
        JPanel main=new JPanel();
        main.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        main.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField name=new JTextField();
        JTextField price=new JTextField();

        price.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    Float.valueOf(price.getText());
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(main,
                            " В поле телефон можно вводить только числа",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    price.setText("");
                }
            }
        });
        name.setText(c.getName());
        price.setText(String.valueOf(c.getPrice()));
        if (String.valueOf(c.getPrice()).equals("0")){
            price.setText("");
        }else price.setText(String.valueOf(c.getPrice()));

        main.add(new JLabel("Название:"));
        main.add(name);
        main.add(new JLabel("Стоимость:"));
        main.add(price);

        JButton add=new JButton("Добавить");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Services.addRow(new Services(name.getText(),Float.valueOf(price.getText())));
                    name.setText("");
                    price.setText("");
                    dialog.dispose();
                    price.setText("");
                    JOptionPane.showMessageDialog(main,
                            "Услуга была добавлена",
                            "Сообщение",
                            JOptionPane.INFORMATION_MESSAGE);
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(main,
                            " В поле стоимость можно вводить только числа",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    price.setText("");
                }
            }
        });
        JButton change=new JButton("Сохранить изменения");
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Services.changeRow(id,new Services(name.getText(),Float.valueOf(price.getText())));
                dialog.dispose();
            }
        });
        JPanel b=new JPanel();
        if (id==-1) {   b.add(add);   }    else b.add(change);
        if (id==-1){   dialog.setTitle("Новая запись"); } else dialog.setTitle("Редактирование");
        dialog.add(main,BorderLayout.CENTER);
        dialog.add(b,BorderLayout.SOUTH);
        dialog.setModal(true);
        dialog.setSize(new Dimension(400,200));
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);
    }
}
