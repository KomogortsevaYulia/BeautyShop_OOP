package com.company.Test;

import com.company.Essence.*;
import com.company.Essence.Record;
import com.company.Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableModelRecordTest {

    @Test
    void addRow() {
        TableModelClients TMClients=new TableModelClients();
        Clients c=TMClients.getList().get(TMClients.getRowCount()-TMClients.getRowCount()/2);
        TableModelServices TMServices=new TableModelServices();
        Services s=TMServices.getList().get(TMServices.getRowCount()-TMServices.getRowCount()/2);
        TableModelEmployee TMEmployee=new TableModelEmployee();
        Employee e=TMEmployee.getList().get(TMEmployee.getRowCount()-TMEmployee.getRowCount()/2);
        Record record=new Record(
                s,
                c,
                e,
                "",
                "",
                ""
        )  ;

        TableModelRecord TMRecord=new TableModelRecord();
        List<Record> listExpected=TMRecord.getList();
        listExpected.add(record);

        TMRecord.addRow(record);
        List<Record> listActual=TMRecord.getList();

        Assertions.assertEquals(listExpected, listActual);
        TMRecord.update();
        List<Record> list=TMRecord.getList();
        int id=0;
        for (Record record1:list
        ) {
            if(record1.getId()>id){
                id=record1.getId();
            }
        }
        int [] id2 = new int[]{id};
        TMRecord.deleteRow(id2);
        TMRecord.update();
    }

    @Test
    void deleteRow() {
        TableModelClients TMClients=new TableModelClients();
        Clients c=TMClients.getList().get(TMClients.getRowCount()-TMClients.getRowCount()/2);
        TableModelServices TMServices=new TableModelServices();
        Services s=TMServices.getList().get(TMServices.getRowCount()-TMServices.getRowCount()/2);
        TableModelEmployee TMEmployee=new TableModelEmployee();
        Employee e=TMEmployee.getList().get(TMEmployee.getRowCount()-TMEmployee.getRowCount()/2);
        Record record=new Record(
                s,
                c,
                e,
                "08.06.2021",
                "11:00",
                ""
        )  ;

        TableModelRecord TMRecord=new TableModelRecord();
        List<Record> listExpected=TMRecord.getList();

        TMRecord.addRow(record);

        TMRecord.update();
        List<Record> list=TMRecord.getList();
        int id=0;
        for (Record record1:list
        ) {
            if(record1.getId()>id){
                id=record1.getId();
            }
        }
        int [] id2 = new int[]{id};
        TMRecord.deleteRow(id2);
        TMRecord.update();
        boolean eq=false;
        List<Record> listActual=TMRecord.getList();
        for (int i = 0; i < listActual.size(); i++) {
            if(listActual.get(i).equals(listExpected.get(i))){
                eq=true;
            }
        }
        Assertions.assertTrue(eq);

    }

    @Test
    void changeRow() {
        TableModelClients TMClients=new TableModelClients();
        Clients c=TMClients.getList().get(TMClients.getRowCount()-TMClients.getRowCount()/2);
        TableModelServices TMServices=new TableModelServices();
        Services s=TMServices.getList().get(TMServices.getRowCount()-TMServices.getRowCount()/2);
        Services s2=TMServices.getList().get(TMServices.getRowCount()-1);
        TableModelEmployee TMEmployee=new TableModelEmployee();
        Employee e=TMEmployee.getList().get(TMEmployee.getRowCount()-TMEmployee.getRowCount()/2);
        Record record=new Record(
                s,
                c,
                e,
                "08.06.2021",
                "11:00",
                ""
        )  ;
        Record record2=new Record(
                s2,
                c,
                e,
                "08.06.2021",
                "11:00",
                ""
        )  ;
        TableModelRecord TMRecord=new TableModelRecord();

        TMRecord.addRow(record);

        TMRecord.update();
        List<Record> list=TMRecord.getList();
        int id=0;
        for (Record record1:list
        ) {
            if(record1.getId()>id){
                id=record1.getId();
            }
        }
        TMRecord.changeRow(id,record2);
        TMRecord.update();
        boolean eq=false;
        record2.setId(id);
        List<Record> listActual=TMRecord.getList();
        for (int i = 0; i < listActual.size(); i++) {
            if(listActual.get(i).equals(record2)){
                eq=true;
            }
        }
        Assertions.assertTrue(eq);
        int [] id2 = new int[]{id};
        TMRecord.deleteRow(id2);
        TMRecord.update();
    }
}