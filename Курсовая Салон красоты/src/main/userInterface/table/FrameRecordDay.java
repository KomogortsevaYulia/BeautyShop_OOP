package main.userInterface.table;

import main.essence.Record;
import main.data.tableModel.TableModelRecordDay;
import main.data.tableModel.UpdateTM;
import main.userInterface.MainFrame;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FrameRecordDay extends JFrame {

    public static TableModelRecordDay TMRecordDay=new TableModelRecordDay();
    public static JTable tableRecordDay=new JTable(TMRecordDay);
    public static Color c2=new Color(176,224,230);
    private static boolean y=true;
    public FrameRecordDay(){
            if(y){
                tableRecordDay.removeColumn(tableRecordDay.getColumnModel().getColumn(0));
                y=false;
            }

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
        add.setBackground(c2);
        delete.setBackground(c2);
        change.setBackground(c2);
        home.setBackground(c2);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameRecord.addRecord();
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
                    JOptionPane.showMessageDialog(buttonPanel,
                            " Запись успешно удалена!",
                            "Уведомление",
                            JOptionPane.INFORMATION_MESSAGE);
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
                    changeRecord();
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

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                MainFrame.f.setVisible(true);
            }
        });

        frame.add(panel);
        frame.setTitle("Записи на день");
        frame.setSize(1200,500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    public static void changeRecord() {
        int index=-1;
        DefaultComboBoxModel DCBMServices = new DefaultComboBoxModel();
        for (int i = 0; i < FrameServices.TMServices.getRowCount(); i++) {
            String s=FrameServices.TMServices.getValueAt(i,0)+"  "+FrameServices.TMServices.getValueAt(i,1)+" р.";
            DCBMServices.addElement(s);
            String str=TMRecordDay.getValueAt(tableRecordDay.getSelectedRow(),1)+"  "+TMRecordDay.getValueAt(tableRecordDay.getSelectedRow(),2)+" р.";
            if (s.equals(str)){
                index=i;
            }
        }
        JComboBox CBServices = new JComboBox(DCBMServices);
        CBServices.setSelectedIndex(index);

        DefaultComboBoxModel DCBMClients = new DefaultComboBoxModel();
        for (int i = 0; i < FrameClients.TMClients.getRowCount(); i++) {
            String s=FrameClients.TMClients.getValueAt(i,1)+"  "+FrameClients.TMClients.getValueAt(i,2)+"  "+FrameClients.TMClients.getValueAt(i,3);
            DCBMClients.addElement(s);
            String str=TMRecordDay.getValueAt(tableRecordDay.getSelectedRow(),3)+"  "+TMRecordDay.getValueAt(tableRecordDay.getSelectedRow(),4)+"  "+TMRecordDay.getValueAt(tableRecordDay.getSelectedRow(),5);
            if (s.equals(str)){
                index=i;
            }
        }
        JComboBox CBClients = new JComboBox(DCBMClients);
        CBClients.setSelectedIndex(index);

        DefaultComboBoxModel DCBMEmployee = new DefaultComboBoxModel();
        for (int i = 0; i < FrameEmployee.TMEmployee.getRowCount(); i++) {
            String s=FrameEmployee.TMEmployee.getValueAt(i,5)+"  "+FrameEmployee.TMEmployee.getValueAt(i,1)+"  "+FrameEmployee.TMEmployee.getValueAt(i,2)+"  "+FrameEmployee.TMEmployee.getValueAt(i,3);
            DCBMEmployee.addElement(s);
            String str=TMRecordDay.getValueAt(tableRecordDay.getSelectedRow(),6)+"  "+TMRecordDay.getValueAt(tableRecordDay.getSelectedRow(),7)+"  "+TMRecordDay.getValueAt(tableRecordDay.getSelectedRow(),8);
            String t=FrameEmployee.TMEmployee.getValueAt(i,1)+"  "+FrameEmployee.TMEmployee.getValueAt(i,2)+"  "+FrameEmployee.TMEmployee.getValueAt(i,3);
            if (str.equals(t)){
                index=i;
            }
        }
        JComboBox CBEmployee = new JComboBox(DCBMEmployee);
        CBEmployee.setSelectedIndex(index);

        // Форматирующий объект даты
        DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("dd.MM.yyyy"));
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);
        // Создание форматированного текстового поля даты
        JFormattedTextField ftfDate = new JFormattedTextField(dateFormatter);
        ftfDate.setColumns(32);
        ftfDate.setValue(new Date());
        Calendar calendar = new GregorianCalendar(Integer.valueOf(String.valueOf(tableRecordDay.getValueAt(tableRecordDay.getSelectedRow(),8)).substring(6,10) ),
                Integer.valueOf(String.valueOf(tableRecordDay.getValueAt(tableRecordDay.getSelectedRow(),8)).substring(3,5) )-1 ,
                Integer.valueOf(String.valueOf(tableRecordDay.getValueAt(tableRecordDay.getSelectedRow(),8)).substring(0,2) ),
                Integer.valueOf(String.valueOf(tableRecordDay.getValueAt(tableRecordDay.getSelectedRow(),9)).substring(0,2) ),
                Integer.valueOf(String.valueOf(tableRecordDay.getValueAt(tableRecordDay.getSelectedRow(),9)).substring(3,5) )
        );
        Date date1 = calendar.getTime();
        ftfDate.setValue(date1);

        // Форматирующий объект даты
        DateFormatter timeFormatter = new DateFormatter(new SimpleDateFormat("HH:mm"));
        timeFormatter.setAllowsInvalid(false);
        timeFormatter.setOverwriteMode(true);
        // Создание форматированного текстового поля даты
        JFormattedTextField ftfTime = new JFormattedTextField(timeFormatter);
        ftfTime.setColumns(32);
        ftfTime.setValue(date1);

        JPanel panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.add(new JLabel("Услуга:"));
        panel.add(new JScrollPane(CBServices));

        JButton addClients=new JButton("Добавить клиента");
        addClients.setBackground(new Color(176,224,230));
        addClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameClients.addCliens();
                DCBMClients.removeAllElements();
                for (int i = 0; i < FrameClients.TMClients.getRowCount(); i++) {
                    String s=FrameClients.TMClients.getValueAt(i,1)+"  "+FrameClients.TMClients.getValueAt(i,2)+"  "+FrameClients.TMClients.getValueAt(i,3);
                    DCBMClients.addElement(s);
                }
                CBClients.setSelectedIndex(DCBMClients.getSize()-1);
            }
        });
        JPanel pan=new JPanel(new BorderLayout());
        pan.setBackground(Color.white);
        pan.add(new JLabel("Клиент:"),BorderLayout.WEST);
        pan.add(addClients,BorderLayout.EAST);
        panel.add(pan);
        panel.add(new JScrollPane(CBClients));

        panel.add(new JLabel("Сотрудник:"));
        panel.add(new JScrollPane(CBEmployee));
        panel.add(new JLabel("Дата:"));
        panel.add(ftfDate);
        panel.add(new JLabel("Время:"));
        panel.add(ftfTime);
        panel.add(new JLabel("Комментарий:"));
        JTextField comment=new JTextField();
        comment.setText(String.valueOf(tableRecordDay.getValueAt(tableRecordDay.getSelectedRow(),10)));
        panel.add(comment);

        JDialog dialog=new JDialog();

        JButton add=new JButton("Изменить");
        add.setBackground(new Color(176,224,230));
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
                if(date.after(date2)){
                    JOptionPane.showMessageDialog(dialog,
                            " Нельзя записывать на прошлое !",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }else {
                    FrameRecord.TMRecord.changeRow(Integer.parseInt(String.valueOf(TMRecordDay.getValueAt(tableRecordDay.getSelectedRow(), 0))), new Record(
                            FrameServices.TMServices.getRow(String.valueOf(FrameServices.TMServices.getValueAt(CBServices.getSelectedIndex(), 0))),
                            FrameClients.TMClients.getRow(Integer.valueOf(String.valueOf(FrameClients.TMClients.getValueAt(CBClients.getSelectedIndex(), 0)))),
                            FrameEmployee.TMEmployee.getRow(Integer.valueOf(String.valueOf(FrameEmployee.TMEmployee.getValueAt(CBEmployee.getSelectedIndex(), 0)))),
                            ftfDate.getText(),
                            ftfTime.getText(),
                            comment.getText())
                    );
                    dialog.dispose();
                    JOptionPane.showMessageDialog(dialog,
                            " Запись успешно изменена!",
                            "Уведомление",
                            JOptionPane.INFORMATION_MESSAGE);
                    UpdateTM.updateTM();
                }
            }
        });

        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        p.add(panel,BorderLayout.CENTER);
        p.add(add,BorderLayout.SOUTH);
        p.setBackground(Color.white);
        p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        dialog.setTitle("Редактирование записи");
        dialog.setLayout(new BorderLayout());
        dialog.add(p);
        dialog.setModal(true);
        dialog.setSize(1000,450);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

}
