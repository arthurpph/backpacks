package com.dev.shau.inventory;

import com.dev.shau.enums.Mochila;
import com.dev.shau.utils.Utils;
import com.hakan.core.ui.inventory.InventoryGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import javax.annotation.Nonnull;
import java.util.HashSet;

/**
 * @author Shau
 */

public class ConfirmInventory extends InventoryGui {
    private final Mochila mochila;

    public ConfirmInventory(Mochila mochila) {
        super("confirm", "Confirmar compra", 0, InventoryType.HOPPER, new HashSet<>());

        super.addOption(Option.CLOSABLE);
        super.addOption(Option.CANCEL_DOWN_CLICK);
        super.addOption(Option.CANCEL_TOP_CLICK);

        this.mochila = mochila;
    }

    @Override
    public void onOpen(@Nonnull Player player) {
        super.setItem(1, Utils.createNamedItem(
                Material.RED_WOOL,
                "&4NEGAR",
                new ItemFlag[]{},
                ""),
                event -> {
                    super.close(player);
                }
        );

        super.setItem(3, Utils.createNamedItem(
                Material.EMERALD_BLOCK,
                "&aCONFIRMAR",
                new ItemFlag[]{},
                ""),
                event -> {
                    super.close(player);
                    player.getInventory().addItem(this.mochila.getItem());
                }
        );
    }
}
