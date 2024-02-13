package com.dev.shau.inventory.backpacks.impl;

import com.dev.shau.inventory.backpacks.Mochila;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

/**
 * @author Shau
 */

public class MochilaGrande extends Mochila {
    public MochilaGrande(ItemStack itemStack) {
        super(itemStack, "mochila_grande", "Mochila Grande", 4, InventoryType.CHEST);
    }
}
