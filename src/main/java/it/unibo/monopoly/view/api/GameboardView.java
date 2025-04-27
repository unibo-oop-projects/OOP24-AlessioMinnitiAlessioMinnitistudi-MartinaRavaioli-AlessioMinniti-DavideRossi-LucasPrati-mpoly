package it.unibo.monopoly.view.api;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.monopoly.controller.api.GameboardLogic;
import it.unibo.monopoly.controller.impl.GameboardLogicImpl;
import it.unibo.monopoly.model.gameboard.api.Tile;

public class GameboardView extends JFrame{
    private final GameboardLogic logic;
    private final List<JPanel> shapes=List.of(new PawnCircle(Color.RED),new PawnTriangle(Color.BLUE),new PawnSquare(Color.GREEN),new PawnSquare(Color.YELLOW));
    private final List<JPanel> tilesView=new ArrayList<>();
    
    public GameboardView(int size, int players, List<Tile> tiles){
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
                this.tilesView.add(tile);
            }
        }

        for(int i=0;i<tiles.size();i++){
            JPanel panel=this.tilesView.get(i);
            JPanel stripe = new JPanel();
            stripe.setPreferredSize(new Dimension(50, 10));
            stripe.setBackground(logic.getTileColor(tiles.get(i).getType()));
            panel.add(stripe, BorderLayout.NORTH);
        }

        for(int i=0; i < players; i++){
            JPanel panel = this.tilesView.get(0);
            panel.add(shapes.get(i));
        }


        this.setVisible(true);
    }
}