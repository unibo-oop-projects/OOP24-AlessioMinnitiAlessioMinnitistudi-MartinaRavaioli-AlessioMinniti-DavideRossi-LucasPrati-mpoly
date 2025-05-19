package it.unibo.monopoly.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.Window;

import java.util.Objects;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * Utility class for common GUI operations.
 */
public final class GuiUtils {

    /**
     * The total number of tiles on the game board.
     * This value determines the maximun valid position for a pawn
     */
    public static final int NUM_TILES = 40;
    private static final double WIDTH_PERC = 0.5;
    private static final double HEIGHT_PERC = 0.5;
    private static final int FONT_STYLE = Font.BOLD;

    private GuiUtils() { }

    /**
     * Create a fixed-size square colored label.
     * If the {@code size} is invalid (zero or negative), returns a placeholder label
     * with an error message instead of throwing an exception.
     * <p>
     * This behavior is intentional to allow graceful failure in GUI contexts where
     * exception handling would be excessive or intrusive.
     *
     * @param color the background color
     * @param size the width and height in pixels
     * @return a square JLabel or an error label if size is invalid
     */
    public static JLabel colorBoxFactory(final Color color, final int size) {
        if (size < 0) {
            return new JLabel("Error size box");
        }
        final JLabel colorBox = new JLabel();
        colorBox.setOpaque(true);
        colorBox.setBackground(color);
        colorBox.setPreferredSize(new Dimension(size, size));
        return colorBox;
    }

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
     *   <li>{@code setModal(true)} is applied if the window is a {@link JDialog}</li>
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
     *    <li>{@code setModal(true)} is enforced for {@link JDialog}</li>
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
                dialog.setModal(true);
            }
        }
    }

    /**
     * Checks whether the provided parameters represent a valid and consistent configuration for a Swing window setup.
     * 
     * @param window the window to configure (must be a {@link JFrame} or {@link JDialog})
     * @param width the width in pixels
     * @param height the height in pixels
     * @param title the window title
     * @param layout the layout manager to apply
     * @return true if the provided parameters form a consistent configuration
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

    /**
     * Refreshes the specified window by revalidating and repainting its content,
     * and making sure it is visible.
     *
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
     * Shows an error message dialog and then terminates the entire application.
     * 
     * @param parent  the parent component for the dialog; may be {@code null}
     *                in which case a default frame is used
     * @param title   the title to display on the dialog window
     * @param message the error message text to show to the user
     */
    public static void showErrorAndExit(final Window parent, final String title, final String message) {
        JOptionPane.showMessageDialog(
            parent,
            message,
            title,
            JOptionPane.ERROR_MESSAGE
        );
        System.exit(0);
    }

    /**
     * Get a default percentage {@link Dimension} of the screen size.
     * 
     * @return a {@link Dimension} based the screen size with default percentage
     */
    public static Dimension getDimensionWindow() {
        return getDimensionWindow(WIDTH_PERC, HEIGHT_PERC);
    }

    /**
     * Get a custom percentage {@link Dimension} of the screen size.
     * 
     * @param widthPerc the percentage of the full screen's width
     * @param heightPerc the percentage of the full screen's height
     * @return a {@link Dimension} based the screen size and the provided percentage
     */
    public static Dimension getDimensionWindow(final double widthPerc, final double heightPerc) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension((int) (screenSize.getWidth() * widthPerc), (int) (screenSize.getHeight() * heightPerc));
    }

    /**
     * Get a new {@link Font} with a small size, according to the {@link Configuration}.
     * 
     * @param config a consistent {@link Configuration} for upload {@code size} and {@code name} parameters
     * @return a new {@link Font} according to the {@link Configuration} parameters
     */
    public static Font getSmallFontFromConfiguration(final Configuration config) {
        return createFont(config.getFontName(), config.getSmallFont());
    }

    /**
     * Get a new {@link Font} with a big size, according to the {@link Configuration}.
     * 
     * @param config a consistent {@link Configuration} for upload {@code size} and {@code name} parameters
     * @return a new {@link Font} according to the {@link Configuration} parameters
     */
    public static Font getBigFontFromConfiguration(final Configuration config) {
        return createFont(config.getFontName(), config.getBigFont());
    }

    private static Font createFont(final String name, final int size) {
        return new Font(name, FONT_STYLE, size);
    }
}
