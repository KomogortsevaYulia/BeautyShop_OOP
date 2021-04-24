package com.company;

import com.company.Model.Clients;
import com.company.Model.DBWorker;
import com.company.Model.Employee;
import com.company.Model.Services;
import com.company.View.MyFrame;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        MyFrame frame=new MyFrame();
        frame.setVisible(true);

        DBWorker.initDB();
	    /*DBWorker.deleteServices();
        DBWorker.addServices(new Services("Стрижка",500));
        DBWorker.addServices(new Services("Покраска",2000));
        DBWorker.addServices(new Services("Маникюр",1200));
        DBWorker.addServices(new Services("Педикюр",1000));
        DBWorker.addServices(new Services("Стрижка",500));
        DBWorker.addServices(new Services("Покраска",2000));
        DBWorker.addServices(new Services("Маникюр",1200));
        DBWorker.addServices(new Services("Педикюр",1000));
        DBWorker.addServices(new Services("Стрижка",500));
        DBWorker.addServices(new Services("Покраска",2000));
        DBWorker.addServices(new Services("Маникюр",1200));
        DBWorker.addServices(new Services("Педикюр",1000));
        List<Services> l=DBWorker.getServices();
        System.out.println("ID "+"Name "+"Price");
        for (Services s:l
             ) {
            System.out.println(s.getId()+" "+s.getName()+" "+s.getPrice());
        }
        DBWorker.deleteClients();
        DBWorker.addClients(new Clients("Орлова","Ольга","Отчество","24.04.2021","email@email",89144699999L));
        DBWorker.addClients(new Clients("Мухина","Мария","Отчество2","24.04.2021","email2@email",89144697777L));
        DBWorker.addClients(new Clients("Фамилия3","Имя3","Отчество3","10.11.08","email3@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия4","Имя4","Отчество4","08.11.08","email4@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия","Имя","Отчество","20.11.08","email@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия2","Имя2","Отчество2","02.11.08","email2@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия3","Имя3","Отчество3","10.11.08","email3@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия4","Имя4","Отчество4","08.11.08","email4@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия","Имя","Отчество","20.11.08","email@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия2","Имя2","Отчество2","02.11.08","email2@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия3","Имя3","Отчество3","10.11.08","email3@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия4","Имя4","Отчество4","08.11.08","email4@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия","Имя","Отчество","20.11.08","email@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия2","Имя2","Отчество2","02.11.08","email2@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия3","Имя3","Отчество3","10.11.08","email3@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия4","Имя4","Отчество4","08.11.08","email4@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия3","Имя3","Отчество3","10.11.08","email3@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия4","Имя4","Отчество4","08.11.08","email4@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия","Имя","Отчество","20.11.08","email@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия2","Имя2","Отчество2","02.11.08","email2@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия3","Имя3","Отчество3","10.11.08","email3@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия4","Имя4","Отчество4","08.11.08","email4@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия3","Имя3","Отчество3","10.11.08","email3@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия4","Имя4","Отчество4","08.11.08","email4@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия","Имя","Отчество","20.11.08","email@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия2","Имя2","Отчество2","02.11.08","email2@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия3","Имя3","Отчество3","10.11.08","email3@email",89144699999L));
        DBWorker.addClients(new Clients("Фамилия4","Имя4","Отчество4","08.11.08","email4@email",89144699999L));

        List<Clients> c=DBWorker.getClients();
        System.out.println("ID_клиента Фамилия Имя Отчество ДР email phone");
        for (Clients s:c
        ) {
            System.out.println(s.getId()+"  "+s.getSurname()+"  "+s.getName()+"  "+s.getMiddle()+"  "+s.getBirthdate()+"  "+s.getEmail()+"  "+s.getPhone());
        }*/
        DBWorker.deleteEmployee();
        DBWorker.addEmployee(new Employee("Фам","Имя","Отч","14.08.99","Парикмахер",89144699999L));
        DBWorker.addEmployee(new Employee("Фам1","Имя1","Отч1","08.02.99","Колорист",89144699999L));
        DBWorker.addEmployee(new Employee("Фам2","Имя2","Отч2","17.09.01","Мастер ногтевого сервиса",89144699999L));
        DBWorker.addEmployee(new Employee("Фам3","Имя3","Отч3","28.12.99","Лешмейкер",89144699999L));
        DBWorker.addEmployee(new Employee("Фам","Имя","Отч","14.08.99","Парикмахер",89144699999L));
        DBWorker.addEmployee(new Employee("Фам1","Имя1","Отч1","08.02.99","Колорист",89144699999L));
        DBWorker.addEmployee(new Employee("Фам2","Имя2","Отч2","17.09.01","Мастер ногтевого сервиса",89144699999L));
        DBWorker.addEmployee(new Employee("Фам3","Имя3","Отч3","28.12.99","Лешмейкер",89144699999L));
        DBWorker.addEmployee(new Employee("Фам","Имя","Отч","14.08.99","Парикмахер",89144699999L));
        DBWorker.addEmployee(new Employee("Фам1","Имя1","Отч1","08.02.99","Колорист",89144699999L));
        DBWorker.addEmployee(new Employee("Фам2","Имя2","Отч2","17.09.01","Мастер ногтевого сервиса",89144699999L));
        DBWorker.addEmployee(new Employee("Фам3","Имя3","Отч3","28.12.99","Лешмейкер",89144699999L));
        DBWorker.addEmployee(new Employee("Фам","Имя","Отч","14.08.99","Парикмахер",89144699999L));
        DBWorker.addEmployee(new Employee("Фам1","Имя1","Отч1","08.02.99","Колорист",89144699999L));
        DBWorker.addEmployee(new Employee("Фам2","Имя2","Отч2","17.09.01","Мастер ногтевого сервиса",89144699999L));
        DBWorker.addEmployee(new Employee("Фам3","Имя3","Отч3","28.12.99","Лешмейкер",89144699999L));


        /*List<Employee> e=DBWorker.getEmployee();
        System.out.println("ID_сотрудника Фамилия Имя Отчество ДР должность phone");
        for (Employee s:e
        ) {
            System.out.println(s.getId()+"  "+s.getSurname()+"  "+s.getName()+"  "+s.getMiddle()+"  "+s.getBirthdate()+"  "+s.getPost()+"  "+s.getPhone());
        }*/
        DBWorker.closeDB();
    }
}
