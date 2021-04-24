package com.company.View;

import com.company.Model.Clients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.company.View.FrameServices.Services;

public class MyFrame extends JFrame {

    public MyFrame(){
        super("Салон красоты");

        JPanel right=new JPanel();
        right.setLayout(new GridLayout(2, 1, 0, 40));
        right.setBackground(Color.WHITE);

        /*UtilDateModel model = new UtilDateModel();
        //model.setDate(20,04,2014);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        // Don't know about the formatter, but there it is...
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());*/

        JButton Calendar=new JButton("Календарь");

        right.add(Calendar);
        right.add(PaneOther());
        right.setPreferredSize(new Dimension(300,500));
        right.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));

        JPanel Top=new JPanel();
        JLabel Name=new JLabel("Beauty - администратор");
        Name.setAlignmentX(Component.CENTER_ALIGNMENT);
        Top.add(Name,BorderLayout.CENTER);
        Top.setPreferredSize(new Dimension(1000,40));
        Top.setBackground(new Color(148,204,209));

        JPanel panel=new JPanel(new BorderLayout());
        panel.add(PanelCenter(),BorderLayout.CENTER);
        panel.add(right,BorderLayout.EAST);
        panel.add(Top,BorderLayout.NORTH);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panel.setBackground(Color.white);
        add(panel,BorderLayout.CENTER);
        setMinimumSize(new Dimension(1000,650));
        setLocationRelativeTo(null);
    }

    public static JPanel PaneOther(){
        JPanel Other =new JPanel();
        Other.setBackground(new Color(218,238,240));
        Other.setLayout(new FlowLayout());
        Other.add(new JLabel("Сегодня день рождение:           "));
        Date current = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String message = formatter.format(current);
        List<Clients> l=FrameClients.Clients.getData();
        for (Clients c:
             l) {
            if(message.equals(c.getBirthdate())){
                Other.add(new JLabel("Клиент: "+c.getSurname()+" "+c.getName()+" "+c.getPhone()));
            }
        }
        return Other;
    }

    public static JPanel PanelCenter(){

        /*JLabel Button1=new JLabel();
        try {
            BufferedImage image = ImageIO.read(new File("1.jpg"));
            Image scaled=image.getScaledInstance(120,120,Image.SCALE_DEFAULT);
            ImageIcon u=new ImageIcon(scaled);
            Button1.setIcon(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Кнопка 1");
            }
        });*/
        JButton Button1=new JButton("Клиенты");

        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameClients f=new FrameClients();
                f.setVisible(true);
            }
        });

        JButton Button2=new JButton("Новый клиент");
        Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameClients.CreateDialog(-1,new Clients("","","","","",0));
            }
        });
        JButton Button3=new JButton("Кнопка");
        JButton Button4=new JButton("Оказание услуги");
        JButton Button5=new JButton("Записи на день");
        JButton Button6=new JButton("Записи на месяц");
        JButton Button7=new JButton("Услуги");
        Button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameServices f=new FrameServices();
            }
        });
        JButton Button8=new JButton("Сотрудники");
        Button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameEmployee f=new FrameEmployee();
                f.setVisible(true);
            }
        });
        JButton Button9=new JButton("Кнопка");

        JPanel controlPane=new JPanel();
        GridLayout layout = new GridLayout(3, 3, 30, 30);
        controlPane.setLayout(layout);
        controlPane.setBackground(Color.white);

        controlPane.add(Button1);
        controlPane.add(Button2);
        controlPane.add(Button3);
        controlPane.add(Button4);
        controlPane.add(Button5);
        controlPane.add(Button6);
        controlPane.add(Button7);
        controlPane.add(Button8);
        controlPane.add(Button9);
        controlPane.setPreferredSize(new Dimension(700,500));
        controlPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
        return controlPane;
    }
}
/*class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}*/