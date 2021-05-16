package com.company.View;

import javax.swing.*;

public class DialogSettings extends JDialog {
    public DialogSettings(){
        JDialog dialog=new JDialog();



        dialog.setTitle("Настройки");
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setSize(400,250);
        dialog.setVisible(true);
    }
}
