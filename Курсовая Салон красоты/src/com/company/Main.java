package com.company;

import com.company.Essence.Clients;
import com.company.Essence.Employee;
import com.company.Essence.Services;
import com.company.Model.DBWorker;
import com.company.View.FrameClients;
import com.company.View.FramePerformedWork;
import com.company.View.MainFrame;
import org.sqlite.core.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //MainFrame m=new MainFrame();
        DBWorker.initDB();
        DBWorker.changeClients(1,55);
        DBWorker.changeClientsEasy(1,40);
        DBWorker.closeDB();
    }
}
