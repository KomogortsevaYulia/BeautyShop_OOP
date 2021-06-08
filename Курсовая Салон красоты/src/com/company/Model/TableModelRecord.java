package com.company.Model;

import com.company.Essence.Record;
import com.company.Essence.Work;
import com.company.View.FrameRecordDay;
import com.company.View.MainFrame;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelRecord extends AbstractTableModel {

    private List<Record> data;

    public TableModelRecord(){
        DBWorker.initDB();
        data=DBWorker.selectRecord();
        DBWorker.closeDB();
    }
    public void update(){
        DBWorker.initDB();
        data=DBWorker.selectRecord();
        DBWorker.closeDB();
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 12;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Record work = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return work.getId();
            case 1:
                return work.getServices().getName();
            case 2:
                return work.getServices().getPrice();
            case 3:
                return work.getClients().getSurname();
            case 4:
                return work.getClients().getName();
            case 5:
                return work.getClients().getMiddle();
            case 6:
                return work.getEmployee().getSurname();
            case 7:
                return work.getEmployee().getName();
            case 8:
                return work.getEmployee().getMiddle();
            case 9:
                return work.getData();
            case 10:
                return work.getTime();
            case 11:
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
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
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
        }
        return null;
    }
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Название услуги";
            case 2:
                return "Стоимость";
            case 3:
                return "Фамилия клиента";
            case 4:
                return "Имя клиента";
            case 5:
                return "Отчество клиента";
            case 6:
                return "Фамилия сотрудника";
            case 7:
                return "Имя сотрудника";
            case 8:
                return "Отчество сотрудника";
            case 9:
                return "Дата";
            case 10:
                return "Время";
            case 11:
                return "Комментарий";
        }
        return null;
    }

    public Record getRow(int id){
        for (Record c:data
        ) {
            if(c.getId() == id) {
                return c;
            }
        }
        return null;
    }
    public List<Record> getList(){
        return data;
    }
    public void addRow(Record p) {

        DBWorker.initDB();
        DBWorker.addRecord(p);
        DBWorker.closeDB();
        UpdateTM.updateTM();
        int max=0;
        for (Record r:data
             ) {
            if(r.getId()>max)max=r.getId();
        }
        Record record= getRow(max);
        Сancellation.addLog("Таблица \"Записи\" "+" Добавили запись: ID:"+record.getId()+
                " Услуга: Название: "+record.getServices().getName()+" Стоимость: "+record.getServices().getPrice()+
                " ФИО клиента: "+record.getClients().getSurname()+" "+record.getClients().getName()+" "+record.getClients().getMiddle()+
                " ФИО сотрудника: "+record.getEmployee().getSurname()+" "+record.getEmployee().getName()+" "+record.getEmployee().getMiddle()+
                " Дата: "+record.getData()+
                " Время: "+record.getTime()+
                " Комментарий: "+record.getComments());

    }
    public void deleteRow(int[] id){
        for (int i = 0; i < id.length; i++) {
            Record record= getRow(id[i]);
            Сancellation.addLog("Таблица \"Записи\" "+" Удалили запись: ID:"+record.getId()+
                    " Услуга: Название: "+record.getServices().getName()+" Стоимость: "+record.getServices().getPrice()+
                    " ФИО клиента: "+record.getClients().getSurname()+" "+record.getClients().getName()+" "+record.getClients().getMiddle()+
                    " ФИО сотрудника: "+record.getEmployee().getSurname()+" "+record.getEmployee().getName()+" "+record.getEmployee().getMiddle()+
                    " Дата: "+record.getData()+
                    " Время: "+record.getTime()+
                    " Комментарий: "+record.getComments());
        }
        DBWorker.initDB();
        DBWorker.deleteRecord(id);
        DBWorker.closeDB();
        UpdateTM.updateTM();

    }
    public void changeRow(int id,Record e){
        List<Record> copy=data;

        DBWorker.initDB();
        DBWorker.changeRecord(id,e);
        DBWorker.closeDB();

        for (int i = 0; i < copy.size(); i++) {
            if(id==copy.get(i).getId()) {
                Record record = data.get(i);
                Сancellation.addLog("Таблица \"Записи\" " + " Изменили запись: ID:" + record.getId() +
                        " Услуга: Название: " + record.getServices().getName() + " Стоимость: " + record.getServices().getPrice() +
                        " ФИО клиента: " + record.getClients().getSurname() + " " + record.getClients().getName() + " " + record.getClients().getMiddle() +
                        " ФИО сотрудника: " + record.getEmployee().getSurname() + " " + record.getEmployee().getName() + " " + record.getEmployee().getMiddle() +
                        " Дата: " + record.getData() +
                        " Время: " + record.getTime() +
                        " Комментарий: " + record.getComments()+
                        "На следующие данные: "+
                                " Услуга: Название: " + e.getServices().getName() + " Стоимость: " + e.getServices().getPrice() +
                                " ФИО клиента: " + e.getClients().getSurname() + " " + e.getClients().getName() + " " + e.getClients().getMiddle() +
                                " ФИО сотрудника: " + e.getEmployee().getSurname() + " " + e.getEmployee().getName() + " " + e.getEmployee().getMiddle() +
                                " Дата: " + e.getData() +
                                " Время: " + e.getTime() +
                                " Комментарий: " + e.getComments()
                        );
            }
        }
        UpdateTM.updateTM();
    }
}
