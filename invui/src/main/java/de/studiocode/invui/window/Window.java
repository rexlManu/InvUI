package de.studiocode.invui.window;

import de.studiocode.inventoryaccess.component.ComponentWrapper;
import de.studiocode.invui.window.impl.AnvilSingleWindowImpl;
import de.studiocode.invui.window.impl.AnvilSplitWindowImpl;
import de.studiocode.invui.window.impl.CartographySingleWindowImpl;
import de.studiocode.invui.window.impl.CartographySplitWindowImpl;
import de.studiocode.invui.window.impl.NormalMergedWindowImpl;
import de.studiocode.invui.window.impl.NormalSingleWindowImpl;
import de.studiocode.invui.window.impl.NormalSplitWindowImpl;
import de.studiocode.invui.window.type.WindowType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * A Window is the way to show a player a GUI. Windows can only have one viewer.
 * The default Window implementations can be instantiated using {@link WindowType}.
 *
 * @see WindowType
 * @see AbstractWindow
 * @see AbstractSingleWindow
 * @see AbstractDoubleWindow
 * @see AbstractSplitWindow
 * @see AbstractMergedWindow
 * @see NormalSingleWindowImpl
 * @see NormalSplitWindowImpl
 * @see NormalMergedWindowImpl
 * @see AnvilSingleWindowImpl
 * @see AnvilSplitWindowImpl
 * @see CartographySingleWindowImpl
 * @see CartographySplitWindowImpl
 */
public interface Window {
    
    /**
     * Shows the window to the player.
     */
    void show();
    
    /**
     * Gets if the player is able to close the {@link Inventory}.
     *
     * @return If the player is able to close the {@link Inventory}.
     */
    boolean isCloseable();
    
    /**
     * Sets if the player should be able to close the {@link Inventory}.
     *
     * @param closeable If the player should be able to close the {@link Inventory}.
     */
    void setCloseable(boolean closeable);
    
    /**
     * Closes the underlying {@link Inventory} for its viewer.
     */
    void close();
    
    /**
     * Gets if the {@link Window} is closed and can't be shown again.
     *
     * @return If the {@link Window} is closed.
     */
    boolean isRemoved();
    
    /**
     * Removes the {@link Window} from the {@link WindowManager} list.
     * If this method is called, the {@link Window} can't be shown again.
     */
    void remove();
    
    /**
     * Changes the title of the {@link Inventory}.
     *
     * @param title The new title
     */
    void changeTitle(@NotNull ComponentWrapper title);
    
    /**
     * Changes the title of the {@link Inventory}.
     *
     * @param title The new title
     */
    void changeTitle(@NotNull BaseComponent[] title);
    
    /**
     * Changes the title of the {@link Inventory}.
     *
     * @param title The new title
     */
    void changeTitle(@NotNull String title);
    
    /**
     * Gets the viewer of this {@link Window}
     *
     * @return The viewer of this window.
     */
    @Nullable Player getViewer();
    
    /**
     * Gets the current {@link Player} that is viewing this
     * {@link Window} or null of there isn't one.
     *
     * @return The current viewer of this {@link Window} (can be null)
     */
    @Nullable Player getCurrentViewer();
    
    /**
     * Gets the viewer's {@link UUID}
     *
     * @return The viewer's {@link UUID}
     */
    @NotNull UUID getViewerUUID();
    
    /**
     * Adds a close handler that will be called when this window gets closed.
     *
     * @param closeHandler The close handler to add
     */
    void addCloseHandler(@NotNull Runnable closeHandler);
    
    /**
     * Removes a close handler that has been added previously.
     *
     * @param closeHandler The close handler to remove
     */
    void removeCloseHandler(@NotNull Runnable closeHandler);
    
}
