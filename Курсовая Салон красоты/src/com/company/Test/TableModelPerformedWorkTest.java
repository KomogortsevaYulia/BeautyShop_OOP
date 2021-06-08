package com.company.Test;

import com.company.Essence.Clients;
import com.company.Essence.Employee;
import com.company.Essence.Services;
import com.company.Essence.Work;
import com.company.Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableModelPerformedWorkTest {

    @Test
    void addRow() {
        TableModelClients TMClients=new TableModelClients();
        Clients c=TMClients.getList().get(TMClients.getRowCount()-TMClients.getRowCount()/2);
        TableModelServices TMServices=new TableModelServices();
        Services s=TMServices.getList().get(TMServices.getRowCount()-TMServices.getRowCount()/2);
        TableModelEmployee TMEmployee=new TableModelEmployee();
        Employee e=TMEmployee.getList().get(TMEmployee.getRowCount()-TMEmployee.getRowCount()/2);
        Work w=new Work(
                s,
                5000,
                40,
                c,
                e,
                "08.06.2021",
                "12:00",
                ""
        );

        TableModelPerformedWork TMPerformedWork=new TableModelPerformedWork();
        List<Work> listExpected=TMPerformedWork.getList();
        listExpected.add(w);

        TMPerformedWork.addRow(w);
        List<Work> listActual=TMPerformedWork.getList();

        Assertions.assertEquals(listExpected, listActual);
        TMPerformedWork.update();
        List<Work> list=TMPerformedWork.getList();
        int id=0;
        for (Work work:list
        ) {
            if(work.getId()>id){
                id=work.getId();
            }
        }
        DBWorker.initDB();
        int [] id2 = new int[]{id};
        DBWorker.deleteWork(id2);
        DBWorker.closeDB();

    }

    @Test
    void cancelRow() {
        TableModelClients TMClients=new TableModelClients();
        Clients c=TMClients.getList().get(TMClients.getRowCount()-TMClients.getRowCount()/2);
        TableModelServices TMServices=new TableModelServices();
        Services s=TMServices.getList().get(TMServices.getRowCount()-TMServices.getRowCount()/2);
        TableModelEmployee TMEmployee=new TableModelEmployee();
        Employee e=TMEmployee.getList().get(TMEmployee.getRowCount()-TMEmployee.getRowCount()/2);
        Work w=new Work(
                s,
                5000,
                40,
                c,
                e,
                "08.06.2021",
                "11:00",
                ""
        );

        TableModelPerformedWork TMPerformedWork=new TableModelPerformedWork();

        TMPerformedWork.addRow(w);
        TMPerformedWork.update();
        List<Work> list=TMPerformedWork.getList();
        int id=0;
        for (Work work:list
             ) {
            if(work.getId()>id){
                id=work.getId();
            }
        }
        TMPerformedWork.cancelRow(id);
        TMPerformedWork.update();
        List<Work> listActual=TMPerformedWork.getList();
        boolean ex=false;
        for (Work work:listActual
             ) {
            if(work.getId()==id){
                if(work.getIncome()==0 & work.getPoint()==0 & work.getComments().equals("Возврат средств")){
                    ex=true;
                }
            }
        }
        Assertions.assertTrue(ex);
        DBWorker.initDB();
        int [] id2 = new int[]{id};
        DBWorker.deleteWork(id2);
        DBWorker.closeDB();
    }
}