package com.dev.shau.inventory.backpacks.impl;

import com.dev.shau.enums.MochilaType;
import com.dev.shau.inventory.backpacks.Mochila;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

/**
 * @author Shau
 */

public class MochilaMedia extends Mochila {
    public MochilaMedia(ItemStack itemStack) {
        super(MochilaType.MEDIA, itemStack, "mochila_media", "Mochila Média", 3, InventoryType.CHEST);
    }
}
