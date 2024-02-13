package com.dev.shau.inventory.backpacks;

import com.dev.shau.Backpacks;
import com.hakan.core.ui.inventory.InventoryGui;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Shau
 */

public class Mochila extends InventoryGui {
    protected final ItemStack itemStack;
    protected final ItemMeta itemMeta;
    protected final PersistentDataContainer persistentDataContainer;

    public Mochila(ItemStack itemStack, String id, String title, int size, InventoryType type) {
        super(id, title, size, type, new HashSet<>());
        super.addOption(Option.CLOSABLE);

        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
        this.persistentDataContainer = itemMeta.getPersistentDataContainer();
    }

    @Override
    public void onOpen(@Nonnull Player player) {
        for(int i = 0; i < this.getSize() * 9; i++) {
            if(this.persistentDataContainer.has(
                    new NamespacedKey(Backpacks.getInstance(), "items" + i),
                    PersistentDataType.STRING
            )) {
                Material material = Material.valueOf(this.persistentDataContainer.get(
                        new NamespacedKey(Backpacks.getInstance(), "items" + i),
                        PersistentDataType.STRING
                ));

                super.setItem(i, new ItemStack(material));
            }
        }
    }

    @Override
    public void onClose(@Nonnull Player player) {
        ItemStack[] currentItems = this.toInventory().getStorageContents();

        int counter = 0;
        for(ItemStack item : currentItems) {
            String key = "items" + counter;

            if(item != null) {
                this.persistentDataContainer.set(
                        new NamespacedKey(Backpacks.getInstance(), key),
                        PersistentDataType.STRING,
                        item.serialize().get("type").toString()
                );
            } else if(this.persistentDataContainer.has(
                    new NamespacedKey(Backpacks.getInstance(), key),
                    PersistentDataType.STRING
            )) {
                this.persistentDataContainer.remove(new NamespacedKey(Backpacks.getInstance(), key));
            }

            counter += 1;
        }

        this.itemStack.setItemMeta(this.itemMeta);
    }
}
