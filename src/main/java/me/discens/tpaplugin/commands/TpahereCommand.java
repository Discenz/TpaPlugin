package me.discens.tpaplugin.commands;

import me.discens.tpaplugin.TpaPlugin;
import me.discens.tpaplugin.api.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpahereCommand implements CommandExecutor {
    private TpaPlugin plugin;

    public TpahereCommand(TpaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Error: Must be a player to use this command.");
            return true;
        }

        Player recipient = Bukkit.getPlayer(args[0]);
        if (recipient == null) {
            sender.sendMessage(ChatColor.RED + "Error: The player could not be found.");
            return true;
        }

        if(plugin.getRequest(recipient) != null) {
            sender.sendMessage("This player already has an active request");
            return true;
        }

        plugin.addRequest((Player) sender, recipient, Type.TPAHERE);
        recipient.sendMessage(sender.getName() + " would like you to teleport to them. Do /tpaccept or /tpdeny");
        sender.sendMessage("Request sent");

        return true;
    }


}