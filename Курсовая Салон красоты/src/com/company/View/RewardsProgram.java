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
    public static JPanel panelInfo=new JPanel(new GridLayout(6,1,5,5));
    public static int getPercentDiscount() {
        return percentDiscount;
    }
    public static int getPercent() {
        return percent;
    }

    public static void update(){
        panelInfo.removeAll();
        Font font = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 14);
        JLabel l=new JLabel("Правила бонусной программы:");
        l.setFont(font);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        l.setHorizontalTextPosition(SwingConstants.CENTER);
        panelInfo.add(l);
        panelInfo.add(new JLabel("1 балл = 1 рубль"));
        panelInfo.add(new JLabel("При оплате любой услуги клиенту возвращается "+percent+"% "));
        panelInfo.add(new JLabel("от суммы оплаты (без учета бонусов)."));
        panelInfo.add(new JLabel("Клиент может оплатить "+percentDiscount+"% бонусами от общей суммы."));
        panelInfo.add(new JLabel("Ниже вы можете отредактировать %:"));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
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
        panelMain.add(panelInfo,BorderLayout.CENTER);

        JButton update=new JButton("Изменить %");
        update.setBackground(new Color(176,224,230));
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
        dialog.setSize(500,300);
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
        p1.add(new JLabel("% от стомости оплаты(без бонусов), который вернется клиенту:  "));
        p1.add(Spercent);
        JPanel p2=new JPanel(new FlowLayout());
        p2.setBackground(Color.white);
        p2.add(new JLabel("% от стоимости услуги, который можно будет оплатить бонусами: "));
        p2.add(SpercentDisc);

        panelChange.add(p1);
        panelChange.add(p2);

        JButton change=new JButton("Сохранить");
        change.setBackground(new Color(176,224,230));
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
        d.setSize(500,200);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }
}
