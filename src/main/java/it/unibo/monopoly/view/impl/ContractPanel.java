package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.utils.GuiUtils;

/**
 * A panel to visualise all information related to a {@link TitleDeed}.
 */
final class ContractPanel extends JPanel {

    private static final long serialVersionUID = 42L;
    private static final int BIG_FONT_SIZE = 15;
    private static final int N_ROWS = 5;

    private ContractPanel() {
        this.setLayout(new BorderLayout());
    }

    private void renderPanel(final TitleDeed titleDeed) {
        final JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(N_ROWS, 1)); 

        //name label
        final JLabel name = new JLabel(titleDeed.getName(), SwingConstants.CENTER);
        name.setFont(new Font(getFont().getName(), Font.BOLD, BIG_FONT_SIZE));

        //group information
        final JPanel groupPanel = new JPanel();
        groupPanel.setLayout(new BorderLayout());
        final JLabel group = new JLabel(titleDeed.getGroup(), SwingConstants.RIGHT);
        final JLabel colorBox = GuiUtils.colorBoxFactory(Color.BLUE, 30);
        groupPanel.add(colorBox, BorderLayout.WEST);
        groupPanel.add(group, BorderLayout.CENTER);

        //owner information
        final JPanel ownerPanel = new JPanel();
        ownerPanel.setLayout(new BorderLayout());
        final JLabel ownerDesc = new JLabel("Proprietario: ");
        final JLabel ownerInfo = new JLabel(titleDeed.getOwner().isPresent() ? titleDeed.getOwner().get() : "NO OWNER", 
                                            SwingConstants.RIGHT);
        ownerPanel.add(ownerDesc, BorderLayout.WEST);
        ownerPanel.add(ownerInfo, BorderLayout.CENTER);

        //sale information
        final JPanel salePanel = new JPanel();
        salePanel.setLayout(new BorderLayout());
        final JLabel saleDesc = new JLabel("Prezzo d'acquisto: "); 
        final JLabel salePrice = new JLabel(Integer.toString(titleDeed.getSalePrice()), SwingConstants.RIGHT);
        salePanel.add(saleDesc, BorderLayout.WEST);
        salePanel.add(salePrice, BorderLayout.CENTER);

        //mortgage information
        final JPanel mortgagePanel = new JPanel();
        mortgagePanel.setLayout(new BorderLayout());
        final JLabel mortgageDesc = new JLabel("Ipoteca: ");
        final JLabel mortgagePrice = new JLabel(Integer.toString(titleDeed.getMortgagePrice()), SwingConstants.RIGHT);
        mortgagePanel.add(mortgageDesc, BorderLayout.WEST);
        mortgagePanel.add(mortgagePrice, BorderLayout.CENTER);
        northPanel.add(name);
        northPanel.add(groupPanel);
        northPanel.add(ownerPanel);
        northPanel.add(salePanel);
        northPanel.add(mortgagePanel);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(rentOptionsList(titleDeed.getRentOptions()), BorderLayout.CENTER);
    }

    private Component rentOptionsList(final List<RentOption> options) {
        final JList<RentOption> optJList = new JList<>(
                                        options.stream()
                                                .collect(Collectors.toCollection(() -> new Vector<RentOption>())));
        optJList.setCellRenderer(new ListItem());

        final JScrollPane scrollableOptionsList = new JScrollPane(optJList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollableOptionsList;
    }

    static ContractPanel createCard(final TitleDeed titleDeed) {
        final ContractPanel panel = new ContractPanel();
        panel.renderPanel(titleDeed);
        return panel;
    }

    private static final class ListItem implements ListCellRenderer<RentOption> {

        @Override
        public Component getListCellRendererComponent(
                final JList<? extends RentOption> list, 
                final RentOption value, 
                final int index,
                final boolean isSelected, 
                final boolean cellHasFocus) {
            final JPanel optionPanel = new JPanel();
            optionPanel.setLayout(new BorderLayout());
            optionPanel.add(new JLabel(value.getTitle()), BorderLayout.WEST);
            optionPanel.add(new JLabel(Integer.toString(value.getPrice()), SwingConstants.RIGHT), BorderLayout.CENTER);
            if (!value.getDescription().isEmpty()) {
                final JTextArea descriptionJTextArea = new JTextArea(value.getDescription());
                optionPanel.add(descriptionJTextArea, BorderLayout.SOUTH);
            }
            return optionPanel;
        }
    }
}
