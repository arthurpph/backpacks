package com.dev.shau.inventory.backpacks;

import com.dev.shau.Backpacks;
import com.dev.shau.enums.MochilaType;
import com.dev.shau.utils.Utils;
import com.hakan.core.ui.inventory.InventoryGui;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
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

public class Mochila extends InventoryGui implements Listener {
    protected final MochilaType mochila;
    protected final ItemStack itemStack;
    protected final ItemMeta itemMeta;
    protected final PersistentDataContainer persistentDataContainer;

    public Mochila(MochilaType mochila, ItemStack itemStack, String id, String title, int size, InventoryType type) {
        super(id, title, size, type, new HashSet<>());
        super.addOption(Option.CLOSABLE);

        this.mochila = mochila;
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
        this.persistentDataContainer = itemMeta.getPersistentDataContainer();

        Bukkit.getPluginManager().registerEvents(this, Backpacks.getInstance());
    }

    @Override
    public void onOpen(@Nonnull Player player) {
        for(int i = 0; i < this.inventory.getSize(); i++) {
            if(this.persistentDataContainer.has(
                    new NamespacedKey(Backpacks.getInstance(), "items" + i),
                    PersistentDataType.STRING
            )) {
                String[] item = this.persistentDataContainer.get(
                        new NamespacedKey(Backpacks.getInstance(), "items" + i),
                        PersistentDataType.STRING
                ).split("x");

                String type = item[0];
                String amount = item[1];

                Material material = Material.valueOf(type);

                super.setItem(i, new ItemStack(material, Integer.parseInt(amount)));
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
                String itemType = item.serialize().get("type").toString();

                if(itemType.equals("PLAYER_HEAD")) {
                    player.getInventory().addItem(item);
                    player.sendMessage(Utils.alternativeColors("&cVocê não pode guardar uma mochila dentro de outra!"));
                    continue;
                }

                this.persistentDataContainer.set(
                        new NamespacedKey(Backpacks.getInstance(), key),
                        PersistentDataType.STRING,
                        itemType + "x" + item.getAmount()
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

        Utils.changeBackpackLore(this.itemStack, currentItems, this.toInventory(), this.mochila);
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(this.getTitle())) {
            ItemStack[] currentItems = this.toInventory().getStorageContents();
            Utils.changeBackpackLore(this.itemStack, currentItems, this.toInventory(), this.mochila);
        }
    }
}
