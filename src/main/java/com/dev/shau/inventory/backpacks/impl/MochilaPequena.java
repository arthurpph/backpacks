package com.dev.shau.inventory.backpacks.impl;

import com.dev.shau.enums.MochilaType;
import com.dev.shau.inventory.backpacks.Mochila;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

/**
 * @author Shau
 */

public class MochilaPequena extends Mochila {
    public MochilaPequena(ItemStack itemStack) {
        super(MochilaType.PEQUENA, itemStack, "mochila_pequena", "Mochila Pequena", 2, InventoryType.CHEST);
    }
}
