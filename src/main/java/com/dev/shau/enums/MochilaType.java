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
    PEQUENA("&a", "Mochila Pequena", 30000, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTE2NWVlMTNhNjA2ZTFiNDQ2OTVhZjQ2YzM5YjUyY2U2NjY1N2E0YzRhNjIzZDBiMjgyYTdiOGNlMDUwOTQwNCJ9fX0="),
    MEDIA("&b", "Mochila Média", 40000, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjMwOGJmNWNjM2U5ZGVjYWYwNzcwYzNmZGFkMWUwNDIxMjFjZjM5Y2MyNTA1YmJiODY2ZTE4YzZkMjNjY2QwYyJ9fX0="),
    GRANDE("&c", "Mochila Grande", 50000, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGRjYzZlYjQwZjNiYWRhNDFlNDMzOTg4OGQ2ZDIwNzQzNzU5OGJkYmQxNzVjMmU3MzExOTFkNWE5YTQyZDNjOCJ9fX0="),
    EXTRA_GRANDE("&9", "Mochila Extra-grande", 60000, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNjMGJmYTg3NWFiOGI4M2Q4ZDk1MTk3NzRjNmM3YzQ1YWQ5YTg4NDNmNjhhNGE1YzAwMDM3NDMyNjBlMmVjNSJ9fX0="),
    GIGANTE("&6", "Mochila Gigante", 70000, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI4MGE4NzFlOTkzOWViMWRlM2I0MGM5YzFmMDVjY2NhODlkYzk2NTQ3YzZlNjZjNjVhNTFiYWU3YTM1NWY4OSJ9fX0=");

    private final String color;
    private final String displayName;
    private final int price;
    private final String base64Texture;
    private final ItemStack item;

    MochilaType(String color, String displayName, int price, String base64Texture) {
        this.color = color;
        this.displayName = Utils.alternativeColors(color + displayName);
        this.price = price;
        this.base64Texture = base64Texture;
        this.item = this.buildItemStack();
    }

    private ItemStack buildItemStack() {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        skullMeta.setDisplayName(this.displayName);
        skullMeta.setLore(Arrays.asList(
            ChatColor.GRAY + "Você ainda pode guardar itens na mochila!",
            ChatColor.GRAY + "Espaço: " + Utils.alternativeColors(this.getColor()) + "\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588 " + ChatColor.GRAY + "(100%)"
        ));
        skullMeta.getPersistentDataContainer().set(
                new NamespacedKey(Backpacks.getInstance(), "mochila"),
                PersistentDataType.STRING,
                this.name()
        );

        Utils.putBase64Texture(skullMeta, this.base64Texture);

        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }
}
