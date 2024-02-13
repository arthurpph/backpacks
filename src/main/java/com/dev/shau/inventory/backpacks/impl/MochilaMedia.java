package com.dev.shau.inventory.backpacks.impl;

import com.dev.shau.inventory.backpacks.Mochila;
import com.hakan.core.ui.inventory.InventoryGui;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

/**
 * @author Shau
 */

public class MochilaMedia extends Mochila {
    public MochilaMedia(ItemStack itemStack) {
        super(itemStack, "mochila_media", "Mochila Média", 3, InventoryType.CHEST);
    }
}
