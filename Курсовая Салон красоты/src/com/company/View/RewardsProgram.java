package com.company.View;

import com.company.Model.DBWorker;
import org.sqlite.core.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RewardsProgram {
    private static int percent ;
    private static int percentDiscount;
    public static JPanel panelInfo=new JPanel(new FlowLayout());
    public static int getPercentDiscount() {
        return percentDiscount;
    }
    public static int getPercent() {
        return percent;
    }

    public static void update(){
        panelInfo.removeAll();
        Font font = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 14);
        JLabel Label = new JLabel();
        Label.setText("Правила бонусной программы:");
        Label.setFont(font);
        panelInfo.add(Label);
        JLabel Label1 = new JLabel();
        Label1.setText("1 балл=1 рубль");
        Label1.setFont(font);
        panelInfo.add(Label1);
        JLabel Label2 = new JLabel();
        Label2.setText("Можно редактировать %");
        Label2.setFont(font);
        panelInfo.add(Label2);
        panelInfo.setBackground(Color.white);
        panelInfo.repaint();
        panelInfo.revalidate();

        DBWorker.initDB();
        DBWorker.changeRewards(percent,percentDiscount);
        DBWorker.closeDB();
    }
    public static void init(){
        DBWorker.initDB();
        int[] i=DBWorker.selectRewards();
        DBWorker.closeDB();
        percent=i[0];
        percentDiscount=i[1];
    }
    public static void DialogRewardsPrograms(){
        DBWorker.initDB();
        int[] i=DBWorker.selectRewards();
        DBWorker.closeDB();
        percent=i[0];
        percentDiscount=i[1];
        JDialog dialog=new JDialog();
        update();
        JPanel panelMain=new JPanel(new BorderLayout());
        panelMain.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelMain.setBackground(Color.white);
        panelMain.add(panelInfo,BorderLayout.NORTH);

        JButton update=new JButton("Изменить %");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                change();
            }
        });
        panelMain.add(update,BorderLayout.SOUTH);

        dialog.add(panelMain);
        dialog.setTitle("Бонусная программа");
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setSize(400,250);
        dialog.setVisible(true);

    }
    public static void change(){
        JDialog d=new JDialog();
        JPanel panelChange=new JPanel();
        panelChange.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelChange.setBackground(Color.white);
        panelChange.setLayout(new GridLayout(3, 1, 10, 10));

        JSpinner Spercent   = new JSpinner(new SpinnerNumberModel(percent, 0, 100, 1));
        JSpinner SpercentDisc   = new JSpinner(new SpinnerNumberModel(percentDiscount, 0, 100, 1));

        JPanel p1=new JPanel(new FlowLayout());
        p1.setBackground(Color.white);
        p1.add(new JLabel("Количество % начисляющихся : "));
        p1.add(Spercent);
        JPanel p2=new JPanel(new FlowLayout());
        p2.setBackground(Color.white);
        p2.add(new JLabel("Количество % скидки  : "));
        p2.add(SpercentDisc);

        panelChange.add(p1);
        panelChange.add(p2);

        JButton change=new JButton("Сохранить");
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (( Integer.parseInt(String.valueOf(Spercent.getValue()))<=100 & Integer.parseInt(String.valueOf(Spercent.getValue()))>-1) &
                            (Integer.parseInt(String.valueOf(SpercentDisc.getValue()))<=100 & Integer.parseInt(String.valueOf(SpercentDisc.getValue()))>-1)
                    ){
                        percent=Integer.parseInt(String.valueOf(Spercent.getValue()));
                        percentDiscount=Integer.parseInt(String.valueOf(SpercentDisc.getValue()));

                        update();
                        d.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(panelChange,
                                "Можно вводить только числа от 0 до 100",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(panelChange,
                            "Можно вводить только числа от 0 до 100",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JPanel panelButton=new JPanel(new FlowLayout());
        panelButton.setBackground(Color.white);
        panelButton.add(change);
        panelChange.add(panelButton);

        d.add(panelChange);
        d.setTitle("Изменение");
        d.setModal(true);
        d.setSize(300,200);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }
}
