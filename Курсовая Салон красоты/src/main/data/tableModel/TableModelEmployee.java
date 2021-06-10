package main.data.tableModel;

import main.essence.Employee;
import main.data.database.DBWorker;
import main.data.log.Log;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelEmployee extends AbstractTableModel {
    private List<Employee> data;

    public TableModelEmployee(){
        DBWorker.initDB();
        data=DBWorker.selectEmployee();
        DBWorker.closeDB();
    }
    public void update(){
        DBWorker.initDB();
        data=DBWorker.selectEmployee();
        DBWorker.closeDB();
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return employee.getId();
            case 1:
                return employee.getSurname();
            case 2:
                return employee.getName();
            case 3:
                return employee.getMiddle();
            case 4:
                return employee.getBirthdate();
            case 5:
                return employee.getPost();
            case 6:
                return employee.getPhone();
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
                return Long.class;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Фамилия";
            case 2:
                return "Имя";
            case 3:
                return "Отчество";
            case 4:
                return "Дата рождения";
            case 5:
                return "Должность";
            case 6:
                return "Телефон";
        }
        return null;
    }

    public Employee getRow(int id){
        for (Employee c:data
        ) {
            if(c.getId() == id) {
                return c;
            }
        }
        return null;
    }
    public List<Employee> getList(){
        return data;
    }
    public String selectRowPhone(int id){
        for (Employee c:data
        ) {
            if(c.getId() == id) {
                return c.getPhone();
            }
        }
        return null;
    }
    public void addRow(Employee p){
        DBWorker.initDB();
        DBWorker.addEmployee(p);
        DBWorker.closeDB();
        UpdateTM.updateTM();
        this.update();
        int id=0;
        for (Employee e:data
             ) {
            if(e.getId()>id){
                id=e.getId();
            }
        }
        for (Employee c:data
        ) {
            if(c.getId()==id)
            {
                Log.addLog("Таблица \"Сотрудники\"  Добавили сотрудника: ID:"+c.getId()+
                        " ФИО: "+c.getSurname()+" "+c.getName()+" "+c.getMiddle()+
                        " Телефон: "+c.getPhone()+" День рождения: "+c.getBirthdate()+ " Должность: "+c.getPost());
            }
        }
    }
    public void deleteRow(int[] id){
        for (int i = 0; i < id.length; i++) {
            for (Employee c:data
            ) {
                if(id[i]==c.getId()){
                    Log.addLog("Таблица \"Сотрудники\"  Удалили данные сотрудника: ID:"+c.getId()+
                            " ФИО: "+c.getSurname()+" "+c.getName()+" "+c.getMiddle()+
                            " Телефон: "+c.getPhone()+" День рождения: "+c.getBirthdate()+ " Должность: "+c.getPost());
                }
            }
        }
        DBWorker.initDB();
        DBWorker.deleteEmployee(id);
        DBWorker.closeDB();
        UpdateTM.updateTM();
    }
    public void changeRow(int id,Employee e){
        Employee employee = null;
        for (Employee c:data
        ) {
            if(id==c.getId()){
                employee=c;
            }
        }
        DBWorker.initDB();
        DBWorker.changeEmployee(id,e);
        DBWorker.closeDB();
        UpdateTM.updateTM();
        for (Employee c:data
        ) {
            if(c.getId()==id)
            {
                Log.addLog("Таблица \"Сотрудники\"   Изменили данные сотрудника: ID:"+c.getId()+
                        " ФИО: "+employee.getSurname()+" "+employee.getName()+" "+employee.getMiddle()+
                        " Телефон: "+employee.getPhone()+" День рождения: "+employee.getBirthdate()+ " Должность: "+employee.getPost()+
                        " На следующие данные:"+" ФИО: "+c.getSurname()+" "+c.getName()+" "+c.getMiddle()+
                        " Телефон: "+c.getPhone()+" День рождения: "+c.getBirthdate()+ " Должность: "+c.getPost());
            }
        }
    }
}
