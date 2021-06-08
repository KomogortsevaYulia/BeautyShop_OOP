package com.company.Test;

import com.company.Essence.Employee;
import com.company.Model.DBWorker;
import com.company.Model.TableModelEmployee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableModelEmployeeTest {


    @Test
    void selectRowPhone() {
        TableModelEmployee TMEmployee=new TableModelEmployee();
        Employee e=new Employee("Федотова","Мария","Ивановна","25.05","мастер","+4-752-758-20-00");
        TMEmployee.addRow(e);
        TMEmployee.update();
        DBWorker.initDB();
        List<Employee> list=DBWorker.selectEmployee();
        DBWorker.closeDB();
        int maxId=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId()>maxId){
                maxId=list.get(i).getId();
            }
        }

        Assertions.assertEquals(e.getPhone(), TMEmployee.selectRowPhone(maxId));
        int [] id = new int[1];
        id[0]=maxId;
        TMEmployee.deleteRow(id);
    }

    @Test
    void addRow() {
        Employee e=new Employee("Федотова","Мария","Ивановна","25.05","мастер","+4-752-758-20-00");

        TableModelEmployee TMEmployee=new TableModelEmployee();
        List<Employee> listExpected=TMEmployee.getList();
        listExpected.add(e);

        TMEmployee.addRow(e);
        List<Employee> listActual=TMEmployee.getList();

        Assertions.assertEquals(listExpected, listActual);

        DBWorker.initDB();
        List<Employee> list=DBWorker.selectEmployee();
        DBWorker.closeDB();
        int maxId=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId()>maxId){
                maxId=list.get(i).getId();
            }
        }
        int [] id = new int[1];
        id[0]=maxId;
        TMEmployee.deleteRow(id);
    }

    @Test
    void deleteRow() {

        Employee e=new Employee("q","q","q","505","мастер","888");
        TableModelEmployee TMEmployee=new TableModelEmployee();

        List<Employee> listExpected=TMEmployee.getList();

        TMEmployee.addRow(e);

        DBWorker.initDB();
        List<Employee> list=DBWorker.selectEmployee();
        DBWorker.closeDB();
        int maxId=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId()>maxId){
                maxId=list.get(i).getId();
            }
        }
        int [] id = new int[1];
        id[0]=maxId;
        TMEmployee.deleteRow(id);


        List<Employee> listActual =TMEmployee.getList();

        Assertions.assertEquals(listExpected, listActual);
    }

    @Test
    void changeRow() {
        Employee e=new Employee("Федотова","Мария","Ивановна","25.05","мастер","+4-752-758-20-00");
        Employee e2=new Employee("Федотова","Ольга","Иннокентьевна","25.05","мастер","+4-752-758-20-00");

        TableModelEmployee TMEmployee=new TableModelEmployee();
        TMEmployee.addRow(e);
        TMEmployee.update();
        List<Employee> list=TMEmployee.getList();
        int maxId=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId()>maxId){
                maxId=list.get(i).getId();
            }
        }

        TMEmployee.changeRow(maxId,e2);
        TMEmployee.update();
        Employee emp=TMEmployee.getRow(maxId);
        e2.setId(emp.getId());
        Assertions.assertEquals(e2.getId(),emp.getId() );
        Assertions.assertEquals(e2.getSurname(),emp.getSurname() );
        Assertions.assertEquals(e2.getName(),emp.getName() );
        Assertions.assertEquals(e2.getMiddle(),emp.getMiddle() );
        Assertions.assertEquals(e2.getPhone(),emp.getPhone() );
        Assertions.assertEquals(e2.getPost(),emp.getPost() );
        Assertions.assertEquals(e2.getBirthdate(),emp.getBirthdate() );

        int [] id = new int[]{maxId};
        TMEmployee.deleteRow(id);
    }
}