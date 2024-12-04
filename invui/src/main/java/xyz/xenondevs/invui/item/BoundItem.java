package xyz.xenondevs.invui.item;

import org.bukkit.entity.Player;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.ScrollGui;
import xyz.xenondevs.invui.gui.TabGui;
import xyz.xenondevs.invui.util.TriConsumer;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * An item that is bound to a specific {@link Gui}.
 */
public sealed interface BoundItem extends Item permits AbstractBoundItem {
    
    /**
     * Gets the {@link Gui} this item is bound to.
     *
     * @return The {@link Gui} this item is bound to.
     * @throws IllegalStateException If no {@link Gui} is bound to this item.
     */
    Gui getGui();
    
    /**
     * Binds this item to a specific {@link Gui}.
     * Called when the item is added to a {@link Gui}.
     *
     * @param gui The {@link Gui} to bind this item to.
     * @throws IllegalStateException If this item is already bound to a {@link Gui}.
     */
    void bind(Gui gui);
    
    /**
     * Checks if this item is already bound to a {@link Gui}.
     *
     * @return Whether this item is already bound to a {@link Gui}.
     */
    boolean isBound();
    
    /**
     * Creates a new {@link Builder} for a {@link BoundItem} that can be bound to any {@link Gui}.
     *
     * @return A new {@link Builder} for a {@link BoundItem}.
     */
    static Builder<Gui> gui() {
        return new CustomBoundItem.Builder<>();
    }
    
    /**
     * Creates a new {@link Builder} for a {@link BoundItem} that can be bound to a {@link PagedGui}.
     * The item will be automatically updated when the bound {@link PagedGui PagedGui's} page or content changes.
     *
     * @return A new {@link Builder} for a {@link BoundItem}.
     */
    static Builder<PagedGui<?>> pagedGui() {
        return new CustomBoundItem.Builder.Paged();
    }
    
    /**
     * Creates a new {@link Builder} for a {@link BoundItem} that can be bound to a {@link ScrollGui}.
     * The item will be automatically updated when the bound {@link ScrollGui ScrollGui's} line or content changes.
     *
     * @return A new {@link Builder} for a {@link BoundItem}.
     */
    static Builder<ScrollGui<?>> scrollGui() {
        return new CustomBoundItem.Builder.Scroll();
    }
    
    /**
     * Creates a new {@link Builder} for a {@link BoundItem} that can be bound to a {@link TabGui}.
     * The item will be automatically updated when the bound {@link TabGui TabGui's} tab changes.
     *
     * @return A new {@link Builder} for a {@link BoundItem}.
     */
    static Builder<TabGui> tabGui() {
        return new CustomBoundItem.Builder.Tab();
    }
    
    /**
     * A builder for a {@link BoundItem}.
     *
     * @param <G> The type of {@link Gui} this item can be bound to.
     */
    sealed interface Builder<G extends Gui> extends Item.Builder<Builder<G>> permits CustomBoundItem.Builder {
        
        /**
         * Adds a bind handler that is called when the item is bound to a {@link Gui}.
         *
         * @param handler The bind handler.
         * @return This builder.
         */
        Builder<G> addBindHandler(BiConsumer<Item, G> handler);
        
        /**
         * Adds a click handler that is called when the item is clicked.
         *
         * @param handler The click handler, receiving the {@link Item} itself, the bound {@link Gui} and the {@link Click}.
         * @return This builder.
         */
        Builder<G> addClickHandler(TriConsumer<Item, G, Click> handler);
        
        /**
         * Sets the item provider that provides the item for a specific player and {@link Gui}.
         *
         * @param itemProvider The item provider.
         * @return This builder.
         */
        Builder<G> setItemProvider(BiFunction<Player, G, ItemProvider> itemProvider);
        
    }
    
}
