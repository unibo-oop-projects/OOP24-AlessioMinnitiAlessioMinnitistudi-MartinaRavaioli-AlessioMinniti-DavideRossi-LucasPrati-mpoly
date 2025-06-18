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
    private static final String NAME_PH = "Player:";
    private static final String NUMERO_PH = "ID number:";
    private static final String PRISON_PH = "Is in prison:";
    private static final String PRISON_TURNS_PH = "Turns left to serve:";
    private static final String PARKING_PH = "Is in parking lot:";
    private static final int N_ROWS = 5;

    private final JLabel userNameJLabel = new JLabel(NAME_PH);
    private final JLabel prisonStateJLabel = new JLabel(PRISON_PH);
    private final JLabel prisonTurnsJLabel = new JLabel(PRISON_TURNS_PH);
    private final JLabel parkingStateJLabel = new JLabel(PARKING_PH);
    private final JLabel orderJLabel = new JLabel(NUMERO_PH);

    private JPanel createRow(final JLabel title, final JLabel info) {
        title.setHorizontalAlignment(SwingConstants.LEFT);
        info.setHorizontalAlignment(SwingConstants.RIGHT);
        final JPanel row = new JPanel();
        row.add(title, BorderLayout.WEST);
        row.add(info, BorderLayout.CENTER);
        return row;
    }


    @Override
    public void renderDefaultUI() {
        this.removeAll();
        this.setLayout(new BorderLayout());
        final JLabel userInfoLabel = new JLabel(NO_PLAYER_PH);
        this.add(userInfoLabel, BorderLayout.CENTER);
    }

    @Override
    public void displayPlayer(final Player pl) {
        this.removeAll();
        this.setLayout(new GridLayout(N_ROWS, 1));
        final var nameLabel = new JLabel(pl.getName());
        nameLabel.setForeground(pl.getColor());
        this.add(createRow(userNameJLabel, nameLabel));
        this.add(createRow(orderJLabel, new JLabel(Integer.toString(pl.getID()))));
        this.add(createRow(prisonStateJLabel, new JLabel(pl.isInPrison() ? "Yes" : "No")));
        this.add(createRow(prisonTurnsJLabel, new JLabel(Integer.toString(pl.turnLeftInPrison()))));
        this.add(createRow(parkingStateJLabel, new JLabel(pl.isParked() ? "Yes" : "No")));
    }

}
