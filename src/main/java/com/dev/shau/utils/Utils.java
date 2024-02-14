package com.dev.shau.utils;

import com.dev.shau.enums.MochilaType;
import com.hakan.core.item.ItemBuilder;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Shau
 */

public class Utils {
    public static ItemStack createNamedPlayerHead(Material material, String base64Texture, String displayName, ItemFlag[] flags, String... lore) {
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

        putBase64Texture(skullMeta, base64Texture);

        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    public static void putBase64Texture(SkullMeta skullMeta, String base64Texture) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64Texture));

        try {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static ItemStack createNamedItem(Material material, int ID, String displayName, ItemFlag[] flags, String... lore) {
        ItemBuilder itemBuilder = new ItemBuilder(material);
        itemBuilder.name(displayName);

        if(flags.length > 0) {
            itemBuilder.addItemFlags(flags);
        }

        if(!Objects.equals(lore[0], "")) {
            itemBuilder.lores(Arrays.asList(lore));
        }

        ItemStack itemStack = itemBuilder.build();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setCustomModelData(ID);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static String alternativeColors(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String calculateSquareBlocks(MochilaType mochilaType, int percentageLeft) {
        StringBuilder squareBlocks = new StringBuilder();

        for(int i = 0; i < percentageLeft / 10; i++) {
            if(i == 0) {
                squareBlocks.append(Utils.alternativeColors(mochilaType.getColor()));
            }

            squareBlocks.append("\u2588");
        }

        for(int i = 0; i < (100 - percentageLeft) / 10; i++) {
            if(i == 0) {
                squareBlocks.append(ChatColor.GRAY);
            }

            squareBlocks.append("\u2588");
        }

        return squareBlocks.toString();
    }

    public static void changeBackpackLore(ItemStack itemStack, ItemStack[] contents, Inventory inventory, MochilaType mochila) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        int itemsCounter = 0;

        for (ItemStack item : contents) {
            if (item != null) {
                itemsCounter += 1;
            }
        }

        int slotsEmpty = inventory.getSize() - itemsCounter;
        double spaceLeft = ((double) slotsEmpty / inventory.getSize()) * 100;
        int spaceRounded = (int) (Math.round(spaceLeft / 10.0) * 10);
        int spaceFixed = spaceRounded == 0 && spaceLeft > 0 ? 10 : spaceRounded;

        String squareBlocks = calculateSquareBlocks(mochila, spaceFixed);
        String spaceLeftMessage = ChatColor.GRAY + "Espaço: " + squareBlocks + ChatColor.GRAY + " (" + (spaceFixed) + "%)";

        if (spaceFixed > 0) {
            itemMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "Você ainda pode guardar itens na mochila!",
                    spaceLeftMessage
            ));
        } else {
            itemMeta.setLore(Arrays.asList(
                    spaceLeftMessage,
                    "",
                    Utils.alternativeColors("&c&lATENÇÃO: &cSua mochila está totalmente cheia!")
            ));
        }

        itemStack.setItemMeta(itemMeta);
    }
}
