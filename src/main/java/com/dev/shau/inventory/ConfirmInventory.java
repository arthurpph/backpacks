package com.dev.shau.inventory;

import com.dev.shau.Backpacks;
import com.dev.shau.enums.MochilaType;
import com.dev.shau.utils.Utils;
import com.hakan.core.ui.inventory.InventoryGui;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemFlag;

import javax.annotation.Nonnull;
import java.util.HashSet;

/**
 * @author Shau
 */

public class ConfirmInventory extends InventoryGui {
    private final MochilaType mochila;

    public ConfirmInventory(MochilaType mochila) {
        super("confirm", "Confirmar compra", 3, InventoryType.CHEST, new HashSet<>());

        super.addOption(Option.CLOSABLE);
        super.addOption(Option.CANCEL_DOWN_CLICK);
        super.addOption(Option.CANCEL_TOP_CLICK);

        this.mochila = mochila;
    }

    @Override
    public void onOpen(@Nonnull Player player) {
        super.setItem(15, Utils.createNamedItem(
                Material.PAPER,
                10093,
                "&4&lNEGAR",
                new ItemFlag[]{},
                ""),
                event -> {
                    super.close(player);
                }
        );

        super.setItem(11, Utils.createNamedItem(
                Material.PAPER,
                10094,
                "&a&lCONFIRMAR",
                new ItemFlag[]{},
                ""),
                event -> {
                    Economy economy = Backpacks.getInstance().getEconomy();

                    super.close(player);

                    if(economy.getBalance(player) < this.mochila.getPrice()) {
                        player.sendMessage(Utils.alternativeColors("&cVocê não tem dinheiro suficiente para comprar isso!"));
                        return;
                    }

                    economy.withdrawPlayer(player, this.mochila.getPrice());
                    player.getInventory().addItem(this.mochila.getItem());
                }
        );
    }
}
