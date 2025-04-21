package it.unibo.monopoly.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Window;

import java.util.Objects;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * Utility class for common GUI operations.
 */
public final class GuiUtils {

    private GuiUtils() { }

    /**
     * Utility method to create a fixed-size square colored label.
     * Useful for player color boxes.
     *
     * @param color the background color
     * @param size the width and height in pixels
     * @return a square JLabel with the given background color
     */
    public static JLabel colorBoxFactory(final Color color, final int size) {
        if (size > 0) {
            final JLabel colorBox = new JLabel();
            colorBox.setOpaque(true);
            colorBox.setBackground(color);
            colorBox.setPreferredSize(new Dimension(size, size));
            return colorBox;
        }
        return new JLabel("Error size box");
    }

    /**
     * Configures a window with default layout and location, along with standard behaviors.
     * <p>
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
     * <p>
     * @apiNote Some properties are automatically enforced and cannot be customized through parameters:
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
}
