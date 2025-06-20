package it.unibo.monopoly.view.impl;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.utils.impl.GuiUtils;
import it.unibo.monopoly.view.api.MainMenuPanelsFactory;
import it.unibo.monopoly.view.api.MainMenuView;
import it.unibo.monopoly.view.api.MenuPanel;
import it.unibo.monopoly.view.api.SettingsPanel;
import it.unibo.monopoly.view.api.SetupPanel;
import it.unibo.monopoly.view.impl.gamepanels.SwingPanelsFactory;

/**
 * Implementation of the {@link MainMenuView} interface.
 */
public final class MainMenuViewImpl implements MainMenuView {

    private static final String TITLE_WINDOW = "Monopoly - Main Menu";

    // Size of boxes and empty borders
    private static final int TOP_BORDER = 10;
    private static final int BOTTOM_BORDER = 10;
    private static final int SIDE_BORDER = 20;

    // Container
    private final JFrame mainFrame = new JFrame();
    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final MenuPanel menuPanel;
    private final SettingsPanel settingsPanel;
    private final SetupPanel setupPanel;

    private final MainMenuPanelsFactory fact = new SwingPanelsFactory();


    /**
     * Assembles the UI of the menu interface and adds all components to {@code mainFrame} object.
     * The {@code mainFrame} is a {@link JFrame}. 
     * @param controller the {@link MainMenuController} that captures the events 
     * happening on this view, implementing the observer pattern. 
     * User events will be captured and handled by the {@code controller} provided to this constructor.
     */
    public MainMenuViewImpl(final MainMenuController controller) {
        GuiUtils.configureWindow(mainFrame,
                                 (int) GuiUtils.getDimensionWindow().getWidth(),
                                 (int) GuiUtils.getDimensionWindow().getHeight(),
                                 TITLE_WINDOW
        );
        menuPanel = fact.menuPanel(controller);
        menuPanel.renderDefaultUI();
        settingsPanel = fact.settingsPanel(controller);
        settingsPanel.renderDefaultUI();
        setupPanel = fact.setupPanel(controller);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(TOP_BORDER, SIDE_BORDER, BOTTOM_BORDER, SIDE_BORDER));
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        displayMainMenu();
    }

    @Override
    public void displayMainMenu() {
        mainPanel.removeAll();
        mainPanel.add(menuPanel.getPanel());
        GuiUtils.refresh(mainFrame);
    }

    @Override
    public void displaySettingsMenu() {
        mainPanel.removeAll();
        mainPanel.add(settingsPanel.getPanel());
        GuiUtils.refresh(mainFrame);
    }

    @Override
    public void displaySetupMenu() {
        mainPanel.removeAll();
        setupPanel.renderDefaultUI();
        mainPanel.add(setupPanel.getPanel());
        GuiUtils.refresh(mainFrame);
    }

    @Override
    public void disposeMainMenu() {
        mainFrame.dispose();
    }

    @Override
    public void displayRules(final String rules) {
        new RulesWindowView(this.mainFrame, rules);
    }

    @Override
    public void displayErrorAndExit(final String title, final String message) {
        GuiUtils.showErrorAndExit(mainFrame, title, message);
        mainFrame.dispose();
    }

    @Override
    public void showInfoMessage(final String title, final String message) {
        GuiUtils.showInfoMessage(mainFrame, title, message);
    }

    @Override
    public void refreshSettingsData(final BankAccountType type) {
        settingsPanel.refreshSettingsData(type);
    }

    @Override
    public void refreshNumPlayers(final int num, final boolean minPlayers, final boolean maxPlayers) {
        menuPanel.refreshNumPlayers(num, minPlayers, maxPlayers);
    }
}
