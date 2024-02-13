package com.dev.shau.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hakan.core.item.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.*;
import java.util.*;

/**
 * @author Shau
 */

public class Utils {
    private static final Gson gson = new Gson();

    public static ItemStack createNamedPlayerHead(Material material, int OwnerID, String displayName, ItemFlag[] flags, String... lore) {
        ItemBuilder itemBuilder = new ItemBuilder(material);
        itemBuilder.name(displayName);

        if(flags.length > 0) {
            itemBuilder.addItemFlags(flags);
        }

        if(!Objects.equals(lore[0], "")) {
            itemBuilder.lores(Arrays.asList(lore));
        }

        ItemStack itemStack = itemBuilder.build();
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(String.valueOf(OwnerID));
        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    public static ItemStack createNamedItem(Material material, String displayName, ItemFlag[] flags, String... lore) {
        ItemBuilder itemBuilder = new ItemBuilder(material);
        itemBuilder.name(displayName);

        if(flags.length > 0) {
            itemBuilder.addItemFlags(flags);
        }

        if(!Objects.equals(lore[0], "")) {
            itemBuilder.lores(Arrays.asList(lore));
        }

        return itemBuilder.build();
    }

    public static String alternativeColors(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
