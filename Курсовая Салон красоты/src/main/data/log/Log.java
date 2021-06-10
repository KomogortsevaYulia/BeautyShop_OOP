package main.data.log;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Log {

    public static void addLog(String s){
        try(FileWriter writer = new FileWriter("src/main/data/log/Log.txt", true))
        {
            PrintWriter writer2 = new PrintWriter(writer);
            writer2.println("["+new Date()+"]      "+s);
            writer2.close();
        }
        catch( IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void DialogСancellation(){

        List<String> lis=new ArrayList<String>();
        try {
            File file = new File("src/main/data/log/Log.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            lis.add(line);
            while (line != null) {
                lis.add(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] list=new String[lis.size()];
        for (int i = 0; i < lis.size(); i++) {
            list[i]=lis.get(i);
        }

        JDialog dialog=new JDialog();
        dialog.setLayout(new BorderLayout());
        // Создание панели
        JPanel contents = new JPanel();
        contents.setBorder(new EmptyBorder(20,20,20,20));

        JList list1 = new JList(list);
        // Размещение компонентов в панели
        JScrollPane p=new JScrollPane(list1);
        p.setPreferredSize(new Dimension(950,430));
        contents.add(p);
        list1.setLayoutOrientation(JList.VERTICAL);

        dialog.setTitle("Логирование");
        dialog.add(contents,BorderLayout.CENTER);
        dialog.setModal(true);
        dialog.setSize(1000,500);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
