package com.dev.shau.inventory.backpacks;

import com.dev.shau.Backpacks;
import com.dev.shau.enums.MochilaType;
import com.dev.shau.utils.Utils;
import com.hakan.core.ui.inventory.InventoryGui;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Shau
 */

public class Mochila extends InventoryGui {
    protected final MochilaType mochila;
    protected final ItemStack itemStack;
    protected final ItemMeta itemMeta;
    protected final PersistentDataContainer persistentDataContainer;

    public Mochila(MochilaType mochila, ItemStack itemStack, String id, String title, int size, InventoryType type) {
        super(id, title, size, type, new HashSet<>());
        super.addOption(Option.CLOSABLE);

        this.mochila = mochila;
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
        this.persistentDataContainer = itemMeta.getPersistentDataContainer();
    }

    @Override
    public void onOpen(@Nonnull Player player) {
        for(int i = 0; i < this.inventory.getSize(); i++) {
            if(this.persistentDataContainer.has(
                    new NamespacedKey(Backpacks.getInstance(), "items" + i),
                    PersistentDataType.STRING
            )) {
                Material material = Material.valueOf(this.persistentDataContainer.get(
                        new NamespacedKey(Backpacks.getInstance(), "items" + i),
                        PersistentDataType.STRING
                ));

                super.setItem(i, new ItemStack(material));
            }
        }
    }

    @Override
    public void onClose(@Nonnull Player player) {
        ItemStack[] currentItems = this.toInventory().getStorageContents();

        int itemsCounter = 0;

        int counter = 0;
        for(ItemStack item : currentItems) {
            String key = "items" + counter;

            if(item != null) {
                itemsCounter += 1;

                this.persistentDataContainer.set(
                        new NamespacedKey(Backpacks.getInstance(), key),
                        PersistentDataType.STRING,
                        item.serialize().get("type").toString()
                );
            } else if(this.persistentDataContainer.has(
                    new NamespacedKey(Backpacks.getInstance(), key),
                    PersistentDataType.STRING
            )) {
                this.persistentDataContainer.remove(new NamespacedKey(Backpacks.getInstance(), key));
            }

            counter += 1;
        }

        int slotsEmpty = this.inventory.getSize() - itemsCounter;
        double spaceLeft = ((double) slotsEmpty / this.inventory.getSize()) * 100;
        int spaceRounded = spaceLeft > 0 ? (int) (Math.round(spaceLeft / (double) 10 + 0.5) * 10) : 0;
        int spaceFixed = 100 - spaceRounded;

        String squareBlocks = Utils.calculateSquareBlocks(this.mochila, 100 - spaceRounded);

        String spaceLeftMessage = ChatColor.GRAY + "Espaço: " + squareBlocks + " (" + (Math.max(spaceFixed, 0)) + "%)";
        if(spaceFixed < 100) {
            this.itemMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "Você ainda pode guardar itens na mochila!",
                    spaceLeftMessage
            ));
        } else {
            this.itemMeta.setLore(Arrays.asList(
                    spaceLeftMessage,
                    "",
                    Utils.alternativeColors("&c&lATENÇÃO: &cSua mochila está totalmente cheia!")
            ));
        }

        this.itemStack.setItemMeta(this.itemMeta);
    }
}
