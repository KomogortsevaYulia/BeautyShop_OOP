package com.company.View;

import com.company.Essence.Services;
import com.company.Essence.Work;
import com.company.Model.DBWorker;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class FrameReport extends JFrame {
    public FrameReport(){

        JPanel panelInfo=new JPanel(new GridLayout());
        List<String[]> listRes=selectDataServices();
        JLabel l=new JLabel("Топ 3 услуги за все время:");
        panelInfo.add(l);
        JLabel l1=new JLabel("Название: "+listRes.get(0)[1]+" Общая сумма дохода: "+listRes.get(0)[2]+" Количество: "+listRes.get(0)[3]);
        panelInfo.add(l1);
        JLabel l2=new JLabel("Название: "+listRes.get(1)[1]+" Общая сумма дохода: "+listRes.get(1)[2]+" Количество: "+listRes.get(1)[3]);
        panelInfo.add(l2);

        setLayout(new BorderLayout());
        add(panelInfo,BorderLayout.CENTER);
        setSize( 700,700);
        setVisible(true);
        setTitle("Отчеты");
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo(null);
    }
    public static List selectDataServices(){
        List<Services> listServices=new ArrayList<Services>();
        List<Work> listWork=new ArrayList<Work>();
        List<String[]> listRes=new ArrayList<String[]>();
        DBWorker.initDB();
        listWork=DBWorker.selectPerformedWork();
        listServices=DBWorker.selectServices();
        DBWorker.closeDB();
        for (Services s:listServices) {
            int count=0;
            float income=0;
            for (Work w:listWork) {
                if(s.getName().equals(w.getServices().getName())){
                    count++;
                    income=income+w.getIncome();
                }
            }
            listRes.add(new String[]{s.getName(),String.valueOf(income),String.valueOf(count)});
        }
       listRes.sort(new Comparator<String[]>() {
           @Override
           public int compare(String[] o1, String[] o2) {
               return Integer.parseInt(o1[3])-Integer.parseInt(o2[3]);
           }
       });
        return listRes;
    }
}
