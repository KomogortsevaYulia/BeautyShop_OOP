package test.tableModel;

import main.essence.Clients;
import main.essence.Employee;
import main.essence.Services;
import main.essence.Work;
import main.data.database.*;
import main.data.tableModel.TableModelClients;
import main.data.tableModel.TableModelEmployee;
import main.data.tableModel.TableModelPerformedWork;
import main.data.tableModel.TableModelServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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
                100,
                4000,
                c,
                e,
                "08.06.2021",
                "12:00",
                ""
        );

        TableModelPerformedWork TMPerformedWork=new TableModelPerformedWork();

        TMPerformedWork.addRow(w);
        TMPerformedWork.update();
        int maxId=0;
        for (Work work:TMPerformedWork.getList()
             ) {
            if(work.getId()>maxId){
                maxId=work.getId();
            }
        }
        w.setId(maxId);
        List<Work> listActual=TMPerformedWork.getList();
        boolean r=false;
        for (int i = 0; i < listActual.size(); i++) {
            if(listActual.get(i).getId()==w.getId()){
                r=true;
            }
        }
        TMPerformedWork.update();
        List<Work> list=TMPerformedWork.getList();
        DBWorker.initDB();
        int [] id2 = new int[]{maxId};
        DBWorker.deleteWork(id2);
        DBWorker.closeDB();

        Assertions.assertTrue(r);
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
                100,
                4000,
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
    /*@Test
    void delRow() {
        DBWorker.initDB();
        int [] id2 = new int[]{53,55,56,57,62,64};
        DBWorker.deleteWork(id2);
        DBWorker.closeDB();
        TableModelPerformedWork TM=new TableModelPerformedWork();
    }*/
}