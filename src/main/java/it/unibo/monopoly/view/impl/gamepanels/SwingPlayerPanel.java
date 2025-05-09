package it.unibo.monopoly.view.impl.gamepanels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.view.api.PlayerPanel;

final class SwingPlayerPanel extends SwingAbstractJPanel implements PlayerPanel {

    private static final long serialVersionUID = 1L;
    private static final String NO_PLAYER_PH = "No player selected";
    private static final String NAME_PH = "Giocatore:";
    //numero giocatore? 
    private static final String PRISON_PH = "È in prigione:";
    private static final String PRISON_TURNS_PH = "Turni rimasti da scontare:";
    private static final String PARKING_PH = "È nel parcheggio:";

    private final JLabel userNameJLabel = new JLabel(NAME_PH);
    private final JLabel prisonStateJLabel = new JLabel(PRISON_PH);
    private final JLabel prisonTurnsJLabel = new JLabel(PRISON_TURNS_PH);
    private final JLabel parkingStateJLabel = new JLabel(PARKING_PH);

    private JPanel createRow(final JLabel title, final JLabel info) {
        title.setHorizontalAlignment(SwingConstants.LEFT);
        info.setHorizontalAlignment(SwingConstants.RIGHT);
        final JPanel row = new JPanel();
        row.add(title, BorderLayout.WEST);
        row.add(info, BorderLayout.CENTER);
        return row;
    }


    @Override
    public void clear() {
        this.removeAll();
        this.setLayout(new BorderLayout());
        final JLabel userInfoLabel = new JLabel(NO_PLAYER_PH);
        this.add(userInfoLabel, BorderLayout.CENTER);
    }

    @Override
    public void displayPlayer(final Player pl) {
        this.setLayout(new GridLayout(4, 1));
        this.add(createRow(userNameJLabel, new JLabel(pl.getName())));
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayPlayer'");
    }

}
