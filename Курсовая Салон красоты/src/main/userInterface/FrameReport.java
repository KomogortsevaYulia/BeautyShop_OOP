package main.userInterface;

import main.essence.Clients;
import main.essence.Employee;
import main.essence.Services;
import main.essence.Work;
import main.data.database.DBWorker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class FrameReport {
    public FrameReport(){
        JDialog d=new JDialog();
        JPanel panelInfo=new JPanel();

         List<String[]> list=selectDataServices();
        List<String[]> list2=selectDataClients();
        List<String[]> list3=selectDataEmployee();
        List<String[]> list4=selectDataServicesIncome();

        String[] res=new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i]="Название: "+list.get(i)[0]+"   Стоимость: "+list.get(i)[1]+"   Количество посещений: "+list.get(i)[2];
        }
        String[] res2=new String[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            res2[i]="ФИО: "+list2.get(i)[0]+"   "+list2.get(i)[1]+"   "+list2.get(i)[2]+"   Количество посещений: "+list2.get(i)[3];
        }
        String[] res3=new String[list3.size()];
        for (int i = 0; i < list3.size(); i++) {
            res3[i]="ФИО: "+list3.get(i)[0]+"   "+list3.get(i)[1]+"   "+list3.get(i)[2]+"   Количество посещений: "+list3.get(i)[3]+" Должность: "+list3.get(i)[4];
        }
        String[] res4=new String[list4.size()];
        for (int i = 0; i < list4.size(); i++) {
            res4[i]="Название: "+list4.get(i)[0]+"   Прибыль: "+list4.get(i)[1]+"   Количество посещений: "+list4.get(i)[2];
        }

        panelInfo.setLayout(new BoxLayout(panelInfo,BoxLayout.Y_AXIS));
        JTabbedPane Data = new JTabbedPane(JTabbedPane.TOP);
        JList paneEmployee = new JList(res3);
        JList paneServices = new JList(res);
        JList paneClients = new JList(res2);
        JList paneServicesIncome = new JList(res4);

        Data.addTab("Топ услуг по популярности", new JScrollPane(new JPanel(new BorderLayout()).add(paneServices)));
        Data.addTab("Топ услуг по прибыли", new JScrollPane(new JPanel(new BorderLayout()).add(paneServicesIncome)));
        Data.addTab("Топ клиентов",new JScrollPane(new JPanel(new BorderLayout()).add(paneClients)));
        Data.addTab("Топ сотрудников", new JScrollPane(new JPanel(new BorderLayout()).add(paneEmployee)));

        panelInfo.setBorder(new EmptyBorder(20,20,20,20));
        panelInfo.add(Data);
        d.setLayout(new BorderLayout());
        d.add(panelInfo,BorderLayout.CENTER);
        d.setSize( 800,400);
        d.setVisible(true);
        d.setTitle("Отчеты");
        d.setLocationRelativeTo(null);
        d.setModal(true);
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
            for (Work w:listWork) {
                if(s.getName().equals(w.getServices().getName())){
                    count++;
                }
            }
            listRes.add(new String[]{s.getName(), String.valueOf(s.getPrice()),String.valueOf(count)});
        }
       listRes.sort(new Comparator<String[]>() {
           @Override
           public int compare(String[] o1, String[] o2) {
               return Integer.parseInt(o2[2])-Integer.parseInt(o1[2]);
           }
       });
        return listRes;
    }
    public static List selectDataServicesIncome(){
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
            listRes.add(new String[]{s.getName(), String.valueOf(income),String.valueOf(count)});
        }
        listRes.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return (int) (Float.parseFloat(o2[1])-Float.parseFloat(o1[1]));
            }
        });
        return listRes;
    }
    public static List selectDataClients(){
        List<Clients> listClients=new ArrayList<Clients>();
        List<Work> listWork=new ArrayList<Work>();
        List<String[]> listRes=new ArrayList<String[]>();
        DBWorker.initDB();
        listWork=DBWorker.selectPerformedWork();
        listClients=DBWorker.selectClients();
        DBWorker.closeDB();
        for (Clients s:listClients) {
            int count=0;
            for (Work w:listWork) {
                if(s.getId()==w.getClients().getId()){
                    count++;
                }
            }
            listRes.add(new String[]{s.getSurname(),s.getName(),s.getMiddle(),String.valueOf(count)});
        }
        listRes.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.parseInt(o2[3])-Integer.parseInt(o1[3]);
            }
        });
        return listRes;
    }
    public static List selectDataEmployee(){
        List<String[]> listRes=new ArrayList<String[]>();
        DBWorker.initDB();
        List<Work>listWork=DBWorker.selectPerformedWork();
        List<Employee>  listEmployee=DBWorker.selectEmployee();
        DBWorker.closeDB();
        for (Employee s:listEmployee) {
            int count=0;
            for (Work w:listWork) {
                if(s.getId()==w.getEmployee().getId()){
                    count++;
                }
            }
            listRes.add(new String[]{s.getSurname(),s.getName(),s.getMiddle(),String.valueOf(count),s.getPost()});
        }
        listRes.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.parseInt(o2[3])-Integer.parseInt(o1[3]);
            }
        });
        return listRes;
    }
}
