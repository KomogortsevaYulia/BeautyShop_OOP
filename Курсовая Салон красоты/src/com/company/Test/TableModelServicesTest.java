package com.company.Test;

import com.company.Essence.Services;
import com.company.Model.TableModelServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableModelServicesTest {

    @Test
    void addRow() {
        Services s=new Services("po",2999);
        TableModelServices TMServices=new TableModelServices();
        List<Services> listExpected= TMServices.getList();
        listExpected.add(s);
        TMServices.addRow(s);
        List<Services> listActual=TMServices.getList();
        String[] id=new String[]{s.getName()};
        TMServices.deleteRow(id);
        TMServices.update();
        Assertions.assertEquals(listExpected, listActual);
    }

    @Test
    void changeRow() {
        Services s=new Services("po",2999);
        Services s2=new Services("re",1000);
        TableModelServices TMServices=new TableModelServices();

        TMServices.addRow(s);
        TMServices.update();
        TMServices.changeRow("po",s2);
        TMServices.update();
        List<Services> listActual=TMServices.getList();
        Assertions.assertEquals(s2.getName(), TMServices.getRow("re").getName());
        Assertions.assertEquals(s2.getPrice(), TMServices.getRow("re").getPrice());
        String[] id=new String[]{s2.getName()};
        TMServices.deleteRow(id);
        TMServices.update();
    }

    @Test
    void deleteRow() {
        Services s=new Services("po",2999);
        TableModelServices TMServices=new TableModelServices();
        List<Services> listExpected= TMServices.getList();
        TMServices.addRow(s);
        TMServices.update();
        String[] id=new String[]{s.getName()};
        TMServices.deleteRow(id);
        TMServices.update();
        List<Services> listActual=TMServices.getList();
        boolean ex=false;
        for (Services serv:listActual
             ) {
            if(serv==s){
                ex=false;
            }
            else{
                ex=true;
            }
        }
        Assertions.assertTrue(ex);
    }
}