package it.unibo.monopoly.utils.impl;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.Objects;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;


/**
 * Utility class for common GUI operations.
 */
public final class GuiUtils {

    private static final double MAX_PERC = 1.0;
    private static final double MIN_PERC = 0.0;
    private static final double WIDTH_PERC = 0.5;
    private static final double HEIGHT_PERC = 0.5;

    private GuiUtils() { /* Prevent instantiation */ }

    /**
     * Configures a window with default layout and location, along with standard behaviors.
     * 
     * This version applies default values for layout and location:
     * <ul>
     *   <li>{@code BorderLayout} is used as default layout manager</li>
     *   <li>{@code setLocationRelativeTo(null)} centers the window on screen</li>
     * </ul>
     * Other properties are enforced and not configurable:
     * <ul>
     *   <li>{@code setResizable(true)} is always applied</li>
     *   <li>{@code setModalityType(Dialog.DEFAULT_MODALITY_TYPE)} is enforced for {@link JDialog}</li>
     *   <li>{@code setDefaultCloseOperation} is set to:
     *     <ul>
     *       <li>{@code EXIT_ON_CLOSE} for {@link JFrame}</li>
     *       <li>{@code DISPOSE_ON_CLOSE} for {@link JDialog}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @param window the window to configure (must be a {@link JFrame} or {@link JDialog})
     * @param width the width in pixels
     * @param height the height in pixels
     * @param title the title to set
     */
    public static void configureWindow(final Window window, final int width, final int height, final String title) {
        configureWindow(window, width, height, title, new BorderLayout(), null);
    }

    /**
     * Configures common properties for a window (either {@link JFrame} or {@link JDialog}).
     * 
     * @implNote Some properties are automatically enforced and cannot be customized through parameters:
     *   <ul>
     *    <li>{@code setResizable(true)} is always applied</li>
     *    <li>{@code setModalityType(Dialog.DEFAULT_MODALITY_TYPE)} is enforced for {@link JDialog}</li>
     *    <li>{@code setDefaultCloseOperation} is set to:
     *      <ul>
     *        <li>{@code EXIT_ON_CLOSE} for {@link JFrame}</li>
     *        <li>{@code DISPOSE_ON_CLOSE} for {@link JDialog}</li>
     *      </ul>
     *    </li>
     *   </ul>
     * 
     * @param window the window to configure (must be a {@link JFrame} or {@link JDialog})
     * @param width the width in pixels
     * @param height the height in pixels
     * @param title the window title
     * @param layout the layout manager to apply to the window
     * @param locationRelativeTo the location of the window relative to the specified component
     */
    public static void configureWindow(final Window window, final int width, final int height, final String title, 
                                        final LayoutManager layout, final Component locationRelativeTo) {
        if (isConsistent(window, width, height, title, layout)) {
            window.setSize(width, height);
            window.setLocationRelativeTo(locationRelativeTo);
            window.setLayout(layout);

            if (window instanceof final JFrame frame) {
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle(title);
                frame.setResizable(true);
            } else if (window instanceof final JDialog dialog) {
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setTitle(title);
                dialog.setResizable(true);
                dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
            }
        }
    }

    /**
     * Refreshes the specified window by revalidating and repainting its content,
     * and making sure it is visible.
     * @param window the window to refresh; must not be {@code null}
    */
    public static void refresh(final Window window) {
        if (Objects.nonNull(window)) {
            window.revalidate();
            window.repaint();
            window.setVisible(true);
        }
    }

    /**
     * Shows a message dialog.
     * @param parent  the parent component for the dialog; may be {@code null}
     *                in which case a default frame is used
     * @param title   the title to display on the dialog window
     * @param message the message text to show to the user
     */
    public static void showInfoMessage(final Window parent,
                                         final String title,
                                         final String message) {
        showMessageDialog(parent, title, message, JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Shows an error message dialog and then terminates the entire application.
     * @param parent  the parent component for the dialog; may be {@code null}
     *                in which case a default frame is used
     * @param title   the title to display on the dialog window
     * @param message the error message text to show to the user
     */
    public static void showErrorAndExit(final Window parent, final String title, final String message) {
        showMessageDialog(parent, title, message, JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    /**
     * Get a default 50% {@link Dimension} of the screen size.
     * @return a {@link Dimension} based the screen size with default percentage
     */
    public static Dimension getDimensionWindow() {
        return getDimensionWindow(WIDTH_PERC, HEIGHT_PERC);
    }

    /**
     * Get a custom percentage {@link Dimension} of the screen size.
     * @param widthPerc the percentage of the full screen's width
     * @param heightPerc the percentage of the full screen's height
     * @return a {@link Dimension} based the screen size and the provided percentage
     * @throws IllegalArgumentException if the provided percentage is not between 0.0 and 1.0
     */
    public static Dimension getDimensionWindow(final double widthPerc, final double heightPerc) {
        if (!isPercentageCorrect(widthPerc, heightPerc)) {
            throw new IllegalArgumentException(
                "The percentage selected ("
                + widthPerc + ", " + heightPerc
                + ") does not follow this rule: 0% < val <= 100%"
            );
        }
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension((int) (screenSize.getWidth() * widthPerc), (int) (screenSize.getHeight() * heightPerc));
    }

    /**
     * Get a new {@link Font} with a small size, according to the {@link Configuration}.
     * @param config a consistent {@link Configuration} for upload {@code size} and {@code name} parameters
     * @return a new {@link Font} according to the {@link Configuration} parameters
     * @throws NullPointerException if the {@link Configuration} is {@code null}
     */
    public static Font getSmallFontFromConfiguration(final Configuration config) {
        Objects.requireNonNull(config, "The configuration must not be null");
        return FontUtils.createFont(config.getFontName(), config.getSmallFont());
    }

    /**
     * Get a new {@link Font} with a big size, according to the {@link Configuration}.
     * @param config a consistent {@link Configuration} for upload {@code size} and {@code name} parameters
     * @return a new {@link Font} according to the {@link Configuration} parameters
     * @throws NullPointerException if the {@link Configuration} is {@code null}
     */
    public static Font getBigFontFromConfiguration(final Configuration config) {
        Objects.requireNonNull(config, "The configuration must not be null");
        return FontUtils.createFont(config.getFontName(), config.getBigFont());
    }

    /**
     * Setup a new global font to use, provided by the configuration.
     * @param config a consistent {@link Configuration} for the font settings
     */
    public static void applyGlobalFont(final Configuration config) {
        Objects.requireNonNull(config, "The configuration must not be null");
        final Font font = getSmallFontFromConfiguration(config);
        FontUtils.configure(font);
    }


    /**
     * Checks whether the provided parameters represent a valid and consistent configuration for a Swing window setup.
     * @param window the window to configure (must be a {@link JFrame} or {@link JDialog})
     * @param width the width in pixels
     * @param height the height in pixels
     * @param title the window title
     * @param layout the layout manager to apply
     * @return true if the provided parameters form a consistent configuration, false otherwise
     */
    private static boolean isConsistent(final Window window, final int width, final int height, 
                                        final String title, final LayoutManager layout) {
        return Objects.nonNull(window)
                && Objects.nonNull(title)
                && Objects.nonNull(layout)
                && (window instanceof JDialog || window instanceof JFrame)
                && width > 0
                && height > 0;
    }

    private static void showMessageDialog(final Window parent,
                                          final String title,
                                          final String message,
                                          final int type) {        
        JOptionPane.showMessageDialog(parent, message, title, type);
    }

    /**
     * Checks whether the provided percentage represent a valid ones.
     * @param widthPerc the width to check
     * @param heightPerc the height to check
     * @return true if the provided percentage are valid, false otherwise
     */
    private static boolean isPercentageCorrect(final double widthPerc, final double heightPerc) {
        return widthPerc > MIN_PERC
            && widthPerc <= MAX_PERC
            && heightPerc > MIN_PERC
            && heightPerc <= MAX_PERC;
    }
}
