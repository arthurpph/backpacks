package com.dev.shau;

import com.dev.shau.commands.MochilasCmd;
import com.dev.shau.inventory.backpacks.impl.*;
import com.hakan.core.HCore;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Shau
 */

@Getter
public class Backpacks extends JavaPlugin {
    @Getter
    private static Backpacks instance;
    private Economy economy;

    @Override
    public void onEnable() {
        instance = this;

        if (!setupEconomy()) {
            sendConsoleMessage(ChatColor.DARK_RED, "Vault nao encontrado, desativando o plugin");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        sendConsoleMessage(ChatColor.GREEN, "Servidor detectado na versao " + Bukkit.getServer().getClass().getName().split("\\.")[3]);

        HCore.initialize(this);

        this.registerEvents();

        getCommand("mochilas").setExecutor(new MochilasCmd());
    }

    @Override
    public void onDisable() {}

    private void sendConsoleMessage(ChatColor color, String message) {
        getServer().getConsoleSender().sendMessage(color + "[" + getDescription().getName() + "]" + " " + message);
    }

    private boolean setupEconomy() {
        if(getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if(rsp == null) {
            return false;
        }

        this.economy = rsp.getProvider();
        return true;
    }

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

                            ItemStack playerItem = e.getItem();
                            Player player = e.getPlayer();
                            switch (mochila) {
                                case "PEQUENA":
                                    new MochilaPequena(playerItem).open(player);
                                    break;
                                case "MEDIA":
                                    new MochilaMedia(playerItem).open(player);
                                    break;
                                case "GRANDE":
                                    new MochilaGrande(playerItem).open(player);
                                    break;
                                case "EXTRA_GRANDE":
                                    new MochilaExtraGrande(playerItem).open(player);
                                    break;
                                case "GIGANTE":
                                    new MochilaGigante(playerItem).open(player);
                                    break;
                            }
                        }
                    }
                });
    }
}
