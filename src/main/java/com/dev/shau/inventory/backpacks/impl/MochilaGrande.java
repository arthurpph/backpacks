package com.dev.shau.inventory.backpacks.impl;

import com.dev.shau.enums.MochilaType;
import com.dev.shau.inventory.backpacks.Mochila;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

/**
 * @author Shau
 */

public class MochilaGrande extends Mochila {
    public MochilaGrande(ItemStack itemStack) {
        super(MochilaType.GRANDE, itemStack, "mochila_grande", "Mochila Grande", 4, InventoryType.CHEST);
    }
}
