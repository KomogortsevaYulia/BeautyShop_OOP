package main.data.tableModel;

import main.essence.Services;
import main.data.database.DBWorker;
import main.data.log.Log;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelServices extends AbstractTableModel {

    private List<Services> data;

    public TableModelServices(){
        DBWorker.initDB();
        data=DBWorker.selectServices();
        DBWorker.closeDB();
    }
    public void update(){
        DBWorker.initDB();
        data=DBWorker.selectServices();
        DBWorker.closeDB();
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Services c= data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getName();
            case 1:
                return c.getPrice();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Название";
            case 1:
                return "Стоимость";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Services getRow(String s){
        for (Services serv:data
             ) {
            if(serv.getName().equals(s)){
                return serv;
            }
        }
        return null;
    }
    public List<Services> getList(){
        return data;
    }
    public boolean addRow(Services p){
        boolean exist = false;
        for (int i = 0; i < getRowCount(); i++) {
            if (
                    p.getName().equals(String.valueOf(getValueAt(i, 0))) &
                            p.getPrice() == Float.parseFloat(String.valueOf(getValueAt(i, 1)))
            ) {
                exist = true;
            }
        }
        if (exist) {
            return false;
        } else {
            DBWorker.initDB();
            DBWorker.addServices(p);
            DBWorker.closeDB();
            UpdateTM.updateTM();
            Log.addLog("Таблица \"Услуги\" " + " Добавили услугу: Название:" + p.getName() +
                    " Стоимость: " + p.getPrice());
            return true;
        }
    }

    public void changeRow(String name,Services s){
        Services services = null;
        for (Services c:data
        ) {
            if(name.equals(c.getName())){
                services=c;
            }
        }
        DBWorker.initDB();
        DBWorker.changeServices(name,s);
        DBWorker.closeDB();
        UpdateTM.updateTM();
        Log.addLog("Таблица \"Услуги\" " + " Изменили данные услуги: Название:" + services.getName() +
                " Стоимость: " + services.getPrice()+
                " На следующие данные: Название: "+s.getName()+" Стоимость: "+s.getPrice());
    }

    public void deleteRow(String[] name){
        for (int i = 0; i < name.length ; i++) {
            for (Services serv : data
            ) {
                if (serv.getName().equals(name[i])) {
                    Log.addLog("Таблица \"Услуги\" " + " Удалили услугу: Название:" + serv.getName() +
                            " Стоимость: " + serv.getPrice());
                }
            }
        }
        DBWorker.initDB();
        DBWorker.deleteServices(name);
        DBWorker.closeDB();
        UpdateTM.updateTM();
    }
}
