package xyz.xenondevs.invui.gui;

import org.jspecify.annotations.Nullable;
import xyz.xenondevs.invui.inventory.Inventory;
import xyz.xenondevs.invui.item.Item;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public sealed interface ScrollGui<C> extends Gui permits AbstractScrollGui {
    
    /**
     * Creates a new {@link Builder Gui Builder} for a {@link ScrollGui} that uses {@link Item Items} as content.
     *
     * @return The new {@link Builder Gui Builder}.
     */
    static <C extends Item> Builder<C> items() {
        return new ScrollItemsGuiImpl.Builder<>();
    }
    
    /**
     * Creates a new {@link ScrollGui} that uses {@link Item Items} as content after configuring a
     * {@link Builder Gui Builder} using the given {@link Consumer}.
     *
     * @param consumer The {@link Consumer} to configure the {@link Builder Gui Builder}.
     * @return The created {@link ScrollGui}.
     */
    static <C extends Item> ScrollGui<C> items(Consumer<Builder<C>> consumer) {
        Builder<C> builder = items();
        consumer.accept(builder);
        return builder.build();
    }
    
    /**
     * Creates a new {@link ScrollGui}.
     *
     * @param width            The width of the {@link ScrollGui}.
     * @param height           The height of the {@link ScrollGui}.
     * @param items            The {@link Item Items} to use.
     * @param contentListSlots The slots where content should be displayed.
     * @return The created {@link ScrollGui}.
     */
    static <C extends Item> ScrollGui<C> ofItems(int width, int height, List<C> items, int... contentListSlots) {
        return new ScrollItemsGuiImpl<>(width, height, items, contentListSlots);
    }
    
    /**
     * Creates a new {@link ScrollGui}.
     *
     * @param structure The {@link Structure} to use.
     * @param items     The {@link Item Items} to use.
     * @return The created {@link ScrollGui}.
     */
    static <C extends Item> ScrollGui<C> ofItems(Structure structure, List<C> items) {
        return new ScrollItemsGuiImpl<>(items, structure);
    }
    
    /**
     * Creates a new {@link Builder Gui Builder} for a {@link ScrollGui} that uses {@link Gui Guis} as content.
     *
     * @return The new {@link Builder Gui Builder}.
     */
    static <C extends Gui> Builder<C> guis() {
        return new ScrollNestedGuiImpl.Builder<>();
    }
    
    /**
     * Creates a new {@link ScrollGui} that uses {@link Gui Guis} as content after configuring a
     * {@link Builder Gui Builder} using the given {@link Consumer}.
     *
     * @param consumer The {@link Consumer} to configure the {@link Builder Gui Builder}.
     * @return The created {@link ScrollGui}.
     */
    static <C extends Gui> ScrollGui<C> guis(Consumer<Builder<C>> consumer) {
        Builder<C> builder = guis();
        consumer.accept(builder);
        return builder.build();
    }
    
    /**
     * Creates a new {@link ScrollGui}.
     *
     * @param width            The width of the {@link ScrollGui}.
     * @param height           The height of the {@link ScrollGui}.
     * @param guis             The {@link Gui Guis} to use.
     * @param contentListSlots The slots where content should be displayed.
     * @return The created {@link ScrollGui}.
     */
    static <C extends Gui> ScrollGui<C> ofGuis(int width, int height, List<C> guis, int... contentListSlots) {
        return new ScrollNestedGuiImpl<>(width, height, guis, contentListSlots);
    }
    
    /**
     * Creates a new {@link ScrollGui}.
     *
     * @param structure The {@link Structure} to use.
     * @param guis      The {@link Gui Guis} to use.
     * @return The created {@link ScrollGui}.
     */
    static <C extends Gui> ScrollGui<C> ofGuis(Structure structure, List<C> guis) {
        return new ScrollNestedGuiImpl<>(guis, structure);
    }
    
    /**
     * Creates a new {@link Builder Gui Builder} for a {@link ScrollGui} that uses {@link Inventory VirtualInventories} as content.
     *
     * @return The new {@link Builder Gui Builder}.
     */
    static <C extends Inventory> Builder<C> inventories() {
        return new ScrollInventoryGuiImpl.Builder<>();
    }
    
    /**
     * Creates a new {@link ScrollGui} that uses {@link Inventory VirtualInventories} as content after configuring a
     * {@link Builder Gui Builder} using the given {@link Consumer}.
     *
     * @param consumer The {@link Consumer} to configure the {@link Builder Gui Builder}.
     * @return The created {@link ScrollGui}.
     */
    static <C extends Inventory> ScrollGui<C> inventories(Consumer<Builder<C>> consumer) {
        Builder<C> builder = inventories();
        consumer.accept(builder);
        return builder.build();
    }
    
    /**
     * Creates a new {@link ScrollGui}.
     *
     * @param width            The width of the {@link ScrollGui}.
     * @param height           The height of the {@link ScrollGui}.
     * @param inventories      The {@link Inventory VirtualInventories} to use.
     * @param contentListSlots The slots where content should be displayed.
     * @return The created {@link ScrollGui}.
     */
    static <C extends Inventory> ScrollGui<C> ofInventories(int width, int height, List<C> inventories, int... contentListSlots) {
        return new ScrollInventoryGuiImpl<>(width, height, inventories, contentListSlots);
    }
    
    /**
     * Creates a new {@link ScrollGui}.
     *
     * @param structure   The {@link Structure} to use.
     * @param inventories The {@link Inventory VirtualInventories} to use.
     * @return The created {@link ScrollGui}.
     */
    static <C extends Inventory> ScrollGui<C> ofInventories(Structure structure, List<C> inventories) {
        return new ScrollInventoryGuiImpl<>(inventories, structure);
    }
    
    /**
     * Gets the current line of this {@link ScrollGui}.
     *
     * @return The current line of this {@link ScrollGui}.
     */
    int getCurrentLine();
    
    /**
     * Gets the max line index of this {@link ScrollGui}.
     *
     * @return The max line index of this {@link ScrollGui}.
     */
    int getMaxLine();
    
    /**
     * Sets the current line of this {@link ScrollGui}.
     *
     * @param line The line to set.
     */
    void setCurrentLine(int line);
    
    /**
     * Checks if it is possible to scroll the specified amount of lines.
     *
     * @param lines The amount of lines to check.
     * @return Whether it is possible to scroll the specified amount of lines.
     */
    boolean canScroll(int lines);
    
    /**
     * Scrolls the specified amount of lines.
     *
     * @param lines The amount of lines to scroll.
     */
    void scroll(int lines);
    
    /**
     * Sets the content of this {@link ScrollGui} for all lines.
     *
     * @param content The content to set.
     */
    void setContent(@Nullable List<C> content);
    
    /**
     * Gets the content of this {@link ScrollGui}.
     *
     * @return The content of this {@link ScrollGui}.
     */
    @Nullable
    List<C> getContent();
    
    /**
     * Bakes the elements of this {@link PagedGui} based on the current content.
     * <p>
     * This method does not need to be called when using {@link #setContent(List)},
     * but is required when the size of the content itself changes.
     */
    void bake();
    
    /**
     * Gets the scroll handlers of this {@link ScrollGui}.
     *
     * @return The scroll handlers of this {@link ScrollGui}.
     */
    @Nullable
    List<BiConsumer<Integer, Integer>> getScrollHandlers();
    
    /**
     * Replaces the currently registered scroll handlers with the specified ones.
     *
     * @param scrollHandlers The new scroll handlers.
     */
    void setScrollHandlers(@Nullable List<BiConsumer<Integer, Integer>> scrollHandlers);
    
    /**
     * Adds a scroll handler to this {@link ScrollGui}.
     *
     * @param scrollHandler The scroll handler to add.
     */
    void addScrollHandler(BiConsumer<Integer, Integer> scrollHandler);
    
    /**
     * Removes the specified scroll handler from this {@link ScrollGui}.
     *
     * @param scrollHandler The scroll handler to remove.
     */
    void removeScrollHandler(BiConsumer<Integer, Integer> scrollHandler);
    
    /**
     * Gets the line count change handlers of this {@link ScrollGui}.
     *
     * @return The line count change handlers of this {@link ScrollGui}.
     */
    @Nullable
    List<BiConsumer<Integer, Integer>> getLineCountChangeHandlers();
    
    /**
     * Replaces the currently registered line count change handlers with the specified ones.
     *
     * @param handlers The new line count change handlers.
     */
    void setLineCountChangeHandlers(@Nullable List<BiConsumer<Integer, Integer>> handlers);
    
    /**
     * Adds a line count change handlers to this {@link ScrollGui}.
     *
     * @param handler The line count change handlers to add.
     */
    void addLineCountChangeHandler(BiConsumer<Integer, Integer> handler);
    
    /**
     * Removes the specified line count change handlers from this {@link ScrollGui}.
     *
     * @param handler The line count change handlers to remove.
     */
    void removeLineCountChangeHandler(BiConsumer<Integer, Integer> handler);
    
    /**
     * A {@link ScrollGui} builder.
     *
     * @param <C> The content type.
     */
    sealed interface Builder<C> extends Gui.Builder<ScrollGui<C>, Builder<C>> permits AbstractScrollGui.AbstractBuilder {
        
        /**
         * Sets the content of the {@link ScrollGui} for all lines.
         *
         * @param content The content to set.
         * @return This {@link Builder Gui Builder}.
         */
        Builder<C> setContent(List<C> content);
        
        /**
         * Adds content to the {@link ScrollGui}.
         *
         * @param content The content to add.
         * @return This {@link Builder Gui Builder}.
         */
        Builder<C> addContent(C content);
        
        /**
         * Sets the scroll handlers of the {@link ScrollGui}.
         *
         * @param handlers The scroll handlers to set.
         * @return This {@link Builder Gui Builder}.
         */
        Builder<C> setScrollHandlers(List<BiConsumer<Integer, Integer>> handlers);
        
        /**
         * Adds a scroll handler to the {@link ScrollGui}.
         *
         * @param handler The scroll handler to add.
         * @return This {@link Builder Gui Builder}.
         */
        Builder<C> addScrollHandler(BiConsumer<Integer, Integer> handler);
        
        /**
         * Sets the line count change handlers of the {@link ScrollGui}.
         *
         * @param handlers The line count change handlers to set.
         * @return This {@link Builder Gui Builder}.
         */
        Builder<C> setLineCountChangeHandlers(List<BiConsumer<Integer, Integer>> handlers);
        
        /**
         * Adds a line count change handler to the {@link ScrollGui}.
         *
         * @param handler The scroll handler to add.
         * @return This {@link Builder Gui Builder}.
         */
        Builder<C> addLineCountChangeHandler(BiConsumer<Integer, Integer> handler);
        
    }
    
}
