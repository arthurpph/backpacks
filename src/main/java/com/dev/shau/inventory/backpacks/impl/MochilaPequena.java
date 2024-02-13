package com.dev.shau.inventory.backpacks.impl;

import com.dev.shau.inventory.backpacks.Mochila;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

/**
 * @author Shau
 */

public class MochilaPequena extends Mochila {
    public MochilaPequena(ItemStack itemStack) {
        super(itemStack, "mochila_pequena", "Mochila Pequena", 2, InventoryType.CHEST);
    }
}
