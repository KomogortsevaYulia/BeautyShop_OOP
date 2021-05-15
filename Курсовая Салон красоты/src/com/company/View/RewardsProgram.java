package com.company.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RewardsProgram {
    private static int percent ;
    private static int percentDiscount;

    public static void FrameRewardsPrograms(){

        JDialog frame=new JDialog();
        JPanel Panel=new JPanel();
        Panel.setBackground(Color.white);
        Panel.setLayout(new GridLayout(3, 2, 10, 10));
        JSpinner Spercent   = new JSpinner(new SpinnerNumberModel(percent, 0, 100, 5));
        JSpinner SpercentDisc   = new JSpinner(new SpinnerNumberModel(percentDiscount, 0, 100, 5));
        JButton change=new JButton("Сохранить");
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                percent=Integer.parseInt(String.valueOf(Spercent.getValue()));
                percentDiscount=Integer.parseInt(String.valueOf(SpercentDisc.getValue()));
                frame.dispose();
            }
        });
        JButton home=new JButton("На главную");
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        Panel.add(new JLabel("Количество % начисляющихся : "));
        Panel.add(Spercent);
        Panel.add(new JLabel("Количество % скидки  : "));
        Panel.add(SpercentDisc);
        Panel.add(change);
        Panel.add(home);
        frame.add(Panel);
        frame.setTitle("Бонусная программа");
        frame.setModal(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(450,200);
        frame.setVisible(true);

    }
}
