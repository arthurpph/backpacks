package com.dev.shau.commands;

import com.dev.shau.inventory.MochilasInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Shau
 */

public class MochilasCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        MochilasInventory mochilasInventory = new MochilasInventory();
        mochilasInventory.open((Player) sender);
        return false;
    }
}
