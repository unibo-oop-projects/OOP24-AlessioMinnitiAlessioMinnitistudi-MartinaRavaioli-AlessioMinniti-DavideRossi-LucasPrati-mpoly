package it.unibo.monopoly.view.impl.gamepanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.utils.impl.GuiUtils;
import it.unibo.monopoly.view.api.ContractPanel;

/**
 * A panel to visualise all information related to a {@link TitleDeed}.
 */
final class SwingContractPanel extends SwingAbstractJPanel implements ContractPanel {

    private static final long serialVersionUID = 43L;
    private static final int BIG_FONT_SIZE = 15;
    private static final int N_ROWS = 5;
    private static final int INFO_PANEL_PROPORTION = 5;
    private static final int CONTRACT_PH_WIDTH = 200;
    private static final int CONTRACT_PH_HEIGHT = 150;
    private static final String CONTRACT_PANEL_PLACEHOLDER = 
            """
            THE CONTRACT OF THE PROPERTY YOU STEPPED ONTO
            WILL APPEAR AS SOON AS YOU MAKE A MOVE
            """;

    SwingContractPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    @Override
    public void renderDefaultUI() {
        this.removeAll();
        final JTextArea contractPlaceholder = new JTextArea(CONTRACT_PANEL_PLACEHOLDER);
        contractPlaceholder.setLineWrap(true);
        contractPlaceholder.setWrapStyleWord(true);
        contractPlaceholder.setEditable(false);
        contractPlaceholder.setPreferredSize(new Dimension(CONTRACT_PH_WIDTH, CONTRACT_PH_HEIGHT));
        this.add(contractPlaceholder);
    }

    @Override
    public void displayPropertyContract(final TitleDeed deed) {
        this.removeAll();
        final JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(N_ROWS, 1)); 

        //name label
        final JLabel name = new JLabel(deed.getName(), SwingConstants.CENTER);
        name.setFont(new Font(getFont().getName(), Font.BOLD, BIG_FONT_SIZE));

        //group information
        final JPanel groupPanel = new JPanel();
        groupPanel.setLayout(new BorderLayout());
        final JLabel group = new JLabel(deed.getGroup().toString(), SwingConstants.RIGHT);

        final JLabel colorBox = GuiUtils.colorBoxFactory(deed.getGroup().getColor(), 30);
        groupPanel.add(colorBox, BorderLayout.WEST);
        groupPanel.add(group, BorderLayout.CENTER);

        //owner information
        final JPanel ownerPanel = new JPanel();
        ownerPanel.setLayout(new BorderLayout());
        final JLabel ownerDesc = new JLabel("Proprietario: ");
        final JLabel ownerInfo = new JLabel(deed.isOwned() ? deed.getOwner() : "NO OWNER", 
                                            SwingConstants.RIGHT);
        ownerPanel.add(ownerDesc, BorderLayout.WEST);
        ownerPanel.add(ownerInfo, BorderLayout.CENTER);

        //sale information
        final JPanel salePanel = new JPanel();
        salePanel.setLayout(new BorderLayout());
        final JLabel saleDesc = new JLabel("Prezzo d'acquisto: "); 
        final JLabel salePrice = new JLabel(Integer.toString(deed.getSalePrice()), SwingConstants.RIGHT);
        salePanel.add(saleDesc, BorderLayout.WEST);
        salePanel.add(salePrice, BorderLayout.CENTER);

        //mortgage information
        final JPanel mortgagePanel = new JPanel();
        mortgagePanel.setLayout(new BorderLayout());
        final JLabel mortgageDesc = new JLabel("Ipoteca: ");
        final JLabel mortgagePrice = new JLabel(Integer.toString(deed.getMortgagePrice()), SwingConstants.RIGHT);
        mortgagePanel.add(mortgageDesc, BorderLayout.WEST);
        mortgagePanel.add(mortgagePrice, BorderLayout.CENTER);
        northPanel.add(name);
        northPanel.add(groupPanel);
        northPanel.add(ownerPanel);
        northPanel.add(salePanel);
        northPanel.add(mortgagePanel);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(rentOptionsList(deed.getRentOptions()), BorderLayout.CENTER);
    }

    private Component rentOptionsList(final List<RentOption> options) {
        final JList<RentOption> optJList = new JList<>(
                                        options.stream()
                                                .collect(Collectors.toCollection(() -> new Vector<RentOption>()))); //NOPMD
                                                /*JList richiede un Vector o un array di oggetti in input 
                                                al costruttore. Passare un array di oggetti
                                                porterebbe alla perdita della type variable 
                                                e eventualmente problemi di type safety
                                                */
        optJList.setCellRenderer(new ListItem());

        optJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int pos = optJList.locationToIndex(e.getPoint());
                final RentOption option = optJList.getModel().getElementAt(pos);
                if (!option.getDescription().isBlank()) {
                    displayDescription(option);
                }
            }
        });

        return new JScrollPane(optJList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void displayDescription(final RentOption option) {
        final JDialog descriptionDialog = new JDialog();
        final JTextArea descriptionJTextArea = new JTextArea();
        descriptionJTextArea.setLineWrap(true);
        descriptionJTextArea.setWrapStyleWord(true);
        descriptionJTextArea.setText(option.getDescription());
        descriptionJTextArea.setEditable(false);
        descriptionDialog.add(descriptionJTextArea);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        descriptionDialog.setSize(sw / INFO_PANEL_PROPORTION, sh / INFO_PANEL_PROPORTION);
        descriptionDialog.setVisible(true);
    }

    private static final class ListItem implements ListCellRenderer<RentOption> {

        private final JPanel optionPanel = new JPanel();
        private final JLabel titleLabel = new JLabel();
        private final JLabel priceLabel = new JLabel();

        private ListItem() {
            priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            optionPanel.setLayout(new BorderLayout());
            optionPanel.add(titleLabel, BorderLayout.WEST);
            optionPanel.add(priceLabel, BorderLayout.CENTER);
            optionPanel.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                )
            );
        }


        @Override
        public Component getListCellRendererComponent(
                final JList<? extends RentOption> list, 
                final RentOption value, 
                final int index,
                final boolean isSelected, 
                final boolean cellHasFocus) {
            titleLabel.setText(value.getTitle());
            priceLabel.setText(Integer.toString(value.getPrice()));
            return optionPanel;
        }
    }
}
