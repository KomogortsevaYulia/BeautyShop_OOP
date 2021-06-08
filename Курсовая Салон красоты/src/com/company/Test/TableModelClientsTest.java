package com.company.Test;

import com.company.Essence.Clients;
import com.company.Essence.Employee;
import com.company.Model.DBWorker;
import com.company.Model.TableModelClients;
import com.company.Model.TableModelEmployee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableModelClientsTest {

    @Test
    void selectRowPhone() {
        TableModelClients TMClients=new TableModelClients();
        Clients e=new Clients("Федотова","Мария","Ивановна","25.05","мастер","+4-752-758-20-00",0);
        TMClients.addRow(e);
        TMClients.update();
        DBWorker.initDB();
        List<Clients> list=DBWorker.selectClients();
        DBWorker.closeDB();
        int maxId=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId()>maxId){
                maxId=list.get(i).getId();
            }
        }

        Assertions.assertEquals(e.getPhone(), TMClients.selectRowPhone(maxId));
        int [] id = new int[1];
        id[0]=maxId;
        TMClients.deleteRow(id);
    }

    @Test
    void addRow() {
        Clients e=new Clients("Федотова","Мария","Ивановна","25.05","e","+4-752-758-20-00",0);

        TableModelClients TMClients=new TableModelClients();
        List<Clients> listExpected=TMClients.getList();
        listExpected.add(e);

        TMClients.addRow(e);
        List<Clients> listActual=TMClients.getList();

        Assertions.assertEquals(listExpected, listActual);

        DBWorker.initDB();
        List<Clients> list=DBWorker.selectClients();
        DBWorker.closeDB();
        int maxId=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId()>maxId){
                maxId=list.get(i).getId();
            }
        }
        int [] id = new int[1];
        id[0]=maxId;
        TMClients.deleteRow(id);
    }

    @Test
    void changeRow() {
        Clients e=new Clients("Федотова","Мария","Ивановна","25.05","мастер","+4-752-758-20-00",0);
        Clients e2=new Clients("Федотова","Ольга","Иннокентьевна","25.05","мастер","+4-752-758-20-00",0);

        TableModelClients TMClients=new TableModelClients();
        TMClients.addRow(e);
        TMClients.update();
        List<Clients> list=TMClients.getList();
        int maxId=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId()>maxId){
                maxId=list.get(i).getId();
            }
        }

        TMClients.changeRow(maxId,e2);
        TMClients.update();
        Clients emp=TMClients.getRow(maxId);
        e2.setId(emp.getId());
        Assertions.assertEquals(e2.getId(),emp.getId() );
        Assertions.assertEquals(e2.getSurname(),emp.getSurname() );
        Assertions.assertEquals(e2.getName(),emp.getName() );
        Assertions.assertEquals(e2.getMiddle(),emp.getMiddle() );
        Assertions.assertEquals(e2.getPhone(),emp.getPhone() );
        Assertions.assertEquals(e2.getEmail(),emp.getEmail() );
        Assertions.assertEquals(e2.getBirthdate(),emp.getBirthdate() );
        Assertions.assertEquals(e2.getPoint(),emp.getPoint() );
        int [] id = new int[]{maxId};
        TMClients.deleteRow(id);
    }

    @Test
    void deleteRow() {
        Clients e=new Clients("q","q","q","505","мастер","888",10);
        TableModelClients TMClients=new TableModelClients();

        List<Clients> listExpected=TMClients.getList();

        TMClients.addRow(e);

        DBWorker.initDB();
        List<Clients> list=DBWorker.selectClients();
        DBWorker.closeDB();
        int maxId=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId()>maxId){
                maxId=list.get(i).getId();
            }
        }
        int [] id = new int[1];
        id[0]=maxId;
        TMClients.deleteRow(id);


        List<Clients> listActual =TMClients.getList();

        Assertions.assertEquals(listExpected, listActual);
    }
}