package xyz.xenondevs.invui.item.impl.controlitem;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.xenondevs.invui.gui.AbstractTabGui;
import xyz.xenondevs.invui.gui.TabGui;

/**
 * Switches between tabs in a {@link AbstractTabGui}
 */
public abstract class TabItem extends ControlItem<TabGui> {
    
    private final int tab;
    
    public TabItem(int tab) {
        this.tab = tab;
    }
    
    @Override
    public void handleClick(ClickType clickType, Player player, InventoryClickEvent event) {
        if (clickType == ClickType.LEFT) getGui().setTab(tab);
    }
    
}
