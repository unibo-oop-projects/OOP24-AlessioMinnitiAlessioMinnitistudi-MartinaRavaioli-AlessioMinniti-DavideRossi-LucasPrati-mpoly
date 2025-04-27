package it.unibo.monopoly.view.api;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.monopoly.controller.api.GameboardLogic;
import it.unibo.monopoly.controller.impl.GameboardLogicImpl;

public class GameboardView extends JFrame{
    private final GameboardLogic logic;

    public GameboardView(int size){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*size, 70*size);
        this.logic=new GameboardLogicImpl();

        JPanel board = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(board);
        this.setVisible(true);
    }
    
}
