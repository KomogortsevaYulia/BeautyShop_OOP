package test;

import main.essence.Work;
import main.data.tableModel.TableModelPerformedWork;
import main.userInterface.FrameReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class FrameReportTest {

    @Test
    void selectDataServices() {
        List<String[]> list= FrameReport.selectDataServices();
        String nameServices=list.get(0)[0];
        TableModelPerformedWork tmPW=new TableModelPerformedWork();
        List<Work> l=tmPW.getList();
        int count=0;
        float price=0;
        for (Work w:l
             ) {
            if(w.getServices().getName().equals(nameServices)){
                count++;
                price=w.getServices().getPrice();
            }
        }

        Assertions.assertEquals(String.valueOf(count),list.get(0)[2]);
        Assertions.assertEquals(String.valueOf(price),list.get(0)[1]);
    }

    @Test
    void selectDataServicesIncome() {
        List<String[]> list= FrameReport.selectDataServicesIncome();
        String nameServices=list.get(0)[0];
        TableModelPerformedWork tmPW=new TableModelPerformedWork();
        List<Work> l=tmPW.getList();
        int count=0;
        float income=0;
        for (Work w:l
        ) {
            if(w.getServices().getName().equals(nameServices)){
                count++;
                income=income+w.getIncome();
            }
        }

        Assertions.assertEquals(String.valueOf(count),list.get(0)[2]);
        Assertions.assertEquals(String.valueOf(income),list.get(0)[1]);
    }

    @Test
    void selectDataClients() {
        List<String[]> list= FrameReport.selectDataClients();
        TableModelPerformedWork tmPW=new TableModelPerformedWork();
        List<Work> l=tmPW.getList();
        int count=0;
       // float income=0;
        for (Work w:l
        ) {
            if(w.getClients().getName().equals(list.get(0)[1])
            &w.getClients().getSurname().equals(list.get(0)[0])&
                    w.getClients().getMiddle().equals(list.get(0)[2])
            ){
                count++;
               // income=income+w.getIncome();
            }
        }

        Assertions.assertEquals(String.valueOf(count),list.get(0)[3]);
    }

    @Test
    void selectDataEmployee() {
        List<String[]> list= FrameReport.selectDataEmployee();
        TableModelPerformedWork tmPW=new TableModelPerformedWork();
        List<Work> l=tmPW.getList();
        int count=0;
        for (Work w:l
        ) {
            if(w.getEmployee().getName().equals(list.get(0)[1])
                    &w.getEmployee().getSurname().equals(list.get(0)[0])&
                    w.getEmployee().getMiddle().equals(list.get(0)[2])
            ){
                count++;
            }
        }

        Assertions.assertEquals(String.valueOf(count),list.get(0)[3]);
    }
}