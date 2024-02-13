package com.dev.shau.inventory;

import com.dev.shau.enums.Mochila;
import com.dev.shau.utils.Utils;
import com.hakan.core.ui.inventory.InventoryGui;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemFlag;

import javax.annotation.Nonnull;
import java.util.HashSet;

/**
 * @author Shau
 */

public class MochilasInventory extends InventoryGui {
    public MochilasInventory() {
        super("mochilas", "Mochilas a venda", 4, InventoryType.CHEST, new HashSet<>());

        super.addOption(Option.CLOSABLE);
        super.addOption(Option.CANCEL_DOWN_CLICK);
        super.addOption(Option.CANCEL_TOP_CLICK);
    }

    @Override
    public void onOpen(@Nonnull Player player) {
        super.setItem(11, Utils.createNamedPlayerHead(
                    Mochila.PEQUENA.getItem().getType(),
                    Mochila.PEQUENA.getID(),
                    Mochila.PEQUENA.getDisplayName(),
                    new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES},
                    Utils.alternativeColors("&7Uma mochila pequena, pode ser um pouco útil"),
                    Utils.alternativeColors("&7Caso você queira partir em aventuras menores"),
                    Utils.alternativeColors("&7E possa ter segurança para guardar poucos items"),
                    "",
                    Utils.alternativeColors("&7Tamanho: &e&lPEQUENO (24x24)"),
                    Utils.alternativeColors("&7Valor de compra: &a&l30.000 COINS"),
                    "",
                    Utils.alternativeColors("&eClique para comprar")
                ),
                event -> {
                    this.openConfirmInventory(player, Mochila.PEQUENA);
                }
        );

        super.setItem(21, Utils.createNamedPlayerHead(
                    Mochila.MEDIA.getItem().getType(),
                    Mochila.MEDIA.getID(),
                    Mochila.MEDIA.getDisplayName(),
                    new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES},
                    ""
                ),
                event -> {
                    this.openConfirmInventory(player, Mochila.MEDIA);
                }
        );

        super.setItem(13, Utils.createNamedPlayerHead(
                Mochila.GRANDE.getItem().getType(),
                Mochila.GRANDE.getID(),
                Mochila.GRANDE.getDisplayName(),
                new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES},
                ""),
                event -> {
                    this.openConfirmInventory(player, Mochila.GRANDE);
                }
        );

        super.setItem(23, Utils.createNamedPlayerHead(
                Mochila.EXTRA_GRANDE.getItem().getType(),
                Mochila.EXTRA_GRANDE.getID(),
                Mochila.EXTRA_GRANDE.getDisplayName(),
                new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES},
                ""),
                event -> {
                    this.openConfirmInventory(player, Mochila.EXTRA_GRANDE);
                }
        );

        super.setItem(15, Utils.createNamedPlayerHead(
                Mochila.GIGANTE.getItem().getType(),
                Mochila.GIGANTE.getID(),
                Mochila.GIGANTE.getDisplayName(),
                new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES},
                ""),
                event -> {
                    this.openConfirmInventory(player, Mochila.GIGANTE);
                }
        );
    }

    private void openConfirmInventory(Player player, Mochila mochila) {
        super.close(player);
        ConfirmInventory confirmInventory = new ConfirmInventory(mochila);
        confirmInventory.open(player);
    }
}
