package com.company.Model;

import com.company.Essence.*;
import com.company.Essence.Record;
import com.company.View.FrameClients;
import com.company.View.RewardsProgram;

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
        FrameClients.TMClients.update();
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

    public void addRow(Work e){
        DBWorker.initDB();
        DBWorker.addPerformedWork(e);
        float p=(e.getClients().getPoint()-e.getPoint()+e.getIncome()* RewardsProgram.getPercent()/100);
        //DBWorker.changeClients(e.getClients().getId(),(e.getClients().getPoint()-e.getPoint()+e.getIncome()* RewardsProgram.getPercent()/100));
        DBWorker.closeDB();
        update();
    }
    public void deleteRow(int[] id){
        DBWorker.initDB();
        DBWorker.deletePerformedWork(id);
        //DBWorker.changeClients(id,(e.getClients().getPoint()-e.getPoint()+e.getIncome()* RewardsProgram.getPercent()));
        DBWorker.closeDB();
        update();
    }
    public void changeRow(int id,Work e){
        DBWorker.initDB();
        DBWorker.changePerformedWork(id,e);
     //   DBWorker.changeClients(id,(e.getClients().getPoint()-e.getPoint()+e.getIncome()* RewardsProgram.getPercent()));
        DBWorker.closeDB();
        update();
    }

}
