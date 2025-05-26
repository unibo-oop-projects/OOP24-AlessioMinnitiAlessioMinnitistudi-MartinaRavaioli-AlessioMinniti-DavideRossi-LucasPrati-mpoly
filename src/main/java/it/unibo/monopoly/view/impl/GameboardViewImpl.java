package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.controller.api.GameboardLogic;
import it.unibo.monopoly.controller.impl.GameboardLogicImpl;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.view.api.GameboardView;

/**
    * board view implementation.
*/
public final class GameboardViewImpl extends JPanel implements GameboardView {
    private static final long serialVersionUID = 1L;
    private static final int STRIPE_WIDTH = 150;
    private static final int STRIPE_HEIGHT = 10;
    private final transient GameboardLogic logic;
    private final transient GameController controller;
    private final List<JPanel> tilesView = new ArrayList<>();
    private final int size;
    private final Map<Integer, Position> pawnPositions = new HashMap<>();
    private final Map<JPanel, Position> tilePositions = new HashMap<>();

    /**
    * start view.
    * @param controller
    */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
                justification = "must return reference to the object instead of a copy")
    public GameboardViewImpl(final GameController controller) {
        this.controller = controller;
        this.size = controller.getSize(this.controller.getTiles().size());
        this.logic = new GameboardLogicImpl();

        renderDefaultUI();
    }

    @Override
    public void addHouse() {

    }

    @Override
    public void addHotel() {

    }

    @Override
    public void changePos(final int currPlayer, final Position newPos) {
        //JOptionPane.showMessageDialog(null, "Operazione completata con successo! "+newPos.getPos(), 
                                        //"Info", JOptionPane.INFORMATION_MESSAGE);
        for (final Map.Entry<JPanel, Position> entry : this.tilePositions.entrySet()) {
            if (entry.getValue().equals(pawnPositions.get(currPlayer - 1))) {
                final JPanel p = entry.getKey();
                for (final Component c : p.getComponents()) {
                    if (c instanceof PawnCircle pawnCircle 
                    && pawnCircle.getColor().equals(controller.getCurrPlayer().getColor())) {
                        p.remove(c);
                        p.revalidate();  // AGGIUNTO
                        p.repaint();
                        break;
                    }
                }
            }
        }

        pawnPositions.replace(currPlayer - 1, pawnPositions.get(currPlayer - 1), newPos);

        for (final Map.Entry<JPanel, Position> entry : this.tilePositions.entrySet()) {
            if (entry.getValue().equals(pawnPositions.get(currPlayer - 1))) {
                final JPanel p = entry.getKey();
                final PawnCircle pawnGUI = new PawnCircle(controller.getCurrPlayer().getColor());
                p.add(pawnGUI);
                p.revalidate();  // AGGIUNTO
                p.repaint(); 
                break;
            }
        }
    }

    @Override
    public void buyProperty(final Property prop, final Pawn currPlayer) {
        for (final JPanel p : tilesView) {
            if (p.getName().equals(prop.getName())) {
                p.setBackground(currPlayer.getColor());
            }
        }
    }

    @Override
    public void renderDefaultUI() {
        this.setLayout(new BorderLayout());
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        final JPanel board = new JPanel(new GridLayout(this.size, this.size));
        this.add(board);

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                final JPanel tile = new JPanel();

                if (logic.isBoardTile(i, j, this.size)) {
                    tile.setBorder(BorderFactory.createLineBorder(Color.black));
                    tile.setBackground(Color.white);
                    this.tilesView.add(tile);
                } else if (logic.tileCard(i, j, this.size) > -1) {
                    tile.setBorder(BorderFactory.createLineBorder(Color.black));

                    if (logic.tileCard(i, j, this.size) == 0) {
                        tile.setBackground(Color.RED);
                        final JLabel label = new JLabel("IMPREVISTI");
                        tile.add(label, BorderLayout.CENTER);
                    } else {
                        tile.setBackground(Color.YELLOW);
                        final JLabel label = new JLabel("PROBABILITA'");
                        tile.add(label, BorderLayout.CENTER);
                    }
                } else {
                    tile.setBackground(Color.lightGray);
                }
                board.add(tile);

            }
        }

        for (int i = 0; i < controller.getTiles().size(); i++) {
            final JPanel panel = this.tilesView.get(i);
            tilePositions.put(panel, controller.getTiles().get(i).getPosition());
            final JPanel stripe = new JPanel();
            stripe.setPreferredSize(new Dimension(STRIPE_WIDTH, STRIPE_HEIGHT));
            stripe.setBackground(controller.getTiles().get(i).getGroup().getColor());
            panel.add(stripe, BorderLayout.NORTH);
            final JLabel label = new JLabel(controller.getTiles().get(i).getName());
            panel.add(label, BorderLayout.CENTER);
            panel.setName(controller.getTiles().get(i).getName());
        }


        for (int i = 0; i < controller.getPawns().size(); i++) {
            pawnPositions.put(i, new PositionImpl(0));
        }

        for (int i = 0; i < controller.getPawns().size(); i++) {
            final JPanel panel = this.tilesView.get(pawnPositions.get(i).getPos());
            final PawnCircle pawnGUI = new PawnCircle(this.controller.getPawns().get(i).getColor());
            pawnGUI.setName("pawn" + i);
            panel.add(pawnGUI);
        }

        this.setVisible(true);
    }

    @Override
    public Component getPanel() {
        return this;
    }
}
