package com.dev.shau;

import com.dev.shau.commands.MochilasCmd;
import com.dev.shau.inventory.backpacks.MochilaPequena;
import com.dev.shau.utils.Utils;
import com.hakan.core.HCore;
import com.hakan.core.ui.inventory.InventoryGui;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Shau
 */

public class Backpacks extends JavaPlugin {
    @Getter
    private static Backpacks instance;

    @Override
    public void onEnable() {
        instance = this;

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[" + getDescription().getName() + "]" + " Servidor detectado na versao " + Bukkit.getServer().getClass().getName().split("\\.")[3]);

        HCore.initialize(this);

        this.registerEvents();

        getCommand("mochilas").setExecutor(new MochilasCmd());
    }

    @Override
    public void onDisable() {}

    private void registerEvents() {
        HCore.registerEvent(PlayerInteractEvent.class)
                .consume(e -> {
                    if(e.getItem() == null) {
                        return;
                    }

                    if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        e.setCancelled(true);

                        ItemMeta itemMeta = e.getItem().getItemMeta();
                        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
                        if(persistentDataContainer.has(new NamespacedKey(this, "mochila"), PersistentDataType.STRING)) {
                            String mochila = persistentDataContainer.get(new NamespacedKey(this, "mochila"), PersistentDataType.STRING);

                            MochilaPequena mochilaPequena = new MochilaPequena(e.getItem());
                            mochilaPequena.open(e.getPlayer());
                        }
                    }
                });
    }

}
