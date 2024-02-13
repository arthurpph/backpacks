package com.dev.shau.inventory.backpacks;

import com.dev.shau.Backpacks;
import com.dev.shau.utils.Utils;
import com.hakan.core.ui.inventory.InventoryGui;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author Shau
 */

public class MochilaPequena extends InventoryGui implements Listener {
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    private final PersistentDataContainer persistentDataContainer;

    public MochilaPequena(ItemStack itemStack) {
        super("mochila_pequena", "Mochila Pequena", 2, InventoryType.CHEST, new HashSet<>());
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
