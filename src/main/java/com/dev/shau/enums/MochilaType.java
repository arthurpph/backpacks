package com.dev.shau.enums;

import com.dev.shau.Backpacks;
import com.dev.shau.utils.Utils;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

/**
 * @author Shau
 */

@Getter
public enum MochilaType {
    PEQUENA("&a", "Mochila Pequena", 52081),
    MEDIA("&b", "Mochila Média", 46547),
    GRANDE("&c", "Mochila Grande", 30768),
    EXTRA_GRANDE("&9", "Mochila Extra-grande", 40968),
    GIGANTE("&6", "Mochila Gigante", 69235);

    private final String color;
    private final String displayName;
    private final int ID;
    private final ItemStack item;

    MochilaType(String color, String displayName, int ID) {
        this.color = color;
        this.displayName = Utils.alternativeColors(color + displayName);
        this.ID = ID;
        this.item = this.buildItemStack();
    }

    private ItemStack buildItemStack() {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        skullMeta.setDisplayName(this.displayName);
        skullMeta.setLore(Arrays.asList(
            ChatColor.GRAY + "Você ainda pode guardar itens na mochila!",
            Utils.alternativeColors(this.color + "100■■■■■■■■")
        ));
        skullMeta.getPersistentDataContainer().set(
                new NamespacedKey(Backpacks.getInstance(), "mochila"),
                PersistentDataType.STRING,
                this.name()
        );

        skullMeta.setOwner(String.valueOf(this.ID));
        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }
}
