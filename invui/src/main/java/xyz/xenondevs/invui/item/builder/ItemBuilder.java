package xyz.xenondevs.invui.item.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class ItemBuilder extends AbstractItemBuilder<ItemBuilder> {
    
    /**
     * Creates a new {@link ItemBuilder} based on the given {@link Material}.
     *
     * @param material The {@link Material}
     */
    public ItemBuilder(Material material) {
        super(material);
    }
    
    /**
     * Creates a new {@link ItemBuilder} based on the given {@link Material} and amount.
     *
     * @param material The {@link Material}
     * @param amount   The amount
     */
    public ItemBuilder(Material material, int amount) {
        super(material, amount);
    }
    
    /**
     * Constructs a new {@link ItemBuilder} based on the give {@link ItemStack}.
     * This will keep the {@link ItemStack} and uses it's {@link ItemMeta}
     *
     * @param base The {@link ItemStack to use as a base}
     */
    public ItemBuilder(ItemStack base) {
        super(base);
    }
    
}
