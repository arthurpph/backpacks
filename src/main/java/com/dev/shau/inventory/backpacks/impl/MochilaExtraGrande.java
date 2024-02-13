package com.dev.shau.inventory.backpacks.impl;

import com.dev.shau.inventory.backpacks.Mochila;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

/**
 * @author Shau
 */

public class MochilaExtraGrande extends Mochila {
    public MochilaExtraGrande(ItemStack itemStack) {
        super(itemStack, "mochila_extragrande", "Mochila Extra Grande", 5, InventoryType.CHEST);
    }
}
