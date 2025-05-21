package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.monopoly.controller.api.GameboardLogic;
import it.unibo.monopoly.controller.impl.GameboardLogicImpl;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.view.api.GameboardView;

/**
    * board view.
*/
public class GameboardViewImpl extends JFrame implements GameboardView {
    private GameboardLogic logic;
    private final List<JPanel> shapes=List.of(new PawnCircle(Color.RED),new PawnTriangle(Color.BLUE),new PawnSquare(Color.GREEN),new PawnSquare(Color.YELLOW));
    private final List<JPanel> tilesView=new ArrayList<>();
    private final int size;
    private final Map<Integer,Position> pawnPositions=new HashMap<>();

    public GameboardViewImpl(int size) {
        this.size = size;
    }

    @Override
    public final void show(List<Player> players, List<Tile> tiles) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.logic=new GameboardLogicImpl();

        JPanel board = new JPanel(new GridLayout(this.size,this.size));
        this.getContentPane().add(board);

        for (int i = 0; i < this.size; i++) {
            for(int j=0; j<this.size;j++) {
                JPanel tile = new JPanel();
                
                if (logic.isBoardTile(i,j,this.size)) {
                    tile.setBorder(BorderFactory.createLineBorder(Color.black));
                    tile.setBackground(Color.white);
                    this.tilesView.add(tile);
                } else {
                    tile.setBackground(Color.lightGray); // Centro non giocabile
                }
                board.add(tile);
                
            }
        }

        for (int i=0;i<40;i++) {
            JPanel panel=this.tilesView.get(i);
            JPanel stripe = new JPanel();
            stripe.setPreferredSize(new Dimension(150, 10));
            // stripe.setBackground(logic.getTileColor(tiles.get(i).getType()));
            panel.add(stripe, BorderLayout.NORTH);
            JLabel label = new JLabel("prova");/*tiles.get(i).getName();*/
            panel.add(label, BorderLayout.CENTER);
            panel.setName(label.getText());
        }

        for (int i =0; i<4;i++) {
            pawnPositions.put(i, new PositionImpl(0));
        }

        for (int i=0; i < 4; i++) {
            JPanel panel = this.tilesView.get(pawnPositions.get(i).getPos());
            panel.add(shapes.get(i));
        }

        this.setVisible(true);
    }

    @Override
    public void addHouse() {

    }

    @Override
    public void addHotel() {

    }

    @Override
    public void changePos(int currPlayer, Position newPos) {
        pawnPositions.replace(currPlayer, pawnPositions.get(currPlayer), newPos);
        
        for (int i=0; i < 4; i++) {
            JPanel panel = this.tilesView.get(pawnPositions.get(i).getPos());
            panel.add(shapes.get(i));
        }
    }

    @Override
    public void clearPanel() {
        for (int i=0; i < 4; i++) {
            JPanel panel = this.tilesView.get(pawnPositions.get(i).getPos());
            panel.remove(shapes.get(i));
        }
    }

    @Override
    public void buyProperty(Property prop, Pawn currPlayer) {
        for (JPanel p : tilesView) {
            if (p.getName().equals(prop.getName())) {
                p.setBackground(currPlayer.getColor());
            }
        }
    }
}
