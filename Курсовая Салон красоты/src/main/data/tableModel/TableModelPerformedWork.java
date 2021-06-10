package main.data.tableModel;

import main.userInterface.table.FrameClients;
import main.userInterface.RewardsProgram;
import main.essence.Work;
import main.data.database.DBWorker;
import main.data.log.Log;
import org.sqlite.core.DB;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelPerformedWork extends AbstractTableModel {

    private List<Work> data;

    public TableModelPerformedWork(){
        DBWorker.initDB();
        data=DBWorker.selectPerformedWork();
        DBWorker.closeDB();
    }
    public void update(){
        DBWorker.initDB();
        data=DBWorker.selectPerformedWork();
        DBWorker.closeDB();
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 14;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Work work = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return work.getId();
            case 1:
                return work.getServices().getName();
            case 2:
                return work.getServices().getPrice();
            case 3:
                return work.getPoint();
            case 4:
                return work.getIncome();
            case 5:
                return work.getClients().getSurname();
            case 6:
                return work.getClients().getName();
            case 7:
                return work.getClients().getMiddle();
            case 8:
                return work.getEmployee().getSurname();
            case 9:
                return work.getEmployee().getName();
            case 10:
                return work.getEmployee().getMiddle();
            case 11:
                return work.getData();
            case 12:
                return work.getTime();
            case 13:
                return work.getComments();
        }
        return null;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Float.class;
            case 3:
                return Float.class;
            case 4:
                return Float.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            case 8:
                return String.class;
            case 9:
                return String.class;
            case 10:
                return String.class;
            case 11:
                return String.class;
            case 12:
                return String.class;
            case 13:
                return String.class;
        }
        return null;
    }
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID работы";
            case 1:
                return "Название услуги";
            case 2:
                return "Стоимость";
            case 3:
                return "Баллы";
            case 4:
                return "Доход";
            case 5:
                return "Фамилия клиента";
            case 6:
                return "Имя клиента";
            case 7:
                return "Отчество клиента";
            case 8:
                return "Фамилия сотрудника";
            case 9:
                return "Имя сотрудника";
            case 10:
                return "Отчество сотрудника";
            case 11:
                return "Дата";
            case 12:
                return "Время";
            case 13:
                return "Комментарий";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;}

    public List<Work> getList(){
        return data;
    }
    public Work getRow(int id){
        for (Work c:data
        ) {
            if(c.getId() == id) {
                return c;
            }
        }
        return null;
    }
    public void addRow(Work e){
            DBWorker.initDB();
            DBWorker.addPerformedWork(e);
            float p=(e.getClients().getPoint()-e.getPoint()+e.getIncome()* RewardsProgram.getPercent()/100);
            DBWorker.changeClientsEasy(e.getClients().getId(),(e.getClients().getPoint()-e.getPoint()+e.getIncome()* RewardsProgram.getPercent()/100));


            List<Work> list= DBWorker.selectPerformedWork();
            DBWorker.closeDB();
            this.update();
            UpdateTM.updateTM();
            int id=0;
            for (int i = 0; i < list.size(); i++) {
                if(id<list.get(i).getId()){
                    id=list.get(i).getId();
                }
            }
            Work work= getRow(id);
            Log.addLog("Таблица \"Выполненные работы\" "+" Добавили запись: ID:"+work.getId()+
                    " Услуга: Название: "+work.getServices().getName()+" Стоимость: "+work.getServices().getPrice()+
                    " Использовали баллов: "+work.getPoint()+
                    " Доход: "+work.getIncome()+
                    " ФИО клиента: "+work.getClients().getSurname()+" "+work.getClients().getName()+" "+work.getClients().getMiddle()+
                    " ФИО сотрудника: "+work.getEmployee().getSurname()+" "+work.getEmployee().getName()+" "+work.getEmployee().getMiddle()+
                    " Дата: "+work.getData()+
                    " Время: "+work.getTime()+
                    " Комментарий: "+work.getComments());
            Log.addLog("Таблица \"Клиенты\" "+" Пересчитали количество бонусов: ID клиента: "+
                    work.getClients().getId());

    }
    public void cancelRow(int id){
            DBWorker.initDB();
                DBWorker.cancelPerformedWork(id);
            DBWorker.closeDB();
            Work work=getRow(id);
            float point= FrameClients.TMClients.getRow(work.getClients().getId()).getPoint()+work.getPoint()-(work.getIncome()*RewardsProgram.getPercent()/100);
            if(point<0)point=0;
            DBWorker.initDB();
                DBWorker.changeClientsEasy(work.getClients().getId(),point);
            DBWorker.closeDB();
            UpdateTM.updateTM();
            Log.addLog("Таблица \"Выполненные работы\" "+" Сделали возврат средств: ID записи:"+work.getId()+
                    " Услуга: Название: "+work.getServices().getName()+" Стоимость: "+work.getServices().getPrice()+
                    " Использовали баллов: "+work.getPoint()+
                    " Доход: "+work.getIncome()+
                    " ФИО клиента: "+work.getClients().getSurname()+" "+work.getClients().getName()+" "+work.getClients().getMiddle()+
                    " ФИО сотрудника: "+work.getEmployee().getSurname()+" "+work.getEmployee().getName()+" "+work.getEmployee().getMiddle()+
                    " Дата: "+work.getData()+
                    " Время: "+work.getTime()+
                    " Комментарий: "+work.getComments());
            Log.addLog("Таблица \"Клиенты\" "+" Пересчитали количество бонусов: ID клиента: "+
                    work.getClients().getId());
    }
}
