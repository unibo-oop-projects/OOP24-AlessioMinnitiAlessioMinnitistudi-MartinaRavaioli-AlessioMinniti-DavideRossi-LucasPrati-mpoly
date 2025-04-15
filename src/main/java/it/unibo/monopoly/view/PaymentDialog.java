package it.unibo.monopoly.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * this class draws a Dialog panel that informs the player wether the payment has been succesful or not.
 */

public final class PaymentDialog extends JDialog {
    private static final long serialVersionUID = -6218820567019985015L;


    /**
     * the constuctor build the frame with all the elements.
     * @param paidImport the import that has been deposited in the players banck account
     * @param succesfull wether the payment has succeded or not
     */
    public PaymentDialog(final int paidImport, final boolean succesfull) {
        final int width = 200; 
        final int heigth = 150;
        this.setSize(width, heigth);
        this.setLocation(heigth, width);
        final String state;
        if (succesfull) {
            state = "succesfully";
        } else {
            state = "unsuccesfully";
        }
        final JTextArea paymentComplete = new JTextArea("the paymente of " + paidImport + "\nhas been " + state + " made");
        final JButton closeButton = new JButton("exit");
        final ActionListener closeListener = e -> {
            this.dispose();
        };
        closeButton.addActionListener(closeListener);
        final JPanel paymentCompletePane = new JPanel();
        paymentCompletePane.add(paymentComplete);
        paymentCompletePane.add(closeButton);

        this.getContentPane().add(paymentCompletePane);
        this.setVisible(true);

    }

}
