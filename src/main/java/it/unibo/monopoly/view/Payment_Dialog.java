package it.unibo.monopoly.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Payment_Dialog extends JDialog{

    public Payment_Dialog (int paidImport, boolean succesfull){
        this.setSize(200, 150);
        this.setLocation(150, 200);
        String state = "";
        if (succesfull) {
            state = "succesfully";
        }else{
            state = "unsuccesfully";
        }
        JTextArea paymentComplete = new JTextArea("the paymente of " + paidImport + "\nhas been " + state + " made");
        JButton closeButton = new JButton("exit");
        ActionListener closeListener = e-> {
            this.dispose();
        };
        closeButton.addActionListener(closeListener);
        JPanel paymentCompletePane = new JPanel();
        paymentCompletePane.add(paymentComplete);
        paymentCompletePane.add(closeButton);

        this.getContentPane().add(paymentCompletePane);
        this.setVisible(true);
        
    }

}
