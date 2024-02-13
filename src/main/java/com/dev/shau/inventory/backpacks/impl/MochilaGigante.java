package com.dev.shau.inventory.backpacks.impl;

import com.dev.shau.inventory.backpacks.Mochila;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

/**
 * @author Shau
 */

public class MochilaGigante extends Mochila {
    public MochilaGigante(ItemStack itemStack) {
        super(itemStack, "mochila_gigante", "Mochila Gigante", 6, InventoryType.CHEST);
    }
}
