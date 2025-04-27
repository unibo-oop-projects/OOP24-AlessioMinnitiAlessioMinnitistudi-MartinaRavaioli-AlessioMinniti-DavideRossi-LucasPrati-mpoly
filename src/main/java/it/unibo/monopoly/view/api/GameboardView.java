package it.unibo.monopoly.view.api;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
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

        for (int i = 0; i < size; i++) {
            for(int j=0; j<size;j++){
                JPanel tile = new JPanel();
                
                if (logic.isBoardTile(i,j,size)) {
                    tile.setBorder(BorderFactory.createLineBorder(Color.black));
                    tile.setBackground(Color.white);
                } else {
                    tile.setBackground(Color.lightGray); // Centro non giocabile
                }
                board.add(tile);
            }
            
        }

        for (int i = 0; i < size; i++) {
            for(int j=0; j<size;j++){
                
            }
            
        }

        this.setVisible(true);
    }
    
}