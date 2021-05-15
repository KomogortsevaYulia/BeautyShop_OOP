package com.company.View;

import com.company.Model.TableModelRecordDay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameRecordDay extends JFrame {

    public static TableModelRecordDay TMRecordDay=new TableModelRecordDay();
    public static JTable tableRecordDay=new JTable(TMRecordDay);

    public FrameRecordDay(){
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
                FrameRecord.addRecord();
                TMRecordDay.update();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableRecordDay.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int [] i=tableRecordDay.getSelectedRows();
                    int [] id = new int[i.length];
                    for (int j = 0; j < i.length; j++) {
                        id[j]= (int) TMRecordDay.getValueAt(i[j],0);
                    }
                    TMRecordDay.deleteRow(id);
                }
            }
        });
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableRecordDay.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    FrameRecord.changeRecord();
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
        panel.add(new JScrollPane(tableRecordDay),BorderLayout.CENTER);//добавление таблицы в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        frame.add(panel);
        frame.setTitle("Записи на день");
        frame.setSize(1200,500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}
