package com.dev.shau.inventory;

import com.dev.shau.enums.MochilaType;
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
                    MochilaType.PEQUENA.getItem().getType(),
                    MochilaType.PEQUENA.getBase64Texture(),
                    MochilaType.PEQUENA.getDisplayName(),
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
                    this.openConfirmInventory(player, MochilaType.PEQUENA);
                }
        );

        super.setItem(21, Utils.createNamedPlayerHead(
                    MochilaType.MEDIA.getItem().getType(),
                    MochilaType.MEDIA.getBase64Texture(),
                    MochilaType.MEDIA.getDisplayName(),
                    new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES},
                    Utils.alternativeColors("&7Uma mochila média, pode ser um pouco útil"),
                    Utils.alternativeColors("&7Caso você queira partir em aventuras menores"),
                    Utils.alternativeColors("&7E possa ter segurança para guardar poucos items"),
                    "",
                    Utils.alternativeColors("&7Tamanho: &e&lMEDIO (24x24)"),
                    Utils.alternativeColors("&7Valor de compra: &a&l40.000 COINS"),
                    "",
                    Utils.alternativeColors("&eClique para comprar"),
                    ""
                ),
                event -> {
                    this.openConfirmInventory(player, MochilaType.MEDIA);
                }
        );

        super.setItem(13, Utils.createNamedPlayerHead(
                MochilaType.GRANDE.getItem().getType(),
                MochilaType.GRANDE.getBase64Texture(),
                MochilaType.GRANDE.getDisplayName(),
                new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES},
                    Utils.alternativeColors("&7Uma mochila grande, pode ser um pouco útil"),
                    Utils.alternativeColors("&7Caso você queira partir em aventuras menores"),
                    Utils.alternativeColors("&7E possa ter segurança para guardar poucos items"),
                    "",
                    Utils.alternativeColors("&7Tamanho: &e&lGRANDE (24x24)"),
                    Utils.alternativeColors("&7Valor de compra: &a&l50.000 COINS"),
                    "",
                    Utils.alternativeColors("&eClique para comprar"),
                ""),
                event -> {
                    this.openConfirmInventory(player, MochilaType.GRANDE);
                }
        );

        super.setItem(23, Utils.createNamedPlayerHead(
                MochilaType.EXTRA_GRANDE.getItem().getType(),
                MochilaType.EXTRA_GRANDE.getBase64Texture(),
                MochilaType.EXTRA_GRANDE.getDisplayName(),
                new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES},
                Utils.alternativeColors("&7Uma mochila extra grande, pode ser um pouco útil"),
                Utils.alternativeColors("&7Caso você queira partir em aventuras menores"),
                Utils.alternativeColors("&7E possa ter segurança para guardar poucos items"),
                "",
                Utils.alternativeColors("&7Tamanho: &e&lEXTRA GRANDE (24x24)"),
                Utils.alternativeColors("&7Valor de compra: &a&l60.000 COINS"),
                "",
                Utils.alternativeColors("&eClique para comprar"),
                ""),
                event -> {
                    this.openConfirmInventory(player, MochilaType.EXTRA_GRANDE);
                }
        );

        super.setItem(15, Utils.createNamedPlayerHead(
                MochilaType.GIGANTE.getItem().getType(),
                MochilaType.GIGANTE.getBase64Texture(),
                MochilaType.GIGANTE.getDisplayName(),
                new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES},
                Utils.alternativeColors("&7Uma mochila gigante, pode ser um pouco útil"),
                Utils.alternativeColors("&7Caso você queira partir em aventuras menores"),
                Utils.alternativeColors("&7E possa ter segurança para guardar poucos items"),
                "",
                Utils.alternativeColors("&7Tamanho: &e&lGIGANTE (24x24)"),
                Utils.alternativeColors("&7Valor de compra: &a&l70.000 COINS"),
                "",
                Utils.alternativeColors("&eClique para comprar"),
                ""),
                event -> {
                    this.openConfirmInventory(player, MochilaType.GIGANTE);
                }
        );
    }

    private void openConfirmInventory(Player player, MochilaType mochila) {
        super.close(player);
        new ConfirmInventory(mochila).open(player);
    }
}
