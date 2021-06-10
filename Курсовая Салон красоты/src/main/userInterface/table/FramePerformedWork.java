package main.userInterface.table;

import main.data.tableModel.TableModelPerformedWork;
import main.userInterface.*;
import main.userInterface.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FramePerformedWork extends JFrame {
    public static TableModelPerformedWork TMWork=new TableModelPerformedWork();
    public static JTable tableWork=new JTable(TMWork);
   // private static float usePoint;
    //private static float income;
    public static Color c2=new Color(176,224,230);

    public FramePerformedWork(){
        tableWork.removeColumn(tableWork.getColumnModel().getColumn(0));
        //tableWork.getColumnModel().getColumn(0).setPreferredWidth(30);
        /*tableWork.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableWork.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableWork.getColumnModel().getColumn(3).setPreferredWidth(100);
        tableWork.getColumnModel().getColumn(4).setPreferredWidth(100);
        tableWork.getColumnModel().getColumn(5).setPreferredWidth(100);
        tableWork.getColumnModel().getColumn(6).setPreferredWidth(100);
        tableWork.getColumnModel().getColumn(7).setPreferredWidth(100);
        tableWork.getColumnModel().getColumn(8).setPreferredWidth(100);
        tableWork.getColumnModel().getColumn(9).setPreferredWidth(50);
        tableWork.getColumnModel().getColumn(10).setPreferredWidth(50);
        tableWork.getColumnModel().getColumn(11).setPreferredWidth(100);
*/
        //метод создающий окно с выполненными работами
        //кнопки+листенеры+панель с таблицей
        JFrame frame=new JFrame();
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        JButton add=new JButton("Новая запись");
        JButton cancel=new JButton("Сделать возврат средств");
        JButton home=new JButton("На главную");
        add.setBackground(c2);
        cancel.setBackground(c2);
        home.setBackground(c2);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogAddPW d=new DialogAddPW();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableWork.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Вы не выбрали запись",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int i=Integer.parseInt(String.valueOf(TMWork.getValueAt(tableWork.getSelectedRow(),0)));
                    if(TMWork.getRow(i).getIncome()>0){
                        TMWork.cancelRow(TMWork.getRow(i).getId());
                        JOptionPane.showMessageDialog(buttonPanel,
                                " Возврат средств выполнен!",
                                "Уведомление",
                                JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(buttonPanel,
                                " Уже был выполнен возврат средств!",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
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
        buttonPanel.add(cancel);
        buttonPanel.add(home);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.add(buttonPanel,BorderLayout.NORTH);
        panel.add(new JScrollPane(tableWork),BorderLayout.CENTER);//добавление таблицы в панель
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                MainFrame.f.setVisible(true);
            }
        });
        frame.add(panel);
        frame.setTitle("Выполненные работы");
        frame.setSize(1200,450);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /*public static void addWork(boolean PRorMain) {
        JLabel pointC=new JLabel();
        JLabel pointLP=new JLabel();
        JLabel pr=new JLabel();
        JLabel in=new JLabel();
        JLabel useP=new JLabel();
        boolean checkBox=false;

        DefaultComboBoxModel DCBMServices = new DefaultComboBoxModel();
        for (int i = 0; i < FrameServices.TMServices.getRowCount(); i++) {
            String s= String.valueOf( FrameServices.TMServices.getValueAt(i,0));
            DCBMServices.addElement(s);
        }
        JComboBox CBServices = new JComboBox(DCBMServices);
        CBServices.setSelectedIndex(0);

        DefaultComboBoxModel DCBMClients = new DefaultComboBoxModel();
        for (int i = 0; i < FrameClients.TMClients.getRowCount(); i++) {
            String s=FrameClients.TMClients.getValueAt(i,1)+"  "+FrameClients.TMClients.getValueAt(i,2)+"  "+FrameClients.TMClients.getValueAt(i,3);
            DCBMClients.addElement(s);
        }
        JComboBox CBClients = new JComboBox(DCBMClients);
        CBClients.setSelectedIndex(0);

        DefaultComboBoxModel DCBMEmployee = new DefaultComboBoxModel();
        for (int i = 0; i < FrameEmployee.TMEmployee.getRowCount(); i++) {
            String s=FrameEmployee.TMEmployee.getValueAt(i,5)+"  "+FrameEmployee.TMEmployee.getValueAt(i,1)+"  "+FrameEmployee.TMEmployee.getValueAt(i,2)+"  "+FrameEmployee.TMEmployee.getValueAt(i,3);
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
        ftfDate.setColumns(10);
        ftfDate.setValue(new Date());

        // Форматирующий объект даты
        DateFormatter timeFormatter = new DateFormatter(new SimpleDateFormat("HH:mm"));
        timeFormatter.setAllowsInvalid(false);
        timeFormatter.setOverwriteMode(true);
        // Создание форматированного текстового поля даты
        JFormattedTextField ftfTime = new JFormattedTextField(timeFormatter);
        ftfTime.setColumns(6);
        ftfTime.setValue(new Date());

        JPanel pM1=new JPanel(new FlowLayout());
        pM1.add(new JLabel("Услуга:  "));
        pM1.add(new JScrollPane(CBServices));

        JPanel pM2=new JPanel(new FlowLayout());
        pM2.add(new JLabel("Клиент:  "));
        pM2.add(new JScrollPane(CBClients));

        JPanel pM5=new JPanel(new FlowLayout());
        pM5.add(new JLabel("Сотрудник:  "));
        pM5.add(new JScrollPane(CBEmployee));

        JPanel pM6=new JPanel(new FlowLayout());
        pM6.add(new JLabel("Дата: "));
        pM6.add(ftfDate);
        pM6.add(new JLabel("  Время: "));
        pM6.add(ftfTime);

        JPanel pM8=new JPanel(new FlowLayout());
        pM8.add(new JLabel("Комментарий:  "));
        JTextField comment=new JTextField();
        comment.setColumns(15);
        pM8.add(comment);


        JPanel panelMain=new JPanel();
        panelMain.setBackground(Color.white);
        panelMain.setLayout(new GridLayout(6, 1, 0, 0));

        JButton addClients=new JButton("Новый клиент");
        addClients.setBackground(c2);
        addClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameClients.addCliens();
                int s=DCBMClients.getSize();
                DCBMClients.removeAllElements();
                s=DCBMClients.getSize();
                for (int i = 0; i < FrameClients.TMClients.getRowCount(); i++) {
                    DCBMClients.addElement(FrameClients.TMClients.getValueAt(i,1)+"  "+FrameClients.TMClients.getValueAt(i,2)+"  "+FrameClients.TMClients.getValueAt(i,3));
                }
            }
        });
        JPanel pM3=new JPanel(new FlowLayout());
        pM3.add(addClients);

        {
            pM1.setBackground(Color.white);
            pM2.setBackground(Color.white);
            pM3.setBackground(Color.white);
            pM5.setBackground(Color.white);
            pM6.setBackground(Color.white);
            pM8.setBackground(Color.white);

        }
        panelMain.add(pM1);
        panelMain.add(pM2);
        panelMain.add(pM3);
        panelMain.add(pM5);
        panelMain.add(pM6);
        panelMain.add(pM8);


        JFrame frame=new JFrame();

        JPanel panelAdd=new JPanel(new FlowLayout());
        panelAdd.setBackground(Color.white);
        JButton add=new JButton("Сохранить");
        add.setBackground(c2);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date=new Date();
                Calendar calendar = new GregorianCalendar(Integer.valueOf(String.valueOf(ftfDate.getText()).substring(6,10) ),
                        Integer.valueOf(String.valueOf(ftfDate.getText()).substring(3,5) )-1 ,
                        Integer.valueOf(String.valueOf(ftfDate.getText()).substring(0,2) ),
                        Integer.valueOf(String.valueOf(ftfTime.getText()).substring(0,2) ),
                        Integer.valueOf(String.valueOf(ftfTime.getText()).substring(3,5) )
                );
                Date date2=calendar.getTime();
                if(date.before(date2)){
                    JOptionPane.showMessageDialog(frame,
                            " Нельзя выполнять работы в будущем времени !",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }else {
                    TMWork.addRow(new Work(
                            FrameServices.TMServices.getRow(String.valueOf(FrameServices.TMServices.getValueAt(CBServices.getSelectedIndex(), 0))),
                            usePoint,
                            income,
                            FrameClients.TMClients.getRow(Integer.valueOf(String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(), 0)))),
                            FrameEmployee.TMEmployee.getRow(Integer.parseInt(String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(), 0)))),
                            ftfDate.getText(),
                            ftfTime.getText(),
                            comment.getText())
                    );
                    frame.dispose();
                    if(PRorMain){

                    }else{
                        MainFrame.f.setVisible(true);
                    }
                    JOptionPane.showMessageDialog(frame,
                            " Запись успешно добавлена!",
                            "Уведомление",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        panelAdd.add(add);


        JPanel panelMainPay =new JPanel(new GridLayout(6, 1, 0, 0));
        JPanel pM4=new JPanel(new FlowLayout());
        pointC.setText("Баллы клиента:  "+0);
        pM4.add(pointC);
        JPanel p1=new JPanel(new FlowLayout());
        pr.setText("Стоимость:   "+0+"  р.");
        p1.add(pr);
        JButton but=new JButton("Рассчитать");
        JPanel p3=new JPanel(new FlowLayout());
        p3.setBackground(Color.white);
        JCheckBox cb=new JCheckBox("Баллы ");
        cb.setBackground(Color.white);
        cb.setHorizontalTextPosition(JCheckBox.LEFT);
        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                but.doClick();
            }
        });
        p3.add(cb);
        p3.add(useP);

        JPanel p2=new JPanel(new FlowLayout());
        pointLP.setText("Баллов начислиться:"+0);
        p2.add(pointLP);

        JPanel p5=new JPanel(new FlowLayout());
        in.setText("Доход: "+0+" p.");
        p5.add(in);

        pM4.setBackground(Color.white);
        p1.setBackground(Color.white);
        p2.setBackground(Color.white);
        p3.setBackground(Color.white);
        p5.setBackground(Color.white);

        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                float price=Float.parseFloat(String.valueOf(FrameServices.TMServices.getValueAt(CBServices.getSelectedIndex(),1)));
                float pointclient=Float.parseFloat(String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(),7)));
                if (cb.isSelected()) {
                    if(price * RewardsProgram.getPercentDiscount()/100 <= pointclient){
                        usePoint= Float.parseFloat(String.valueOf(price * RewardsProgram.getPercentDiscount()/100));
                        useP.setText("   Использовать "+ usePoint+" баллов");
                    }
                    else{
                        usePoint=pointclient;
                        useP.setText("   Использовать "+ pointclient+" баллов ");
                    }
                } else {
                    usePoint=0;
                    useP.setText("   Использовать "+0+" баллов ");
                }

                income=price-usePoint;
                float pointsLaterPay=income*RewardsProgram.getPercent()/100;

                pointC.setText("Баллы клиента:  "+pointclient);
                pointLP.setText("Баллов начислиться:"+pointsLaterPay);
                in.setText("Доход: "+income+" p.");
                pr.setText("Стоимость:   "+price+"  р.");

                main.repaint();
                main.revalidate();
            }
        });
        JPanel p=new JPanel(new FlowLayout());
        p.setBackground(Color.white);
        p.add(but);
        panelMainPay.add(p);
        panelMainPay.add(pM4);
        panelMainPay.add(p1);
        panelMainPay.add(p2);
        panelMainPay.add(p3);
        panelMainPay.add(p5);

        main.setLayout(new BorderLayout());
        main.add(panelMain,BorderLayout.NORTH);
        main.add(panelMainPay,BorderLayout.CENTER);
        main.add(panelAdd,BorderLayout.SOUTH);
        main.setBackground(Color.white);
        main.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        frame.setTitle("Новая выполненная работа");
        frame.setLayout(new BorderLayout());
        frame.add(main);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                if(PRorMain){
                    FramePerformedWork fr=new FramePerformedWork();
                }else{
                    MainFrame.f.setVisible(true);
                }
            }
        });
        frame.setSize(670,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }*/
}
